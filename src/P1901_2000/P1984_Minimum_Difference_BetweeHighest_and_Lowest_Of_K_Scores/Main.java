package P1901_2000.P1984_Minimum_Difference_BetweeHighest_and_Lowest_Of_K_Scores;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {90};
        int k1 = 1;
        System.out.println(solution.minimumDifference(nums1, k1)); // Output: 0

        int[] nums2 = {9, 4, 1, 7};
        int k2 = 2;
        System.out.println(solution.minimumDifference(nums2, k2)); // Output: 2
    }

    public int minimumDifference(int[] nums, int k) {
        // ff only one student is chosen, difference is zero
        if (k == 1) {
            return 0;
        }

        //sort the scores
        Arrays.sort(nums);

        int minDiff = Integer.MAX_VALUE;

        // sliding window of size k
        for (int i = 0; i <= nums.length - k; i++) {
            int diff = nums[i + k - 1] - nums[i];
            minDiff = Math.min(minDiff, diff);
        }

        return minDiff;
    }

}

//Complexity:
// time - O(n log n)
// space - O(1)


//You are given a 0-indexed integer array nums, where nums[i] represents the score of the ith student. You are also
// given an integer k.
//Pick the scores of any k students from the array so that the difference between the highest and the lowest of the k
// scores is minimized.
//Return the minimum possible difference.

//Example 1:
//Input: nums = [90], k = 1
//Output: 0
//Explanation: There is one way to pick score(s) of one student:
//- [90]. The difference between the highest and lowest score is 90 - 90 = 0.
//The minimum possible difference is 0.

//Example 2:
//Input: nums = [9,4,1,7], k = 2
//Output: 2
//Explanation: There are six ways to pick score(s) of two students:
//- [9,4,1,7]. The difference between the highest and lowest score is 9 - 4 = 5.
//- [9,4,1,7]. The difference between the highest and lowest score is 9 - 1 = 8.
//- [9,4,1,7]. The difference between the highest and lowest score is 9 - 7 = 2.
//- [9,4,1,7]. The difference between the highest and lowest score is 4 - 1 = 3.
//- [9,4,1,7]. The difference between the highest and lowest score is 7 - 4 = 3.
//- [9,4,1,7]. The difference between the highest and lowest score is 7 - 1 = 6.
//The minimum possible difference is 2.

//Constraints:
//1 <= k <= nums.length <= 1000
//0 <= nums[i] <= 105
