package P2701_2800.P2719_Count_of_Integers;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.count("1", "12", 1, 8));  // Output: 11
        System.out.println(solution.count("1", "5", 1, 5));   // Output: 5
    }

    private static final int MOD = 1_000_000_007;
    private String num1, num2;
    private int minSum, maxSum;
    private int[][][] dp;

    public int count(String num1, String num2, int min_sum, int max_sum) {
        this.num1 = num1;
        this.num2 = num2;
        this.minSum = min_sum;
        this.maxSum = max_sum;

        // Calculate the result for numbers in the range [1, num2]
        int resultNum2 = countValidNumbers(num2);

        // Calculate the result for numbers in the range [1, num1-1]
        int resultNum1 = countValidNumbers(num1);

        // Subtract to get the result in the range [num1, num2]
        // We add the count for num1 itself if it's valid
        int count = (resultNum2 - resultNum1 + MOD) % MOD;
        count = (count + (isValid(num1) ? 1 : 0)) % MOD;

        return count;
    }

    private int countValidNumbers(String num) {
        int n = num.length();
        dp = new int[n + 1][maxSum + 1][2];  // dp[pos][currentSum][tight]
        for (int[][] arr : dp) {
            for (int[] subArr : arr) {
                Arrays.fill(subArr, -1);
            }
        }

        return countHelper(0, 0, true, num);
    }

    private int countHelper(int pos, int currentSum, boolean tight, String num) {
        if (currentSum > maxSum) {
            return 0;  // Prune branches with sum exceeding maxSum
        }
        if (pos == num.length()) {
            return (currentSum >= minSum && currentSum <= maxSum) ? 1 : 0;
        }
        if (dp[pos][currentSum][tight ? 1 : 0] != -1) {
            return dp[pos][currentSum][tight ? 1 : 0];
        }

        int limit = tight ? num.charAt(pos) - '0' : 9;
        int res = 0;

        for (int digit = 0; digit <= limit; digit++) {
            res = (res + countHelper(pos + 1, currentSum + digit, tight && digit == limit, num)) % MOD;
        }

        dp[pos][currentSum][tight ? 1 : 0] = res;
        return res;
    }

    private boolean isValid(String number) {
        int sum = 0;
        for (char c : number.toCharArray()) {
            sum += c - '0';
        }
        return sum >= minSum && sum <= maxSum;
    }
}

//Explanation of the Code:
//countGoodNumbers: This is the main method that computes the number of good integers between num1 and
// num2. It calculates the count of valid integers for num2, subtracts the count for numbers strictly less
// than num1, and adds one if num1 itself is a valid number.
//countValidNumbers: This method counts the number of valid integers up to a given number num. It uses a dynamic
// programming table dp to store subproblem results, where dp[pos][currentSum][tight] represents the number of
// valid numbers starting from position pos with the current sum currentSum and tight indicating whether the
// number being built is still "tight" to the given number.
//countHelper: This is the recursive helper method that builds valid numbers digit by digit, updating the current
// sum and handling the tight condition to ensure we only consider valid numbers.
//isValid: This utility method checks if a given number has a digit sum between min_sum and max_sum.
//Time Complexity:
//The time complexity of this solution is proportional to the number of digits in the input
// numbers (num1 and num2) times the maximum sum of digits (max_sum). Since the maximum length of the numbers is
// around 22 digits (as num2 can go up to 10^22), the time complexity is efficient for the given constraints.



//You are given two numeric strings num1 and num2 and two integers max_sum and min_sum. We denote an
// integer x to be good if:
//num1 <= x <= num2
//min_sum <= digit_sum(x) <= max_sum.
//Return the number of good integers. Since the answer may be large, return it modulo 109 + 7.
//Note that digit_sum(x) denotes the sum of the digits of x.
//
//Example 1:
//Input: num1 = "1", num2 = "12", min_sum = 1, max_sum = 8
//Output: 11
//Explanation: There are 11 integers whose sum of digits lies between 1 and 8 are 1,2,3,4,5,6,7,8,10,11, and
// 12. Thus, we return 11.

//Example 2:
//Input: num1 = "1", num2 = "5", min_sum = 1, max_sum = 5
//Output: 5
//Explanation: The 5 integers whose sum of digits lies between 1 and 5 are 1,2,3,4, and 5. Thus, we return 5.
//
//Constraints:
//1 <= num1 <= num2 <= 1022
//1 <= min_sum <= max_sum <= 400