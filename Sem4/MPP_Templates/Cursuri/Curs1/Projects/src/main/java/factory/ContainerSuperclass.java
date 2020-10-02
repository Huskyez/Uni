package factory;

import model.Task;

import java.util.ArrayList;

public abstract class ContainerSuperclass {

    protected ArrayList<Task> tasks = new ArrayList<>();

    public ContainerSuperclass() {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
