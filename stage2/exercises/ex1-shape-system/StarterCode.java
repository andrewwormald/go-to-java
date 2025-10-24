// TODO: Define Shape interface
interface Shape {
    // TODO: Add method signatures for:
    // - double getArea()
    // - double getPerimeter()
    // - void draw()
}

// TODO: Implement Circle class
class Circle /* implements Shape */ {
    // TODO: Add private field for radius
    // TODO: Add constructor
    // TODO: Implement all Shape methods
    // Area = π * r²
    // Perimeter = 2 * π * r
}

// TODO: Implement Rectangle class
class Rectangle /* implements Shape */ {
    // TODO: Add private fields for width and height
    // TODO: Add constructor
    // TODO: Implement all Shape methods
    // Area = width * height
    // Perimeter = 2 * (width + height)
}

// TODO: Implement Triangle class
class Triangle /* implements Shape */ {
    // TODO: Add private fields for three sides (a, b, c)
    // TODO: Add constructor
    // TODO: Implement all Shape methods
    // Use Heron's formula for area: sqrt(s * (s-a) * (s-b) * (s-c))
    // where s = (a + b + c) / 2
    // Perimeter = a + b + c
}

public class StarterCode {
    // TODO: Add static method to demonstrate polymorphism
    public static void demonstrateShape(Shape shape) {
        // TODO: Call draw(), then print area and perimeter
    }

    public static void main(String[] args) {
        System.out.println("=== Shape System Demo ===");

        // TODO: Create instances of each shape
        // TODO: Store them in a Shape[] array
        // TODO: Use demonstrateShape() method for each

        System.out.println("Demo complete!");
    }
}