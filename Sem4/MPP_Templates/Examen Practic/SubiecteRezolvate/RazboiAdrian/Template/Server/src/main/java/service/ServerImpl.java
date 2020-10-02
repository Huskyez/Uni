package service;

import model.Carte;
import model.Joc;
import model.User;
import repository.JocRepository;
import repository.UserRepository;
import utils.IObserver;
import utils.MyRandom;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServerImpl implements IServerImpl{
    private JocRepository jocRepository;
    private UserRepository userRepository;
    private Map<User, IObserver> observers = new ConcurrentHashMap<>();
    private List<User> playingUsers;
    private Map<User, List<Carte>> listaCartiJucatori = new HashMap<>();
    private Map<User, List<Carte>> listaCartiServerInitial = new HashMap<>();
    private Map<User, Carte> listaOTuraDeJoc = new HashMap<>();
    private List<Carte> listaCarti;
    boolean jocActiv = false;
    int raspunsuriPrimite = 0;
    private static MyRandom random = MyRandom.getInstance();

    public ServerImpl(JocRepository jocRepository, UserRepository userRepository) {
        this.jocRepository = jocRepository;
        this.userRepository = userRepository;
        listaCarti = new ArrayList<>();
        listaCarti.add(new Carte("6"));
        listaCarti.add(new Carte("7"));
        listaCarti.add(new Carte("8"));
        listaCarti.add(new Carte("9"));
        listaCarti.add(new Carte("J"));
        listaCarti.add(new Carte("Q"));
        listaCarti.add(new Carte("K"));
        listaCarti.add(new Carte("A"));
    }

    public void saveUser(User user){
        userRepository.save(user);
    }
    public boolean login(User user, IObserver observer){
        if(userRepository.findOne(user.getUsername()).getPassword().equals(user.getPassword())){
            observers.putIfAbsent(user, observer);
            return true;
        }
        return false;
    }

    @Override
    public void logout(User user) {
        observers.remove(user);
    }

    @Override
    public void startJoc() throws RemoteException {
        if(!jocActiv && observers.size() > 1){
            playingUsers = new ArrayList<>();
            for(User u : observers.keySet()){
                playingUsers.add(u);
            }
            jocActiv = true;
            genereazaPachetCarti();
        }
        else{
            //intra in asteptare
        }
    }



    private void genereazaPachetCarti() throws RemoteException {
        for(User u : playingUsers){
            List<Carte> pachetCarti = new ArrayList<>();
            String carti = ""; //pt a ver ca nu se repeta cartile
            pachetCarti.add(listaCarti.get(random.getRandomInt(0,7)));
            carti += pachetCarti.get(0).getNume();

            Carte carte = listaCarti.get(random.getRandomInt(0,7));
            while(pachetCarti.size() < 4){
                if(!carti.contains(carte.getNume())){
                    pachetCarti.add(carte);
                    carti += carte.getNume();
                }
                else {
                    carte = listaCarti.get(random.getRandomInt(0,7));
                }
                carte = listaCarti.get(random.getRandomInt(0,7));
            }
            notifyJucatoriLogatiSiPachetCarti(u, playingUsers, pachetCarti);
            listaCartiServerInitial.put(u, new ArrayList<>(pachetCarti)); //lista cu pachetul initial
            listaCartiJucatori.put(u, pachetCarti); //retinem pachetul initial de carti
        }
    }
    @Override
    public void notifyJucatoriLogatiSiPachetCarti(User u, List<User> playingUsers, List<Carte> pachetCarti) throws RemoteException {
        observers.get(u).updateJucatoriLogatiSiPachetCarti(playingUsers, pachetCarti);
    }

    @Override
    public void primesteCarti(User user, Carte carte) throws RemoteException {
        raspunsuriPrimite++;
        listaOTuraDeJoc.put(user, carte);
        if(raspunsuriPrimite == playingUsers.size()){
            notifyStergeCarteaPrimita();
            List<Carte> cartiTuraJucatori = new ArrayList<>();
            for(User u : playingUsers){
                cartiTuraJucatori.add(listaOTuraDeJoc.get(u));
            }
            notifyCartiAdversari(cartiTuraJucatori);
            calculeazaCastigatorTura();
            if(jocActiv)
                notifyTuraNoua();
            raspunsuriPrimite = 0;
        }
    }

    private void notifyTuraNoua() throws RemoteException {
        for(User u : playingUsers){
            observers.get(u).alegereCarteNoua();
        }
    }

    private void calculeazaCastigatorTura() throws RemoteException {

        List<Integer> vectorAparitii = new ArrayList<>();
        int i;
        for(i = 0 ; i < listaCarti.size(); i++){
            vectorAparitii.add(i, 0);
        }
        int poz = 0;
        int maxim = 0;
        for(User u : playingUsers){
            Carte c = listaOTuraDeJoc.get(u);
            if(listaCarti.contains(c)){
                vectorAparitii.set(listaCarti.indexOf(c), vectorAparitii.get(listaCarti.indexOf(c)) + 1);
                if(listaCarti.indexOf(c) >= maxim){
                    maxim = listaCarti.indexOf(c);
                }
                poz++;
            }
        }
        for(i = 0 ; i < vectorAparitii.size(); i++){
            if(vectorAparitii.get(i) > 1){
                jocActiv = false;
                notifyEndGame();
                break;
            }
        }

        if(jocActiv){
            //dam cartile castigatorului
            for(User u : listaOTuraDeJoc.keySet()){
                Carte c = listaOTuraDeJoc.get(u);
                if(listaCarti.indexOf(c) == maxim){
                    notifyCastigatorTura(u);
                }
            }
        }
    }

    private void notifyEndGame() {
        int max = 0;
        User castigator = new User("", "");
        for(User u : listaCartiJucatori.keySet()){
            if(listaCartiJucatori.get(u).size() > max){
                castigator = u;
                max = listaCartiJucatori.get(castigator).size();
            }
        }
        System.out.println("####################");
        System.out.println(castigator.getUsername());
        System.out.println("####################");

        //creez joc
        String participantiJoc = "";
        for(User u : playingUsers){
            participantiJoc += u.getUsername() +'/';
        }

        String cartiJucator = "";
        for(User u : listaCartiServerInitial.keySet()){
            for(Carte c : listaCartiServerInitial.get(u)){
                cartiJucator += c.getNume() + ',';
            }
            cartiJucator = cartiJucator.substring(0, cartiJucator.length() - 1);
            cartiJucator += '/';
        }
        String winner = castigator.getUsername();

        String cartiCastigate ="";
        for(Carte c : listaCartiJucatori.get(castigator)){
            cartiCastigate += c.getNume() + ',';
        }
        Joc joc = new Joc(participantiJoc, cartiJucator, winner, cartiCastigate);
        jocRepository.save(joc);
    }

    private void notifyCastigatorTura(User u) throws RemoteException {
        for(User user : listaOTuraDeJoc.keySet()){
            listaCartiJucatori.get(u).add(listaOTuraDeJoc.get(user));
        }
        notifyCastigator(u, listaCartiJucatori.get(u));
    }

    private void notifyCastigator(User u, List<Carte> listaCarti) throws RemoteException {
        observers.get(u).updateCastigator(listaCarti);
    }

    private void notifyStergeCarteaPrimita() throws RemoteException {
        for(User u : listaOTuraDeJoc.keySet()){
            List<Carte> cartiJucator =  listaCartiJucatori.get(u);
            Carte carteTura = listaOTuraDeJoc.get(u);
            cartiJucator.remove(carteTura);
            observers.get(u).updateStergeCarteTrimisa(cartiJucator);
        }
    }

    private void notifyCartiAdversari(List<Carte> listaOTuraDeJoc) throws RemoteException {
        for(User u : playingUsers){
            observers.get(u).updateCartiAdversari(listaOTuraDeJoc);
        }
    }
}