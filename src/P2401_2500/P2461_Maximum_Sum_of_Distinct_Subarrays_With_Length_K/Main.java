package P2401_2500.P2461_Maximum_Sum_of_Distinct_Subarrays_With_Length_K;

import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 5, 4, 2, 9, 9, 9};
        int k1 = 3;
        System.out.println(solution.maximumSubarraySum(nums1, k1)); // Output: 15

        int[] nums2 = {4, 4, 4};
        int k2 = 3;
        System.out.println(solution.maximumSubarraySum(nums2, k2)); // Output: 0
    }

    public long maximumSubarraySum(int[] nums, int k) {
        long maxSum = 0;
        long currentSum = 0;
        HashSet<Integer> set = new HashSet<>();

        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            // Add the current number to the sum
            currentSum += nums[right];

            // Add the current number to the set
            while (set.contains(nums[right])) {
                // Remove numbers from the left until the duplicate is removed
                set.remove(nums[left]);
                currentSum -= nums[left];
                left++;
            }
            set.add((int) nums[right]);

            // If the window size is k, evaluate the sum
            if (right - left + 1 == k) {
                maxSum = Math.max(maxSum, currentSum);
                // Slide the window by removing the leftmost element
                set.remove(nums[left]);
                currentSum -= nums[left];
                left++;
            }
        }
        return maxSum;
    }
}

//Explanation
//Sliding Window:
//Use two pointers, left and right, to represent the current window.
//Expand the window by moving right and add nums[right] to currentSum.
//If there’s a duplicate in the window, move left to shrink the window until the duplicate is removed.
//Window Validation:
//Ensure the window contains exactly k distinct elements before considering its sum for maxSum.
//Optimization:
//Using a HashSet ensures that checking and removing duplicates is efficient (O(1) average time complexity).
//Complexity
//Time Complexity:
//O(n), where n is the length of the array. Each element is processed at
// most twice (once when added and once when removed).
//Space Complexity:
//O(k), for the HashSet that stores at most k elements.


//You are given an integer array nums and an integer k. Find the maximum subarray sum of all the subarrays of
// nums that meet the following conditions:
//The length of the subarray is k, and
//All the elements of the subarray are distinct.
//Return the maximum subarray sum of all the subarrays that meet the conditions. If no subarray
// meets the conditions, return 0.
//A subarray is a contiguous non-empty sequence of elements within an array.
//
//Example 1:
//Input: nums = [1,5,4,2,9,9,9], k = 3
//Output: 15
//Explanation: The subarrays of nums with length 3 are:
//- [1,5,4] which meets the requirements and has a sum of 10.
//- [5,4,2] which meets the requirements and has a sum of 11.
//- [4,2,9] which meets the requirements and has a sum of 15.
//- [2,9,9] which does not meet the requirements because the element 9 is repeated.
//- [9,9,9] which does not meet the requirements because the element 9 is repeated.
//We return 15 because it is the maximum subarray sum of all the subarrays that meet the conditions

//Example 2:
//Input: nums = [4,4,4], k = 3
//Output: 0
//Explanation: The subarrays of nums with length 3 are:
//- [4,4,4] which does not meet the requirements because the element 4 is repeated.
//We return 0 because no subarrays meet the conditions.
//
//Constraints:
//1 <= k <= nums.length <= 105
//1 <= nums[i] <= 105
