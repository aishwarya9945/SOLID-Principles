# Dependency Inversion Principle (DIP)

## Definition
The Dependency Inversion Principle states that **high‑level modules should not depend on low‑level modules; both should depend on abstractions**.  
Abstractions should not depend on details. Details should depend on abstractions.

---

## In Interview
“DIP means that instead of a high‑level class directly depending on a concrete implementation, it should depend on an abstraction (interface). This makes the system flexible, testable, and easy to extend. For example, a messenger app should depend on a `ProtocolHandler` interface, not directly on `TCPProtocolHandler`.”

---

## Why It Matters
- Decouples high‑level logic from low‑level details.
- Makes systems flexible and extensible.
- Enables dependency injection and inversion of control.
- Improves testability (mocking abstractions instead of concrete classes).
- Aligns with clean architecture and interview expectations.

---

## When to Apply
- When high‑level modules are tightly coupled to low‑level implementations.
- When you want to swap implementations without modifying core logic.
- During refactoring: replace direct instantiations with abstractions and factories.

---

## How to Apply
- Introduce interfaces or abstract classes for low‑level modules.
- Make high‑level modules depend on these abstractions.
- Use dependency injection (constructor, setter, or factory) to provide concrete implementations.

---

## ❌ Before DIP (Violation Example)
```java
// Application Layer
class MyMessenger {
    TCPProtocolHandler tcpProtocolHandler = new TCPProtocolHandler();

    public void send(String to, String message) {
        tcpProtocolHandler.sendMessage("Message to - " + to + ", message - " + message);
    }
}

// Transport Layer
class TCPProtocolHandler {
    public void sendMessage(String message) {
        System.out.println("TCPProtocol Handler Sending Message - " + message);
    }
}

public class DIPBefore {
    public static void main(String[] args) {
        MyMessenger messenger = new MyMessenger();
        messenger.send("Alice", "SOLID is easy");
    }
}
```

**Problem:**
- `MyMessenger` is tightly coupled to `TCPProtocolHandler`.
- To support UDP, you must modify `MyMessenger`.
- Violates DIP because high‑level depends on low‑level details.

---

## ✅ After DIP (Correct Example)
```java
// Abstraction
interface ProtocolHandler {
    void sendMessage(String message);
}

// Transport Layer
class TCPProtocolHandler implements ProtocolHandler {
    @Override
    public void sendMessage(String message) {
        System.out.println("TCPProtocol Handler Sending Message - " + message);
    }
}

class UDPProtocolHandler implements ProtocolHandler {
    @Override
    public void sendMessage(String message) {
        System.out.println("UDPProtocol Handler Sending Message - " + message);
    }
}

// Application Layer
class MyMessenger {
    private ProtocolHandler protocolHandler;

    // Dependency injected via constructor
    public MyMessenger(ProtocolHandler protocolHandler) {
        this.protocolHandler = protocolHandler;
    }

    public void send(String to, String message) {
        protocolHandler.sendMessage("Message to - " + to + ", message - " + message);
    }
}

public class DIPAfter {
    public static void main(String[] args) {
        ProtocolHandler tcpHandler = new TCPProtocolHandler();
        ProtocolHandler udpHandler = new UDPProtocolHandler();

        MyMessenger messenger1 = new MyMessenger(tcpHandler);
        messenger1.send("Alice", "SOLID is easy with TCP");

        MyMessenger messenger2 = new MyMessenger(udpHandler);
        messenger2.send("Bob", "SOLID is easy with UDP");
    }
}
```

**Improvement:**
- `MyMessenger` depends on the abstraction `ProtocolHandler`.
- Concrete implementations (`TCPProtocolHandler`, `UDPProtocolHandler`) can be swapped without modifying `MyMessenger`.
- This satisfies DIP.

---

## Common Mistakes
- High‑level modules directly instantiating low‑level classes.
- No abstraction layer between core logic and implementation details.
- Ignoring dependency injection and hardcoding dependencies.

---

## Patterns That Support DIP
- **Dependency Injection** → inject abstractions instead of instantiating.
- **Factory Pattern** → create objects without exposing instantiation logic.
- **Strategy Pattern** → swap behaviors at runtime via abstractions.
- **Inversion of Control (IoC)** → framework manages dependencies.

---

## Interview Q&A
**Q: What does DIP mean?**  
A: High‑level modules should depend on abstractions, not concrete implementations.

**Q: Give a real‑world example.**  
A: A messenger app should depend on a `ProtocolHandler` interface, not directly on `TCPProtocolHandler`.

**Q: How do you apply DIP in design?**  
A: Introduce abstractions, inject dependencies, and ensure both high‑level and low‑level modules depend on interfaces.

---

## Key Takeaway
- **Before DIP** → High‑level class (`MyMessenger`) tightly coupled to low‑level (`TCPProtocolHandler`).
- **After DIP** → High‑level class depends on abstraction (`ProtocolHandler`), making the system flexible, extensible, and testable.