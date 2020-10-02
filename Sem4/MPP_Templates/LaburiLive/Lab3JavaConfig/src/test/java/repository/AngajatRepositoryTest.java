package repository;

import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.*;

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
        repo = new AngajatRepository(props);
    }

    @Test
    public void findOne() {

    }
}