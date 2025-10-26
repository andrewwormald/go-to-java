# Go to Java - Stage 3 Exercises

⚠️ **DEPRECATED**: This file has been replaced with a cleaner structure.

👉 **Go to**: [`exercises/`](exercises/) directory for the new organized approach.

Each exercise now has:
- Clear instructions in its own directory
- Starter code templates
- Automated tests
- Reference solutions

## Exercise 1: Collection Mastery

**Goal**: Practice different collection types and their use cases

**Task**: Create a library management system:
- Use `ArrayList<Book>` for books collection
- Use `HashMap<String, Book>` for ISBN lookup
- Use `HashSet<String>` for unique authors
- Use `TreeMap<String, Integer>` for sorted genre counts

```java
class Book {
    String title, author, isbn, genre;
    int year;
    // Constructor and getters
}
```

**File**: Create `stage3/LibraryExercise.java`

**Requirements**:
- Add books to collections
- Search by ISBN, author, genre
- Display statistics (books per genre, unique authors)
- Remove books and update all collections

---

## Exercise 2: Generic Class Creation

**Goal**: Create your own generic classes

**Task**: Build a generic `Pair<T, U>` and `Triple<T, U, V>` class:

```java
// Your generic classes should support:
Pair<String, Integer> nameAge = new Pair<>("Alice", 25);
Triple<String, Double, Boolean> productInfo = new Triple<>("Laptop", 999.99, true);
```

**File**: Create `stage3/GenericContainerExercise.java`

**Requirements**:
- Generic constructors
- Getter/setter methods
- `equals()` and `hashCode()` methods
- `toString()` method
- Create a `PairList<T, U>` that stores multiple pairs

---

## Exercise 3: Go Slices vs Java Collections

**Goal**: Understand collection differences

**Task**: Translate this Go code to Java using appropriate collections:

```go
// Go code to translate
func processScores(scores []int) map[string]int {
    result := make(map[string]int)

    // Calculate statistics
    sum := 0
    for _, score := range scores {
        sum += score
    }

    // Sort scores
    sort.Ints(scores)

    result["min"] = scores[0]
    result["max"] = scores[len(scores)-1]
    result["avg"] = sum / len(scores)
    result["median"] = scores[len(scores)/2]

    return result
}
```

**File**: Create `stage3/ScoreProcessorExercise.java`

**Requirements**:
- Use appropriate Java collections
- Handle the sorting without modifying original data
- Use generics properly
- Add error handling for empty collections

---

## Exercise 4: Wildcards and Bounded Types

**Goal**: Master advanced generics concepts

**Task**: Create a data analysis framework:

```java
// Create methods with these signatures:
public static double sumNumbers(List<? extends Number> numbers)
public static void addIntegers(List<? super Integer> numbers)
public static <T extends Comparable<T>> T findMax(List<T> items)
public static <T> List<T> merge(List<? extends T> list1, List<? extends T> list2)
```

**File**: Create `stage3/DataAnalysisExercise.java`

**Requirements**:
- Implement all methods correctly
- Test with different numeric types
- Demonstrate why wildcards are necessary
- Create examples showing compilation errors without proper bounds

---

## Exercise 5: Collection Performance

**Goal**: Understand when to use which collection

**Task**: Benchmark different collections for various operations:

**File**: Create `stage3/CollectionBenchmarkExercise.java`

**Compare**:
- `ArrayList` vs `LinkedList` for insertions/deletions
- `HashMap` vs `TreeMap` for lookups
- `HashSet` vs `TreeSet` for uniqueness checking
- Array vs `ArrayList` for random access

**Requirements**:
- Time operations with `System.currentTimeMillis()`
- Test with different data sizes (1K, 10K, 100K elements)
- Print results in a formatted table
- Explain when to use each collection type

---

## Exercise 6: Custom Iterator

**Goal**: Implement the Iterator pattern

**Task**: Create a custom collection with iterator:

```java
class NumberSequence implements Iterable<Integer> {
    // Generates numbers: start, start+step, start+2*step, ...
}
```

**File**: Create `stage3/IteratorExercise.java`

**Requirements**:
- Implement `Iterable<Integer>`
- Create custom `Iterator<Integer>` inner class
- Support both ascending and descending sequences
- Make it work with enhanced for loops
- Add methods: `skip(int n)`, `limit(int n)`

---

## Exercise 7: Stream-like Operations (Without Streams)

**Goal**: Practice collection manipulation manually

**Task**: Implement functional-style operations using traditional collections:

```java
class CollectionUtils {
    public static <T> List<T> filter(List<T> list, Predicate<T> predicate)
    public static <T, R> List<R> map(List<T> list, Function<T, R> mapper)
    public static <T> T reduce(List<T> list, T identity, BinaryOperator<T> accumulator)
}
```

**File**: Create `stage3/FunctionalCollectionExercise.java`

**Note**: Create simple functional interfaces or use method references

**Requirements**:
- Implement without using Stream API
- Test with different data types
- Chain operations together
- Compare performance with Stream API (if you know it)

---

## Exercise 8: Type Safety Challenge

**Goal**: Fix type safety issues

**Task**: You're given this "legacy" code to fix:

```java
// Fix this code to be type-safe
@SuppressWarnings("unchecked")
public class LegacyDataProcessor {
    private List data = new ArrayList();
    private Map lookupTable = new HashMap();

    public void addData(Object item) {
        data.add(item);
        lookupTable.put(item.toString(), item);
    }

    public Object findData(String key) {
        return lookupTable.get(key);
    }

    public List getAllData() {
        return data;
    }
}
```

**File**: Create `stage3/TypeSafetyExercise.java`

**Requirements**:
- Make it completely type-safe with generics
- Eliminate all warnings
- Add proper bounds where appropriate
- Create test cases showing type safety benefits

---

## Advanced Challenge: Generic Cache

**Goal**: Combine all Stage 3 concepts

**Task**: Build a generic LRU (Least Recently Used) cache:

```java
class LRUCache<K, V> {
    private final int capacity;
    // Your implementation

    public V get(K key) { /* ... */ }
    public void put(K key, V value) { /* ... */ }
    public void clear() { /* ... */ }
    public int size() { /* ... */ }
}
```

**File**: Create `stage3/LRUCacheExercise.java`

**Requirements**:
- Generic key-value storage
- Fixed capacity with LRU eviction
- Efficient operations (use HashMap + LinkedList or LinkedHashMap)
- Thread-safe version (bonus)
- Statistics: hit rate, miss rate, evictions

---

## Testing Your Solutions

```bash
cd stage3
javac YourExercise.java
java YourExercise
```

### Solution Hints

**Exercise 1**: Use `keySet()`, `values()`, `entrySet()` for HashMap iteration

**Exercise 2**: Use `Objects.equals()` and `Objects.hash()` for null-safe implementations

**Exercise 3**:
- Use `Collections.sort()` on a copy: `new ArrayList<>(original)`
- Consider `Integer` vs `int` for collections

**Exercise 4**:
- `? extends` for reading (covariance)
- `? super` for writing (contravariance)
- Bounded types restrict the generic parameter

**Exercise 5**: Use `System.nanoTime()` for more precise timing

**Exercise 6**: Iterator must track state and implement `hasNext()` and `next()`

**Exercise 7**: Create interfaces like `interface Predicate<T> { boolean test(T t); }`

**Exercise 8**: Make the class generic: `LegacyDataProcessor<T>`

## Real-World Applications

These exercises prepare you for:
- **Library system** → Database applications
- **Generic containers** → Utility libraries
- **Score processor** → Data analysis tools
- **Wildcards** → API design
- **Performance** → System optimization
- **Iterators** → Custom data structures
- **Functional ops** → Data processing pipelines
- **Type safety** → Legacy code modernization
- **LRU Cache** → High-performance systems

Master these patterns for effective Java development!