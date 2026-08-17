package P1501_1600.P1563_Stone_Game_V;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] stoneValue1 = {6, 2, 3, 4, 5, 5};
        System.out.println(solution.stoneGameV(stoneValue1)); // 18

        int[] stoneValue2 = {7, 7, 7, 7, 7, 7, 7};
        System.out.println(solution.stoneGameV(stoneValue2)); // 28

        int[] stoneValue3 = {4};
        System.out.println(solution.stoneGameV(stoneValue3)); // 0
    }

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;

        // prefix[i] = sum of stoneValue[0..i-1]
        long[] prefix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }

        // dp[l][r] = maximum score Alice can get from subarray [l..r]
        int[][] dp = new int[n][n];

        // process shorter intervals first
        for (int len = 2; len <= n; len++) {

            for (int l = 0; l + len <= n; l++) {

                int r = l + len - 1;

                for (int k = l; k < r; k++) {

                    long leftSum = prefix[k + 1] - prefix[l];
                    long rightSum = prefix[r + 1] - prefix[k + 1];

                    if (leftSum < rightSum) {

                        // Bob throws away the right part
                        dp[l][r] = Math.max(
                                dp[l][r],
                                (int) leftSum + dp[l][k]
                        );

                    } else if (leftSum > rightSum) {

                        // Bob throws away the left part
                        dp[l][r] = Math.max(
                                dp[l][r],
                                (int) rightSum + dp[k + 1][r]
                        );

                    } else {

                        // equal sums - Alice chooses which part remains
                        dp[l][r] = Math.max(
                                dp[l][r],
                                Math.max(
                                        (int) leftSum + dp[l][k],
                                        (int) rightSum + dp[k + 1][r]
                                )
                        );
                    }
                }
            }
        }

        return dp[0][n - 1];
    }

}

//Complexity:
// time - O(n^3)
// space - O(n^2)


//There are several stones arranged in a row, and each stone has an associated value which is an integer given in
// the array stoneValue.
//In each round of the game, Alice divides the row into two non-empty rows (i.e. left row and right row), then Bob
// calculates the value of each row which is the sum of the values of all the stones in this row. Bob throws away
// the row which has the maximum value, and Alice's score increases by the value of the remaining row. If the value of
// the two rows are equal, Bob lets Alice decide which row will be thrown away. The next round starts with the
// remaining row.
//The game ends when there is only one stone remaining. Alice's score is initially zero.
//Return the maximum score that Alice can obtain.

//Example 1:
//Input: stoneValue = [6,2,3,4,5,5]
//Output: 18
//Explanation: In the first round, Alice divides the row to [6,2,3], [4,5,5]. The left row has the value 11 and the
// right row has value 14. Bob throws away the right row and Alice's score is now 11.
//In the second round Alice divides the row to [6], [2,3]. This time Bob throws away the left row and Alice's score
// becomes 16 (11 + 5).
//The last round Alice has only one choice to divide the row which is [2], [3]. Bob throws away the right row and
// Alice's score is now 18 (16 + 2). The game ends because only one stone is remaining in the row.

//Example 2:
//Input: stoneValue = [7,7,7,7,7,7,7]
//Output: 28

//Example 3:
//Input: stoneValue = [4]
//Output: 0

//Constraints:
//1 <= stoneValue.length <= 500
//1 <= stoneValue[i] <= 106
