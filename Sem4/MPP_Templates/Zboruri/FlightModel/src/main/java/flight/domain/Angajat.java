package flight.domain;

import java.io.Serializable;

public class Angajat implements HasID<String>, Serializable {
    private String ID;
    private String password;

    public Angajat(String ID, String password) {
        this.ID = ID;
        this.password = password;
    }

    public Angajat() {
    }

    @Override
    public String getID() {
        return this.ID;
    }

    @Override
    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Angajat{" +
                "user=" + ID +
                ", password='" + password + '\'' +
                '}';
    }
}
