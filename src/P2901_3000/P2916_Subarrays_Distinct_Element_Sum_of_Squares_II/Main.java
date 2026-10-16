package P2901_3000.P2916_Subarrays_Distinct_Element_Sum_of_Squares_II;


public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 2, 1};
        System.out.println(solution.sumCounts(nums1)); // 15

        int[] nums2 = {2, 2};
        System.out.println(solution.sumCounts(nums2)); // 3

        int[] nums3 = {1, 2, 3};
        System.out.println(solution.sumCounts(nums3)); // 20
    }

    private static final long MOD = 1_000_000_007L;

    private int n;
    private long[] sum;
    private long[] sumSquares;
    private long[] lazy;

    public int sumCounts(int[] nums) {
        n = nums.length;

        sum = new long[4 * n];
        sumSquares = new long[4 * n];
        lazy = new long[4 * n];

        int[] last = new int[100001];
        java.util.Arrays.fill(last, -1);

        long answer = 0;

        for (int i = 0; i < n; i++) {
            int x = nums[i];

            update(1, 0, n - 1, last[x] + 1, i, 1);

            last[x] = i;
            answer = (answer + sumSquares[1]) % MOD;
        }

        return (int) answer;
    }

    private void apply(int node, int left, int right, long value) {
        long length = right - left + 1L;

        sumSquares[node] = (
                sumSquares[node]
                        + 2L * value % MOD * sum[node] % MOD
                        + length * value % MOD * value % MOD
        ) % MOD;

        sum[node] = (sum[node] + length * value) % MOD;
        lazy[node] = (lazy[node] + value) % MOD;
    }

    private void push(int node, int left, int right) {
        if (lazy[node] == 0 || left == right) {
            return;
        }

        int mid = left + (right - left) / 2;

        apply(node * 2, left, mid, lazy[node]);
        apply(node * 2 + 1, mid + 1, right, lazy[node]);

        lazy[node] = 0;
    }

    private void update(
            int node, int left, int right,
            int queryLeft, int queryRight, long value
    ) {
        if (queryLeft > right || queryRight < left) {
            return;
        }

        if (queryLeft <= left && right <= queryRight) {
            apply(node, left, right, value);
            return;
        }

        push(node, left, right);

        int mid = left + (right - left) / 2;

        update(node * 2, left, mid, queryLeft, queryRight, value);
        update(node * 2 + 1, mid + 1, right,
                queryLeft, queryRight, value);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        sumSquares[node] = (
                sumSquares[node * 2] + sumSquares[node * 2 + 1]
        ) % MOD;
    }

}

//Complexity:
// time - O(n log n)
// space - O(n + M)      M ~ 100001


//You are given a 0-indexed integer array nums.
//The distinct count of a subarray of nums is defined as:
//Let nums[i..j] be a subarray of nums consisting of all the indices from i to j such that 0 <= i <= j < nums.length.
// Then the number of distinct values in nums[i..j] is called the distinct count of nums[i..j].
//Return the sum of the squares of distinct counts of all subarrays of nums.
//Since the answer may be very large, return it modulo 109 + 7.
//A subarray is a contiguous non-empty sequence of elements within an array.

//Example 1:
//Input: nums = [1,2,1]
//Output: 15
//Explanation: Six possible subarrays are:
//[1]: 1 distinct value
//[2]: 1 distinct value
//[1]: 1 distinct value
//[1,2]: 2 distinct values
//[2,1]: 2 distinct values
//[1,2,1]: 2 distinct values
//The sum of the squares of the distinct counts in all subarrays is equal to 12 + 12 + 12 + 22 + 22 + 22 = 15.

//Example 2:
//Input: nums = [2,2]
//Output: 3
//Explanation: Three possible subarrays are:
//[2]: 1 distinct value
//[2]: 1 distinct value
//[2,2]: 1 distinct value
//The sum of the squares of the distinct counts in all subarrays is equal to 12 + 12 + 12 = 3.

//Constraints:
//1 <= nums.length <= 105
//1 <= nums[i] <= 105
