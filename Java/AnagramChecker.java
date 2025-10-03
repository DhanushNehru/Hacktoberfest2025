import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Anagram Checker - Multiple approaches to verify if two strings are anagrams
 * 
 * An anagram is a word formed by rearranging letters of another word,
 * using all original letters exactly once.
 * 
 * Example: "listen" and "silent" are anagrams
 * 
 * @author Prince Yadav
 * @version 2.0
 * @since October 2025
 */
public class AnagramChecker {

    /**
     * Checks if two strings are anagrams using HashMap (case-insensitive)
     * Ignores non-alphabetic characters and whitespace
     * 
     * Time Complexity: O(n + m) where n, m are lengths of strings
     * Space Complexity: O(k) where k is number of unique characters
     * 
     * @param word1 First string to compare
     * @param word2 Second string to compare
     * @return true if strings are anagrams, false otherwise
     */
    public static boolean isAnagram(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return false;
        }

        // Remove non-alpha characters and convert to lowercase
        word1 = word1.replaceAll("[^a-zA-Z]", "").toLowerCase();
        word2 = word2.replaceAll("[^a-zA-Z]", "").toLowerCase();

        // Quick length check
        if (word1.length() != word2.length()) {
            return false;
        }

        // Count character frequencies
        Map<Character, Integer> charCount = new HashMap<>();

        for (char c : word1.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }

        for (char c : word2.toCharArray()) {
            if (!charCount.containsKey(c)) {
                return false;
            }

            int count = charCount.get(c) - 1;
            if (count == 0) {
                charCount.remove(c);
            } else {
                charCount.put(c, count);
            }
        }

        return charCount.isEmpty();
    }

    /**
     * Alternative approach using sorting (simpler but slightly less efficient)
     * 
     * Time Complexity: O(n log n)
     * Space Complexity: O(n) for character arrays
     * 
     * @param word1 First string
     * @param word2 Second string
     * @return true if anagrams
     */
    public static boolean isAnagramSorting(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return false;
        }

        word1 = word1.replaceAll("[^a-zA-Z]", "").toLowerCase();
        word2 = word2.replaceAll("[^a-zA-Z]", "").toLowerCase();

        if (word1.length() != word2.length()) {
            return false;
        }

        char[] arr1 = word1.toCharArray();
        char[] arr2 = word2.toCharArray();

        Arrays.sort(arr1);
        Arrays.sort(arr2);

        return Arrays.equals(arr1, arr2);
    }

    /**
     * Optimized approach using character frequency array (for lowercase letters
     * only)
     * Most efficient for simple anagram checking
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(1) - fixed 26 character array
     * 
     * @param word1 First string
     * @param word2 Second string
     * @return true if anagrams
     */
    public static boolean isAnagramOptimized(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return false;
        }

        word1 = word1.replaceAll("[^a-zA-Z]", "").toLowerCase();
        word2 = word2.replaceAll("[^a-zA-Z]", "").toLowerCase();

        if (word1.length() != word2.length()) {
            return false;
        }

        int[] charFreq = new int[26]; // For 'a' to 'z'

        for (int i = 0; i < word1.length(); i++) {
            charFreq[word1.charAt(i) - 'a']++;
            charFreq[word2.charAt(i) - 'a']--;
        }

        for (int count : charFreq) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Prints test result in formatted manner
     */
    private static void testAnagram(String word1, String word2) {
        boolean result1 = isAnagram(word1, word2);
        boolean result2 = isAnagramSorting(word1, word2);
        boolean result3 = isAnagramOptimized(word1, word2);

        System.out.printf("%-20s | %-20s | HashMap: %-5s | Sorting: %-5s | Optimized: %-5s%n",
                "\"" + word1 + "\"", "\"" + word2 + "\"",
                result1, result2, result3);
    }

    public static void main(String[] args) {
        System.out.println("=== Anagram Checker Test Cases ===\n");
        System.out.printf("%-20s | %-20s | %-14s | %-14s | %-14s%n",
                "Word 1", "Word 2", "HashMap", "Sorting", "Optimized");
        System.out.println("-".repeat(95));

        // Basic anagrams
        testAnagram("evil", "vile");
        testAnagram("angel", "glean");
        testAnagram("listen", "silent");

        // With spaces and punctuation
        testAnagram("school master", "the classroom");
        testAnagram("conversation", "voices rant on");

        // Not anagrams
        testAnagram("listen", "hearing");
        testAnagram("hello", "world");

        // Edge cases
        testAnagram("", "");
        testAnagram("a", "a");
        testAnagram("abc", "def");

        System.out.println("\n=== Performance Comparison ===\n");

        String str1 = "astronomer";
        String str2 = "moon starer";

        // Benchmark HashMap approach
        long start = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            isAnagram(str1, str2);
        }
        long hashMapTime = System.nanoTime() - start;

        // Benchmark Sorting approach
        start = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            isAnagramSorting(str1, str2);
        }
        long sortingTime = System.nanoTime() - start;

        // Benchmark Optimized approach
        start = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            isAnagramOptimized(str1, str2);
        }
        long optimizedTime = System.nanoTime() - start;

        System.out.printf("HashMap approach:   %d ms%n", hashMapTime / 1_000_000);
        System.out.printf("Sorting approach:   %d ms%n", sortingTime / 1_000_000);
        System.out.printf("Optimized approach: %d ms%n", optimizedTime / 1_000_000);

        System.out.println("\nRecommendation:");
        System.out.println("- Use Optimized (array) for simple lowercase anagrams");
        System.out.println("- Use HashMap for Unicode/multilingual support");
        System.out.println("- Use Sorting for simplicity and readability");
    }
}