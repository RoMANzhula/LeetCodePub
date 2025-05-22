package P3301_3400.P3362_Zero_Array_Transformation_III;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.maxRemoval(new int[]{2, 0, 2}, new int[][]{{0, 2}, {0, 2}, {1, 1}})); // Output: 1
        System.out.println(solution.maxRemoval(new int[]{1, 1, 1, 1}, new int[][]{{1, 3}, {0, 2}, {1, 3}, {1, 2}})); // Output: 2
        System.out.println(solution.maxRemoval(new int[]{1, 2, 3, 4}, new int[][]{{0, 3}})); // Output: -1
    }

    public int maxRemoval(int[] nums, int[][] queries) {
        int queryIndex = 0;
        int n = nums.length;

        // sort queries by their start (li)
        Arrays.sort(queries, Comparator.comparingInt(a -> a[0]));

        // max-heap for available queries (by ri)
        PriorityQueue<Integer> available = new PriorityQueue<>(Collections.reverseOrder());
        // Mmn-heap for currently running queries (by ri)
        PriorityQueue<Integer> running = new PriorityQueue<>();

        for (int i = 0; i < n; ++i) {
            // add all queries that start at or before i to the available heap
            while (queryIndex < queries.length && queries[queryIndex][0] <= i) {
                available.offer(queries[queryIndex][1]);
                queryIndex++;
            }

            // remove expired queries from running heap
            while (!running.isEmpty() && running.peek() < i) {
                running.poll();
            }

            //add queries from available to running until nums[i] is satisfied
            while (nums[i] > running.size()) {
                if (available.isEmpty() || available.peek() < i) {
                    return -1;
                }
                running.offer(available.poll());
            }
        }

        // remaining available queries are removable
        return available.size();
    }

}

//Complexity:
// time - O(Q log Q + n)    Q = queries.length
// space - O(Q)


//You are given an integer array nums of length n and a 2D array queries where queries[i] = [li, ri].
//Each queries[i] represents the following action on nums:
//Decrement the value at each index in the range [li, ri] in nums by at most 1.
//The amount by which the value is decremented can be chosen independently for each index.
//A Zero Array is an array with all its elements equal to 0.
//Return the maximum number of elements that can be removed from queries, such that nums can still be converted to
// a zero array using the remaining queries. If it is not possible to convert nums to a zero array, return -1.

//Example 1:
//Input: nums = [2,0,2], queries = [[0,2],[0,2],[1,1]]
//Output: 1
//Explanation:
//After removing queries[2], nums can still be converted to a zero array.
//Using queries[0], decrement nums[0] and nums[2] by 1 and nums[1] by 0.
//Using queries[1], decrement nums[0] and nums[2] by 1 and nums[1] by 0.

//Example 2:
//Input: nums = [1,1,1,1], queries = [[1,3],[0,2],[1,3],[1,2]]
//Output: 2
//Explanation:
//We can remove queries[2] and queries[3].

//Example 3:
//Input: nums = [1,2,3,4], queries = [[0,3]]
//Output: -1
//Explanation:
//nums cannot be converted to a zero array even after using all the queries.

//Constraints:
//1 <= nums.length <= 105
//0 <= nums[i] <= 105
//1 <= queries.length <= 105
//queries[i].length == 2
//0 <= li <= ri < nums.length
