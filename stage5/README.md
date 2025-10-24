# Go to Java - Stage 5: Advanced Features & Flyweight Pattern

## Concepts Covered
- Exception handling (try/catch/finally, checked vs unchecked)
- Lambda expressions and functional interfaces
- Streams API for data processing
- Optional class for null safety
- Method references
- Flyweight pattern for memory optimization
- Advanced design patterns in practice

## Files
1. **ExceptionHandling.java** - Comprehensive exception handling patterns
2. **FunctionalFeatures.java** - Lambda expressions, streams, and Optional
3. **FlyweightPattern.java** - Memory-efficient object sharing pattern

## How to Run

```bash
# Compile and run each file
javac stage5/ExceptionHandling.java
java -cp . stage5.ExceptionHandling

javac stage5/FunctionalFeatures.java
java -cp . stage5.FunctionalFeatures

javac stage5/FlyweightPattern.java
java -cp . stage5.FlyweightPattern
```

## Exception Handling vs Go

| Java | Go |
|------|-----|
| `try/catch/finally` | `if err != nil` |
| Checked exceptions | Explicit error returns |
| `throws` declaration | Error in return tuple |
| Exception chaining | Wrapped errors |
| `try-with-resources` | `defer` statements |

## Functional Programming Features

### Lambda Expressions
```java
// Java
list.forEach(item -> System.out.println(item));

// Go equivalent
for _, item := range list {
    fmt.Println(item)
}
```

### Streams vs Go Pipelines
```java
// Java Streams
numbers.stream()
    .filter(n -> n > 0)
    .map(n -> n * 2)
    .collect(Collectors.toList());

// Go would use explicit loops or generics
```

### Optional vs Go Pointers
```java
// Java Optional
Optional<String> value = Optional.ofNullable(getValue());
value.ifPresent(System.out::println);

// Go pointer checking
if value := getValue(); value != nil {
    fmt.Println(*value)
}
```

## Flyweight Pattern Benefits

1. **Memory Efficiency**: Share common data among many objects
2. **Performance**: Reduce object creation overhead
3. **Scalability**: Handle thousands of similar objects efficiently

### When to Use Flyweight
- Large numbers of similar objects
- Object creation is expensive
- Most object state can be made extrinsic
- Groups of objects can be replaced by few shared objects

### Flyweight vs Go
- **Java**: Explicit pattern with factory management
- **Go**: String interning, struct embedding, interfaces
- **Memory**: Both support efficient memory usage, different approaches

## Key Functional Interfaces

| Interface | Method | Purpose |
|-----------|--------|---------|
| `Predicate<T>` | `test(T) -> boolean` | Filtering/conditions |
| `Function<T,R>` | `apply(T) -> R` | Transformation |
| `Consumer<T>` | `accept(T) -> void` | Side effects |
| `Supplier<T>` | `get() -> T` | Value generation |

## Best Practices Summary

1. **Exceptions**: Use specific types, don't ignore, clean up resources
2. **Streams**: Prefer functional style for data processing
3. **Optional**: Use instead of null returns for safety
4. **Flyweight**: Apply when memory efficiency is critical
5. **Lambda**: Keep simple, prefer method references when possible