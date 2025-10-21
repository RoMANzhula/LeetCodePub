package P3301_3400.P3346_Maximum_Frequency_of_an_Element_After_Performing_Operations_I;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.maxFrequency(new int[]{1, 4, 5}, 1, 2)); // Output: 2
        System.out.println(solution.maxFrequency(new int[]{5, 11, 20, 20}, 5, 1)); // Output: 2
        System.out.println(solution.maxFrequency(new int[]{1,90}, 76, 1)); // Output: 1
        System.out.println(solution.maxFrequency(new int[]{51,17,70}, 10, 3)); // Output: 2
    }

    public int maxFrequency(int[] nums, int k, int numOperations) {
        int n = nums.length;
        if (n == 0) return 0;

        int minVal = Integer.MAX_VALUE;
        int maxVal = Integer.MIN_VALUE;
        for (int v : nums) {
            if (v < minVal) minVal = v;
            if (v > maxVal) maxVal = v;
        }

        int rangeLeft = minVal - k;
        int rangeRight = maxVal + k;
        int size = rangeRight - rangeLeft + 1; // number of integer points to consider

        int offset = -rangeLeft; // map value v -> index (v + offset) == (v - rangeLeft)

        // freq array for exact values
        int[] freq = new int[size];
        for (int v : nums) {
            freq[v + offset]++;
        }

        // difference array for coverage intervals; length = size + 1 so we can do R+1 safely
        int[] diff = new int[size + 1];
        for (int v : nums) {
            int L = v - k + offset;
            int R = v + k + offset;
            if (L < 0) L = 0;
            if (R >= size) R = size - 1;
            diff[L] += 1;
            diff[R + 1] -= 1;
        }

        // build cover[] by prefix sums
        int[] cover = new int[size];
        int running = 0;
        for (int i = 0; i < size; i++) {
            running += diff[i];
            cover[i] = running;
        }

        // evaluate best possible frequency
        int best = 0;
        for (int i = 0; i < size; i++) {
            int totalCovering = cover[i];
            int exact = freq[i];
            int possible = Math.min(totalCovering, exact + numOperations);
            if (possible > best) best = possible;
            if (best == n) return n; // can't do better than all elements
        }

        return best;
    }

}

//Complexity:
// time - O(n + R)
// space - O(R)
// R = (max(nums) - min(nums) + 2*k + 1


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
//Adding 0 to nums[1]. nums becomes [1, 4, 5].
//Adding -1 to nums[2]. nums becomes [1, 4, 4].

//Example 2:
//Input: nums = [5,11,20,20], k = 5, numOperations = 1
//Output: 2
//Explanation:
//We can achieve a maximum frequency of two by:
//Adding 0 to nums[1].

//Constraints:
//1 <= nums.length <= 105
//1 <= nums[i] <= 105
//0 <= k <= 105
//0 <= numOperations <= nums.length
