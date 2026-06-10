package P3601_3700.P3691_Maximum_Total_Subarray_Value_II;

import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.maxTotalValue(new int[]{1, 3, 2}, 2)); // 4

        System.out.println(solution.maxTotalValue(new int[]{4, 2, 5, 1}, 3)); // 12
    }

    private int[] nums;
    private int[][] stMax;
    private int[][] stMin;
    private int[] log2;

    public long maxTotalValue(int[] nums, int k) {
        this.nums = nums;
        int n = nums.length;

        buildSparseTables(nums);

        PriorityQueue<Node> pq =
                new PriorityQueue<>((a, b) -> Long.compare(b.value, a.value));

        for (int l = 0; l < n; l++) {
            long val = rangeValue(l, n - 1);
            pq.offer(new Node(val, l, n - 1));
        }

        long answer = 0;

        while (k-- > 0) {
            Node cur = pq.poll();

            answer += cur.value;

            int l = cur.l;
            int r = cur.r;

            if (r > l) {
                long nextVal = rangeValue(l, r - 1);
                pq.offer(new Node(nextVal, l, r - 1));
            }
        }

        return answer;
    }

    private void buildSparseTables(int[] a) {
        int n = a.length;

        log2 = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            log2[i] = log2[i / 2] + 1;
        }

        int K = log2[n] + 1;

        stMax = new int[K][n];
        stMin = new int[K][n];

        for (int i = 0; i < n; i++) {
            stMax[0][i] = a[i];
            stMin[0][i] = a[i];
        }

        for (int j = 1; j < K; j++) {
            int len = 1 << j;
            int half = len >> 1;

            for (int i = 0; i + len <= n; i++) {
                stMax[j][i] =
                        Math.max(stMax[j - 1][i],
                                stMax[j - 1][i + half]);

                stMin[j][i] =
                        Math.min(stMin[j - 1][i],
                                stMin[j - 1][i + half]);
            }
        }
    }

    private long rangeValue(int l, int r) {
        int len = r - l + 1;
        int p = log2[len];

        int mx = Math.max(
                stMax[p][l],
                stMax[p][r - (1 << p) + 1]
        );

        int mn = Math.min(
                stMin[p][l],
                stMin[p][r - (1 << p) + 1]
        );

        return (long) mx - mn;
    }

    static class Node {
        long value;
        int l;
        int r;

        Node(long value, int l, int r) {
            this.value = value;
            this.l = l;
            this.r = r;
        }
    }

}

//Complexity:
// time - O((n + k) log n)
// space - O(n log n)


//You are given an integer array nums of length n and an integer k.
//You must select exactly k distinct subarrays nums[l..r] of nums. Subarrays may overlap, but the exact same
// subarray (same l and r) cannot be chosen more than once.
//The value of a subarray nums[l..r] is defined as: max(nums[l..r]) - min(nums[l..r]).
//The total value is the sum of the values of all chosen subarrays.
//Return the maximum possible total value you can achieve.

//Example 1:
//Input: nums = [1,3,2], k = 2
//Output: 4
//Explanation:
//One optimal approach is:
//Choose nums[0..1] = [1, 3]. The maximum is 3 and the minimum is 1, giving a value of 3 - 1 = 2.
//Choose nums[0..2] = [1, 3, 2]. The maximum is still 3 and the minimum is still 1, so the value is also 3 - 1 = 2.
//Adding these gives 2 + 2 = 4.

//Example 2:
//Input: nums = [4,2,5,1], k = 3
//Output: 12
//Explanation:
//One optimal approach is:
//Choose nums[0..3] = [4, 2, 5, 1]. The maximum is 5 and the minimum is 1, giving a value of 5 - 1 = 4.
//Choose nums[1..3] = [2, 5, 1]. The maximum is 5 and the minimum is 1, so the value is also 4.
//Choose nums[2..3] = [5, 1]. The maximum is 5 and the minimum is 1, so the value is again 4.
//Adding these gives 4 + 4 + 4 = 12.

//Constraints:
//1 <= n == nums.length <= 5 * 10​​​​​​​4
//0 <= nums[i] <= 109
//1 <= k <= min(105, n * (n + 1) / 2)
