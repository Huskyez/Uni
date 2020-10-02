package flight.domain;

import java.io.Serializable;

public class Bilet implements HasID<Integer>, Serializable {
    private Integer ID;
    private Integer zborID;
    private Integer clientID;
    private String turisti;
    private Integer locuri;

    public Bilet(Integer ID, Integer zborID, Integer clientID, String turisti, Integer locuri) {
        this.ID = ID;
        this.zborID = zborID;
        this.clientID = clientID;
        this.turisti = turisti;
        this.locuri = locuri;
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

    public String getTuristi() {
        return turisti;
    }

    public void setTuristi(String turisti) {
        this.turisti = turisti;
    }

    public Integer getLocuri() {
        return locuri;
    }

    public void setLocuri(Integer locuri) {
        this.locuri = locuri;
    }


    @Override
    public String toString() {
        return "Bilet{" +
                "ID=" + ID +
                ", zborID=" + zborID +
                ", clientID=" + clientID +
                ", turisti='" + turisti + '\'' +
                ", locuri=" + locuri +
                '}';
    }
}
