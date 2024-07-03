package P1501_1600.P1509_Minimum_Difference_Between_Largest_and_Smallest_Value_in_Three_Moves;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {5, 3, 2, 4};
        int[] nums2 = {1, 5, 0, 10, 14};
        int[] nums3 = {3, 100, 20};

        System.out.println(solution.minDifference(nums1)); // Output: 0
        System.out.println(solution.minDifference(nums2)); // Output: 1
        System.out.println(solution.minDifference(nums3)); // Output: 0
    }

    public int minDifference(int[] nums) {
        if (nums.length <= 4) {
            return 0;
        }

        Arrays.sort(nums);

        int n = nums.length;
        int minDiff = Integer.MAX_VALUE;

        // Consider four possible scenarios:
        // 1. Change the three largest elements
        // 2. Change the two largest elements and the smallest element
        // 3. Change the largest element and the two smallest elements
        // 4. Change the three smallest elements
        for (int i = 0; i <= 3; i++) {
            minDiff = Math.min(minDiff, nums[n - 4 + i] - nums[i]);
        }

        return minDiff;
    }
}

//Explanation:
//Initial Check: If the array length is less than or equal to 4, return 0 since you can change all elements to the
// same value in at most 3 moves.
//Sorting: Sort the array to easily access the smallest and largest elements.
//Compute Minimum Difference: Consider the four scenarios:
//Changing the three largest elements.
//Changing the two largest elements and the smallest element.
//Changing the largest element and the two smallest elements.
//Changing the three smallest elements.
//For each scenario, calculate the difference between the remaining maximum and minimum elements after the changes
// and keep track of the minimum difference encountered.
//Return Result: The minimum difference found is the result.
//This approach ensures that you find the smallest possible difference after performing at most three moves,
// considering all relevant scenarios.


//You are given an integer array nums.
//In one move, you can choose one element of nums and change it to any value.
//Return the minimum difference between the largest and smallest value of nums after performing at most three moves.
//
//Example 1:
//Input: nums = [5,3,2,4]
//Output: 0
//Explanation: We can make at most 3 moves.
//In the first move, change 2 to 3. nums becomes [5,3,3,4].
//In the second move, change 4 to 3. nums becomes [5,3,3,3].
//In the third move, change 5 to 3. nums becomes [3,3,3,3].
//After performing 3 moves, the difference between the minimum and maximum is 3 - 3 = 0.

//Example 2:
//Input: nums = [1,5,0,10,14]
//Output: 1
//Explanation: We can make at most 3 moves.
//In the first move, change 5 to 0. nums becomes [1,0,0,10,14].
//In the second move, change 10 to 0. nums becomes [1,0,0,0,14].
//In the third move, change 14 to 1. nums becomes [1,0,0,0,1].
//After performing 3 moves, the difference between the minimum and maximum is 1 - 0 = 1.
//It can be shown that there is no way to make the difference 0 in 3 moves.

//Example 3:
//Input: nums = [3,100,20]
//Output: 0
//Explanation: We can make at most 3 moves.
//In the first move, change 100 to 7. nums becomes [3,7,20].
//In the second move, change 20 to 7. nums becomes [3,7,7].
//In the third move, change 3 to 7. nums becomes [7,7,7].
//After performing 3 moves, the difference between the minimum and maximum is 7 - 7 = 0.
//
//Constraints:
//1 <= nums.length <= 105
//-109 <= nums[i] <= 109