package P2501_2600.P2501_Longest_Square_Streak_in_an_Array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {2, 4, 16, 4}; // Example case
        System.out.println(solution.longestSquareStreak(nums1)); // Expected output: 2

        int[] nums2 = {83109, 8425, 36388, 11909, 4537, 60326}; // Another case
        System.out.println(solution.longestSquareStreak(nums2)); // Expected output: -1 or 2 based on your logic

    }

    public int longestSquareStreak(int[] nums) {
        // Remove duplicates using a HashSet
        Set<Integer> uniqueNums = new HashSet<>();
        for (int num : nums) {
            uniqueNums.add(num);
        }

        // Convert the set back to an array and sort it in descending order
        Integer[] sortedNums = uniqueNums.toArray(new Integer[0]);
        Arrays.sort(sortedNums, (a, b) -> b - a); // Sort in descending order

        int maxNum = sortedNums[0]; // The maximum number in the sorted array
        int[] dp = new int[maxNum + 1]; // dp[i] := the longest square streak starting with i

        // Iterate through the sorted unique numbers
        for (int num : sortedNums) {
            dp[num] = 1; // Initialize streak for the current number
            long squaredNum = (long) num * num; // Calculate the square of the number
            if (squaredNum <= maxNum) {
                dp[num] += dp[(int) squaredNum]; // Update the streak length
            }
        }

        // Find the maximum streak length
        int ans = 0;
        for (int streak : dp) {
            ans = Math.max(ans, streak);
        }

        return ans < 2 ? -1 : ans; // Return -1 if no valid streak is found
    }

}

//Explanation:
//Removing Duplicates: It uses a HashSet to store unique integers from the input array.
//Sorting: The unique numbers are converted to an array and sorted in descending order.
//Dynamic Programming Array: A dp array tracks the longest square streak for each number.
//Calculating Streaks: For each number, it initializes its streak length, calculates its square, and
// updates the streak if the squared number exists in the original numbers.
//Finding Maximum Streak: It searches the dp array for the maximum streak length.
//Return Result: If the longest streak is less than 2, it returns -1; otherwise, it returns the length of
// the longest streak.

//You are given an integer array nums. A subsequence of nums is called a square streak if:
//The length of the subsequence is at least 2, and
//after sorting the subsequence, each element (except the first element) is the square of the previous number.
//Return the length of the longest square streak in nums, or return -1 if there is no square streak.
//A subsequence is an array that can be derived from another array by deleting some or no elements without
// changing the order of the remaining elements.
//
//Example 1:
//Input: nums = [4,3,6,16,8,2]
//Output: 3
//Explanation: Choose the subsequence [4,16,2]. After sorting it, it becomes [2,4,16].
//- 4 = 2 * 2.
//- 16 = 4 * 4.
//Therefore, [4,16,2] is a square streak.
//It can be shown that every subsequence of length 4 is not a square streak.

//Example 2:
//Input: nums = [2,3,5,6,7]
//Output: -1
//Explanation: There is no square streak in nums so return -1.
//
//Constraints:
//2 <= nums.length <= 105
//2 <= nums[i] <= 105
