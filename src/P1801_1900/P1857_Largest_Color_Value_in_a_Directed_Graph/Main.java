package P1801_1900.P1857_Largest_Color_Value_in_a_Directed_Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String colors1 = "abaca";
        int[][] edges1 = {{0, 1}, {0, 2}, {2, 3}, {3, 4}};
        System.out.println(solution.largestPathValue(colors1, edges1)); // Output: 3

        String colors2 = "a";
        int[][] edges2 = {{0, 0}};
        System.out.println(solution.largestPathValue(colors2, edges2)); // Output: -1
    }

    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[n];

        //build the graph
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            inDegree[edge[1]]++;
        }

        // DP array: dp[i][c] = max count of color 'c' (0-25) at node i
        int[][] dp = new int[n][26];
        Queue<Integer> queue = new LinkedList<>();

        // start with nodes having zero in-degree
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        int visited = 0;
        int maxColorCount = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            visited++;

            int colorIndex = colors.charAt(node) - 'a';
            dp[node][colorIndex]++;
            maxColorCount = Math.max(maxColorCount, dp[node][colorIndex]);

            for (int neighbor : graph.get(node)) {
                // update neighbor's dp
                for (int c = 0; c < 26; c++) {
                    dp[neighbor][c] = Math.max(dp[neighbor][c], dp[node][c]);
                }

                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // if we didn’t visit all nodes, there is a cycle
        return visited == n ? maxColorCount : -1;
    }

}

//Explanation:
//Build the adjacency list and compute in-degrees.
//Use topological sort (BFS) to process nodes.
//Use a 2D array dp[node][color] to track the max count of each color at each node
//Update dp as we traverse the graph.
//If not all nodes are visited, it means there is a cycle → return -1

//Complexity:
// time: O(n + m + 26n) → n for nodes, m for edges, 26n for dp updates.
// dpace: O(n + m + 26n) → adjacency list + in-degree + dp array.


//There is a directed graph of n colored nodes and m edges. The nodes are numbered from 0 to n - 1.
//You are given a string colors where colors[i] is a lowercase English letter representing the color of the ith
// node in this graph (0-indexed). You are also given a 2D array edges where edges[j] = [aj, bj] indicates that
// there is a directed edge from node aj to node bj.
//A valid path in the graph is a sequence of nodes x1 -> x2 -> x3 -> ... -> xk such that there is a directed edge
// from xi to xi+1 for every 1 <= i < k. The color value of the path is the number of nodes that are colored the
// most frequently occurring color along that path.
//Return the largest color value of any valid path in the given graph, or -1 if the graph contains a cycle.

//Example 1:
//Input: colors = "abaca", edges = [[0,1],[0,2],[2,3],[3,4]]
//Output: 3
//Explanation: The path 0 -> 2 -> 3 -> 4 contains 3 nodes that are colored "a" (red in the above image).

//Example 2:
//Input: colors = "a", edges = [[0,0]]
//Output: -1
//Explanation: There is a cycle from 0 to 0.

//Constraints:
//n == colors.length
//m == edges.length
//1 <= n <= 105
//0 <= m <= 105
//colors consists of lowercase English letters.
//0 <= aj, bj < n
