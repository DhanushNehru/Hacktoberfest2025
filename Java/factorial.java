import java.util.Scanner;

// Class to calculate factorial
class FactorialCalculator {
    
    // Method to calculate factorial
    public int calculateFactorial(int number) {
        int factorial = 1;
        
        // Loop to calculate factorial
        for (int i = 1; i <= number; i++) {
            factorial *= i;
        }
        
        return factorial;
    }
}

// Main class
public class FactorialMain {
    public static void main(String[] args) {
        // Create scanner object to take input
        Scanner scanner = new Scanner(System.in);
        
        // Create object of FactorialCalculator class
        FactorialCalculator calculator = new FactorialCalculator();
        
        // Take user input
        System.out.print("Enter a number to calculate its factorial: ");
        int number = scanner.nextInt();
        
        // Calculate factorial using the class method
        int result = calculator.calculateFactorial(number);
        
        // Print the result
        System.out.println("The factorial of " + number + " is: " + result);
        
        // Close scanner
        scanner.close();
    }
}
