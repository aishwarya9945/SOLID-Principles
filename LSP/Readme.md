# Liskov Substitution Principle (LSP)

## Definition
The Liskov Substitution Principle states that **objects of a superclass should be replaceable with objects of its subclasses without breaking the application**.  
Subclasses must honor the contract of the base class — no unexpected exceptions, no altered semantics.

---

## In Interview
“LSP means that if class `B` is a subclass of class `A`, then anywhere you use `A`, you should be able to substitute `B` without breaking functionality. Subclasses must behave consistently with the base class contract.”

## Why It Matters
- Ensures inheritance is safe and predictable.
- Prevents runtime errors when substituting subclasses.
- Encourages designing subclasses that extend behavior without breaking existing logic.
- Demonstrates understanding of polymorphism and robust system design.

---

## When to Apply
- When designing class hierarchies with inheritance.
- When overriding methods in subclasses.
- When ensuring polymorphic behavior works seamlessly across base and derived classes.

---

## How to Implement
- Subclasses should not throw unexpected exceptions.
- Subclasses should not weaken preconditions or strengthen postconditions.
- Subclasses should extend functionality while respecting the base class contract.

---

## ❌ Before LSP (Violation Example)
```java
class Vehicle {
    public void start() {
        System.out.println("Starting a Vehicle");
    }
    public void stop() {
        System.out.println("Stopping a Vehicle");
    }
}

class Bike extends Vehicle {
    @Override
    public void start() {
        // Violates LSP: throws unexpected exception
        throw new RuntimeException("Boom you are gone..");
    }
}
```

**Problem:**
- `Bike` cannot safely substitute `Vehicle`.
- `testDrive(new Bike())` crashes instead of behaving like a `Vehicle`.
- Substitution fails → violates LSP.

---

## ✅ After LSP (Correct Example)
```java
class Vehicle1 {
    public void start() {
        System.out.println("Starting a Vehicle");
    }
    public void stop() {
        System.out.println("Stopping a Vehicle");
    }
}

class Bike1 extends Vehicle1 {
    @Override
    public void start() {
        System.out.println("Starting a Bike"); // consistent with Vehicle contract
    }
    @Override
    public void stop() {
        System.out.println("Stopping a Bike");
    }
}

class Car1 extends Vehicle1 {
    @Override
    public void start() {
        System.out.println("Starting a Car");
    }
    @Override
    public void stop() {
        System.out.println("Stopping a Car");
    }
}

public class LSPAfter {
    static void testDrive(Vehicle1 vehicle) {
        vehicle.start();
        vehicle.stop();
    }
    public static void main(String[] args) {
        testDrive(new Vehicle1()); // works
        testDrive(new Bike1());    // works
        testDrive(new Car1());     // works
    }
}
```

**Improvement:**
- `Bike1` and `Car1` override methods but keep expected behavior.
- `testDrive()` works with all subclasses.
- Substitution is safe → satisfies LSP.

---

## Common Mistakes
- Subclasses throwing exceptions not expected in the base class.
- Changing semantics (e.g., `start()` means something different in subclass).
- Breaking polymorphism by altering contracts.

---

## Patterns That Support LSP
- **Template Method Pattern** → ensures subclasses extend behavior safely.
- **Strategy Pattern** → avoids unsafe inheritance by favoring composition.
- **Interface Segregation** → keeps contracts small and easier to honor.

---

## Interview Q&A
**Q: What does LSP mean in practice?**  
A: Subclasses must be substitutable for their base class without breaking functionality.

**Q: Give an example of violating LSP.**  
A: If a subclass throws exceptions or changes semantics unexpectedly
(e.g., Bike.start() throws an error while Vehicle.start() doesn’t), substitution fails.

**Q: How do you ensure subclasses follow LSP?**  
A: By designing subclasses to respect the base class contract — no unexpected exceptions, no altered semantics.

**Q: Why is LSP important?**  
A: It ensures safe inheritance, predictable polymorphism, and robust system design without breaking existing functionality.

---

## Key Takeaway
- **Before LSP** → Subclass (`Bike`) breaks substitution by throwing exceptions.
- **After LSP** → Subclasses (`Bike1`, `Car1`) honor the base class contract, making substitution safe and predictable.
