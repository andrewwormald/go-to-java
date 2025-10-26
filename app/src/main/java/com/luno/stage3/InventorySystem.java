package com.luno.stage3;// Go to Java - Stage 3.3: Inventory Management System
// Fun project combining collections, generics, and iterators

import java.util.*;

// Generic Item class
abstract class Item {
    protected String name;
    protected String description;
    protected double weight;
    protected int value;

    public Item(String name, String description, double weight, int value) {
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.value = value;
    }

    public abstract String getType();
    public abstract void use();

    // Getters
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getWeight() { return weight; }
    public int getValue() { return value; }

    @Override
    public String toString() {
        return String.format("%s (%s) - Weight: %.1f, Value: %d",
                name, getType(), weight, value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Item item = (Item) obj;
        return Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

class Weapon extends Item {
    private int damage;

    public Weapon(String name, String description, double weight, int value, int damage) {
        super(name, description, weight, value);
        this.damage = damage;
    }

    @Override
    public String getType() { return "WEAPON"; }

    @Override
    public void use() {
        System.out.println("Wielding " + name + " (Damage: " + damage + ")");
    }

    public int getDamage() { return damage; }

    @Override
    public String toString() {
        return super.toString() + ", Damage: " + damage;
    }
}

class Potion extends Item {
    private int healAmount;

    public Potion(String name, String description, double weight, int value, int healAmount) {
        super(name, description, weight, value);
        this.healAmount = healAmount;
    }

    @Override
    public String getType() { return "POTION"; }

    @Override
    public void use() {
        System.out.println("Drinking " + name + " (Heals: " + healAmount + " HP)");
    }

    public int getHealAmount() { return healAmount; }

    @Override
    public String toString() {
        return super.toString() + ", Heals: " + healAmount + " HP";
    }
}

class Armor extends Item {
    private int defense;

    public Armor(String name, String description, double weight, int value, int defense) {
        super(name, description, weight, value);
        this.defense = defense;
    }

    @Override
    public String getType() { return "ARMOR"; }

    @Override
    public void use() {
        System.out.println("Equipping " + name + " (Defense: " + defense + ")");
    }

    public int getDefense() { return defense; }

    @Override
    public String toString() {
        return super.toString() + ", Defense: " + defense;
    }
}

// Generic Inventory class
class Inventory<T extends Item> implements Iterable<T> {
    private List<T> items;
    private Map<String, Integer> itemCounts;
    private double maxWeight;
    private double currentWeight;

    public Inventory(double maxWeight) {
        this.items = new ArrayList<>();
        this.itemCounts = new HashMap<>();
        this.maxWeight = maxWeight;
        this.currentWeight = 0.0;
    }

    public boolean addItem(T item) {
        if (currentWeight + item.getWeight() > maxWeight) {
            System.out.println("Cannot add " + item.getName() + " - inventory too heavy!");
            return false;
        }

        items.add(item);
        currentWeight += item.getWeight();
        itemCounts.put(item.getName(), itemCounts.getOrDefault(item.getName(), 0) + 1);
        return true;
    }

    public boolean removeItem(String itemName) {
        Iterator<T> iterator = items.iterator();
        while (iterator.hasNext()) {
            T item = iterator.next();
            if (item.getName().equals(itemName)) {
                iterator.remove();
                currentWeight -= item.getWeight();
                int count = itemCounts.get(itemName);
                if (count <= 1) {
                    itemCounts.remove(itemName);
                } else {
                    itemCounts.put(itemName, count - 1);
                }
                return true;
            }
        }
        return false;
    }

    public T findItem(String name) {
        for (T item : items) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    public List<T> findItemsByType(String type) {
        List<T> result = new ArrayList<>();
        for (T item : items) {
            if (item.getType().equals(type)) {
                result.add(item);
            }
        }
        return result;
    }

    public void sortByValue() {
        items.sort((a, b) -> Integer.compare(b.getValue(), a.getValue()));
    }

    public void sortByWeight() {
        items.sort(Comparator.comparingDouble(Item::getWeight));
    }

    public int getTotalValue() {
        return items.stream().mapToInt(Item::getValue).sum();
    }

    // Iterator implementation for for-each loops
    @Override
    public Iterator<T> iterator() {
        return items.iterator();
    }

    // Statistics
    public void printStatistics() {
        System.out.println("\n=== Inventory Statistics ===");
        System.out.println("Items: " + items.size());
        System.out.println("Weight: " + String.format("%.1f/%.1f", currentWeight, maxWeight));
        System.out.println("Total Value: " + getTotalValue());
        System.out.println("Item Counts: " + itemCounts);

        // Group by type
        Map<String, Integer> typeCount = new HashMap<>();
        for (T item : items) {
            typeCount.put(item.getType(), typeCount.getOrDefault(item.getType(), 0) + 1);
        }
        System.out.println("By Type: " + typeCount);
    }

    public void printInventory() {
        System.out.println("\n=== Inventory Contents ===");
        if (items.isEmpty()) {
            System.out.println("Inventory is empty");
            return;
        }

        for (T item : items) {
            System.out.println("  " + item);
        }
    }
}

public class InventorySystem {
    public static void main(String[] args) {
        System.out.println("=== RPG Inventory Management System ===");

        // Create a general inventory that can hold any Item
        Inventory<Item> playerInventory = new Inventory<>(50.0);

        // Create various items
        Item[] gameItems = {
            new Weapon("Iron Sword", "A sturdy iron blade", 3.5, 150, 25),
            new Weapon("Magic Staff", "Channels mystical energy", 2.0, 300, 15),
            new Armor("Leather Armor", "Basic protection", 5.0, 100, 10),
            new Armor("Chain Mail", "Interlocking metal rings", 8.0, 250, 20),
            new Potion("Health Potion", "Restores health", 0.5, 50, 25),
            new Potion("Mana Potion", "Restores magical energy", 0.5, 60, 30),
            new Potion("Health Potion", "Another health potion", 0.5, 50, 25)  // duplicate
        };

        // Add items to inventory
        System.out.println("\n--- Adding Items ---");
        for (Item item : gameItems) {
            boolean added = playerInventory.addItem(item);
            if (added) {
                System.out.println("Added: " + item.getName());
            }
        }

        playerInventory.printInventory();
        playerInventory.printStatistics();

        // Search and use items
        System.out.println("\n--- Finding and Using Items ---");
        Item sword = playerInventory.findItem("Iron Sword");
        if (sword != null) {
            sword.use();
        }

        // Find items by type using generics
        System.out.println("\n--- Finding Items by Type ---");
        List<Item> weapons = playerInventory.findItemsByType("WEAPON");
        System.out.println("Weapons found: " + weapons.size());
        for (Item weapon : weapons) {
            System.out.println("  " + weapon);
        }

        List<Item> potions = playerInventory.findItemsByType("POTION");
        System.out.println("Potions found: " + potions.size());

        // Sorting
        System.out.println("\n--- Sorting by Value (Descending) ---");
        playerInventory.sortByValue();
        playerInventory.printInventory();

        System.out.println("\n--- Sorting by Weight ---");
        playerInventory.sortByWeight();
        playerInventory.printInventory();

        // Remove items
        System.out.println("\n--- Removing Items ---");
        boolean removed = playerInventory.removeItem("Health Potion");
        System.out.println("Removed health potion: " + removed);

        // Try to remove non-existent item
        removed = playerInventory.removeItem("Dragon Scale");
        System.out.println("Removed dragon scale: " + removed);

        playerInventory.printStatistics();

        // Using enhanced for loop (iterator)
        System.out.println("\n--- Using Iterator (Enhanced For Loop) ---");
        for (Item item : playerInventory) {
            System.out.println("Item: " + item.getName() + " (Value: " + item.getValue() + ")");
        }

        // Specialized inventories using generics
        System.out.println("\n--- Specialized Weapon Inventory ---");
        Inventory<Weapon> weaponInventory = new Inventory<>(20.0);
        weaponInventory.addItem(new Weapon("Excalibur", "Legendary sword", 4.0, 1000, 50));
        weaponInventory.addItem(new Weapon("Bow", "Elven longbow", 1.5, 200, 20));

        for (Weapon weapon : weaponInventory) {
            System.out.println("Weapon damage: " + weapon.getDamage());
        }

        System.out.println("\n=== Inventory System Demo Complete ===");
    }
}