package P401_500.P416_Partition_Equal_Subset_Sum;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 5, 11, 5};
        System.out.println(solution.canPartition(nums1)); // true

        int[] nums2 = {1, 2, 3, 5};
        System.out.println(solution.canPartition(nums2)); // false

    }

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // if total sum is odd, it can't be partitioned equally
        if (sum % 2 != 0) return false;

        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true; // base case: 0 sum is always possible

        for (int num : nums) {
            // traverse backwards to avoid overwriting results from the current round
            for (int i = target; i >= num; i--) {
                dp[i] = dp[i] || dp[i - num];
            }
        }

        return dp[target];
    }

}

//We want to determine if the input array can be partitioned into two subsets with equal sum.
//That’s only possible if the total sum of the array is even.
//If it's even, we try to find a subset that adds up to half of the total sum.
//Approach: Dynamic Programming (DP)
//Let target = totalSum / 2.
//We create a boolean DP array where dp[i] will be true if a subset with sum i can be formed using
// elements of the array.
//Complexity:
//Time Complexity: O(n * target), where n is the length of the array.
//Space Complexity: O(target) — we optimize using a 1D DP array.


//Given an integer array nums, return true if you can partition the array into two subsets such that the sum
// of the elements in both subsets is equal or false otherwise.

//Example 1:
//Input: nums = [1,5,11,5]
//Output: true
//Explanation: The array can be partitioned as [1, 5, 5] and [11].

//Example 2:
//Input: nums = [1,2,3,5]
//Output: false
//Explanation: The array cannot be partitioned into equal sum subsets.

//Constraints:
//1 <= nums.length <= 200
//1 <= nums[i] <= 100
