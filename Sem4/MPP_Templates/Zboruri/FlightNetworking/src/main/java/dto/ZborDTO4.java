package dto;

import java.io.Serializable;

public class ZborDTO4 implements Serializable {
    private Integer zborID;
    private Integer locuri;

    public ZborDTO4(Integer zborID, Integer locuri) {
        this.zborID = zborID;
        this.locuri = locuri;
    }

    public Integer getZborID() {
        return zborID;
    }

    public Integer getLocuri() {
        return locuri;
    }
}
