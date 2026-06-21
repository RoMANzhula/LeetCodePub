package P1801_1900.P1833_Maximum_Ice_Cream_Bars;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] costs1 = {1,3,2,4,1};
        int coins1 = 7;
        System.out.println(solution.maxIceCream(costs1, coins1)); // 4

        int[] costs2 = {10,6,8,7,7,8};
        int coins2 = 5;
        System.out.println(solution.maxIceCream(costs2, coins2)); // 0

        int[] costs3 = {7,3,3,6,6,6,10,5,9,2};
        int coins3 = 56;
        System.out.println(solution.maxIceCream(costs3, coins3)); // 10
    }

    public int maxIceCream(int[] costs, int coins) {
        int maxCost = 100000;

        // counting sort array
        int[] freq = new int[maxCost + 1];

        // fill frequency
        for (int cost : costs) {
            freq[cost]++;
        }

        int count = 0;

        // traverse from cheapest to most expensive
        for (int cost = 1; cost <= maxCost; cost++) {
            if (freq[cost] == 0) continue;

            // try to buy as many bars of this cost as possible
            int canBuy = Math.min(freq[cost], coins / cost);

            count += canBuy;
            coins -= canBuy * cost;

            if (coins < cost) {
                // can't afford any more of this or higher costs
                break;
            }
        }

        return count;
    }

}

//Complexity:
// time - O(n + maxCost)
// space - O(maxCost)


//It is a sweltering summer day, and a boy wants to buy some ice cream bars.
//At the store, there are n ice cream bars. You are given an array costs of length n, where costs[i] is the price of
// the ith ice cream bar in coins. The boy initially has coins coins to spend, and he wants to buy as many ice cream
// bars as possible.
//Note: The boy can buy the ice cream bars in any order.
//Return the maximum number of ice cream bars the boy can buy with coins coins.
//You must solve the problem by counting sort.

//Example 1:
//Input: costs = [1,3,2,4,1], coins = 7
//Output: 4
//Explanation: The boy can buy ice cream bars at indices 0,1,2,4 for a total price of 1 + 3 + 2 + 1 = 7.

//Example 2:
//Input: costs = [10,6,8,7,7,8], coins = 5
//Output: 0
//Explanation: The boy cannot afford any of the ice cream bars.

//Example 3:
//Input: costs = [1,6,3,1,2,5], coins = 20
//Output: 6
//Explanation: The boy can buy all the ice cream bars for a total price of 1 + 6 + 3 + 1 + 2 + 5 = 18.

//Constraints:
//costs.length == n
//1 <= n <= 105
//1 <= costs[i] <= 105
//1 <= coins <= 108
