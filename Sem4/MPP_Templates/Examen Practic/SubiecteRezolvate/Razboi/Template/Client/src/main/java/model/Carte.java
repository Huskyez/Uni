package model;

import java.io.Serializable;
import java.util.Objects;

public class Carte implements Serializable{
    private String simbol;

    public Carte() {
    }

    public Carte(String simbol) {
        this.simbol = simbol;
    }

    public String getSimbol() {
        return simbol;
    }

    public void setSimbol(String simbol) {
        this.simbol = simbol;
    }

}
