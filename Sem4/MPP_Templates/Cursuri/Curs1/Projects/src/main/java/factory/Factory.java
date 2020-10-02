package factory;

import model.Strategy;

public interface Factory {

    Container createContainer(Strategy strategy);

}
