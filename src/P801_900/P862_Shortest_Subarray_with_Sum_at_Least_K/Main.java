package P801_900.P862_Shortest_Subarray_with_Sum_at_Least_K;

import java.util.Deque;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.shortestSubarray(new int[]{1}, 1)); // Output: 1
        System.out.println(solution.shortestSubarray(new int[]{1, 2}, 4)); // Output: -1
        System.out.println(solution.shortestSubarray(new int[]{2, -1, 2}, 3)); // Output: 3
    }

    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        long[] prefixSum = new long[n + 1];

        // Compute prefix sums
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }

        // Deque to store indices
        Deque<Integer> deque = new LinkedList<>();
        int minLength = Integer.MAX_VALUE;

        for (int i = 0; i <= n; i++) {
            // Check if we can find a valid subarray
            while (!deque.isEmpty() && prefixSum[i] - prefixSum[deque.peekFirst()] >= k) {
                minLength = Math.min(minLength, i - deque.pollFirst());
            }

            // Remove indices that are no longer useful
            while (!deque.isEmpty() && prefixSum[i] <= prefixSum[deque.peekLast()]) {
                deque.pollLast();
            }

            // Add current index to the deque
            deque.addLast(i);
        }

        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }
}

//Explanation of Key Steps:
//Prefix Sum:
//Helps compute subarray sums efficiently.
//Deque Operations:
//pollFirst(): Removes indices whose subarray sum with the current index meets the condition.
//pollLast(): Removes indices with prefix sums greater than or equal to the current prefix sum, as
// they are no longer useful.
//Complexity Analysis:
//Time Complexity:
//O(n)
//Each index is added and removed from the deque at most once.
//Space Complexity:
//O(n)
//For storing the prefix sums and the deque.


//Given an integer array nums and an integer k, return the length of the shortest non-empty subarray of nums
// with a sum of at least k. If there is no such subarray, return -1.
//A subarray is a contiguous part of an array.
//
//Example 1:
//Input: nums = [1], k = 1
//Output: 1

//Example 2:
//Input: nums = [1,2], k = 4
//Output: -1

//Example 3:
//Input: nums = [2,-1,2], k = 3
//Output: 3
//
//Constraints:
//1 <= nums.length <= 105
//-105 <= nums[i] <= 105
//1 <= k <= 109