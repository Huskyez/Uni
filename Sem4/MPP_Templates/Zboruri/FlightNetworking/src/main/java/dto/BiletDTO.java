package dto;

import java.io.Serializable;

public class BiletDTO implements Serializable {
    private Integer zborID;
    private Integer numarLocuri;

    public BiletDTO(Integer zborID, Integer numarLocuri){
        this.zborID = zborID;
        this.numarLocuri = numarLocuri;
    }

    public Integer getZborID() {
        return zborID;
    }

    public Integer getNumarLocuri() {
        return numarLocuri;
    }

    @Override
    public String toString() {
        return "BiletDTO{" +
                "zborID=" + zborID +
                ", numarLocuri=" + numarLocuri +
                '}';
    }
}
