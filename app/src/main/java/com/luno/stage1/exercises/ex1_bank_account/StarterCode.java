package com.luno.stage1.exercises.ex1_bank_account;

public class StarterCode {
    // TODO: Add private fields
    // - accountNumber (String)
    private String accountNumber;
    // - accountHolder (String)
    private String accountHolder;
    // - balance (double)
    private double balance;

    // TODO: Add constructor that takes accountNumber, accountHolder, and initialBalance
    public StarterCode(String accountNumber, String accountHolder, double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }

        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    // TODO: Add getter methods (no setters for security)
    public String getAccountNumber() {
        return this.accountNumber; // TODO: Implement
    }

    public String getAccountHolder() {
        return this.accountHolder; // TODO: Implement
    }

    public double getBalance() {
        return this.balance; // TODO: Implement
    }

    // TODO: Add deposit method
    public void deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }

        this.balance += amount;
        System.out.println("Deposited " + this.balance + " to Account: " + this.accountNumber);
    }

    // TODO: Add withdraw method
    public boolean withdraw(double amount) {
        if  (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }

        if (amount > this.balance) {
            throw new IllegalArgumentException("Amount cannot be greater than balance");
        }

        this.balance -= amount;

        System.out.println("Withdrawn " + this.balance + " from Account: " + this.accountNumber);
        return true;
    }

    // TODO: Add toString method for account info
    @Override
    public String toString() {
        return String.format("Account: %s, Holder %s, Balance: %.2f", this.accountNumber, this.accountHolder, this.balance);
    }

    // Helper method for currency formatting
    private String formatCurrency(double amount) {
        return String.format("$%.2f", amount);
    }
}