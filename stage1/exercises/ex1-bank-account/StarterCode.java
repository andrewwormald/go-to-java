public class StarterCode {
    // TODO: Add private fields
    // - accountNumber (String)
    // - accountHolder (String)
    // - balance (double)

    // TODO: Add constructor that takes accountNumber, accountHolder, and initialBalance
    public StarterCode(String accountNumber, String accountHolder, double initialBalance) {
        // TODO: Validate initialBalance is not negative
        // TODO: Initialize fields
    }

    // TODO: Add getter methods (no setters for security)
    public String getAccountNumber() {
        return null; // TODO: Implement
    }

    public String getAccountHolder() {
        return null; // TODO: Implement
    }

    public double getBalance() {
        return 0.0; // TODO: Implement
    }

    // TODO: Add deposit method
    public void deposit(double amount) {
        // TODO: Validate amount is positive
        // TODO: Add to balance
        // TODO: Print confirmation message
    }

    // TODO: Add withdraw method
    public boolean withdraw(double amount) {
        // TODO: Validate amount is positive
        // TODO: Check sufficient funds
        // TODO: Subtract from balance if valid
        // TODO: Print confirmation or error message
        // TODO: Return true if successful, false if failed
        return false;
    }

    // TODO: Add toString method for account info
    @Override
    public String toString() {
        // TODO: Return formatted string like "Account: 12345, Holder: John Doe, Balance: $1000.00"
        return null;
    }

    // Helper method for currency formatting
    private String formatCurrency(double amount) {
        return String.format("$%.2f", amount);
    }
}