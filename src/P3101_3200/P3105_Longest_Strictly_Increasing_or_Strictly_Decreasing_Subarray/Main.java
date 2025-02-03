package P3101_3200.P3105_Longest_Strictly_Increasing_or_Strictly_Decreasing_Subarray;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1,4,3,3,2};
        System.out.println(solution.longestMonotonicSubarray(nums1)); // output: 2

        int[] nums2 = {3,3,3,3};
        System.out.println(solution.longestMonotonicSubarray(nums2)); // output: 1

        int[] nums3 = {3, 2, 1};
        System.out.println(solution.longestMonotonicSubarray(nums3)); // output: 3
    }

    public int longestMonotonicSubarray(int[] nums) {
        int len = nums.length;

        if (nums.length == 1) return 1;

        int maxLen = 1;
        int incLen = 1;
        int decLen = 1;

        for (int i = 1; i < len; i++) {
            if (nums[i] > nums[i - 1]) {
                incLen++;
                decLen = 1; // reset decreasing length
            } else if (nums[i] < nums[i - 1]) {
                decLen++;
                incLen = 1; // reset increasing length
            } else {
                incLen = 1;
                decLen = 1;
            }

            maxLen = Math.max(maxLen, Math.max(incLen, decLen));
        }

        return maxLen;
    }
}


//You are given an array of integers nums. Return the length of the longest subarray of nums which is either
// strictly increasing or strictly decreasing.
//Example 1:
//Input: nums = [1,4,3,3,2]
//Output: 2
//Explanation:
//The strictly increasing subarrays of nums are [1], [2], [3], [3], [4], and [1,4].
//The strictly decreasing subarrays of nums are [1], [2], [3], [3], [4], [3,2], and [4,3].
//Hence, we return 2.
//
//Example 2:
//Input: nums = [3,3,3,3]
//Output: 1
//Explanation:
//The strictly increasing subarrays of nums are [3], [3], [3], and [3].
//The strictly decreasing subarrays of nums are [3], [3], [3], and [3].
//Hence, we return 1.
//
//Example 3:
//Input: nums = [3,2,1]
//Output: 3
//Explanation:
//The strictly increasing subarrays of nums are [3], [2], and [1].
//The strictly decreasing subarrays of nums are [3], [2], [1], [3,2], [2,1], and [3,2,1].
//Hence, we return 3.
//
//Constraints:
//1 <= nums.length <= 50
//1 <= nums[i] <= 50
