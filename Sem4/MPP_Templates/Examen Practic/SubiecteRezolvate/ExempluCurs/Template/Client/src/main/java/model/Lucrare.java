package model;

import java.io.Serializable;

public class Lucrare implements Serializable {
    private Integer paperId;
    private Corector corector1;
    private Corector corector2;
    private Double nota1;
    private Double nota2;
    private Double notaFinala;
    private Participant participant;

    public Lucrare() {
    }

    public Lucrare(Integer paperId, Corector corector1, Corector corector2, Participant participant) {
        this.paperId = paperId;
        this.corector1 = corector1;
        this.corector2 = corector2;
        this.participant = participant;
        this.nota1 = null;
        this.nota2 = null;
        this.notaFinala = null;
    }


    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public Corector getCorector1() {
        return corector1;
    }

    public void setCorector1(Corector corector1) {
        this.corector1 = corector1;
    }

    public Corector getCorector2() {
        return corector2;
    }

    public void setCorector2(Corector corector2) {
        this.corector2 = corector2;
    }

    public Double getNota1() {
        return nota1;
    }

    public void setNota1(Double nota1) {
        this.nota1 = nota1;
    }

    public Double getNota2() {
        return nota2;
    }

    public void setNota2(Double nota2) {
        this.nota2 = nota2;
    }

    public Double getNotaFinala() {
        return notaFinala;
    }

    public void setNotaFinala(Double notaFinala) {
        this.notaFinala = notaFinala;
    }
}
