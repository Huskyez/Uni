package model;

import java.io.Serializable;
import java.util.Objects;

public class Carte implements Serializable {
    private String nume;

    public Carte() {
    }

    public Carte(String nume) {
        this.nume = nume;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    @Override
    public boolean equals(Object o) {
        Carte carte = (Carte) o;
        return Objects.equals(nume, carte.getNume());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume);
    }
}
