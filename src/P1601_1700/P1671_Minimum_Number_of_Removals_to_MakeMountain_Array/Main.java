package P1601_1700.P1671_Minimum_Number_of_Removals_to_MakeMountain_Array;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 3, 1};
        int[] nums2 = {2, 1, 1, 5, 6, 2, 3, 1};

        System.out.println("Output for nums1: " + solution.minimumMountainRemovals(nums1)); // Output: 0
        System.out.println("Output for nums2: " + solution.minimumMountainRemovals(nums2)); // Output: 3
    }

    public int minimumMountainRemovals(int[] nums) {
        int n = nums.length;

        // Step 1: Calculate LIS from the left for each element
        int[] lis = new int[n];
        Arrays.fill(lis, 1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    lis[i] = Math.max(lis[i], lis[j] + 1);
                }
            }
        }

        // Step 2: Calculate LIS from the right (LDS) for each element
        int[] lds = new int[n];
        Arrays.fill(lds, 1);
        for (int i = n - 2; i >= 0; i--) {
            for (int j = n - 1; j > i; j--) {
                if (nums[i] > nums[j]) {
                    lds[i] = Math.max(lds[i], lds[j] + 1);
                }
            }
        }

        // Step 3: Calculate the maximum length of a mountain
        int maxMountainLength = 0;
        for (int i = 1; i < n - 1; i++) {
            if (lis[i] > 1 && lds[i] > 1) { // it should be a peak
                maxMountainLength = Math.max(maxMountainLength, lis[i] + lds[i] - 1);
            }
        }

        // Minimum removals = total elements - max mountain length
        return n - maxMountainLength;
    }

}

//You may recall that an array arr is a mountain array if and only if:
//
//arr.length >= 3
//There exists some index i (0-indexed) with 0 < i < arr.length - 1 such that:
//arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
//arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
//Given an integer array nums, return the minimum number of elements to remove to make nums a mountain array.
//
//Example 1:
//Input: nums = [1,3,1]
//Output: 0
//Explanation: The array itself is a mountain array so we do not need to remove any elements.

//Example 2:
//Input: nums = [2,1,1,5,6,2,3,1]
//Output: 3
//Explanation: One solution is to remove the elements at indices 0, 1, and 5, making the array nums = [1,5,6,3,1].
//
//Constraints:
//3 <= nums.length <= 1000
//1 <= nums[i] <= 109
//It is guaranteed that you can make a mountain array out of nums.


//Explanation
//Calculate LIS: We calculate the longest increasing subsequence for each element up to that
// point, storing the result in the lis array.
//Calculate LDS: We compute the longest decreasing subsequence for each element starting from
// that point, storing results in lds.
//Calculate the Mountain Array Length: For each index
//i, check if it can be a peak (i.e., lis[i] > 1 and lds[i] > 1). If so, calculate the mountain length at
//i as lis[i] + lds[i] - 1.
//Calculate Minimum Removals: Subtract the length of the longest mountain found from the total length of
// the array to get the minimum number of deletions needed.
//Complexity
//Time Complexity:
//O(n 2), due to the nested loops for LIS and LDS calculations.
//Space Complexity:
//O(n), for storing the lis and lds arrays.