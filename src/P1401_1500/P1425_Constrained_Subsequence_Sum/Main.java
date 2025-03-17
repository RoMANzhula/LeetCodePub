package P1401_1500.P1425_Constrained_Subsequence_Sum;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        int[] nums1 = {10, 2, -10, 5, 20};
        int k1 = 2;
        int result1 = maxSubsequenceSum(nums1, k1);
        System.out.println(result1); // Output: 37

        int[] nums2 = {-1, -2, -3};
        int k2 = 1;
        int result2 = maxSubsequenceSum(nums2, k2);
        System.out.println(result2); // Output: -1

        int[] nums3 = {10, -2, -10, -5, 20};
        int k3 = 2;
        int result3 = maxSubsequenceSum(nums3, k3);
        System.out.println(result3); // Output: 23
    }

    public static int maxSubsequenceSum(int[] nums, int k) {
        int n = nums.length;

        //create an array to store the maximum sum within a window of size k
        int[] maxSum = new int[n];
        maxSum[0] = nums[0];

        //initialize the deque to store indices of elements in decreasing order
        Deque<Integer> deque = new LinkedList<>();
        deque.add(0);

        for (int i = 1; i < n; i++) {
            //remove elements that are out of the current window from the front of the deque
            while (!deque.isEmpty() && i - deque.peekFirst() > k) {
                deque.pollFirst();
            }

            maxSum[i] = Math.max(maxSum[deque.peekFirst()], 0) + nums[i];

            //remove elements that are less than the current element from the back of the deque
            while (!deque.isEmpty() && maxSum[i] >= maxSum[deque.peekLast()]) {
                deque.pollLast();
            }

            //add the current element's index to the deque
            deque.add(i);

        }

        int result = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            result = Math.max(result, maxSum[i]);
        }

        return result; //bingo
    }

}

//Given an integer array nums and an integer k, return the maximum sum of a non-empty subsequence of that array
// such that for every two consecutive integers in the subsequence, nums[i] and nums[j], where i < j, the
// condition j - i <= k is satisfied.
//A subsequence of an array is obtained by deleting some number of elements (can be zero) from the array,
// leaving the remaining elements in their original order.

//Example 1:
//Input: nums = [10,2,-10,5,20], k = 2
//Output: 37
//Explanation: The subsequence is [10, 2, 5, 20].

//Example 2:
//Input: nums = [-1,-2,-3], k = 1
//Output: -1
//Explanation: The subsequence must be non-empty, so we choose the largest number.

//Example 3:
//Input: nums = [10,-2,-10,-5,20], k = 2
//Output: 23
//Explanation: The subsequence is [10, -2, -5, 20].

//Constraints:
//1 <= k <= nums.length <= 105
//-104 <= nums[i] <= 104
