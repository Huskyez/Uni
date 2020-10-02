package service;

import domain.Client;
import repository.Repository;

public class ClientService {
    private Repository<Integer, Client> repo;

    public ClientService(Repository<Integer, Client> repo) {
        this.repo = repo;
    }

    public Iterable<Client> findAll(){
        return repo.findAll();
    }
}
