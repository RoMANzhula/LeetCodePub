package P3501_3600.P3510_Minimum_Pair_Removal_to_Sort_Array_II;

import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {5, 2, 3, 1};
        int[] nums2 = {1, 2, 2};
        int[] nums3 = {10, -5, -6, 20};

        System.out.println(solution.minimumPairRemoval(nums1)); // 2
        System.out.println(solution.minimumPairRemoval(nums2)); // 0
        System.out.println(solution.minimumPairRemoval(nums3)); // 2
    }

    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;

        long[] arr = new long[n];
        for (int i = 0; i < n; i++) arr[i] = nums[i];

        int[] prev = new int[n];
        int[] next = new int[n];
        boolean[] alive = new boolean[n];

        for (int i = 0; i < n; i++) {
            prev[i] = i - 1;
            next[i] = i + 1;
            alive[i] = true;
        }
        next[n - 1] = -1;

        PriorityQueue<Pair> pq = new PriorityQueue<>(
                (a, b) -> a.sum == b.sum ? a.left - b.left : Long.compare(a.sum, b.sum)
        );

        for (int i = 0; i < n - 1; i++) {
            pq.offer(new Pair(arr[i] + arr[i + 1], i));
        }

        int ops = 0;

        while (true) {
            if (isNonDecreasing(arr, next, alive)) break;

            Pair cur;
            while (true) {
                cur = pq.poll();
                int i = cur.left;
                int j = next[i];

                if (j != -1 && alive[i] && alive[j] && arr[i] + arr[j] == cur.sum) {
                    break;
                }
            }

            int i = cur.left;
            int j = next[i];

            arr[i] = arr[i] + arr[j];
            alive[j] = false;

            next[i] = next[j];
            if (next[j] != -1) {
                prev[next[j]] = i;
            }

            if (prev[i] != -1) {
                pq.offer(new Pair(arr[prev[i]] + arr[i], prev[i]));
            }
            if (next[i] != -1) {
                pq.offer(new Pair(arr[i] + arr[next[i]], i));
            }

            ops++;
        }

        return ops;
    }

    private boolean isNonDecreasing(long[] arr, int[] next, boolean[] alive) {
        int i = 0;
        while (i != -1 && !alive[i]) i = next[i];

        while (i != -1 && next[i] != -1) {
            int j = next[i];
            if (arr[i] > arr[j]) return false;
            i = j;
        }
        return true;
    }

    class Pair {
        long sum;
        int left;

        Pair(long sum, int left) {
            this.sum = sum;
            this.left = left;
        }
    }

}

//Complexity:
// time - O(n log n)
// space - O(n)


//Given an array nums, you can perform the following operation any number of times:
//Select the adjacent pair with the minimum sum in nums. If multiple such pairs exist, choose the leftmost one.
//Replace the pair with their sum.
//Return the minimum number of operations needed to make the array non-decreasing.
//An array is said to be non-decreasing if each element is greater than or equal to its previous element (if it exists).

//Example 1:
//Input: nums = [5,2,3,1]
//Output: 2
//Explanation:
//The pair (3,1) has the minimum sum of 4. After replacement, nums = [5,2,4].
//The pair (2,4) has the minimum sum of 6. After replacement, nums = [5,6].
//The array nums became non-decreasing in two operations.

//Example 2:
//Input: nums = [1,2,2]
//Output: 0
//Explanation:
//The array nums is already sorted.

//Constraints:
//1 <= nums.length <= 105
//-109 <= nums[i] <= 109
