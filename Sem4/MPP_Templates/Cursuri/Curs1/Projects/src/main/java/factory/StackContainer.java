package factory;

import model.Task;

public class StackContainer extends ContainerSuperclass implements Container {

    @Override
    public Task remove() {

        if(tasks.size()!=0) {
            return tasks.remove(tasks.size()-1);
        }
        else
            return null;
    }

}
