# Go to Java - Stage 2: Interfaces & Polymorphism

## Concepts Covered
- Interface definition and implementation
- Default and static methods in interfaces
- Abstract classes vs interfaces
- Multiple interface implementation
- Method overriding vs overloading
- Polymorphism with interface references
- `instanceof` operator

## Files
1. **Interfaces.java** - Interface basics, multiple implementations, polymorphism
2. **AbstractClasses.java** - Abstract classes, mixed concrete/abstract methods
3. **CardGame.java** - Fun card game demonstrating all concepts together

## How to Run

```bash
# Compile and run each file
javac stage2/Interfaces.java
java -cp . stage2.Interfaces

javac stage2/AbstractClasses.java
java -cp . stage2.AbstractClasses

javac stage2/CardGame.java
java -cp . stage2.CardGame
```

## Key Differences from Go

| Java | Go |
|------|----|
| `implements` keyword | Implicit interface satisfaction |
| Abstract classes | No equivalent (use composition) |
| Default methods | No equivalent |
| Multiple inheritance via interfaces | Embedded interfaces |
| Explicit interface declaration | Duck typing |

## Important Concepts
- **Interface**: Contract defining what methods a class must implement
- **Abstract Class**: Partial implementation - cannot be instantiated
- **Polymorphism**: Same interface, different behavior
- **Default Methods**: Interfaces can provide default implementations (Java 8+)