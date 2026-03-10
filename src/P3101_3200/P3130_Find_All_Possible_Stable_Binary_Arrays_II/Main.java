package P3101_3200.P3130_Find_All_Possible_Stable_Binary_Arrays_II;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.numberOfStableArrays(1,1,2)); // 2
        System.out.println(solution.numberOfStableArrays(1,2,1)); // 1
        System.out.println(solution.numberOfStableArrays(3,3,2)); // 14
    }

    private static final int MOD = 1_000_000_007;
    private long[][][] dp;
    private int limit;

    public int numberOfStableArrays(int zero, int one, int limit) {
        this.limit = limit;

        dp = new long[zero + 1][one + 1][2];

        for (int i = 0; i <= zero; i++) {
            for (int j = 0; j <= one; j++) {
                dp[i][j][0] = -1;
                dp[i][j][1] = -1;
            }
        }

        long res = (dfs(zero, one, 0) + dfs(zero, one, 1)) % MOD;

        return (int) res;
    }

    private long dfs(int zerosLeft, int onesLeft, int last) {

        if (zerosLeft < 0 || onesLeft < 0) {
            return 0;
        }

        if (zerosLeft == 0) {
            return (last == 1 && onesLeft <= limit) ? 1 : 0;
        }

        if (onesLeft == 0) {
            return (last == 0 && zerosLeft <= limit) ? 1 : 0;
        }

        if (dp[zerosLeft][onesLeft][last] != -1) {
            return dp[zerosLeft][onesLeft][last];
        }

        long result;

        if (last == 0) {

            result =
                    (dfs(zerosLeft - 1, onesLeft, 0)
                            + dfs(zerosLeft - 1, onesLeft, 1)
                            - dfs(zerosLeft - limit - 1, onesLeft, 1)
                            + MOD) % MOD;

        } else {

            result =
                    (dfs(zerosLeft, onesLeft - 1, 0)
                            + dfs(zerosLeft, onesLeft - 1, 1)
                            - dfs(zerosLeft, onesLeft - limit - 1, 0)
                            + MOD) % MOD;
        }

        dp[zerosLeft][onesLeft][last] = result;
        return result;
    }

}

//Complexity:
// time and space - O(zero * one)


//You are given 3 positive integers zero, one, and limit.
//A binary array arr is called stable if:
//The number of occurrences of 0 in arr is exactly zero.
//The number of occurrences of 1 in arr is exactly one.
//Each subarray of arr with a size greater than limit must contain both 0 and 1.
//Return the total number of stable binary arrays.
//Since the answer may be very large, return it modulo 109 + 7.

//Example 1:
//Input: zero = 1, one = 1, limit = 2
//Output: 2
//Explanation:
//The two possible stable binary arrays are [1,0] and [0,1].

//Example 2:
//Input: zero = 1, one = 2, limit = 1
//Output: 1
//Explanation:
//The only possible stable binary array is [1,0,1].

//Example 3:
//Input: zero = 3, one = 3, limit = 2
//Output: 14
//Explanation:
//All the possible stable binary arrays are
// [0,0,1,0,1,1], [0,0,1,1,0,1], [0,1,0,0,1,1],
// [0,1,0,1,0,1], [0,1,0,1,1,0], [0,1,1,0,0,1],
// [0,1,1,0,1,0], [1,0,0,1,0,1], [1,0,0,1,1,0],
// [1,0,1,0,0,1], [1,0,1,0,1,0], [1,0,1,1,0,0],
// [1,1,0,0,1,0], and [1,1,0,1,0,0].

//Constraints:
//1 <= zero, one, limit <= 1000
