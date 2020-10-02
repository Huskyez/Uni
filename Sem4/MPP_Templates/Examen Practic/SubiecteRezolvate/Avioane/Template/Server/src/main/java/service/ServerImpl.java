package service;

import model.Game;
import model.User;
import repository.GameRepository;
import repository.UserRepository;
import utils.IObserver;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServerImpl implements IServerImpl{
    private boolean jocActiv = false;
    private UserRepository userRepository;
    private GameRepository gameRepository;
    public ServerImpl(UserRepository userRepository, GameRepository gameRepository) {
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
    }
    Map<User, IObserver> players = new ConcurrentHashMap<>();//coada de asteptare
    List<User> playingUsers; //jucatorii activi

    public void saveUser(User user){
        userRepository.save(user);
    }
    public boolean login(User user){
        return userRepository.findOne(user.getUsername()).getPassword().equals(user.getPassword());
    }

    public void startGame(User user, IObserver observer){
        players.putIfAbsent(user, observer);
        if(jocActiv == false && players.size() > 1){
            User user1 = (User)players.keySet().toArray()[0];
            User user2 = (User)players.keySet().toArray()[1];
            this.playingUsers = new ArrayList<>();
            playingUsers.add(user1);
            playingUsers.add(user2);
            jocActiv = true;
            Game game = new Game(gameRepository.findIndex(), user1, user2, user1.getPositieAvion(), user2.getPositieAvion());
            gameRepository.save(game);
            yourTurn(user1);
        }

    }

    private void yourTurn(User user) {
        try {
            players.get(user).move();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void oneMoved(User user, String pozitii){
        for(User u : playingUsers){
            if(!user.getUsername().equals(u.getUsername()) ){ //este oponentul
                if(pozitii.equals(u.getPositieAvion())){
                    jocActiv = false;
                    try {
                        players.get(user).endOfGame(true);
                        players.get(u).endOfGame(false);

                        //notifyEndOfGame(user, u); //user -winner
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    yourTurn(u);
                }
            }
        }




    }

    private void notifyEndOfGame(User winner, User loser) throws RemoteException {
        Game game = gameRepository.findOneByUsers(winner.getUsername(), loser.getUsername());
        if(game == null ){
            game = gameRepository.findOneByUsers(winner.getUsername(), loser.getUsername());
        }
        game.setWinner(winner.getUsername());

       players.get(userRepository.findOne(winner.getUsername())).endOfGame(true);
       players.get(userRepository.findOne(loser.getUsername())).endOfGame(false);
    }
}
