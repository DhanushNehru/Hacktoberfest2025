/**
 *
 * Description:
 *   Solves the Regular Expression Matching problem using Dynamic Programming.
 *   Supports '.' and '*' pattern symbols.
 *
 *   '.' -> Matches any single character.
 *   '*' -> Matches zero or more of the preceding element.
 *
 * Time Complexity: O(m * n)
 * Space Complexity: O(m * n)
 *
 * Example:
 *   Input:
 *     s = "mississippi"
 *     p = "mis*is*p*."
 *   Output:
 *     true
 *
 * Author: <your-github-username>
 */

import java.util.*;

public class RegularExpressionMatching {

    public static boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true; // Empty string matches empty pattern

        // Handle patterns like a*, a*b*, a*b*c* that can match empty string
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        // Fill DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);

                if (pc == sc || pc == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pc == '*') {
                    dp[i][j] = dp[i][j - 2]; // Zero occurrence
                    if (p.charAt(j - 2) == sc || p.charAt(j - 2) == '.') {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Regular Expression Matching (DP) ===");
        System.out.print("Enter the input string (s): ");
        String s = sc.nextLine();

        System.out.print("Enter the pattern (p): ");
        String p = sc.nextLine();

        boolean result = isMatch(s, p);
        System.out.println("Result: " + result);

        sc.close();
    }
}
