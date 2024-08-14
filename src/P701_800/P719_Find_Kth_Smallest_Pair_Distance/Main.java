package P701_800.P719_Find_Kth_Smallest_Pair_Distance;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 3, 1};
        int k1 = 1;
        System.out.println(solution.smallestDistancePair(nums1, k1)); // Output: 0

        int[] nums2 = {1, 1, 1};
        int k2 = 2;
        System.out.println(solution.smallestDistancePair(nums2, k2)); // Output: 0

        int[] nums3 = {1, 6, 1};
        int k3 = 3;
        System.out.println(solution.smallestDistancePair(nums3, k3)); // Output: 5
    }

    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);

        int left = 0, right = nums[nums.length - 1] - nums[0];

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (countPairs(nums, mid) < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    private int countPairs(int[] nums, int mid) {
        int count = 0;
        int j = 0;

        for (int i = 0; i < nums.length; i++) {
            while (j < nums.length && nums[j] - nums[i] <= mid) {
                j++;
            }
            count += j - i - 1;
        }

        return count;
    }
}

//Explanation:
//Binary Search: We search the distance in the range [0, max(nums) - min(nums)]. For each midpoint (mid), we count
// how many pairs have a distance less than or equal to mid.
//Counting Function: The function countPairs counts the number of pairs with a distance less than or equal to
// mid. This is done using a two-pointer technique where i is fixed and j increments to find the maximum j such
// that the distance is within mid.
//This approach is efficient and works within the constraints provided, making it suitable for large input sizes.


//The distance of a pair of integers a and b is defined as the absolute difference between a and b.
//Given an integer array nums and an integer k, return the kth smallest distance among all the pairs nums[i] and
// nums[j] where 0 <= i < j < nums.length.
//
//Example 1:
//Input: nums = [1,3,1], k = 1
//Output: 0
//Explanation: Here are all the pairs:
//(1,3) -> 2
//(1,1) -> 0
//(3,1) -> 2
//Then the 1st smallest distance pair is (1,1), and its distance is 0.

//Example 2:
//Input: nums = [1,1,1], k = 2
//Output: 0

//Example 3:
//Input: nums = [1,6,1], k = 3
//Output: 5
//
//Constraints:
//n == nums.length
//2 <= n <= 104
//0 <= nums[i] <= 106
//1 <= k <= n * (n - 1) / 2