# SOLID Principles Reference

## 🛠 Single Responsibility Principle (SRP)
"A class should have one, and only one, reason to change." — Robert C. Martin

## In Interview
“SRP means a class should have only one reason to change.
For instance, a Report class shouldn’t both generate and email reports.
Splitting responsibilities makes the code easier to test and maintain.”
---

### 💡 Definition
SRP states that every module, class, or function should have responsibility over a single part of the program's functionality, and that responsibility should be entirely encapsulated by the class.

---

### 🚀 Why It Matters
- **Maintainability**: Changes in one requirement (e.g., database logic) don’t break unrelated logic (e.g., notifications).
- **Testability**: Smaller, focused classes are easier to unit test.
- **Reusability**: Components like `AccountNotifier` can be reused in other services without dragging along unrelated code.

---

### 📅 When to Apply
- When a class grows beyond ~200–300 lines of code.
- When describing a class requires the word **“and”** (e.g., “It saves data **and** sends emails”).
- When a change in one area (e.g., schema update) forces unrelated re‑testing (e.g., email formatting).

---

### 🛠 How to Implement
1. **Identify Responsibilities**: Look for different “actors” or reasons for change.
2. **Extract Classes**: Move persistence logic to a Repository, messaging logic to a Notifier.
3. **Delegate**: Use the Service class to orchestrate calls between specialized classes.

---

### ⚠️ Common Mistakes
- **God Object**: One giant class managing everything.
- **Fragile Code**: Tight coupling where one change ripples through the system.
- **Misinterpretation**: Thinking SRP means “one method per class” instead of “one responsibility.”

---

### 🏗 Common Patterns
- **Repository Pattern**: Encapsulates database operations.
- **Service Layer Pattern**: Encapsulates business logic.
- **Strategy/Observer Patterns**: Decouple notifications or calculations.

---

## 🎯 Interview Q&A

### 🔹 Direct Questions
**Q1: What is the Single Responsibility Principle?**  
**A:** SRP means a class should have only one reason to change. Each class should focus on a single responsibility.

**Q2: Why is SRP important?**  
**A:** It improves maintainability, testability, and reusability. Without SRP, classes become fragile and hard to extend.

**Q3: Does SRP mean a class should only have one method?**  
**A:** No. A class can have multiple methods as long as they all serve the same responsibility.

---

### 🔹 Scenario-Based Questions
**Q4: Suppose you have a class that handles user authentication and also sends emails. Which principle is violated?**  
**A:** SRP. Authentication and messaging are two separate responsibilities.

**Q5: You’re asked to design a payment system that allows adding new payment methods without modifying existing code. Which principle applies?**  
**A:** That’s **OCP (Open/Closed Principle)**, but SRP is also relevant — separate payment processing from notification logic.

**Q6: If a class both saves data to the database and formats reports, what’s the issue?**  
**A:** It violates SRP. Database logic and reporting logic are separate responsibilities.

---

### 🔹 Code-Level Example
```java
// ❌ Violates SRP
class AccountService {
    public void openAccount() {
        System.out.println("fill account internal details");
        System.out.println("store account object in database");
        System.out.println("send out welcome message");
    }
}

// ✅ Follows SRP
class AccountService {
    public void openAccount() {
        System.out.println("fill account internal details");
    }
}

class AccountRepository {
    public void saveAccount() {
        System.out.println("store account object in database");
    }
}

class AccountNotifier {
    public void sendWelcomeMessage() {
        System.out.println("send out welcome message");
    }
}
