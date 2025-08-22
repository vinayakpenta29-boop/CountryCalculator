package com.example.countrycalculator;

public class Balance {
    private String name;
    private double balance;

    public Balance(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }
}
