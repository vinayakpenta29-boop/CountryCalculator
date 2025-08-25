package com.example.countrycalculator.model;

public class Friend {
    private String name;
    private double balance;

    public Friend(String name) {
        this.name = name;
        this.balance = 0;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public double getAmountPaid() {
    return amountPaid;
    }
    

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
