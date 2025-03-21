package P1801_1900.P1877_Minimize_Maximum_Pair_Sum_in_Array;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] nums1 = {3, 5, 2, 3};
        System.out.println(minMaxPairSum(nums1)); // Output: 7

        int[] nums2 = {3, 5, 4, 2, 4, 6};
        System.out.println(minMaxPairSum(nums2)); // Output: 8
    }

    public static int minMaxPairSum(int[] nums) {
        Arrays.sort(nums);

        int left = 0;
        int right = nums.length - 1;
        int result = Integer.MIN_VALUE;

        while (left < right) {
            int sum = nums[left] + nums[right];
            result = Math.max(result, sum);
            left++;
            right--;
        }

        return result;
    }

}
//The pair sum of a pair (a,b) is equal to a + b. The maximum pair sum is the largest pair sum in a list of pairs.
//For example, if we have pairs (1,5), (2,3), and (4,4), the maximum pair sum would
// be max(1+5, 2+3, 4+4) = max(6, 5, 8) = 8.
//Given an array nums of even length n, pair up the elements of nums into n / 2 pairs such that:
//Each element of nums is in exactly one pair, and
//The maximum pair sum is minimized.
//Return the minimized maximum pair sum after optimally pairing up the elements.

//Example 1:
//Input: nums = [3,5,2,3]
//Output: 7
//Explanation: The elements can be paired up into pairs (3,3) and (5,2).
//The maximum pair sum is max(3+3, 5+2) = max(6, 7) = 7.

//Example 2:
//Input: nums = [3,5,4,2,4,6]
//Output: 8
//Explanation: The elements can be paired up into pairs (3,5), (4,4), and (6,2).
//The maximum pair sum is max(3+5, 4+4, 6+2) = max(8, 8, 8) = 8.

//Constraints:
//n == nums.length
//2 <= n <= 105
//n is even.
//1 <= nums[i] <= 105
