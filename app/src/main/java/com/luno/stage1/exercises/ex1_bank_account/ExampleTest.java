package com.luno.stage1.exercises.ex1_bank_account;

public class ExampleTest {
    public static void main(String[] args) {
        System.out.println("=== Bank Account Test ===");
        boolean allPassed = true;

        try {
            // Test account creation
            StarterCode account = new StarterCode("12345", "John Doe", 1000.00);
            System.out.println(account);

            // Test basic getters
            if (!"12345".equals(account.getAccountNumber())) {
                System.out.println("❌ Account number test failed");
                allPassed = false;
            } else if (!"John Doe".equals(account.getAccountHolder())) {
                System.out.println("❌ Account holder test failed");
                allPassed = false;
            } else if (Math.abs(account.getBalance() - 1000.00) > 0.01) {
                System.out.println("❌ Initial balance test failed");
                allPassed = false;
            } else {
                System.out.println("✅ Account creation test passed");
            }

            // Test deposit
            account.deposit(250.00);
            if (Math.abs(account.getBalance() - 1250.00) > 0.01) {
                System.out.println("❌ Deposit test failed: expected 1250.00, got " + account.getBalance());
                allPassed = false;
            } else {
                System.out.println("✅ Deposit test passed");
            }

            // Test valid withdrawal
            boolean withdrawSuccess = account.withdraw(250.00);
            if (!withdrawSuccess || Math.abs(account.getBalance() - 1000.00) > 0.01) {
                System.out.println("❌ Valid withdrawal test failed");
                allPassed = false;
            } else {
                System.out.println("✅ Valid withdrawal test passed");
            }

            // Test invalid withdrawal (insufficient funds)
            boolean withdrawFail = account.withdraw(2000.00);
            if (withdrawFail || Math.abs(account.getBalance() - 1000.00) > 0.01) {
                System.out.println("❌ Insufficient funds test failed");
                allPassed = false;
            } else {
                System.out.println("✅ Insufficient funds test passed");
            }

            // Test negative deposit
            try {
                account.deposit(-100.00);
                System.out.println("❌ Negative deposit validation failed");
                allPassed = false;
            } catch (Exception e) {
                System.out.println("✅ Negative deposit validation passed");
            }

            // Test toString format
            String accountStr = account.toString();
            if (accountStr != null && accountStr.contains("12345") &&
                accountStr.contains("John Doe") && accountStr.contains("$1000.00")) {
                System.out.println("✅ toString format test passed");
            } else {
                System.out.println("❌ toString format test failed");
                allPassed = false;
            }

        } catch (Exception e) {
            System.out.println("❌ Test failed with exception: " + e.getMessage());
            allPassed = false;
        }

        System.out.println();
        if (allPassed) {
            System.out.println("🎉 All tests passed!");
            System.out.println("\n💡 Key concepts learned:");
            System.out.println("- Private fields with public methods");
            System.out.println("- Input validation");
            System.out.println("- Proper encapsulation");
        } else {
            System.out.println("❌ Some tests failed. Check your implementation.");
        }
    }
}