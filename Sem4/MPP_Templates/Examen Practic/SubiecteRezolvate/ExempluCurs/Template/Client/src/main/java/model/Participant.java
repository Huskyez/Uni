package model;

import java.io.Serializable;

public class Participant implements Serializable {
    private Integer id;
    private String nume;

    public Participant() {
    }

    public Participant(Integer id, String nume) {
        this.id = id;
        this.nume = nume;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }
}
