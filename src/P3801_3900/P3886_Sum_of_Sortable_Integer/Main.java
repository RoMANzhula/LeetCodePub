package P3801_3900.P3886_Sum_of_Sortable_Integer;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.sortableIntegers(new int[]{3, 1, 2}));
        System.out.println(solution.sortableIntegers(new int[]{7, 6, 5}));
        System.out.println(solution.sortableIntegers(new int[]{5, 8}));
    }

    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int answer = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) {
                continue;
            }

            boolean valid = true;

            for (int start = 0; start < n; start += k) {
                if (!isRotation(nums, sorted, start, k)) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                answer += k;
            }
        }

        return answer;
    }

    private static boolean isRotation(
            int[] nums, int[] sorted, int start, int k) {

        int[] prefix = new int[k];

        for (int i = 1, j = 0; i < k; i++) {
            while (j > 0 && sorted[start + i] != sorted[start + j]) {
                j = prefix[j - 1];
            }

            if (sorted[start + i] == sorted[start + j]) {
                j++;
            }

            prefix[i] = j;
        }

        for (int i = 0, j = 0; i < 2 * k - 1; i++) {
            int value = nums[start + i % k];

            while (j > 0 && value != sorted[start + j]) {
                j = prefix[j - 1];
            }

            if (value == sorted[start + j]) {
                j++;
            }

            if (j == k) {
                return true;
            }
        }

        return false;
    }

}


//You are given an integer array nums of length n.
//An integer k is called sortable if k divides n and you can sort nums in non-decreasing order by sequentially
// performing the following operations:
//Partition nums into consecutive subarrays of length k.
//Cyclically rotate each subarray independently any number of times to the left or to the right.
//Return an integer denoting the sum of all possible sortable integers k.

//Example 1:
//Input: nums = [3,1,2]
//Output: 3
//Explanation:
//For n = 3, possible divisors are 1 and 3.
//For k = 1: each subarray has one element. No rotation can sort the array.
//For k = 3: the single subarray [3, 1, 2] can be rotated once to produce [1, 2, 3], which is sorted.
//Only k = 3 is sortable. Hence, the answer is 3.

//Example 2:
//Input: nums = [7,6,5]
//Output: 0
//Explanation:
//For n = 3, possible divisors are 1 and 3.
//For k = 1: each subarray has one element. No rotation can sort the array.
//For k = 3: the single subarray [7, 6, 5] cannot be rotated into non-decreasing order.
//No k is sortable. Hence, the answer is 0.

//Example 3:
//Input: nums = [5,8]
//Output: 3
//Explanation:
//For n = 2, possible divisors are 1 and 2.
//Since [5, 8] is already sorted, every divisor is sortable. Hence, the answer is 1 + 2 = 3.

//Constraints:
//1 <= n == nums.length <= 105
//1 <= nums[i] <= 105