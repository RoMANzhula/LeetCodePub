package P301_400.P396_Rotate_Function;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {4, 3, 2, 6};
        System.out.println(solution.maxRotateFunction(nums1)); // Output: 26

        int[] nums2 = {100};
        System.out.println(solution.maxRotateFunction(nums2)); // Output: 0
    }

    public int maxRotateFunction(int[] nums) {
        int n = nums.length;

        long sum = 0;
        long f0 = 0;

        // compute sum and F(0)
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            f0 += (long) i * nums[i];
        }

        long max = f0;
        long current = f0;

        // compute F(k) using recurrence
        for (int k = 1; k < n; k++) {
            current = current + sum - (long) n * nums[n - k];
            max = Math.max(max, current);
        }

        return (int) max;
    }

}

//Complexity:
// time - O(n)
// space - O(1)


//You are given an integer array nums of length n.
//Assume arrk to be an array obtained by rotating nums by k positions clock-wise. We define the rotation function F on
// nums as follow:
//F(k) = 0 * arrk[0] + 1 * arrk[1] + ... + (n - 1) * arrk[n - 1].
//Return the maximum value of F(0), F(1), ..., F(n-1).
//The test cases are generated so that the answer fits in a 32-bit integer.

//Example 1:
//Input: nums = [4,3,2,6]
//Output: 26
//Explanation:
//F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
//F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
//F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
//F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26
//So the maximum value of F(0), F(1), F(2), F(3) is F(3) = 26.

//Example 2:
//Input: nums = [100]
//Output: 0

//Constraints:
//n == nums.length
//1 <= n <= 105
//-100 <= nums[i] <= 100
