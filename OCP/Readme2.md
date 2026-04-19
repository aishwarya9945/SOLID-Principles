Here’s a clean **README2.md** section with the classic **Shape/Area Calculation OCP example** — this is another interview‑friendly case study that pairs nicely with your Sorting and Payment Processing examples:

---

# Open/Closed Principle (OCP)

## Definition
The Open/Closed Principle states that software entities (classes, modules, functions) should be **open for extension but closed for modification**.  
This means you can add new functionality without changing existing, tested code.

---

## In Interview
“OCP means a class should be **open for extension but closed for modification**.  
For instance, an area calculator shouldn’t hardcode logic for Circle, Rectangle, and Triangle inside one method.  
Instead, each shape should implement a `Shape` interface with its own `area()` method.  
This way, new shapes can be added without touching the calculator, making the design flexible and safe.”

---

## Example: Shape Area Calculation

### ❌ Before OCP (Bad Design)
```java
class AreaCalculator {
    public double calculateArea(Object shape) {
        if (shape instanceof Circle) {
            Circle c = (Circle) shape;
            return Math.PI * c.radius * c.radius;
        } else if (shape instanceof Rectangle) {
            Rectangle r = (Rectangle) shape;
            return r.length * r.width;
        }
        // Adding a new shape requires modifying this method
        return 0;
    }
}

class Circle {
    double radius;
    Circle(double radius) { this.radius = radius; }
}

class Rectangle {
    double length, width;
    Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }
}

public class BeforeOCP {
    public static void main(String[] args) {
        AreaCalculator calc = new AreaCalculator();
        System.out.println("Circle area: " + calc.calculateArea(new Circle(5)));
        System.out.println("Rectangle area: " + calc.calculateArea(new Rectangle(4, 6)));
    }
}
```

**Problem:**
- Every time a new shape is introduced (Triangle, Polygon, etc.), the `AreaCalculator` must be modified.
- Violates OCP because the class is not closed for modification.

---

### ✅ After OCP (Good Design)
```java
// Abstraction
interface Shape {
    double area();
}

// Concrete implementations
class Circle implements Shape {
    double radius;
    Circle(double radius) { this.radius = radius; }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}

class Rectangle implements Shape {
    double length, width;
    Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public double area() {
        return length * width;
    }
}

// Calculator delegates to interface
class AreaCalculator {
    public double calculateArea(Shape shape) {
        return shape.area();
    }
}

public class AfterOCP {
    public static void main(String[] args) {
        AreaCalculator calc = new AreaCalculator();
        System.out.println("Circle area: " + calc.calculateArea(new Circle(5)));
        System.out.println("Rectangle area: " + calc.calculateArea(new Rectangle(4, 6)));
    }
}
```

**Improvement:**
- `AreaCalculator` never changes again.
- New shapes (Triangle, Polygon, etc.) can be added by creating new classes implementing `Shape`.
- This is **open for extension** but **closed for modification**.

---

## Key Takeaway
- **Before OCP** → Adding new shapes requires modifying the calculator.
- **After OCP** → Calculator is stable, extensible via new shape classes.
```