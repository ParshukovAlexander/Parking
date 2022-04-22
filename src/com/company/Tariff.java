package com.company;

public class Tariff {
    private int minutes;
    private double rate;

    public Tariff(int minutes, double rate) {
        this.minutes = minutes;
        this.rate = rate;
    }

    public int getMinutes() {
        return minutes;
    }

    public double getRate() {
        return rate;
    }
}
