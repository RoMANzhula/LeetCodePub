package P901_1000.P940_Distinct_Subsequences_II;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.distinctSubseqII("abc")); // 7
        System.out.println(solution.distinctSubseqII("aba")); // 6
        System.out.println(solution.distinctSubseqII("aaa")); // 3
    }

    private final long MOD = 1_000_000_007L;

    public int distinctSubseqII(String s) {
        int n = s.length();

        // using the first i characters, including empty subsequence
        long[] dp = new long[n + 1];

        dp[0] = 1;

        // last position where each character appeared
        int[] last = new int[26];
        Arrays.fill(last, -1);

        for (int i = 1; i <= n; i++) {
            char c = s.charAt(i - 1);
            int index = c - 'a';

            // keep the character or append c
            dp[i] = (2 * dp[i - 1]) % MOD;

            // if c appeared before, remove duplicate subsequences
            if (last[index] != -1) {
                dp[i] = (dp[i] - dp[last[index]] + MOD) % MOD;
            }

            // store the current position
            last[index] = i - 1;
        }

        // remove the empty subsequence
        return (int) ((dp[n] - 1 + MOD) % MOD);
    }

}

//Complexity:
// time - O(n)
// space - O(n + 26)


//Given a string s, return the number of distinct non-empty subsequences of s. Since the answer may be very large,
// return it modulo 109 + 7.
//A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of
// the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a
// subsequence of "abcde" while "aec" is not.

//Example 1:
//Input: s = "abc"
//Output: 7
//Explanation: The 7 distinct subsequences are "a", "b", "c", "ab", "ac", "bc", and "abc".

//Example 2:
//Input: s = "aba"
//Output: 6
//Explanation: The 6 distinct subsequences are "a", "b", "ab", "aa", "ba", and "aba".

//Example 3:
//Input: s = "aaa"
//Output: 3
//Explanation: The 3 distinct subsequences are "a", "aa" and "aaa".

//Constraints:
//1 <= s.length <= 2000
//s consists of lowercase English letters.
