package P1701_1800.P1760_Minimum_Limit_of_Balls_in_a_Bag;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {9};
        int maxOperations1 = 2;
        System.out.println(solution.minimumSize(nums1, maxOperations1)); // Output: 3

        int[] nums2 = {2, 4, 8, 2};
        int maxOperations2 = 4;
        System.out.println(solution.minimumSize(nums2, maxOperations2)); // Output: 2
    }

    public int minimumSize(int[] nums, int maxOperations) {
        int left = 1; // Minimum possible penalty
        int right = Arrays.stream(nums).max().getAsInt(); // Maximum possible penalty
        int result = right;

        while (left <= right) {
            int mid = left + (right - left) / 2; // Midpoint penalty
            if (canAchieve(nums, maxOperations, mid)) {
                result = mid; // Update result to the current feasible penalty
                right = mid - 1; // Try for a smaller penalty
            } else {
                left = mid + 1; // Increase penalty to make it feasible
            }
        }

        return result;
    }

    private boolean canAchieve(int[] nums, int maxOperations, int penalty) {
        int operations = 0;

        for (int balls : nums) {
            // Calculate how many splits are needed for this bag to have at most `penalty` balls
            if (balls > penalty) {
                operations += (balls - 1) / penalty; // Number of splits required
            }
            if (operations > maxOperations) {
                return false; // Exceeded the allowed operations
            }
        }

        return true; // Feasible with the given penalty
    }

}

//Explanation
//Binary Search:
//-Start with the smallest possible penalty (left = 1) and the largest possible penalty (right = max(nums)).
//-Use binary search to minimize the penalty.
//Feasibility Check (canAchieve):
//-For a given penalty, calculate the number of operations required to ensure no bag exceeds the penalty.
//-If the operations required are less than or equal to maxOperations, the penalty is feasible.
//Efficiency:
//The binary search runs in O(log(max(nums))).
//The feasibility check runs in O(n), where n is the size of nums.
//Total time complexity: O(n⋅log(max(nums))).


//You are given an integer array nums where the ith bag contains nums[i] balls. You are also given an
// integer maxOperations.
//You can perform the following operation at most maxOperations times:
//Take any bag of balls and divide it into two new bags with a positive number of balls.
//For example, a bag of 5 balls can become two new bags of 1 and 4 balls, or two new bags of 2 and 3 balls.
//Your penalty is the maximum number of balls in a bag. You want to minimize your penalty after the operations.
//Return the minimum possible penalty after performing the operations.
//
//Example 1:
//Input: nums = [9], maxOperations = 2
//Output: 3
//Explanation:
//- Divide the bag with 9 balls into two bags of sizes 6 and 3. [9] -> [6,3].
//- Divide the bag with 6 balls into two bags of sizes 3 and 3. [6,3] -> [3,3,3].
//The bag with the most number of balls has 3 balls, so your penalty is 3 and you should return 3.

//Example 2:
//Input: nums = [2,4,8,2], maxOperations = 4
//Output: 2
//Explanation:
//- Divide the bag with 8 balls into two bags of sizes 4 and 4. [2,4,8,2] -> [2,4,4,4,2].
//- Divide the bag with 4 balls into two bags of sizes 2 and 2. [2,4,4,4,2] -> [2,2,2,4,4,2].
//- Divide the bag with 4 balls into two bags of sizes 2 and 2. [2,2,2,4,4,2] -> [2,2,2,2,2,4,2].
//- Divide the bag with 4 balls into two bags of sizes 2 and 2. [2,2,2,2,2,4,2] -> [2,2,2,2,2,2,2,2].
//The bag with the most number of balls has 2 balls, so your penalty is 2, and you should return 2.
//
//Constraints:
//1 <= nums.length <= 105
//1 <= maxOperations, nums[i] <= 109
