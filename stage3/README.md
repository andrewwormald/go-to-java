# Stage 3: Collections & Generics

## Concepts Covered
- Collections Framework (ArrayList, LinkedList, HashMap, HashSet, TreeMap)
- Generic classes and methods
- Type parameters and bounded types
- Wildcards (? extends, ? super)
- Iterator pattern and enhanced for loops
- Collections utility methods
- Type safety and type erasure

## Files
1. **Collections.java** - Core collections (List, Map, Set) and utility methods
2. **Generics.java** - Generic classes, methods, wildcards, and type safety
3. **InventorySystem.java** - RPG inventory system using collections and generics

## How to Run

```bash
# Compile and run each file
javac stage3/Collections.java
java -cp . stage3.Collections

javac stage3/Generics.java
java -cp . stage3.Generics

javac stage3/InventorySystem.java
java -cp . stage3.InventorySystem
```

## Collections Comparison with Go

| Java Collection | Go Equivalent | Notes |
|----------------|---------------|-------|
| `ArrayList<T>` | `[]T` | Dynamic array/slice |
| `LinkedList<T>` | No direct equivalent | Doubly-linked list |
| `HashMap<K,V>` | `map[K]V` | Hash table |
| `HashSet<T>` | `map[T]bool` | Unique values only |
| `TreeMap<K,V>` | No direct equivalent | Sorted map |

## Generics Comparison with Go

| Java | Go |
|------|-----|
| `Class<T>` | `type Class[T any] struct` |
| `<T extends Comparable<T>>` | `[T constraints.Ordered]` |
| `List<? extends Number>` | `interface{ ~int \| ~float64 }` |
| `List<? super Integer>` | No direct equivalent |

## Key Concepts
- **Type Safety**: Generics prevent ClassCastException at runtime
- **Type Erasure**: Generic type info removed at runtime
- **Diamond Operator**: `<>` for type inference (Java 7+)
- **Wildcards**: Flexible type bounds for method parameters
- **Iterator**: Pattern for traversing collections safely