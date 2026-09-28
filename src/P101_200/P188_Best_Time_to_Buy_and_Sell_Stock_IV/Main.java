package P101_200.P188_Best_Time_to_Buy_and_Sell_Stock_IV;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int k1 = 2;
        int[] prices1 = {2, 4, 1};
        System.out.println(solution.maxProfit(k1, prices1)); // 2

        int k2 = 2;
        int[] prices2 = {3, 2, 6, 5, 0, 3};

        System.out.println(solution.maxProfit(k2, prices2)); // 7
    }

    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length < 2 || k == 0) {
            return 0;
        }

        int[] buy = new int[k + 1];
        int[] sell = new int[k + 1];

        Arrays.fill(buy, Integer.MIN_VALUE / 2);

        for (int price : prices) {
            for (int t = 1; t <= k; t++) {
                buy[t] = Math.max(buy[t], sell[t - 1] - price);
                sell[t] = Math.max(sell[t], buy[t] + price);
            }
        }

        return sell[k];
    }

}

//Complexity:
// time - O(n * k)
// space - O(k)


//You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.
//Find the maximum profit you can achieve. You may complete at most k transactions: i.e. you may buy at most k times
// and sell at most k times.
//Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

//Example 1:
//Input: k = 2, prices = [2,4,1]
//Output: 2
//Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.

//Example 2:
//Input: k = 2, prices = [3,2,6,5,0,3]
//Output: 7
//Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on
// day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.

//Constraints:
//1 <= k <= 100
//1 <= prices.length <= 1000
//0 <= prices[i] <= 1000
