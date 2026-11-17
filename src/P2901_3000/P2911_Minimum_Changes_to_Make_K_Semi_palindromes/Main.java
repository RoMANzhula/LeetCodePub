package P2901_3000.P2911_Minimum_Changes_to_Make_K_Semi_palindromes;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.minimumChanges("abcac", 2));   // 1
        System.out.println(solution.minimumChanges("abcdef", 2));  // 2
        System.out.println(solution.minimumChanges("aabbaa", 3));   // 0
    }

    public int minimumChanges(String s, int k) {
        int n = s.length();
        int[][] cost = new int[n][n];

        for (int left = 0; left < n; left++) {
            for (int right = left + 1; right < n; right++) {
                int len = right - left + 1;
                int best = Integer.MAX_VALUE;

                for (int d = 1; d < len; d++) {
                    if (len % d != 0) {
                        continue;
                    }

                    int changes = 0;

                    for (int r = 0; r < d; r++) {
                        int count = len / d;

                        for (int p = 0; p < count / 2; p++) {
                            int i = left + r + p * d;
                            int j = left + r + (count - 1 - p) * d;

                            if (s.charAt(i) != s.charAt(j)) {
                                changes++;
                            }
                        }
                    }

                    best = Math.min(best, changes);
                }

                cost[left][right] = best;
            }
        }

        int INF = 1_000_000;
        int[][] dp = new int[k + 1][n + 1];

        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }

        dp[0][0] = 0;

        for (int groups = 1; groups <= k; groups++) {
            for (int end = groups * 2; end <= n; end++) {
                for (int start = (groups - 1) * 2;
                     start <= end - 2; start++) {

                    dp[groups][end] = Math.min(
                            dp[groups][end],
                            dp[groups - 1][start] + cost[start][end - 1]
                    );
                }
            }
        }

        return dp[k][n];
    }

}

//Complexity:
// time - O(n^3*log n+k*n^2)
// space - O(n^2 + k*n)



//Given a string s and an integer k, partition s into k substrings such that the letter changes needed to make each
// substring a semi-palindrome are minimized.
//Return the minimum number of letter changes required.
//A semi-palindrome is a special type of string that can be divided into palindromes based on a repeating pattern. To
// check if a string is a semi-palindrome:​
//Choose a positive divisor d of the string's length. d can range from 1 up to, but not including, the string's
// length. For a string of length 1, it does not have a valid divisor as per this definition, since the only divisor is
// its length, which is not allowed.
//For a given divisor d, divide the string into groups where each group contains characters from the string that
// follow a repeating pattern of length d. Specifically, the first group consists of characters at positions 1, 1 + d,
// 1 + 2d, and so on; the second group includes characters at positions 2, 2 + d, 2 + 2d, etc.
//The string is considered a semi-palindrome if each of these groups forms a palindrome.
//Consider the string "abcabc":
//The length of "abcabc" is 6. Valid divisors are 1, 2, and 3.
//For d = 1: The entire string "abcabc" forms one group. Not a palindrome.
//For d = 2:
//Group 1 (positions 1, 3, 5): "acb"
//Group 2 (positions 2, 4, 6): "bac"
//Neither group forms a palindrome.
//For d = 3:
//Group 1 (positions 1, 4): "aa"
//Group 2 (positions 2, 5): "bb"
//Group 3 (positions 3, 6): "cc"
//All groups form palindromes. Therefore, "abcabc" is a semi-palindrome.

//Example 1:
//Input: s = "abcac", k = 2
//Output: 1
//Explanation: Divide s into "ab" and "cac". "cac" is already semi-palindrome. Change "ab" to "aa", it becomes
// semi-palindrome with d = 1.

//Example 2:
//Input: s = "abcdef", k = 2
//Output: 2
//Explanation: Divide s into substrings "abc" and "def". Each needs one change to become semi-palindrome.

//Example 3:
//Input: s = "aabbaa", k = 3
//Output: 0
//Explanation: Divide s into substrings "aa", "bb" and "aa". All are already semi-palindromes.

//Constraints:
//2 <= s.length <= 200
//1 <= k <= s.length / 2
//s contains only lowercase English letters.
