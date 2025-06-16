package P2001_2100.P2016_Maximum_Difference_Between_Increasing_Elements;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {7, 1, 5, 4};
        System.out.println(solution.maximumDifference(nums1)); // Output: 4

        int[] nums2 = {9, 4, 3, 2};
        System.out.println(solution.maximumDifference(nums2)); // Output: -1

        int[] nums3 = {1, 5, 2, 10};
        System.out.println(solution.maximumDifference(nums3)); // Output: 9
    }

    public int maximumDifference(int[] nums) {
        int minValue = nums[0]; // minimum value seen so far
        int maxDiff = -1;       // default if no valid i, j found

        for (int j = 1; j < nums.length; j++) {
            if (nums[j] > minValue) {
                // found a valid pair, update maxDiff if needed
                maxDiff = Math.max(maxDiff, nums[j] - minValue);
            } else {
                // update minValue if current element is smaller
                minValue = nums[j];
            }
        }

        return maxDiff;
    }

}

//Complexity:
// time - O(n)
// space - O(1)


//Given a 0-indexed integer array nums of size n, find the maximum difference between nums[i] and
// nums[j] (i.e., nums[j] - nums[i]), such that 0 <= i < j < n and nums[i] < nums[j].
//Return the maximum difference. If no such i and j exists, return -1.

//Example 1:
//Input: nums = [7,1,5,4]
//Output: 4
//Explanation:
//The maximum difference occurs with i = 1 and j = 2, nums[j] - nums[i] = 5 - 1 = 4.
//Note that with i = 1 and j = 0, the difference nums[j] - nums[i] = 7 - 1 = 6, but i > j, so it is not valid.

//Example 2:
//Input: nums = [9,4,3,2]
//Output: -1
//Explanation:
//There is no i and j such that i < j and nums[i] < nums[j].

//Example 3:
//Input: nums = [1,5,2,10]
//Output: 9
//Explanation:
//The maximum difference occurs with i = 0 and j = 3, nums[j] - nums[i] = 10 - 1 = 9.

//Constraints:
//n == nums.length
//2 <= n <= 1000
//1 <= nums[i] <= 109
