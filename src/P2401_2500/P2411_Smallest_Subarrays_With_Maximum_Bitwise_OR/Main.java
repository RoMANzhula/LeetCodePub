package P2401_2500.P2411_Smallest_Subarrays_With_Maximum_Bitwise_OR;

import java.util.Arrays;


public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(Arrays.toString(solution.smallestSubarrays(new int[]{1, 0, 2, 1, 3}))); // [3,3,2,2,1]
        System.out.println(Arrays.toString(solution.smallestSubarrays(new int[]{1, 2})));          // [2,1]
    }

    public int[] smallestSubarrays(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int[] lastSeen = new int[32]; // one for each bit position

        // initialize all lastSeen positions to -1
        Arrays.fill(lastSeen, -1);

        for (int i = n - 1; i >= 0; i--) {
            // update lastSeen for bits set in nums[i]
            for (int b = 0; b < 32; b++) {
                if ((nums[i] & (1 << b)) != 0) {
                    lastSeen[b] = i;
                }
            }

            // find the farthest index we must go to collect all bits
            int farthest = i;
            for (int b = 0; b < 32; b++) {
                if (lastSeen[b] != -1) {
                    farthest = Math.max(farthest, lastSeen[b]);
                }
            }

            res[i] = farthest - i + 1;
        }

        return res;
    }

}

//complexity:
// time - O(n * 32)
// space - O(32)


//You are given a 0-indexed array nums of length n, consisting of non-negative integers. For each
// index i from 0 to n - 1, you must determine the size of the minimum sized non-empty subarray of
// nums starting at i (inclusive) that has the maximum possible bitwise OR.
//In other words, let Bij be the bitwise OR of the subarray nums[i...j]. You need to find the smallest subarray
// starting at i, such that bitwise OR of this subarray is equal to max(Bik) where i <= k <= n - 1.
//The bitwise OR of an array is the bitwise OR of all the numbers in it.
//Return an integer array answer of size n where answer[i] is the length of the minimum sized subarray starting at
// i with maximum bitwise OR.
//A subarray is a contiguous non-empty sequence of elements within an array.

//Example 1:
//Input: nums = [1,0,2,1,3]
//Output: [3,3,2,2,1]
//Explanation:
//The maximum possible bitwise OR starting at any index is 3.
//- Starting at index 0, the shortest subarray that yields it is [1,0,2].
//- Starting at index 1, the shortest subarray that yields the maximum bitwise OR is [0,2,1].
//- Starting at index 2, the shortest subarray that yields the maximum bitwise OR is [2,1].
//- Starting at index 3, the shortest subarray that yields the maximum bitwise OR is [1,3].
//- Starting at index 4, the shortest subarray that yields the maximum bitwise OR is [3].
//Therefore, we return [3,3,2,2,1].

//Example 2:
//Input: nums = [1,2]
//Output: [2,1]
//Explanation:
//Starting at index 0, the shortest subarray that yields the maximum bitwise OR is of length 2.
//Starting at index 1, the shortest subarray that yields the maximum bitwise OR is of length 1.
//Therefore, we return [2,1].

//Constraints:
//n == nums.length
//1 <= n <= 105
//0 <= nums[i] <= 109
