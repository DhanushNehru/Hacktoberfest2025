# Classes and Objects 🏗️

## What are Classes and Objects?

**Class**: A blueprint or template that defines the structure and behavior of objects. Think of it as a cookie cutter.

**Object**: An instance of a class - the actual "thing" created from the blueprint. Think of it as the actual cookie made from the cutter.

## Real-World Analogy

**Class = House Blueprint**

- Defines rooms, doors, windows
- Specifies materials and layout
- Shows how to build the house

**Object = Actual House**

- Built using the blueprint
- Has specific address, color, furniture
- Multiple houses can be built from same blueprint

## Key Components of a Class

### 1. **Attributes (Properties/Fields)**

Data that describes the object's state.

### 2. **Methods (Functions)**

Actions that the object can perform.

### 3. **Constructor**

Special method that initializes new objects.

### 4. **Access Modifiers**

Control who can access what (public, private, protected).

## Implementation Examples

### Python Example

```python
class Student:
    # Class variable (shared by all instances)
    school_name = "Tech University"
    total_students = 0

    def __init__(self, name, age, student_id):
        # Instance variables (unique to each object)
        self.name = name
        self.age = age
        self.student_id = student_id
        self.grades = []
        self.is_enrolled = True

        # Update class variable
        Student.total_students += 1

    # Instance methods
    def add_grade(self, subject, grade):
        """Add a grade for a subject"""
        self.grades.append({"subject": subject, "grade": grade})
        print(f"Added grade {grade} for {subject} to {self.name}")

    def get_average_grade(self):
        """Calculate average grade"""
        if not self.grades:
            return 0
        total = sum(grade["grade"] for grade in self.grades)
        return total / len(self.grades)

    def display_info(self):
        """Display student information"""
        print(f"\n--- Student Information ---")
        print(f"Name: {self.name}")
        print(f"Age: {self.age}")
        print(f"ID: {self.student_id}")
        print(f"School: {Student.school_name}")
        print(f"Enrolled: {self.is_enrolled}")
        print(f"Average Grade: {self.get_average_grade():.2f}")
        print(f"Grades: {self.grades}")

    def graduate(self):
        """Graduate the student"""
        if self.is_enrolled and self.get_average_grade() >= 60:
            self.is_enrolled = False
            print(f"🎓 Congratulations! {self.name} has graduated!")
            return True
        else:
            print(f"❌ {self.name} cannot graduate yet.")
            return False

    # Class method (works with class, not instance)
    @classmethod
    def get_total_students(cls):
        return cls.total_students

    @classmethod
    def create_honor_student(cls, name, age):
        """Factory method to create honor student"""
        student_id = f"HON{cls.total_students + 1:03d}"
        student = cls(name, age, student_id)
        student.add_grade("Mathematics", 95)
        student.add_grade("Science", 98)
        return student

    # Static method (utility function)
    @staticmethod
    def is_valid_grade(grade):
        return 0 <= grade <= 100

    # String representation
    def __str__(self):
        return f"Student({self.name}, ID: {self.student_id})"

    def __repr__(self):
        return f"Student('{self.name}', {self.age}, '{self.student_id}')"

# Creating and using objects
def main():
    print("=== Creating Student Objects ===")

    # Create individual students
    alice = Student("Alice Johnson", 20, "STU001")
    bob = Student("Bob Smith", 19, "STU002")
    charlie = Student("Charlie Brown", 21, "STU003")

    # Create honor student using class method
    diana = Student.create_honor_student("Diana Prince", 20)

    print(f"Total students created: {Student.get_total_students()}")

    # Add grades to students
    alice.add_grade("Mathematics", 85)
    alice.add_grade("Physics", 92)
    alice.add_grade("Chemistry", 78)

    bob.add_grade("Mathematics", 76)
    bob.add_grade("Physics", 68)
    bob.add_grade("Chemistry", 82)

    charlie.add_grade("Mathematics", 45)
    charlie.add_grade("Physics", 52)

    # Display student information
    students = [alice, bob, charlie, diana]

    for student in students:
        student.display_info()

        # Try to graduate each student
        student.graduate()
        print("-" * 40)

    # Demonstrate static method
    print(f"Is 85 a valid grade? {Student.is_valid_grade(85)}")
    print(f"Is 150 a valid grade? {Student.is_valid_grade(150)}")

    # Show string representations
    print(f"\nString representation: {alice}")
    print(f"Repr representation: {repr(bob)}")

if __name__ == "__main__":
    main()
```

### Java Example

```java
public class BankAccount {
    // Class variables (static)
    private static int totalAccounts = 0;
    private static final double MINIMUM_BALANCE = 100.0;

    // Instance variables
    private String accountNumber;
    private String accountHolder;
    private double balance;
    private String accountType;
    private boolean isActive;

    // Constructor
    public BankAccount(String accountHolder, String accountType, double initialDeposit) {
        this.accountNumber = generateAccountNumber();
        this.accountHolder = accountHolder;
        this.accountType = accountType;
        this.balance = initialDeposit;
        this.isActive = true;

        totalAccounts++;

        System.out.println("Account created for " + accountHolder);
        System.out.println("Account Number: " + accountNumber);
    }

    // Overloaded constructor
    public BankAccount(String accountHolder, String accountType) {
        this(accountHolder, accountType, MINIMUM_BALANCE);
    }

    // Instance methods
    public boolean deposit(double amount) {
        if (amount > 0 && isActive) {
            balance += amount;
            System.out.println("Deposited $" + amount + ". New balance: $" + balance);
            return true;
        }
        System.out.println("Invalid deposit amount or account inactive");
        return false;
    }

    public boolean withdraw(double amount) {
        if (!isActive) {
            System.out.println("Account is inactive");
            return false;
        }

        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew $" + amount + ". New balance: $" + balance);
            return true;
        }

        System.out.println("Invalid withdrawal amount or insufficient funds");
        return false;
    }

    public void transfer(BankAccount targetAccount, double amount) {
        if (this.withdraw(amount)) {
            if (targetAccount.deposit(amount)) {
                System.out.println("Transfer successful to " + targetAccount.getAccountHolder());
            } else {
                // Rollback if target deposit fails
                this.deposit(amount);
                System.out.println("Transfer failed - amount returned");
            }
        }
    }

    public void displayAccountInfo() {
        System.out.println("\n=== Account Information ===");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Type: " + accountType);
        System.out.println("Balance: $" + String.format("%.2f", balance));
        System.out.println("Status: " + (isActive ? "Active" : "Inactive"));
        System.out.println("==========================");
    }

    public void closeAccount() {
        if (balance > 0) {
            System.out.println("Cannot close account with remaining balance: $" + balance);
            return;
        }

        isActive = false;
        System.out.println("Account " + accountNumber + " has been closed");
    }

    // Getters
    public String getAccountNumber() { return accountNumber; }
    public String getAccountHolder() { return accountHolder; }
    public double getBalance() { return balance; }
    public String getAccountType() { return accountType; }
    public boolean isActive() { return isActive; }

    // Setters (with validation)
    public void setAccountHolder(String accountHolder) {
        if (accountHolder != null && !accountHolder.trim().isEmpty()) {
            this.accountHolder = accountHolder;
        }
    }

    // Static methods
    public static int getTotalAccounts() {
        return totalAccounts;
    }

    public static double getMinimumBalance() {
        return MINIMUM_BALANCE;
    }

    // Private helper method
    private String generateAccountNumber() {
        return "ACC" + String.format("%06d", totalAccounts + 1);
    }

    // Override toString for better object representation
    @Override
    public String toString() {
        return String.format("BankAccount[%s, %s, $%.2f]",
                           accountNumber, accountHolder, balance);
    }
}

// Usage class
public class BankingSystem {
    public static void main(String[] args) {
        System.out.println("=== Banking System Demo ===\n");

        // Create bank accounts
        BankAccount alice = new BankAccount("Alice Johnson", "Checking", 1500.0);
        BankAccount bob = new BankAccount("Bob Smith", "Savings", 2000.0);
        BankAccount charlie = new BankAccount("Charlie Brown", "Checking"); // Uses minimum balance

        System.out.println("\nTotal accounts created: " + BankAccount.getTotalAccounts());
        System.out.println("Minimum balance required: $" + BankAccount.getMinimumBalance());

        // Perform banking operations
        System.out.println("\n=== Banking Operations ===");

        alice.deposit(500);
        alice.withdraw(200);

        bob.deposit(1000);
        bob.withdraw(500);

        charlie.deposit(50);
        charlie.withdraw(200); // Should fail

        // Transfer money
        System.out.println("\n=== Transfer Operations ===");
        alice.transfer(bob, 300);
        bob.transfer(charlie, 150);

        // Display all account information
        System.out.println("\n=== Final Account Status ===");
        alice.displayAccountInfo();
        bob.displayAccountInfo();
        charlie.displayAccountInfo();

        // Try to close accounts
        System.out.println("\n=== Account Closure ===");
        charlie.withdraw(charlie.getBalance()); // Withdraw all money
        charlie.closeAccount(); // Now can close

        alice.closeAccount(); // Should fail - has balance

        // Show object string representation
        System.out.println("\n=== Object Representations ===");
        System.out.println("Alice's account: " + alice);
        System.out.println("Bob's account: " + bob);
        System.out.println("Charlie's account: " + charlie);
    }
}
```

### C++ Example

```cpp
#include <iostream>
#include <string>
#include <vector>
#include <iomanip>

class Car {
private:
    // Private member variables
    std::string make;
    std::string model;
    int year;
    double mileage;
    double fuelLevel;
    double fuelCapacity;
    bool isRunning;

    // Static member (shared by all instances)
    static int totalCarsProduced;

public:
    // Constructor with parameters
    Car(const std::string& make, const std::string& model, int year, double fuelCapacity = 50.0)
        : make(make), model(model), year(year), fuelCapacity(fuelCapacity) {
        mileage = 0.0;
        fuelLevel = fuelCapacity; // Start with full tank
        isRunning = false;
        totalCarsProduced++;

        std::cout << "Car created: " << year << " " << make << " " << model << std::endl;
    }

    // Copy constructor
    Car(const Car& other)
        : make(other.make), model(other.model), year(other.year),
          mileage(other.mileage), fuelLevel(other.fuelLevel),
          fuelCapacity(other.fuelCapacity), isRunning(false) {
        totalCarsProduced++;
        std::cout << "Car copied: " << getFullName() << std::endl;
    }

    // Destructor
    ~Car() {
        std::cout << "Car destroyed: " << getFullName() << std::endl;
    }

    // Public member functions
    void startEngine() {
        if (fuelLevel > 0 && !isRunning) {
            isRunning = true;
            std::cout << "🚗 " << getFullName() << " engine started!" << std::endl;
        } else if (fuelLevel <= 0) {
            std::cout << "❌ Cannot start - no fuel!" << std::endl;
        } else {
            std::cout << "⚠️ Engine is already running!" << std::endl;
        }
    }

    void stopEngine() {
        if (isRunning) {
            isRunning = false;
            std::cout << "🛑 " << getFullName() << " engine stopped." << std::endl;
        } else {
            std::cout << "⚠️ Engine is already off!" << std::endl;
        }
    }

    void drive(double distance) {
        if (!isRunning) {
            std::cout << "❌ Cannot drive - engine is off!" << std::endl;
            return;
        }

        double fuelNeeded = distance * 0.1; // 0.1 gallons per mile

        if (fuelNeeded > fuelLevel) {
            double maxDistance = fuelLevel / 0.1;
            std::cout << "⛽ Not enough fuel! Can only drive " << maxDistance << " miles." << std::endl;
            mileage += maxDistance;
            fuelLevel = 0;
            stopEngine();
        } else {
            mileage += distance;
            fuelLevel -= fuelNeeded;
            std::cout << "🛣️ Drove " << distance << " miles. Total mileage: " << mileage << std::endl;
        }
    }

    void refuel(double gallons) {
        double spaceAvailable = fuelCapacity - fuelLevel;
        double actualFuel = std::min(gallons, spaceAvailable);

        fuelLevel += actualFuel;
        std::cout << "⛽ Added " << actualFuel << " gallons. Fuel level: "
                  << fuelLevel << "/" << fuelCapacity << std::endl;

        if (actualFuel < gallons) {
            std::cout << "⚠️ Tank is full! " << (gallons - actualFuel) << " gallons overflow." << std::endl;
        }
    }

    void displayInfo() const {
        std::cout << "\n=== Car Information ===" << std::endl;
        std::cout << "Make: " << make << std::endl;
        std::cout << "Model: " << model << std::endl;
        std::cout << "Year: " << year << std::endl;
        std::cout << "Mileage: " << std::fixed << std::setprecision(1) << mileage << " miles" << std::endl;
        std::cout << "Fuel: " << std::fixed << std::setprecision(1) << fuelLevel
                  << "/" << fuelCapacity << " gallons" << std::endl;
        std::cout << "Engine: " << (isRunning ? "Running" : "Off") << std::endl;
        std::cout << "======================" << std::endl;
    }

    // Getter methods (const because they don't modify the object)
    std::string getMake() const { return make; }
    std::string getModel() const { return model; }
    int getYear() const { return year; }
    double getMileage() const { return mileage; }
    double getFuelLevel() const { return fuelLevel; }
    bool getIsRunning() const { return isRunning; }

    std::string getFullName() const {
        return std::to_string(year) + " " + make + " " + model;
    }

    // Setter methods (with validation)
    void setMake(const std::string& newMake) {
        if (!newMake.empty()) {
            make = newMake;
        }
    }

    void setModel(const std::string& newModel) {
        if (!newModel.empty()) {
            model = newModel;
        }
    }

    // Static method
    static int getTotalCarsProduced() {
        return totalCarsProduced;
    }

    // Operator overloading for comparison
    bool operator<(const Car& other) const {
        return mileage < other.mileage;
    }

    // Friend function for output stream
    friend std::ostream& operator<<(std::ostream& os, const Car& car) {
        os << car.getFullName() << " (Mileage: " << car.mileage << ")";
        return os;
    }
};

// Initialize static member
int Car::totalCarsProduced = 0;

// Utility function to demonstrate object usage
void carRoadTrip(Car& car, const std::vector<double>& distances) {
    std::cout << "\n🗺️ Starting road trip with " << car.getFullName() << std::endl;

    car.startEngine();

    for (size_t i = 0; i < distances.size(); ++i) {
        std::cout << "\nLeg " << (i + 1) << ": ";
        car.drive(distances[i]);

        // Refuel if fuel is low
        if (car.getFuelLevel() < 5.0) {
            std::cout << "🚨 Low fuel warning!" << std::endl;
            car.refuel(20.0);
        }
    }

    car.stopEngine();
    std::cout << "🏁 Road trip completed!" << std::endl;
}

// Main function demonstrating class usage
int main() {
    std::cout << "=== Car Dealership Demo ===" << std::endl;

    // Create car objects
    Car car1("Toyota", "Camry", 2023);
    Car car2("Honda", "Civic", 2022, 45.0); // Custom fuel capacity
    Car car3("Ford", "Mustang", 2024, 60.0);

    std::cout << "\nTotal cars produced: " << Car::getTotalCarsProduced() << std::endl;

    // Display initial information
    car1.displayInfo();
    car2.displayInfo();
    car3.displayInfo();

    // Test car operations
    std::cout << "\n=== Testing Car Operations ===" << std::endl;

    // Road trip with car1
    std::vector<double> tripDistances = {50.0, 75.0, 100.0, 25.0};
    carRoadTrip(car1, tripDistances);

    // Manual operations with car2
    std::cout << "\n=== Manual Operations ===" << std::endl;
    car2.startEngine();
    car2.drive(200.0);
    car2.drive(300.0); // Should run out of fuel
    car2.refuel(50.0);
    car2.startEngine();
    car2.drive(100.0);
    car2.stopEngine();

    // Copy constructor demonstration
    std::cout << "\n=== Copy Constructor Demo ===" << std::endl;
    Car car4 = car1; // Copy constructor called

    std::cout << "\nOriginal car: " << car1 << std::endl;
    std::cout << "Copied car: " << car4 << std::endl;

    // Final status
    std::cout << "\n=== Final Car Status ===" << std::endl;
    car1.displayInfo();
    car2.displayInfo();
    car3.displayInfo();
    car4.displayInfo();

    std::cout << "\nTotal cars produced: " << Car::getTotalCarsProduced() << std::endl;

    return 0;
} // Destructors called automatically when objects go out of scope
```

## Key Concepts Explained

### 1. **Class vs Object**

```python
# Class - Blueprint
class Dog:
    def __init__(self, name, breed):
        self.name = name
        self.breed = breed

    def bark(self):
        print(f"{self.name} says Woof!")

# Objects - Instances
buddy = Dog("Buddy", "Golden Retriever")  # Object 1
max_dog = Dog("Max", "German Shepherd")   # Object 2
```

### 2. **Instance vs Class Variables**

```python
class Counter:
    total_count = 0  # Class variable (shared)

    def __init__(self, name):
        self.name = name        # Instance variable (unique)
        Counter.total_count += 1  # Modify class variable

c1 = Counter("First")
c2 = Counter("Second")
print(Counter.total_count)  # 2 (shared by all)
print(c1.name)             # "First" (unique to c1)
```

### 3. **Method Types**

```python
class Example:
    class_var = "shared"

    def __init__(self, value):
        self.instance_var = value

    def instance_method(self):      # Works with instance
        return self.instance_var

    @classmethod
    def class_method(cls):          # Works with class
        return cls.class_var

    @staticmethod
    def static_method(x, y):        # Utility function
        return x + y
```

## Best Practices

1. **Use meaningful class and variable names**

   ```python
   # Good
   class BankAccount:
       def __init__(self, account_number, balance):

   # Bad
   class BA:
       def __init__(self, an, bal):
   ```

2. **Keep classes focused (Single Responsibility)**

   ```python
   # Good - focused on one responsibility
   class EmailSender:
       def send_email(self, to, subject, body): pass

   # Bad - too many responsibilities
   class EmailSenderAndDatabaseAndLogger:
       def send_email(self): pass
       def save_to_db(self): pass
       def log_message(self): pass
   ```

3. **Use proper encapsulation**

   ```python
   class GoodClass:
       def __init__(self):
           self._protected = "internal use"
           self.__private = "very internal"
           self.public = "everyone can see"
   ```

4. **Provide meaningful string representations**
   ```python
   class Person:
       def __str__(self):  # For end users
           return f"{self.name} ({self.age} years old)"

       def __repr__(self): # For developers
           return f"Person('{self.name}', {self.age})"
   ```

## Common Pitfalls

❌ **Mutable default arguments:**

```python
class BadClass:
    def __init__(self, items=[]):  # Don't do this!
        self.items = items

# All instances share the same list!
```

✅ **Use None and create new objects:**

```python
class GoodClass:
    def __init__(self, items=None):
        self.items = items if items is not None else []
```

❌ **Forgetting to use self:**

```python
class BadClass:
    def __init__(self, name):
        name = name  # Wrong! Should be self.name = name
```

❌ **Making everything public:**

```python
class BadClass:
    def __init__(self):
        self.internal_counter = 0      # Should be private
        self.secret_key = "abc123"     # Definitely should be private
```

## Summary

Classes and objects are the foundation of OOP. Classes define the structure and behavior, while objects are the actual instances that hold data and can perform actions. Understanding this relationship is crucial for writing effective object-oriented code.

**Remember**: A class is like a blueprint, and objects are the houses built from that blueprint. Each house (object) has its own address, color, and furniture (data), but they all follow the same basic structure (class definition). 🏠
