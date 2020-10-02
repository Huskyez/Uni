package dto;

import java.io.Serializable;

public class ZborDTO2 implements Serializable {
    private String destinatie;
    private String plecare;

    public ZborDTO2(String destinatie, String plecare) {
        this.destinatie = destinatie;
        this.plecare = plecare;
    }

    public String getDestinatie() {
        return destinatie;
    }

    public String getPlecare() {
        return plecare;
    }
}
