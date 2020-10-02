package flight.repository;

import flight.domain.Bilet;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class BiletRepositoryTest {
    Properties props = new Properties();
    BiletRepoInterface repo;

    @Before
    public void setUp() throws Exception {
        try {
            props.load(new FileInputStream("databasetest.config"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        repo = new BiletRepository(props);
    }

    @Test
    public void save() {
        assert (repo.size() == 2);
        repo.save(new Bilet(3,1,2,"Vasile",20));
        assert (repo.size() == 3);
    }

    @Test
    public void delete() {
        repo.delete(3);
        assert(repo.size() == 2);
    }

    @Test
    public void findOne() {
        assert(repo.findOne(1).getClientID() == 1);
        assert(repo.findOne(2).getZborID() == 2);
    }

    @Test
    public void findAll() {
        Iterable<Bilet> it = repo.findAll();
        List<Bilet> list = new ArrayList<>();
        it.forEach(x-> list.add(x));

        assert(repo.size() == list.size());
    }
}