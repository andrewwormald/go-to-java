# Go to Java - Stage 1 Exercises

⚠️ **DEPRECATED**: This file has been replaced with a cleaner structure.

👉 **Go to**: [`exercises/`](exercises/) directory for the new organized approach.

Each exercise now has:
- Clear instructions in its own directory
- Starter code templates
- Automated tests
- Reference solutions

## Exercise 1: Create Your Own Class

**Goal**: Practice basic class creation and encapsulation

**Task**: Create a `BankAccount` class with the following requirements:
- Private fields: `accountNumber` (String), `balance` (double), `accountHolder` (String)
- Constructor that takes all three parameters
- Getter methods for all fields
- Methods: `deposit(double amount)`, `withdraw(double amount)`, `getBalance()`
- Add validation: deposits must be positive, withdrawals can't exceed balance

**File**: Create `stage1/BankAccountExercise.java`

**Expected Output**:
```
Account: 12345, Holder: John Doe, Balance: $1000.00
After deposit: Balance: $1250.00
After withdrawal: Balance: $1000.00
Withdrawal failed: Insufficient funds
```

---

## Exercise 2: Inheritance Practice

**Goal**: Practice inheritance and method overriding

**Task**: Create a vehicle hierarchy:
- Base class `Vehicle` with fields: `brand`, `year`, `price`
- Method `getInfo()` that returns basic vehicle information
- Subclass `Car` that adds `numberOfDoors` field
- Subclass `Motorcycle` that adds `engineSize` field
- Override `getInfo()` in both subclasses to include specific information

**File**: Create `stage1/VehicleExercise.java`

**Expected Output**:
```
Vehicle: Toyota 2020 - $25000.00
Car: Honda 2022, 4 doors - $28000.00
Motorcycle: Yamaha 2021, 650cc engine - $12000.00
```

---

## Exercise 3: Go vs Java Comparison

**Goal**: Understand the differences between Go structs and Java classes

**Task**: Translate this Go code to Java:

```go
// Go code to translate
type Player struct {
    Name  string
    Level int
    Score float64
}

func (p *Player) LevelUp() {
    p.Level++
    p.Score += 100.0
}

func (p Player) GetRank() string {
    if p.Score >= 1000 {
        return "Expert"
    } else if p.Score >= 500 {
        return "Intermediate"
    }
    return "Beginner"
}
```

**File**: Create `stage1/PlayerExercise.java`

**Requirements**:
- Convert the struct to a Java class with proper encapsulation
- Add a constructor
- Add getter methods
- Implement the methods with proper Java syntax
- Create a main method to test your implementation

---

## Exercise 4: Constructor Overloading

**Goal**: Practice multiple constructors (Java-specific concept)

**Task**: Create a `Rectangle` class with:
- Fields: `width`, `height`, `color`
- Default constructor (creates 1x1 white rectangle)
- Constructor with width and height (default color: white)
- Constructor with all parameters
- Methods: `getArea()`, `getPerimeter()`, `toString()`

**File**: Create `stage1/RectangleExercise.java`

**Test all constructors**:
```java
Rectangle r1 = new Rectangle();
Rectangle r2 = new Rectangle(5, 3);
Rectangle r3 = new Rectangle(4, 2, "blue");
```

---

## Exercise 5: Method Overloading vs Overriding

**Goal**: Understand the difference between overloading and overriding

**Task**: Create a `Calculator` class with:
- Overloaded `add` methods: `add(int, int)`, `add(double, double)`, `add(int, int, int)`
- Create a `ScientificCalculator` subclass that:
  - Overrides one of the `add` methods to include logging
  - Adds new methods: `power(double base, double exponent)`, `sqrt(double number)`

**File**: Create `stage1/CalculatorExercise.java`

---

## Exercise Solutions

Check your solutions by running:
```bash
cd stage1
javac YourExercise.java
java YourExercise
```

### Solution Hints

**Exercise 1 Hints**:
- Use `private` fields and `public` methods
- Add `if` statements for validation
- Use `System.out.printf("%.2f", balance)` for currency formatting

**Exercise 2 Hints**:
- Use `extends` keyword for inheritance
- Call `super()` in subclass constructors
- Use `@Override` annotation for overridden methods

**Exercise 3 Hints**:
- Go receivers become `this` in Java
- Go's automatic getters become explicit getter methods
- Method names follow camelCase convention

**Exercise 4 Hints**:
- Use `this()` to call other constructors
- Default values can be set in constructor calls

**Exercise 5 Hints**:
- Same method name, different parameters = overloading
- Same method signature in parent/child = overriding
- Use `super.methodName()` to call parent method

## Bonus Challenge

Create a complete mini-application that combines all concepts:
- A `Game` class that manages multiple `Player` objects
- Players can level up, gain items, and compete
- Use inheritance for different player types (Warrior, Mage, Archer)
- Demonstrate polymorphism by treating all players uniformly

**File**: Create `stage1/GameChallenge.java`