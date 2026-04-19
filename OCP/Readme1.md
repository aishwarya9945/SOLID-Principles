Here’s a clean **README1.md section** with just the **Payment Processing OCP example**

# Open/Closed Principle (OCP)

## Definition
The Open/Closed Principle states that software entities (classes, modules, functions) should be **open for extension but closed for modification**.  
This means you can add new functionality without changing existing, tested code.

## In Interview
“OCP means a class should be **open for extension but closed for modification**.  
For instance, a payment processor shouldn’t hardcode every payment type with `if/else`.  
Instead, it should depend on a `PaymentMethod` interface so new payment options can be added without touching the processor.  
This makes the design flexible, prevents breaking tested code, and aligns with clean architecture principles.”

-----

## Real‑World Example: Payment Processing

### ❌ Before OCP (Bad Design)
```java
class PaymentProcessor {
    void process(String type, double amount) {
        if (type.equals("CreditCard")) {
            System.out.println("Paid " + amount + " using Credit Card");
        } else if (type.equals("PayPal")) {
            System.out.println("Paid " + amount + " using PayPal");
        } else if (type.equals("UPI")) {
            System.out.println("Paid " + amount + " using UPI");
        }
        // Every new payment method requires modifying this method
    }
}

public class BeforeOCP {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();
        processor.process("CreditCard", 100.0);
        processor.process("PayPal", 200.0);
    }
}
```

**Problem:**
- Adding a new payment type (Crypto, Wallet, NetBanking) means editing `PaymentProcessor`.
- Violates OCP because the class is not closed for modification.

---

### ✅ After OCP (Good Design)
```java
// Abstraction
interface PaymentMethod {
    void pay(double amount);
}

// Concrete implementations
class CreditCardPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Credit Card");
    }
}

class PayPalPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using PayPal");
    }
}

class UpiPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using UPI");
    }
}

// Processor delegates to interface
class PaymentProcessor {
    void process(PaymentMethod method, double amount) {
        method.pay(amount);
    }
}

public class AfterOCP {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();

        processor.process(new CreditCardPayment(), 100.0);
        processor.process(new PayPalPayment(), 200.0);
        processor.process(new UpiPayment(), 300.0);
    }
}
```

**Improvement:**
- `PaymentProcessor` never changes again.
- New payment types are added by creating new classes implementing `PaymentMethod`.
- This is **open for extension** but **closed for modification**.


