package flight.repository;

import flight.domain.Client;

public interface ClientRepoInterface extends Repository<Integer, Client> {
    Client findOneByNumeAndAdress(String nume, String adresa);
}
