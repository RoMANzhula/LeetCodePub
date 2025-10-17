package P3001_3100.P3003_Maximize_the_Number_of_Partitions_After_Operations;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.maxPartitionsAfterOperations("accca", 2)); // 3
        System.out.println(solution.maxPartitionsAfterOperations("aabaab", 3)); // 1
        System.out.println(solution.maxPartitionsAfterOperations("xxyz", 1)); // 4
    }

    public int maxPartitionsAfterOperations(String s, int k) {
        Map<Long, Integer> memo = new HashMap<>();

        return dfs(s, 0, true, 0, k, memo) + 1;
    }

    private int dfs(String s, int i, boolean canChange, int mask, int k, Map<Long, Integer> memo) {
        if (i == s.length()) return 0;

        long key = (((long) i) << 27) | ((canChange ? 1L : 0L) << 26) | mask;

        if (memo.containsKey(key)) return memo.get(key);

        int res = getRes(s, i, canChange, mask, 1 << (s.charAt(i) - 'a'), k, memo);

        if (canChange) {
            for (int j = 0; j < 26; ++j) {
                res = Math.max(res, getRes(s, i, false, mask, 1 << j, k, memo));
            }
        }

        memo.put(key, res);

        return res;
    }

    private int getRes(String s, int i, boolean nextCanChange, int mask, int newBit, int k, Map<Long, Integer> memo) {
        int newMask = mask | newBit;

        if (Integer.bitCount(newMask) > k) {
            // need to start a new partition
            return 1 + dfs(s, i + 1, nextCanChange, newBit, k, memo);
        }

        return dfs(s, i + 1, nextCanChange, newMask, k, memo);
    }

}

//Complexity:
// time - O(n * 26 * 2)
// space - O(n * 2)


//You are given a string s and an integer k.
//First, you are allowed to change at most one index in s to another lowercase English letter.
//After that, do the following partitioning operation until s is empty:
//Choose the longest prefix of s containing at most k distinct characters.
//Delete the prefix from s and increase the number of partitions by one. The remaining characters (if any) in s
// maintain their initial order.
//Return an integer denoting the maximum number of resulting partitions after the operations by optimally choosing
// at most one index to change.

//Example 1:
//Input: s = "accca", k = 2
//Output: 3
//Explanation:
//The optimal way is to change s[2] to something other than a and c, for example, b. then it becomes "acbca".
//Then we perform the operations:
//The longest prefix containing at most 2 distinct characters is "ac", we remove it and s becomes "bca".
//Now The longest prefix containing at most 2 distinct characters is "bc", so we remove it and s becomes "a".
//Finally, we remove "a" and s becomes empty, so the procedure ends.
//Doing the operations, the string is divided into 3 partitions, so the answer is 3.

//Example 2:
//Input: s = "aabaab", k = 3
//Output: 1
//Explanation:
//Initially s contains 2 distinct characters, so whichever character we change, it will contain at most 3 distinct
// characters, so the longest prefix with at most 3 distinct characters would always be all of it, therefore
// the answer is 1.

//Example 3:
//Input: s = "xxyz", k = 1
//Output: 4
//Explanation:
//The optimal way is to change s[0] or s[1] to something other than characters in s, for example, to change s[0] to w.
//Then s becomes "wxyz", which consists of 4 distinct characters, so as k is 1, it will divide into 4 partitions.

//Constraints:
//1 <= s.length <= 104
//s consists only of lowercase English letters.
//1 <= k <= 26
