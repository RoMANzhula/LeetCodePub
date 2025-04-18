package P1901_2000.P1913_Maximum_Product_Difference_Between_Two_Pairs;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] nums1 = {5,6,2,7,4};
        int[] nums2 = {4,2,5,9,7,4,8};

        System.out.println(maxProductDifference(nums1)); // 34
        System.out.println(maxProductDifference(nums2)); // 64
    }

    public static int maxProductDifference(int[] nums) {
        Arrays.sort(nums);

        return (nums[nums.length - 1] * nums[nums.length - 2]) - (nums[0] * nums[1]);
    }

}

//The product difference between two pairs (a, b) and (c, d) is defined as (a * b) - (c * d).
//For example, the product difference between (5, 6) and (2, 7) is (5 * 6) - (2 * 7) = 16.
//Given an integer array nums, choose four distinct indices w, x, y, and z such that the product difference
// between pairs (nums[w], nums[x]) and (nums[y], nums[z]) is maximized.
//Return the maximum such product difference.

//Example 1:
//Input: nums = [5,6,2,7,4]
//Output: 34
//Explanation: We can choose indices 1 and 3 for the first pair (6, 7) and indices 2 and 4 for the second pair (2, 4).
//The product difference is (6 * 7) - (2 * 4) = 34.

//Example 2:
//Input: nums = [4,2,5,9,7,4,8]
//Output: 64
//Explanation: We can choose indices 3 and 6 for the first pair (9, 8) and indices 1 and 5 for the second pair (2, 4).
//The product difference is (9 * 8) - (2 * 4) = 64.

//Constraints:
//4 <= nums.length <= 104
//1 <= nums[i] <= 104
