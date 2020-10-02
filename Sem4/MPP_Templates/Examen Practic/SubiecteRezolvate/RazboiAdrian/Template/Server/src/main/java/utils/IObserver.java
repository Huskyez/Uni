package utils;

import model.Carte;
import model.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface IObserver extends Remote{
    void updateJucatoriLogatiSiPachetCarti(List<User> playingUsers, List<Carte> listaCarti) throws RemoteException;

    void updateCartiAdversari(List<Carte> listaOTuraDeJoc) throws RemoteException;

    void updateStergeCarteTrimisa(List<Carte> cartiJucator) throws RemoteException;

    void updateCastigator(List<Carte> listaCarti) throws RemoteException;

    void alegereCarteNoua() throws RemoteException;
    //toate functiile de aici arunca remote exception
}
