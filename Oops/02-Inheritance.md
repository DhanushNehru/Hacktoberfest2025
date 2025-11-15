# Inheritance 🧬

## What is Inheritance?

Inheritance is a mechanism where a new class (child/derived class) inherits properties and methods from an existing class (parent/base class). It's like how children inherit traits from their parents - they get some characteristics automatically but can also develop their own unique features.

## Real-World Analogy

Think of **vehicles**:

- **Vehicle** (parent): Has wheels, engine, can move
- **Car** (child): Inherits vehicle properties + has 4 wheels, doors
- **Motorcycle** (child): Inherits vehicle properties + has 2 wheels, no doors
- **Truck** (child): Inherits vehicle properties + has cargo space

## Key Benefits

- **Code Reusability**: Don't repeat common functionality
- **Hierarchical Organization**: Natural way to model relationships
- **Extensibility**: Easy to add new features to existing code
- **Maintainability**: Changes in parent class affect all children

## Types of Inheritance

### 1. Single Inheritance

One child class inherits from one parent class.

### 2. Multiple Inheritance

One child class inherits from multiple parent classes (not supported in all languages).

### 3. Multilevel Inheritance

Child class inherits from parent, which inherits from grandparent.

### 4. Hierarchical Inheritance

Multiple child classes inherit from one parent class.

## Implementation Examples

### Python Example

```python
# Base/Parent Class
class Animal:
    def __init__(self, name, species):
        self.name = name
        self.species = species
        self.is_alive = True

    def eat(self):
        print(f"{self.name} is eating...")

    def sleep(self):
        print(f"{self.name} is sleeping...")

    def make_sound(self):
        print(f"{self.name} makes a sound...")

# Derived/Child Class
class Dog(Animal):
    def __init__(self, name, breed):
        super().__init__(name, "Canine")  # Call parent constructor
        self.breed = breed
        self.loyalty = "High"

    # Override parent method
    def make_sound(self):
        print(f"{self.name} barks: Woof! Woof!")

    # New method specific to Dog
    def fetch(self):
        print(f"{self.name} is fetching the ball!")

    def guard(self):
        print(f"{self.name} is guarding the house!")

class Cat(Animal):
    def __init__(self, name, indoor=True):
        super().__init__(name, "Feline")
        self.indoor = indoor
        self.independence = "High"

    # Override parent method
    def make_sound(self):
        print(f"{self.name} meows: Meow! Meow!")

    # New method specific to Cat
    def climb(self):
        print(f"{self.name} is climbing the tree!")

    def purr(self):
        print(f"{self.name} is purring contentedly...")

# Usage
dog = Dog("Buddy", "Golden Retriever")
cat = Cat("Whiskers", indoor=True)

# Inherited methods
dog.eat()        # Buddy is eating...
cat.sleep()      # Whiskers is sleeping...

# Overridden methods
dog.make_sound() # Buddy barks: Woof! Woof!
cat.make_sound() # Whiskers meows: Meow! Meow!

# Child-specific methods
dog.fetch()      # Buddy is fetching the ball!
cat.purr()       # Whiskers is purring contentedly...

print(f"Dog breed: {dog.breed}")           # Golden Retriever
print(f"Cat independence: {cat.independence}") # High
```

### Java Example

```java
// Base/Parent Class
class Vehicle {
    protected String brand;
    protected int year;
    protected double price;

    public Vehicle(String brand, int year, double price) {
        this.brand = brand;
        this.year = year;
        this.price = price;
    }

    public void start() {
        System.out.println(brand + " is starting...");
    }

    public void stop() {
        System.out.println(brand + " has stopped.");
    }

    public void displayInfo() {
        System.out.println("Brand: " + brand + ", Year: " + year + ", Price: $" + price);
    }
}

// Derived/Child Class
class Car extends Vehicle {
    private int doors;
    private String fuelType;

    public Car(String brand, int year, double price, int doors, String fuelType) {
        super(brand, year, price);  // Call parent constructor
        this.doors = doors;
        this.fuelType = fuelType;
    }

    // Override parent method
    @Override
    public void start() {
        System.out.println("Car " + brand + " engine is starting with a smooth hum...");
    }

    // New method specific to Car
    public void openTrunk() {
        System.out.println("Car trunk is now open.");
    }

    public void playMusic() {
        System.out.println("Playing music through car stereo system.");
    }

    @Override
    public void displayInfo() {
        super.displayInfo();  // Call parent method
        System.out.println("Doors: " + doors + ", Fuel Type: " + fuelType);
    }
}

class Motorcycle extends Vehicle {
    private boolean hasSidecar;
    private int engineCC;

    public Motorcycle(String brand, int year, double price, boolean hasSidecar, int engineCC) {
        super(brand, year, price);
        this.hasSidecar = hasSidecar;
        this.engineCC = engineCC;
    }

    @Override
    public void start() {
        System.out.println("Motorcycle " + brand + " engine roars to life!");
    }

    public void wheelie() {
        System.out.println("Performing a wheelie on the " + brand + "!");
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Engine CC: " + engineCC + ", Has Sidecar: " + hasSidecar);
    }
}

// Usage
public class InheritanceDemo {
    public static void main(String[] args) {
        Car car = new Car("Toyota", 2023, 25000, 4, "Hybrid");
        Motorcycle bike = new Motorcycle("Harley", 2022, 15000, false, 1200);

        // Inherited methods
        car.start();        // Car Toyota engine is starting...
        bike.start();       // Motorcycle Harley engine roars to life!

        car.displayInfo();  // Shows car-specific info
        bike.displayInfo(); // Shows motorcycle-specific info

        // Child-specific methods
        car.playMusic();    // Playing music through car stereo system.
        bike.wheelie();     // Performing a wheelie on the Harley!
    }
}
```

### C++ Example

```cpp
#include <iostream>
#include <string>

// Base/Parent Class
class Shape {
protected:
    std::string color;
    double x, y;  // Position

public:
    Shape(std::string c, double x_pos, double y_pos)
        : color(c), x(x_pos), y(y_pos) {}

    virtual void draw() {  // Virtual function for polymorphism
        std::cout << "Drawing a " << color << " shape at (" << x << ", " << y << ")" << std::endl;
    }

    virtual double getArea() = 0;  // Pure virtual function (abstract)

    void move(double dx, double dy) {
        x += dx;
        y += dy;
        std::cout << "Shape moved to (" << x << ", " << y << ")" << std::endl;
    }

    std::string getColor() const { return color; }
};

// Derived/Child Class
class Circle : public Shape {
private:
    double radius;

public:
    Circle(std::string c, double x, double y, double r)
        : Shape(c, x, y), radius(r) {}

    // Override parent method
    void draw() override {
        std::cout << "Drawing a " << color << " circle with radius " << radius
                  << " at (" << x << ", " << y << ")" << std::endl;
    }

    // Implement pure virtual function
    double getArea() override {
        return 3.14159 * radius * radius;
    }

    // New method specific to Circle
    double getCircumference() {
        return 2 * 3.14159 * radius;
    }
};

class Rectangle : public Shape {
private:
    double width, height;

public:
    Rectangle(std::string c, double x, double y, double w, double h)
        : Shape(c, x, y), width(w), height(h) {}

    void draw() override {
        std::cout << "Drawing a " << color << " rectangle " << width << "x" << height
                  << " at (" << x << ", " << y << ")" << std::endl;
    }

    double getArea() override {
        return width * height;
    }

    double getPerimeter() {
        return 2 * (width + height);
    }
};

// Usage
int main() {
    Circle circle("red", 10, 20, 5);
    Rectangle rect("blue", 0, 0, 8, 6);

    // Inherited methods
    circle.move(5, 5);    // Shape moved to (15, 25)
    rect.move(-2, 3);     // Shape moved to (-2, 3)

    // Overridden methods
    circle.draw();        // Drawing a red circle...
    rect.draw();          // Drawing a blue rectangle...

    // Polymorphic behavior
    Shape* shapes[] = {&circle, &rect};
    for (int i = 0; i < 2; i++) {
        std::cout << "Area: " << shapes[i]->getArea() << std::endl;
    }

    return 0;
}
```

## Method Overriding vs Method Overloading

### Method Overriding

- **Same method name** in parent and child class
- **Same parameters** but different implementation
- Achieved through inheritance

### Method Overloading

- **Same method name** but **different parameters**
- Can be in the same class or inherited classes
- Compile-time polymorphism

## The `super` Keyword

The `super` keyword is used to:

- Call parent class constructor
- Access parent class methods
- Access parent class variables (when hidden by child class)

```python
class Parent:
    def __init__(self, name):
        self.name = name

    def greet(self):
        print(f"Hello from {self.name}")

class Child(Parent):
    def __init__(self, name, age):
        super().__init__(name)  # Call parent constructor
        self.age = age

    def greet(self):
        super().greet()  # Call parent method
        print(f"I am {self.age} years old")
```

## Best Practices

1. **Use inheritance for "is-a" relationships**

   - Dog IS-A Animal ✅
   - Car HAS-A Engine (use composition instead) ❌

2. **Keep inheritance hierarchies shallow**

   - Deep hierarchies are hard to maintain

3. **Use `super()` to call parent methods**

   - Maintains the inheritance chain

4. **Override methods meaningfully**

   - Don't override just for the sake of it

5. **Consider composition over inheritance**
   - Sometimes "has-a" is better than "is-a"

## Common Pitfalls

❌ **Avoid deep inheritance chains:**

```python
class A: pass
class B(A): pass
class C(B): pass
class D(C): pass  # Too deep!
```

❌ **Don't use inheritance for code reuse alone:**

```python
class Utils:
    def helper_method(self): pass

class MyClass(Utils):  # Wrong! MyClass is not a Utils
    pass
```

✅ **Use composition instead:**

```python
class Utils:
    def helper_method(self): pass

class MyClass:
    def __init__(self):
        self.utils = Utils()  # Composition
```

## Summary

Inheritance allows you to build upon existing code, creating specialized versions of general concepts. It's a powerful tool for code organization and reuse, but should be used thoughtfully to maintain clean, understandable code structures.

**Remember**: Inheritance represents "is-a" relationships. If you can't say "Child IS-A Parent," consider using composition instead! 🏗️
