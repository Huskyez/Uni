package flight.repository;

import flight.domain.Bilet;

public interface BiletRepoInterface extends Repository<Integer, Bilet> {
    Integer findCorrectIndex();
}
