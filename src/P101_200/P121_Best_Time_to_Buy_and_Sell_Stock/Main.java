package P101_200.P121_Best_Time_to_Buy_and_Sell_Stock;

public class Main {

    public static void main(String[] args) {
        int[] test1 = {7,1,5,3,6,4};
        int[] test2 = {7,6,4,3,1};
        int[] test3 = {2,4,1};
        System.out.println(maxProfit(test3));
    }

    public static int maxProfit(int[] prices) {

        int maxProfit = 0; //start max profit
        int minPrice = prices[0]; //start minimal price

        for (int i = 1; i < prices.length; i++) {
            int price = prices[i]; //current price on day "i"

            int potentialProfit = price - minPrice; //calculate potential profit

            maxProfit = Math.max(maxProfit, potentialProfit); //equals potential and current max profits

            minPrice = Math.min(minPrice, price); //restart minimal price
        }

        return maxProfit; //return max profit

    }
}

//You are given an array prices where prices[i] is the price of a given stock on the ith day.
//You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the
// future to sell that stock.
//Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.

//Example 1:
//Input: prices = [7,1,5,3,6,4]
//Output: 5
//Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
//Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.

//Example 2:
//Input: prices = [7,6,4,3,1]
//Output: 0
//Explanation: In this case, no transactions are done and the max profit = 0.

//Constraints:
//1 <= prices.length <= 105
//0 <= prices[i] <= 104
