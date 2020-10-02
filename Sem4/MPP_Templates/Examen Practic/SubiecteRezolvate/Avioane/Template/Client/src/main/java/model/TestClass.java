package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class TestClass implements Serializable {
    private Integer id;
    private LocalTime date;

    public TestClass() {
    }

    public TestClass(LocalTime date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalTime getDate() {
        return date;
    }

    public void setDate(LocalTime date) {
        this.date = date;
    }
}
