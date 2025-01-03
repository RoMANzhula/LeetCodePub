package P2201_2300.P2270_Number_of_Ways_to_Split_Array;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {10, 4, -8, 7};
        System.out.println(solution.waysToSplitArray(nums1)); // 2

        int[] nums2 = {2, 3, 1, 0};
        System.out.println(solution.waysToSplitArray(nums2)); // 2
    }

    public int waysToSplitArray(int[] nums) {
        int lengthN = nums.length;

        // step 1: calculate the total sum of the array
        long totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        // step 2: traverse the 'nums' and calculate the prefix sums
        long leftSum = 0;
        int validSplits = 0;

        for (int i = 0; i < lengthN - 1; i++) {
            leftSum += nums[i];
            long rightSum = totalSum - leftSum;

            // checking if the split is valid
            if (leftSum >= rightSum) validSplits++;
        }

        return validSplits;
    }

}

//Time Complexity:
//O(n) since we iterate through the array twice — once for computing the total sum and once for checking the splits.
//Space Complexity:
//O(1) as we use only a few variables for calculations.


//You are given a 0-indexed integer array nums of length n.
//nums contains a valid split at index i if the following are true:
//The sum of the first i + 1 elements is greater than or equal to the sum of the last n - i - 1 elements.
//There is at least one element to the right of i. That is, 0 <= i < n - 1.
//Return the number of valid splits in nums.
//
//Example 1:
//Input: nums = [10,4,-8,7]
//Output: 2
//Explanation:
//There are three ways of splitting nums into two non-empty parts:
//- Split nums at index 0. Then, the first part is [10], and its sum is 10. The second part is [4,-8,7], and
// its sum is 3. Since 10 >= 3, i = 0 is a valid split.
//- Split nums at index 1. Then, the first part is [10,4], and its sum is 14. The second part is [-8,7], and
// its sum is -1. Since 14 >= -1, i = 1 is a valid split.
//- Split nums at index 2. Then, the first part is [10,4,-8], and its sum is 6. The second part is [7], and
// its sum is 7. Since 6 < 7, i = 2 is not a valid split.
//Thus, the number of valid splits in nums is 2.

//Example 2:
//Input: nums = [2,3,1,0]
//Output: 2
//Explanation:
//There are two valid splits in nums:
//- Split nums at index 1. Then, the first part is [2,3], and its sum is 5. The second part is [1,0], and
// its sum is 1. Since 5 >= 1, i = 1 is a valid split.
//- Split nums at index 2. Then, the first part is [2,3,1], and its sum is 6. The second part is [0], and
// its sum is 0. Since 6 >= 0, i = 2 is a valid split.
//
//Constraints:
//2 <= nums.length <= 105
//-105 <= nums[i] <= 105
