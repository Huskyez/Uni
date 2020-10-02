package model;

import java.io.Serializable;
import java.util.Objects;

public class Jucator implements Serializable {
    private String username;
    private String password;
    private boolean castigator;

    public Jucator() {
    }

    public Jucator(String username, String password) {
        this.username = username;
        this.password = password;
        this.castigator = false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        Jucator jucator = (Jucator) o;
        return Objects.equals(username, jucator.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}
