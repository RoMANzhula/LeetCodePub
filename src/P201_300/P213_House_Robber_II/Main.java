package P201_300.P213_House_Robber_II;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {2, 3, 2};
        System.out.println(solution.rob(nums1)); // 3

        int[] nums2 = {1, 2, 3, 1};
        System.out.println(solution.rob(nums2)); // 4

        int[] nums3 = {1, 2, 3};
        System.out.println(solution.rob(nums3)); // 3

        int[] nums4 = {1};
        System.out.println(solution.rob(nums4)); // 1
    }

    public int rob(int[] nums) {
        int n = nums.length;

        if (n == 1) {
            return nums[0];
        }

        return Math.max(
                robLinear(nums, 0, n - 2),
                robLinear(nums, 1, n - 1)
        );
    }

    private int robLinear(int[] nums, int start, int end) {
        int prev2 = 0;
        int prev1 = 0;

        for (int i = start; i <= end; i++) {
            int current = Math.max(prev1, prev2 + nums[i]);

            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }

}

//Complexity:
// time - O(n)
// space - O(1)


//You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed.
// All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one.
// Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two
// adjacent houses were broken into on the same night.
//Given an integer array nums representing the amount of money of each house, return the maximum amount of money you
// can rob tonight without alerting the police.

//Example 1:
//Input: nums = [2,3,2]
//Output: 3
//Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.

//Example 2:
//Input: nums = [1,2,3,1]
//Output: 4
//Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
//Total amount you can rob = 1 + 3 = 4.

//Example 3:
//Input: nums = [1,2,3]
//Output: 3

//Constraints:
//1 <= nums.length <= 100
//0 <= nums[i] <= 1000
