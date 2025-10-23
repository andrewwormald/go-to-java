// Stage 2.2: Abstract Classes and Mixed Implementation
// Coming from Go: No direct equivalent - combines interface concepts with partial implementation

// Abstract class - cannot be instantiated directly
abstract class Animal {
    protected String name;
    protected int age;

    // Constructor in abstract class
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Concrete method (has implementation)
    public void sleep() {
        System.out.println(name + " is sleeping...");
    }

    // Abstract method (must be implemented by subclasses)
    public abstract void makeSound();
    public abstract void move();

    // Another concrete method
    public String getInfo() {
        return name + " (" + age + " years old)";
    }
}

// Interface for additional behavior
interface Trainable {
    void train();
    boolean isTrained();
}

// Concrete class extending abstract class and implementing interface
class Dog extends Animal implements Trainable {
    private boolean trained;

    public Dog(String name, int age) {
        super(name, age);  // call abstract class constructor
        this.trained = false;
    }

    // Must implement abstract methods
    @Override
    public void makeSound() {
        System.out.println(name + " says: Woof! Woof!");
    }

    @Override
    public void move() {
        System.out.println(name + " runs around playfully");
    }

    // Implement interface methods
    @Override
    public void train() {
        trained = true;
        System.out.println(name + " has been trained!");
    }

    @Override
    public boolean isTrained() {
        return trained;
    }

    // Dog-specific method
    public void fetch() {
        if (trained) {
            System.out.println(name + " fetches the ball!");
        } else {
            System.out.println(name + " doesn't understand 'fetch' yet");
        }
    }
}

class Cat extends Animal {
    private int livesLeft;

    public Cat(String name, int age) {
        super(name, age);
        this.livesLeft = 9;
    }

    @Override
    public void makeSound() {
        System.out.println(name + " says: Meow!");
    }

    @Override
    public void move() {
        System.out.println(name + " gracefully jumps onto the counter");
    }

    public void useLive() {
        if (livesLeft > 0) {
            livesLeft--;
            System.out.println(name + " used a life! Lives left: " + livesLeft);
        }
    }
}

// Abstract class extending another abstract class
abstract class Bird extends Animal {
    protected boolean canFly;

    public Bird(String name, int age, boolean canFly) {
        super(name, age);
        this.canFly = canFly;
    }

    // Concrete method specific to birds
    public void preen() {
        System.out.println(name + " preens its feathers");
    }

    // Still abstract - subclasses must implement
    @Override
    public abstract void makeSound();

    @Override
    public void move() {
        if (canFly) {
            System.out.println(name + " soars through the sky");
        } else {
            System.out.println(name + " walks along the ground");
        }
    }
}

class Eagle extends Bird {
    public Eagle(String name, int age) {
        super(name, age, true);  // eagles can fly
    }

    @Override
    public void makeSound() {
        System.out.println(name + " screeches: SCREECH!");
    }

    public void hunt() {
        System.out.println(name + " swoops down to catch prey");
    }
}

public class AbstractClasses {
    public static void main(String[] args) {
        System.out.println("=== Abstract Classes and Interfaces ===");

        Dog dog = new Dog("Rex", 3);
        Cat cat = new Cat("Whiskers", 2);
        Eagle eagle = new Eagle("Freedom", 5);

        // Array of Animal references (polymorphism)
        Animal[] animals = {dog, cat, eagle};

        System.out.println("\n=== All Animals Info ===");
        for (Animal animal : animals) {
            System.out.println(animal.getInfo());
        }

        System.out.println("\n=== All Animals Make Sounds ===");
        for (Animal animal : animals) {
            animal.makeSound();  // calls overridden method
        }

        System.out.println("\n=== All Animals Move ===");
        for (Animal animal : animals) {
            animal.move();  // calls overridden method
        }

        System.out.println("\n=== Training Demo ===");
        // Only dog implements Trainable
        if (dog instanceof Trainable) {
            Trainable trainableAnimal = dog;  // interface reference
            trainableAnimal.train();
            dog.fetch();  // before training
        }

        System.out.println("\n=== Specific Behaviors ===");
        dog.fetch();      // after training
        cat.useLive();    // cat-specific
        eagle.hunt();     // eagle-specific
        eagle.preen();    // bird-specific

        System.out.println("\n=== Sleep Time ===");
        for (Animal animal : animals) {
            animal.sleep();  // inherited concrete method
        }

        // Cannot instantiate abstract classes:
        // Animal animal = new Animal("test", 1);  // Compilation error!
        // Bird bird = new Bird("test", 1, true);  // Compilation error!
    }
}