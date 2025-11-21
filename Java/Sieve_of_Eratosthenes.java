package Java;

public class Sieve_of_eratosthenes {
  static int[] sieve(int n) {
        // creation of boolean array
        boolean[] prime = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            prime[i] = true;
        }

        for (int p = 2; p * p <= n; p++) {
            if (prime[p]) {
                // marking as false
                for (int i = p * p; i <= n; i += p)
                    prime[i] = false;
            }
        }

        // Count number of primes
        int count = 0;
        for (int p = 2; p <= n; p++) {
            if (prime[p])
                count++;
        }

        // Store primes in an array
        int[] res = new int[count];
        int index = 0;
        for (int p = 2; p <= n; p++) {
            if (prime[p])
                res[index++] = p;
        }

        return res;
    }

    public static void main(String[] args) {
        int n = 35;
        int[] res = sieve(n);
        for (int ele : res) {
            System.out.print(ele + " ");
        }
    }      
}
