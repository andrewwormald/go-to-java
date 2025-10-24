# Exercise 1: Bank Account System

## Goal
Practice basic class creation, encapsulation, and validation.

## What You'll Build
A simple bank account with deposits, withdrawals, and proper validation.

## Requirements
Complete the `BankAccount` class to:
1. Store account details securely (private fields)
2. Validate all operations (positive amounts, sufficient funds)
3. Provide safe access through public methods
4. Format currency properly

## Expected Output
```
=== Bank Account Test ===
Account: 12345, Holder: John Doe, Balance: $1000.00
After deposit: Balance: $1250.00
After withdrawal: Balance: $1000.00
❌ Withdrawal failed: Insufficient funds

✅ All tests passed!
```

## Key Concepts
- Private fields with public methods
- Input validation
- Proper encapsulation
- Constructor usage

## Time Estimate
30-45 minutes

## Run Your Solution
```bash
javac *.java && java ExampleTest
```