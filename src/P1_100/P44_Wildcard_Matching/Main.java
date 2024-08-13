package P1_100.P44_Wildcard_Matching;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.isMatch("aa", "a")); // Output: false
        System.out.println(solution.isMatch("aa", "*")); // Output: true
        System.out.println(solution.isMatch("cb", "?a")); // Output: false
    }

    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        // DP table
        boolean[][] dp = new boolean[m + 1][n + 1];

        // Empty pattern matches empty string
        dp[0][0] = true;

        // Handle patterns with '*'
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1];
            }
        }

        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sChar = s.charAt(i - 1);
                char pChar = p.charAt(j - 1);

                if (pChar == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                } else if (pChar == '?' || pChar == sChar) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = false;
                }
            }
        }

        return dp[m][n];
    }
}


//Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:
//'?' Matches any single character.
//'*' Matches any sequence of characters (including the empty sequence).
//The matching should cover the entire input string (not partial).
//
//Example 1:
//Input: s = "aa", p = "a"
//Output: false
//Explanation: "a" does not match the entire string "aa".

//Example 2:
//Input: s = "aa", p = "*"
//Output: true
//Explanation: '*' matches any sequence.

//Example 3:
//Input: s = "cb", p = "?a"
//Output: false
//Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
//
//Constraints:
//0 <= s.length, p.length <= 2000
//s contains only lowercase English letters.
//p contains only lowercase English letters, '?' or '*'.