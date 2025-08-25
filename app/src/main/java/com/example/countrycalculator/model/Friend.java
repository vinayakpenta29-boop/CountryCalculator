package com.example.countrycalculator.model;

public class Friend {
    private String name;
    private double balance;
    private double amountPaid; // ✅ Added this field

    public Friend(String name) {
        this.name = name;
        this.balance = 0;
        this.amountPaid = 0; // ✅ Initialize amountPaid
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

    public void setAmountPaid(double amountPaid) {  // ✅ Added setter
        this.amountPaid = amountPaid;
    }
}
