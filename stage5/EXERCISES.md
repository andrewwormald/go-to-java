# Go to Java - Stage 5 Exercises

⚠️ **DEPRECATED**: This file has been replaced with a cleaner structure.

👉 **Go to**: [`exercises/`](exercises/) directory for the new organized approach.

Each exercise now has:
- Clear instructions in its own directory
- Starter code templates
- Automated tests
- Reference solutions

## Exercise 1: Exception Handling Mastery

**Goal**: Practice comprehensive exception handling

**Task**: Build a file processing system with robust error handling:

```java
class FileProcessor {
    public ProcessingResult processFiles(List<String> filePaths) {
        // Handle: FileNotFoundException, SecurityException, IOException
        // Use try-with-resources for file handling
        // Implement retry logic for transient failures
    }
}
```

**File**: Create `stage5/FileProcessorExercise.java`

**Requirements**:
- Custom exceptions: `ProcessingException`, `InvalidFormatException`
- Try-with-resources for file operations
- Exception chaining for root cause analysis
- Partial success handling (some files succeed, others fail)
- Logging exceptions appropriately

**Compare to Go**:
```go
// Go version for comparison
func processFiles(paths []string) ([]Result, error) {
    var results []Result
    for _, path := range paths {
        content, err := os.ReadFile(path)
        if err != nil {
            return results, fmt.Errorf("failed to read %s: %w", path, err)
        }
        // process content...
    }
    return results, nil
}
```

---

## Exercise 2: Lambda and Functional Interfaces

**Goal**: Master functional programming in Java

**Task**: Create a data processing pipeline using lambdas:

```java
// Implement these functional interfaces
interface Validator<T> { boolean isValid(T item); }
interface Transformer<T, R> { R transform(T item); }
interface Aggregator<T, R> { R aggregate(List<T> items); }

class DataPipeline<T> {
    public <R> R process(List<T> data,
                         Validator<T> validator,
                         Transformer<T, R> transformer,
                         Aggregator<R, R> aggregator) {
        // Your implementation
    }
}
```

**File**: Create `stage5/LambdaPipelineExercise.java`

**Requirements**:
- Chain multiple operations using lambdas
- Method references where appropriate
- Custom functional interfaces
- Error handling in lambda expressions
- Performance comparison: lambdas vs traditional loops

---

## Exercise 3: Streams API Mastery

**Goal**: Build complex data processing with streams

**Task**: Analyze a large dataset of sales records:

```java
class SalesRecord {
    String region, product, salesperson;
    LocalDate date;
    double amount;
    // Constructor, getters
}
```

**File**: Create `stage5/SalesAnalysisExercise.java`

**Implement These Analytics**:
- Top 10 products by revenue
- Sales by region and quarter
- Best performing salespeople
- Month-over-month growth rates
- Customer segmentation analysis

**Requirements**:
- Use parallel streams for large datasets
- Custom collectors for complex aggregations
- Stream operations: filter, map, reduce, collect, groupingBy
- Performance benchmarking
- Memory usage optimization

---

## Exercise 4: Optional Deep Dive

**Goal**: Master null-safe programming

**Task**: Refactor null-prone code to use Optional:

**Before (problematic code)**:
```java
public String getUserDisplayName(String userId) {
    User user = userRepository.findById(userId);
    if (user != null) {
        Profile profile = user.getProfile();
        if (profile != null) {
            String displayName = profile.getDisplayName();
            if (displayName != null && !displayName.isEmpty()) {
                return displayName;
            }
        }
        return user.getUsername();
    }
    return "Anonymous";
}
```

**File**: Create `stage5/OptionalRefactoringExercise.java`

**Requirements**:
- Eliminate all null checks using Optional
- Chain operations fluently
- Use `flatMap`, `filter`, `orElse`, `orElseGet`
- Compare performance: Optional vs null checks
- Handle nested Optional scenarios

---

## Exercise 5: Go Error Handling vs Java Exceptions

**Goal**: Compare error handling philosophies

**Task**: Implement the same service in both styles:

**Go-style (simulate in Java)**:
```java
class Result<T> {
    T value;
    Exception error;
    // Simulate Go's (value, error) pattern
}

class GoStyleService {
    Result<User> createUser(UserRequest request) {
        // Return Result with either value or error
    }
}
```

**Java-style**:
```java
class JavaStyleService {
    User createUser(UserRequest request) throws ValidationException, PersistenceException {
        // Traditional exception throwing
    }
}
```

**File**: Create `stage5/ErrorHandlingComparisonExercise.java`

**Requirements**:
- Implement both approaches for the same operations
- Compare error propagation patterns
- Measure performance differences
- Discuss maintainability trade-offs
- Show when each approach is better

---

## Exercise 6: Flyweight Pattern Implementation

**Goal**: Master memory optimization patterns

**Task**: Create a massive multiplayer game world:

```java
// Intrinsic state (shared)
class TerrainType {
    String texture, color;
    int movementCost;
    // Shared among many tiles
}

// Extrinsic state (unique per tile)
class WorldTile {
    int x, y, elevation;
    TerrainType type; // Flyweight reference
}

class TerrainFactory {
    // Flyweight factory
}
```

**File**: Create `stage5/GameWorldExercise.java`

**Requirements**:
- Support 1 million+ tiles efficiently
- Flyweight factory with caching
- Memory usage measurements
- Comparison with naive approach
- Thread-safe flyweight access
- Performance benchmarks

---

## Exercise 7: Advanced Flyweight with Weak References

**Goal**: Implement memory-aware flyweights

**Task**: Create a flyweight system that automatically releases unused flyweights:

```java
class SmartFlyweightFactory<K, V> {
    private Map<K, WeakReference<V>> cache = new ConcurrentHashMap<>();

    public V getFlyweight(K key, Supplier<V> factory) {
        // Implement with WeakReference cleanup
    }
}
```

**File**: Create `stage5/SmartFlyweightExercise.java`

**Requirements**:
- Use `WeakReference` for automatic cleanup
- Monitor memory usage and GC behavior
- Implement reference queue for cleanup notifications
- Thread-safe implementation
- Performance vs memory trade-offs

---

## Exercise 8: Functional Programming vs OOP Design

**Goal**: Compare paradigms for the same problem

**Task**: Implement a text processing system using both approaches:

**OOP Approach**:
```java
interface TextProcessor {
    String process(String text);
}

class UpperCaseProcessor implements TextProcessor { /* ... */ }
class TrimProcessor implements TextProcessor { /* ... */ }
class ValidatorProcessor implements TextProcessor { /* ... */ }
```

**Functional Approach**:
```java
Function<String, String> upperCase = String::toUpperCase;
Function<String, String> trim = String::trim;
Predicate<String> isValid = s -> s.length() > 0;

// Chain operations functionally
```

**File**: Create `stage5/ParadigmComparisonExercise.java`

**Requirements**:
- Same functionality in both paradigms
- Performance benchmarks
- Code readability comparison
- Extensibility analysis
- Memory usage patterns

---

## Exercise 9: Stream Processing Pipeline

**Goal**: Build a real-time data processing system

**Task**: Process a stream of log entries:

```java
class LogEntry {
    Instant timestamp;
    Level level;
    String service, message;
    Map<String, String> metadata;
}

class LogProcessor {
    // Process logs in real-time
    // Aggregate statistics
    // Detect anomalies
    // Generate alerts
}
```

**File**: Create `stage5/LogProcessingExercise.java`

**Requirements**:
- Stream processing with windowing
- Real-time aggregations
- Pattern detection using streams
- Backpressure handling
- Custom collectors for metrics
- Integration with Java 8+ time API

---

## Advanced Challenge: Reactive Streams

**Goal**: Implement a simple reactive system

**Task**: Create a reactive stock price system:

```java
interface Publisher<T> {
    void subscribe(Subscriber<T> subscriber);
}

interface Subscriber<T> {
    void onNext(T item);
    void onError(Throwable error);
    void onComplete();
}

class StockPriceStream implements Publisher<StockPrice> {
    // Implement reactive stock price updates
}
```

**File**: Create `stage5/ReactiveStreamsExercise.java`

**Requirements**:
- Asynchronous data flow
- Backpressure handling
- Error propagation
- Multiple subscribers
- Rate limiting
- Integration with CompletableFuture

---

## Testing Your Solutions

```bash
cd stage5
javac YourExercise.java
java YourExercise
```

### Solution Hints

**Exercise 1**: Use `finally` blocks or try-with-resources for cleanup

**Exercise 2**: Method references: `String::toUpperCase` instead of `s -> s.toUpperCase()`

**Exercise 3**: `Collectors.groupingBy()` and `Collectors.mapping()` for complex aggregations

**Exercise 4**: `Optional.ofNullable().map().filter().orElse()`

**Exercise 5**: Go-style forces explicit error handling, Java-style allows propagation

**Exercise 6**: Use `ConcurrentHashMap` for thread-safe factory caching

**Exercise 7**: `WeakReference.get()` returns null when object is collected

**Exercise 8**: Functional style uses composition, OOP uses polymorphism

**Exercise 9**: Use `Stream.iterate()` or custom spliterator for infinite streams

**Challenge**: Use `CompletableFuture.supplyAsync()` for asynchronous processing

## Performance Considerations

**Memory Usage**:
- Flyweight: Shared intrinsic state
- Streams: Lazy evaluation vs eager collection creation
- Optional: Small overhead for null safety

**Processing Speed**:
- Lambdas: JVM optimization improves over time
- Parallel streams: Overhead for small datasets
- Exception handling: Stack trace creation cost

**Scalability**:
- Reactive streams: Handle backpressure
- Flyweight: Linear memory growth vs exponential
- Functional style: Easier parallelization

## Real-World Applications

These advanced features power:
- **Exception handling** → Enterprise systems, web services
- **Functional programming** → Data analysis, ETL pipelines
- **Streams** → Big data processing, analytics
- **Optional** → APIs, null-safe libraries
- **Flyweight** → Game engines, caching systems
- **Reactive streams** → Real-time systems, microservices

Master these for production Java development!