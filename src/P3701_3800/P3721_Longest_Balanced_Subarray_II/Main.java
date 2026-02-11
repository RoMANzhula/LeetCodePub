package P3701_3800.P3721_Longest_Balanced_Subarray_II;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.longestBalanced(new int[]{2, 5, 4, 3}));     // 4
        System.out.println(solution.longestBalanced(new int[]{3, 2, 2, 5, 4})); // 5
        System.out.println(solution.longestBalanced(new int[]{1, 2, 3, 2}));    // 3
    }


    public int longestBalanced(int[] nums) {
        Map<Integer, Queue<Integer>> occurrences = new HashMap<>();

        int len = 0;
        int[] prefixSum = new int[nums.length];
        prefixSum[0] = sgn(nums[0]);
        occurrences.computeIfAbsent(nums[0], k -> new LinkedList<>()).add(1);

        for (int i = 1; i < nums.length; i++) {
            prefixSum[i] = prefixSum[i - 1];
            Queue<Integer> occ = occurrences.computeIfAbsent(nums[i], k ->
                    new LinkedList<>()
            );
            if (occ.isEmpty()) {
                prefixSum[i] += sgn(nums[i]);
            }
            occ.add(i + 1);
        }

        SegmentTree seg = new SegmentTree(prefixSum);

        for (int i = 0; i < nums.length; i++) {
            len = Math.max(len, seg.findLast(i + len, 0) - i);

            int nextPos = nums.length + 1;
            occurrences.get(nums[i]).poll();
            if (!occurrences.get(nums[i]).isEmpty()) {
                nextPos = occurrences.get(nums[i]).peek();
            }

            seg.add(i + 1, nextPos - 1, -sgn(nums[i]));
        }

        return len;
    }

    private int sgn(int x) {
        return (x % 2) == 0 ? 1 : -1;
    }

}


class SegmentTree {
    private int[] min;
    private int[] max;
    private int[] lazy;
    private int n;

    public SegmentTree(int[] data) {
        n = data.length;
        min = new int[n * 4];
        max = new int[n * 4];
        lazy = new int[n * 4];
        build(data, 1, n, 1);
    }

    private void build(int[] data, int l, int r, int idx) {
        if (l == r) {
            min[idx] = max[idx] = data[l - 1];
            return;
        }

        int mid = (l + r) / 2;

        build(data, l, mid, idx * 2);
        build(data, mid + 1, r, idx * 2 + 1);
        pushUp(idx);
    }

    private void pushUp(int idx) {
        min[idx] = Math.min(min[idx * 2], min[idx * 2 + 1]);
        max[idx] = Math.max(max[idx * 2], max[idx * 2 + 1]);
    }

    private void apply(int idx, int val) {
        min[idx] += val;
        max[idx] += val;
        lazy[idx] += val;
    }

    private void pushDown(int idx) {
        if (lazy[idx] != 0) {
            apply(idx * 2, lazy[idx]);
            apply(idx * 2 + 1, lazy[idx]);
            lazy[idx] = 0;
        }
    }

    public void add(int l, int r, int val) {
        update(l, r, val, 1, n, 1);
    }

    private void update(int l, int r, int val, int cl, int cr, int idx) {
        if (l <= cl && cr <= r) {
            apply(idx, val);
            return;
        }

        pushDown(idx);

        int mid = (cl + cr) / 2;

        if (l <= mid) update(l, r, val, cl, mid, idx * 2);
        if (r > mid) update(l, r, val, mid + 1, cr, idx * 2 + 1);

        pushUp(idx);
    }

    public int findLast(int l, int val) {
        return find(l, n, val, 1, n, 1);
    }

    private int find(int l, int r, int val, int cl, int cr, int idx) {
        if (min[idx] > val || max[idx] < val) return -1;
        if (cl == cr) return cl;

        pushDown(idx);

        int mid = (cl + cr) / 2;

        if (r > mid) {
            int res = find(Math.max(l, mid + 1), r, val, mid + 1, cr, idx * 2 + 1);
            if (res != -1) return res;
        }

        if (l <= mid) {
            return find(l, Math.min(r, mid), val, cl, mid, idx * 2);
        }

        return -1;
    }
}

//Complexity:
// time - O(n log n)
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
//1 <= nums.length <= 105
//1 <= nums[i] <= 105
