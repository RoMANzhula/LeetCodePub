package P2401_2500.P2472_Maximum_Number_of_Non_overlapping_Palindrome_Substrings;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String s1 = "abaccdbbd";
        int k1 = 3;
        System.out.println(solution.maxPalindromes(s1, k1));

        String s2 = "adbcda";
        int k2 = 2;
        System.out.println(solution.maxPalindromes(s2, k2));
    }

    public int maxPalindromes(String s, int k) {
        int n = s.length();

        boolean[][] palindrome = new boolean[n][n];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)
                        && (j - i <= 2 || palindrome[i + 1][j - 1])) {
                    palindrome[i][j] = true;
                }
            }
        }

        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1];

            for (int start = 0; start < i; start++) {
                if (i - start >= k && palindrome[start][i - 1]) {
                    dp[i] = Math.max(dp[i], dp[start] + 1);
                }
            }
        }

        return dp[n];
    }

}

//Complexity:
// time and space - O(n^2)


//You are given a string s and a positive integer k.
//Select a set of non-overlapping substrings from the string s that satisfy the following conditions:
//The length of each substring is at least k.
//Each substring is a palindrome.
//Return the maximum number of substrings in an optimal selection.
//A substring is a contiguous sequence of characters within a string.

//Example 1:
//Input: s = "abaccdbbd", k = 3
//Output: 2
//Explanation: We can select the substrings underlined in s = "abaccdbbd". Both "aba" and "dbbd" are palindromes and
// have a length of at least k = 3.
//It can be shown that we cannot find a selection with more than two valid substrings.

//Example 2:
//Input: s = "adbcda", k = 2
//Output: 0
//Explanation: There is no palindrome substring of length at least 2 in the string.

//Constraints:
//1 <= k <= s.length <= 2000
//s consists of lowercase English letters.
