package service;

import model.Corector;
import model.Lucrare;
import model.Participant;
import repository.CorectorRepository;
import repository.LucrareRepository;
import repository.ParticipantRepository;
import utils.IObserver;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ServerImpl implements IServerImpl{
    private CorectorRepository corectorRepository;
    private ParticipantRepository participantRepository;
    private LucrareRepository lucrareRepository;

    Map<String, IObserver> observers = new ConcurrentHashMap<>();

    public ServerImpl(CorectorRepository corectorRepository, ParticipantRepository participantRepository, LucrareRepository lucrareRepository) {
        this.corectorRepository = corectorRepository;
        this.participantRepository = participantRepository;
        this.lucrareRepository = lucrareRepository;
    }

    public void saveCorector(Corector corector){
        corectorRepository.save(corector);
    }

    @Override
    public void saveParticipant(Participant participant) {
        participantRepository.save(participant);
    }

    @Override
    public void saveLucrare(Lucrare lucrare) {
        lucrareRepository.save(lucrare);
    }

    public boolean login(Corector corector, IObserver observer){
        observers.putIfAbsent(corector.getUsername(), observer);
        return corectorRepository.findOne(corector.getUsername()).getPassword().equals(corector.getPassword());
    }

    @Override
    public List<Lucrare> getAllLucrariCorector(String username) {
        return StreamSupport.stream(lucrareRepository.findAll().spliterator(),false)
                .filter(lucrare -> lucrare.getCorector1().getUsername().equals(username)
                || lucrare.getCorector2().getUsername().equals(username))
                .filter(p->p.getNotaFinala() == null)
                .collect(Collectors.toList());
    }

    @Override
    public void logout(String username) {
        observers.remove(username);
    }

    @Override
    public void notaLucrare(Integer paperId, Double nota, String username, IObserver observer) {
        Lucrare lucrare = lucrareRepository.findOne(paperId);
        Double nota1 = lucrare.getNota1();
        Double nota2 = lucrare.getNota2();

        if(nota1 == null && nota2 == null){
            lucrare.setNota1(nota);
            lucrareRepository.update(lucrare);
        }else
            if(nota1 == null){
                lucrare.setNota1(nota);
                lucrareRepository.update(lucrare);
                diferentaNote(lucrare);
            }else{
                lucrare.setNota2(nota);
                lucrareRepository.update(lucrare);
                diferentaNote(lucrare);
            }
    }

    private void diferentaNote(Lucrare lucrare) {
        boolean recorectare = false;
        Double dif = Math.abs(lucrare.getNota1() - lucrare.getNota2());
        if(dif >= 1.0){
            recorectare = true;
            lucrare.setNota1(-1.0);
            lucrare.setNota2(-1.0);
            lucrareRepository.update(lucrare);
            notifyCorectori(lucrare, recorectare);
        }else {
            lucrare.setNotaFinala((lucrare.getNota1()+lucrare.getNota2())/2.0);
            lucrareRepository.update(lucrare);
            notifyCorectori(lucrare, recorectare);
        }
    }

    private void notifyCorectori(Lucrare lucrare, boolean recorectare) {
        if(recorectare == true) {
            try {
                IObserver observer1 = observers.get(lucrare.getCorector1().getUsername());
                IObserver observer2 = observers.get(lucrare.getCorector2().getUsername());

                observer1.recorectare();
                observer2.recorectare();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }else{
            try {
                IObserver observer1 = observers.get(lucrare.getCorector1().getUsername());
                IObserver observer2 = observers.get(lucrare.getCorector2().getUsername());
                observer1.ok();
                observer2.ok();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }


}
