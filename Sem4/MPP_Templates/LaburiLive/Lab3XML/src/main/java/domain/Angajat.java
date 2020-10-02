package domain;

public class Angajat implements HasID<Integer>{
    private Integer ID;
    private String user;
    private String password;

    public Angajat(Integer ID, String user, String password) {
        this.ID = ID;
        this.user = user;
        this.password = password;
    }

    @Override
    public Integer getID() {
        return this.ID;
    }

    @Override
    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
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
                "ID=" + ID +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
