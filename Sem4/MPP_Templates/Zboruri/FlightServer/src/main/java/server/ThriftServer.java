package server;

import flight.domain.Client;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import flight.repository.AngajatRepository;
import flight.repository.BiletRepoInterface;
import flight.repository.ClientRepoInterface;
import flight.repository.ZborRepoInterface;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ThriftServer implements ServiceThrift.Iface{
    private AngajatRepository repoAngajat;
    private ZborRepoInterface repoZbor;
    private ClientRepoInterface repoClient;
    private BiletRepoInterface repoBilet;
    private Map<String, Integer> loggedAngajati;
    private List<Integer> ports = new ArrayList<>();
    private List<MainWindowController.Client> observers = new ArrayList<>();

    public ThriftServer(AngajatRepository repoAngajat, ZborRepoInterface repoZbor, ClientRepoInterface repoClient, BiletRepoInterface repoBilet) {
        this.repoAngajat = repoAngajat;
        this.repoZbor = repoZbor;
        this.repoClient = repoClient;
        this.repoBilet = repoBilet;
        loggedAngajati = new ConcurrentHashMap<>();
    }

    @Override
    public void addObserver(int port) throws TTransportException {
        TTransport transport = new TSocket("localhost",port);
        transport.open();
        TProtocol protocol = new TBinaryProtocol(transport);
        MainWindowController.Client client = new MainWindowController.Client(protocol);
        ports.add(port);
        observers.add(client);
    }

    public void removeObserver(int port) {
        int index = ports.indexOf(port);
        ports.remove(index);
        observers.remove(index);
    }

    @Override
    public void logIn(Angajat angajat) throws TException {
        flight.domain.Angajat angajatR = repoAngajat.logIn(angajat.getID(), angajat.getPassword());
        if (angajatR != null){
            Integer obs = loggedAngajati.putIfAbsent(angajat.getID(),5560);
            if(obs != null)
                System.out.println("deja conectat");
            else
                System.out.println("OK!");
        }
    }

    @Override
    public void logOut(int port) throws TException {
        removeObserver(port);
    }

    @Override
    public List<Zbor> getZboruri() throws TException {
        List<Zbor> zboruri = new ArrayList<>();
        repoZbor.findAll().forEach(x->{
            Zbor z = new Zbor(x.getId(),x.getDestinatie(),x.getPlecare().toString(),x.getAeroport(),x.getLocuri());
            zboruri.add(z);
        });
        return zboruri;
    }

    @Override
    public List<ZborDTO> getZboruriCautare(String destinatie, String dataCorecta) throws TException {
        Iterable<flight.domain.Zbor> it = repoZbor.findByDestinatieAndPlecare(destinatie, dataCorecta);
        List<flight.domain.Zbor> zboruri = new ArrayList();
        it.forEach(x -> zboruri.add(x));
        List<ZborDTO> zboruriDTO = new ArrayList<>();
        zboruri.forEach(
                x -> {
                    Integer hour = x.getPlecare().getHour();
                    Integer minute = x.getPlecare().getMinute();
                    Integer second = x.getPlecare().getSecond();
                    String s = hour.toString()+":"+minute.toString()+":"+second.toString();
                    if (x.getLocuri() > 0)
                        zboruriDTO.add(new ZborDTO(s, x.getLocuri()));
                });
        return zboruriDTO;
    }

    @Override
    public Clientulet findClient(String nume, String adresa) throws TException {
        Client clientD =  repoClient.findOneByNumeAndAdress(nume,adresa);
        return new Clientulet(clientD.getID(),clientD.getNume(),clientD.getAdresa());
    }

    @Override
    public Zbor findZbor(String destinatie, String data, String ora) throws TException {
        flight.domain.Zbor zbor = repoZbor.findOneByDestinatieAndDateTime(destinatie,data+"T"+ora);
        return new Zbor(zbor.getId(),zbor.getDestinatie(),zbor.getPlecare().toString(),zbor.getAeroport(),zbor.getLocuri());
    }

    @Override
    public int findIndex() throws TException {
        return repoBilet.findCorrectIndex();
    }

    @Override
    public void cumparaBilet(Bilet bilet, int ZborId, int locuri) throws TException {
        repoBilet.save(new flight.domain.Bilet(bilet.ID,bilet.ZborId,bilet.ClientId,bilet.turisti,bilet.locuri));
        repoZbor.updateZbor(ZborId, locuri);
    }

    @Override
    public void notifyServer() throws TException {
         for(MainWindowController.Client client : observers) {
             client.Update(getZboruri());
         }
    }


}
