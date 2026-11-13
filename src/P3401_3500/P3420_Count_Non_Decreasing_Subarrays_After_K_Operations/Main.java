package P3401_3500.P3420_Count_Non_Decreasing_Subarrays_After_K_Operations;

public class Main {

    public static void main(String[] args) {
        int[] nums1 = {6, 3, 1, 2, 4, 4};
        int k1 = 7;
        System.out.println(new Main().countNonDecreasingSubarrays(nums1, k1)); // 17

        int[] nums2 = {6, 3, 1, 3, 6};
        int k2 = 4;
        System.out.println(new Main().countNonDecreasingSubarrays(nums2, k2)); // 12
    }

    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        int log = 1;

        while ((1 << log) <= n) {
            log++;
        }

        long[] prefix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        int[] next = new int[n];
        int[] stack = new int[n];
        int top = -1;

        for (int i = n - 1; i >= 0; i--) {
            while (top >= 0 && nums[stack[top]] <= nums[i]) {
                top--;
            }

            next[i] = top >= 0 ? stack[top] : n;
            stack[++top] = i;
        }

        int[][] up = new int[log][n + 1];
        long[][] cost = new long[log][n + 1];

        for (int i = 0; i < n; i++) {
            up[0][i] = next[i];

            cost[0][i] =
                    (long) nums[i] * (next[i] - i)
                            - (prefix[next[i]] - prefix[i]);
        }

        for (int i = 0; i < log; i++) {
            up[i][n] = n;
        }

        for (int p = 1; p < log; p++) {
            for (int i = 0; i < n; i++) {
                int mid = up[p - 1][i];

                up[p][i] = up[p - 1][mid];

                cost[p][i] =
                        cost[p - 1][i] + cost[p - 1][mid];
            }
        }

        long answer = 0;

        for (int left = 0; left < n; left++) {
            int low = left;
            int high = n - 1;
            int best = left - 1;

            while (low <= high) {
                int mid = low + (high - low) / 2;

                if (getCost(left, mid, nums, prefix, up, cost, log) <= k) {
                    best = mid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            answer += best - left + 1;
        }

        return answer;
    }

    private long getCost(
            int left,
            int right,
            int[] nums,
            long[] prefix,
            int[][] up,
            long[][] cost,
            int log
    ) {
        long result = 0;
        int pos = left;

        for (int p = log - 1; p >= 0; p--) {
            int nextPos = up[p][pos];

            if (nextPos <= right + 1) {
                result += cost[p][pos];
                pos = nextPos;
            }
        }

        if (pos <= right) {
            result += (long) nums[pos] * (right - pos + 1)
                    - (prefix[right + 1] - prefix[pos]);
        }

        return result;
    }

}

//Complexity:
// time - O(n log^2 n)
// space - O(n log n)


//You are given an array nums of n integers and an integer k.
//For each subarray of nums, you can apply up to k operations on it. In each operation, you increment any element of
// the subarray by 1.
//Note that each subarray is considered independently, meaning changes made to one subarray do not persist to another.
//Return the number of subarrays that you can make non-decreasing after performing at
// most k operations.
//An array is said to be non-decreasing if each element is greater than or equal to its previous element, if it exists.

//Example 1:
//Input: nums = [6,3,1,2,4,4], k = 7
//Output: 17
//Explanation:
//Out of all 21 possible subarrays of nums, only the subarrays [6, 3, 1], [6, 3, 1, 2], [6, 3, 1, 2, 4] and
// [6, 3, 1, 2, 4, 4] cannot be made non-decreasing after applying up to k = 7 operations. Thus, the number of
// non-decreasing subarrays is 21 - 4 = 17.

//Example 2:
//Input: nums = [6,3,1,3,6], k = 4
//Output: 12
//Explanation:
//The subarray [3, 1, 3, 6] along with all subarrays of nums with three or fewer elements, except [6, 3, 1], can be
// made non-decreasing after k operations. There are 5 subarrays of a single element, 4 subarrays of two elements, and
// 2 subarrays of three elements except [6, 3, 1], so there are 1 + 5 + 4 + 2 = 12 subarrays that can be made
// non-decreasing.

//Constraints:
//1 <= nums.length <= 105
//1 <= nums[i] <= 109
//1 <= k <= 109
