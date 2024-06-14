package P901_1000.P945_Minimum_Increment_to_Make_Array_Unique;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 2, 2};
        System.out.println(solution.minIncrementForUnique(nums1)); // Output: 1

        int[] nums2 = {3, 2, 1, 2, 1, 7};
        System.out.println(solution.minIncrementForUnique(nums2)); // Output: 6
    }

    public int minIncrementForUnique(int[] nums) {
        Arrays.sort(nums); // Step 1: Sort the array

        int moves = 0; // Variable to count the number of moves
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= nums[i - 1]) { // If current element is not greater than previous
                int increment = nums[i - 1] - nums[i] + 1;
                nums[i] += increment; // Increment the current element
                moves += increment; // Add the increment to the move count
            }
        }

        return moves; // Return the total number of moves
    }
}

//Explanation:
//Sorting the Array:
//By sorting the array, we can ensure that any duplicates will be adjacent.
//Iterating through the Array:
//Start from the second element (i = 1) and check if it's less than or equal to the previous
// element (nums[i] <= nums[i - 1]).
//If it is, compute the required increment to make it one more than the previous
// element (increment = nums[i - 1] - nums[i] + 1).
//Add this increment to the current element (nums[i] += increment) and to the move counter (moves += increment).
//This approach ensures that all elements in the array are unique with the minimum number of moves, adhering to
// the constraints provided.


//You are given an integer array nums. In one move, you can pick an index i where 0 <= i < nums.length and
// increment nums[i] by 1.
//Return the minimum number of moves to make every value in nums unique.
//The test cases are generated so that the answer fits in a 32-bit integer.
//
//Example 1:
//Input: nums = [1,2,2]
//Output: 1
//Explanation: After 1 move, the array could be [1, 2, 3].

//Example 2:
//Input: nums = [3,2,1,2,1,7]
//Output: 6
//Explanation: After 6 moves, the array could be [3, 4, 1, 2, 5, 7].
//It can be shown with 5 or less moves that it is impossible for the array to have all unique values.
//
//Constraints:
//1 <= nums.length <= 105
//0 <= nums[i] <= 105