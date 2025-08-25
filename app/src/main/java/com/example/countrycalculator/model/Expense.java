package com.ravi.countrycalculator;

public class Expense {
    private String name;
    private double amount;
    private String payerName;

    public Expense(String name, double amount, String payerName) {
        this.name = name;
        this.amount = amount;
        this.payerName = payerName;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public String getPayerName() {
        return payerName;
    }
}
