package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MessageTask extends Task{

    private String mesaj;
    private String from;
    private String to;
    private LocalDateTime date;
    private DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy:MM:dd_hh:mm");

    public String getMesaj() {
        return mesaj;
    }

    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public DateTimeFormatter getFormater() {
        return formater;
    }

    public void setFormater(DateTimeFormatter formater) {
        this.formater = formater;
    }

    public MessageTask(String taskId, String descriere, String mesaj, String from, String to, LocalDateTime date) {
        super(taskId, descriere);
        this.mesaj = mesaj;
        this.from = from;
        this.to = to;
        this.date = date;
    }

    @Override
    public void execute() {
        System.out.println(mesaj +" "+  date.format(formater));
    }

    @Override
    public String toString() {
        return  super.toString() + '|' +
                "mesaj=" + mesaj + '|' +
                "from=" + from + '|' +
                "to=" + to + '|' +
                "date=" + date.format(formater);
    }

}

