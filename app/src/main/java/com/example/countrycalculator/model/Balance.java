package com.example.countrycalculator.model;

public class Balance {
    private String friendName;
    private double balance;

    public Balance(String friendName, double balance) {
        this.friendName = friendName;
        this.balance = balance;
    }

    public String getFriendName() {
        return friendName;
    }

    public double getBalance() {
        return balance;
    }
}
