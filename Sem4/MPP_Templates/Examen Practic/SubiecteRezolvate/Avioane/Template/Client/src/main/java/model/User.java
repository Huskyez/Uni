package model;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private String username;
    private String password;
    private String positieAvion;

    public User() {
    }

    public void setPositieAvion(String positieAvion) {
        this.positieAvion = positieAvion;
    }

    public String getPositieAvion() {
        return positieAvion;
    }

    public User(String username, String password) {
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

    @Override
    public boolean equals(Object o) {
        User user = (User) o;
        return Objects.equals(username, user.username);

    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, positieAvion);
    }
}
