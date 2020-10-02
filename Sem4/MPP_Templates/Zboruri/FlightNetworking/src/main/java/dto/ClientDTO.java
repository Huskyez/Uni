package dto;

import java.io.Serializable;

public class ClientDTO implements Serializable {
    private String nume;
    private String adresa;

    public ClientDTO(String nume, String adresa) {
        this.nume = nume;
        this.adresa = adresa;
    }

    public String getNume() {
        return nume;
    }

    public String getAdresa() {
        return adresa;
    }
}
