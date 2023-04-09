package com.hangman.db;

import java.sql.Timestamp;

public class User {
    private String username;
    private Timestamp timestamp;
    private int score;
    private int time;

    public User(String username, Timestamp timestamp, int score, int time) {
        this.username = username;
        this.timestamp = timestamp;
        this.score = score;
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "User [username=" + username + ", timestamp=" + timestamp + ", score=" + score + ", time=" + time + "]";
    }
}
