package com.luno.stage3.exercises.ex2_generic_containers;

public class ExampleTest {
    public static void main(String[] args) {
        System.out.println("=== Generic Containers Test ===");
        boolean allPassed = true;

        // Test Pair
        try {
            // Note: You'll need to fix the generic types in StarterCode.java
            // This test assumes you've made Pair<T, U> generic

            // For now, this will work with Object, but should be generic
            Pair namAge = new Pair("Alice", 25);
            System.out.println("Pair: " + namAge);

            if (namAge.getFirst().equals("Alice") && namAge.getSecond().equals(25)) {
                System.out.println("✅ Pair creation test passed");
            } else {
                System.out.println("❌ Pair creation test failed");
                allPassed = false;
            }

            // Test swap
            Pair swapped = namAge.swap();
            System.out.println("Swapped: " + swapped);

            if (swapped.getFirst().equals(25) && swapped.getSecond().equals("Alice")) {
                System.out.println("✅ Pair swap test passed");
            } else {
                System.out.println("❌ Pair swap test failed");
                allPassed = false;
            }

        } catch (Exception e) {
            System.out.println("❌ Pair tests failed with exception: " + e.getMessage());
            allPassed = false;
        }

        // Test Container
        try {
            Container container = new Container();
            container.add("Apple");
            container.add("Banana");
            container.add("Cherry");

            System.out.println("Container size: " + container.size());

            if (container.size() == 3) {
                System.out.println("✅ Container size test passed");
            } else {
                System.out.println("❌ Container size test failed: expected 3, got " + container.size());
                allPassed = false;
            }

            // Test get
            Object first = container.get(0);
            System.out.println("First item: " + first);

            if ("Apple".equals(first)) {
                System.out.println("✅ Container get test passed");
            } else {
                System.out.println("❌ Container get test failed");
                allPassed = false;
            }

            // Test filter (bonus if implemented)
            try {
                // Simple predicate for items starting with 'A'
                Predicate<String> startsWithA = item -> item.toString().startsWith("A");
                Container filtered = container.filter(startsWithA);
                System.out.println("Filtered container: " + filtered);

                if (filtered.size() == 1) {
                    System.out.println("✅ Container filter test passed");
                } else {
                    System.out.println("❌ Container filter test failed");
                    allPassed = false;
                }
            } catch (Exception e) {
                System.out.println("⚠️ Filter test skipped (optional): " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("❌ Container tests failed with exception: " + e.getMessage());
            allPassed = false;
        }

        System.out.println();
        if (allPassed) {
            System.out.println("🎉 All tests passed!");
            System.out.println("\n💡 Next steps:");
            System.out.println("1. Make sure your classes are truly generic (no Object types)");
            System.out.println("2. Try creating Pair<String, Integer> and see compilation errors with wrong types");
            System.out.println("3. Implement the filter method if you haven't already");
        } else {
            System.out.println("❌ Some tests failed. Check your implementation.");
        }
    }
}