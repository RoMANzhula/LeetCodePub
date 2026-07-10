package P3501_3600.P3534_Path_Existence_Queries_in_a_Graph_II;

import java.util.Arrays;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int n = 5;
        int[] nums = {5, 3, 1, 9, 10};
        int maxDiff = 2;

        int[][] queries = {
                {0, 1},
                {0, 2},
                {2, 3},
                {4, 3}
        };

        int[] answer = solution.pathExistenceQueries(n, nums, maxDiff, queries);

        System.out.println(Arrays.toString(answer));
        // Expected: [1, 2, -1, 1]
    }

    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));

        int[] pos = new int[n];
        int[] value = new int[n];

        for (int i = 0; i < n; i++) {
            value[i] = arr[i][0];
            pos[arr[i][1]] = i;
        }

        int[] right = new int[n];
        int r = 0;

        for (int i = 0; i < n; i++) {
            while (r + 1 < n && value[r + 1] - value[i] <= maxDiff)
                r++;
            right[i] = r;
        }

        int[] left = new int[n];
        int l = 0;

        for (int i = 0; i < n; i++) {
            while (value[i] - value[l] > maxDiff)
                l++;
            left[i] = l;
        }

        int LOG = 18;

        int[][] jumpRight = new int[LOG][n];
        int[][] jumpLeft = new int[LOG][n];

        for (int i = 0; i < n; i++) {
            jumpRight[0][i] = right[i];
            jumpLeft[0][i] = left[i];
        }

        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < n; i++) {
                jumpRight[k][i] = jumpRight[k - 1][jumpRight[k - 1][i]];
                jumpLeft[k][i] = jumpLeft[k - 1][jumpLeft[k - 1][i]];
            }
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int a = pos[queries[i][0]];
            int b = pos[queries[i][1]];

            if (a == b) {
                ans[i] = 0;
                continue;
            }

            if (a < b) {
                if (right[a] < a + 1) {
                    ans[i] = -1;
                    continue;
                }

                int cur = a;
                int steps = 0;

                for (int k = LOG - 1; k >= 0; k--) {
                    if (jumpRight[k][cur] < b) {
                        cur = jumpRight[k][cur];
                        steps += 1 << k;
                    }
                }

                if (right[cur] >= b)
                    ans[i] = steps + 1;
                else
                    ans[i] = -1;
            } else {

                if (left[a] > a - 1) {
                    ans[i] = -1;
                    continue;
                }

                int cur = a;
                int steps = 0;

                for (int k = LOG - 1; k >= 0; k--) {
                    if (jumpLeft[k][cur] > b) {
                        cur = jumpLeft[k][cur];
                        steps += 1 << k;
                    }
                }

                if (left[cur] <= b)
                    ans[i] = steps + 1;
                else
                    ans[i] = -1;
            }
        }

        return ans;
    }

}

//Complexity:
// time - O((n + q) log n)
// space - O(n log n)


//You are given an integer n representing the number of nodes in a graph, labeled from 0 to n - 1.
//You are also given an integer array nums of length n and an integer maxDiff.
//An undirected edge exists between nodes i and j if the absolute difference between nums[i] and nums[j] is at most
// maxDiff (i.e., |nums[i] - nums[j]| <= maxDiff).
//You are also given a 2D integer array queries. For each queries[i] = [ui, vi], find the minimum distance between
// nodes ui and vi. If no path exists between the two nodes, return -1 for that query.
//Return an array answer, where answer[i] is the result of the ith query.
//Note: The edges between the nodes are unweighted.

//Example 1:
//Input: n = 5, nums = [1,8,3,4,2], maxDiff = 3, queries = [[0,3],[2,4]]
//Output: [1,1]
//Explanation:
//The resulting graph is:
//Query	Shortest Path	Minimum Distance
//[0, 3]	0 → 3	1
//[2, 4]	2 → 4	1
//Thus, the output is [1, 1].

//Example 2:
//Input: n = 5, nums = [5,3,1,9,10], maxDiff = 2, queries = [[0,1],[0,2],[2,3],[4,3]]
//Output: [1,2,-1,1]
//Explanation:
//The resulting graph is:
//Query	Shortest Path	Minimum Distance
//[0, 1]	0 → 1	1
//[0, 2]	0 → 1 → 2	2
//[2, 3]	None	-1
//[4, 3]	3 → 4	1
//Thus, the output is [1, 2, -1, 1].

//Example 3:
//Input: n = 3, nums = [3,6,1], maxDiff = 1, queries = [[0,0],[0,1],[1,2]]
//Output: [0,-1,-1]
//Explanation:
//There are no edges between any two nodes because:
//Nodes 0 and 1: |nums[0] - nums[1]| = |3 - 6| = 3 > 1
//Nodes 0 and 2: |nums[0] - nums[2]| = |3 - 1| = 2 > 1
//Nodes 1 and 2: |nums[1] - nums[2]| = |6 - 1| = 5 > 1
//Thus, no node can reach any other node, and the output is [0, -1, -1].

//Constraints:
//1 <= n == nums.length <= 105
//0 <= nums[i] <= 105
//0 <= maxDiff <= 105
//1 <= queries.length <= 105
//queries[i] == [ui, vi]
//0 <= ui, vi < n
