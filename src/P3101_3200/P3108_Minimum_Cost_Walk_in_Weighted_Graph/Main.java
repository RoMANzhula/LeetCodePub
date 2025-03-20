package P3101_3200.P3108_Minimum_Cost_Walk_in_Weighted_Graph;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int n1 = 5;
        int[][] edges1 = {{0, 1, 7}, {1, 3, 7}, {1, 2, 1}};
        int[][] query1 = {{0, 3}, {3, 4}};
        System.out.println(Arrays.toString(solution.minimumCost(n1, edges1, query1))); // Output: [1, -1]

        int n2 = 3;
        int[][] edges2 = {{0, 2, 7}, {0, 1, 15}, {1, 2, 6}, {1, 2, 1}};
        int[][] query2 = {{1, 2}};
        System.out.println(Arrays.toString(solution.minimumCost(n2, edges2, query2))); // Output: [0]
    }

    public int[] minimumCost(int n, int[][] edges, int[][] query) {
        UnionFind uf = new UnionFind(n);
        int[] result = new int[query.length];

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            uf.unionByRank(u, v, w);
        }

        for (int i = 0; i < query.length; i++) {
            int u = query[i][0], v = query[i][1];
            result[i] = uf.getMinCost(u, v);
        }

        return result;
    }
}

class UnionFind {
    private int[] parent;
    private int[] rank;
    private int[] weight;
    private static final int MAX_WEIGHT = (1 << 17) - 1; // 2^17 - 1 > 10^5

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        weight = new int[n];
        Arrays.fill(weight, MAX_WEIGHT);
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    private int find(int u) {
        if (parent[u] != u) {
            parent[u] = find(parent[u]); // Path compression
        }
        return parent[u];
    }

    public void unionByRank(int u, int v, int w) {
        int rootU = find(u);
        int rootV = find(v);
        int newWeight = weight[rootU] & weight[rootV] & w;
        weight[rootU] = newWeight;
        weight[rootV] = newWeight;

        if (rootU == rootV) return;

        if (rank[rootU] < rank[rootV]) {
            parent[rootU] = rootV;
        } else if (rank[rootU] > rank[rootV]) {
            parent[rootV] = rootU;
        } else {
            parent[rootU] = rootV;
            rank[rootV]++;
        }
    }

    public int getMinCost(int u, int v) {
        if (u == v) return 0;
        return find(u) == find(v) ? weight[find(u)] : -1;
    }
}

//Explanation:
//Union-Find (Disjoint Set):
//-Uses path compression and union by rank for efficiency.
//-Stores the bitwise AND value for each component.
//Steps:
//-Initialize parent, rank, and weight.
//-Merge components using bitwise AND.
//-Answer each query by checking if nodes belong to the same component.
//Time Complexity:
//Union-Find operations: O(α(n)) (almost constant time)
//Processing edges: O(m log n) (m = number of edges)
//Querying: O(q log n) (q = number of queries)


//There is an undirected weighted graph with n vertices labeled from 0 to n - 1.
//You are given the integer n and an array edges, where edges[i] = [ui, vi, wi] indicates that there is an
// edge between vertices ui and vi with a weight of wi.
//A walk on a graph is a sequence of vertices and edges. The walk starts and ends with a vertex, and
// each edge connects the vertex that comes before it and the vertex that comes after it. It's important
// to note that a walk may visit the same edge or vertex more than once.

//The cost of a walk starting at node u and ending at node v is defined as the bitwise AND of the weights of the
// edges traversed during the walk. In other words, if the sequence of edge weights encountered during the
// walk is w0, w1, w2, ..., wk, then the cost is calculated as w0 & w1 & w2 & ... & wk, where & denotes the
// bitwise AND operator.
//You are also given a 2D array query, where query[i] = [si, ti]. For each query, you need to find the minimum cost
// of the walk starting at vertex si and ending at vertex ti. If there exists no such walk, the answer is -1.
//Return the array answer, where answer[i] denotes the minimum cost of a walk for query i.

//Example 1:
//Input: n = 5, edges = [[0,1,7],[1,3,7],[1,2,1]], query = [[0,3],[3,4]]
//Output: [1,-1]
//Explanation:
//To achieve the cost of 1 in the first query, we need to move on the following edges: 0->1 (weight 7),
// 1->2 (weight 1), 2->1 (weight 1), 1->3 (weight 7).
//In the second query, there is no walk between nodes 3 and 4, so the answer is -1.

//Example 2:
//Input: n = 3, edges = [[0,2,7],[0,1,15],[1,2,6],[1,2,1]], query = [[1,2]]
//Output: [0]
//Explanation:
//To achieve the cost of 0 in the first query, we need to move on the following edges: 1->2 (weight 1),
// 2->1 (weight 6), 1->2 (weight 1).

//Constraints:
//2 <= n <= 105
//0 <= edges.length <= 105
//edges[i].length == 3
//0 <= ui, vi <= n - 1
//ui != vi
//0 <= wi <= 105
//1 <= query.length <= 105
//query[i].length == 2
//0 <= si, ti <= n - 1
//si != ti
