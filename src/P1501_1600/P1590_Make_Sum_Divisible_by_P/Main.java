package P1501_1600.P1590_Make_Sum_Divisible_by_P;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {3, 1, 4, 2};
        int p1 = 6;
        System.out.println(solution.minSubarray(nums1, p1)); // Output: 1

        int[] nums2 = {6, 3, 5, 2};
        int p2 = 9;
        System.out.println(solution.minSubarray(nums2, p2)); // Output: 2

        int[] nums3 = {1, 2, 3};
        int p3 = 3;
        System.out.println(solution.minSubarray(nums3, p3)); // Output: 0
    }

    public int minSubarray(int[] nums, int p) {
        // Step 1: Calculate the total sum of the array
        long totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        // Step 2: Calculate the remainder
        int remainder = (int)(totalSum % p);
        if (remainder == 0) {
            // If the total sum is divisible by p, no need to remove anything
            return 0;
        }

        // Step 3: Use a HashMap to track prefix sums modulo p
        HashMap<Integer, Integer> prefixModMap = new HashMap<>();
        prefixModMap.put(0, -1);  // Handle edge case for prefix starting from index 0
        int prefixSum = 0;
        int minLen = nums.length;

        for (int i = 0; i < nums.length; i++) {
            // Step 4: Update prefix sum and take modulo p
            prefixSum = (prefixSum + nums[i]) % p;

            // Step 5: Find the required mod value (to remove the correct subarray)
            int requiredMod = (prefixSum - remainder + p) % p;

            // Step 6: Check if we have seen this mod value before
            if (prefixModMap.containsKey(requiredMod)) {
                int prevIndex = prefixModMap.get(requiredMod);
                minLen = Math.min(minLen, i - prevIndex);
            }

            // Step 7: Update the HashMap with the current prefix sum mod value
            prefixModMap.put(prefixSum, i);
        }

        // Step 8: If no valid subarray was found, return -1
        return minLen == nums.length ? -1 : minLen;
    }
}

//Explanation:
//Total Sum: First, calculate the total sum of the array.
//Modulo Check: If the sum modulo p is already 0, return 0 since no subarray needs to be removed.
//Prefix Sums: Use prefix sums and store the modulo p of these sums in a HashMap. This allows efficient checking
// for a subarray whose sum can make the remaining array divisible by p.
//Result: The smallest subarray length is tracked and updated throughout the iteration. If no valid subarray
// is found, return -1.
//Time Complexity:
//The time complexity is O(n), where n is the length of the array. Each element is processed once, and HashMap
// operations (put/get) take constant time.
//Space Complexity:
//The space complexity is O(n), due to the HashMap storing prefix sums.


//Given an array of positive integers nums, remove the smallest subarray (possibly empty) such that the sum of the
// remaining elements is divisible by p. It is not allowed to remove the whole array.
//Return the length of the smallest subarray that you need to remove, or -1 if it's impossible.
//A subarray is defined as a contiguous block of elements in the array.
//
//Example 1:
//Input: nums = [3,1,4,2], p = 6
//Output: 1
//Explanation: The sum of the elements in nums is 10, which is not divisible by 6. We can remove the subarray [4], and
// the sum of the remaining elements is 6, which is divisible by 6.

//Example 2:
//Input: nums = [6,3,5,2], p = 9
//Output: 2
//Explanation: We cannot remove a single element to get a sum divisible by 9. The best way is to remove the
// subarray [5,2], leaving us with [6,3] with sum 9.

//Example 3
//Input: nums = [1,2,3], p = 3
//Output: 0
//Explanation: Here the sum is 6. which is already divisible by 3. Thus we do not need to remove anything.
//
//Constraints:
//1 <= nums.length <= 105
//1 <= nums[i] <= 109
//1 <= p <= 109