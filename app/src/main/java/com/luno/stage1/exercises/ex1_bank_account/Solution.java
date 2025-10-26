package com.luno.stage1.exercises.ex1_bank_account;

public class Solution {
    private String accountNumber;
    private String accountHolder;
    private double balance;

    public Solution(String accountNumber, String accountHolder, double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
        System.out.println("After deposit: Balance: " + formatCurrency(balance));
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }

        if (amount > balance) {
            System.out.println("❌ Withdrawal failed: Insufficient funds");
            return false;
        }

        balance -= amount;
        System.out.println("After withdrawal: Balance: " + formatCurrency(balance));
        return true;
    }

    @Override
    public String toString() {
        return String.format("Account: %s, Holder: %s, Balance: %s",
                accountNumber, accountHolder, formatCurrency(balance));
    }

    private String formatCurrency(double amount) {
        return String.format("$%.2f", amount);
    }
}