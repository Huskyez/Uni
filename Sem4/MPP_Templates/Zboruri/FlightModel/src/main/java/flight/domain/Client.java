package flight.domain;

import java.io.Serializable;

public class Client implements HasID<Integer>, Serializable {
    private Integer ID;
    private String nume;
    private String adresa;

    public Client(Integer ID, String nume, String adresa) {
        this.ID = ID;
        this.nume = nume;
        this.adresa = adresa;
    }

    @Override
    public Integer getID() {
        return ID;
    }

    @Override
    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getNume() {
        return this.nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getAdresa() {
        return this.adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    @Override
    public String toString() {
        return "Client{" +
                "ID=" + ID +
                ", nume='" + nume + '\'' +
                ", adresa='" + adresa + '\'' +
                '}';
    }
}
