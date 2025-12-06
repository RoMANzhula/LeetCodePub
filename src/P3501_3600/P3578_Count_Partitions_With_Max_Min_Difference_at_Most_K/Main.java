package P3501_3600.P3578_Count_Partitions_With_Max_Min_Difference_at_Most_K;

import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {9, 4, 1, 3, 7};
        int k1 = 4;
        System.out.println(solution.countPartitions(nums1, k1)); // Expected: 6

        int[] nums2 = {3, 3, 4};
        int k2 = 0;
        System.out.println(solution.countPartitions(nums2, k2)); // Expected: 2
    }

    private static final int MOD = 1_000_000_007;

    public static int countPartitions(int[] nums, int k) {
        int n = nums.length;

        // dp[i] = number of ways to partition nums[0..i-1]
        long[] dp = new long[n + 1];
        long[] prefix = new long[n + 1];

        dp[0] = 1;
        prefix[0] = 1;

        Deque<Integer> maxDeque = new ArrayDeque<>();
        Deque<Integer> minDeque = new ArrayDeque<>();

        int left = 0;

        for (int right = 0; right < n; right++) {

            // maintain max deque (decreasing)
            while (!maxDeque.isEmpty() && nums[maxDeque.peekLast()] <= nums[right]) {
                maxDeque.pollLast();
            }
            maxDeque.addLast(right);

            // maintain min deque (increasing)
            while (!minDeque.isEmpty() && nums[minDeque.peekLast()] >= nums[right]) {
                minDeque.pollLast();
            }
            minDeque.addLast(right);

            // shrink window until condition satisfied
            while (!maxDeque.isEmpty() && !minDeque.isEmpty()
                    && nums[maxDeque.peekFirst()] - nums[minDeque.peekFirst()] > k) {

                if (maxDeque.peekFirst() == left) maxDeque.pollFirst();
                if (minDeque.peekFirst() == left) minDeque.pollFirst();

                left++;
            }

            long ways = prefix[right];
            if (left > 0) {
                ways = (ways - prefix[left - 1] + MOD) % MOD;
            }

            dp[right + 1] = ways;
            prefix[right + 1] = (prefix[right] + dp[right + 1]) % MOD;
        }

        return (int) dp[n];
    }

}

//Complexity:
// time and space - O(n)


//You are given an integer array nums and an integer k. Your task is to partition nums into one or more non-empty
// contiguous segments such that in each segment, the difference between its maximum and minimum elements is at most k.
//Return the total number of ways to partition nums under this condition.
//Since the answer may be too large, return it modulo 109 + 7.

//Example 1:
//Input: nums = [9,4,1,3,7], k = 4
//Output: 6
//Explanation:
//There are 6 valid partitions where the difference between the maximum and minimum elements in each segment is
// at most k = 4:
//[[9], [4], [1], [3], [7]]
//[[9], [4], [1], [3, 7]]
//[[9], [4], [1, 3], [7]]
//[[9], [4, 1], [3], [7]]
//[[9], [4, 1], [3, 7]]
//[[9], [4, 1, 3], [7]]

//Example 2:
//Input: nums = [3,3,4], k = 0
//Output: 2
//Explanation:
//There are 2 valid partitions that satisfy the given conditions:
//[[3], [3], [4]]
//[[3, 3], [4]]

//Constraints:
//2 <= nums.length <= 5 * 104
//1 <= nums[i] <= 109
//0 <= k <= 109
