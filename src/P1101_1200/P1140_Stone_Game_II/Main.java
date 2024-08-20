package P1101_1200.P1140_Stone_Game_II;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] piles1 = {2, 7, 9, 4, 4};
        int[] piles2 = {1, 2, 3, 4, 5, 100};

        System.out.println(solution.stoneGameII(piles1));  // Output: 10
        System.out.println(solution.stoneGameII(piles2));  // Output: 104

    }

//    public int stoneGameII(int[] piles) {
//        int n = piles.length;
//
//        // Suffix sum array to calculate the total number of stones from pile i to the end
//        int[] suffixSum = new int[n + 1];
//        suffixSum[n] = 0;
//
//        for (int i = n - 1; i >= 0; i--) {
//            suffixSum[i] = suffixSum[i + 1] + piles[i];
//        }
//
//        // dp[i][M] represents the maximum stones Alice can get starting from pile i with M as the current M value
//        int[][] dp = new int[n][n + 1];
//
//        for (int i = n - 1; i >= 0; i--) {
//            for (int M = 1; M <= n; M++) {
//                for (int X = 1; X <= 2 * M && i + X <= n; X++) {
//                    if (i + X >= n) {
//                        dp[i][M] = suffixSum[i];
//                    } else {
//                        dp[i][M] = Math.max(dp[i][M], suffixSum[i] - dp[i + X][Math.max(M, X)]);
//                    }
//                }
//            }
//        }
//
//        // The answer is the maximum number of stones Alice can get starting from the first pile with M = 1
//        return dp[0][1];
//    }

    //faster solution
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        int[][] memo = new int[n][n + 1];

        // Initialize memoization array to -1, indicating uncalculated states
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        // Suffix sum array to calculate the total number of stones from pile i to the end
        int[] suffixSum = new int[n + 1];
        suffixSum[n] = 0;

        for (int i = n - 1; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }

        // Start the recursive DP process with M = 1 from the first pile
        return dp(0, 1, piles, suffixSum, memo);
    }

    private int dp(int i, int M, int[] piles, int[] suffixSum, int[][] memo) {
        int n = piles.length;

        // Base case: if we're at or beyond the last pile, return 0
        if (i == n) {
            return 0;
        }

        // If the current state has been computed before, return its value
        if (memo[i][M] != -1) {
            return memo[i][M];
        }

        // Try taking between 1 and 2 * M piles and maximize Alice's result
        int maxStones = 0;
        for (int X = 1; X <= 2 * M && i + X <= n; X++) {
            // Calculate how many stones Alice can get if she takes X piles
            maxStones = Math.max(maxStones, suffixSum[i] - dp(i + X, Math.max(M, X), piles, suffixSum, memo));
        }

        // Store the result in memoization array and return it
        memo[i][M] = maxStones;
        return maxStones;
    }
}


//Alice and Bob continue their games with piles of stones.  There are a number of piles arranged in a row, and each
// pile has a positive integer number of stones piles[i].  The objective of the game is to end with the most stones.
//Alice and Bob take turns, with Alice starting first.  Initially, M = 1.
//On each player's turn, that player can take all the stones in the first X remaining piles, where 1 <= X <= 2M.  Then,
// we set M = max(M, X).
//The game continues until all the stones have been taken.
//Assuming Alice and Bob play optimally, return the maximum number of stones Alice can get.
//
//Example 1:
//Input: piles = [2,7,9,4,4]
//Output: 10
//Explanation:  If Alice takes one pile at the beginning, Bob takes two piles, then Alice takes 2 piles again. Alice
// can get 2 + 4 + 4 = 10 piles in total. If Alice takes two piles at the beginning, then Bob can take all three
// piles left. In this case, Alice get 2 + 7 = 9 piles in total. So we return 10 since it's larger.

//Example 2:
//Input: piles = [1,2,3,4,5,100]
//Output: 104
//
//Constraints:
//1 <= piles.length <= 100
//1 <= piles[i] <= 104
