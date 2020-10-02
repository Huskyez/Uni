package dto;

import java.io.Serializable;

public class ZborDTO3 implements Serializable {
    private String destinatie;
    private String data;
    private String ora;

    public ZborDTO3(String destinatie, String data, String ora) {
        this.destinatie = destinatie;
        this.data = data;
        this.ora = ora;
    }

    public String getDestinatie() {
        return destinatie;
    }

    public String getData() {
        return data;
    }

    public String getOra() {
        return ora;
    }
}
