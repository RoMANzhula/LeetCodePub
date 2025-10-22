package P3301_3400.P3347_Maximum_Frequency_of_an_Element_After_Performing_Operations_II;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.maxFrequency(new int[]{1, 4, 5}, 1, 2)); // Output: 2
        System.out.println(solution.maxFrequency(new int[]{5, 11, 20, 20}, 5, 1)); // Output: 2
        System.out.println(solution.maxFrequency(new int[]{94, 10}, 51, 1)); // Output 1
    }

    public int maxFrequency(int[] nums, int k, int numOperations) {
        int n = nums.length;
        // build intervals [nums[i]-k, nums[i]+k] as long to avoid overflow
        long[] L = new long[n];
        long[] R = new long[n];
        for (int i = 0; i < n; i++) {
            L[i] = (long) nums[i] - (long) k;
            R[i] = (long) nums[i] + (long) k;
        }
        Arrays.sort(L);
        Arrays.sort(R);

        int i = 0, j = 0, curr = 0, maxCover = 0;
        while (i < n) {
            if (j == n || L[i] <= R[j]) {
                curr++;
                maxCover = Math.max(maxCover, curr);
                i++;
            } else {
                curr--;
                j++;
            }
        }

        // best when equal(T) == 0
        int best = Math.min(maxCover, numOperations);

        // prepare sorted copy of nums to iterate distinct values and counts
        Arrays.sort(nums);
        int idx = 0;
        while (idx < n) {
            int val = nums[idx];
            int start = idx;
            while (idx < n && nums[idx] == val) idx++;
            int equalCount = idx - start;

            long v = val;
            // cover = number of intervals with L <= v and R >= v
            int countL = upperBound(L, v);      // number of L <= v
            int countR_lt_v = lowerBound(R, v); // number of R < v
            int cover = countL - countR_lt_v;

            int candidate = Math.min(cover, equalCount + numOperations);
            best = Math.max(best, candidate);
        }

        return best;
    }

    // first index > x
    private int upperBound(long[] arr, long x) {
        int lo = 0, hi = arr.length; // hi is exclusive

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] <= x) lo = mid + 1;
            else hi = mid;
        }

        return lo;
    }

    // first index >= x
    private int lowerBound(long[] arr, long x) {
        int lo = 0, hi = arr.length; // hi is exclusive

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] < x) lo = mid + 1;
            else hi = mid;
        }

        return lo;
    }

}

//Complexity:
// time - O(n log n)
// space - O(n)


//You are given an integer array nums and two integers k and numOperations.
//You must perform an operation numOperations times on nums, where in each operation you:
//Select an index i that was not selected in any previous operations.
//Add an integer in the range [-k, k] to nums[i].
//Return the maximum possible frequency of any element in nums after performing the operations.

//Example 1:
//Input: nums = [1,4,5], k = 1, numOperations = 2
//Output: 2
//Explanation:
//We can achieve a maximum frequency of two by:
//Adding 0 to nums[1], after which nums becomes [1, 4, 5].
//Adding -1 to nums[2], after which nums becomes [1, 4, 4].

//Example 2:
//Input: nums = [5,11,20,20], k = 5, numOperations = 1
//Output: 2
//Explanation:
//We can achieve a maximum frequency of two by:
//Adding 0 to nums[1].

//Constraints:
//1 <= nums.length <= 105
//1 <= nums[i] <= 109
//0 <= k <= 109
//0 <= numOperations <= nums.length
