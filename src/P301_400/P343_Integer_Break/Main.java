package P301_400.P343_Integer_Break;

public class Main {

    public static void main(String[] args) {
        System.out.println(integerBreak(10));
        System.out.println(integerBreak(2));
    }

    public static int integerBreak(int n) {
        //handle base cases
        if (n <= 3) {
            return n - 1;
        }

        //initialize an array to store the maximum products
        int[] dp = new int[n + 1];

        //initialize values for n = 2 and n = 3
        dp[2] = 2;
        dp[3] = 3;

        //fill the dp array from 4 to n
        for (int i = 4; i <= n; i++) {
            dp[i] = Math.max(dp[i - 2] * 2, dp[i - 3] * 3);
        }

        return dp[n]; //bingo

    }
}

//Given an integer n, break it into the sum of k positive integers, where k >= 2, and maximize the product of
// those integers.
//Return the maximum product you can get.

//Example 1:
//Input: n = 2
//Output: 1
//Explanation: 2 = 1 + 1, 1 × 1 = 1.

//Example 2:
//Input: n = 10
//Output: 36
//Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.

//Constraints:
//2 <= n <= 58
