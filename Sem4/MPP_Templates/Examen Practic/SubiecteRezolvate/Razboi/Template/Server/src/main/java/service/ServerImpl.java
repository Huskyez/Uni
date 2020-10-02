package service;

import model.Carte;
import model.Jucator;
import repository.JucatorRepository;
import utils.AnException;
import utils.IObserver;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class ServerImpl implements IServerImpl{
    private JucatorRepository jucatorRepository;
    private Map<Jucator, IObserver> observers = new ConcurrentHashMap<>();
    private Map<Jucator, IObserver> jucatoriActivi = new ConcurrentHashMap<>();
    private Carte[] carti = {new Carte("6"),new Carte("7"),new Carte("8"),new Carte("9")
                            ,new Carte("J"),new Carte("Q"),new Carte("K"),new Carte("A")};
    private Random rand = new Random();
    private boolean jocActiv = false;
    private Map<Jucator, Carte> cartiJucatori = new ConcurrentHashMap<>();


    public ServerImpl(JucatorRepository jucatorRepository) {
        this.jucatorRepository = jucatorRepository;
    }

//    public void saveUser(Jucator user){
//        jucatorRepository.save(user);
//    }

    public boolean login(Jucator jucator, IObserver observer) throws AnException{
        Jucator jucator1 = jucatorRepository.findOne(jucator.getUsername());
        if(jucator1 != null) {
            boolean logat = jucator1.getPassword().equals(jucator.getPassword());
            if (logat) {
                if(observers.containsKey(jucator1))
                    throw new AnException("Already logged in!");
                observers.putIfAbsent(jucator, observer);
            }
            return logat;
        }else
            throw new AnException("Wrong credentials!");
    }

    @Override
    public void logout(Jucator jucator) {
        observers.remove(jucator);
    }

    @Override
    public void inceputJoc() {
        jocActiv = true;
        Iterable<Jucator> totiJucatorii = jucatorRepository.findAll();
        List<Jucator> jucatorii = new ArrayList<>();
        totiJucatorii.forEach(x->jucatorii.add(x));
        for(Jucator jucator:totiJucatorii){
            IObserver observer = observers.get(jucator);
            if(observer != null){
                try{
                    jucatoriActivi.put(jucator,observer);
                    ArrayList<Jucator> jucators = new ArrayList<>(observers.keySet());
                    jucators.remove(jucator);
                    observer.inceputJoc(jucators);
                    observer.primesteCarti(daCarti());
                }catch (RemoteException e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean getJocStatus() {
        return jocActiv;
    }

    @Override
    public void amAlesCarte(Carte carte, Jucator jucator, IObserver observer) {
        cartiJucatori.put(jucator,carte);
        if(cartiJucatori.size() == jucatoriActivi.size())
            verificareCastigator();
    }

    private void verificareCastigator() {
        Carte[] needed = new Carte[9];
        cartiJucatori.values().forEach(x->{
            if(x.getSimbol().equals(carti[0].getSimbol()))
                needed[0] = x;
            if(x.getSimbol().equals(carti[1].getSimbol()))
                needed[1] = x;
            if(x.getSimbol().equals(carti[2].getSimbol()))
                needed[2] = x;
            if(x.getSimbol().equals(carti[3].getSimbol()))
                needed[3] = x;
            if(x.getSimbol().equals(carti[4].getSimbol()))
                needed[4] = x;
            if(x.getSimbol().equals(carti[5].getSimbol()))
                needed[5] = x;
            if(x.getSimbol().equals(carti[6].getSimbol()))
                needed[6] = x;
            if(x.getSimbol().equals(carti[7].getSimbol()))
                needed[7] = x;
        });
        int i = needed.length-1;
        while (needed[i] == null)
            i--;
        Carte castigatoare = needed[i];
        sfarsitRunda(castigatoare);
    }

    private void sfarsitRunda(Carte castigatoare) {
        ArrayList<Carte> cartiJucate = new  ArrayList<Carte>(cartiJucatori.values());
        Jucator j1 = null;
        for (Jucator jucator:cartiJucatori.keySet()) {
            if(cartiJucatori.get(jucator).equals(castigatoare))
                j1 = jucator;
        }
        notificaJucatori(j1);
    }

    private void notificaJucatori(Jucator j1) {
        Iterable<Jucator> totiJucatorii = jucatorRepository.findAll();
        List<Jucator> jucatorii = new ArrayList<>();
        totiJucatorii.forEach(x->jucatorii.add(x));
        for(Jucator jucator:totiJucatorii){
            IObserver observer = jucatoriActivi.get(jucator);
            if(observer != null){
                if(jucator.equals(j1)) {
                    try {
                        observer.endOfRound(true, new ArrayList<>(cartiJucatori.values()));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    observer.endOfRound(false, new ArrayList<>(cartiJucatori.values()));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private List<Carte> daCarti(){
        List<Carte> cartiDate = new ArrayList<>();

        Carte carte = carti[rand.nextInt(7)];

        while(cartiDate.size()<4){
            if(!cartiDate.contains(carte))
                cartiDate.add(carte);
            carte = carti[rand.nextInt(7)];
        }
        return cartiDate;
    }
}
