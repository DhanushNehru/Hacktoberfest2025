/**
 * Prime Number Checker
 * This class provides utility methods to check if a number is prime
 * and to find prime numbers within a given range.
 */
public class PrimeNumberChecker {

    /**
     * Checks if a given number is prime
     * @param num the number to check
     * @return true if the number is prime, false otherwise
     */
    public static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        if (num <= 3) {
            return true;
        }
        if (num % 2 == 0 || num % 3 == 0) {
            return false;
        }
        for (int i = 5; i * i <= num; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Finds all prime numbers up to a given limit
     * @param limit the upper limit to find primes
     * @return an array of prime numbers up to the limit
     */
    public static int[] findPrimesUpTo(int limit) {
        int count = 0;
        for (int i = 2; i <= limit; i++) {
            if (isPrime(i)) {
                count++;
            }
        }
        int[] primes = new int[count];
        int index = 0;
        for (int i = 2; i <= limit; i++) {
            if (isPrime(i)) {
                primes[index++] = i;
            }
        }
        return primes;
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        System.out.println("Testing Prime Number Checker");
        System.out.println("Is 17 prime? " + isPrime(17));
        System.out.println("Is 20 prime? " + isPrime(20));
        System.out.println("Primes up to 30: ");
        for (int prime : findPrimesUpTo(30)) {
            System.out.print(prime + " ");
        }
    }
}
