# Encapsulation 🔒

## What is Encapsulation?

Encapsulation is the bundling of data (attributes) and methods (functions) that operate on that data into a single unit (class), while restricting direct access to some of the object's components. Think of it like a capsule that protects the medicine inside.

## Real-World Analogy

Imagine a **car**:

- You can use the steering wheel, pedals, and gear shift (public interface)
- You cannot directly access the engine internals, fuel injection system, or transmission (private implementation)
- The car's internal systems are protected from external interference

## Key Benefits

- **Data Protection**: Prevents unauthorized access and modification
- **Modularity**: Changes to internal implementation don't affect external code
- **Maintainability**: Easier to debug and modify code
- **Security**: Sensitive data remains hidden

## Implementation Examples

### Python Example

```python
class BankAccount:
    def __init__(self, account_number, initial_balance):
        self.account_number = account_number  # Public
        self.__balance = initial_balance      # Private (name mangling)
        self._transaction_history = []        # Protected (convention)

    # Public method to access private balance
    def get_balance(self):
        return self.__balance

    # Public method to modify private balance safely
    def deposit(self, amount):
        if amount > 0:
            self.__balance += amount
            self._transaction_history.append(f"Deposited: ${amount}")
            return True
        return False

    def withdraw(self, amount):
        if 0 < amount <= self.__balance:
            self.__balance -= amount
            self._transaction_history.append(f"Withdrew: ${amount}")
            return True
        return False

    # Private method (internal use only)
    def __validate_transaction(self, amount):
        return amount > 0 and amount <= self.__balance

# Usage
account = BankAccount("12345", 1000)
print(account.get_balance())  # 1000
account.deposit(500)
print(account.get_balance())  # 1500

# This would cause an AttributeError (encapsulation working!)
# print(account.__balance)  # Error!
```

### Java Example

```java
public class Student {
    // Private fields (encapsulated data)
    private String name;
    private int age;
    private double gpa;

    // Constructor
    public Student(String name, int age, double gpa) {
        this.name = name;
        setAge(age);  // Use setter for validation
        setGpa(gpa);  // Use setter for validation
    }

    // Public getter methods (controlled access)
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getGpa() {
        return gpa;
    }

    // Public setter methods (controlled modification)
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        }
    }

    public void setAge(int age) {
        if (age >= 0 && age <= 150) {
            this.age = age;
        }
    }

    public void setGpa(double gpa) {
        if (gpa >= 0.0 && gpa <= 4.0) {
            this.gpa = gpa;
        }
    }

    // Private helper method
    private boolean isValidGpa(double gpa) {
        return gpa >= 0.0 && gpa <= 4.0;
    }
}

// Usage
Student student = new Student("Alice", 20, 3.8);
System.out.println(student.getName()); // Alice
student.setAge(21);  // Valid
student.setAge(-5);  // Invalid - won't change age
```

### C++ Example

```cpp
#include <iostream>
#include <string>

class Rectangle {
private:
    double width;
    double height;

public:
    // Constructor
    Rectangle(double w, double h) {
        setWidth(w);
        setHeight(h);
    }

    // Public getters
    double getWidth() const {
        return width;
    }

    double getHeight() const {
        return height;
    }

    // Public setters with validation
    void setWidth(double w) {
        if (w > 0) {
            width = w;
        }
    }

    void setHeight(double h) {
        if (h > 0) {
            height = h;
        }
    }

    // Public methods using private data
    double getArea() const {
        return width * height;
    }

    double getPerimeter() const {
        return 2 * (width + height);
    }
};

// Usage
int main() {
    Rectangle rect(5.0, 3.0);
    std::cout << "Area: " << rect.getArea() << std::endl;  // 15

    rect.setWidth(10.0);
    std::cout << "New Area: " << rect.getArea() << std::endl;  // 30

    return 0;
}
```

## Access Modifiers

| Modifier      | Description                            | Access Level                |
| ------------- | -------------------------------------- | --------------------------- |
| **Public**    | Accessible from anywhere               | External classes can access |
| **Private**   | Accessible only within the same class  | Most restrictive            |
| **Protected** | Accessible within class and subclasses | Inheritance-friendly        |

## Best Practices

1. **Make fields private by default**
2. **Provide public getters/setters when needed**
3. **Validate data in setters**
4. **Use meaningful method names**
5. **Keep the public interface minimal**
6. **Document your public methods**

## Common Pitfalls

❌ **Don't do this:**

```python
class BadExample:
    def __init__(self):
        self.important_data = "sensitive"  # Public - anyone can modify!

bad = BadExample()
bad.important_data = "hacked!"  # Oops!
```

✅ **Do this instead:**

```python
class GoodExample:
    def __init__(self):
        self.__important_data = "sensitive"  # Private

    def get_data(self):
        return self.__important_data

    def set_data(self, value):
        if self.__is_valid(value):
            self.__important_data = value

    def __is_valid(self, value):
        return isinstance(value, str) and len(value) > 0
```

## Summary

Encapsulation is about creating a protective barrier around your data and providing controlled access through well-defined interfaces. It's like having a security guard for your code - only authorized operations are allowed!

**Remember**: Good encapsulation leads to more robust, maintainable, and secure code. 🛡️
