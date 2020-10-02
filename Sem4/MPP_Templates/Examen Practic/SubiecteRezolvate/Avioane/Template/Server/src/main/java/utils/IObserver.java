package utils;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IObserver extends Remote {
    public void move() throws RemoteException;
    public void endOfGame(boolean winner) throws RemoteException;
}
