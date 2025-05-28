package P3301_3400.P3372_Maximize_the_Number_of_Target_Nodes_After_Connecting_Trees_I;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] edges1 = {{0, 1}, {0, 2}, {2, 3}, {2, 4}};
        int[][] edges2 = {{0, 1}, {0, 2}, {0, 3}, {2, 7}, {1, 4}, {4, 5}, {4, 6}};
        int k1 = 2;

        System.out.println(Arrays.toString(solution.maxTargetNodes(edges1, edges2, k1))); // Output: [9,7,9,8,8]

        int[][] edges3 = {{0, 1}, {0, 2}, {0, 3}, {0, 4}};
        int[][] edges4 = {{0, 1}, {1, 2}, {2, 3}};
        int k2 = 1;

        System.out.println(Arrays.toString(solution.maxTargetNodes(edges3, edges4, k2))); // Output: [6,3,3,3,3]

    }

    public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
        int n = edges1.length + 1;
        int m = edges2.length + 1;
        List<List<Integer>> graph1 = buildGraph(edges1, n);
        List<List<Integer>> graph2 = buildGraph(edges2, m);
        int[] ans = new int[n];

        int maxReachableInGraph2 = 0;

        if (k > 0) {
            for (int i = 0; i < m; i++) {
                maxReachableInGraph2 = Math.max(maxReachableInGraph2, dfs(graph2, i, -1, k - 1));
            }
        }

        for (int i = 0; i < n; i++) {
            ans[i] = maxReachableInGraph2 + dfs(graph1, i, -1, k);
        }

        return ans;
    }

    // DFS to count how many nodes can be reached from node u with up to k steps
    private int dfs(List<List<Integer>> graph, int u, int prev, int k) {
        if (k == 0) return 1;

        int res = 0;

        for (int v : graph.get(u)) {
            if (v != prev) {
                res += dfs(graph, v, u, k - 1);
            }
        }

        return 1 + res;
    }

    // builds the adjacency list
    private List<List<Integer>> buildGraph(int[][] edges, int size) {
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        return graph;
    }

}

//Complexity:
// time - O((n + m) * min(max(n, m), b^k))
// space - O(n + m + k)


//There exist two undirected trees with n and m nodes, with distinct labels in ranges [0, n - 1] and
// [0, m - 1], respectively.
//You are given two 2D integer arrays edges1 and edges2 of lengths n - 1 and m - 1, respectively, where
// edges1[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the first tree and
// edges2[i] = [ui, vi] indicates that there is an edge between nodes ui and vi in the second tree. You are
// also given an integer k.
//Node u is target to node v if the number of edges on the path from u to v is less than or equal to k. Note that a
// node is always target to itself.
//Return an array of n integers answer, where answer[i] is the maximum possible number of nodes target to node i of
// the first tree if you have to connect one node from the first tree to another node in the second tree.
//Note that queries are independent from each other. That is, for every query you will remove the added edge before
// proceeding to the next query.

//Example 1:
//Input: edges1 = [[0,1],[0,2],[2,3],[2,4]], edges2 = [[0,1],[0,2],[0,3],[2,7],[1,4],[4,5],[4,6]], k = 2
//Output: [9,7,9,8,8]
//Explanation:
//For i = 0, connect node 0 from the first tree to node 0 from the second tree.
//For i = 1, connect node 1 from the first tree to node 0 from the second tree.
//For i = 2, connect node 2 from the first tree to node 4 from the second tree.
//For i = 3, connect node 3 from the first tree to node 4 from the second tree.
//For i = 4, connect node 4 from the first tree to node 4 from the second tree.

//Example 2:
//Input: edges1 = [[0,1],[0,2],[0,3],[0,4]], edges2 = [[0,1],[1,2],[2,3]], k = 1
//Output: [6,3,3,3,3]
//Explanation:
//For every i, connect node i of the first tree with any node of the second tree.

//Constraints:
//2 <= n, m <= 1000
//edges1.length == n - 1
//edges2.length == m - 1
//edges1[i].length == edges2[i].length == 2
//edges1[i] = [ai, bi]
//0 <= ai, bi < n
//edges2[i] = [ui, vi]
//0 <= ui, vi < m
//The input is generated such that edges1 and edges2 represent valid trees.
//0 <= k <= 1000
