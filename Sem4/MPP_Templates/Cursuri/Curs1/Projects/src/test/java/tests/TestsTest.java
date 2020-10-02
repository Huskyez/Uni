package tests;

import model.MessageTask;
import model.Task;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class TestsTest {
    @Test
    public void messageTasks() {
        Task[] tasks = new Task[5];
        tasks[0]=new MessageTask("1","feedback","ai luat 9","teacher","student", LocalDateTime.now());
        tasks[1]=new MessageTask("2","feedback","ai luat 10","teacher","student", LocalDateTime.now());
        tasks[2]=new MessageTask("3","feedback","ai luat 7","teacher","student", LocalDateTime.now());
        tasks[3]=new MessageTask("4","feedback","ai luat 5","teacher","student", LocalDateTime.now());
        tasks[4]=new MessageTask("5","feedback","ai luat 8","teacher","student", LocalDateTime.now());

        assert(tasks[0].getDescriere().equals("feedback"));
    }

    @Test
    public void messageTasks2() {
        Task[] tasks = new Task[5];
        tasks[0]=new MessageTask("1","feedback","ai luat 9","teacher","student", LocalDateTime.now());
        tasks[1]=new MessageTask("2","feedback","ai luat 10","teacher","student", LocalDateTime.now());
        tasks[2]=new MessageTask("3","feedback","ai luat 7","teacher","student", LocalDateTime.now());
        tasks[3]=new MessageTask("4","feedback","ai luat 5","teacher","student", LocalDateTime.now());
        tasks[4]=new MessageTask("5","feedback","ai luat 8","teacher","student", LocalDateTime.now());

        assert(tasks[1].getTaskId().equals("2"));

    }
}