package P3501_3600.P3573_Best_Time_to_Buy_and_Sell_Stock_V;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.maximumProfit(
                new int[]{1,7,9,8,2}, 2)); // 14

        System.out.println(solution.maximumProfit(
                new int[]{12,16,19,19,8,1,19,13,9}, 3)); // 36
    }

    public long maximumProfit(int[] prices, int k) {
        int n = prices.length;
        long NEG = Long.MIN_VALUE / 4;

        // dp[t][0] = free
        // dp[t][1] = holding long
        // dp[t][2] = holding short
        long[][] dp = new long[k + 1][3];

        for (int t = 0; t <= k; t++) {
            dp[t][0] = NEG;
            dp[t][1] = NEG;
            dp[t][2] = NEG;
        }
        dp[0][0] = 0;

        for (int price : prices) {
            long[][] next = new long[k + 1][3];
            for (int t = 0; t <= k; t++) {
                Arrays.fill(next[t], NEG);
            }

            for (int t = 0; t <= k; t++) {
                // stay free
                next[t][0] = Math.max(next[t][0], dp[t][0]);

                // open long
                next[t][1] = Math.max(next[t][1], dp[t][0] - price);

                // open short
                next[t][2] = Math.max(next[t][2], dp[t][0] + price);

                // continue holding long
                next[t][1] = Math.max(next[t][1], dp[t][1]);

                // continue holding short
                next[t][2] = Math.max(next[t][2], dp[t][2]);

                // close long
                if (t + 1 <= k) {
                    next[t + 1][0] =
                            Math.max(next[t + 1][0], dp[t][1] + price);
                }

                // close short
                if (t + 1 <= k) {
                    next[t + 1][0] =
                            Math.max(next[t + 1][0], dp[t][2] - price);
                }
            }

            dp = next;
        }

        long ans = 0;

        for (int t = 0; t <= k; t++) {
            ans = Math.max(ans, dp[t][0]);
        }

        return ans;
    }

}

//Complexity:
// time - O(n * k)
// space - O(k)


//You are given an integer array prices where prices[i] is the price of a stock in dollars on the ith day, and an
// integer k.
//You are allowed to make at most k transactions, where each transaction can be either of the following:
//Normal transaction: Buy on day i, then sell on a later day j where i < j. You profit prices[j] - prices[i].
//Short selling transaction: Sell on day i, then buy back on a later day j where i < j. You profit prices[i] - prices[j].
//Note that you must complete each transaction before starting another. Additionally, you can't buy or sell on the
// same day you are selling or buying back as part of a previous transaction.
//Return the maximum total profit you can earn by making at most k transactions.

//Example 1:
//Input: prices = [1,7,9,8,2], k = 2
//Output: 14
//Explanation:
//We can make $14 of profit through 2 transactions:
//A normal transaction: buy the stock on day 0 for $1 then sell it on day 2 for $9.
//A short selling transaction: sell the stock on day 3 for $8 then buy back on day 4 for $2.

//Example 2:
//Input: prices = [12,16,19,19,8,1,19,13,9], k = 3
//Output: 36
//Explanation:
//We can make $36 of profit through 3 transactions:
//A normal transaction: buy the stock on day 0 for $12 then sell it on day 2 for $19.
//A short selling transaction: sell the stock on day 3 for $19 then buy back on day 4 for $8.
//A normal transaction: buy the stock on day 5 for $1 then sell it on day 6 for $19.

//Constraints:
//2 <= prices.length <= 103
//1 <= prices[i] <= 109
//1 <= k <= prices.length / 2
