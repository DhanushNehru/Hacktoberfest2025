# Method Overloading and Overriding 🔄

## What are Method Overloading and Overriding?

**Method Overloading**: Multiple methods with the same name but different parameters (compile-time polymorphism).

**Method Overriding**: Child class provides a specific implementation of a method already defined in parent class (runtime polymorphism).

## Real-World Analogy

**Method Overloading = Swiss Army Knife**

- Same tool name ("cut") but different implementations
- cut(paper) - uses scissors blade
- cut(wire) - uses wire cutter
- cut(wood) - uses saw blade

**Method Overriding = Vehicle Controls**

- All vehicles have "start()" method
- Car: turn key, press button
- Motorcycle: kick start, electric start
- Airplane: complex startup sequence

## Method Overloading

### Key Rules:

1. **Same method name**
2. **Different parameter lists** (number, type, or order)
3. **Return type can be different** (but not the only difference)
4. **Resolved at compile time**

## Method Overriding

### Key Rules:

1. **Same method signature** (name, parameters, return type)
2. **Inheritance relationship** required
3. **Child class implementation** replaces parent's
4. **Resolved at runtime**

## Implementation Examples

### Python Example - Method Overloading (Simulated)

```python
from typing import Union, List, overload
import math

class Calculator:
    """Demonstrates method overloading patterns in Python"""

    # Python doesn't have true method overloading, but we can simulate it

    def add(self, *args):
        """Overloaded add method - handles different argument types and counts"""
        if len(args) == 2:
            a, b = args
            if isinstance(a, (int, float)) and isinstance(b, (int, float)):
                return self._add_numbers(a, b)
            elif isinstance(a, str) and isinstance(b, str):
                return self._add_strings(a, b)
            elif isinstance(a, list) and isinstance(b, list):
                return self._add_lists(a, b)
        elif len(args) == 3:
            return self._add_three_numbers(*args)
        elif len(args) > 3:
            return self._add_multiple_numbers(args)
        else:
            raise ValueError("Invalid number of arguments")

    def _add_numbers(self, a: float, b: float) -> float:
        """Add two numbers"""
        print(f"Adding numbers: {a} + {b}")
        return a + b

    def _add_strings(self, a: str, b: str) -> str:
        """Concatenate two strings"""
        print(f"Concatenating strings: '{a}' + '{b}'")
        return a + b

    def _add_lists(self, a: List, b: List) -> List:
        """Combine two lists"""
        print(f"Combining lists: {a} + {b}")
        return a + b

    def _add_three_numbers(self, a: float, b: float, c: float) -> float:
        """Add three numbers"""
        print(f"Adding three numbers: {a} + {b} + {c}")
        return a + b + c

    def _add_multiple_numbers(self, numbers: tuple) -> float:
        """Add multiple numbers"""
        print(f"Adding multiple numbers: {numbers}")
        return sum(numbers)

    # Alternative approach using type checking
    def multiply(self, a, b=None, c=None):
        """Multiply with different parameter combinations"""
        if b is None:
            # Square the number
            print(f"Squaring: {a}²")
            return a * a
        elif c is None:
            # Multiply two numbers
            if isinstance(a, str) and isinstance(b, int):
                print(f"Repeating string '{a}' {b} times")
                return a * b
            else:
                print(f"Multiplying: {a} × {b}")
                return a * b
        else:
            # Multiply three numbers
            print(f"Multiplying three: {a} × {b} × {c}")
            return a * b * c

    # Using functools.singledispatch for true overloading
    from functools import singledispatch

    @singledispatch
    def process(self, arg):
        """Generic process method"""
        print(f"Processing unknown type: {type(arg)}")
        return str(arg)

    @process.register
    def _(self, arg: int):
        """Process integer"""
        print(f"Processing integer: {arg}")
        return arg * 2

    @process.register
    def _(self, arg: str):
        """Process string"""
        print(f"Processing string: '{arg}'")
        return arg.upper()

    @process.register
    def _(self, arg: list):
        """Process list"""
        print(f"Processing list: {arg}")
        return len(arg)

# Method Overriding Example
class Shape:
    """Base class for shapes"""

    def __init__(self, name):
        self.name = name

    def area(self):
        """Calculate area - to be overridden by subclasses"""
        raise NotImplementedError("Subclasses must implement area()")

    def perimeter(self):
        """Calculate perimeter - to be overridden by subclasses"""
        raise NotImplementedError("Subclasses must implement perimeter()")

    def display_info(self):
        """Display shape information - can be overridden"""
        print(f"Shape: {self.name}")
        print(f"Area: {self.area()}")
        print(f"Perimeter: {self.perimeter()}")

    def describe(self):
        """Base description - may be extended by subclasses"""
        return f"This is a {self.name}"

class Rectangle(Shape):
    """Rectangle class - overrides Shape methods"""

    def __init__(self, width, height):
        super().__init__("Rectangle")
        self.width = width
        self.height = height

    def area(self):
        """Override: Calculate rectangle area"""
        return self.width * self.height

    def perimeter(self):
        """Override: Calculate rectangle perimeter"""
        return 2 * (self.width + self.height)

    def display_info(self):
        """Override: Enhanced display with dimensions"""
        print(f"=== {self.name} Information ===")
        print(f"Width: {self.width}")
        print(f"Height: {self.height}")
        super().display_info()  # Call parent method
        print("=" * 30)

    def describe(self):
        """Override: More specific description"""
        base_desc = super().describe()
        return f"{base_desc} with width {self.width} and height {self.height}"

class Circle(Shape):
    """Circle class - overrides Shape methods"""

    def __init__(self, radius):
        super().__init__("Circle")
        self.radius = radius

    def area(self):
        """Override: Calculate circle area"""
        return math.pi * self.radius ** 2

    def perimeter(self):
        """Override: Calculate circle circumference"""
        return 2 * math.pi * self.radius

    def display_info(self):
        """Override: Enhanced display with radius"""
        print(f"=== {self.name} Information ===")
        print(f"Radius: {self.radius}")
        super().display_info()
        print("=" * 30)

    def describe(self):
        """Override: Circle-specific description"""
        return f"This is a {self.name} with radius {self.radius}"

class Triangle(Shape):
    """Triangle class - demonstrates method overriding"""

    def __init__(self, base, height, side1, side2):
        super().__init__("Triangle")
        self.base = base
        self.height = height
        self.side1 = side1
        self.side2 = side2

    def area(self):
        """Override: Calculate triangle area"""
        return 0.5 * self.base * self.height

    def perimeter(self):
        """Override: Calculate triangle perimeter"""
        return self.base + self.side1 + self.side2

    def display_info(self):
        """Override: Triangle-specific display"""
        print(f"=== {self.name} Information ===")
        print(f"Base: {self.base}")
        print(f"Height: {self.height}")
        print(f"Sides: {self.side1}, {self.side2}, {self.base}")
        super().display_info()
        print("=" * 30)

def demonstrate_overloading():
    """Demonstrate method overloading"""
    print("=== Method Overloading Demo ===\n")

    calc = Calculator()

    # Different add operations
    print("1. Add method overloading:")
    print(f"Numbers: {calc.add(5, 3)}")
    print(f"Strings: {calc.add('Hello', ' World')}")
    print(f"Lists: {calc.add([1, 2], [3, 4])}")
    print(f"Three numbers: {calc.add(1, 2, 3)}")
    print(f"Multiple numbers: {calc.add(1, 2, 3, 4, 5)}")

    print("\n2. Multiply method overloading:")
    print(f"Square: {calc.multiply(5)}")
    print(f"Two numbers: {calc.multiply(4, 6)}")
    print(f"String repeat: {calc.multiply('Hi! ', 3)}")
    print(f"Three numbers: {calc.multiply(2, 3, 4)}")

    print("\n3. Single dispatch overloading:")
    print(f"Integer: {calc.process(42)}")
    print(f"String: {calc.process('hello')}")
    print(f"List: {calc.process([1, 2, 3, 4, 5])}")

def demonstrate_overriding():
    """Demonstrate method overriding"""
    print("\n=== Method Overriding Demo ===\n")

    # Create different shapes
    shapes = [
        Rectangle(5, 3),
        Circle(4),
        Triangle(6, 4, 5, 7)
    ]

    print("1. Polymorphic method calls:")
    for shape in shapes:
        shape.display_info()
        print(f"Description: {shape.describe()}")
        print()

    print("2. Direct method overriding comparison:")
    rect = Rectangle(8, 6)

    # Show that overridden methods are called
    print("Rectangle methods (overridden):")
    print(f"Area: {rect.area()}")           # Calls Rectangle.area()
    print(f"Perimeter: {rect.perimeter()}")  # Calls Rectangle.perimeter()

    # Show super() usage
    print("\n3. Using super() to extend parent functionality:")
    rect.display_info()  # Calls Rectangle.display_info() which calls Shape.display_info()

if __name__ == "__main__":
    demonstrate_overloading()
    demonstrate_overriding()
```

### Java Example - True Method Overloading and Overriding

```java
// Method Overloading Example
class MathOperations {

    // Overloaded add methods - same name, different parameters

    // 1. Two integers
    public int add(int a, int b) {
        System.out.println("Adding two integers: " + a + " + " + b);
        return a + b;
    }

    // 2. Three integers
    public int add(int a, int b, int c) {
        System.out.println("Adding three integers: " + a + " + " + b + " + " + c);
        return a + b + c;
    }

    // 3. Two doubles
    public double add(double a, double b) {
        System.out.println("Adding two doubles: " + a + " + " + b);
        return a + b;
    }

    // 4. Array of integers
    public int add(int[] numbers) {
        System.out.print("Adding array: ");
        for (int num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();

        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        return sum;
    }

    // 5. String concatenation
    public String add(String a, String b) {
        System.out.println("Concatenating strings: '" + a + "' + '" + b + "'");
        return a + b;
    }

    // Overloaded multiply methods

    public int multiply(int a) {
        System.out.println("Squaring: " + a + "²");
        return a * a;
    }

    public int multiply(int a, int b) {
        System.out.println("Multiplying two integers: " + a + " × " + b);
        return a * b;
    }

    public double multiply(double a, double b) {
        System.out.println("Multiplying two doubles: " + a + " × " + b);
        return a * b;
    }

    public String multiply(String str, int times) {
        System.out.println("Repeating string '" + str + "' " + times + " times");
        return str.repeat(times);
    }

    // Overloaded constructors
    public MathOperations() {
        System.out.println("🔧 Default MathOperations created");
    }

    public MathOperations(String name) {
        System.out.println("🔧 Named MathOperations created: " + name);
    }
}

// Method Overriding Example - Base class
abstract class Vehicle {
    protected String brand;
    protected String model;
    protected int year;

    public Vehicle(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        System.out.println("🚗 Vehicle created: " + getFullName());
    }

    // Method to be overridden
    public abstract void start();

    // Method to be overridden
    public abstract void accelerate();

    // Method to be overridden
    public void brake() {
        System.out.println(getFullName() + " is braking...");
    }

    // Method that can be overridden
    public void displayInfo() {
        System.out.println("=== Vehicle Information ===");
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
    }

    // Final method - cannot be overridden
    public final String getFullName() {
        return year + " " + brand + " " + model;
    }

    // Static method - cannot be overridden (but can be hidden)
    public static void showVehicleTypes() {
        System.out.println("Vehicle types: Car, Motorcycle, Truck, etc.");
    }
}

// Derived class - Car
class Car extends Vehicle {
    private int doors;
    private String fuelType;

    public Car(String brand, String model, int year, int doors, String fuelType) {
        super(brand, model, year);  // Call parent constructor
        this.doors = doors;
        this.fuelType = fuelType;
        System.out.println("🚙 Car specifics: " + doors + " doors, " + fuelType + " fuel");
    }

    // Override abstract method
    @Override
    public void start() {
        System.out.println("🔑 " + getFullName() + " - Turning key, engine purrs to life");
        System.out.println("   Dashboard lights up, systems initialized");
    }

    // Override abstract method
    @Override
    public void accelerate() {
        System.out.println("🏎️ " + getFullName() + " - Pressing gas pedal, smooth acceleration");
        System.out.println("   Automatic transmission shifting gears");
    }

    // Override inherited method
    @Override
    public void brake() {
        System.out.println("🛑 " + getFullName() + " - ABS braking system engaged");
        System.out.println("   Anti-lock brakes preventing wheel lockup");
        super.brake();  // Call parent method
    }

    // Override and extend parent method
    @Override
    public void displayInfo() {
        super.displayInfo();  // Call parent method first
        System.out.println("Type: Car");
        System.out.println("Doors: " + doors);
        System.out.println("Fuel Type: " + fuelType);
        System.out.println("==========================");
    }

    // Car-specific method
    public void openTrunk() {
        System.out.println("🎒 " + getFullName() + " trunk opened");
    }

    // Static method (hides parent static method)
    public static void showVehicleTypes() {
        System.out.println("Car types: Sedan, SUV, Hatchback, Coupe, etc.");
    }
}

// Derived class - Motorcycle
class Motorcycle extends Vehicle {
    private int engineCC;
    private boolean hasSidecar;

    public Motorcycle(String brand, String model, int year, int engineCC, boolean hasSidecar) {
        super(brand, model, year);
        this.engineCC = engineCC;
        this.hasSidecar = hasSidecar;
        System.out.println("🏍️ Motorcycle specifics: " + engineCC + "CC engine, " +
                          (hasSidecar ? "with" : "without") + " sidecar");
    }

    @Override
    public void start() {
        System.out.println("🏍️ " + getFullName() + " - Kick starting, engine roars to life!");
        System.out.println("   Checking gear position, warming up engine");
    }

    @Override
    public void accelerate() {
        System.out.println("🏍️ " + getFullName() + " - Twisting throttle, powerful acceleration");
        System.out.println("   Manual gear shifting, engine revving high");
    }

    @Override
    public void brake() {
        System.out.println("🛑 " + getFullName() + " - Hand and foot brakes applied");
        System.out.println("   Careful braking to maintain balance");
        // Note: Not calling super.brake() - completely different implementation
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Type: Motorcycle");
        System.out.println("Engine: " + engineCC + "CC");
        System.out.println("Sidecar: " + (hasSidecar ? "Yes" : "No"));
        System.out.println("==========================");
    }

    public void wheelie() {
        System.out.println("🤸 " + getFullName() + " performing a wheelie!");
    }
}

// Demo class
public class OverloadingOverridingDemo {

    public static void demonstrateOverloading() {
        System.out.println("=== Method Overloading Demo ===\n");

        MathOperations math = new MathOperations("Calculator");

        // Different add method calls - resolved at compile time
        System.out.println("1. Add method overloading:");
        System.out.println("Result: " + math.add(5, 3));                    // int, int
        System.out.println("Result: " + math.add(2, 4, 6));                // int, int, int
        System.out.println("Result: " + math.add(3.14, 2.86));             // double, double
        System.out.println("Result: " + math.add(new int[]{1, 2, 3, 4}));  // int[]
        System.out.println("Result: " + math.add("Hello", " World"));       // String, String

        System.out.println("\n2. Multiply method overloading:");
        System.out.println("Result: " + math.multiply(7));                  // int
        System.out.println("Result: " + math.multiply(4, 5));               // int, int
        System.out.println("Result: " + math.multiply(2.5, 3.0));           // double, double
        System.out.println("Result: " + math.multiply("Hi! ", 3));          // String, int
    }

    public static void demonstrateOverriding() {
        System.out.println("\n=== Method Overriding Demo ===\n");

        // Create different vehicles
        Vehicle[] vehicles = {
            new Car("Toyota", "Camry", 2023, 4, "Hybrid"),
            new Motorcycle("Harley", "Street 750", 2022, 750, false),
            new Car("Tesla", "Model S", 2024, 4, "Electric")
        };

        System.out.println("1. Polymorphic method calls (runtime resolution):");
        for (Vehicle vehicle : vehicles) {
            System.out.println("\n--- Testing " + vehicle.getFullName() + " ---");
            vehicle.start();        // Calls overridden method
            vehicle.accelerate();   // Calls overridden method
            vehicle.brake();        // Calls overridden method
            System.out.println();
        }

        System.out.println("2. Detailed vehicle information:");
        for (Vehicle vehicle : vehicles) {
            vehicle.displayInfo();  // Calls overridden method
            System.out.println();
        }

        System.out.println("3. Type-specific methods:");
        Car car = (Car) vehicles[0];
        car.openTrunk();

        Motorcycle bike = (Motorcycle) vehicles[1];
        bike.wheelie();

        System.out.println("\n4. Static method hiding:");
        Vehicle.showVehicleTypes();     // Parent static method
        Car.showVehicleTypes();         // Child static method (hiding)
    }

    public static void main(String[] args) {
        demonstrateOverloading();
        demonstrateOverriding();

        System.out.println("\n🏁 Demo completed!");
    }
}
```

### C++ Example - Comprehensive Overloading and Overriding

```cpp
#include <iostream>
#include <string>
#include <vector>
#include <memory>

// Method Overloading Example
class Calculator {
public:
    // Overloaded add functions - same name, different parameters

    // 1. Two integers
    int add(int a, int b) {
        std::cout << "Adding two integers: " << a << " + " << b << std::endl;
        return a + b;
    }

    // 2. Three integers
    int add(int a, int b, int c) {
        std::cout << "Adding three integers: " << a << " + " << b << " + " << c << std::endl;
        return a + b + c;
    }

    // 3. Two doubles
    double add(double a, double b) {
        std::cout << "Adding two doubles: " << a << " + " << b << std::endl;
        return a + b;
    }

    // 4. Vector of integers
    int add(const std::vector<int>& numbers) {
        std::cout << "Adding vector: ";
        for (int num : numbers) {
            std::cout << num << " ";
        }
        std::cout << std::endl;

        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        return sum;
    }

    // 5. String concatenation
    std::string add(const std::string& a, const std::string& b) {
        std::cout << "Concatenating strings: '" << a << "' + '" << b << "'" << std::endl;
        return a + b;
    }

    // Overloaded multiply functions

    int multiply(int a) {
        std::cout << "Squaring: " << a << "²" << std::endl;
        return a * a;
    }

    int multiply(int a, int b) {
        std::cout << "Multiplying two integers: " << a << " × " << b << std::endl;
        return a * b;
    }

    double multiply(double a, double b) {
        std::cout << "Multiplying two doubles: " << a << " × " << b << std::endl;
        return a * b;
    }

    // Operator overloading (special case of function overloading)
    Calculator operator+(const Calculator& other) {
        std::cout << "Adding two calculators (operator overloading)" << std::endl;
        return Calculator();
    }
};

// Method Overriding Example - Base class
class Animal {
protected:
    std::string name;
    std::string species;
    int age;

public:
    Animal(const std::string& name, const std::string& species, int age)
        : name(name), species(species), age(age) {
        std::cout << "🐾 Animal created: " << name << " (" << species << ")" << std::endl;
    }

    // Virtual destructor for proper cleanup
    virtual ~Animal() {
        std::cout << "🐾 Animal " << name << " destroyed" << std::endl;
    }

    // Pure virtual function - must be overridden
    virtual void makeSound() = 0;

    // Virtual function - can be overridden
    virtual void move() {
        std::cout << name << " is moving around" << std::endl;
    }

    // Virtual function - can be overridden
    virtual void eat() {
        std::cout << name << " is eating" << std::endl;
    }

    // Virtual function - can be overridden
    virtual void displayInfo() {
        std::cout << "\n=== Animal Information ===" << std::endl;
        std::cout << "Name: " << name << std::endl;
        std::cout << "Species: " << species << std::endl;
        std::cout << "Age: " << age << " years" << std::endl;
    }

    // Non-virtual function - cannot be overridden (but can be hidden)
    void sleep() {
        std::cout << name << " is sleeping... 😴" << std::endl;
    }

    // Static function - cannot be overridden
    static void showAnimalKingdom() {
        std::cout << "Animal Kingdom: Mammals, Birds, Reptiles, Fish, etc." << std::endl;
    }

    // Getters
    std::string getName() const { return name; }
    std::string getSpecies() const { return species; }
    int getAge() const { return age; }
};

// Derived class - Dog
class Dog : public Animal {
private:
    std::string breed;
    bool isTrained;

public:
    Dog(const std::string& name, const std::string& breed, int age, bool trained = false)
        : Animal(name, "Canine", age), breed(breed), isTrained(trained) {
        std::cout << "🐕 Dog created: " << name << " (" << breed << ")" << std::endl;
    }

    ~Dog() override {
        std::cout << "🐕 Dog " << name << " destroyed" << std::endl;
    }

    // Override pure virtual function
    void makeSound() override {
        std::cout << name << " barks: Woof! Woof! 🐕" << std::endl;
    }

    // Override virtual function
    void move() override {
        std::cout << name << " runs on four legs, tail wagging 🏃‍♂️" << std::endl;
    }

    // Override virtual function
    void eat() override {
        std::cout << name << " eats dog food from a bowl 🍖" << std::endl;
        Animal::eat();  // Call parent method too
    }

    // Override and extend virtual function
    void displayInfo() override {
        Animal::displayInfo();  // Call parent method first
        std::cout << "Breed: " << breed << std::endl;
        std::cout << "Trained: " << (isTrained ? "Yes" : "No") << std::endl;
        std::cout << "=========================" << std::endl;
    }

    // Dog-specific methods
    void fetch() {
        std::cout << name << " is fetching the ball! 🎾" << std::endl;
    }

    void bark(int times) {
        std::cout << name << " barks " << times << " times: ";
        for (int i = 0; i < times; ++i) {
            std::cout << "Woof! ";
        }
        std::cout << std::endl;
    }

    // Function hiding (not overriding - different signature)
    void sleep(int hours) {
        std::cout << name << " sleeps for " << hours << " hours 😴" << std::endl;
    }
};

// Derived class - Bird
class Bird : public Animal {
private:
    double wingspan;
    bool canFly;

public:
    Bird(const std::string& name, const std::string& species, int age,
         double wingspan, bool canFly = true)
        : Animal(name, species, age), wingspan(wingspan), canFly(canFly) {
        std::cout << "🐦 Bird created: " << name << " (" << species << ")" << std::endl;
    }

    ~Bird() override {
        std::cout << "🐦 Bird " << name << " destroyed" << std::endl;
    }

    void makeSound() override {
        std::cout << name << " chirps: Tweet! Tweet! 🐦" << std::endl;
    }

    void move() override {
        if (canFly) {
            std::cout << name << " flies through the air with " << wingspan
                      << "cm wingspan ✈️" << std::endl;
        } else {
            std::cout << name << " walks on the ground (flightless bird) 🚶‍♂️" << std::endl;
        }
    }

    void eat() override {
        std::cout << name << " pecks at seeds and insects 🌱" << std::endl;
    }

    void displayInfo() override {
        Animal::displayInfo();
        std::cout << "Wingspan: " << wingspan << " cm" << std::endl;
        std::cout << "Can Fly: " << (canFly ? "Yes" : "No") << std::endl;
        std::cout << "=========================" << std::endl;
    }

    void buildNest() {
        std::cout << name << " is building a nest 🏠" << std::endl;
    }

    void sing() {
        std::cout << name << " sings a beautiful melody 🎵" << std::endl;
    }
};

// Derived class - Fish
class Fish : public Animal {
private:
    std::string waterType;  // "freshwater" or "saltwater"
    double maxDepth;

public:
    Fish(const std::string& name, const std::string& species, int age,
         const std::string& waterType, double maxDepth)
        : Animal(name, species, age), waterType(waterType), maxDepth(maxDepth) {
        std::cout << "🐟 Fish created: " << name << " (" << species << ")" << std::endl;
    }

    ~Fish() override {
        std::cout << "🐟 Fish " << name << " destroyed" << std::endl;
    }

    void makeSound() override {
        std::cout << name << " makes bubbles: Blub! Blub! 🫧" << std::endl;
    }

    void move() override {
        std::cout << name << " swims gracefully through " << waterType
                  << " up to " << maxDepth << "m deep 🏊‍♂️" << std::endl;
    }

    void eat() override {
        std::cout << name << " eats plankton and smaller fish 🦐" << std::endl;
    }

    void displayInfo() override {
        Animal::displayInfo();
        std::cout << "Water Type: " << waterType << std::endl;
        std::cout << "Max Depth: " << maxDepth << " meters" << std::endl;
        std::cout << "=========================" << std::endl;
    }

    void swim(double depth) {
        if (depth <= maxDepth) {
            std::cout << name << " swims to " << depth << "m depth 🌊" << std::endl;
        } else {
            std::cout << name << " cannot swim that deep! Max: " << maxDepth << "m" << std::endl;
        }
    }
};

// Demonstration functions
void demonstrateOverloading() {
    std::cout << "=== Method Overloading Demo ===\n" << std::endl;

    Calculator calc;

    // Function overloading - compiler chooses based on parameters
    std::cout << "1. Add method overloading:" << std::endl;
    std::cout << "Result: " << calc.add(5, 3) << std::endl;                    // int, int
    std::cout << "Result: " << calc.add(2, 4, 6) << std::endl;                // int, int, int
    std::cout << "Result: " << calc.add(3.14, 2.86) << std::endl;             // double, double
    std::cout << "Result: " << calc.add(std::vector<int>{1, 2, 3, 4}) << std::endl; // vector
    std::cout << "Result: " << calc.add(std::string("Hello"), std::string(" World")) << std::endl; // string

    std::cout << "\n2. Multiply method overloading:" << std::endl;
    std::cout << "Result: " << calc.multiply(7) << std::endl;                  // int
    std::cout << "Result: " << calc.multiply(4, 5) << std::endl;               // int, int
    std::cout << "Result: " << calc.multiply(2.5, 3.0) << std::endl;           // double, double

    std::cout << "\n3. Operator overloading:" << std::endl;
    Calculator calc2;
    Calculator result = calc + calc2;  // Calls overloaded + operator
}

void demonstrateOverriding() {
    std::cout << "\n=== Method Overriding Demo ===\n" << std::endl;

    // Create different animals using polymorphism
    std::vector<std::unique_ptr<Animal>> animals;
    animals.push_back(std::make_unique<Dog>("Buddy", "Golden Retriever", 3, true));
    animals.push_back(std::make_unique<Bird>("Tweety", "Canary", 2, 15.5, true));
    animals.push_back(std::make_unique<Fish>("Nemo", "Clownfish", 1, "saltwater", 30.0));
    animals.push_back(std::make_unique<Bird>("Penguin", "Emperor Penguin", 5, 75.0, false));

    std::cout << "1. Polymorphic method calls (runtime resolution):" << std::endl;
    for (const auto& animal : animals) {
        std::cout << "\n--- Testing " << animal->getName() << " ---" << std::endl;
        animal->makeSound();    // Calls overridden method (virtual dispatch)
        animal->move();         // Calls overridden method
        animal->eat();          // Calls overridden method
        animal->sleep();        // Calls non-virtual method (same for all)
    }

    std::cout << "\n2. Detailed animal information:" << std::endl;
    for (const auto& animal : animals) {
        animal->displayInfo();  // Calls overridden method
    }

    std::cout << "3. Type-specific methods (after casting):" << std::endl;

    // Cast to specific types to access derived class methods
    Dog* dog = dynamic_cast<Dog*>(animals[0].get());
    if (dog) {
        dog->fetch();
        dog->bark(3);
        dog->sleep(8);  // Function hiding - different from Animal::sleep()
    }

    Bird* bird = dynamic_cast<Bird*>(animals[1].get());
    if (bird) {
        bird->buildNest();
        bird->sing();
    }

    Fish* fish = dynamic_cast<Fish*>(animals[2].get());
    if (fish) {
        fish->swim(15.0);
        fish->swim(50.0);  // Should fail - too deep
    }

    std::cout << "\n4. Static method (not overridden):" << std::endl;
    Animal::showAnimalKingdom();
}

void demonstrateVirtualTable() {
    std::cout << "\n=== Virtual Table Demo ===\n" << std::endl;

    std::cout << "Creating animals and showing virtual function calls:" << std::endl;

    // Base pointer to derived objects
    Animal* animals[] = {
        new Dog("Rex", "German Shepherd", 4, true),
        new Bird("Eagle", "Bald Eagle", 8, 220.0, true),
        new Fish("Shark", "Great White", 15, "saltwater", 1200.0)
    };

    std::cout << "\nCalling virtual functions through base pointers:" << std::endl;
    for (int i = 0; i < 3; ++i) {
        std::cout << "\nAnimal " << (i + 1) << ":" << std::endl;
        animals[i]->makeSound();  // Virtual dispatch to correct derived method
        animals[i]->move();       // Virtual dispatch to correct derived method
    }

    // Clean up
    for (int i = 0; i < 3; ++i) {
        delete animals[i];  // Virtual destructor ensures proper cleanup
    }
}

int main() {
    std::cout << "=== C++ Method Overloading and Overriding Demo ===\n" << std::endl;

    demonstrateOverloading();
    demonstrateOverriding();
    demonstrateVirtualTable();

    std::cout << "\n🏁 Demo completed!" << std::endl;

    return 0;
}
```

## Key Differences Summary

| Aspect              | Method Overloading              | Method Overriding                          |
| ------------------- | ------------------------------- | ------------------------------------------ |
| **Definition**      | Same name, different parameters | Same signature, different implementation   |
| **Relationship**    | Same class or inheritance       | Inheritance required                       |
| **Resolution**      | Compile-time (static)           | Runtime (dynamic)                          |
| **Parameters**      | Must be different               | Must be same                               |
| **Return Type**     | Can be different                | Must be same (covariant in some languages) |
| **Access Modifier** | Can be different                | Cannot be more restrictive                 |
| **Performance**     | Faster (compile-time)           | Slightly slower (virtual dispatch)         |

## Best Practices

### Method Overloading

1. **Make parameter differences clear**

   ```cpp
   // Good - clear parameter differences
   void process(int value);
   void process(string text);
   void process(vector<int> data);

   // Bad - confusing parameter differences
   void process(int a, double b);
   void process(double a, int b);  // Easy to mix up
   ```

2. **Use default parameters instead of overloading when appropriate**

   ```cpp
   // Instead of multiple overloads
   void connect(string host);
   void connect(string host, int port);
   void connect(string host, int port, string user);

   // Use default parameters
   void connect(string host, int port = 80, string user = "guest");
   ```

3. **Avoid overloading with similar parameter types**
   ```cpp
   // Avoid this - too similar
   void setValue(int value);
   void setValue(long value);
   void setValue(short value);
   ```

### Method Overriding

1. **Use virtual destructors in base classes**

   ```cpp
   class Base {
   public:
       virtual ~Base() = default;  // Essential for proper cleanup
   };
   ```

2. **Use override keyword (C++11)**

   ```cpp
   class Derived : public Base {
   public:
       void someMethod() override;  // Compiler checks override is valid
   };
   ```

3. **Call parent methods when extending functionality**

   ```python
   class Child(Parent):
       def method(self):
           super().method()  # Call parent first
           # Add child-specific behavior
   ```

4. **Don't change method contracts in overrides**

   ```python
   # Bad - changes expected behavior
   class Parent:
       def divide(self, a, b):
           return a / b  # Can raise ZeroDivisionError

   class Child(Parent):
       def divide(self, a, b):
           return 0 if b == 0 else a / b  # Changes contract!
   ```

## Common Pitfalls

❌ **Overloading vs Overriding confusion:**

```cpp
class Base {
public:
    virtual void method(int x) { }
};

class Derived : public Base {
public:
    void method(double x) { }  // This is HIDING, not overriding!
};
```

❌ **Forgetting virtual keyword:**

```cpp
class Base {
public:
    void method() { }  // Not virtual - cannot be overridden
};

class Derived : public Base {
public:
    void method() { }  // This HIDES the base method
};
```

❌ **Overloading with only return type difference:**

```cpp
// This won't compile - return type alone cannot distinguish overloads
int getValue();
string getValue();  // Error!
```

## Summary

Method overloading and overriding are powerful features that enable flexible and extensible code design. Overloading provides multiple ways to call the same logical operation with different parameters, while overriding allows specialized implementations in derived classes.

**Key Takeaways:**

- **Overloading**: Same name, different parameters, compile-time resolution
- **Overriding**: Same signature, different implementation, runtime resolution
- Use overloading for convenience and flexibility
- Use overriding for polymorphism and specialization
- Always consider the Liskov Substitution Principle when overriding

Master these concepts to write more maintainable and flexible object-oriented code! 🎯
