package com.luno.stage2.exercises.ex1_shape_system;

interface Shape {
    double getArea();
    double getPerimeter();
    void draw();
}

class Circle implements Shape {
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a circle with radius " + radius);
    }
}

class Rectangle implements Shape {
    private final double width;
    private final double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }

    @Override
    public void draw() {
        System.out.println("Drawing a rectangle " + width + "x" + height);
    }
}

class Triangle implements Shape {
    private final double a;
    private final double b;
    private final double c;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double getArea() {
        // Heron's formula: sqrt(s * (s-a) * (s-b) * (s-c))
        // where s = (a + b + c) / 2
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    @Override
    public double getPerimeter() {
        return a + b + c;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a triangle with sides " + a + ", " + b + ", " + c);
    }
}

public class StarterCode {
    public static void demonstrateShape(Shape shape) {
        shape.draw();
        System.out.printf("Area: %.2f, Perimeter: %.2f%n", shape.getArea(), shape.getPerimeter());
    }

    public static void main(String[] args) {
        System.out.println("=== Shape System Demo ===");

        // Create instances of each shape
        Shape circle = new Circle(5.0);
        Shape rectangle = new Rectangle(4.0, 6.0);
        Shape triangle = new Triangle(3.0, 4.0, 5.0);

        // Store them in a Shape[] array
        Shape[] shapes = {circle, rectangle, triangle};

        // Use demonstrateShape() method for each
        for (Shape shape : shapes) {
            demonstrateShape(shape);
            System.out.println();
        }

        System.out.println("Demo complete!");
    }
}