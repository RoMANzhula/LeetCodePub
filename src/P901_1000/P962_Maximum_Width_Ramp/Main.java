package P901_1000.P962_Maximum_Width_Ramp;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {6, 0, 8, 2, 1, 5};
        System.out.println(solution.maxWidthRamp(nums1));  // Output: 4

        int[] nums2 = {9, 8, 1, 0, 1, 9, 4, 0, 4, 1};
        System.out.println(solution.maxWidthRamp(nums2));  // Output: 7
    }

    public int maxWidthRamp(int[] nums) {
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();

        // Step 1: Build a stack of indices where nums[i] are in decreasing order
        for (int i = 0; i < n; i++) {
            if (stack.isEmpty() || nums[stack.peek()] > nums[i]) {
                stack.push(i);
            }
        }

        int maxWidth = 0;

        // Step 2: Traverse from the end and try to find the maximum width ramp
        for (int j = n - 1; j >= 0; j--) {
            // Check if the current nums[j] can form a ramp with the top of the stack
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[j]) {
                maxWidth = Math.max(maxWidth, j - stack.pop());
            }
        }

        return maxWidth;
    }
}

//Explanation:
//Step 1: We iterate through the array from left to right, building a stack of indices where nums[i] values are
// in strictly decreasing order. This is because these indices might serve as the starting points i for potential ramps.
//Step 2: We traverse the array from right to left (index j), and for each nums[j], we try to find the
// largest index i in the stack where nums[i] <= nums[j]. Every time we find such a pair, we calculate the
// ramp width j - i and update the maximum width.
//Efficiency: The stack helps us efficiently find the valid starting points i for ramps in O(n) time, making
// the overall complexity O(n), which is suitable for large input sizes.
//Time Complexity:
//O(n): We make two passes through the array—one for building the stack and one for finding the maximum width ramp.
//Space Complexity:
//O(n): The stack can hold at most n indices.


//A ramp in an integer array nums is a pair (i, j) for which i < j and nums[i] <= nums[j]. The
// width of such a ramp is j - i.
//Given an integer array nums, return the maximum width of a ramp in nums. If there is no ramp in nums, return 0.
//
//Example 1:
//Input: nums = [6,0,8,2,1,5]
//Output: 4
//Explanation: The maximum width ramp is achieved at (i, j) = (1, 5): nums[1] = 0 and nums[5] = 5.

//Example 2:
//Input: nums = [9,8,1,0,1,9,4,0,4,1]
//Output: 7
//Explanation: The maximum width ramp is achieved at (i, j) = (2, 9): nums[2] = 1 and nums[9] = 1.
//
//Constraints:
//2 <= nums.length <= 5 * 104
//0 <= nums[i] <= 5 * 104