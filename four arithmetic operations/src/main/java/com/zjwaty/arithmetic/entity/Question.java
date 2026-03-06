package com.zjwaty.arithmetic.entity;

public class Question {
    private String expression;
    private int correctAnswer;
    private int userAnswer;
    private boolean correct;

    public Question() {}

    public Question(String expression, int correctAnswer) {
        this.expression = expression;
        this.correctAnswer = correctAnswer;
    }

    // getters and setters
    public String getExpression() { return expression; }
    public void setExpression(String expression) { this.expression = expression; }
    public int getCorrectAnswer() { return correctAnswer; }
    public void setCorrectAnswer(int correctAnswer) { this.correctAnswer = correctAnswer; }
    public int getUserAnswer() { return userAnswer; }
    public void setUserAnswer(int userAnswer) { this.userAnswer = userAnswer; }
    public boolean isCorrect() { return correct; }
    public void setCorrect(boolean correct) { this.correct = correct; }
}