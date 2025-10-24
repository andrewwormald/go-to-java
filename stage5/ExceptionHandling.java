// Go to Java - Stage 5.1: Exception Handling
// Coming from Go: Java uses exceptions instead of explicit error returns

import java.io.*;
import java.util.*;

// Custom exceptions
class InsufficientFundsException extends Exception {
    private final double requestedAmount;
    private final double availableAmount;

    public InsufficientFundsException(double requestedAmount, double availableAmount) {
        super(String.format("Insufficient funds: requested %.2f, available %.2f",
                requestedAmount, availableAmount));
        this.requestedAmount = requestedAmount;
        this.availableAmount = availableAmount;
    }

    public double getRequestedAmount() { return requestedAmount; }
    public double getAvailableAmount() { return availableAmount; }
}

class InvalidAccountException extends RuntimeException {
    public InvalidAccountException(String accountNumber) {
        super("Invalid account number: " + accountNumber);
    }
}

// Business logic with exception handling
class BankAccount {
    private final String accountNumber;
    private double balance;
    private boolean frozen;

    public BankAccount(String accountNumber, double initialBalance) {
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Account number cannot be null or empty");
        }
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }

        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.frozen = false;
    }

    // Method that throws checked exception
    public void withdraw(double amount) throws InsufficientFundsException {
        validateNotFrozen();
        validatePositiveAmount(amount);

        if (amount > balance) {
            throw new InsufficientFundsException(amount, balance);
        }

        balance -= amount;
        System.out.println(String.format("Withdrew %.2f. New balance: %.2f", amount, balance));
    }

    // Method that might throw unchecked exception
    public void deposit(double amount) {
        validateNotFrozen();
        validatePositiveAmount(amount);

        balance += amount;
        System.out.println(String.format("Deposited %.2f. New balance: %.2f", amount, balance));
    }

    public void freeze() {
        frozen = true;
        System.out.println("Account " + accountNumber + " has been frozen");
    }

    public void unfreeze() {
        frozen = false;
        System.out.println("Account " + accountNumber + " has been unfrozen");
    }

    private void validateNotFrozen() {
        if (frozen) {
            throw new IllegalStateException("Account is frozen");
        }
    }

    private void validatePositiveAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
    }

    public double getBalance() { return balance; }
    public String getAccountNumber() { return accountNumber; }
    public boolean isFrozen() { return frozen; }
}

// Service class demonstrating try-with-resources
class AccountFileManager {
    // Method that uses try-with-resources (automatic resource management)
    public void saveAccountData(BankAccount account, String filename) throws IOException {
        // try-with-resources automatically closes the resources
        try (FileWriter writer = new FileWriter(filename);
             PrintWriter printer = new PrintWriter(writer)) {

            printer.println("Account: " + account.getAccountNumber());
            printer.println("Balance: " + account.getBalance());
            printer.println("Frozen: " + account.isFrozen());
            printer.println("Timestamp: " + new Date());

            System.out.println("Account data saved to " + filename);
        }
        // FileWriter and PrintWriter are automatically closed here
    }

    // Method demonstrating exception chaining
    public BankAccount loadAccountData(String filename) throws IOException {
        try (Scanner scanner = new Scanner(new File(filename))) {
            String accountLine = scanner.nextLine();
            String balanceLine = scanner.nextLine();

            String accountNumber = accountLine.split(": ")[1];
            double balance = Double.parseDouble(balanceLine.split(": ")[1]);

            return new BankAccount(accountNumber, balance);

        } catch (NumberFormatException e) {
            // Chain the original exception
            throw new IOException("Invalid number format in file: " + filename, e);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IOException("Invalid file format: " + filename, e);
        } catch (NoSuchElementException e) {
            throw new IOException("Incomplete file: " + filename, e);
        }
    }
}

// Utility class for demonstrating different exception handling patterns
class BankingService {
    private final Map<String, BankAccount> accounts = new HashMap<>();

    public void addAccount(BankAccount account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public BankAccount getAccount(String accountNumber) {
        BankAccount account = accounts.get(accountNumber);
        if (account == null) {
            throw new InvalidAccountException(accountNumber);
        }
        return account;
    }

    // Method demonstrating multiple catch blocks
    public void transferMoney(String fromAccount, String toAccount, double amount) {
        try {
            BankAccount from = getAccount(fromAccount);
            BankAccount to = getAccount(toAccount);

            from.withdraw(amount);  // might throw InsufficientFundsException
            to.deposit(amount);     // might throw IllegalArgumentException

            System.out.println(String.format("Transferred %.2f from %s to %s",
                    amount, fromAccount, toAccount));

        } catch (InsufficientFundsException e) {
            System.err.println("Transfer failed: " + e.getMessage());
            System.err.println("Shortfall: " + (e.getRequestedAmount() - e.getAvailableAmount()));
        } catch (InvalidAccountException e) {
            System.err.println("Transfer failed: " + e.getMessage());
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.err.println("Transfer failed due to validation error: " + e.getMessage());
        }
    }

    // Method demonstrating finally block
    public void performMaintenanceOperation(String accountNumber) {
        BankAccount account = null;
        boolean wasUpdated = false;

        try {
            account = getAccount(accountNumber);
            account.freeze();  // freeze for maintenance

            // Simulate some maintenance work that might fail
            if (Math.random() < 0.3) {
                throw new RuntimeException("Maintenance operation failed");
            }

            wasUpdated = true;
            System.out.println("Maintenance completed successfully");

        } catch (Exception e) {
            System.err.println("Maintenance failed: " + e.getMessage());
        } finally {
            // This block always executes
            if (account != null) {
                account.unfreeze();  // always unfreeze
                System.out.println("Account unfrozen in finally block");
            }
            System.out.println("Maintenance operation ended. Updated: " + wasUpdated);
        }
    }
}

public class ExceptionHandling {
    // Method demonstrating exception propagation
    public static void demonstrateExceptionPropagation() throws IOException {
        AccountFileManager fileManager = new AccountFileManager();
        BankAccount account = new BankAccount("ACC123", 1000.0);

        // This might throw IOException, which we let propagate
        fileManager.saveAccountData(account, "account.txt");
    }

    public static void main(String[] args) {
        System.out.println("=== Java Exception Handling ===");

        // Basic exception handling
        System.out.println("\n--- Basic Exception Handling ---");
        BankAccount account1 = new BankAccount("ACC001", 500.0);

        try {
            account1.deposit(100.0);        // success
            account1.withdraw(200.0);       // success
            account1.withdraw(500.0);       // will throw InsufficientFundsException
        } catch (InsufficientFundsException e) {
            System.err.println("Caught exception: " + e.getMessage());
            System.err.println("Available: " + e.getAvailableAmount());
        }

        // Multiple exception types
        System.out.println("\n--- Multiple Exception Types ---");
        try {
            account1.deposit(-50.0);  // IllegalArgumentException
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid argument: " + e.getMessage());
        }

        try {
            account1.freeze();
            account1.deposit(100.0);  // IllegalStateException
        } catch (IllegalStateException e) {
            System.err.println("State error: " + e.getMessage());
        } finally {
            account1.unfreeze();
        }

        // Banking service with complex exception handling
        System.out.println("\n--- Banking Service Exception Handling ---");
        BankingService bankingService = new BankingService();
        bankingService.addAccount(new BankAccount("ACC001", 1000.0));
        bankingService.addAccount(new BankAccount("ACC002", 500.0));

        // Successful transfer
        bankingService.transferMoney("ACC001", "ACC002", 200.0);

        // Failed transfers (various reasons)
        bankingService.transferMoney("ACC002", "ACC001", 1000.0);  // insufficient funds
        bankingService.transferMoney("ACC001", "ACC999", 100.0);   // invalid account
        bankingService.transferMoney("ACC001", "ACC002", -50.0);   // invalid amount

        // Maintenance operation with finally
        System.out.println("\n--- Finally Block Demo ---");
        bankingService.performMaintenanceOperation("ACC001");

        // Try-with-resources demo
        System.out.println("\n--- Try-with-Resources Demo ---");
        AccountFileManager fileManager = new AccountFileManager();
        BankAccount account2 = new BankAccount("SAVE001", 2500.0);

        try {
            fileManager.saveAccountData(account2, "account_data.txt");
        } catch (IOException e) {
            System.err.println("File operation failed: " + e.getMessage());
        }

        // Exception propagation demo
        System.out.println("\n--- Exception Propagation Demo ---");
        try {
            demonstrateExceptionPropagation();
        } catch (IOException e) {
            System.err.println("Caught propagated exception: " + e.getMessage());
        }

        // Loading with chained exceptions
        try {
            BankAccount loadedAccount = fileManager.loadAccountData("nonexistent.txt");
        } catch (IOException e) {
            System.err.println("Load failed: " + e.getMessage());
            if (e.getCause() != null) {
                System.err.println("Caused by: " + e.getCause().getClass().getSimpleName());
            }
        }

        // Exception handling best practices demo
        System.out.println("\n--- Exception Handling Best Practices ---");
        System.out.println("1. Use specific exception types");
        System.out.println("2. Don't catch Exception unless necessary");
        System.out.println("3. Always clean up resources (use try-with-resources)");
        System.out.println("4. Log exceptions appropriately");
        System.out.println("5. Don't ignore exceptions");

        System.out.println("\n=== Exception Handling Demo Complete ===");
    }
}