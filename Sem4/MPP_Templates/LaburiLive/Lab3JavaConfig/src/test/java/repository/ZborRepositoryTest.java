package repository;

import domain.Client;
import domain.Zbor;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import static org.junit.Assert.*;

public class ZborRepositoryTest {
    Properties props = new Properties();
    Repository<Integer, Zbor> repo;

    @Before
    public void setUp() throws Exception {
        try {
            props.load(new FileInputStream("databasetest.config"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        repo = new ZborRepository(props);
    }

    @Test
    public void save() {
    }

    @Test
    public void delete() {

    }

    @Test
    public void findOne() {
    }

    @Test
    public void findAll() {

    }
}