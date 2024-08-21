package P601_700.P664_Strange_Printer;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.strangePrinter("aaabbb")); // Output: 2
        System.out.println(solution.strangePrinter("aba"));    // Output: 2
    }

//    public int strangePrinter(String s) {
//        int n = s.length();
//        int[][] dp = new int[n][n];
//
//        for (int i = n - 1; i >= 0; i--) {
//            dp[i][i] = 1; // base case: a single character needs 1 turn
//            for (int j = i + 1; j < n; j++) {
//                dp[i][j] = dp[i][j - 1] + 1; // start with the worst case
//                for (int k = i; k < j; k++) {
//                    int temp = dp[i][k] + dp[k + 1][j];
//                    if (s.charAt(k) == s.charAt(j)) {
//                        temp--; // merge if characters at k and j are the same
//                    }
//                    dp[i][j] = Math.min(dp[i][j], temp);
//                }
//            }
//        }
//
//        return dp[0][n-1];
//    }

    // faster solution
    public int strangePrinter(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        // Fill the dp array
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1; // Single character needs 1 turn
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = dp[i][j - 1] + 1; // Start with the worst case
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - 1]);
                }
                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j]);
                }
            }
        }

        return dp[0][n - 1];
    }
}

//Explanation:
//Base Case: We initialize dp[i][i] = 1 because a single character requires exactly one print.
//Optimization:
//If s[i] == s[j], we reduce the problem size by merging s[i] and s[j], using the previous result dp[i][j-1].
//Instead of checking all splits, we focus on more beneficial splits by leveraging character equality, thus
// reducing unnecessary cycles.
//Result: The result is retrieved from dp[0][n-1], which gives the minimum number of turns required to print
// the entire string.
//Time Complexity
//The optimized approach still has a time complexity of O(n^3) in the worst case but is faster in practice due
// to reduced redundant calculations. This is often the best possible for problems involving 2D DP over a string.
// However, for the typical problem constraints (n ≤ 100), this solution will perform efficiently.


//There is a strange printer with the following two special properties:
//The printer can only print a sequence of the same character each time.
//At each turn, the printer can print new characters starting from and ending at any place and will cover the
// original existing characters.
//Given a string s, return the minimum number of turns the printer needed to print it.
//
//Example 1:
//Input: s = "aaabbb"
//Output: 2
//Explanation: Print "aaa" first and then print "bbb".

//Example 2:
//Input: s = "aba"
//Output: 2
//Explanation: Print "aaa" first and then print "b" from the second place of the string, which will
// cover the existing character 'a'.
//
//Constraints:
//1 <= s.length <= 100
//s consists of lowercase English letters.