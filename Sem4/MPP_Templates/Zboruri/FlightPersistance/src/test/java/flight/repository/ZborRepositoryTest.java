package flight.repository;

import flight.domain.Zbor;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ZborRepositoryTest {
    Properties props = new Properties();
    ZborRepoInterface repo;

    @Before
    public void setUp() throws Exception {
        try {
            props.load(new FileInputStream("databasetest.config"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        //repo = new ZborRepository(props);
    }

    @Test
    public void save() {
        assert (repo.size() == 2);
        repo.save(new Zbor(3, "New York", LocalDateTime.of(2019, 3, 12,12,30), "GVA", 200));
        assert (repo.size() == 3);

    }

    @Test
    public void delete() {
        repo.delete(3);
        assert(repo.size() == 2);
    }

    @Test
    public void findOne() {
        assert(repo.findOne(1).getAeroport().equals("BUD"));
        assert(repo.findOne(2).getAeroport().equals("LYS"));
    }

    @Test
    public void findAll() {
        Iterable<Zbor> it = repo.findAll();
        List<Zbor> list = new ArrayList<>();
        it.forEach(x-> list.add(x));

        assert(repo.size() == list.size());
    }
}