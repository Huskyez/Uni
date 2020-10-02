package service;

import model.Corector;
import model.Lucrare;
import model.Participant;
import utils.IObserver;

import java.util.List;

public interface IServerImpl {
    public void saveCorector(Corector corector);
    public void saveParticipant(Participant participant);
    public void saveLucrare(Lucrare lucrare);


    public boolean login(Corector corector, IObserver observer);

    public List<Lucrare> getAllLucrariCorector(String username);

    void logout(String username);

    void notaLucrare(Integer paperId, Double nota, String username, IObserver observer);
}
