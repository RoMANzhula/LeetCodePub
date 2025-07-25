package P3401_3500.P3487_Maximum_Unique_Subarray_Sum_After_Deletion;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 2, 3, 4, 5};
        int[] nums2 = {1, 1, 0, 1, 1};
        int[] nums3 = {1, 2, -1, -2, 1, 0, -1};

        System.out.println(solution.maxSum(new int[]{2, -10, 6}));          // 8
        System.out.println(solution.maxSum(new int[]{-100}));              // -100
        System.out.println(solution.maxSum(new int[]{-20, 20}));           // 20
        System.out.println(solution.maxSum(new int[]{1, 1, 0, 1, 1}));      // 1
        System.out.println(solution.maxSum(new int[]{1, 2, 3, 4, 5}));      // 15
        System.out.println(solution.maxSum(new int[]{1, 2, -1, -2, 1, 0})); // 3

    }

    public int maxSum(int[] nums) {
        int maxVal = Arrays.stream(nums).max().orElse(Integer.MIN_VALUE);

        if (maxVal <= 0) {
            return maxVal;
        }

        Set<Integer> uniqueNums = new HashSet<>();

        for (int num : nums) {
            uniqueNums.add(num);
        }

        int sum = 0;

        for (int num : uniqueNums) {
            sum += Math.max(0, num);
        }

        return sum;
    }

}

//Complexity:
// time and space - O(n)


//You are given an integer array nums.
//You are allowed to delete any number of elements from nums without making it empty. After performing the deletions,
// select a subarray of nums such that:
//All elements in the subarray are unique.
//The sum of the elements in the subarray is maximized.
//Return the maximum sum of such a subarray.

//Example 1:
//Input: nums = [1,2,3,4,5]
//Output: 15
//Explanation:
//Select the entire array without deleting any element to obtain the maximum sum.
//Example 2:

//Input: nums = [1,1,0,1,1]
//Output: 1
//Explanation:
//Delete the element nums[0] == 1, nums[1] == 1, nums[2] == 0, and nums[3] == 1. Select the entire array [1] to
// obtain the maximum sum.

//Example 3:
//Input: nums = [1,2,-1,-2,1,0,-1]
//Output: 3
//Explanation:
//Delete the elements nums[2] == -1 and nums[3] == -2, and select the subarray [2, 1] from [1, 2, 1, 0, -1] to obtain
// the maximum sum.

//Constraints:
//1 <= nums.length <= 100
//-100 <= nums[i] <= 100
