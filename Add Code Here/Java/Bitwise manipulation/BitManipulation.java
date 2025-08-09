package Bitwise manipulation;


    public class BitManipulation {

        // Get the i-th bit of a number
        public static int getIthBit(int num, int i) {
            int mask = 1 << i;
            return (num & mask) != 0 ? 1 : 0;
        }
    
        // Clear the i-th bit of a number
        public static int clearIthBit(int num, int i) {
            int mask = ~(1 << i);
            return num & mask;
        }
    
        // Update the i-th bit of a number to a given value (0 or 1)
        public static int updateIthBit(int num, int i, int value) {
            num = clearIthBit(num, i); // First clear the i-th bit
            int mask = (value << i);
            return num | mask;
        }
    
        // Clear a range of bits from position i to j (inclusive)
        public static int clearRangeOfBits(int num, int i, int j) {
            int left = (~0) << (j + 1);   // All 1s till position j and 0s after that
            int right = (1 << i) - 1;     // All 0s till position i and 1s after that
            int mask = left | right;      // Combine to create a mask with 0s in range i to j
            return num & mask;
        }
    
        // Count the number of set bits (1s) in a number
        public static int countSetBits(int num) {
            int count = 0;
            while (num != 0) {
                count += (num & 1);  // Check the least significant bit
                num >>= 1;           // Right shift the number
            }
            return count;
        }
    
        public static void main(String[] args) {
            int num = 29; // Example number in binary: 11101
    
            System.out.println("Original number: " + num);
            System.out.println("Get 3rd bit: " + getIthBit(num, 3));
            System.out.println("Clear 2nd bit: " + clearIthBit(num, 2));
            System.out.println("Update 1st bit to 0: " + updateIthBit(num, 1, 0));
            System.out.println("Clear range of bits from 1 to 3: " + clearRangeOfBits(num, 1, 3));
            System.out.println("Count of set bits: " + countSetBits(num));
        }
    }
    

