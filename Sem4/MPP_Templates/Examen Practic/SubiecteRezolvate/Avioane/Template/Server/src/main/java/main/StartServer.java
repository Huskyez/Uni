package main;

import model.TestClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import repository.GameRepository;

import java.time.LocalDate;
import java.time.LocalTime;

public class StartServer {
    public static void main(String[] args) {
        ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-server.xml");
        GameRepository r = new GameRepository();
        r.save(new TestClass(LocalTime.of(21,22)));
        r.save(new TestClass(LocalTime.of(21,22)));
        r.save(new TestClass(LocalTime.of(21,22)));

        System.out.println(r.findOneTest(1).getDate());
    }
}