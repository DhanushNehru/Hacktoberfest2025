# Polymorphism 🎭

## What is Polymorphism?

Polymorphism comes from Greek words "poly" (many) and "morph" (forms). It's the ability of objects of different types to be treated as instances of the same type through a common interface. One interface, many implementations!

## Real-World Analogy

Think of a **remote control**:

- The same "power" button works on TV, stereo, air conditioner
- Each device responds differently to the same button press
- You use the same interface (button) but get different behaviors

Or consider **drawing tools**:

- You can "draw" with a pencil, pen, brush, or marker
- Same action ("draw") but different results based on the tool

## Types of Polymorphism

### 1. Compile-time Polymorphism (Static)

- **Method Overloading**: Same method name, different parameters
- **Operator Overloading**: Same operator, different behaviors

### 2. Runtime Polymorphism (Dynamic)

- **Method Overriding**: Same method signature, different implementations
- **Interface Implementation**: Multiple classes implementing same interface

## Implementation Examples

### Python Example - Runtime Polymorphism

```python
from abc import ABC, abstractmethod
import math

# Abstract base class (interface)
class Shape(ABC):
    @abstractmethod
    def area(self):
        pass

    @abstractmethod
    def perimeter(self):
        pass

    @abstractmethod
    def draw(self):
        pass

# Concrete implementations
class Circle(Shape):
    def __init__(self, radius):
        self.radius = radius

    def area(self):
        return math.pi * self.radius ** 2

    def perimeter(self):
        return 2 * math.pi * self.radius

    def draw(self):
        print(f"Drawing a circle with radius {self.radius}")

class Rectangle(Shape):
    def __init__(self, width, height):
        self.width = width
        self.height = height

    def area(self):
        return self.width * self.height

    def perimeter(self):
        return 2 * (self.width + self.height)

    def draw(self):
        print(f"Drawing a rectangle {self.width}x{self.height}")

class Triangle(Shape):
    def __init__(self, base, height, side1, side2):
        self.base = base
        self.height = height
        self.side1 = side1
        self.side2 = side2

    def area(self):
        return 0.5 * self.base * self.height

    def perimeter(self):
        return self.base + self.side1 + self.side2

    def draw(self):
        print(f"Drawing a triangle with base {self.base}")

# Polymorphic function
def calculate_total_area(shapes):
    """Calculate total area of different shapes"""
    total = 0
    for shape in shapes:
        total += shape.area()  # Same method call, different implementations
    return total

def draw_all_shapes(shapes):
    """Draw all shapes regardless of their type"""
    for shape in shapes:
        shape.draw()  # Polymorphic method call

# Usage - Polymorphism in action!
shapes = [
    Circle(5),
    Rectangle(4, 6),
    Triangle(3, 4, 3, 5),
    Circle(2),
    Rectangle(2, 8)
]

# Same function works with different shape types
print(f"Total area: {calculate_total_area(shapes):.2f}")

# Same method call, different behaviors
draw_all_shapes(shapes)

# Polymorphic behavior with different objects
for shape in shapes:
    print(f"Area: {shape.area():.2f}, Perimeter: {shape.perimeter():.2f}")
```

### Java Example - Interface Polymorphism

```java
// Interface definition
interface Animal {
    void makeSound();
    void move();
    String getType();
}

// Concrete implementations
class Dog implements Animal {
    private String name;

    public Dog(String name) {
        this.name = name;
    }

    @Override
    public void makeSound() {
        System.out.println(name + " barks: Woof! Woof!");
    }

    @Override
    public void move() {
        System.out.println(name + " runs on four legs");
    }

    @Override
    public String getType() {
        return "Mammal";
    }
}

class Bird implements Animal {
    private String name;

    public Bird(String name) {
        this.name = name;
    }

    @Override
    public void makeSound() {
        System.out.println(name + " chirps: Tweet! Tweet!");
    }

    @Override
    public void move() {
        System.out.println(name + " flies through the air");
    }

    @Override
    public String getType() {
        return "Avian";
    }
}

class Fish implements Animal {
    private String name;

    public Fish(String name) {
        this.name = name;
    }

    @Override
    public void makeSound() {
        System.out.println(name + " makes bubbles: Blub! Blub!");
    }

    @Override
    public void move() {
        System.out.println(name + " swims through water");
    }

    @Override
    public String getType() {
        return "Aquatic";
    }
}

// Polymorphic utility class
class AnimalShelter {
    public static void feedAnimals(Animal[] animals) {
        System.out.println("Feeding time at the shelter!");
        for (Animal animal : animals) {
            animal.makeSound();  // Polymorphic call
            System.out.println("Feeding " + animal.getType() + " animal");
        }
    }

    public static void exerciseAnimals(Animal[] animals) {
        System.out.println("Exercise time!");
        for (Animal animal : animals) {
            animal.move();  // Polymorphic call
        }
    }
}

// Usage
public class PolymorphismDemo {
    public static void main(String[] args) {
        // Array of different animal types
        Animal[] animals = {
            new Dog("Buddy"),
            new Bird("Tweety"),
            new Fish("Nemo"),
            new Dog("Max"),
            new Bird("Polly")
        };

        // Polymorphic method calls
        AnimalShelter.feedAnimals(animals);
        System.out.println();
        AnimalShelter.exerciseAnimals(animals);

        // Individual polymorphic calls
        System.out.println("\nIndividual animal actions:");
        for (Animal animal : animals) {
            animal.makeSound();
            animal.move();
            System.out.println("Type: " + animal.getType());
            System.out.println("---");
        }
    }
}
```

### C++ Example - Virtual Functions

```cpp
#include <iostream>
#include <vector>
#include <memory>

// Base class with virtual functions
class Vehicle {
protected:
    std::string brand;

public:
    Vehicle(const std::string& b) : brand(b) {}

    // Virtual functions enable polymorphism
    virtual void start() {
        std::cout << brand << " vehicle is starting..." << std::endl;
    }

    virtual void accelerate() = 0;  // Pure virtual (abstract)

    virtual void brake() {
        std::cout << brand << " is braking..." << std::endl;
    }

    virtual ~Vehicle() = default;  // Virtual destructor

    std::string getBrand() const { return brand; }
};

// Derived classes
class Car : public Vehicle {
public:
    Car(const std::string& brand) : Vehicle(brand) {}

    void start() override {
        std::cout << "Car " << brand << " engine purrs to life" << std::endl;
    }

    void accelerate() override {
        std::cout << "Car " << brand << " accelerates smoothly" << std::endl;
    }

    void brake() override {
        std::cout << "Car " << brand << " brakes with ABS system" << std::endl;
    }
};

class Motorcycle : public Vehicle {
public:
    Motorcycle(const std::string& brand) : Vehicle(brand) {}

    void start() override {
        std::cout << "Motorcycle " << brand << " roars to life!" << std::endl;
    }

    void accelerate() override {
        std::cout << "Motorcycle " << brand << " accelerates with a roar!" << std::endl;
    }

    void brake() override {
        std::cout << "Motorcycle " << brand << " brakes carefully" << std::endl;
    }
};

class Truck : public Vehicle {
public:
    Truck(const std::string& brand) : Vehicle(brand) {}

    void start() override {
        std::cout << "Truck " << brand << " diesel engine rumbles" << std::endl;
    }

    void accelerate() override {
        std::cout << "Truck " << brand << " accelerates with heavy load" << std::endl;
    }

    void brake() override {
        std::cout << "Truck " << brand << " air brakes hiss" << std::endl;
    }
};

// Polymorphic functions
void testDrive(Vehicle& vehicle) {
    std::cout << "=== Test driving " << vehicle.getBrand() << " ===" << std::endl;
    vehicle.start();      // Polymorphic call
    vehicle.accelerate(); // Polymorphic call
    vehicle.brake();      // Polymorphic call
    std::cout << std::endl;
}

void raceVehicles(const std::vector<std::unique_ptr<Vehicle>>& vehicles) {
    std::cout << "🏁 RACE START! 🏁" << std::endl;
    for (const auto& vehicle : vehicles) {
        vehicle->start();      // Polymorphic call through pointer
        vehicle->accelerate(); // Polymorphic call through pointer
    }
    std::cout << "🏁 RACE FINISH! 🏁\n" << std::endl;
}

// Usage
int main() {
    // Create different vehicle objects
    Car car("Toyota");
    Motorcycle bike("Harley");
    Truck truck("Volvo");

    // Polymorphic function calls with references
    testDrive(car);
    testDrive(bike);
    testDrive(truck);

    // Polymorphic container with smart pointers
    std::vector<std::unique_ptr<Vehicle>> vehicles;
    vehicles.push_back(std::make_unique<Car>("BMW"));
    vehicles.push_back(std::make_unique<Motorcycle>("Yamaha"));
    vehicles.push_back(std::make_unique<Truck>("Mercedes"));

    raceVehicles(vehicles);

    return 0;
}
```

### Method Overloading Example (Compile-time Polymorphism)

```python
class Calculator:
    def add(self, a, b):
        """Add two numbers"""
        return a + b

    def add(self, a, b, c):
        """Add three numbers"""
        return a + b + c

    # Python doesn't support method overloading directly
    # Use default parameters or *args instead
    def multiply(self, *args):
        """Multiply variable number of arguments"""
        result = 1
        for num in args:
            result *= num
        return result

    def process(self, data):
        """Process different types of data"""
        if isinstance(data, (int, float)):
            return data * 2
        elif isinstance(data, str):
            return data.upper()
        elif isinstance(data, list):
            return [x * 2 for x in data]
        else:
            return str(data)

# Usage
calc = Calculator()
print(calc.multiply(2, 3))        # 6
print(calc.multiply(2, 3, 4))     # 24
print(calc.multiply(1, 2, 3, 4))  # 24

print(calc.process(5))            # 10
print(calc.process("hello"))      # HELLO
print(calc.process([1, 2, 3]))    # [2, 4, 6]
```

## Duck Typing (Python's Approach)

"If it walks like a duck and quacks like a duck, then it's a duck!"

```python
class Duck:
    def quack(self):
        print("Quack! Quack!")

    def fly(self):
        print("Duck is flying")

class Airplane:
    def quack(self):
        print("Airplane horn: HONK!")

    def fly(self):
        print("Airplane is flying at 30,000 feet")

class Robot:
    def quack(self):
        print("Robot says: BEEP BOOP QUACK")

    def fly(self):
        print("Robot activates jetpack")

def make_it_fly_and_quack(thing):
    """Works with any object that has quack() and fly() methods"""
    thing.quack()
    thing.fly()

# All these work due to duck typing!
duck = Duck()
plane = Airplane()
robot = Robot()

for thing in [duck, plane, robot]:
    make_it_fly_and_quack(thing)
```

## Benefits of Polymorphism

1. **Code Flexibility**: Same interface, different implementations
2. **Extensibility**: Easy to add new types without changing existing code
3. **Maintainability**: Changes to implementations don't affect client code
4. **Abstraction**: Hide implementation details behind common interfaces
5. **Testability**: Easy to create mock objects for testing

## Best Practices

1. **Design interfaces first**

   - Think about what methods objects should have in common

2. **Use meaningful method names**

   - Same operation should have the same name across classes

3. **Keep interfaces simple**

   - Don't force unrelated methods into the same interface

4. **Favor composition over inheritance**

   - Sometimes multiple interfaces are better than deep inheritance

5. **Use abstract base classes/interfaces**
   - Enforce contracts that implementing classes must follow

## Common Pitfalls

❌ **Forcing unrelated classes into inheritance:**

```python
class Flyable:
    def fly(self): pass

class Bird(Flyable): pass
class Airplane(Flyable): pass  # Airplane is not a bird!
```

✅ **Use interfaces/protocols instead:**

```python
from abc import ABC, abstractmethod

class Flyable(ABC):
    @abstractmethod
    def fly(self): pass

class Bird(Flyable): pass
class Airplane(Flyable): pass  # Both can fly, different implementations
```

❌ **Breaking the Liskov Substitution Principle:**

```python
class Rectangle:
    def set_width(self, w): self.width = w
    def set_height(self, h): self.height = h

class Square(Rectangle):
    def set_width(self, w):
        self.width = self.height = w  # Changes behavior!
```

## Summary

Polymorphism is about treating different objects uniformly through a common interface. It's the magic that lets you write flexible, extensible code that works with objects you haven't even created yet!

**Key Takeaway**: Write code that depends on abstractions (interfaces) rather than concrete implementations. This makes your code more flexible and easier to extend. 🎯
