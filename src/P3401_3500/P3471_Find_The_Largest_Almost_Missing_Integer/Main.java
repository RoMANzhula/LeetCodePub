package P3401_3500.P3471_Find_The_Largest_Almost_Missing_Integer;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {3, 9, 2, 1, 7};
        int k1 = 3;

        System.out.println(solution.largestInteger(nums1, k1)); // 7


        int[] nums2 = {3, 9, 7, 2, 1, 7};
        int k2 = 4;

        System.out.println(solution.largestInteger(nums2, k2)); // 3


        int[] nums3 = {0, 0};
        int k3 = 1;

        System.out.println(solution.largestInteger(nums3, k3)); // -1
    }

    public int largestInteger(int[] nums, int k) {
        int[] subarrayCount = new int[51];

        // check every subarray of size k
        for (int start = 0; start <= nums.length - k; start++) {

            // prevent counting the same number twice inside the same subarray
            boolean[] seen = new boolean[51];

            for (int i = start; i < start + k; i++) {
                int value = nums[i];

                if (!seen[value]) {
                    seen[value] = true;
                    subarrayCount[value]++;
                }
            }
        }

        // find the largest number that appears in exactly one subarray
        for (int value = 50; value >= 0; value--) {
            if (subarrayCount[value] == 1) {
                return value;
            }
        }

        return -1;
    }

}

//Complexity:
// time - O(n^2)
// space - O(1)


//You are given an integer array nums and an integer k.
//An integer x is almost missing from nums if x appears in exactly one subarray of size k within nums.
//Return the largest almost missing integer from nums. If no such integer exists, return -1.
//A subarray is a contiguous sequence of elements within an array.

//Example 1:
//Input: nums = [3,9,2,1,7], k = 3
//Output: 7
//Explanation:
//1 appears in 2 subarrays of size 3: [9, 2, 1] and [2, 1, 7].
//2 appears in 3 subarrays of size 3: [3, 9, 2], [9, 2, 1], [2, 1, 7].
//3 appears in 1 subarray of size 3: [3, 9, 2].
//7 appears in 1 subarray of size 3: [2, 1, 7].
//9 appears in 2 subarrays of size 3: [3, 9, 2], and [9, 2, 1].
//We return 7 since it is the largest integer that appears in exactly one subarray of size k.

//Example 2:
//Input: nums = [3,9,7,2,1,7], k = 4
//Output: 3
//Explanation:
//1 appears in 2 subarrays of size 4: [9, 7, 2, 1], [7, 2, 1, 7].
//2 appears in 3 subarrays of size 4: [3, 9, 7, 2], [9, 7, 2, 1], [7, 2, 1, 7].
//3 appears in 1 subarray of size 4: [3, 9, 7, 2].
//7 appears in 3 subarrays of size 4: [3, 9, 7, 2], [9, 7, 2, 1], [7, 2, 1, 7].
//9 appears in 2 subarrays of size 4: [3, 9, 7, 2], [9, 7, 2, 1].
//We return 3 since it is the largest and only integer that appears in exactly one subarray of size k.

//Example 3:
//Input: nums = [0,0], k = 1
//Output: -1
//Explanation:
//There is no integer that appears in only one subarray of size 1.

//Constraints:
//1 <= nums.length <= 50
//0 <= nums[i] <= 50
//1 <= k <= nums.length
