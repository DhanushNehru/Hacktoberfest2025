// Iterative Solution
class HappyNumber {
    public boolean isHappy(int N) {
        
        do {
            N = HappyNumber(N);    
        } while (N != 1 && N != 4);
        
        if (N == 1) {
        return true;
        }
    return false;
    }
    
    static int HappyNumber(int N) {

        int sum = 0;
        while (N > 0) {
            int Rem = N % 10;
            N /= 10;
            sum += (Rem * Rem);
        }
        return sum;
    }
}


// Recurive Solution
class Solution {
    public boolean isHappy(int n) {
        
        int sum = 0;
        while (n > 0) {
            sum += (n % 10) * (n % 10);
            n /= 10;
        }
        if (sum == 1) return true;
        if (sum == 4) return false;
        
        return isHappy (sum);
    }
}
