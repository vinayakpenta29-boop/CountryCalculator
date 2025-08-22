package com.example.countrycalculator.model;

public class Friend {
    private String name;
    private double amountPaid;

    public Friend(String name, double amountPaid) {
        this.name = name;
        this.amountPaid = amountPaid;
    }

    public String getName() {
        return name;
    }

    public double getAmountPaid() {
        return amountPaid;
    }
}
