package com.zjwaty.arithmetic.entity;

import java.util.List;
import java.util.ArrayList;

public class Exercise {
    private String username;
    private List<Question> questions;
    private int score;
    private double accuracy;
    private String timestamp;

    public Exercise() {
        this.questions = new ArrayList<>();
    }

    // getters and setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public List<Question> getQuestions() { return questions; }
    public void setQuestions(List<Question> questions) { this.questions = questions; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    public double getAccuracy() { return accuracy; }
    public void setAccuracy(double accuracy) { this.accuracy = accuracy; }
    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
}