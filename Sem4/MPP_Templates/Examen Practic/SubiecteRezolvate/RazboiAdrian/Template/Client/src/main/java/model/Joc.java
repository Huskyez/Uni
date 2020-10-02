package model;

import java.io.Serializable;

public class Joc implements Serializable {
    public Joc() {
    }

    private Integer id;
    private String participanti;
    private String cartiServer;
    private String castigator;
    private String cartiCastigate;

    public Joc(String participanti, String cartiServer, String castigator, String cartiCastigate) {
        this.participanti = participanti;
        this.cartiServer = cartiServer;
        this.castigator = castigator;
        this.cartiCastigate = cartiCastigate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParticipanti() {
        return participanti;
    }

    public void setParticipanti(String participanti) {
        this.participanti = participanti;
    }

    public String getCartiServer() {
        return cartiServer;
    }

    public void setCartiServer(String cartiServer) {
        this.cartiServer = cartiServer;
    }

    public String getCastigator() {
        return castigator;
    }

    public void setCastigator(String castigator) {
        this.castigator = castigator;
    }

    public String getCartiCastigate() {
        return cartiCastigate;
    }

    public void setCartiCastigate(String cartiCastigate) {
        this.cartiCastigate = cartiCastigate;
    }

}
