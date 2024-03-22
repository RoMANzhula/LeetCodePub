package P901_1000.P977_Squares_of_a_Sorted_Array;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {-4,-1,0,3,10};
        int[] nums2 = {-7,-3,2,3,11};

        System.out.println(Arrays.toString(solution.sortedSquares(nums1))); // output [0,1,9,16,100]
        System.out.println(Arrays.toString(solution.sortedSquares(nums2))); // output: [4,9,9,49,121]
    }

    public int[] sortedSquares(int[] nums) {
        int[] result = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            result[i] = nums[i] * nums[i];
        }

        Arrays.sort(result);

        return result;
    }
}

//Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number
// sorted in non-decreasing order.
//Example 1:
//Input: nums = [-4,-1,0,3,10]
//Output: [0,1,9,16,100]
//Explanation: After squaring, the array becomes [16,1,0,9,100].
//After sorting, it becomes [0,1,9,16,100].

//Example 2:
//Input: nums = [-7,-3,2,3,11]
//Output: [4,9,9,49,121]
//
//Constraints:
//1 <= nums.length <= 104
//-104 <= nums[i] <= 104
//nums is sorted in non-decreasing order.
