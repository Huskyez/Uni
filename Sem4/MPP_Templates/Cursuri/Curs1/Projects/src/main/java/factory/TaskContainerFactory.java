package factory;

import model.*;

public class TaskContainerFactory implements Factory {

    //lazy initialization singleton

    private static TaskContainerFactory instance;

    private TaskContainerFactory(){}

    public static TaskContainerFactory getInstance(){
        if(instance==null){
            instance = new TaskContainerFactory();
        }
        return instance;
    }


    @Override
    public Container createContainer(Strategy strategy) {
        if(strategy==Strategy.FIFO){
            return new QueueContainer();
        }
        else{
            return new StackContainer();
        }

    }

}
