# Go to Java - Stage 1: Java Basics & OOP Foundations

## Concepts Covered
- Classes and Objects (vs Go structs)
- Constructors (Java-specific concept)
- Access modifiers (private, public, protected)
- Encapsulation with getters/setters
- Inheritance with `extends`
- Method overriding with `@Override`
- Polymorphism basics

## Files
1. **BasicClass.java** - Classes, constructors, methods, encapsulation
2. **Inheritance.java** - Inheritance, method overriding, polymorphism

## How to Run

```bash
# Compile and run BasicClass
javac stage1/BasicClass.java
java -cp . stage1.BasicClass

# Compile and run Inheritance
javac stage1/Inheritance.java
java -cp . stage1.Inheritance
```

## Key Differences from Go
- **Classes vs Structs**: Java classes include both data and behavior
- **Constructors**: Special methods for object initialization
- **Inheritance**: Java uses `extends`, Go uses composition
- **Access Modifiers**: Java has fine-grained access control
- **Method Receivers**: Java methods are defined inside classes
- **Polymorphism**: Similar to Go interfaces but inheritance-based