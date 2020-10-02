package service;

import controller.GameController;
import model.User;
import utils.IObserver;

import java.rmi.RemoteException;

public interface IServerImpl {
    public void saveUser(User user);
    public boolean login(User user);

    void startGame(User user, IObserver observer);
    public void oneMoved(User user, String pozitii);
}
