package flight.repository;

import flight.domain.Zbor;


public interface ZborRepoInterface extends Repository<Integer,Zbor>{
    Iterable<Zbor> findByDestinatieAndPlecare(String Destinatie, String plecare);

    Zbor findOneByDestinatieAndDateTime(String Destinatie, String DateTime);

    Zbor updateZbor(Integer Id, Integer locuri);
}
