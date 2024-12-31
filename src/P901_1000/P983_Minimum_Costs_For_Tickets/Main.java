package P901_1000.P983_Minimum_Costs_For_Tickets;

import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        // Example 1
        int[] days1 = {1, 4, 6, 7, 8, 20};
        int[] costs1 = {2, 7, 15};
        System.out.println(solution.minCostTickets(days1, costs1)); // Output: 11

        // Example 2
        int[] days2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31};
        int[] costs2 = {2, 7, 15};
        System.out.println(solution.minCostTickets(days2, costs2)); // Output: 17
    }

    public int minCostTickets(int[] days, int[] costs) {
        // set max num of days in a year
        int maxDay = 365;

        //set to quickly check if a day is a travel day
        Set<Integer> travelDays = new HashSet<>();
        for (int day : days) {
            travelDays.add(day);
        }

        //dynamic programming array to store minimum cost up to each day
        int[] dp = new int[maxDay + 1];

        // iterate through all days of the year
        for (int i = 1; i <= maxDay; i++) {
            // if it's not a travel day - cost remains the same as the previous day
            if (!travelDays.contains(i)) {
                dp[i] = dp[i - 1];
            } else {
                //otherwise - calculate the min cost considering all pass options
                int cost1 = dp[i - 1] + costs[0]; // 1-day pass
                int cost7 = dp[Math.max(0, i - 7)] + costs[1]; // 7-day pass
                int cost30 = dp[Math.max(0, i - 30)] + costs[2]; // 30-day pass
                dp[i] = Math.min(cost1, Math.min(cost7, cost30));
            }
        }

        // the answer is the cost to cover up to the last travel day
        return dp[days[days.length - 1]];
    }

}

//Explanation:
//Travel Days and DP Array:
//-Use a Set to store the travel days for quick lookup.
//-Create a dp array where dp[i] represents the minimum cost to cover travel up to day i.
//Dynamic Programming Transition:
//-If a day is not a travel day, dp[i] = dp[i - 1].
//-Otherwise, calculate the minimum cost using:
//  --A 1-day pass, costing dp[i - 1] + costs[0].
//  --A 7-day pass, costing dp[Math.max(0, i - 7)] + costs[1].
//  --A 30-day pass, costing dp[Math.max(0, i - 30)] + costs[2].
//Final Result:
//-The result is the value at dp[days[days.length - 1]] since that’s the last travel day.
// Time complexity: O(n) - O(365)
// Space complexity: O(365 + n) - for max value n = travel days = 365 if we travel all days


//You have planned some train traveling one year in advance. The days of the year in which you will travel are
// given as an integer array days. Each day is an integer from 1 to 365.
//Train tickets are sold in three different ways:
//a 1-day pass is sold for costs[0] dollars,
//a 7-day pass is sold for costs[1] dollars, and
//a 30-day pass is sold for costs[2] dollars.
//The passes allow that many days of consecutive travel.
//
//For example, if we get a 7-day pass on day 2, then we can travel for 7 days: 2, 3, 4, 5, 6, 7, and 8.
//Return the minimum number of dollars you need to travel every day in the given list of days.
//
//Example 1:
//Input: days = [1,4,6,7,8,20], costs = [2,7,15]
//Output: 11
//Explanation: For example, here is one way to buy passes that lets you travel your travel plan:
//On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
//On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
//On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
//In total, you spent $11 and covered all the days of your travel.

//Example 2:
//Input: days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
//Output: 17
//Explanation: For example, here is one way to buy passes that lets you travel your travel plan:
//On day 1, you bought a 30-day pass for costs[2] = $15 which covered days 1, 2, ..., 30.
//On day 31, you bought a 1-day pass for costs[0] = $2 which covered day 31.
//In total, you spent $17 and covered all the days of your travel.
//
//Constraints:
//1 <= days.length <= 365
//1 <= days[i] <= 365
//days is in strictly increasing order.
//costs.length == 3
//1 <= costs[i] <= 1000
