package factory;

import model.Task;

public class QueueContainer extends ContainerSuperclass implements Container {

    @Override
    public Task remove() {

        if(tasks.size()!=0) {
            return tasks.remove(0);
        }
        else
            return null;
    }

}
