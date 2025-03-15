package P901_1000.P935_Knight_Dialer;

public class Main {

    public static void main(String[] args) {
        System.out.println(knightDialer(1));    // Output: 10
        System.out.println(knightDialer(2));    // Output: 20
        System.out.println(knightDialer(3131)); // Output: 136006598
    }

    public static int knightDialer(int n) {
        int MOD = 1_000_000_007;

        if (n == 1) {
            return 10;
        }

        //create a 2D array to store the number of ways to reach each numeric cell with a given number of jumps
        long[][] dp = new long[n][10];

        //initialize the base case for one jump
        for (int i = 0; i < 10; i++) {
            dp[0][i] = 1;
        }

        //define the possible moves for the knight
        int[][] moves = {
                {4, 6},   //knight moves from 0
                {6, 8},   //knight moves from 1
                {7, 9},   //knight moves from 2
                {4, 8},   //knight moves from 3
                {3, 9, 0}, //knight moves from 4
                {},       //knight moves from 5 (no valid moves)
                {1, 7, 0}, //knight moves from 6
                {2, 6},   //knight moves from 7
                {1, 3},   //knight moves from 8
                {2, 4}    //knight moves from 9
        };

        //fill in the dp array using dynamic programming
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 10; j++) {
                for (int move : moves[j]) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][move]) % MOD;
                }
            }
        }

        //sum up the number of ways to reach each numeric cell after n - 1 jumps
        long result = 0;
        for (int i = 0; i < 10; i++) {
            result = (result + dp[n - 1][i]) % MOD;
        }

        return (int) result; //bingo
    }

}


//The chess knight has a unique movement, it may move two squares vertically and one square horizontally, or two
// squares horizontally and one square vertically (with both forming the shape of an L). The possible movements of
// chess knight are shown in this diagram:
//A chess knight can move as indicated in the chess diagram below:
//We have a chess knight and a phone pad as shown below, the knight can only stand on a numeric cell (i.e. blue cell).
//Given an integer n, return how many distinct phone numbers of length n we can dial.
//You are allowed to place the knight on any numeric cell initially and then you should perform n - 1 jumps to
// dial a number of length n. All jumps should be valid knight jumps.
//As the answer may be very large, return the answer modulo 109 + 7.

//Example 1:
//Input: n = 1
//Output: 10
//Explanation: We need to dial a number of length 1, so placing the knight over any numeric cell of
// the 10 cells is sufficient.

//Example 2:
//Input: n = 2
//Output: 20
//Explanation: All the valid number we can dial
// are [04, 06, 16, 18, 27, 29, 34, 38, 40, 43, 49, 60, 61, 67, 72, 76, 81, 83, 92, 94]

//Example 3:
//Input: n = 3131
//Output: 136006598
//Explanation: Please take care of the mod.

//Constraints:
//1 <= n <= 5000
