Here’s a clean **README.md section** with just the **Ascending/Descending Sorting example**

# Open/Closed Principle (OCP)

## Definition
The Open/Closed Principle states that software entities(classes, modules, functions) should be **open for extension but closed for modification**.  
This means you can add new functionality without changing existing, tested code.

---

## In Interview
“OCP means a class should be open for extension but closed for modification.
For instance, a sorting utility shouldn’t hardcode ascending or descending logic.
Instead, it should accept a comparator interface so new sorting strategies can be added without changing the existing code.
This makes the design flexible, prevents breaking tested code, and aligns with clean architecture principles.”

## Why It Matters
- Prevents breaking stable code when adding new features.
- Encourages extensibility through abstraction and interfaces.
- Reduces risk of bugs introduced by modifying core logic.
- Aligns with clean architecture and interview expectations.

---

## When to Apply
- When requirements change frequently (e.g., new sorting criteria, new payment methods).
- When adding new features should not require editing old classes.
- During refactoring: replace long `if/else` chains with polymorphism.

---

## How to implement:-
Use abstraction (interfaces/abstract classes) and dependency injection.- Prefer composition over modifying existing classes.

## Before OCP (Hardcoded Sorting)

### Ascending
```java
int arr[] = {5, 1, 4, 2, 3};

for (int i = 0; i < arr.length; i++) {
    for (int j = i + 1; j < arr.length; j++) {
        if (arr[i] > arr[j]) { // ascending
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
```

### Descending
```java
int arr[] = {5, 1, 4, 2, 3};

for (int i = 0; i < arr.length; i++) {
    for (int j = i + 1; j < arr.length; j++) {
        if (arr[i] < arr[j]) { // descending
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
```

**Problem:**
- Sorting logic is hardcoded.
- To add new criteria (e.g., sort by name, score), you must **modify** the method.
- Violates OCP because the code is not closed for modification.

---

## After OCP (Flexible with Comparator)

```java
interface ValueComparator {
    int compare(int value1, int value2);
}

class ArrayUtil {
    public static void sort(int[] a, ValueComparator comparator) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (comparator.compare(a[i], a[j]) > 0) {
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
    }
}

class AscComparator implements ValueComparator {
    @Override
    public int compare(int value1, int value2) {
        return value1 - value2; // ascending
    }
}

class DescComparator implements ValueComparator {
    @Override
    public int compare(int value1, int value2) {
        return value2 - value1; // descending
    }
}

public class OCPAfter {
    public static void main(String[] args) {
        int arr[] = {5, 1, 4, 2, 3};

        System.out.println("Ascending order:");
        ArrayUtil.sort(arr, new AscComparator());
        for (int element : arr) {
            System.out.print(element + " ");
        }

        System.out.println("\nDescending order:");
        ArrayUtil.sort(arr, new DescComparator());
        for (int element : arr) {
            System.out.print(element + " ");
        }
    }
}
```

**Improvement:**
- `ArrayUtil.sort` never changes again.
- New comparators (ascending, descending, by score, by name, etc.) can be added without touching `ArrayUtil`.
- This is **open for extension** but **closed for modification**.

---

## Common Mistakes
- Using long `if/else` chains for every new requirement.
- Editing existing methods instead of extending via new classes.
- Ignoring abstraction and relying on concrete implementations.

---

## Patterns That Support OCP
- **Strategy Pattern** → interchangeable behaviors (like comparators).
- **Decorator Pattern** → add responsibilities without changing existing code.
- **Factory Pattern** → create objects without exposing instantiation logic.

---

## Interview Q&A
**Q: What does “open for extension, closed for modification” mean?**  
A: You can add new functionality by writing new classes (extension) but don’t need to change existing tested code (closed).

**Q: Give a real‑world example.**  
A: Payment processing — instead of modifying the processor for each new method, you add new payment classes implementing a common interface.

**Q: Which design patterns support OCP?**  
A: Strategy, Decorator, Factory, Template Method.

---

## Key Takeaway
- **Before OCP** → Sorting logic is hardcoded, requires modification for every new requirement.
- **After OCP** → Sorting logic is stable, extensible via new comparator classes.
```
