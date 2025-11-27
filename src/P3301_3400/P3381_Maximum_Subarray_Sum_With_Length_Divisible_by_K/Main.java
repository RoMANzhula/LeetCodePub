package P3301_3400.P3381_Maximum_Subarray_Sum_With_Length_Divisible_by_K;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.maxSubarraySum(new int[]{1, 2}, 1));           // 3
        System.out.println(solution.maxSubarraySum(new int[]{-1, -2, -3, -4, -5}, 4)); // -10
        System.out.println(solution.maxSubarraySum(new int[]{-5, 1, 2, -3, 4}, 2));   // 4
    }

    public long maxSubarraySum(int[] nums, int k) {
        long ans = Long.MIN_VALUE;
        long prefix = 0;
        // minPrefix[i % k] := the minimum prefix sum of the first i numbers
        long[] minPrefix = new long[k];
        Arrays.fill(minPrefix, Long.MAX_VALUE / 2);
        minPrefix[k - 1] = 0;

        for (int i = 0; i < nums.length; ++i) {
            prefix += nums[i];
            ans = Math.max(ans, prefix - minPrefix[i % k]);
            minPrefix[i % k] = Math.min(minPrefix[i % k], prefix);
        }

        return ans;
    }

}

//Complexity:
// time - O(n)
// space - O(k)


//You are given an array of integers nums and an integer k.
//Return the maximum sum of a subarray of nums, such that the size of the subarray is divisible by k.

//Example 1:
//Input: nums = [1,2], k = 1
//Output: 3
//Explanation:
//The subarray [1, 2] with sum 3 has length equal to 2 which is divisible by 1.

//Example 2:
//Input: nums = [-1,-2,-3,-4,-5], k = 4
//Output: -10
//Explanation:
//The maximum sum subarray is [-1, -2, -3, -4] which has length equal to 4 which is divisible by 4.

//Example 3:
//Input: nums = [-5,1,2,-3,4], k = 2
//Output: 4
//Explanation:
//The maximum sum subarray is [1, 2, -3, 4] which has length equal to 4 which is divisible by 2.

//Constraints:
//1 <= k <= nums.length <= 2 * 105
//-109 <= nums[i] <= 109
