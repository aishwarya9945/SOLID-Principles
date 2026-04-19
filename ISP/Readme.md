# Interface Segregation Principle (ISP)

## Definition
The Interface Segregation Principle states that **a client should not be forced to implement an interface it does not use**.  
Large, “fat” interfaces should be split into smaller, more specific ones so classes only depend on what they actually need.

---

## In Interview
“ISP means that instead of designing one big interface with many methods, you should design smaller, role‑specific interfaces. This prevents clients from being burdened with irrelevant methods. For example, a vegetarian restaurant shouldn’t be forced to implement non‑veg serving methods.”

---

## Why It Matters
- Prevents classes from having meaningless or empty methods.
- Makes interfaces more cohesive and easier to understand.
- Reduces coupling and improves flexibility.
- Aligns with clean architecture and interview expectations.

---

## When to Apply
- When different clients need different subsets of functionality.
- When interfaces start to grow too large and unrelated.
- During refactoring: split “fat” interfaces into smaller, focused contracts.

---

## How to Apply
- Break large interfaces into smaller, role‑specific ones.
- Ensure each client implements only what it actually needs.
- Favor composition over inheritance when combining multiple roles.

---

## ❌ Before ISP (Violation Example)
```java
interface Restaurant {
    void vegMeals();
    void nonVegMeals();
}

class ABCVegRestaurant implements Restaurant {
    @Override
    public void vegMeals() {
        System.out.println("Serving vegetarian meals");
    }

    @Override
    public void nonVegMeals() {
        System.out.println("Sorry, I don't serve non-veg meals");
    }
}

public class ISPBefore {
    public static void main(String[] args) {
        Restaurant vegOnly = new ABCVegRestaurant();
        vegOnly.vegMeals();
        vegOnly.nonVegMeals(); // forced to implement, but meaningless
    }
}
```

**Problem:**
- `ABCVegRestaurant` is forced to implement `nonVegMeals()` even though it doesn’t serve non‑veg food.
- Violates ISP because the client depends on methods it doesn’t use.

---

## ✅ After ISP (Correct Example)
```java
interface VegRestaurant {
    void vegMeals();
}

interface NonVegRestaurant {
    void nonVegMeals();
}

class ABCVegRestaurant implements VegRestaurant {
    @Override
    public void vegMeals() {
        System.out.println("Serving vegetarian meals");
    }
}

class XYZNonVegRestaurant implements NonVegRestaurant {
    @Override
    public void nonVegMeals() {
        System.out.println("Serving non-vegetarian meals");
    }
}

public class ISPAfter {
    public static void main(String[] args) {
        VegRestaurant vegOnly = new ABCVegRestaurant();
        vegOnly.vegMeals();

        NonVegRestaurant nonVegOnly = new XYZNonVegRestaurant();
        nonVegOnly.nonVegMeals();
    }
}
```

**Improvement:**
- Interfaces are segregated into smaller, role‑specific contracts.
- Each restaurant only implements what it actually serves.
- Clients depend only on what they use → satisfies ISP.

---

## Common Mistakes
- Designing “fat” interfaces with too many unrelated methods.
- Forcing all clients to implement every method.
- Using inheritance instead of composition when roles differ.

---

## Patterns That Support ISP
- **Role Interfaces** → small, focused contracts.
- **Adapter Pattern** → bridges between different interfaces.
- **Composition over inheritance** → combine small interfaces instead of extending large ones.

---

## Interview Q&A
**Q: What does ISP mean?**  
A: Clients should not be forced to depend on methods they don’t use.

**Q: Give a real‑world example.**  
A: A vegetarian restaurant shouldn’t be forced to implement non‑veg serving methods.

**Q: How do you apply ISP in design?**  
A: Split large interfaces into smaller, specific ones so classes only implement what they need.

---

## Key Takeaway
- **Before ISP** → One big interface forces classes to implement irrelevant methods.
- **After ISP** → Smaller, segregated interfaces let classes depend only on what they actually use.

