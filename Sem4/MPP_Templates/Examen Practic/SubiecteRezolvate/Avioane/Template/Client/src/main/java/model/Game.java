package model;

import java.io.Serializable;

public class Game implements Serializable {
    private Integer id;
    private User user1;
    private User user2;
    private String pos1;
    private String pos2;
    private String winner;

    public Game(Integer id, User user1, User user2, String pos1, String pos2) {
        this.id = id;
        this.user1 = user1;
        this.user2 = user2;
        this.pos1 = pos1;
        this.pos2 = pos2;
        this.winner = "unfinished";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public String getPos1() {
        return pos1;
    }

    public void setPos1(String pos1) {
        this.pos1 = pos1;
    }

    public String getPos2() {
        return pos2;
    }

    public void setPos2(String pos2) {
        this.pos2 = pos2;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public Game() {
    }

}
