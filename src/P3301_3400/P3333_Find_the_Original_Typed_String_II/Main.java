package P3301_3400.P3333_Find_the_Original_Typed_String_II;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.possibleStringCount("aabbccdd", 7)); // Output: 5
        System.out.println(solution.possibleStringCount("aabbccdd", 8)); // Output: 1
        System.out.println(solution.possibleStringCount("aaabbb", 3));   // Output: 8
    }


    static final int MOD = 1_000_000_007;

    public int possibleStringCount(String word, int k) {
        List<Integer> groups = getConsecutiveLetters(word);

        // calculate total combinations (product of group sizes)
        long totalCombinations = 1;
        for (int group : groups) {
            totalCombinations = (totalCombinations * group) % MOD;
        }

        // if k <= number of groups, all combinations are valid
        if (k <= groups.size()) {
            return (int) totalCombinations;
        }

        // DP: dp[j] = number of ways to form length j using first i groups
        int[] dp = new int[k];
        dp[0] = 1; // base case: empty string

        for (int group : groups) {
            int[] newDp = new int[k];
            int windowSum = 0;

            for (int j = 0; j < k; ++j) {
                // add current window sum
                newDp[j] = (newDp[j] + windowSum) % MOD;

                // expand window
                windowSum = (windowSum + dp[j]) % MOD;

                // shrink window if needed
                if (j >= group) {
                    windowSum = (windowSum - dp[j - group] + MOD) % MOD;
                }
            }

            dp = newDp;
        }

        // sum all invalid combinations (length < k)
        int invalidCombinations = 0;

        for (int count : dp) {
            invalidCombinations = (invalidCombinations + count) % MOD;
        }

        // result = total - invalid
        return (int) ((totalCombinations - invalidCombinations + MOD) % MOD);
    }


    private List<Integer> getConsecutiveLetters(String word) {
        List<Integer> groups = new ArrayList<>();
        int count = 1;

        for (int i = 1; i < word.length(); ++i) {
            if (word.charAt(i) == word.charAt(i - 1)) {
                count++;
            } else {
                groups.add(count);
                count = 1;
            }
        }

        groups.add(count);

        return groups;
    }

}

//Complexity:
// time - O(n * k)
// space - O(n)


//Alice is attempting to type a specific string on her computer. However, she tends to be clumsy and may press a
// key for too long, resulting in a character being typed multiple times.
//You are given a string word, which represents the final output displayed on Alice's screen. You are also given a
// positive integer k.
//Return the total number of possible original strings that Alice might have intended to type, if she was trying to
// type a string of size at least k.
//Since the answer may be very large, return it modulo 109 + 7.

//Example 1:
//Input: word = "aabbccdd", k = 7
//Output: 5
//Explanation:
//The possible strings are: "aabbccdd", "aabbccd", "aabbcdd", "aabccdd", and "abbccdd".

//Example 2:
//Input: word = "aabbccdd", k = 8
//Output: 1
//Explanation:
//The only possible string is "aabbccdd".

//Example 3:
//Input: word = "aaabbb", k = 3
//Output: 8

//Constraints:
//1 <= word.length <= 5 * 105
//word consists only of lowercase English letters.
//1 <= k <= 2000
