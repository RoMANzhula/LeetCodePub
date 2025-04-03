package P2801_2900.P2874_Maximum_Value_of_an_Ordered_Triplet_II;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {12, 6, 1, 2, 7};
        int[] nums2 = {1, 10, 3, 4, 19};
        int[] nums3 = {1, 2, 3};

        System.out.println(solution.maximumTripletValue(nums1)); // Output: 77
        System.out.println(solution.maximumTripletValue(nums2)); // Output: 133
        System.out.println(solution.maximumTripletValue(nums3)); // Output: 0
    }

    public long maximumTripletValue(int[] nums) {
        int n = nums.length;
        if (n < 3) return 0;

        // arrays to store prefix and suffix maximums
        int[] maxLeft = new int[n];
        int[] maxRight = new int[n];

        // compute maxLeft[i] for i < j
        maxLeft[0] = nums[0];
        for (int i = 1; i < n; i++) {
            maxLeft[i] = Math.max(maxLeft[i - 1], nums[i]);
        }

        // compute maxRight[k] for k > j
        maxRight[n - 1] = nums[n - 1];
        for (int k = n - 2; k >= 0; k--) {
            maxRight[k] = Math.max(maxRight[k + 1], nums[k]);
        }

        // calculate maximum triplet value
        long maxValue = 0;
        for (int j = 1; j < n - 1; j++) {
            long tripletValue = (long) (maxLeft[j - 1] - nums[j]) * maxRight[j + 1];
            maxValue = Math.max(maxValue, tripletValue);
        }

        return maxValue;
    }

}

//Explanation:
//Precompute maxLeft and maxRight in O(n).
//Iterate over j (middle element) in O(n) and compute the result efficiently.
//Uses long to prevent integer overflow for large values.
//Time Complexity: O(n)


//You are given a 0-indexed integer array nums.
//Return the maximum value over all triplets of indices (i, j, k) such that i < j < k. If all such triplets have a
// negative value, return 0.
//The value of a triplet of indices (i, j, k) is equal to (nums[i] - nums[j]) * nums[k].

//Example 1:
//Input: nums = [12,6,1,2,7]
//Output: 77
//Explanation: The value of the triplet (0, 2, 4) is (nums[0] - nums[2]) * nums[4] = 77.
//It can be shown that there are no ordered triplets of indices with a value greater than 77.

//Example 2:
//Input: nums = [1,10,3,4,19]
//Output: 133
//Explanation: The value of the triplet (1, 2, 4) is (nums[1] - nums[2]) * nums[4] = 133.
//It can be shown that there are no ordered triplets of indices with a value greater than 133.

//Example 3:
//Input: nums = [1,2,3]
//Output: 0
//Explanation: The only ordered triplet of indices (0, 1, 2) has a negative value
// of (nums[0] - nums[1]) * nums[2] = -3. Hence, the answer would be 0.

//Constraints:
//3 <= nums.length <= 105
//1 <= nums[i] <= 106
