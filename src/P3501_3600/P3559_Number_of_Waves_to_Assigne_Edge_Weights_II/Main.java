package P3501_3600.P3559_Number_of_Waves_to_Assigne_Edge_Weights_II;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] edges1 = {
                {1, 2}
        };

        int[][] queries1 = {
                {1, 1},
                {1, 2}
        };

        System.out.println(Arrays.toString(solution.assignEdgeWeights(edges1, queries1))); // [0, 1]

        int[][] edges2 = {
                {1, 2},
                {1, 3},
                {3, 4},
                {3, 5}
        };

        int[][] queries2 = {
                {1, 4},
                {3, 4},
                {2, 5}
        };

        System.out.println(Arrays.toString(solution.assignEdgeWeights(edges2, queries2))); // [2, 1, 4]
    }

    static final int MOD = 1_000_000_007;

    int LOG;
    List<Integer>[] graph;
    int[][] up;
    int[] depth;
    int[] pow2;

    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {

        int n = edges.length + 1;

        LOG = 1;
        while ((1 << LOG) <= n) LOG++;

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        up = new int[n + 1][LOG];
        depth = new int[n + 1];

        dfs(1, 1);

        pow2 = new int[n];
        pow2[0] = 1;
        for (int i = 1; i < n; i++) {
            pow2[i] = (int)((pow2[i - 1] * 2L) % MOD);
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int u = queries[i][0];
            int v = queries[i][1];

            int lca = lca(u, v);

            int dist = depth[u] + depth[v] - 2 * depth[lca];

            if (dist == 0) {
                ans[i] = 0;
            } else {
                ans[i] = pow2[dist - 1];
            }
        }

        return ans;
    }

    private void dfs(int node, int parent) {

        up[node][0] = parent;

        for (int j = 1; j < LOG; j++) {
            up[node][j] = up[up[node][j - 1]][j - 1];
        }

        for (int next : graph[node]) {
            if (next == parent) continue;
            depth[next] = depth[node] + 1;
            dfs(next, node);
        }
    }

    private int lca(int a, int b) {

        if (depth[a] < depth[b]) {
            int t = a;
            a = b;
            b = t;
        }

        int diff = depth[a] - depth[b];

        for (int j = LOG - 1; j >= 0; j--) {
            if (((diff >> j) & 1) == 1) {
                a = up[a][j];
            }
        }

        if (a == b) return a;

        for (int j = LOG - 1; j >= 0; j--) {
            if (up[a][j] != up[b][j]) {
                a = up[a][j];
                b = up[b][j];
            }
        }

        return up[a][0];
    }

}

//Complexity:
// time - O((n + q) log n)
// space - O(n log n)


//There is an undirected tree with n nodes labeled from 1 to n, rooted at node 1. The tree is represented by a 2D
// integer array edges of length n - 1, where edges[i] = [ui, vi] indicates that there is an edge between
// nodes ui and vi.
//Initially, all edges have a weight of 0. You must assign each edge a weight of either 1 or 2.
//The cost of a path between any two nodes u and v is the total weight of all edges in the path connecting them.
//You are given a 2D integer array queries. For each queries[i] = [ui, vi], determine the number of ways to assign
// weights to edges in the path such that the cost of the path between ui and vi is odd.
//Return an array answer, where answer[i] is the number of valid assignments for queries[i].
//Since the answer may be large, apply modulo 109 + 7 to each answer[i].
//Note: For each query, disregard all edges not in the path between node ui and vi.

//Example 1:
//Input: edges = [[1,2]], queries = [[1,1],[1,2]]
//Output: [0,1]
//Explanation:
//Query [1,1]: The path from Node 1 to itself consists of no edges, so the cost is 0. Thus, the number of valid
// assignments is 0.
//Query [1,2]: The path from Node 1 to Node 2 consists of one edge (1 → 2). Assigning weight 1 makes the cost odd,
// while 2 makes it even. Thus, the number of valid assignments is 1.

//Example 2:
//Input: edges = [[1,2],[1,3],[3,4],[3,5]], queries = [[1,4],[3,4],[2,5]]
//Output: [2,1,4]
//Explanation:
//Query [1,4]: The path from Node 1 to Node 4 consists of two edges (1 → 3 and 3 → 4). Assigning weights (1,2) or
// (2,1) results in an odd cost. Thus, the number of valid assignments is 2.
//Query [3,4]: The path from Node 3 to Node 4 consists of one edge (3 → 4). Assigning weight 1 makes the cost odd,
// while 2 makes it even. Thus, the number of valid assignments is 1.
//Query [2,5]: The path from Node 2 to Node 5 consists of three edges (2 → 1, 1 → 3, and 3 → 5). Assigning (1,2,2),
// (2,1,2), (2,2,1), or (1,1,1) makes the cost odd. Thus, the number of valid assignments is 4.

//Constraints:
//2 <= n <= 105
//edges.length == n - 1
//edges[i] == [ui, vi]
//1 <= queries.length <= 105
//queries[i] == [ui, vi]
//1 <= ui, vi <= n
//edges represents a valid tree.
