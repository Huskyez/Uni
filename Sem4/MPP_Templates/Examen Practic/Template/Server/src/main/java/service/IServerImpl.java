package service;

import model.User;
import utils.ServerException;
import utils.IObserver;

public interface IServerImpl {
    //public void saveUser(model.User user);
    boolean login(User user, IObserver observer) throws ServerException;

    void logout(User user);
}
