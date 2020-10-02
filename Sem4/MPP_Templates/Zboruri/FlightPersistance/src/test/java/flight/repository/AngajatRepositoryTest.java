package flight.repository;

import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AngajatRepositoryTest {
    Properties props = new Properties();
    AngajatRepository repo;

    @Before
    public void setUp() throws Exception {
        try {
            props.load(new FileInputStream("databasetest.config"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        //repo = new AngajatRepository(props);
    }

    @Test
    public void findOne() {
        //assert(repo.logIn("popescu.ion").getPassword().equals(new Angajat("popescu.ion","pi123").getPassword()));
       // assert(repo.logIn("vasilescu.vasile").getPassword().equals(new Angajat("vasilescu.vasile","vv123").getPassword()));
    }
}