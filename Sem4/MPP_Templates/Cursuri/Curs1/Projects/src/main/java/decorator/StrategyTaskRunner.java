package decorator;

import factory.Container;
import model.Strategy;
import model.Task;
import factory.TaskContainerFactory;

public class StrategyTaskRunner implements TaskRunner {

    private Container container;

    public StrategyTaskRunner(Strategy strategy) {
        this.container = TaskContainerFactory.getInstance().createContainer(strategy);
    }

    @Override
    public void executeOneTask() {
        Task currentTask = container.remove();
        currentTask.execute();
    }

    @Override
    public void executeAll() {
        while(container.isEmpty() == false){
            executeOneTask();
        }
    }

    @Override
    public void addTask(Task t) {
        container.add(t);
    }

    @Override
    public boolean hasTask() {
        return !container.isEmpty();
    }

}
