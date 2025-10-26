package com.luno.stage1;// Go to Java - Stage 1.2: Inheritance and Method Overriding
// Coming from Go: Java has inheritance (extends), Go uses composition

// Base class (parent/superclass)
class Character {
    protected String name;
    protected int health;
    protected int level;

    public Character(String name, int health) {
        this.name = name;
        this.health = health;
        this.level = 1;
    }

    // Method that can be overridden by subclasses
    public void attack() {
        System.out.println(name + " performs a basic attack!");
    }

    public void levelUp() {
        level++;
        health += 10;
        System.out.println(name + " leveled up to " + level + "!");
    }

    public String getStatus() {
        return String.format("%s - Level %d, Health: %d", name, level, health);
    }
}

// Derived class (child/subclass) - extends Character
class Warrior extends Character {
    private int armor;

    public Warrior(String name) {
        super(name, 120);  // calls parent constructor
        this.armor = 50;
    }

    // Method overriding - replacing parent's method with specialized version
    @Override
    public void attack() {
        System.out.println(name + " swings a mighty sword!");
    }

    // New method specific to Warrior
    public void defend() {
        System.out.println(name + " raises shield! Armor: " + armor);
    }

    // Override levelUp to add warrior-specific behavior
    @Override
    public void levelUp() {
        super.levelUp();  // call parent's levelUp first
        armor += 5;       // then add warrior-specific behavior
        System.out.println("Armor increased to " + armor);
    }
}

class Mage extends Character {
    private int mana;

    public Mage(String name) {
        super(name, 80);  // less health than warrior
        this.mana = 150;
    }

    @Override
    public void attack() {
        if (mana >= 20) {
            mana -= 20;
            System.out.println(name + " casts a fireball! Mana: " + mana);
        } else {
            System.out.println(name + " is out of mana, uses staff!");
        }
    }

    public void meditate() {
        mana += 30;
        System.out.println(name + " meditates. Mana restored to " + mana);
    }

    @Override
    public void levelUp() {
        super.levelUp();
        mana += 20;
        System.out.println("Mana increased to " + mana);
    }
}

public class Inheritance {
    public static void main(String[] args) {
        System.out.println("=== Creating Characters ===");
        Warrior warrior = new Warrior("Conan");
        Mage mage = new Mage("Gandalf");

        System.out.println(warrior.getStatus());
        System.out.println(mage.getStatus());

        System.out.println("\n=== Different Attack Behaviors ===");
        warrior.attack();  // calls Warrior's overridden method
        mage.attack();     // calls Mage's overridden method

        System.out.println("\n=== Class-Specific Methods ===");
        warrior.defend();  // only warriors can defend
        mage.meditate();   // only mages can meditate

        System.out.println("\n=== Leveling Up ===");
        warrior.levelUp(); // calls overridden method
        mage.levelUp();    // calls overridden method

        System.out.println("\n=== Polymorphism Demo ===");
        // This is like Go interfaces - same method name, different behavior
        Character[] party = {warrior, mage};

        for (Character character : party) {
            character.attack(); // calls the appropriate overridden method
        }

        System.out.println("\n=== Final Status ===");
        System.out.println(warrior.getStatus());
        System.out.println(mage.getStatus());
    }
}