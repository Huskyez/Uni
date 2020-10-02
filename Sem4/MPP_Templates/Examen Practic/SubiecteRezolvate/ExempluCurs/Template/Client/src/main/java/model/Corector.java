package model;

import java.io.Serializable;

public class Corector implements Serializable {
    private String username;
    private String password;

    public Corector() {
    }

    public Corector(String username, String password) {
        this.username = username;
        this.password = password;
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

}
