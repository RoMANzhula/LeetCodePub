package P2501_2600.P2561_Rearranging_Fruits;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.minCost(new int[]{4, 2, 2, 2}, new int[]{1, 4, 1, 2})); // Output: 1
        System.out.println(solution.minCost(new int[]{2, 3, 4, 1}, new int[]{3, 2, 5, 1})); // Output: -1
    }

    public long minCost(int[] basket1, int[] basket2) {
        Map<Integer, Integer> count = new TreeMap<>();

        for (int fruit : basket1) {
            count.put(fruit, count.getOrDefault(fruit, 0) + 1);
        }

        for (int fruit : basket2) {
            count.put(fruit, count.getOrDefault(fruit, 0) - 1);
        }

        List<Integer> extra = new ArrayList<>();
        int minValue = Integer.MAX_VALUE;

        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            int fruit = entry.getKey();
            int freq = entry.getValue();

            minValue = Math.min(minValue, fruit);

            if (freq % 2 != 0) return -1; // impossible to balance

            for (int i = 0; i < Math.abs(freq) / 2; i++) {
                extra.add(fruit);
            }
        }

        Collections.sort(extra);
        long cost = 0;

        for (int i = 0; i < extra.size() / 2; i++) {
            cost += Math.min(extra.get(i), 2 * minValue);
        }

        return cost;
    }

}

//Complexity:
// time - O(n log n)
// space - O(n)


//You have two fruit baskets containing n fruits each. You are given two 0-indexed integer arrays basket1 and
// basket2 representing the cost of fruit in each basket. You want to make both baskets equal. To do so, you
// can use the following operation as many times as you want:
//Chose two indices i and j, and swap the ith fruit of basket1 with the jth fruit of basket2.
//The cost of the swap is min(basket1[i],basket2[j]).
//Two baskets are considered equal if sorting them according to the fruit cost makes them exactly the same baskets.
//Return the minimum cost to make both the baskets equal or -1 if impossible.

//Example 1:
//Input: basket1 = [4,2,2,2], basket2 = [1,4,1,2]
//Output: 1
//Explanation: Swap index 1 of basket1 with index 0 of basket2, which has cost 1. Now basket1 = [4,1,2,2] and
// basket2 = [2,4,1,2]. Rearranging both the arrays makes them equal.

//Example 2:
//Input: basket1 = [2,3,4,1], basket2 = [3,2,5,1]
//Output: -1
//Explanation: It can be shown that it is impossible to make both the baskets equal.

//Constraints:
//basket1.length == basket2.length
//1 <= basket1.length <= 105
//1 <= basket1[i],basket2[i] <= 109
