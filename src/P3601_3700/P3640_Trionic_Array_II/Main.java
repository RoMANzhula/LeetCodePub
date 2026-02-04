package P3601_3700.P3640_Trionic_Array_II;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.maxSumTrionic(new int[]{0, -2, -1, -3, 0, 2, -1})); // -4
        System.out.println(solution.maxSumTrionic(new int[]{1, 4, 2, 7})); // 14
    }

    public long maxSumTrionic(int[] nums) {
        int n = nums.length;
        int i = 0;
        long ans = Long.MIN_VALUE;

        while (i < n) {
            int l = i;
            i++;

            // strictly increasing
            while (i < n && nums[i - 1] < nums[i]) {
                i++;
            }
            if (i == l + 1) {
                continue; // no increasing
            }

            int p = i - 1;

            // strictly decreasing
            long s = (long) nums[p - 1] + nums[p];
            while (i < n && nums[i - 1] > nums[i]) {
                s += nums[i];
                i++;
            }

            if (i == p + 1 || i == n || nums[i - 1] == nums[i]) {
                continue; // no valid decreasing or no room for final inc
            }

            int q = i - 1;

            // start final increasing
            s += nums[i];
            i++;

            long mx = 0, t = 0;

            // extend right increasing (take best suffix)
            while (i < n && nums[i - 1] < nums[i]) {
                t += nums[i];
                mx = Math.max(mx, t);
                i++;
            }
            s += mx;

            // extend left increasing (take best prefix)
            mx = 0;
            t = 0;
            for (int j = p - 2; j >= l; j--) {
                t += nums[j];
                mx = Math.max(mx, t);
            }
            s += mx;

            ans = Math.max(ans, s);

            // reset pointer
            i = q;
        }

        return ans;
    }

}

//Complexity:
// time - O(n)
// space - O(1)


//You are given an integer array nums of length n.
//A trionic subarray is a contiguous subarray nums[l...r] (with 0 <= l < r < n) for which there exist
// indices l < p < q < r such that:
//nums[l...p] is strictly increasing,
//nums[p...q] is strictly decreasing,
//nums[q...r] is strictly increasing.
//Return the maximum sum of any trionic subarray in nums.

//Example 1:
//Input: nums = [0,-2,-1,-3,0,2,-1]
//Output: -4
//Explanation:
//Pick l = 1, p = 2, q = 3, r = 5:
//nums[l...p] = nums[1...2] = [-2, -1] is strictly increasing (-2 < -1).
//nums[p...q] = nums[2...3] = [-1, -3] is strictly decreasing (-1 > -3)
//nums[q...r] = nums[3...5] = [-3, 0, 2] is strictly increasing (-3 < 0 < 2).
//Sum = (-2) + (-1) + (-3) + 0 + 2 = -4.

//Example 2:
//Input: nums = [1,4,2,7]
//Output: 14
//Explanation:
//Pick l = 0, p = 1, q = 2, r = 3:
//nums[l...p] = nums[0...1] = [1, 4] is strictly increasing (1 < 4).
//nums[p...q] = nums[1...2] = [4, 2] is strictly decreasing (4 > 2).
//nums[q...r] = nums[2...3] = [2, 7] is strictly increasing (2 < 7).
//Sum = 1 + 4 + 2 + 7 = 14.

//Constraints:
//4 <= n = nums.length <= 105
//-109 <= nums[i] <= 109
//It is guaranteed that at least one trionic subarray exists.
