package domain;
import java.time.LocalDateTime;

public class Zbor implements HasID<Integer> {
    private Integer ID;
    private String destinatie;
    private LocalDateTime plecare;
    private String aeroport;
    private Integer locuri;

    public Zbor(Integer ID, String destinatie, LocalDateTime plecare, String aeroport, Integer locuri) {
        this.ID = ID;
        this.destinatie = destinatie;
        this.plecare = plecare;
        this.aeroport = aeroport;
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

    public String getDestinatie() {
        return this.destinatie;
    }

    public void setDestinatie(String destinatie) {
        this.destinatie = destinatie;
    }

    public LocalDateTime getPlecare() {
        return this.plecare;
    }

    public void setPlecare(LocalDateTime plecare) {
        this.plecare = plecare;
    }

    public String getAeroport() {
        return this.aeroport;
    }

    public void setAeroport(String aeroport) {
        this.aeroport = aeroport;
    }

    public Integer getLocuri() {
        return this.locuri;
    }

    public void setLocuri(Integer locuri) {
        this.locuri = locuri;
    }

    @Override
    public String toString() {
        return "Zbor{" +
                "ID=" + ID +
                ", destinatie='" + destinatie + '\'' +
                ", plecare=" + plecare +
                ", aeroport='" + aeroport + '\'' +
                ", locuri=" + locuri +
                '}';
    }
}
