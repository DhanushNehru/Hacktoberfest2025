public class BinomialCoefficient {

    // Function to calculate factorial using a for loop
    public static int factorial(int n) {
        int f = 1;
        for (int i = 1; i <= n; i++) {
            f = f * i; 
        }
        return f; 
    }

    // Function to calculate binomial coefficient
    public static int binomial(int n, int r) {
        int fact_n = factorial(n);
        int fact_r = factorial(r);
        int fact_nmr = factorial(n - r);
        int bino = fact_n / (fact_r * fact_nmr); // Corrected formula

        return bino;
    }

    public static void main(String[] args) {
        System.out.println("Binomial Coefficient of (4, 2): " + binomial(4, 2)); // Example calculation
    }
}
