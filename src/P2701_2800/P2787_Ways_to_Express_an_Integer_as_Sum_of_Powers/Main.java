package P2701_2800.P2787_Ways_to_Express_an_Integer_as_Sum_of_Powers;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.numberOfWays(10, 2)); // Output: 1
        System.out.println(solution.numberOfWays(4, 1));  // Output: 2
        System.out.println(solution.numberOfWays(160, 3)); // Example case
    }

    static final int MOD = 1_000_000_007;
    static int[][] dp;

    public static int numberOfWays(int n, int x) {
        dp = new int[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = -1;
            }
        }

        return solve(n, 1, x);
    }

    private static int solve(int remaining, int current, int x) {
        if (remaining == 0) return 1; // found one way
        if (remaining < 0) return 0;

        // calculate current^x
        int power = (int) Math.pow(current, x);
        if (power > remaining) return 0;

        if (dp[remaining][current] != -1) {
            return dp[remaining][current];
        }

        // include current + Exclude current
        int include = solve(remaining - power, current + 1, x) % MOD;
        int exclude = solve(remaining, current + 1, x) % MOD;

        return dp[remaining][current] = (include + exclude) % MOD;
    }

}

//Complexity:
// time - O(n * sqrt[n])
// space - O(n^2)


//Given two positive integers n and x.
//Return the number of ways n can be expressed as the sum of the xth power of unique positive integers, in other
// words, the number of sets of unique integers [n1, n2, ..., nk] where n = n1x + n2x + ... + nkx.
//Since the result can be very large, return it modulo 109 + 7.
//For example, if n = 160 and x = 3, one way to express n is n = 23 + 33 + 53.

//Example 1:
//Input: n = 10, x = 2
//Output: 1
//Explanation: We can express n as the following: n = 32 + 12 = 10.
//It can be shown that it is the only way to express 10 as the sum of the 2nd power of unique integers.

//Example 2:
//Input: n = 4, x = 1
//Output: 2
//Explanation: We can express n in the following ways:
//- n = 41 = 4.
//- n = 31 + 11 = 4.

//Constraints:
//1 <= n <= 300
//1 <= x <= 5
