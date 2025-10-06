import java.util.ArrayList;
import java.util.List;

class Solution {
    // Mapping of digits to letters as per telephone keypad
    private static final String[] KEYPAD = {
        "",     // 0
        "",     // 1
        "abc",  // 2
        "def",  // 3
        "ghi",  // 4
        "jkl",  // 5
        "mno",  // 6
        "pqrs", // 7
        "tuv",  // 8
        "wxyz"  // 9
    };

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        // If input is empty, return an empty list
        if (digits == null || digits.isEmpty()) {
            return result;
        }
        // Start backtracking to generate all combinations
        backtrack(result, digits, new StringBuilder(), 0);
        return result;
    }

    // Recursive helper function to generate combinations
    private void backtrack(List<String> result, String digits, StringBuilder current, int index) {
        // If the current combination is complete, add to result list
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        // Get the possible letters for the current digit
        String letters = KEYPAD[digits.charAt(index) - '0'];
        for (char letter : letters.toCharArray()) {
            // Append the letter and move to the next digit
            current.append(letter);
            backtrack(result, digits, current, index + 1);
            // Backtrack: remove the last letter to try other combinations
            current.deleteCharAt(current.length() - 1);
        }
    }
}
