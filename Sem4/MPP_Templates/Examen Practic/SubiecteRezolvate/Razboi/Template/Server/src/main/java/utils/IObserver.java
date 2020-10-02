package utils;

import model.Carte;
import model.Jucator;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IObserver extends Remote{
    //toate functiile de aici arunca RemoteException()
    void inceputJoc(List<Jucator> jucatori) throws RemoteException;
    void primesteCarti(List<Carte> carti) throws RemoteException;

    void endOfRound(boolean b, List<Carte> carti) throws RemoteException;
}
