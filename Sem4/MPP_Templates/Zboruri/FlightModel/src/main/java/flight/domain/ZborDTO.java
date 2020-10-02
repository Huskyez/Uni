package flight.domain;

import java.io.Serializable;
import java.time.LocalTime;

public class ZborDTO implements Serializable {
    private LocalTime ora;
    private Integer locuri;

    public ZborDTO(LocalTime ora,Integer locuri) {
        this.ora = ora;
        this.locuri = locuri;
    }

    public LocalTime getOra() {
        return ora;
    }

    public void setOra(LocalTime ora) {
        this.ora = ora;
    }

    public Integer getLocuri() {
        return locuri;
    }

    public void setLocuri(Integer locuri) {
        this.locuri = locuri;
    }

    @Override
    public String toString() {
        return "ZborDTO{" +
                "ora=" + ora +
                ", locuri=" + locuri +
                '}';
    }
}
