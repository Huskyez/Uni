package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JocDTO implements Serializable {
    public JocDTO() {
    }

    private Integer id;
    private List<String> participanti;
    private Map<String, List<Carte>> cartiInitiale;
    private String castigator;
    private List<Carte> cartiCastigate;

    public JocDTO(Integer id, List<String> participanti, Map<String, List<Carte>> cartiInitiale, String castigator, List<Carte> cartiCastigate) {
        this.id = id;
        this.participanti = participanti;
        this.cartiInitiale = cartiInitiale;
        this.castigator = castigator;
        this.cartiCastigate = cartiCastigate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<String> getParticipanti() {
        return participanti;
    }

    public void setParticipanti(List<String> participanti) {
        this.participanti = participanti;
    }

    public Map<String, List<Carte>> getCartiInitiale() {
        return cartiInitiale;
    }

    public void setCartiInitiale(Map<String, List<Carte>> cartiInitiale) {
        this.cartiInitiale = cartiInitiale;
    }

    public String getCastigator() {
        return castigator;
    }

    public void setCastigator(String castigator) {
        this.castigator = castigator;
    }

    public List<Carte> getCartiCastigate() {
        return cartiCastigate;
    }

    public void setCartiCastigate(List<Carte> cartiCastigate) {
        this.cartiCastigate = cartiCastigate;
    }
}
