package flight.domain;

public interface HasID<ID> {
    ID getID();
    void setID(ID ID);
}
