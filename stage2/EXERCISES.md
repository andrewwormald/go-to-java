# Go to Java - Stage 2 Exercises

⚠️ **DEPRECATED**: This file has been replaced with a cleaner structure.

👉 **Go to**: [`exercises/`](exercises/) directory for the new organized approach.

Each exercise now has:
- Clear instructions in its own directory
- Starter code templates
- Automated tests
- Reference solutions

## Exercise 1: Interface Implementation

**Goal**: Practice creating and implementing interfaces

**Task**: Create a shape drawing system:
- Interface `Shape` with methods: `getArea()`, `getPerimeter()`, `draw()`
- Implement classes: `Circle`, `Rectangle`, `Triangle`
- Each shape should print its dimensions when `draw()` is called
- Create a `ShapeCalculator` class with static methods that work with any `Shape`

**File**: Create `stage2/ShapeExercise.java`

**Expected behavior**:
```java
Shape[] shapes = {new Circle(5), new Rectangle(4, 6), new Triangle(3, 4, 5)};
for (Shape shape : shapes) {
    shape.draw();
    System.out.println("Area: " + shape.getArea());
}
```

---

## Exercise 2: Abstract Classes

**Goal**: Practice abstract classes with mixed implementation

**Task**: Create an employee management system:
- Abstract class `Employee` with fields: `name`, `id`, `baseSalary`
- Abstract method: `calculateSalary()`
- Concrete method: `getEmployeeInfo()`
- Subclasses: `FullTimeEmployee` (fixed salary), `ContractEmployee` (hourly rate), `SalesEmployee` (commission-based)

**File**: Create `stage2/EmployeeExercise.java`

**Requirements**:
- FullTime: salary = baseSalary
- Contract: salary = hours * hourlyRate (add hours field)
- Sales: salary = baseSalary + (sales * commissionRate) (add sales and commission fields)

---

## Exercise 3: Multiple Interface Implementation

**Goal**: Practice implementing multiple interfaces

**Task**: Create a media player system:
```java
interface Playable {
    void play();
    void pause();
    void stop();
}

interface Downloadable {
    void download(String url);
    boolean isDownloaded();
}

interface Shareable {
    void share(String platform);
    String getShareUrl();
}
```

Create classes:
- `Song` implements `Playable` and `Shareable`
- `Podcast` implements `Playable` and `Downloadable`
- `Video` implements all three interfaces

**File**: Create `stage2/MediaPlayerExercise.java`

---

## Exercise 4: Go Interface vs Java Interface

**Goal**: Understand interface differences between Go and Java

**Task**: Translate this Go interface pattern to Java:

```go
// Go code to translate
type Writer interface {
    Write(data []byte) error
}

type FileWriter struct {
    filename string
}

func (f FileWriter) Write(data []byte) error {
    // Implementation here
    fmt.Printf("Writing %d bytes to %s\n", len(data), f.filename)
    return nil
}

type NetworkWriter struct {
    host string
    port int
}

func (n NetworkWriter) Write(data []byte) error {
    fmt.Printf("Sending %d bytes to %s:%d\n", len(data), n.host, n.port)
    return nil
}
```

**File**: Create `stage2/WriterExercise.java`

**Requirements**:
- Create Java `Writer` interface (note: avoid conflict with java.io.Writer)
- Implement `FileWriter` and `NetworkWriter` classes
- Handle exceptions properly (no Go-style error returns)
- Create a method that accepts any `Writer` and demonstrates polymorphism

---

## Exercise 5: Default Methods and Interface Evolution

**Goal**: Practice default methods in interfaces

**Task**: Create a notification system that evolves over time:
- Start with `Notifier` interface: `void sendNotification(String message)`
- Add default method: `default void sendUrgentNotification(String message)`
- Add static method: `static String formatMessage(String message, String priority)`
- Implement classes: `EmailNotifier`, `SMSNotifier`, `PushNotifier`
- Some classes override the default method, others use it as-is

**File**: Create `stage2/NotificationExercise.java`

---

## Exercise 6: Abstract Class vs Interface Decision

**Goal**: Learn when to use abstract classes vs interfaces

**Task**: Design a game entity system and justify your choices:

Create both approaches for comparison:

**Approach A**: Use abstract class `GameEntity`
**Approach B**: Use interface `GameEntity`

Both should support:
- Position (x, y coordinates)
- Health management
- Movement
- Damage dealing

Implement: `Player`, `Enemy`, `NPC` for both approaches

**File**: Create `stage2/GameEntityExercise.java`

**Question**: In your main method, write comments explaining when you'd choose abstract class vs interface

---

## Exercise 7: Polymorphism Challenge

**Goal**: Master polymorphic behavior

**Task**: Create a restaurant ordering system:
- Interface `OrderItem` with `getPrice()` and `getDescription()`
- Classes: `Food`, `Drink`, `Dessert` (each with different pricing logic)
- Class `Order` that contains a list of `OrderItem`
- Methods in `Order`: `addItem()`, `getTotalPrice()`, `printReceipt()`
- Implement a discount system using polymorphism

**File**: Create `stage2/RestaurantExercise.java`

**Bonus**: Add different discount strategies using interfaces

---

## Solutions Check

Run your exercises:
```bash
cd stage2
javac YourExercise.java
java YourExercise
```

### Solution Hints

**Exercise 1**:
- Use `Math.PI` for circle calculations
- `Math.sqrt()` for triangle area (Heron's formula)

**Exercise 2**:
- Abstract classes can have constructors
- Use `super()` to call parent constructor

**Exercise 3**:
- A class can implement multiple interfaces with commas
- Each interface method must be implemented

**Exercise 4**:
- Java doesn't return errors, use exceptions
- Create custom `WriteException` if needed
- Use `byte[]` instead of Go's `[]byte`

**Exercise 5**:
- Default methods provide implementation in interface
- Static methods called with `InterfaceName.methodName()`

**Exercise 6**:
- Abstract class: when you have shared implementation
- Interface: when you only define contract

**Exercise 7**:
- Use `instanceof` to check type when needed
- Consider Strategy pattern for discounts

## Real-World Connection

These exercises mirror real development scenarios:
- **Shape system** → Graphics libraries
- **Employee system** → HR/Payroll applications
- **Media player** → Streaming applications
- **Writer pattern** → Logging/IO systems
- **Notification system** → Alert frameworks
- **Game entities** → Game development
- **Restaurant system** → E-commerce platforms

Practice these patterns as they're fundamental to Java development!