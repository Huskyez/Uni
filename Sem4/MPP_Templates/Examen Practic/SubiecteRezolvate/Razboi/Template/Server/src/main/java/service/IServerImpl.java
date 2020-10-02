package service;

import model.Carte;
import model.Jucator;
import utils.AnException;
import utils.IObserver;

public interface IServerImpl {
    //public void saveUser(Jucator jucator);
    boolean login(Jucator jucator, IObserver observer) throws AnException;

    void logout(Jucator jucator);

    void inceputJoc();

    boolean getJocStatus();

    void amAlesCarte(Carte carte, Jucator jucator, IObserver observer);
}
