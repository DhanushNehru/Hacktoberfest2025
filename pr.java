public class Factorial {
    public static void main(String[] args) {
        int number = 5; // Change this value to compute factorial of a different number
        int factorial = 1;
        for (int i = 1; i <= number; i++) {
            factorial *= i;
        }
        System.out.println("Factorial of " + number + " is " + factorial);
    }
}
