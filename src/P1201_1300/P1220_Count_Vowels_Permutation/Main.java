package P1201_1300.P1220_Count_Vowels_Permutation;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        System.out.println(countStrings(5));
        System.out.println(countStrings(2));
        System.out.println(countStrings(1));
        System.out.println(countStrings(144));
    }

    public static int countStrings(int n) {
        int MOD = 1000000007;
        long[] dp = new long[5]; //dp[j] represents the number of valid strings ending with vowel j

        // initialize the base case
        Arrays.fill(dp, 1);

        for (int i = 2; i <= n; i++) {
            long[] newDp = new long[5];
            newDp[0] = (dp[1]) % MOD; //'a' can only be followed by 'e'
            newDp[1] = (dp[0] + dp[2]) % MOD; //'e' can be followed by 'a' or 'i'
            newDp[2] = (dp[0] + dp[1] + dp[3] + dp[4]) % MOD; //'i' can't be followed by 'i'
            newDp[3] = (dp[2] + dp[4]) % MOD; //'o' can be followed by 'i' or 'u'
            newDp[4] = (dp[0]) % MOD; //'u' can only be followed by 'a'

            dp = Arrays.copyOf(newDp, 5);
        }

        long totalCount = 0;
        //summarize
        for (int j = 0; j < 5; j++) {
            totalCount = (totalCount + dp[j]) % MOD;
        }

        return (int)totalCount; //bingo
    }
}

//Given an integer n, your task is to count how many strings of length n can be formed under the following rules:
//Each character is a lower case vowel ('a', 'e', 'i', 'o', 'u')
//Each vowel 'a' may only be followed by an 'e'.
//Each vowel 'e' may only be followed by an 'a' or an 'i'.
//Each vowel 'i' may not be followed by another 'i'.
//Each vowel 'o' may only be followed by an 'i' or a 'u'.
//Each vowel 'u' may only be followed by an 'a'.
//Since the answer may be too large, return it modulo 10^9 + 7.

//Example 1:
//Input: n = 1
//Output: 5
//Explanation: All possible strings are: "a", "e", "i" , "o" and "u".

//Example 2:
//Input: n = 2
//Output: 10
//Explanation: All possible strings are: "ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou" and "ua".

//Example 3:
//Input: n = 5
//Output: 68

//Constraints:
//1 <= n <= 2 * 10^4
