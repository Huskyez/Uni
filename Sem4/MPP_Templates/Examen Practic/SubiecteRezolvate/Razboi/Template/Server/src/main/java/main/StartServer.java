package main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartServer {
    public static void main(String[] args) {
        String c = "client,";

        ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-server.xml");
    }
}