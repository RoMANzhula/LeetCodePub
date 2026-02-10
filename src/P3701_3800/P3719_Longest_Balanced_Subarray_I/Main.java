package P3701_3800.P3719_Longest_Balanced_Subarray_I;

import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {2, 5, 4, 3};
        int[] nums2 = {3, 2, 2, 5, 4};
        int[] nums3 = {1, 2, 3, 2};

        System.out.println(solution.longestBalanced(nums1)); // 4
        System.out.println(solution.longestBalanced(nums2)); // 5
        System.out.println(solution.longestBalanced(nums3)); // 3
    }

    public int longestBalanced(int[] nums) {
        int n = nums.length;
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            Set<Integer> evenSet = new HashSet<>();
            Set<Integer> oddSet = new HashSet<>();

            for (int j = i; j < n; j++) {
                if (nums[j] % 2 == 0) {
                    evenSet.add(nums[j]);
                } else {
                    oddSet.add(nums[j]);
                }

                if (evenSet.size() == oddSet.size()) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }

        return maxLen;
    }

}

//Complexity:
// time - O(n^2)
// space - O(n)


//You are given an integer array nums.
//A subarray is called balanced if the number of distinct even numbers in the subarray is equal to the number of
// distinct odd numbers.
//Return the length of the longest balanced subarray.

//Example 1:
//Input: nums = [2,5,4,3]
//Output: 4
//Explanation:
//The longest balanced subarray is [2, 5, 4, 3].
//It has 2 distinct even numbers [2, 4] and 2 distinct odd numbers [5, 3]. Thus, the answer is 4.

//Example 2:
//Input: nums = [3,2,2,5,4]
//Output: 5
//Explanation:
//The longest balanced subarray is [3, 2, 2, 5, 4].
//It has 2 distinct even numbers [2, 4] and 2 distinct odd numbers [3, 5]. Thus, the answer is 5.

//Example 3:
//Input: nums = [1,2,3,2]
//Output: 3
//Explanation:
//The longest balanced subarray is [2, 3, 2].
//It has 1 distinct even number [2] and 1 distinct odd number [3]. Thus, the answer is 3.

//Constraints:
//1 <= nums.length <= 1500
//1 <= nums[i] <= 105
