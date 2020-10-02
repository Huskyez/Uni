package flight.repository;

import flight.domain.Client;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class ClientRepositoryTest {
    Properties props = new Properties();
    ClientRepoInterface repo;

    @Before
    public void setUp() throws Exception {
        try {
            props.load(new FileInputStream("databasetest.config"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        repo = new ClientRepository(props);
    }

    @Test
    public void save() {
        assert (repo.size() == 2);
        repo.save(new Client(3, "Ciprian Teisanu","Piata Marasti, Cluj"));
        assert (repo.size() == 3);
    }

    @Test
    public void delete(){
        repo.delete(3);
        assert(repo.size() == 2);
    }

    @Test
    public void findOne() {
        assert(repo.findOne(1).getNume().equals("Ionut Frent"));
        assert(repo.findOne(2).getNume().equals("Adrian Tamas"));
    }

    @Test
    public void findAll() {
        Iterable<Client> it = repo.findAll();
        List<Client> list = new ArrayList<>();
        it.forEach(x-> list.add(x));

        assert(repo.size() == list.size());
    }
}