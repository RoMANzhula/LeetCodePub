package P201_300.P209_Minimum_Size_Subarray_Sum;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {2, 3, 1, 2, 4, 3};
        int target1 = 7;
        System.out.println(solution.minSubArrayLen(target1, nums1)); // Output: 2

        int[] nums2 = {1, 4, 4};
        int target2 = 4;
        System.out.println(solution.minSubArrayLen(target2, nums2)); // Output: 1

        int[] nums3 = {1, 1, 1, 1, 1, 1, 1, 1};
        int target3 = 11;
        System.out.println(solution.minSubArrayLen(target3, nums3)); // Output: 0
    }

    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int minLength = Integer.MAX_VALUE;
        int left = 0;
        int sum = 0;

        for (int right = 0; right < n; right++) {
            sum += nums[right];

            while (sum >= target) {
                minLength = Math.min(minLength, right - left + 1);
                sum -= nums[left];
                left++;
            }
        }

        return (minLength == Integer.MAX_VALUE) ? 0 : minLength;
    }

}

//Explanation:
//We use two pointers (left, right) to create a window.
//Expand the window by moving right.
//Once the sum is ≥ target, we try to shrink the window by moving left to get the minimal length.
//Return the smallest length found, or 0 if none.
//Complexity:
//Time: O(n)
//Space: O(1)


//Given an array of positive integers nums and a positive integer target, return the minimal length of a
// subarray whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.

//Example 1:
//Input: target = 7, nums = [2,3,1,2,4,3]
//Output: 2
//Explanation: The subarray [4,3] has the minimal length under the problem constraint.

//Example 2:
//Input: target = 4, nums = [1,4,4]
//Output: 1

//Example 3:
//Input: target = 11, nums = [1,1,1,1,1,1,1,1]
//Output: 0

//Constraints:
//1 <= target <= 109
//1 <= nums.length <= 105
//1 <= nums[i] <= 104
