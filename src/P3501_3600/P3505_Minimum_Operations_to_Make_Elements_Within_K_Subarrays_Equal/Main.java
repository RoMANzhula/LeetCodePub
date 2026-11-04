package P3501_3600.P3505_Minimum_Operations_to_Make_Elements_Within_K_Subarrays_Equal;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {5, -2, 1, 3, 7, 3, 6, 4, -1};
        System.out.println(solution.minOperations(nums1, 3, 2)); // 0

        int[] nums2 = {9, -2, -2, -2, 1, 5};
        System.out.println(solution.minOperations(nums2, 2, 2)); // 3
    }

    private static class DualHeap {

        private final PriorityQueue<Long> small =
                new PriorityQueue<>(Collections.reverseOrder());

        private final PriorityQueue<Long> large =
                new PriorityQueue<>();

        private final Map<Long, Integer> delayed = new HashMap<>();

        private int smallSize;
        private int largeSize;

        private long smallSum;
        private long largeSum;

        private void prune(PriorityQueue<Long> heap) {
            while (!heap.isEmpty()) {
                long value = heap.peek();
                int count = delayed.getOrDefault(value, 0);

                if (count == 0) {
                    break;
                }

                heap.poll();

                if (count == 1) {
                    delayed.remove(value);
                } else {
                    delayed.put(value, count - 1);
                }
            }
        }

        private void balance() {
            if (smallSize > largeSize + 1) {
                prune(small);

                long value = small.poll();

                smallSize--;
                smallSum -= value;

                large.offer(value);

                largeSize++;
                largeSum += value;

                prune(small);
            } else if (smallSize < largeSize) {
                prune(large);

                long value = large.poll();

                largeSize--;
                largeSum -= value;

                small.offer(value);

                smallSize++;
                smallSum += value;

                prune(large);
            }
        }

        void add(long value) {
            prune(small);

            if (small.isEmpty() || value <= small.peek()) {
                small.offer(value);
                smallSize++;
                smallSum += value;
            } else {
                large.offer(value);
                largeSize++;
                largeSum += value;
            }

            balance();
        }

        void remove(long value) {
            prune(small);

            delayed.merge(value, 1, Integer::sum);

            if (value <= small.peek()) {
                smallSize--;
                smallSum -= value;

                if (value == small.peek()) {
                    prune(small);
                }
            } else {
                largeSize--;
                largeSum -= value;

                if (!large.isEmpty() && value == large.peek()) {
                    prune(large);
                }
            }

            balance();
        }

        long getCost() {
            prune(small);
            prune(large);

            long median = small.peek();

            return median * smallSize - smallSum
                    + largeSum - median * largeSize;
        }
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        long[] costs = new long[n - x + 1];

        DualHeap heap = new DualHeap();

        for (int i = 0; i < n; i++) {

            heap.add(nums[i]);

            if (i >= x) {
                heap.remove(nums[i - x]);
            }

            if (i >= x - 1) {
                costs[i - x + 1] = heap.getCost();
            }
        }

        long INF = Long.MAX_VALUE / 4;

        long[] prev = new long[n + 1];
        long[] curr = new long[n + 1];

        for (int j = 1; j <= k; j++) {

            Arrays.fill(curr, INF);

            for (int i = 1; i <= n; i++) {

                curr[i] = curr[i - 1];

                if (i >= x) {

                    int start = i - x;

                    if (prev[start] != INF) {
                        curr[i] = Math.min(
                                curr[i],
                                prev[start] + costs[start]
                        );
                    }
                }
            }

            long[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[n];
    }

}


//You are given an integer array nums and two integers, x and k. You can perform the following operation any number of
// times (including zero):
//Increase or decrease any element of nums by 1.
//Return the minimum number of operations needed to have at least k non-overlapping subarrays of size exactly x in
// nums, where all elements within each subarray are equal.

//Example 1:
//Input: nums = [5,-2,1,3,7,3,6,4,-1], x = 3, k = 2
//Output: 8
//Explanation:
//Use 3 operations to add 3 to nums[1] and use 2 operations to subtract 2 from nums[3]. The resulting
// array is [5, 1, 1, 1, 7, 3, 6, 4, -1].
//Use 1 operation to add 1 to nums[5] and use 2 operations to subtract 2 from nums[6]. The resulting
// array is [5, 1, 1, 1, 7, 4, 4, 4, -1].
//Now, all elements within each subarray [1, 1, 1] (from indices 1 to 3) and [4, 4, 4] (from indices 5 to 7) are equal.
// Since 8 total operations were used, 8 is the output.

//Example 2:
//Input: nums = [9,-2,-2,-2,1,5], x = 2, k = 2
//Output: 3
//Explanation:
//Use 3 operations to subtract 3 from nums[4]. The resulting array is [9, -2, -2, -2, -2, 5].
//Now, all elements within each subarray [-2, -2] (from indices 1 to 2) and [-2, -2] (from indices 3 to 4) are equal.
// Since 3 operations were used, 3 is the output.

//Constraints:
//2 <= nums.length <= 105
//-106 <= nums[i] <= 106
//2 <= x <= nums.length
//1 <= k <= 15
//2 <= k * x <= nums.length
