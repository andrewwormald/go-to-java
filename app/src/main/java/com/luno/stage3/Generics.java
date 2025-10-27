package com.luno.stage3;// Go to Java - Stage 3.2: Generics and Type Safety
// Coming from Go: Similar to Go generics but more verbose syntax

import java.util.*;

// Generic class definition
class Box<T> {
    private T content;

    public void put(T item) {
        this.content = item;
    }

    public T get() {
        return content;
    }

    public boolean isEmpty() {
        return content == null;
    }

    @Override
    public String toString() {
        return "Box{" + content + "}";
    }
}

// Generic class with multiple type parameters
class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() { return key; }
    public V getValue() { return value; }

    @Override
    public String toString() {
        return "(" + key + ", " + value + ")";
    }
}

// Generic interface
interface Comparable<T> {
    int compareTo(T other);
}

// Class implementing generic interface
class Person implements Comparable<Person> {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Person other) {
        return Integer.compare(this.age, other.age);
    }

    public String getName() { return name; }
    public int getAge() { return age; }

    @Override
    public String toString() {
        return name + "(" + age + ")";
    }
}

public class Generics {
    // Generic method (type parameter in method)
    public static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // Generic method with bounded type parameter
    public static <T extends Comparable<T>> T findMax(List<T> list) {
        if (list.isEmpty()) {
            return null;
        }

        T max = list.get(0);
        for (T item : list) {
            if (item.compareTo(max) > 0) {
                max = item;
            }
        }
        return max;
    }

    // Wildcards - upper bounded
    public static double sumNumbers(List<? extends Number> numbers) {
        double sum = 0.0;
        for (Number num : numbers) {
            sum += num.doubleValue();
        }
        return sum;
    }

    // Wildcards - lower bounded
    public static void addNumbers(List<? super Integer> numbers) {
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
    }

    public static void main(String[] args) {
        System.out.println("=== Java Generics ===");

        // Generic classes
        System.out.println("\n--- Generic Box Class ---");
        Box<String> stringBox = new Box<>();
        stringBox.put("Hello, Generics!");
        System.out.println("String box: " + stringBox.get());

        Box<Integer> intBox = new Box<>();
        intBox.put(42);
        System.out.println("Integer box: " + intBox.get());

        // Can't put wrong type - compile error!
        // stringBox.put(123);  // Compilation error!

        // Multiple type parameters
        System.out.println("\n--- Pair Class (Multiple Type Parameters) ---");
        Pair<String, Integer> nameAge = new Pair<>("Alice", 25);
        Pair<Integer, String> idName = new Pair<>(101, "Bob");

        System.out.println("Name-Age pair: " + nameAge);
        System.out.println("ID-Name pair: " + idName);

        // Generic collections (type safety)
        System.out.println("\n--- Type-Safe Collections ---");
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 25));
        people.add(new Person("Bob", 30));
        people.add(new Person("Charlie", 20));

        System.out.println("People: " + people);

        // Generic methods
        System.out.println("\n--- Generic Methods ---");
        String[] words = {"hello", "world", "java"};
        System.out.println("Before swap: " + Arrays.toString(words));
        swap(words, 0, 2);
        System.out.println("After swap: " + Arrays.toString(words));

        Integer[] nums = {5, 2, 8, 1, 9};
        System.out.println("Before swap: " + Arrays.toString(nums));
        swap(nums, 1, 3);
        System.out.println("After swap: " + Arrays.toString(nums));

        // Bounded type parameters
        System.out.println("\n--- Bounded Type Parameters ---");
        Person oldest = findMax(people);
        System.out.println("Oldest person: " + oldest);

        List<Integer> numbers = Arrays.asList(1, 5, 3, 9, 2);
        // Note: Integer implements Comparable<Integer>, which works with our findMax method
        Integer maxNum = Collections.max(numbers); // Using Collections.max instead for this example
        System.out.println("Max number: " + maxNum);

        // Wildcards
        System.out.println("\n--- Wildcards ---");

        // Upper bounded wildcard (? extends Number)
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        List<Double> doubles = Arrays.asList(1.1, 2.2, 3.3);

        System.out.println("Sum of integers: " + sumNumbers(integers));
        System.out.println("Sum of doubles: " + sumNumbers(doubles));

        // Lower bounded wildcard (? super Integer)
        List<Number> numberList = new ArrayList<>();
        addNumbers(numberList);
        System.out.println("Added numbers: " + numberList);

        // Diamond operator (Java 7+) - type inference
        System.out.println("\n--- Diamond Operator (Type Inference) ---");
        Map<String, List<Integer>> complexMap = new HashMap<>();  // inferred types
        complexMap.put("evens", new ArrayList<>());
        complexMap.put("odds", new ArrayList<>());

        complexMap.get("evens").addAll(Arrays.asList(2, 4, 6, 8));
        complexMap.get("odds").addAll(Arrays.asList(1, 3, 5, 7));

        System.out.println("Complex map: " + complexMap);

        // Raw types (avoid these - shown for understanding)
        System.out.println("\n--- Raw Types (Don't Use!) ---");
        @SuppressWarnings("rawtypes")  // suppress compiler warning
        List rawList = new ArrayList();  // raw type - no generics
        rawList.add("string");
        rawList.add(123);  // can add anything - dangerous!

        System.out.println("Raw list: " + rawList);
        // Object obj = rawList.get(0);  // returns Object, need casting
    }
}