package P1_100.P55_Jump_Game;

public class Main {
    public static void main(String[] args) {
        int[] nums1 = {2,3,1,1,4}; //true
        int[] nums2 = {3,2,1,0,4}; //false

        System.out.println(canJump(nums1));
        System.out.println(canJump(nums2));
    }

    public static boolean canJump(int[] nums) {
        int maxReachable = 0; //the maximum index we can reach

        for (int i = 0; i < nums.length; i++) {
            if (i > maxReachable) {
                return false; //can't reach the current index
            }

            maxReachable = Math.max(maxReachable, i + nums[i]);

            if (maxReachable >= nums.length - 1) {
                return true; //we can reach or go beyond the last index
            }
        }

        return true; //if we have iterated through the array without returning false
    }
}

//You are given an integer array nums. You are initially positioned at the array's first index, and each element in
// the array represents your maximum jump length at that position.
//Return true if you can reach the last index, or false otherwise.

//Example 1:
//Input: nums = [2,3,1,1,4]
//Output: true
//Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

//Example 2:
//Input: nums = [3,2,1,0,4]
//Output: false
//Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it
// impossible to reach the last index.
//
//Constraints:
//1 <= nums.length <= 104
//0 <= nums[i] <= 105
