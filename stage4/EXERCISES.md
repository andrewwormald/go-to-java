# Go to Java - Stage 4 Exercises

⚠️ **DEPRECATED**: This file has been replaced with a cleaner structure.

👉 **Go to**: [`exercises/`](exercises/) directory for the new organized approach.

Each exercise now has:
- Clear instructions in its own directory
- Starter code templates
- Automated tests
- Reference solutions

## Exercise 1: Observer Pattern Implementation

**Goal**: Master the Observer pattern and event-driven design

**Task**: Build a stock market monitoring system:
- `Stock` class with price changes
- `Observer` interface for price change notifications
- Implement: `InvestorObserver`, `NewsAgencyObserver`, `TradingBotObserver`
- Each observer reacts differently to price changes

**File**: Create `stage4/StockMarketExercise.java`

**Requirements**:
- Stocks notify all observers when price changes
- Observers can subscribe/unsubscribe dynamically
- Include different threshold-based notifications
- Demonstrate adding/removing observers at runtime

**Expected behavior**:
```java
Stock appleStock = new Stock("AAPL", 150.00);
InvestorObserver investor = new InvestorObserver("Warren");
appleStock.addObserver(investor);
appleStock.setPrice(155.00); // Triggers notification
```

---

## Exercise 2: Strategy Pattern Practice

**Goal**: Implement interchangeable algorithms

**Task**: Create a payment processing system:
- `PaymentStrategy` interface
- Implementations: `CreditCardPayment`, `PayPalPayment`, `CryptoPayment`, `BankTransferPayment`
- `ShoppingCart` class that uses different payment strategies
- Each payment method has different processing logic and fees

**File**: Create `stage4/PaymentSystemExercise.java`

**Requirements**:
- Switch payment methods at runtime
- Calculate fees differently for each method
- Validate payment information per strategy
- Handle payment failures appropriately

---

## Exercise 3: Factory Pattern Variations

**Goal**: Master object creation patterns

**Task**: Build a document processing system:

**Simple Factory**:
```java
class DocumentFactory {
    public static Document createDocument(String type);
}
```

**Factory Method**:
```java
abstract class DocumentProcessor {
    public abstract Document createDocument();
    public void processDocument() { /* template method */ }
}
```

**Abstract Factory**:
```java
interface DocumentFactoryFamily {
    Document createDocument();
    Template createTemplate();
    Exporter createExporter();
}
```

**File**: Create `stage4/DocumentFactoryExercise.java`

**Requirements**:
- Support: PDF, Word, Excel, PowerPoint documents
- Each factory family (Office, Google, OpenSource) creates compatible objects
- Demonstrate all three factory patterns

---

## Exercise 4: Go Channels vs Java Observer

**Goal**: Compare Go and Java event handling approaches

**Task**: Translate this Go channel pattern to Java Observer pattern:

```go
// Go code to translate
type Event struct {
    Type string
    Data interface{}
}

func eventProcessor() {
    events := make(chan Event, 100)

    // Multiple consumers
    go func() {
        for event := range events {
            if event.Type == "USER_LOGIN" {
                // Handle login
            }
        }
    }()

    go func() {
        for event := range events {
            if event.Type == "ORDER_PLACED" {
                // Handle order
            }
        }
    }()

    // Producer
    events <- Event{Type: "USER_LOGIN", Data: "user123"}
    events <- Event{Type: "ORDER_PLACED", Data: orderData}
}
```

**File**: Create `stage4/ChannelVsObserverExercise.java`

**Requirements**:
- Implement Java Observer version
- Compare synchronous vs asynchronous processing
- Show filtering by event type
- Discuss trade-offs between approaches

---

## Exercise 5: Composition over Inheritance

**Goal**: Practice composition principles

**Task**: Refactor an inheritance hierarchy to use composition:

**Bad Inheritance Example**:
```java
class Vehicle { /* ... */ }
class Car extends Vehicle { /* ... */ }
class ElectricCar extends Car { /* ... */ }
class HybridCar extends Car { /* ... */ }
class Truck extends Vehicle { /* ... */ }
class ElectricTruck extends Truck { /* ... */ }
// This becomes unwieldy quickly!
```

**Your Task**: Redesign using composition with:
- `Engine` interface (`GasEngine`, `ElectricEngine`, `HybridEngine`)
- `DriveSystem` interface (`FrontWheel`, `RearWheel`, `AllWheel`)
- `Vehicle` class that composes these behaviors

**File**: Create `stage4/CompositionExercise.java`

**Requirements**:
- Flexible combination of features
- Easy to add new engine types or drive systems
- Demonstrate runtime behavior changes
- Compare with original inheritance approach

---

## Exercise 6: Event-Driven Architecture

**Goal**: Build a complete event system

**Task**: Create a e-commerce order processing system:

```java
// Event types to handle
class OrderPlacedEvent { /* ... */ }
class PaymentProcessedEvent { /* ... */ }
class InventoryCheckedEvent { /* ... */ }
class ShippingInitiatedEvent { /* ... */ }
```

**File**: Create `stage4/ECommerceEventExercise.java`

**Requirements**:
- Central `EventBus` for all events
- Multiple handlers for each event type
- Event handlers can trigger new events
- Implement: `InventoryService`, `PaymentService`, `ShippingService`, `NotificationService`
- Show complete order flow through events

---

## Exercise 7: Encapsulation Best Practices

**Goal**: Master data protection and immutability

**Task**: Create a banking system with proper encapsulation:

**Requirements**:
- Immutable `Money` class (amount, currency)
- `BankAccount` with defensive copying
- `Transaction` history that can't be modified externally
- `Bank` class managing multiple accounts securely
- Proper validation and business rules

**File**: Create `stage4/SecureBankingExercise.java`

**Security Requirements**:
- No direct access to sensitive data
- Immutable transaction records
- Defensive copying for collections
- Proper input validation
- Thread-safe operations (bonus)

---

## Exercise 8: Design Pattern Combination

**Goal**: Use multiple patterns together

**Task**: Build a game engine architecture:

**Combine These Patterns**:
- **Observer**: Game events (player actions, collisions)
- **Strategy**: AI behavior, input handling
- **Factory**: Creating game entities
- **Composite**: Scene graph with nested objects
- **Command**: Undo/redo system

**File**: Create `stage4/GameEngineExercise.java`

**Requirements**:
- Player actions trigger events
- Different AI strategies for enemies
- Factory creates different entity types
- Scene contains nested game objects
- Commands can be undone/redone

---

## Exercise 9: Anti-Patterns Recognition

**Goal**: Identify and fix common mistakes

**Task**: You're given this problematic code to refactor:

```java
// Fix these anti-patterns
public class GameManager {
    public static GameManager instance; // Singleton abuse
    public List<Player> players = new ArrayList<>(); // Public fields
    public Map data = new HashMap(); // Raw types

    public void doEverything(String action, Object... params) {
        // God method - does too much
        if (action.equals("MOVE")) {
            // 50 lines of movement logic
        } else if (action.equals("ATTACK")) {
            // 40 lines of attack logic
        } // ... many more actions
    }

    public Object getStuff(String key) {
        return data.get(key); // Primitive obsession
    }
}
```

**File**: Create `stage4/RefactoringExercise.java`

**Fix These Issues**:
- Singleton abuse → Dependency injection
- Public fields → Proper encapsulation
- Raw types → Generic safety
- God class → Single responsibility
- Primitive obsession → Value objects

---

## Advanced Challenge: Plugin Architecture

**Goal**: Design an extensible system

**Task**: Create a text editor with plugin support:

**Core System**:
```java
interface Plugin {
    String getName();
    void initialize(EditorContext context);
    void execute();
}

class PluginManager {
    // Load plugins dynamically
    // Manage plugin lifecycle
}
```

**File**: Create `stage4/PluginArchitectureExercise.java`

**Requirements**:
- Plugin discovery and loading
- Plugin communication through events
- Core editor functionality
- Sample plugins: Spell Checker, Word Counter, Syntax Highlighter
- Plugin dependencies and ordering

---

## Testing Your Solutions

```bash
cd stage4
javac YourExercise.java
java YourExercise
```

### Solution Hints

**Exercise 1**: Use `List<Observer>` and iterate to notify all

**Exercise 2**: Strategy pattern eliminates conditional logic

**Exercise 3**: Abstract factory ensures object compatibility

**Exercise 4**: Java Observer is push-based, Go channels are pull-based

**Exercise 5**: Composition allows multiple inheritance of behavior

**Exercise 6**: Event bus decouples producers from consumers

**Exercise 7**: Use `Collections.unmodifiableList()` for defensive copying

**Exercise 8**: Patterns work together, not in isolation

**Exercise 9**: Each anti-pattern has specific refactoring techniques

**Challenge**: Use reflection for plugin discovery: `Class.forName()`

## Real-World Applications

These patterns are everywhere:
- **Observer** → GUI frameworks, model-view architectures
- **Strategy** → Payment systems, sorting algorithms
- **Factory** → Database drivers, UI widgets
- **Composition** → Game engines, microservices
- **Events** → Web applications, message queues
- **Encapsulation** → Banking, security systems
- **Pattern combinations** → Enterprise applications
- **Anti-patterns** → Legacy system modernization
- **Plugin architecture** → IDEs, web browsers

Master these for professional Java development!