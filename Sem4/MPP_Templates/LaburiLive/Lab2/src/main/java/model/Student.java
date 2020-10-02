package model;

public class Student {
    private int ID;
    private String nume;
    private int varsta;

    public Student(int ID, String nume, int varsta){
        this.ID = ID;
        this.nume = nume;
        this.varsta = varsta;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    @Override
    public String toString() {
        return "Student{" +
                ", nume='" + nume + '\'' +
                ", varsta=" + varsta +
                '}';
    }
}
