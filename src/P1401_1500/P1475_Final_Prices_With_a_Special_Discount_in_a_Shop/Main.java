package P1401_1500.P1475_Final_Prices_With_a_Special_Discount_in_a_Shop;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] prices1 = {8, 4, 6, 2, 3};
        System.out.println(Arrays.toString(solution.finalPrices(prices1))); // Output: [4, 2, 4, 2, 3]

        int[] prices2 = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(solution.finalPrices(prices2))); // Output: [1, 2, 3, 4, 5]

        int[] prices3 = {10, 1, 1, 6};
        System.out.println(Arrays.toString(solution.finalPrices(prices3))); // Output: [9, 0, 1, 6]
    }

    public int[] finalPrices(int[] prices) {
        int n = prices.length;
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            result[i] = prices[i]; // Default to the original price
            for (int j = i + 1; j < n; j++) {
                if (prices[j] <= prices[i]) {
                    result[i] = prices[i] - prices[j];
                    break; // Stop at the first applicable discount
                }
            }
        }

        return result;
    }
}

//Explanation:
//Initialization: Create a result array to store the final prices.
//Outer Loop: Iterate through each price in the array.
//Inner Loop: For each price at index i, look for the first price at index j > i where prices[j] <= prices[i].
//If such a price is found, calculate the discounted price (prices[i] - prices[j]) and break the loop.
//If no discount is applicable, the original price remains.
//Output: Return the result array.
//Complexity:
//Time Complexity:
//O(n2) in the worst case due to the nested loop.
//Space Complexity:
//O(1), as no additional data structures are used except the result array.


//You are given an integer array prices where prices[i] is the price of the ith item in a shop.
//There is a special discount for items in the shop. If you buy the ith item, then you will receive a discount
// equivalent to prices[j] where j is the minimum index such that j > i and prices[j] <= prices[i]. Otherwise,
// you will not receive any discount at all.
//Return an integer array answer where answer[i] is the final price you will pay for the ith item of the shop,
// considering the special discount.
//
//Example 1:
//Input: prices = [8,4,6,2,3]
//Output: [4,2,4,2,3]
//Explanation:
//For item 0 with price[0]=8 you will receive a discount equivalent to
// prices[1]=4, therefore, the final price you will pay is 8 - 4 = 4.
//For item 1 with price[1]=4 you will receive a discount equivalent to
// prices[3]=2, therefore, the final price you will pay is 4 - 2 = 2.
//For item 2 with price[2]=6 you will receive a discount equivalent to
// prices[3]=2, therefore, the final price you will pay is 6 - 2 = 4.
//For items 3 and 4 you will not receive any discount at all.

//Example 2:
//Input: prices = [1,2,3,4,5]
//Output: [1,2,3,4,5]
//Explanation: In this case, for all items, you will not receive any discount at all.

//Example 3:
//Input: prices = [10,1,1,6]
//Output: [9,0,1,6]
//
//Constraints:
//1 <= prices.length <= 500
//1 <= prices[i] <= 1000