package utils;

import model.Lucrare;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IObserver extends Remote{
    void ok() throws RemoteException;
    void recorectare() throws RemoteException;
}
