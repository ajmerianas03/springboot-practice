package com.springboot_practice.springboot_practice;

public class Result {

    private int correctCount;
    private int incorrectCount;
    private double percentage;

    // Constructor
    public Result(int correctCount, int incorrectCount, double percentage) {
        this.correctCount = correctCount;
        this.incorrectCount = incorrectCount;
        this.percentage = percentage;
    }

    // Getters and Setters
    public int getCorrectCount() {
        return correctCount;
    }

    public void setCorrectCount(int correctCount) {
        this.correctCount = correctCount;
    }

    public int getIncorrectCount() {
        return incorrectCount;
    }

    public void setIncorrectCount(int incorrectCount) {
        this.incorrectCount = incorrectCount;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}
