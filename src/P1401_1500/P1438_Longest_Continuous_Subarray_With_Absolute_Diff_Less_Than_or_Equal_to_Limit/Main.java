package P1401_1500.P1438_Longest_Continuous_Subarray_With_Absolute_Diff_Less_Than_or_Equal_to_Limit;

import java.util.Deque;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {8, 2, 4, 7};
        int limit1 = 4;
        System.out.println(solution.longestSubarray(nums1, limit1)); // Output: 2

        int[] nums2 = {10, 1, 2, 4, 7, 2};
        int limit2 = 5;
        System.out.println(solution.longestSubarray(nums2, limit2)); // Output: 4

        int[] nums3 = {4, 2, 2, 2, 4, 4, 2, 2};
        int limit3 = 0;
        System.out.println(solution.longestSubarray(nums3, limit3)); // Output: 3
    }

    public int longestSubarray(int[] nums, int limit) {
        // Deques to store the indices of the minimum and maximum elements
        Deque<Integer> minDeque = new LinkedList<>();
        Deque<Integer> maxDeque = new LinkedList<>();

        int left = 0;
        int result = 0;

        for (int right = 0; right < nums.length; right++) {
            // Maintain the maxDeque
            while (!maxDeque.isEmpty() && nums[maxDeque.peekLast()] <= nums[right]) {
                maxDeque.pollLast();
            }
            maxDeque.offerLast(right);

            // Maintain the minDeque
            while (!minDeque.isEmpty() && nums[minDeque.peekLast()] >= nums[right]) {
                minDeque.pollLast();
            }
            minDeque.offerLast(right);

            // Ensure the current window satisfies the condition
            while (nums[maxDeque.peekFirst()] - nums[minDeque.peekFirst()] > limit) {
                // Move the left pointer
                if (maxDeque.peekFirst() == left) {
                    maxDeque.pollFirst();
                }
                if (minDeque.peekFirst() == left) {
                    minDeque.pollFirst();
                }
                left++;
            }

            // Calculate the length of the current valid window
            result = Math.max(result, right - left + 1);
        }

        return result;
    }
}

//Explanation:
//Deques Initialization: We use two deques minDeque and maxDeque to keep track of the indices of the
// minimum and maximum values in the current window.
//Sliding Window: We iterate over the array using the right pointer.
//Maintaining Deques:
//For maxDeque: Remove elements from the back while the current element is greater than the elements at the back.
//For minDeque: Remove elements from the back while the current element is smaller than the elements at the back.
//Checking Condition: If the difference between the maximum and minimum elements in the current window exceeds the
// limit, move the left pointer to the right until the condition is satisfied.
//Calculating Result: Update the result with the maximum length of the valid window.
//This approach ensures that the solution runs efficiently in O(n) time complexity, where n is the length of the
// array. This is because each element is added and removed from the deques at most once.


//Given an array of integers nums and an integer limit, return the size of the longest non-empty subarray such
// that the absolute difference between any two elements of this subarray is less than or equal to limit.
//
//Example 1:
//Input: nums = [8,2,4,7], limit = 4
//Output: 2
//Explanation: All subarrays are:
//[8] with maximum absolute diff |8-8| = 0 <= 4.
//[8,2] with maximum absolute diff |8-2| = 6 > 4.
//[8,2,4] with maximum absolute diff |8-2| = 6 > 4.
//[8,2,4,7] with maximum absolute diff |8-2| = 6 > 4.
//[2] with maximum absolute diff |2-2| = 0 <= 4.
//[2,4] with maximum absolute diff |2-4| = 2 <= 4.
//[2,4,7] with maximum absolute diff |2-7| = 5 > 4.
//[4] with maximum absolute diff |4-4| = 0 <= 4.
//[4,7] with maximum absolute diff |4-7| = 3 <= 4.
//[7] with maximum absolute diff |7-7| = 0 <= 4.
//Therefore, the size of the longest subarray is 2.

//Example 2:

//Input: nums = [10,1,2,4,7,2], limit = 5
//Output: 4
//Explanation: The subarray [2,4,7,2] is the longest since the maximum absolute diff is |2-7| = 5 <= 5.
//Example 3:
//
//Input: nums = [4,2,2,2,4,4,2,2], limit = 0
//Output: 3
//
//
//Constraints:
//
//1 <= nums.length <= 105
//1 <= nums[i] <= 109
//0 <= limit <= 109