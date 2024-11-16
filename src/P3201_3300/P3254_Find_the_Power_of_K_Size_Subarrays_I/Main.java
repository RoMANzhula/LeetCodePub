package P3201_3300.P3254_Find_the_Power_of_K_Size_Subarrays_I;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 2, 3, 4, 3, 2, 5};
        int k1 = 3;
        System.out.println(Arrays.toString(solution.resultsArray(nums1, k1))); // Output: [3, 4, -1, -1, -1]

        // Example 2
        int[] nums2 = {2, 2, 2, 2, 2};
        int k2 = 4;
        System.out.println(Arrays.toString(solution.resultsArray(nums2, k2))); // Output: [-1, -1]

        // Example 3
        int[] nums3 = {3, 2, 3, 2, 3, 2};
        int k3 = 2;
        System.out.println(Arrays.toString(solution.resultsArray(nums3, k3))); // Output: [-1, 3, -1, 3, -1]
    }

//    public static int[] resultsArray(int[] nums, int k) {
//        int n = nums.length;
//        int[] results = new int[n - k + 1];
//
//        for (int i = 0; i <= n - k; i++) {
//            // Extract the subarray of size k
//            int[] subarray = Arrays.copyOfRange(nums, i, i + k);
//
//            // Check if the subarray is sorted in ascending order
//            boolean isSorted = true;
//            for (int j = 1; j < k; j++) {
//                if (subarray[j] != subarray[j - 1] + 1) {
//                    isSorted = false;
//                    break;
//                }
//            }
//
//            // Calculate power if sorted, else assign -1
//            if (isSorted) {
//                results[i] = subarray[k - 1]; // Maximum element is the last one in the sorted array
//            } else {
//                results[i] = -1;
//            }
//        }
//
//        return results;
//    }

    //faster solution
    public int[] resultsArray(int[] nums, int k) {
        int n = nums.length;
        int[] results = new int[n - k + 1];

        for (int i = 0; i <= n - k; i++) {
            // Check if the current window satisfies the conditions
            boolean isValid = true;

            for (int j = i + 1; j < i + k; j++) {
                if (nums[j] != nums[j - 1] + 1) {
                    isValid = false;
                    break;
                }
            }

            if (isValid) {
                results[i] = nums[i + k - 1]; // Maximum element in sorted array is the last element
            } else {
                results[i] = -1;
            }
        }

        return results;
    }

}

//Instead of checking the entire subarray each time, use the sliding window to focus only on the boundary changes.
//Efficiency:
//Time Complexity:
//O(n), as we only traverse the array once.
//Space Complexity:
//O(1), as we don't store subarrays explicitly.


//You are given an array of integers nums of length n and a positive integer k.
//The power of an array is defined as:
//Its maximum element if all of its elements are consecutive and sorted in ascending order.
//-1 otherwise.
//You need to find the power of all subarrays of nums of size k.
//Return an integer array results of size n - k + 1, where results[i] is the power of nums[i..(i + k - 1)].
//
//Example 1:
//Input: nums = [1,2,3,4,3,2,5], k = 3
//Output: [3,4,-1,-1,-1]
//Explanation:
//There are 5 subarrays of nums of size 3:
//[1, 2, 3] with the maximum element 3.
//[2, 3, 4] with the maximum element 4.
//[3, 4, 3] whose elements are not consecutive.
//[4, 3, 2] whose elements are not sorted.
//[3, 2, 5] whose elements are not consecutive.

//Example 2:
//Input: nums = [2,2,2,2,2], k = 4
//Output: [-1,-1]

//Example 3:
//Input: nums = [3,2,3,2,3,2], k = 2
//Output: [-1,3,-1,3,-1]
//
//Constraints:
//1 <= n == nums.length <= 500
//1 <= nums[i] <= 105
//1 <= k <= n
