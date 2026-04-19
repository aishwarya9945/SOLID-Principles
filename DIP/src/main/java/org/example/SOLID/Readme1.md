let’s take a **different example** for the Dependency Inversion Principle (DIP) using **Payment Processing**:
---

# Dependency Inversion Principle (DIP)

## Definition
High‑level modules should not depend on low‑level modules. Both should depend on abstractions.  
Abstractions should not depend on details. Details should depend on abstractions.

---

## ❌ Before DIP (Violation Example)

```java
// High-level module
class PaymentProcessor {
    private CreditCardPayment creditCardPayment = new CreditCardPayment();

    public void process(String amount) {
        creditCardPayment.pay(amount);
    }
}

// Low-level module
class CreditCardPayment {
    public void pay(String amount) {
        System.out.println("Paid " + amount + " using Credit Card");
    }
}

public class DIPBefore {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();
        processor.process("1000");
    }
}
```

### Interview Explanation
- The high‑level class `PaymentProcessor` is tightly coupled to `CreditCardPayment`.
- If you want to add PayPal or UPI, you must **modify `PaymentProcessor`**.
- This violates DIP because the high‑level module depends directly on a low‑level detail.

---

## ✅ After DIP (Correct Example)

```java
// Abstraction
interface PaymentMethod {
    void pay(String amount);
}

// Low-level modules
class CreditCardPayment implements PaymentMethod {
    @Override
    public void pay(String amount) {
        System.out.println("Paid " + amount + " using Credit Card");
    }
}

class PayPalPayment implements PaymentMethod {
    @Override
    public void pay(String amount) {
        System.out.println("Paid " + amount + " using PayPal");
    }
}

// High-level module
class PaymentProcessor {
    private PaymentMethod paymentMethod;

    // Dependency injected via constructor
    public PaymentProcessor(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void process(String amount) {
        paymentMethod.pay(amount);
    }
}

public class DIPAfter {
    public static void main(String[] args) {
        PaymentProcessor processor1 = new PaymentProcessor(new CreditCardPayment());
        processor1.process("1000");

        PaymentProcessor processor2 = new PaymentProcessor(new PayPalPayment());
        processor2.process("2000");
    }
}
```

### Interview Explanation
- Now `PaymentProcessor` depends on the abstraction `PaymentMethod`.
- Concrete implementations (`CreditCardPayment`, `PayPalPayment`) can be swapped without modifying `PaymentProcessor`.
- This satisfies DIP: both high‑level and low‑level modules depend on abstractions.
- In interviews, emphasize that this design supports **dependency injection**, extensibility, and testability.

---

## Key Interview Takeaway
- **Before DIP:** High‑level class (`PaymentProcessor`) tightly coupled to low‑level (`CreditCardPayment`).
- **After DIP:** High‑level class depends on abstraction (`PaymentMethod`), making the system flexible and extensible.
- **Phrase to use in interviews:**  
  “DIP ensures high‑level modules depend on abstractions, not concrete implementations. This makes systems flexible, extensible, and testable.”
