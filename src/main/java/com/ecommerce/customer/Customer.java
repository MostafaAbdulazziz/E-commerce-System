package com.ecommerce.customer;

public class Customer {
    private String name;
    private double balance;

    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }
    public void pay(double amount) {
        if (balance < amount) throw new IllegalStateException("Insufficient balance");
        balance -= amount;
    }
    public double getBalance() {
        return balance;
                }
}

