// Go to Java - Stage 3.1: Collections Framework
// Coming from Go: Similar to slices/maps but with rich type hierarchy

import java.util.*;

public class Collections {
    public static void main(String[] args) {
        System.out.println("=== Java Collections Framework ===");

        // ArrayList - like Go slice, but with methods
        System.out.println("\n--- ArrayList (like Go slice) ---");
        ArrayList<String> names = new ArrayList<>();
        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");
        names.add(1, "Diana");  // insert at index

        System.out.println("Names: " + names);
        System.out.println("Size: " + names.size());
        System.out.println("First: " + names.get(0));
        System.out.println("Contains Bob: " + names.contains("Bob"));

        // Enhanced for loop (like Go range)
        System.out.print("Iterating: ");
        for (String name : names) {
            System.out.print(name + " ");
        }
        System.out.println();

        // LinkedList - different performance characteristics
        System.out.println("\n--- LinkedList (efficient insertion/deletion) ---");
        LinkedList<Integer> numbers = new LinkedList<>();
        numbers.addFirst(10);
        numbers.addLast(30);
        numbers.add(1, 20);  // middle
        System.out.println("Numbers: " + numbers);
        System.out.println("First: " + numbers.peekFirst());
        System.out.println("Last: " + numbers.peekLast());

        // HashMap - like Go map
        System.out.println("\n--- HashMap (like Go map) ---");
        HashMap<String, Integer> ages = new HashMap<>();
        ages.put("Alice", 25);
        ages.put("Bob", 30);
        ages.put("Charlie", 35);

        System.out.println("Ages: " + ages);
        System.out.println("Alice's age: " + ages.get("Alice"));
        System.out.println("Contains Diana: " + ages.containsKey("Diana"));

        // Iterate over map (similar to Go range over map)
        System.out.println("All ages:");
        for (Map.Entry<String, Integer> entry : ages.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }

        // Alternative iteration
        System.out.println("Just names:");
        for (String name : ages.keySet()) {
            System.out.println("  " + name);
        }

        // HashSet - like Go map[T]bool for set operations
        System.out.println("\n--- HashSet (unique values only) ---");
        HashSet<String> uniqueNames = new HashSet<>();
        uniqueNames.add("Alice");
        uniqueNames.add("Bob");
        uniqueNames.add("Alice");  // duplicate, won't be added
        uniqueNames.add("Charlie");

        System.out.println("Unique names: " + uniqueNames);
        System.out.println("Size: " + uniqueNames.size());

        // Set operations
        HashSet<String> otherNames = new HashSet<>();
        otherNames.add("Bob");
        otherNames.add("Diana");
        otherNames.add("Eve");

        // Union (addAll)
        HashSet<String> union = new HashSet<>(uniqueNames);
        union.addAll(otherNames);
        System.out.println("Union: " + union);

        // Intersection (retainAll)
        HashSet<String> intersection = new HashSet<>(uniqueNames);
        intersection.retainAll(otherNames);
        System.out.println("Intersection: " + intersection);

        // TreeMap - sorted map
        System.out.println("\n--- TreeMap (sorted by key) ---");
        TreeMap<String, String> sortedData = new TreeMap<>();
        sortedData.put("zebra", "animal");
        sortedData.put("apple", "fruit");
        sortedData.put("banana", "fruit");
        sortedData.put("cat", "animal");

        System.out.println("Sorted data: " + sortedData);

        // Collections utility methods
        System.out.println("\n--- Collections Utility Methods ---");
        List<Integer> nums = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9));
        System.out.println("Original: " + nums);

        Collections.sort(nums);
        System.out.println("Sorted: " + nums);

        Collections.reverse(nums);
        System.out.println("Reversed: " + nums);

        Collections.shuffle(nums);
        System.out.println("Shuffled: " + nums);

        System.out.println("Max: " + Collections.max(nums));
        System.out.println("Min: " + Collections.min(nums));

        // Converting between collection types
        System.out.println("\n--- Converting Between Types ---");
        List<String> list = new ArrayList<>(uniqueNames);  // Set to List
        Set<String> backToSet = new HashSet<>(list);       // List to Set
        System.out.println("List from set: " + list);
        System.out.println("Set from list: " + backToSet);

        // Array to List and back
        String[] array = {"one", "two", "three"};
        List<String> listFromArray = Arrays.asList(array);
        System.out.println("List from array: " + listFromArray);

        String[] backToArray = list.toArray(new String[0]);
        System.out.println("Array from list: " + Arrays.toString(backToArray));
    }
}