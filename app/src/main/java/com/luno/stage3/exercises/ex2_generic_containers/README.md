# Exercise 2: Generic Containers

## Goal
Create your own generic classes to understand how Java generics work.

## What You'll Build
Generic `Pair<T, U>` and `Container<T>` classes with type safety.

## Requirements
Complete the starter code to create:
1. `Pair<T, U>` - holds two values of different types
2. `Container<T>` - holds a list of items with utility methods

## Expected Output
```
=== Generic Containers Test ===
Pair: (Alice, 25)
Swapped: (25, Alice)
Container size: 3
First item: Apple
Filtered container: [Apple]

✅ All tests passed!
```

## Key Concepts
- Generic class definition with `<T, U>`
- Type safety and compilation errors
- Generic methods
- Wildcards (`? extends`, `? super`)

## Time Estimate
30-45 minutes

## Run Your Solution
```bash
javac *.java && java ExampleTest
```