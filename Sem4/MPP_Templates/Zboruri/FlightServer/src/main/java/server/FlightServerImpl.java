package server;

import flight.domain.*;
import flight.domain.*;
import flight.domain.Angajat;
import flight.domain.Bilet;
import flight.domain.Zbor;
import flight.domain.ZborDTO;
import flight.repository.AngajatRepository;
import flight.repository.BiletRepoInterface;
import flight.repository.ClientRepoInterface;
import flight.repository.ZborRepoInterface;
import services.FlightException;
import services.IFlightObserver;
import services.IFlightServer;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class FlightServerImpl implements IFlightServer{
    private AngajatRepository repoAngajat;
    private ZborRepoInterface repoZbor;
    private ClientRepoInterface repoClient;
    private BiletRepoInterface repoBilet;
    private Map<String, IFlightObserver> loggedAngajati;

    public FlightServerImpl(AngajatRepository repoAngajat, ZborRepoInterface repoZbor, ClientRepoInterface repoClient, BiletRepoInterface repoBilet) {
        this.repoAngajat = repoAngajat;
        this.repoZbor = repoZbor;
        this.repoClient = repoClient;
        this.repoBilet = repoBilet;
        loggedAngajati = new ConcurrentHashMap<>();
    }

    @Override
    public void logIn(Angajat angajat, IFlightObserver observer) throws FlightException {
        Angajat angajatR = repoAngajat.logIn(angajat.getID(), angajat.getPassword());
        if (angajatR != null) {
            if(loggedAngajati.get(angajat.getID())!=null)
                throw new FlightException("Employee already logged in!");
            loggedAngajati.put(angajat.getID(),observer);
        } else
            throw new FlightException("Authentication failed!");
    }

    @Override
    public Iterable<Zbor> getZboruri() {
        Iterable<Zbor> it = repoZbor.findAll();
        List<Zbor> zboruri = new ArrayList<>();
        it.forEach(x -> zboruri.add(x));
        return zboruri.stream()
                .filter(p -> p.getLocuri() > 0).collect(Collectors.toList());
    }

    @Override
    public Iterable<ZborDTO> getZboruriCautare(String destinatie, String plecare) {
        Iterable<Zbor> it = repoZbor.findByDestinatieAndPlecare(destinatie, plecare);
        List<Zbor> zboruri = new ArrayList();
        it.forEach(x -> zboruri.add(x));
        List<ZborDTO> zboruriDTO = new ArrayList<>();
        zboruri.forEach(
                x -> {
                    int hour = x.getPlecare().getHour();
                    int minute = x.getPlecare().getMinute();
                    if (x.getLocuri() > 0)
                        zboruriDTO.add(new ZborDTO(LocalTime.of(hour, minute), x.getLocuri()));
                });
        zboruriDTO.forEach(x -> System.out.println(x));
        return zboruriDTO;
    }

    @Override
    public void zborUpdate(Integer zborId, Integer locuri){
        repoZbor.updateZbor(zborId, locuri);
    }


    @Override
    public Client findClient(String numeClient, String adresaClient) {
        return repoClient.findOneByNumeAndAdress(numeClient, adresaClient);
    }

    @Override
    public Zbor findZbor(String destinatie, String data, String ora) {
        return repoZbor.findOneByDestinatieAndDateTime(destinatie, data + "T" + ora);
    }

    @Override
    public Integer findIndex() {
        return repoBilet.findCorrectIndex();
    }

    @Override
    public void cumparaBilet(Bilet bilet, int id) {
        repoBilet.save(bilet);
        Zbor z = repoZbor.findOne(id);
        repoZbor.updateZbor(id, z.getLocuri()-bilet.getLocuri());
        notifyAllAngajati();
    }

    private void notifyAllAngajati() {
        Iterable<Angajat> angajati=repoAngajat.findAll();
        for(Angajat angajat :angajati){
            IFlightObserver client = loggedAngajati.get(angajat.getID());
            if (client!=null){
                System.out.println("Notifying"+ angajat.getID());
                Iterable<Zbor> zboruri = repoZbor.findAll();
                try {
                    client.ticketBought(zboruri);
                } catch (FlightException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void logOut(Angajat angajat, IFlightObserver observer) throws FlightException {
        IFlightObserver localClient = loggedAngajati.remove(angajat.getID());
        if(localClient == null)
            throw new FlightException("Employee" + angajat.getID() + "is not logged in");
    }


}
