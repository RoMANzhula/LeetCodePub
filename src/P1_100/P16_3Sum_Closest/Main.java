package P1_100.P16_3Sum_Closest;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {-1, 2, 1, -4};
        int target1 = 1;
        System.out.println("Output: " + solution.threeSumClosest(nums1, target1)); // Output: 2

        int[] nums2 = {0, 0, 0};
        int target2 = 1;
        System.out.println("Output: " + solution.threeSumClosest(nums2, target2)); // Output: 0
    }

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums); // sort the array for two-pointer approach
        int closestSum = nums[0] + nums[1] + nums[2]; // initialize with first 3 numbers

        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int currentSum = nums[i] + nums[left] + nums[right];

                // update closestSum if the current is closer to target
                if (Math.abs(currentSum - target) < Math.abs(closestSum - target)) {
                    closestSum = currentSum;
                }

                if (currentSum < target) {
                    left++; // need a bigger sum
                } else if (currentSum > target) {
                    right--; // need a smaller sum
                } else {
                    // exxact match found
                    return currentSum;
                }
            }
        }

        return closestSum;
    }

}

//Complexity:
// time - O(n^2)
// space - O(1)


//Given an integer array nums of length n and an integer target, find three integers in nums such that the sum is
// closest to target.
//Return the sum of the three integers.
//You may assume that each input would have exactly one solution.

//Example 1:
//Input: nums = [-1,2,1,-4], target = 1
//Output: 2
//Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

//Example 2:
//Input: nums = [0,0,0], target = 1
//Output: 0
//Explanation: The sum that is closest to the target is 0. (0 + 0 + 0 = 0).

//Constraints:
//3 <= nums.length <= 500
//-1000 <= nums[i] <= 1000
//-104 <= target <= 104