package com.luno.stage3.exercises.ex2_generic_containers;

import java.util.*;

// TODO: Make this class generic with two type parameters T and U
class Pair {
    // TODO: Add private fields for first and second values
    // private T first;
    // private U second;

    // TODO: Add constructor that takes both values
    public Pair(Object first, Object second) {
        // TODO: Implement
    }

    // TODO: Add getters and setters with proper types
    public Object getFirst() {
        return null; // TODO: Implement
    }

    public Object getSecond() {
        return null; // TODO: Implement
    }

    // TODO: Create a swapped version that returns Pair<U, T>
    public Pair swap() {
        return null; // TODO: Implement
    }

    @Override
    public String toString() {
        return "(" + getFirst() + ", " + getSecond() + ")";
    }
}

// TODO: Make this class generic with type parameter T
class Container {
    // TODO: Add private field for storing items
    // private List<T> items;

    public Container() {
        // TODO: Initialize the list
    }

    // TODO: Add method to add item of type T
    public void add(Object item) {
        // TODO: Implement
    }

    // TODO: Add method to get item by index, return type T
    public Object get(int index) {
        return null; // TODO: Implement
    }

    public int size() {
        return 0; // TODO: Implement
    }

    // TODO: Add generic method that filters items based on condition
    // Hint: Use a Predicate-like interface or create your own
    public Container filter(Object condition) {
        return null; // TODO: Implement
    }

    @Override
    public String toString() {
        return "Container" + "[]"; // TODO: Fix to show actual items
    }
}

// Simple predicate interface for filtering
interface Predicate<T> {
    boolean test(T item);
}

public class StarterCode {
    // This class is just for naming - your implementation goes in the classes above
}