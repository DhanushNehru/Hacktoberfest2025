# Static vs Instance Members 🏗️⚡

## What are Static and Instance Members?

**Instance Members**: Belong to individual objects. Each object has its own copy.

**Static Members**: Belong to the class itself. Shared by all instances of the class.

## Real-World Analogy

**Instance Members = Personal Belongings**

- Each student has their own name, age, grades
- John's grade doesn't affect Mary's grade
- Each person has individual characteristics

**Static Members = School Resources**

- School name is same for all students
- Total student count affects everyone
- Library rules apply to all students
- School building is shared by everyone

## Key Differences

| Aspect             | Instance Members               | Static Members       |
| ------------------ | ------------------------------ | -------------------- |
| **Ownership**      | Belongs to object              | Belongs to class     |
| **Memory**         | One copy per object            | One copy total       |
| **Access**         | Through object                 | Through class name   |
| **Lifetime**       | Object creation to destruction | Program start to end |
| **Initialization** | In constructor                 | At class loading     |

## Implementation Examples

### Python Example

```python
import datetime
from typing import List, ClassVar

class Employee:
    """Demonstrates static vs instance members"""

    # Static/Class variables (shared by all instances)
    company_name: ClassVar[str] = "TechCorp Solutions"
    total_employees: ClassVar[int] = 0
    company_founded: ClassVar[int] = 2010
    salary_grades: ClassVar[dict] = {
        "Junior": 50000,
        "Mid": 75000,
        "Senior": 100000,
        "Lead": 125000
    }
    all_employees: ClassVar[List['Employee']] = []

    def __init__(self, name: str, employee_id: str, grade: str, department: str):
        """Constructor - initializes instance variables"""

        # Instance variables (unique to each object)
        self.name = name
        self.employee_id = employee_id
        self.grade = grade
        self.department = department
        self.hire_date = datetime.date.today()
        self.is_active = True
        self.projects = []
        self.performance_ratings = []

        # Update static variables
        Employee.total_employees += 1
        Employee.all_employees.append(self)

        print(f"👤 Employee created: {self.name} (ID: {self.employee_id})")
        print(f"   Grade: {self.grade}, Department: {self.department}")
        print(f"   Total employees now: {Employee.total_employees}")

    # Instance methods (work with individual objects)
    def get_salary(self) -> int:
        """Get salary based on grade - instance method"""
        return Employee.salary_grades.get(self.grade, 0)

    def add_project(self, project_name: str) -> None:
        """Add project to employee - instance method"""
        self.projects.append(project_name)
        print(f"📋 {self.name} assigned to project: {project_name}")

    def add_performance_rating(self, rating: float) -> None:
        """Add performance rating - instance method"""
        if 1.0 <= rating <= 5.0:
            self.performance_ratings.append(rating)
            print(f"⭐ {self.name} received rating: {rating}/5.0")
        else:
            print("❌ Invalid rating! Must be between 1.0 and 5.0")

    def get_average_rating(self) -> float:
        """Calculate average performance rating - instance method"""
        if not self.performance_ratings:
            return 0.0
        return sum(self.performance_ratings) / len(self.performance_ratings)

    def promote(self) -> bool:
        """Promote employee to next grade - instance method"""
        grade_progression = ["Junior", "Mid", "Senior", "Lead"]
        current_index = grade_progression.index(self.grade)

        if current_index < len(grade_progression) - 1:
            old_grade = self.grade
            self.grade = grade_progression[current_index + 1]
            print(f"🎉 {self.name} promoted from {old_grade} to {self.grade}!")
            print(f"   New salary: ${self.get_salary():,}")
            return True
        else:
            print(f"❌ {self.name} is already at the highest grade!")
            return False

    def display_info(self) -> None:
        """Display employee information - instance method"""
        print(f"\n👤 === Employee Information ===")
        print(f"Name: {self.name}")
        print(f"ID: {self.employee_id}")
        print(f"Grade: {self.grade}")
        print(f"Department: {self.department}")
        print(f"Salary: ${self.get_salary():,}")
        print(f"Hire Date: {self.hire_date}")
        print(f"Status: {'Active' if self.is_active else 'Inactive'}")
        print(f"Projects: {', '.join(self.projects) if self.projects else 'None'}")
        print(f"Avg Rating: {self.get_average_rating():.2f}/5.0")
        print(f"Company: {Employee.company_name}")  # Accessing static variable
        print("=" * 35)

    # Class methods (work with class, not instances)
    @classmethod
    def get_company_info(cls) -> dict:
        """Get company information - class method"""
        return {
            "name": cls.company_name,
            "founded": cls.company_founded,
            "total_employees": cls.total_employees,
            "years_in_business": datetime.date.today().year - cls.company_founded
        }

    @classmethod
    def create_intern(cls, name: str, employee_id: str, department: str):
        """Factory method to create intern - class method"""
        intern = cls(name, employee_id, "Junior", department)
        intern.add_project("Orientation Program")
        return intern

    @classmethod
    def get_employees_by_department(cls, department: str) -> List['Employee']:
        """Get all employees in a department - class method"""
        return [emp for emp in cls.all_employees if emp.department == department]

    @classmethod
    def get_employees_by_grade(cls, grade: str) -> List['Employee']:
        """Get all employees of a specific grade - class method"""
        return [emp for emp in cls.all_employees if emp.grade == grade]

    @classmethod
    def calculate_total_payroll(cls) -> int:
        """Calculate total company payroll - class method"""
        return sum(emp.get_salary() for emp in cls.all_employees if emp.is_active)

    @classmethod
    def display_company_stats(cls) -> None:
        """Display company statistics - class method"""
        info = cls.get_company_info()
        print(f"\n🏢 === Company Statistics ===")
        print(f"Company: {info['name']}")
        print(f"Founded: {info['founded']} ({info['years_in_business']} years ago)")
        print(f"Total Employees: {info['total_employees']}")
        print(f"Total Payroll: ${cls.calculate_total_payroll():,}")

        # Department breakdown
        departments = {}
        for emp in cls.all_employees:
            departments[emp.department] = departments.get(emp.department, 0) + 1

        print("Department Breakdown:")
        for dept, count in departments.items():
            print(f"  {dept}: {count} employees")

        # Grade breakdown
        grades = {}
        for emp in cls.all_employees:
            grades[emp.grade] = grades.get(emp.grade, 0) + 1

        print("Grade Breakdown:")
        for grade, count in grades.items():
            print(f"  {grade}: {count} employees")
        print("=" * 35)

    # Static methods (utility functions, don't need class or instance)
    @staticmethod
    def is_valid_employee_id(employee_id: str) -> bool:
        """Validate employee ID format - static method"""
        # Format: EMP followed by 4 digits
        return (len(employee_id) == 7 and
                employee_id.startswith("EMP") and
                employee_id[3:].isdigit())

    @staticmethod
    def calculate_years_of_service(hire_date: datetime.date) -> int:
        """Calculate years of service - static method"""
        today = datetime.date.today()
        return today.year - hire_date.year - ((today.month, today.day) < (hire_date.month, hire_date.day))

    @staticmethod
    def format_salary(salary: int) -> str:
        """Format salary for display - static method"""
        return f"${salary:,}"

    @staticmethod
    def get_grade_requirements() -> dict:
        """Get requirements for each grade - static method"""
        return {
            "Junior": "0-2 years experience",
            "Mid": "2-5 years experience",
            "Senior": "5-10 years experience",
            "Lead": "10+ years experience"
        }

    # Special methods
    def __str__(self) -> str:
        return f"Employee({self.name}, {self.employee_id}, {self.grade})"

    def __repr__(self) -> str:
        return f"Employee('{self.name}', '{self.employee_id}', '{self.grade}', '{self.department}')"

def demonstrate_static_vs_instance():
    """Demonstrate the differences between static and instance members"""

    print("=== Static vs Instance Members Demo ===\n")

    # Show initial class state
    print("1. Initial class state (before creating any objects):")
    print(f"Company name: {Employee.company_name}")
    print(f"Total employees: {Employee.total_employees}")
    print(f"Salary grades: {Employee.salary_grades}")

    # Create employees (instance objects)
    print("\n2. Creating employee instances:")
    alice = Employee("Alice Johnson", "EMP0001", "Mid", "Engineering")
    bob = Employee("Bob Smith", "EMP0002", "Senior", "Marketing")
    charlie = Employee("Charlie Brown", "EMP0003", "Junior", "Engineering")
    diana = Employee.create_intern("Diana Prince", "EMP0004", "HR")  # Using class method

    # Show how static variables are shared
    print(f"\n3. Static variables are shared by all instances:")
    print(f"Total employees (accessed via class): {Employee.total_employees}")
    print(f"Total employees (accessed via alice): {alice.total_employees}")
    print(f"Total employees (accessed via bob): {bob.total_employees}")
    print("All show the same value because it's static!")

    # Show how instance variables are unique
    print(f"\n4. Instance variables are unique to each object:")
    alice.add_project("Web Application")
    alice.add_project("Mobile App")
    alice.add_performance_rating(4.5)

    bob.add_project("Marketing Campaign")
    bob.add_performance_rating(4.8)

    charlie.add_project("Bug Fixes")
    charlie.add_performance_rating(3.9)

    print(f"Alice's projects: {alice.projects}")
    print(f"Bob's projects: {bob.projects}")
    print(f"Charlie's projects: {charlie.projects}")
    print("Each employee has their own project list!")

    # Demonstrate instance methods
    print(f"\n5. Instance methods work with individual objects:")
    alice.display_info()
    bob.promote()  # Promote Bob
    bob.display_info()

    # Demonstrate class methods
    print(f"\n6. Class methods work with the class as a whole:")
    Employee.display_company_stats()

    engineering_team = Employee.get_employees_by_department("Engineering")
    print(f"\nEngineering team: {[emp.name for emp in engineering_team]}")

    senior_employees = Employee.get_employees_by_grade("Senior")
    print(f"Senior employees: {[emp.name for emp in senior_employees]}")

    # Demonstrate static methods
    print(f"\n7. Static methods are utility functions:")
    print(f"Is 'EMP0001' valid ID? {Employee.is_valid_employee_id('EMP0001')}")
    print(f"Is 'INVALID' valid ID? {Employee.is_valid_employee_id('INVALID')}")
    print(f"Years of service for Alice: {Employee.calculate_years_of_service(alice.hire_date)}")
    print(f"Formatted salary: {Employee.format_salary(75000)}")

    grade_reqs = Employee.get_grade_requirements()
    print("Grade requirements:")
    for grade, req in grade_reqs.items():
        print(f"  {grade}: {req}")

    # Show memory efficiency of static variables
    print(f"\n8. Memory efficiency demonstration:")
    print(f"Company name is stored once (static): '{Employee.company_name}'")
    print(f"But each employee has their own name (instance):")
    for emp in Employee.all_employees:
        print(f"  {emp.name} (stored in individual object)")

if __name__ == "__main__":
    demonstrate_static_vs_instance()
```

### Java Example

```java
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class BankAccount {
    // Static variables (class-level, shared by all instances)
    private static String bankName = "Global Trust Bank";
    private static String bankCode = "GTB";
    private static int totalAccounts = 0;
    private static double totalDeposits = 0.0;
    private static final double MINIMUM_BALANCE = 100.0;
    private static final double INTEREST_RATE = 0.025; // 2.5%
    private static List<BankAccount> allAccounts = new ArrayList<>();

    // Instance variables (object-level, unique to each instance)
    private String accountNumber;
    private String accountHolderName;
    private String accountType;
    private double balance;
    private LocalDate openDate;
    private boolean isActive;
    private List<String> transactionHistory;

    // Constructor
    public BankAccount(String holderName, String accountType, double initialDeposit) {
        // Initialize instance variables
        this.accountHolderName = holderName;
        this.accountType = accountType;
        this.balance = initialDeposit;
        this.openDate = LocalDate.now();
        this.isActive = true;
        this.transactionHistory = new ArrayList<>();

        // Generate account number using static variable
        totalAccounts++;
        this.accountNumber = bankCode + String.format("%06d", totalAccounts);

        // Update static variables
        totalDeposits += initialDeposit;
        allAccounts.add(this);

        // Record transaction
        addTransaction("Account opened with initial deposit: $" + initialDeposit);

        System.out.println("🏦 Account created: " + accountNumber + " for " + holderName);
        System.out.println("   Type: " + accountType + ", Balance: $" + balance);
        System.out.println("   Total accounts in bank: " + totalAccounts);
    }

    // Instance methods (work with individual account objects)
    public boolean deposit(double amount) {
        if (amount > 0 && isActive) {
            balance += amount;
            totalDeposits += amount;  // Update static variable
            addTransaction("Deposited: $" + amount + " | Balance: $" + balance);
            System.out.println("💰 Deposited $" + amount + " to " + accountNumber);
            return true;
        }
        System.out.println("❌ Invalid deposit amount or account inactive");
        return false;
    }

    public boolean withdraw(double amount) {
        if (!isActive) {
            System.out.println("❌ Account is inactive");
            return false;
        }

        if (amount > 0 && (balance - amount) >= MINIMUM_BALANCE) {
            balance -= amount;
            totalDeposits -= amount;  // Update static variable
            addTransaction("Withdrew: $" + amount + " | Balance: $" + balance);
            System.out.println("💸 Withdrew $" + amount + " from " + accountNumber);
            return true;
        }

        System.out.println("❌ Insufficient funds or invalid amount");
        System.out.println("   Current balance: $" + balance + ", Minimum required: $" + MINIMUM_BALANCE);
        return false;
    }

    public void transfer(BankAccount targetAccount, double amount) {
        if (this.withdraw(amount)) {
            if (targetAccount.deposit(amount)) {
                System.out.println("✅ Transfer successful: $" + amount + " to " + targetAccount.getAccountNumber());
                addTransaction("Transferred: $" + amount + " to " + targetAccount.getAccountNumber());
                targetAccount.addTransaction("Received: $" + amount + " from " + this.accountNumber);
            } else {
                // Rollback if target deposit fails
                this.deposit(amount);
                System.out.println("❌ Transfer failed - amount returned");
            }
        }
    }

    public void calculateInterest() {
        if (isActive && balance > MINIMUM_BALANCE) {
            double interest = balance * INTEREST_RATE / 12; // Monthly interest
            balance += interest;
            totalDeposits += interest;
            addTransaction("Interest earned: $" + String.format("%.2f", interest) + " | Balance: $" + balance);
            System.out.println("💎 Interest earned: $" + String.format("%.2f", interest) + " on " + accountNumber);
        }
    }

    private void addTransaction(String transaction) {
        String timestampedTransaction = LocalDate.now() + ": " + transaction;
        transactionHistory.add(timestampedTransaction);
    }

    public void displayAccountInfo() {
        System.out.println("\n🏦 === Account Information ===");
        System.out.println("Bank: " + bankName);  // Static variable
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Holder: " + accountHolderName);
        System.out.println("Type: " + accountType);
        System.out.println("Balance: $" + String.format("%.2f", balance));
        System.out.println("Opened: " + openDate);
        System.out.println("Status: " + (isActive ? "Active" : "Inactive"));
        System.out.println("Years with bank: " + getYearsWithBank());
        System.out.println("Recent transactions:");

        int recentCount = Math.min(3, transactionHistory.size());
        for (int i = transactionHistory.size() - recentCount; i < transactionHistory.size(); i++) {
            System.out.println("  " + transactionHistory.get(i));
        }
        System.out.println("============================");
    }

    public int getYearsWithBank() {
        return Period.between(openDate, LocalDate.now()).getYears();
    }

    // Getters (instance methods)
    public String getAccountNumber() { return accountNumber; }
    public String getAccountHolderName() { return accountHolderName; }
    public double getBalance() { return balance; }
    public boolean isActive() { return isActive; }

    // Class methods (static methods that work with class-level data)
    public static String getBankName() {
        return bankName;
    }

    public static int getTotalAccounts() {
        return totalAccounts;
    }

    public static double getTotalDeposits() {
        return totalDeposits;
    }

    public static double getMinimumBalance() {
        return MINIMUM_BALANCE;
    }

    public static double getInterestRate() {
        return INTEREST_RATE;
    }

    public static void displayBankStatistics() {
        System.out.println("\n🏛️ === Bank Statistics ===");
        System.out.println("Bank Name: " + bankName);
        System.out.println("Bank Code: " + bankCode);
        System.out.println("Total Accounts: " + totalAccounts);
        System.out.println("Total Deposits: $" + String.format("%.2f", totalDeposits));
        System.out.println("Average Balance: $" + String.format("%.2f",
                          totalAccounts > 0 ? totalDeposits / totalAccounts : 0));

        // Account type breakdown
        Map<String, Integer> typeCount = new HashMap<>();
        Map<String, Double> typeBalance = new HashMap<>();

        for (BankAccount account : allAccounts) {
            String type = account.accountType;
            typeCount.put(type, typeCount.getOrDefault(type, 0) + 1);
            typeBalance.put(type, typeBalance.getOrDefault(type, 0.0) + account.balance);
        }

        System.out.println("\nAccount Type Breakdown:");
        for (String type : typeCount.keySet()) {
            System.out.println("  " + type + ": " + typeCount.get(type) +
                             " accounts, $" + String.format("%.2f", typeBalance.get(type)) + " total");
        }
        System.out.println("=========================");
    }

    public static List<BankAccount> getAccountsByType(String accountType) {
        List<BankAccount> result = new ArrayList<>();
        for (BankAccount account : allAccounts) {
            if (account.accountType.equals(accountType)) {
                result.add(account);
            }
        }
        return result;
    }

    public static List<BankAccount> getHighBalanceAccounts(double threshold) {
        List<BankAccount> result = new ArrayList<>();
        for (BankAccount account : allAccounts) {
            if (account.balance >= threshold) {
                result.add(account);
            }
        }
        return result;
    }

    public static void processMonthlyInterest() {
        System.out.println("\n💰 Processing monthly interest for all accounts...");
        for (BankAccount account : allAccounts) {
            account.calculateInterest();
        }
        System.out.println("✅ Monthly interest processing completed!");
    }

    // Static utility methods (don't need class or instance data)
    public static boolean isValidAccountNumber(String accountNumber) {
        return accountNumber != null &&
               accountNumber.length() == 9 &&
               accountNumber.startsWith(bankCode) &&
               accountNumber.substring(3).matches("\\d{6}");
    }

    public static String formatCurrency(double amount) {
        return "$" + String.format("%.2f", amount);
    }

    public static double calculateCompoundInterest(double principal, double rate, int years) {
        return principal * Math.pow(1 + rate, years);
    }

    public static String generateAccountNumber() {
        return bankCode + String.format("%06d", totalAccounts + 1);
    }

    // Factory methods (static methods that create instances)
    public static BankAccount createSavingsAccount(String holderName, double initialDeposit) {
        System.out.println("🏦 Creating savings account...");
        return new BankAccount(holderName, "Savings", Math.max(initialDeposit, MINIMUM_BALANCE));
    }

    public static BankAccount createCheckingAccount(String holderName, double initialDeposit) {
        System.out.println("🏦 Creating checking account...");
        return new BankAccount(holderName, "Checking", Math.max(initialDeposit, MINIMUM_BALANCE));
    }

    public static BankAccount createBusinessAccount(String businessName, double initialDeposit) {
        System.out.println("🏦 Creating business account...");
        return new BankAccount(businessName, "Business", Math.max(initialDeposit, MINIMUM_BALANCE * 5));
    }

    @Override
    public String toString() {
        return String.format("BankAccount[%s, %s, $%.2f]",
                           accountNumber, accountHolderName, balance);
    }
}

// Demo class
class BankingSystemDemo {
    public static void main(String[] args) {
        System.out.println("=== Static vs Instance Members Demo ===\n");

        // Show initial static state
        System.out.println("1. Initial bank state (before creating accounts):");
        System.out.println("Bank: " + BankAccount.getBankName());
        System.out.println("Total accounts: " + BankAccount.getTotalAccounts());
        System.out.println("Total deposits: " + BankAccount.formatCurrency(BankAccount.getTotalDeposits()));
        System.out.println("Minimum balance: " + BankAccount.formatCurrency(BankAccount.getMinimumBalance()));

        // Create accounts using different methods
        System.out.println("\n2. Creating bank accounts:");
        BankAccount alice = BankAccount.createSavingsAccount("Alice Johnson", 1500.0);
        BankAccount bob = BankAccount.createCheckingAccount("Bob Smith", 2000.0);
        BankAccount company = BankAccount.createBusinessAccount("TechCorp Inc.", 10000.0);
        BankAccount charlie = new BankAccount("Charlie Brown", "Savings", 800.0);

        // Show how static variables are shared
        System.out.println("\n3. Static variables are shared:");
        System.out.println("Total accounts (via class): " + BankAccount.getTotalAccounts());
        System.out.println("Total accounts (via alice): " + alice.getTotalAccounts());  // Same value
        System.out.println("Total accounts (via bob): " + bob.getTotalAccounts());      // Same value

        // Show how instance variables are unique
        System.out.println("\n4. Instance variables are unique:");
        alice.deposit(500);
        bob.withdraw(300);
        company.deposit(5000);

        System.out.println("Alice's balance: " + BankAccount.formatCurrency(alice.getBalance()));
        System.out.println("Bob's balance: " + BankAccount.formatCurrency(bob.getBalance()));
        System.out.println("Company's balance: " + BankAccount.formatCurrency(company.getBalance()));

        // Demonstrate instance methods
        System.out.println("\n5. Instance methods work with individual accounts:");
        alice.transfer(bob, 200);
        bob.transfer(charlie, 150);

        alice.displayAccountInfo();
        bob.displayAccountInfo();

        // Demonstrate static methods
        System.out.println("\n6. Static methods work with class-level data:");
        BankAccount.displayBankStatistics();

        List<BankAccount> savingsAccounts = BankAccount.getAccountsByType("Savings");
        System.out.println("\nSavings accounts: " + savingsAccounts.size());
        for (BankAccount account : savingsAccounts) {
            System.out.println("  " + account);
        }

        List<BankAccount> highBalance = BankAccount.getHighBalanceAccounts(1000.0);
        System.out.println("\nHigh balance accounts (>$1000): " + highBalance.size());

        // Demonstrate static utility methods
        System.out.println("\n7. Static utility methods:");
        System.out.println("Is 'GTB000001' valid? " + BankAccount.isValidAccountNumber("GTB000001"));
        System.out.println("Is 'INVALID' valid? " + BankAccount.isValidAccountNumber("INVALID"));

        double futureValue = BankAccount.calculateCompoundInterest(1000, 0.05, 10);
        System.out.println("$1000 at 5% for 10 years: " + BankAccount.formatCurrency(futureValue));

        // Process monthly interest (affects all accounts)
        BankAccount.processMonthlyInterest();

        // Final statistics
        System.out.println("\n8. Final bank statistics:");
        BankAccount.displayBankStatistics();

        System.out.println("\n🏁 Demo completed!");
    }
}
```
