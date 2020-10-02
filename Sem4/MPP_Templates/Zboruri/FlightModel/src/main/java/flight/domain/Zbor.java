package flight.domain;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Zbor implements Serializable, Comparable<Zbor>, Identifiable<Integer> {
    private Integer ID;
    private String destinatie;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime plecare;
    private String aeroport;
    private Integer locuri;

    public Zbor() {}

    public Zbor(Integer ID){this(ID,"",null,"",null);}
    public Zbor(Integer ID, String destinatie){this(ID,destinatie,null,"",null);}
    public Zbor(Integer ID, String destinatie, LocalDateTime plecare){this(ID,destinatie,plecare,"",null);}
    public Zbor(Integer ID, String destinatie, LocalDateTime plecare, String aeroport){this(ID,destinatie,plecare,aeroport,null);}
    public Zbor(Integer ID, String destinatie, LocalDateTime plecare, String aeroport, Integer locuri) {
        this.ID = ID;
        this.destinatie = destinatie;
        this.plecare = plecare;
        this.aeroport = aeroport;
        this.locuri = locuri;
    }

//    @Override
//    public Integer getID() {
//        return this.ID;
//    }
//
//    @Override
//    public void setID(Integer ID) {
//        this.ID = ID;
//    }

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

    @Override
    public int compareTo(Zbor o) {
        return ID.compareTo(o.ID);
    }

    @Override
    public void setId(Integer integer) {
        ID = integer;
    }

    @Override
    public Integer getId() {
        return ID;
    }

    @Override
    public int hashCode() {
        return ID != null ? ID.hashCode() : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zbor zbor = (Zbor) o;
        return ID.equals(zbor.ID);
    }
}
