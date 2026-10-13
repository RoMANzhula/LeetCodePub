package P3701_3800.P3727_Maximum_Alternating_Sum_of_Squares;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 2, 3};
        System.out.println(solution.maxAlternatingSum(nums1)); // 12

        int[] nums2 = {1, -1, 2, -2, 3, -3};
        System.out.println(solution.maxAlternatingSum(nums2)); // 16
    }

    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] squares = new long[n];

        for (int i = 0; i < n; i++) {
            squares[i] = (long) nums[i] * nums[i];
        }

        Arrays.sort(squares);

        long score = 0;

        for (int i = 0; i < n; i++) {
            if (i < (n + 1) / 2) {
                score += squares[n - 1 - i];
            } else {
                score -= squares[n - 1 - i];
            }
        }

        return score;
    }

}

//Complexity:
// time - O(n log n)
// space - O(n)


//You are given an integer array nums. You may rearrange the elements in any order.
//The alternating score of an array arr is defined as:
//score = arr[0]2 - arr[1]2 + arr[2]2 - arr[3]2 + ...
//Return an integer denoting the maximum possible alternating score of nums after rearranging its elements.

//Example 1:
//Input: nums = [1,2,3]
//Output: 12
//Explanation:
//A possible rearrangement for nums is [2,1,3], which gives the maximum alternating score among all possible
// rearrangements.
//The alternating score is calculated as:
//score = 22 - 12 + 32 = 4 - 1 + 9 = 12

//Example 2:
//Input: nums = [1,-1,2,-2,3,-3]
//Output: 16
//Explanation:
//A possible rearrangement for nums is [-3,-1,-2,1,3,2], which gives the maximum alternating score among all
// possible rearrangements.
//The alternating score is calculated as:
//score = (-3)2 - (-1)2 + (-2)2 - (1)2 + (3)2 - (2)2 = 9 - 1 + 4 - 1 + 9 - 4 = 16

//Constraints:
//1 <= nums.length <= 105
//-4 * 104 <= nums[i] <= 4 * 104
