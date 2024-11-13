package P2501_2600.P2563_Count_the_Number_of_Fair_Pairs;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.countFairPairs(new int[]{0, 1, 7, 4, 4, 5}, 3, 6)); // Output: 6
        System.out.println(solution.countFairPairs(new int[]{1, 7, 9, 2, 5}, 11, 11)); // Output: 1
    }

    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        int n = nums.length;
        long fairPairsCount = 0;

        for (int i = 0; i < n - 1; i++) {
            int left = binarySearchLeft(nums, i + 1, n - 1, lower - nums[i]);
            int right = binarySearchRight(nums, i + 1, n - 1, upper - nums[i]);

            if (left != -1 && right != -1 && left <= right) {
                fairPairsCount += right - left + 1;
            }
        }

        return fairPairsCount;
    }

    private int binarySearchLeft(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left < nums.length ? left : -1;
    }

    private int binarySearchRight(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right >= 0 ? right : -1;
    }
}

//Explanation of Helper Functions:
//binarySearchLeft: Finds the smallest index left where nums[i] + nums[left] >= lower.
//binarySearchRight: Finds the largest index right where nums[i] + nums[right] <= upper.
//Complexity Analysis:
//Time Complexity:
//O(n log n) due to sorting and binary search for each element.
//Space Complexity:
//O(1) if we ignore the sorting space, or O(n) if including the sort.


//Given a 0-indexed integer array nums of size n and two integers lower and upper, return the number of fair pairs.
//A pair (i, j) is fair if:
//0 <= i < j < n, and
//lower <= nums[i] + nums[j] <= upper
//
//Example 1:
//Input: nums = [0,1,7,4,4,5], lower = 3, upper = 6
//Output: 6
//Explanation: There are 6 fair pairs: (0,3), (0,4), (0,5), (1,3), (1,4), and (1,5).

//Example 2:
//Input: nums = [1,7,9,2,5], lower = 11, upper = 11
//Output: 1
//Explanation: There is a single fair pair: (2,3).
//
//Constraints:
//1 <= nums.length <= 105
//nums.length == n
//-109 <= nums[i] <= 109
//-109 <= lower <= upper <= 109
