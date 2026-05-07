package P3601_3700.P36360_Jump_Game_IX;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {2, 1, 3};
        System.out.println(Arrays.toString(solution.maxValue(nums1)));
        // [2, 2, 3]

        int[] nums2 = {2, 3, 1};
        System.out.println(Arrays.toString(solution.maxValue(nums2)));
        // [3, 3, 3]

        int[] nums3 = {1, 2, 1, 2};
        System.out.println(Arrays.toString(solution.maxValue(nums3)));
        // [1, 2, 2, 2]
    }

    public int[] maxValue(int[] nums) {
        int n = nums.length;

        int[] suffixMin = new int[n];
        suffixMin[n - 1] = nums[n - 1];

        // build suffix minimum array
        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(nums[i], suffixMin[i + 1]);
        }

        int[] ans = new int[n];

        int start = 0;
        int currentMax = nums[0];

        for (int i = 0; i < n - 1; i++) {
            currentMax = Math.max(currentMax, nums[i]);

            // if there is NO inversion crossing this border, we can finish the current component
            if (currentMax <= suffixMin[i + 1]) {

                int componentMax = currentMax;

                for (int j = start; j <= i; j++) {
                    ans[j] = componentMax;
                }

                start = i + 1;

                if (start < n) {
                    currentMax = nums[start];
                }
            }
        }

        // Last component
        currentMax = nums[start];
        for (int i = start; i < n; i++) {
            currentMax = Math.max(currentMax, nums[i]);
        }

        for (int i = start; i < n; i++) {
            ans[i] = currentMax;
        }

        return ans;
    }

}

//Complexity:
// time and space - O(n)


//You are given an integer array nums.
//From any index i, you can jump to another index j under the following rules:
//Jump to index j where j > i is allowed only if nums[j] < nums[i].
//Jump to index j where j < i is allowed only if nums[j] > nums[i].
//For each index i, find the maximum value in nums that can be reached by following any sequence of valid jumps
// starting at i.
//Return an array ans where ans[i] is the maximum value reachable starting from index i.

//Example 1:
//Input: nums = [2,1,3]
//Output: [2,2,3]
//Explanation:
//For i = 0: No jump increases the value.
//For i = 1: Jump to j = 0 as nums[j] = 2 is greater than nums[i].
//For i = 2: Since nums[2] = 3 is the maximum value in nums, no jump increases the value.
//Thus, ans = [2, 2, 3].

//Example 2:
//Input: nums = [2,3,1]
//Output: [3,3,3]
//Explanation:
//For i = 0: Jump forward to j = 2 as nums[j] = 1 is less than nums[i] = 2, then from i = 2 jump to j = 1 as
// nums[j] = 3 is greater than nums[2].
//For i = 1: Since nums[1] = 3 is the maximum value in nums, no jump increases the value.
//For i = 2: Jump to j = 1 as nums[j] = 3 is greater than nums[2] = 1.
//Thus, ans = [3, 3, 3].

//Constraints:
//1 <= nums.length <= 105
//1 <= nums[i] <= 109
