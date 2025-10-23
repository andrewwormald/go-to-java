// Stage 4.1: Encapsulation Best Practices and Composition
// Coming from Go: Java encapsulation is more explicit, composition similar

import java.util.*;

// Immutable class example (like Go's readonly structs)
final class Money {
    private final int cents;  // final = cannot be changed after construction
    private final String currency;

    public Money(int cents, String currency) {
        if (cents < 0) {
            throw new IllegalArgumentException("Cents cannot be negative");
        }
        if (currency == null || currency.trim().isEmpty()) {
            throw new IllegalArgumentException("Currency cannot be null or empty");
        }
        this.cents = cents;
        this.currency = currency;
    }

    // Only getters, no setters - immutable
    public int getCents() { return cents; }
    public String getCurrency() { return currency; }

    // Return new instance instead of modifying (immutable pattern)
    public Money add(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Cannot add different currencies");
        }
        return new Money(this.cents + other.cents, this.currency);
    }

    public Money subtract(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Cannot subtract different currencies");
        }
        if (this.cents < other.cents) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        return new Money(this.cents - other.cents, this.currency);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Money money = (Money) obj;
        return cents == money.cents && Objects.equals(currency, money.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cents, currency);
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", cents / 100.0, currency);
    }
}

// Mutable class with proper encapsulation
class BankAccount {
    private Money balance;  // composition - has-a relationship
    private final String accountNumber;
    private final String owner;
    private List<String> transactionHistory;

    public BankAccount(String accountNumber, String owner, Money initialBalance) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        logTransaction("Account opened with " + initialBalance);
    }

    // Defensive copying - return copy, not original
    public Money getBalance() {
        return balance;  // Money is immutable, so this is safe
    }

    public String getAccountNumber() {
        return accountNumber;  // String is immutable
    }

    public String getOwner() {
        return owner;  // String is immutable
    }

    // Return defensive copy of mutable field
    public List<String> getTransactionHistory() {
        return new ArrayList<>(transactionHistory);  // return copy, not original
    }

    // Business logic with validation
    public void deposit(Money amount) {
        if (amount.getCents() <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        if (!amount.getCurrency().equals(balance.getCurrency())) {
            throw new IllegalArgumentException("Currency mismatch");
        }

        balance = balance.add(amount);
        logTransaction("Deposited " + amount + ", new balance: " + balance);
    }

    public void withdraw(Money amount) {
        if (amount.getCents() <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (!amount.getCurrency().equals(balance.getCurrency())) {
            throw new IllegalArgumentException("Currency mismatch");
        }

        try {
            balance = balance.subtract(amount);
            logTransaction("Withdrew " + amount + ", new balance: " + balance);
        } catch (IllegalArgumentException e) {
            logTransaction("Failed withdrawal of " + amount + " - " + e.getMessage());
            throw e;
        }
    }

    public void transfer(BankAccount target, Money amount) {
        this.withdraw(amount);  // will throw if insufficient funds
        target.deposit(amount);
        logTransaction("Transferred " + amount + " to " + target.getAccountNumber());
    }

    private void logTransaction(String description) {
        String timestamp = new Date().toString();
        transactionHistory.add(timestamp + ": " + description);
    }

    @Override
    public String toString() {
        return String.format("Account[%s, %s, %s]", accountNumber, owner, balance);
    }
}

// Composition vs Inheritance example
class Address {
    private final String street;
    private final String city;
    private final String postalCode;

    public Address(String street, String city, String postalCode) {
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
    }

    public String getStreet() { return street; }
    public String getCity() { return city; }
    public String getPostalCode() { return postalCode; }

    @Override
    public String toString() {
        return street + ", " + city + " " + postalCode;
    }
}

// Prefer composition over inheritance
class Customer {
    private final String name;
    private final Address address;  // composition - has-a
    private final List<BankAccount> accounts;

    public Customer(String name, Address address) {
        this.name = name;
        this.address = address;  // composition
        this.accounts = new ArrayList<>();
    }

    public void addAccount(BankAccount account) {
        if (!account.getOwner().equals(this.name)) {
            throw new IllegalArgumentException("Account owner must match customer name");
        }
        accounts.add(account);
    }

    public String getName() { return name; }
    public Address getAddress() { return address; }

    // Defensive copy
    public List<BankAccount> getAccounts() {
        return new ArrayList<>(accounts);
    }

    public Money getTotalBalance() {
        if (accounts.isEmpty()) {
            return new Money(0, "USD");
        }

        Money total = accounts.get(0).getBalance();
        for (int i = 1; i < accounts.size(); i++) {
            total = total.add(accounts.get(i).getBalance());
        }
        return total;
    }

    @Override
    public String toString() {
        return String.format("Customer[%s, %s, %d accounts]", name, address, accounts.size());
    }
}

public class EncapsulationBestPractices {
    public static void main(String[] args) {
        System.out.println("=== Encapsulation and Composition Best Practices ===");

        // Immutable Money class
        System.out.println("\n--- Immutable Money Class ---");
        Money money1 = new Money(15000, "USD");  // $150.00
        Money money2 = new Money(5000, "USD");   // $50.00

        System.out.println("Money 1: " + money1);
        System.out.println("Money 2: " + money2);

        Money sum = money1.add(money2);  // returns new instance
        System.out.println("Sum: " + sum);
        System.out.println("Original money1 unchanged: " + money1);

        // Proper encapsulation with BankAccount
        System.out.println("\n--- Encapsulated Bank Account ---");
        BankAccount account1 = new BankAccount("ACC001", "Alice Johnson", money1);
        BankAccount account2 = new BankAccount("ACC002", "Bob Smith", money2);

        System.out.println("Account 1: " + account1);
        System.out.println("Account 2: " + account2);

        // Safe operations through methods
        System.out.println("\n--- Banking Operations ---");
        account1.deposit(new Money(2500, "USD"));  // $25.00
        account1.withdraw(new Money(1000, "USD")); // $10.00

        // Transfer between accounts
        account1.transfer(account2, new Money(3000, "USD"));  // $30.00

        System.out.println("After operations:");
        System.out.println("Account 1: " + account1);
        System.out.println("Account 2: " + account2);

        // Defensive copying - transaction history
        System.out.println("\n--- Transaction History (Defensive Copy) ---");
        List<String> history = account1.getTransactionHistory();
        System.out.println("Account 1 transactions:");
        for (String transaction : history) {
            System.out.println("  " + transaction);
        }

        // Try to modify returned list - won't affect original
        history.clear();  // clears the copy, not the original
        System.out.println("After clearing copy, original history size: " +
                account1.getTransactionHistory().size());

        // Composition example
        System.out.println("\n--- Composition vs Inheritance ---");
        Address address = new Address("123 Main St", "Springfield", "12345");
        Customer customer = new Customer("Alice Johnson", address);

        customer.addAccount(account1);
        // customer.addAccount(account2);  // would throw - different owner

        System.out.println("Customer: " + customer);
        System.out.println("Customer address: " + customer.getAddress());
        System.out.println("Total balance: " + customer.getTotalBalance());

        // Error handling examples
        System.out.println("\n--- Error Handling ---");
        try {
            account1.withdraw(new Money(1000000, "USD"));  // too much
        } catch (IllegalArgumentException e) {
            System.out.println("Withdrawal failed: " + e.getMessage());
        }

        try {
            Money invalidMoney = new Money(-100, "USD");  // negative
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid money creation: " + e.getMessage());
        }

        try {
            Money eurMoney = new Money(1000, "EUR");
            account1.deposit(eurMoney);  // wrong currency
        } catch (IllegalArgumentException e) {
            System.out.println("Currency mismatch: " + e.getMessage());
        }

        System.out.println("\n=== Encapsulation Demo Complete ===");
    }
}