class Solution {
    public boolean isPalindrome(int x) {
        // Negative numbers cannot be palindromes (e.g., -121 reads 121- reversed).
        if (x < 0) {
            return false;
        }

        // Single-digit numbers are always palindromes.
        if (x >= 0 && x < 10) {
            return true;
        }

        long reversedNum = 0;
        int originalNum = x;

        // Reverse the number
        while (x != 0) {
            int digit = x % 10; // Get the last digit
            reversedNum = reversedNum * 10 + digit; // Append the digit to reversedNum
            x /= 10; // Remove the last digit from x
        }

        // Compare the reversed number with the original number
        return reversedNum == originalNum;
    }
}
