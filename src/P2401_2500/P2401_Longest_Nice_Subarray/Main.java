package P2401_2500.P2401_Longest_Nice_Subarray;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 3, 8, 48, 10};
        System.out.println(solution.longestNiceSubarray(nums1)); // Output: 3

        int[] nums2 = {3, 1, 5, 11, 13};
        System.out.println(solution.longestNiceSubarray(nums2)); // Output: 1
    }

    public int longestNiceSubarray(int[] nums) {
        int left = 0, maxLength = 0, currBitwiseAND = 0;

        for (int right = 0; right < nums.length; right++) {
            while ((currBitwiseAND & nums[right]) != 0) { // conflict detected
                currBitwiseAND ^= nums[left]; // remove leftmost element
                left++; // shrink window
            }

            currBitwiseAND |= nums[right]; // add new element
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

}

//Approach:
//We maintain a window [left, right] that ensures all elements in the window satisfy the "nice" property.
//Use a bitmask (currBitwiseAND) to track the bitwise OR of all elements in the window.
//Expand the window by moving right until we find an element that violates the "nice" property.
//If an element at right causes an AND conflict, shrink the window by moving left until the condition is restored.
//Keep track of the maximum window length encountered.
//Complexity Analysis:
//Time Complexity: O(N) (Each element is added and removed from the window at most once)
//Space Complexity: O(1) (Only a few integer variables are used)

//You are given an array nums consisting of positive integers.
//We call a subarray of nums nice if the bitwise AND of every pair of elements that are in different positions in
// the subarray is equal to 0.
//Return the length of the longest nice subarray.
//A subarray is a contiguous part of an array.
//Note that subarrays of length 1 are always considered nice.

//Example 1:
//Input: nums = [1,3,8,48,10]
//Output: 3
//Explanation: The longest nice subarray is [3,8,48]. This subarray satisfies the conditions:
//- 3 AND 8 = 0.
//- 3 AND 48 = 0.
//- 8 AND 48 = 0.
//It can be proven that no longer nice subarray can be obtained, so we return 3.

//Example 2:
//Input: nums = [3,1,5,11,13]
//Output: 1
//Explanation: The length of the longest nice subarray is 1. Any subarray of length 1 can be chosen.

//Constraints:
//1 <= nums.length <= 105
//1 <= nums[i] <= 109
