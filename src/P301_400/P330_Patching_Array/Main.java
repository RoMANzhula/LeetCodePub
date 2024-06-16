package P301_400.P330_Patching_Array;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 3};
        int n1 = 6;
        System.out.println(solution.minPatches(nums1, n1)); // Output: 1

        int[] nums2 = {1, 5, 10};
        int n2 = 20;
        System.out.println(solution.minPatches(nums2, n2)); // Output: 2

        int[] nums3 = {1, 2, 2};
        int n3 = 5;
        System.out.println(solution.minPatches(nums3, n3)); // Output: 0
    }

    public int minPatches(int[] nums, int n) {
        long miss = 1; // the smallest number that cannot be formed
        int i = 0; // index for the current position in nums array
        int patches = 0; // number of patches required

        while (miss <= n) {
            if (i < nums.length && nums[i] <= miss) {
                // If nums[i] is less than or equal to miss, we can form numbers up to miss + nums[i]
                miss += nums[i];
                i++;
            } else {
                // Otherwise, we need to patch the array by adding miss
                miss += miss;
                patches++;
            }
        }
        return patches;
    }
}

//Explanation:
//Initialization:
//miss is initialized to 1. It represents the smallest number that we currently cannot form.
//i is the index to iterate through the nums array.
//patches counts how many patches (additions) we need to make.
//Loop:
//
//The loop continues until miss exceeds n.
//If the current element in nums (nums[i]) is less than or equal to miss, it means we can extend the range of
// numbers we can form up to miss + nums[i]. Therefore, we add nums[i] to miss and move to the next element (i++).
//If nums[i] is greater than miss, we need to patch the array by adding miss itself. This doubles miss (since we
// add miss to the sum, extending the range) and increments the patches count.
//Return:
//Once the loop ends, the patches variable holds the minimum number of patches required.
//This solution ensures that we always have the minimum number of patches by adding the smallest
// required patch each time.


//Given a sorted integer array nums and an integer n, add/patch elements to the array such that any number in the
// range [1, n] inclusive can be formed by the sum of some elements in the array.
//Return the minimum number of patches required.
//
//Example 1:
//Input: nums = [1,3], n = 6
//Output: 1
//Explanation:
//Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
//Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3].
//Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
//So we only need 1 patch.

//Example 2:
//Input: nums = [1,5,10], n = 20
//Output: 2
//Explanation: The two patches can be [2, 4].

//Example 3:
//Input: nums = [1,2,2], n = 5
//Output: 0
//
//Constraints:
//1 <= nums.length <= 1000
//1 <= nums[i] <= 104
//nums is sorted in ascending order.
//1 <= n <= 231 - 1