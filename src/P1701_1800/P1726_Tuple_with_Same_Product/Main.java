package P1701_1800.P1726_Tuple_with_Same_Product;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.tupleSameProduct(new int[]{2, 3, 4, 6})); // Output: 8
        System.out.println(solution.tupleSameProduct(new int[]{1, 2, 4, 5, 10})); // Output: 16
    }

    public int tupleSameProduct(int[] nums) {
        int len = nums.length;
        Map<Integer, Integer> productCount = new HashMap<>();
        int count = 0;

        // calculate the products of all pairs of nums and store them in the map
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int product = nums[i] * nums[j];
                productCount.put(product, productCount.getOrDefault(product, 0) + 1);
            }
        }

        // calculate the number of tuples based on product frequencies
        for (Integer freq : productCount.values()) {
            if (freq > 1) {
                count += (freq * (freq - 1)) / 2 * 8;
            }
        }

        return count;
    }
}

//We use Map<Integer, Integer> to store the number of pairs that have the same product.
//We go through all possible pairs of elements in the array and store their product in the map.
//For each product, if it occurs more than once, we calculate the number of possible tuples using the formula:
//freq∗(freq−1)/2∗8
//This takes into account permutations (a, b, c, d).
//This approach has a difficulty O(N^2), which is acceptable for N≤1000.


//Given an array nums of distinct positive integers, return the number of tuples (a, b, c, d) such
// that a * b = c * d where a, b, c, and d are elements of nums, and a != b != c != d.
//
//Example 1:
//Input: nums = [2,3,4,6]
//Output: 8
//Explanation: There are 8 valid tuples:
//(2,6,3,4) , (2,6,4,3) , (6,2,3,4) , (6,2,4,3)
//(3,4,2,6) , (4,3,2,6) , (3,4,6,2) , (4,3,6,2)

//Example 2:
//Input: nums = [1,2,4,5,10]
//Output: 16
//Explanation: There are 16 valid tuples:
//(1,10,2,5) , (1,10,5,2) , (10,1,2,5) , (10,1,5,2)
//(2,5,1,10) , (2,5,10,1) , (5,2,1,10) , (5,2,10,1)
//(2,10,4,5) , (2,10,5,4) , (10,2,4,5) , (10,2,5,4)
//(4,5,2,10) , (4,5,10,2) , (5,4,2,10) , (5,4,10,2)
//
//Constraints:
//1 <= nums.length <= 1000
//1 <= nums[i] <= 104
//All elements in nums are distinct.