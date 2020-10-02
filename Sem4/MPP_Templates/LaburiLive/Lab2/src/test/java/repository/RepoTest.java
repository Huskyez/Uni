package repository;

import model.Student;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.*;

public class RepoTest {
    Properties props = new Properties();

    @Test
    public void Teste() {

        try {
            props.load(new FileInputStream("bd.config"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        Repo repo = new Repo(props);
        assert(repo.size() == 4);
        repo.save(new Student(1,"Gheorghe",19));
        assert(repo.size() == 5);
        repo.delete(1);
        assert(repo.size() == 4);
    }
}