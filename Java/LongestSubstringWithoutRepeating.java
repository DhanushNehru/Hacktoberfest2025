import java.util.HashMap;

/**
 * Finds the length of the longest substring without repeating characters.
 * Uses sliding window + HashMap approach.
 */
public class LongestSubstringWithoutRepeating {

    /**
     * Returns the length of the longest substring without repeating characters.
     * @param s input string
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) return 0;

        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0, maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            // If character is already in the current window, move left pointer
            if (map.containsKey(c)) {
                left = Math.max(left, map.get(c) + 1);
            }

            map.put(c, right); // store/update last seen index
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    // Example usage
    public static void main(String[] args) {
        LongestSubstringWithoutRepeating solver = new LongestSubstringWithoutRepeating();

        String s1 = "abcabcbb";
        System.out.println("Input: " + s1);
        System.out.println("Length of longest substring: " + solver.lengthOfLongestSubstring(s1)); // 3

        String s2 = "bbbbb";
        System.out.println("Input: " + s2);
        System.out.println("Length of longest substring: " + solver.lengthOfLongestSubstring(s2)); // 1

        String s3 = "pwwkew";
        System.out.println("Input: " + s3);
        System.out.println("Length of longest substring: " + solver.lengthOfLongestSubstring(s3)); // 3
    }
}
