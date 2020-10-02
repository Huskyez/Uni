package service;

import model.User;
import repository.UserRepository;
import utils.ServerException;
import utils.IObserver;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServerImpl implements IServerImpl {
    private UserRepository userRepository;
    private Map<User, IObserver> observers = new ConcurrentHashMap<>();

    public ServerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    public void saveUser(model.User user){
//        userRepository.save(user);
//    }

    public boolean login(User user, IObserver observer) throws ServerException {
        User user1 = userRepository.findOne(user.getUsername());
        if(user1 != null) {
            boolean logat = user1.getPassword().equals(user.getPassword());
            if (logat) {
                if(observers.containsKey(user1))
                    throw new ServerException("Already logged in!");
                observers.putIfAbsent(user, observer);
            }
            return logat;
        }else
            throw new ServerException("Wrong credentials!");
    }

    @Override
    public void logout(User user) {
        observers.remove(user);
    }

}
