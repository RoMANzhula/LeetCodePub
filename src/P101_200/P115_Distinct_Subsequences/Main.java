package P101_200.P115_Distinct_Subsequences;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String s1 = "rabbbit";
        String t1 = "rabbit";
        System.out.println(solution.numDistinct(s1, t1)); // Output: 3

        String s2 = "babgbag";
        String t2 = "bag";
        System.out.println(solution.numDistinct(s2, t2)); // Output: 5
    }

    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();

        // dp[i][j] = number of subsequences of s[0..i-1] that form t[0..j-1]
        int[][] dp = new int[m + 1][n + 1];

        // base case: empty t can always be formed (1 way)
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1;
        }

        // fill DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[m][n];
    }

}

//Complexity:
// time and space - O(m * n)


//Given two strings s and t, return the number of distinct subsequences of s which equals t.
//The test cases are generated so that the answer fits on a 32-bit signed integer.

//Example 1:
//Input: s = "rabbbit", t = "rabbit"
//Output: 3
//Explanation:
//As shown below, there are 3 ways you can generate "rabbit" from s.
//rabbbit
//rabbbit
//rabbbit

//Example 2:
//Input: s = "babgbag", t = "bag"
//Output: 5
//Explanation:
//As shown below, there are 5 ways you can generate "bag" from s.
//babgbag
//babgbag
//babgbag
//babgbag
//babgbag

//Constraints:
//1 <= s.length, t.length <= 1000
//s and t consist of English letters.
