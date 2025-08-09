package P1_100.P53_Maximum_Subarray;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] nums2 = {1};
        int[] nums3 = {5, 4, -1, 7, 8};

        System.out.println(solution.maxSubArray(nums1)); // Output: 6
        System.out.println(solution.maxSubArray(nums2)); // Output: 1
        System.out.println(solution.maxSubArray(nums3)); // Output: 23
    }

    public int maxSubArray(int[] nums) {
        int currentSum = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // either start a new subarray at nums[i], or extend the previous subarray
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

}


//Given an integer array nums, find the subarray with the largest sum, and return its sum.

//Example 1:
//Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
//Output: 6
//Explanation: The subarray [4,-1,2,1] has the largest sum 6.

//Example 2:
//Input: nums = [1]
//Output: 1
//Explanation: The subarray [1] has the largest sum 1.

//Example 3:
//Input: nums = [5,4,-1,7,8]
//Output: 23
//Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.

//Constraints:
//1 <= nums.length <= 105
//-104 <= nums[i] <= 104
