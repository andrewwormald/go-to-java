// Stage 1.1: Basic Class and Object Creation
// Coming from Go: This is like defining a struct, but with methods attached

public class BasicClass {
    // Fields (like Go struct fields, but with access modifiers)
    private String name;        // private = only this class can access
    public int health;          // public = anyone can access
    protected int mana;         // protected = this class and subclasses

    // Constructor (Go doesn't have these - it's like a special "new" function)
    public BasicClass(String name, int health) {
        this.name = name;       // 'this' is like a method receiver in Go
        this.health = health;
        this.mana = 100;
    }

    // Default constructor (no parameters)
    public BasicClass() {
        this("Unknown", 100);   // calls the other constructor
    }

    // Getter method (encapsulation - controlled access to private fields)
    public String getName() {
        return name;
    }

    // Setter method with validation
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        }
    }

    // Method (like Go methods but defined inside the class)
    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) health = 0;
    }

    // Method that returns something
    public boolean isAlive() {
        return health > 0;
    }

    // toString method (like Go's String() method for fmt.Stringer interface)
    @Override
    public String toString() {
        return String.format("%s (Health: %d, Mana: %d)", name, health, mana);
    }

    // Main method - entry point (like func main() in Go)
    public static void main(String[] args) {
        // Creating objects (instances of the class)
        BasicClass character1 = new BasicClass("Warrior", 150);
        BasicClass character2 = new BasicClass(); // uses default constructor

        System.out.println("=== Character Creation ===");
        System.out.println("Character 1: " + character1);
        System.out.println("Character 2: " + character2);

        // Using methods
        System.out.println("\n=== Taking Damage ===");
        character1.takeDamage(50);
        System.out.println("After damage: " + character1);

        // Using getters/setters
        System.out.println("\n=== Using Getters/Setters ===");
        character2.setName("Mage");
        System.out.println("Renamed character: " + character2.getName());

        // Accessing public field directly (not recommended, but possible)
        character2.health = 80;
        System.out.println("Direct health change: " + character2);
    }
}