package P3601_3700.P3634_Minimum_Removals_to_Balance_Array;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {2, 1, 5};
        int k1 = 2;
        System.out.println(solution.minRemoval(nums1, k1)); // 1

        int[] nums2 = {1, 6, 2, 9};
        int k2 = 3;
        System.out.println(solution.minRemoval(nums2, k2)); // 2

        int[] nums3 = {4, 6};
        int k3 = 2;
        System.out.println(solution.minRemoval(nums3, k3)); // 0
    }

    public int minRemoval(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);

        int left = 0;
        int maxWindow = 1;

        for (int right = 0; right < n; right++) {
            while ((long) nums[right] > (long) nums[left] * k) {
                left++;
            }
            maxWindow = Math.max(maxWindow, right - left + 1);
        }

        return n - maxWindow;
    }

}

//Complexity:
// time - O(n log n)
// space - O(1)


//You are given an integer array nums and an integer k.
//An array is considered balanced if the value of its maximum element is at most k times the minimum element.
//You may remove any number of elements from nums without making it empty.
//Return the minimum number of elements to remove so that the remaining array is balanced.
//Note: An array of size 1 is considered balanced as its maximum and minimum are equal, and the condition always
// holds true.

//Example 1:
//Input: nums = [2,1,5], k = 2
//Output: 1
//Explanation:
//Remove nums[2] = 5 to get nums = [2, 1].
//Now max = 2, min = 1 and max <= min * k as 2 <= 1 * 2. Thus, the answer is 1.

//Example 2:
//Input: nums = [1,6,2,9], k = 3
//Output: 2
//Explanation:
//Remove nums[0] = 1 and nums[3] = 9 to get nums = [6, 2].
//Now max = 6, min = 2 and max <= min * k as 6 <= 2 * 3. Thus, the answer is 2.

//Example 3:
//Input: nums = [4,6], k = 2
//Output: 0
//Explanation:
//Since nums is already balanced as 6 <= 4 * 2, no elements need to be removed.

//Constraints:
//1 <= nums.length <= 105
//1 <= nums[i] <= 109
//1 <= k <= 105
