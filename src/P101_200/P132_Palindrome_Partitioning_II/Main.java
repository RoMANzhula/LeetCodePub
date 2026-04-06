package P101_200.P132_Palindrome_Partitioning_II;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.minCut("aab")); // 1
        System.out.println(solution.minCut("a"));   // 0
        System.out.println(solution.minCut("ab"));  // 1
    }

    public int minCut(String s) {
        int n = s.length();

        boolean[][] isPal = new boolean[n][n];

        // precompute palindromes
        for (int end = 0; end < n; end++) {
            for (int start = 0; start <= end; start++) {
                if (s.charAt(start) == s.charAt(end) &&
                        (end - start <= 2 || isPal[start + 1][end - 1])) {
                    isPal[start][end] = true;
                }
            }
        }

        //DP for minimum cuts
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = i; // worst case: cut every character

            for (int j = 0; j <= i; j++) {
                if (isPal[j][i]) {
                    if (j == 0) {
                        dp[i] = 0;
                    } else {
                        dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                    }
                }
            }
        }

        return dp[n - 1];
    }

}

//Complexity:
// time and space - O(n^2)


//Given a string s, partition s such that every substring of the partition is a palindrome.
//Return the minimum cuts needed for a palindrome partitioning of s.

//Example 1:
//Input: s = "aab"
//Output: 1
//Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.

//Example 2:
//Input: s = "a"
//Output: 0

//Example 3:
//Input: s = "ab"
//Output: 1

//Constraints:
//1 <= s.length <= 2000
//s consists of lowercase English letters only.
