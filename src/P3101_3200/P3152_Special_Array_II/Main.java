package P3101_3200.P3152_Special_Array_II;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {3, 4, 1, 2, 6};
        int[][] queries1 = {{0, 4}};
        System.out.println(Arrays.toString(solution.isArraySpecial(nums1, queries1))); // Output: [false]

        int[] nums2 = {4, 3, 1, 6};
        int[][] queries2 = {{0, 2}, {2, 3}};
        System.out.println(Arrays.toString(solution.isArraySpecial(nums2, queries2))); // Output: [false, true]
    }

    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] parity = new int[n - 1]; // Helper array to store parity differences

        // Step 1: Calculate parity differences
        for (int i = 0; i < n - 1; i++) {
            parity[i] = (nums[i] % 2 != nums[i + 1] % 2) ? 1 : 0;
        }

        // Step 2: Compute prefix sums for the parity array
        int[] prefixSum = new int[n];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + (i - 1 < parity.length ? parity[i - 1] : 0);
        }

        // Step 3: Process each query
        boolean[] result = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int from = queries[i][0];
            int to = queries[i][1];

            // Check if the subarray is special
            result[i] = (prefixSum[to] - prefixSum[from] == to - from);
        }

        return result;
    }
}

//Complexity Analysis
//Time Complexity
//Calculating Parity Differences:
// O(n): We iterate through the array nums once to compute the parity array.
//Computing Prefix Sum:
// O(n): We calculate the cumulative prefix sum for the parity array.
//Processing Queries:
// O(q): For each query, we calculate the difference between two prefix sum values, which takes O(1) per query.
//Total Time Complexity:
// O(n+q), where n is the length of nums and q is the number of queries. This is efficient given the constraints.
//
//Space Complexity
//Auxiliary Space:
//-O(n) for the parity array.
//-O(n) for the prefixSum array.
//-O(q) for the result array.
//Total Space Complexity:
//O(n+q).


//An array is considered special if every pair of its adjacent elements contains two numbers with different parity.
//You are given an array of integer nums and a 2D integer matrix queries, where for
// queries[i] = [fromi, toi] your task is to check that subarray nums[fromi..toi] is special or not.

//Return an array of booleans answer such that answer[i] is true if nums[fromi..toi] is special.
//
//Example 1:
//Input: nums = [3,4,1,2,6], queries = [[0,4]]
//Output: [false]
//Explanation:
//The subarray is [3,4,1,2,6]. 2 and 6 are both even.
//
//Example 2:
//Input: nums = [4,3,1,6], queries = [[0,2],[2,3]]
//Output: [false,true]
//Explanation:
//
//The subarray is [4,3,1]. 3 and 1 are both odd. So the answer to this query is false.
//The subarray is [1,6]. There is only one pair: (1,6) and it contains numbers with different parity. So the
// answer to this query is true.
//
//Constraints:
//1 <= nums.length <= 105
//1 <= nums[i] <= 105
//1 <= queries.length <= 105
//queries[i].length == 2
//0 <= queries[i][0] <= queries[i][1] <= nums.length - 1