public class ExampleTest {
    public static void main(String[] args) {
        System.out.println("=== Shape System Test ===");
        boolean allPassed = true;

        try {
            // Test Circle
            Circle circle = new Circle(5.0);
            circle.draw();
            double circleArea = circle.getArea();
            double circlePerimeter = circle.getPerimeter();

            System.out.printf("Area: %.2f, Perimeter: %.2f%n", circleArea, circlePerimeter);

            if (Math.abs(circleArea - Math.PI * 25) > 0.1) {
                System.out.println("❌ Circle area test failed");
                allPassed = false;
            } else if (Math.abs(circlePerimeter - 2 * Math.PI * 5) > 0.1) {
                System.out.println("❌ Circle perimeter test failed");
                allPassed = false;
            } else {
                System.out.println("✅ Circle test passed");
            }

            System.out.println();

            // Test Rectangle
            Rectangle rectangle = new Rectangle(4.0, 6.0);
            rectangle.draw();
            double rectArea = rectangle.getArea();
            double rectPerimeter = rectangle.getPerimeter();

            System.out.printf("Area: %.2f, Perimeter: %.2f%n", rectArea, rectPerimeter);

            if (Math.abs(rectArea - 24.0) > 0.01) {
                System.out.println("❌ Rectangle area test failed");
                allPassed = false;
            } else if (Math.abs(rectPerimeter - 20.0) > 0.01) {
                System.out.println("❌ Rectangle perimeter test failed");
                allPassed = false;
            } else {
                System.out.println("✅ Rectangle test passed");
            }

            System.out.println();

            // Test Triangle (3-4-5 right triangle)
            Triangle triangle = new Triangle(3.0, 4.0, 5.0);
            triangle.draw();
            double triangleArea = triangle.getArea();
            double trianglePerimeter = triangle.getPerimeter();

            System.out.printf("Area: %.2f, Perimeter: %.2f%n", triangleArea, trianglePerimeter);

            if (Math.abs(triangleArea - 6.0) > 0.1) {
                System.out.println("❌ Triangle area test failed: expected ~6.0, got " + triangleArea);
                allPassed = false;
            } else if (Math.abs(trianglePerimeter - 12.0) > 0.01) {
                System.out.println("❌ Triangle perimeter test failed");
                allPassed = false;
            } else {
                System.out.println("✅ Triangle test passed");
            }

            System.out.println();

            // Test polymorphism
            Shape[] shapes = {circle, rectangle, triangle};
            System.out.println("=== Polymorphism Test ===");
            for (Shape shape : shapes) {
                if (shape instanceof Circle) {
                    System.out.println("Processing Circle polymorphically");
                } else if (shape instanceof Rectangle) {
                    System.out.println("Processing Rectangle polymorphically");
                } else if (shape instanceof Triangle) {
                    System.out.println("Processing Triangle polymorphically");
                }

                // This should work for all shapes
                double area = shape.getArea();
                if (area > 0) {
                    System.out.println("✅ Polymorphic area calculation works");
                } else {
                    System.out.println("❌ Polymorphic area calculation failed");
                    allPassed = false;
                }
            }

        } catch (Exception e) {
            System.out.println("❌ Test failed with exception: " + e.getMessage());
            e.printStackTrace();
            allPassed = false;
        }

        System.out.println();
        if (allPassed) {
            System.out.println("🎉 All tests passed!");
            System.out.println("\n💡 Key concepts learned:");
            System.out.println("- Interface definition and implementation");
            System.out.println("- Polymorphism with interface references");
            System.out.println("- Method implementation across different classes");
        } else {
            System.out.println("❌ Some tests failed. Check your implementation.");
        }
    }
}