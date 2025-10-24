// Go to Java - Stage 2.1: Interfaces and Implementation
// Coming from Go: Java interfaces are similar but more explicit

// Interface definition (like Go interface but must be explicitly implemented)
interface Drawable {
    void draw();                    // abstract method (no implementation)
    default void highlight() {     // default method (Java 8+, has implementation)
        System.out.println("*highlighted*");
    }

    // Static method in interface (Java 8+)
    static void printDrawingInfo() {
        System.out.println("Drawing system v1.0");
    }
}

// Another interface to show multiple interface implementation
interface Moveable {
    void move(int x, int y);
    int getSpeed();
}

// Class implementing interfaces (uses 'implements' keyword)
class Circle implements Drawable, Moveable {
    private int x, y, radius;
    private int speed;

    public Circle(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.speed = 5;
    }

    // Must implement all abstract methods from interfaces
    @Override
    public void draw() {
        System.out.println("Drawing circle at (" + x + "," + y + ") with radius " + radius);
    }

    @Override
    public void move(int newX, int newY) {
        this.x = newX;
        this.y = newY;
        System.out.println("Circle moved to (" + x + "," + y + ")");
    }

    @Override
    public int getSpeed() {
        return speed;
    }
}

class Rectangle implements Drawable, Moveable {
    private int x, y, width, height;
    private int speed;

    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = 3;
    }

    @Override
    public void draw() {
        System.out.println("Drawing rectangle at (" + x + "," + y + ") " + width + "x" + height);
    }

    @Override
    public void move(int newX, int newY) {
        this.x = newX;
        this.y = newY;
        System.out.println("Rectangle moved to (" + x + "," + y + ")");
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    // Override default method to customize behavior
    @Override
    public void highlight() {
        System.out.println(">>> RECTANGLE HIGHLIGHTED <<<");
    }
}

public class Interfaces {
    // Method that accepts any Drawable (polymorphism)
    public static void renderShape(Drawable shape) {
        shape.draw();
        shape.highlight();
    }

    // Method that accepts any Moveable
    public static void moveShape(Moveable shape, int x, int y) {
        System.out.println("Moving shape at speed: " + shape.getSpeed());
        shape.move(x, y);
    }

    public static void main(String[] args) {
        System.out.println("=== Interface Implementation Demo ===");

        Circle circle = new Circle(10, 20, 15);
        Rectangle rectangle = new Rectangle(0, 0, 50, 30);

        // Using interfaces for polymorphism
        Drawable[] shapes = {circle, rectangle};

        System.out.println("\n=== Drawing All Shapes ===");
        for (Drawable shape : shapes) {
            renderShape(shape);  // polymorphic method call
        }

        System.out.println("\n=== Moving Shapes ===");
        Moveable[] moveableShapes = {circle, rectangle};
        for (Moveable shape : moveableShapes) {
            moveShape(shape, 100, 100);
        }

        System.out.println("\n=== Static Interface Method ===");
        Drawable.printDrawingInfo();

        // Interface reference can hold any implementing object
        System.out.println("\n=== Interface References ===");
        Drawable drawableCircle = new Circle(5, 5, 10);
        Moveable moveableRect = new Rectangle(20, 20, 40, 40);

        drawableCircle.draw();
        moveableRect.move(30, 30);
    }
}