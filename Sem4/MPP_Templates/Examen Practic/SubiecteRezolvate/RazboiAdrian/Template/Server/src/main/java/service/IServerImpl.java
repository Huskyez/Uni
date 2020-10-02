package service;

import model.Carte;
import model.User;
import utils.IObserver;

import java.rmi.RemoteException;
import java.util.List;

public interface IServerImpl {
    public void saveUser(User user);
    public boolean login(User user, IObserver observer);

    void logout(User user);

    void startJoc() throws RemoteException;
    void notifyJucatoriLogatiSiPachetCarti(User u, List<User> playingUsers, List<Carte> pachetCarti) throws RemoteException;

    void primesteCarti(User user, Carte carte) throws RemoteException;
}
