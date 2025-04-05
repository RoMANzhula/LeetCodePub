package P1_100.P10_Regular_Expression_Matching;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.isMatch("aa", "a"));    // false
        System.out.println(solution.isMatch("aa", "a*"));   // true
        System.out.println(solution.isMatch("ab", ".*"));   // true
        System.out.println(solution.isMatch("mississippi", "mis*is*p*.")); // false
        System.out.println(solution.isMatch("aab", "c*a*b")); // true
    }

    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        // dp[i][j] = true if s[0..i-1] matches p[0..j-1]
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true; // empty string matches empty pattern

        //handle patterns like a*, a*b*, a*b*c* that can match empty string
        for (int j = 2; j <= n; j += 2) {
            if (p.charAt(j - 1) == '*' && dp[0][j - 2]) {
                dp[0][j] = true;
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);

                if (pc == '.' || pc == sc) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pc == '*') {
                    char prev = p.charAt(j - 2);
                    // zero occurrence of the character before '*'
                    dp[i][j] = dp[i][j - 2];
                    //one or more occurrence if previous char matches current char in s
                    if (prev == '.' || prev == sc) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                }
            }
        }

        return dp[m][n];
    }

}


//Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:
//'.' Matches any single character.​​​​
//'*' Matches zero or more of the preceding element.
//The matching should cover the entire input string (not partial).

//Example 1:
//Input: s = "aa", p = "a"
//Output: false
//Explanation: "a" does not match the entire string "aa".

//Example 2:
//Input: s = "aa", p = "a*"
//Output: true
//Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".

//Example 3:
//Input: s = "ab", p = ".*"
//Output: true
//Explanation: ".*" means "zero or more (*) of any character (.)".

//Constraints:
//1 <= s.length <= 20
//1 <= p.length <= 20
//s contains only lowercase English letters.
//p contains only lowercase English letters, '.', and '*'.
//It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.
