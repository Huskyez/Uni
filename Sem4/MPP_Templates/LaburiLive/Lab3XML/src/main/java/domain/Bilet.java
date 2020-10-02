package domain;

public class Bilet implements HasID<Integer>{
    private Integer ID;
    private Integer zborID;
    private Integer clientID;

    public Bilet(Integer ID, Integer zborID, Integer clientID) {
        this.ID = ID;
        zborID = zborID;
        clientID = clientID;
    }

    @Override
    public Integer getID() {
        return this.ID;
    }

    @Override
    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getZborID() {
        return this.zborID;
    }

    public void setZborID(Integer zborID) {
        this.zborID = zborID;
    }

    public Integer getClientID() {
        return this.clientID;
    }

    public void setClientID(Integer clientID) {
        this.clientID = clientID;
    }

    @Override
    public String toString() {
        return "Bilet{" +
                "ID=" + ID +
                ", ZborID=" + zborID +
                ", ClientID=" + clientID +
                '}';
    }
}
