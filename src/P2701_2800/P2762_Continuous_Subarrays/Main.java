package P2701_2800.P2762_Continuous_Subarrays;

import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {5, 4, 2, 4};
        System.out.println("Output: " + solution.continuousSubarrays(nums1)); // Expected: 8

        // Test case 2
        int[] nums2 = {1, 2, 3};
        System.out.println("Output: " + solution.continuousSubarrays(nums2)); // Expected: 6
    }

    public long continuousSubarrays(int[] nums) {
        int n = nums.length;
        long total = 0;

        // Sliding window variables
        int left = 0;
        TreeMap<Integer, Integer> freqMap = new TreeMap<>();

        for (int right = 0; right < n; right++) {
            // Add the current element to the frequency map
            freqMap.put(nums[right], freqMap.getOrDefault(nums[right], 0) + 1);

            // Check the condition for the window
            while (freqMap.lastKey() - freqMap.firstKey() > 2) {
                // Shrink the window from the left
                freqMap.put(nums[left], freqMap.get(nums[left]) - 1);
                if (freqMap.get(nums[left]) == 0) {
                    freqMap.remove(nums[left]);
                }
                left++;
            }

            // All subarrays ending at 'right' and starting from 'left' to 'right' are valid
            total += (right - left + 1);
        }

        return total;
    }
}

//Explanation:
//Sliding Window:
//Use two pointers (left and right) to maintain the bounds of the current window.
//Use a TreeMap to keep track of the frequency of elements within the current window. This allows us
// to efficiently determine the minimum (firstKey()) and maximum (lastKey()) values in the window.
//Condition Check:
//Continuously adjust the left pointer until the condition (max - min <= 2) is satisfied.
//Count Subarrays:
//For each position of right, all subarrays starting from left to right are valid. The count is (right - left + 1).
//Complexity:
//Time Complexity: O(n log k), where
//n is the length of the array and
//k is the number of distinct elements in any sliding window (managed by TreeMap).
//Space Complexity: O(k) for the TreeMap.


//You are given a 0-indexed integer array nums. A subarray of nums is called continuous if:
//Let i, i + 1, ..., j be the indices in the subarray. Then, for each pair of
// indices i <= i1, i2 <= j, 0 <= |nums[i1] - nums[i2]| <= 2.
//Return the total number of continuous subarrays.
//A subarray is a contiguous non-empty sequence of elements within an array.
//
//Example 1:
//Input: nums = [5,4,2,4]
//Output: 8
//Explanation:
//Continuous subarray of size 1: [5], [4], [2], [4].
//Continuous subarray of size 2: [5,4], [4,2], [2,4].
//Continuous subarray of size 3: [4,2,4].
//Thereare no subarrys of size 4.
//Total continuous subarrays = 4 + 3 + 1 = 8.
//It can be shown that there are no more continuous subarrays.
//
//Example 2:
//Input: nums = [1,2,3]
//Output: 6
//Explanation:
//Continuous subarray of size 1: [1], [2], [3].
//Continuous subarray of size 2: [1,2], [2,3].
//Continuous subarray of size 3: [1,2,3].
//Total continuous subarrays = 3 + 2 + 1 = 6.
//
//Constraints:
//1 <= nums.length <= 105
//1 <= nums[i] <= 109