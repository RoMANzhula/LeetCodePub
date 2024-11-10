package P3001_3100.P3097_Shortest_Subarray_With_OR_at_Least_K_II;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 2, 3};
        int k1 = 2;
        System.out.println("Example 1: " + solution.minimumSubarrayLength(nums1, k1)); // Output: 1

        int[] nums2 = {2, 1, 8};
        int k2 = 10;
        System.out.println("Example 2: " + solution.minimumSubarrayLength(nums2, k2)); // Output: 3

        int[] nums3 = {1, 2};
        int k3 = 0;
        System.out.println("Example 3: " + solution.minimumSubarrayLength(nums3, k3)); // Output: 1

    }

    private static final int kMaxBit = 30;  // Max bit count

    public int minimumSubarrayLength(int[] nums, int k) {
        final int n = nums.length;
        int ans = n + 1;
        int ors = 0;
        int[] count = new int[kMaxBit + 1];

        for (int l = 0, r = 0; r < n; r++) {
            ors = orNum(ors, nums[r], count);
            while (ors >= k && l <= r) {
                ans = Math.min(ans, r - l + 1);
                ors = undoOrNum(ors, nums[l], count);
                l++;
            }
        }

        return (ans == n + 1) ? -1 : ans;
    }

    // Calculate the OR value and update the count array
    private int orNum(int ors, int num, int[] count) {
        for (int i = 0; i < kMaxBit; i++) {
            if ((num >> i & 1) == 1 && ++count[i] == 1) {
                ors += 1 << i;
            }
        }
        return ors;
    }

    // Undo the OR value and update the count array
    private int undoOrNum(int ors, int num, int[] count) {
        for (int i = 0; i < kMaxBit; i++) {
            if ((num >> i & 1) == 1 && --count[i] == 0) {
                ors -= 1 << i;
            }
        }
        return ors;
    }
}

//Explanation:
//Variables & Data Structures:
//- ors keeps track of the OR value for the current subarray.
//- count is used to track how many times each bit is encountered in the subarray. It is necessary to undo
// the OR operation when moving the left pointer (l).
//- ans is initialized to n + 1 to signify that no valid subarray has been found yet. It is updated when a
// valid subarray is found.
//Main Logic:
//Sliding Window: We iterate with the right pointer (r) and calculate the OR for each subarray. Once the OR
// exceeds or meets the required value k, we try shrinking the window from the left (l) while maintaining the
// OR condition. The length of the valid subarray is updated if it is smaller than the previously found subarrays.
//Helper Methods:
//- orNum: This method adds a number's bits to the OR value while updating the bit counts. It uses bitwise
// operations to check each bit of the number.
//- undoOrNum: This method removes a number's bits from the OR value while updating the bit counts, allowing
// the sliding window to shrink correctly.
//Edge Cases:
//The result will be -1 if no valid subarray is found.
//If the condition is met by a subarray, the minimum length is updated.


//You are given an array nums of non-negative integers and an integer k.
//An array is called special if the bitwise OR of all of its elements is at least k.
//Return the length of the shortest special non-empty subarray of nums, or return -1 if no special subarray exists.
//
//Example 1:
//Input: nums = [1,2,3], k = 2
//Output: 1
//Explanation:
//The subarray [3] has OR value of 3. Hence, we return 1.

//Example 2:
//Input: nums = [2,1,8], k = 10
//Output: 3
//Explanation:
//The subarray [2,1,8] has OR value of 11. Hence, we return 3.

//Example 3:
//Input: nums = [1,2], k = 0
//Output: 1
//Explanation:
//The subarray [1] has OR value of 1. Hence, we return 1.
//
//Constraints:
//1 <= nums.length <= 2 * 105
//0 <= nums[i] <= 109
//0 <= k <= 109
