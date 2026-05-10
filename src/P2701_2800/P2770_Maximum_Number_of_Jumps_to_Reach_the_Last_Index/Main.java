package P2701_2800.P2770_Maximum_Number_of_Jumps_to_Reach_the_Last_Index;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 3, 6, 4, 1, 2};

        System.out.println(solution.maximumJumps(nums1, 2)); // 3
        System.out.println(solution.maximumJumps(nums1, 3)); // 5
        System.out.println(solution.maximumJumps(nums1, 0)); // -1
    }

    public int maximumJumps(int[] nums, int target) {
        int len = nums.length;
        int[] dp = new int[len];

        Arrays.fill(dp, -1);
        dp[0] = 0;

        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                long diff = (long) nums[i] - nums[j];

                if (diff >= -target && diff <= target && dp[j] != -1) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        return dp[len - 1];
    }

}

//Complexity:
// time - O(n^2)
// space - O(n)


//You are given a 0-indexed array nums of n integers and an integer target.
//You are initially positioned at index 0. In one step, you can jump from index i to any index j such that:
//0 <= i < j < n
//-target <= nums[j] - nums[i] <= target
//Return the maximum number of jumps you can make to reach index n - 1.
//If there is no way to reach index n - 1, return -1.

//Example 1:
//Input: nums = [1,3,6,4,1,2], target = 2
//Output: 3
//Explanation: To go from index 0 to index n - 1 with the maximum number of jumps, you can perform the following
// jumping sequence:
//- Jump from index 0 to index 1.
//- Jump from index 1 to index 3.
//- Jump from index 3 to index 5.
//It can be proven that there is no other jumping sequence that goes from 0 to n - 1 with more than 3 jumps. Hence,
// the answer is 3.

//Example 2:
//Input: nums = [1,3,6,4,1,2], target = 3
//Output: 5
//Explanation: To go from index 0 to index n - 1 with the maximum number of jumps, you can perform the following
// jumping sequence:
//- Jump from index 0 to index 1.
//- Jump from index 1 to index 2.
//- Jump from index 2 to index 3.
//- Jump from index 3 to index 4.
//- Jump from index 4 to index 5.
//It can be proven that there is no other jumping sequence that goes from 0 to n - 1 with more than 5 jumps. Hence,
// the answer is 5.

//Example 3:
//Input: nums = [1,3,6,4,1,2], target = 0
//Output: -1
//Explanation: It can be proven that there is no jumping sequence that goes from 0 to n - 1. Hence, the answer is -1.

//Constraints:
//2 <= nums.length == n <= 1000
//-109 <= nums[i] <= 109
//0 <= target <= 2 * 109
