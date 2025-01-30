package P2401_2500.P2493_Divide_Nodes_Into_the_Maximum_Number_of_Groups;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int n1 = 6;
        int[][] edges1 = {{1, 2}, {1, 4}, {1, 5}, {2, 6}, {2, 3}, {4, 6}};
        System.out.println(solution.magnificentSets(n1, edges1));  // Output: 4

        int n2 = 3;
        int[][] edges2 = {{1, 2}, {2, 3}, {3, 1}};
        System.out.println(solution.magnificentSets(n2, edges2));  // Output: -1

        int n3 = 26;
        int[][] edges3 = {
                {9, 16}, {8, 3}, {20, 21}, {12, 16}, {14, 3}, {7, 21}, {22, 3}, {22, 18},
                {11, 16}, {25, 4}, {2, 4}, {14, 21}, {23, 3}, {17, 3}, {2, 16}, {24, 16},
                {13, 4}, {10, 21}, {7, 4}, {9, 18}, {14, 18}, {14, 4}, {14, 16}, {1, 3},
                {25, 18}, {17, 4}, {1, 16}, {23, 4}, {2, 21}, {5, 16}, {24, 18}, {20, 18},
                {19, 16}, {24, 21}, {9, 3}, {24, 3}, {19, 18}, {25, 16}, {19, 21}, {6, 3},
                {26, 18}, {5, 21}, {20, 16}, {2, 3}, {10, 18}, {26, 16}, {8, 4}, {11, 21},
                {23, 16}, {13, 16}, {25, 3}, {7, 18}, {19, 3}, {20, 4}, {26, 3}, {23, 18},
                {15, 18}, {17, 18}, {10, 16}, {26, 21}, {23, 21}, {7, 16}, {8, 18}, {10, 4},
                {24, 4}, {7, 3}, {11, 18}, {9, 4}, {26, 4}, {13, 21}, {22, 16}, {22, 21},
                {20, 3}, {6, 18}, {9, 21}, {10, 3}, {22, 4}, {1, 18}, {25, 21}, {11, 4},
                {1, 21}, {15, 3}, {1, 4}, {15, 16}, {2, 18}, {13, 3}, {8, 21}, {13, 18},
                {11, 3}, {15, 21}, {8, 16}, {17, 16}, {15, 4}, {12, 3}, {6, 4}, {17, 21},
                {5, 18}, {6, 16}, {6, 21}, {12, 4}, {19, 4}, {5, 3}, {12, 21}, {5, 4}
        };
        System.out.println(solution.magnificentSets(n3, edges3)); // Output: 4
    }

    public int magnificentSets(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        UnionFind uf = new UnionFind(n);
        Map<Integer, Integer> rootToGroupSize = new HashMap<>();

        // build the graph and union connected components
        for (int[] edge : edges) {
            int u = edge[0] - 1;
            int v = edge[1] - 1;
            graph.get(u).add(v);
            graph.get(v).add(u);
            uf.unionByRank(u, v);
        }

        // calculate the maximum group size for each connected component
        for (int i = 0; i < n; i++) {
            int newGroupSize = bfs(graph, i);
            if (newGroupSize == -1) return -1; // Not bipartite
            int root = uf.find(i);
            rootToGroupSize.put(root, Math.max(rootToGroupSize.getOrDefault(root, 0), newGroupSize));
        }

        // sum up the maximum group sizes for all connected components
        int ans = 0;
        for (int groupSize : rootToGroupSize.values()) {
            ans += groupSize;
        }

        return ans;
    }

    private int bfs(List<List<Integer>> graph, int u) {
        int step = 0;
        Queue<Integer> q = new LinkedList<>();
        Map<Integer, Integer> nodeToStep = new HashMap<>();
        q.offer(u);
        nodeToStep.put(u, 1);

        while (!q.isEmpty()) {
            step++;
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                int node = q.poll();
                for (int neighbor : graph.get(node)) {
                    if (!nodeToStep.containsKey(neighbor)) {
                        q.offer(neighbor);
                        nodeToStep.put(neighbor, step + 1);
                    } else if (nodeToStep.get(neighbor) == step) {
                        // there is an odd-length cycle, so the graph is not bipartite
                        return -1;
                    }
                }
            }
        }

        return step;
    }
}

class UnionFind {
    private int[] id;
    private int[] rank;

    public UnionFind(int n) {
        id = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    public void unionByRank(int u, int v) {
        int i = find(u);
        int j = find(v);
        if (i == j) return;
        if (rank[i] < rank[j]) {
            id[i] = j;
        } else if (rank[i] > rank[j]) {
            id[j] = i;
        } else {
            id[i] = j;
            rank[j]++;
        }
    }

    public int find(int u) {
        return id[u] == u ? u : (id[u] = find(id[u]));
    }
}


//Explanation of the Code:
//Union-Find Data Structure :
//-The UnionFind class is used to group nodes into connected components.
//-It supports two operations:
//      -find: Finds the root of a node with path compression.
//      -unionByRank: Merges two sets based on their ranks.
//Graph Construction :
//-The graph is represented as an adjacency list.
//-Edges are added to the graph, and connected components are merged using the UnionFind data structure.
//BFS for Group Size Calculation :
//-For each node, we perform BFS to calculate the maximum number of groups (step) that can be formed.
//-If an odd-length cycle is detected during BFS (i.e., nodeToStep.get(neighbor) == step), the graph is not
// bipartite, and we return -1.
//Combining Results :
//-For each connected component, we track the maximum group size using a rootToGroupSize map.
//-Finally, we sum up the maximum group sizes for all connected components to get the result.
//Complexity:
//Time Complexity : O(N+E)
//Where N is the number of nodes and E is the number of edges.
//Space Complexity : O(N+E)
//Due to the graph representation, Union-Find data structure, BFS auxiliary structures, and the root-to-group size map.


//You are given a positive integer n representing the number of nodes in an undirected graph. The nodes are labeled
// from 1 to n.
//You are also given a 2D integer array edges, where edges[i] = [ai, bi] indicates that there is a bidirectional
// edge between nodes ai and bi. Notice that the given graph may be disconnected.
//Divide the nodes of the graph into m groups (1-indexed) such that:
//Each node in the graph belongs to exactly one group.
//For every pair of nodes in the graph that are connected by an edge [ai, bi], if ai belongs to the group with
// ndex x, and bi belongs to the group with index y, then |y - x| = 1.
//Return the maximum number of groups (i.e., maximum m) into which you can divide the nodes. Return -1 if it is
// impossible to group the nodes with the given conditions.
//
//Example 1:
//Input: n = 6, edges = [[1,2],[1,4],[1,5],[2,6],[2,3],[4,6]]
//Output: 4
//Explanation: As shown in the image we:
//- Add node 5 to the first group.
//- Add node 1 to the second group.
//- Add nodes 2 and 4 to the third group.
//- Add nodes 3 and 6 to the fourth group.
//We can see that every edge is satisfied.
//It can be shown that that if we create a fifth group and move any node from the third or fourth group to it, at
// least on of the edges will not be satisfied.

//Example 2:
//Input: n = 3, edges = [[1,2],[2,3],[3,1]]
//Output: -1
//Explanation: If we add node 1 to the first group, node 2 to the second group, and node 3 to the third group to
// satisfy the first two edges, we can see that the third edge will not be satisfied.
//It can be shown that no grouping is possible.
//
//Constraints:
//1 <= n <= 500
//1 <= edges.length <= 104
//edges[i].length == 2
//1 <= ai, bi <= n
//ai != bi
//There is at most one edge between any pair of vertices.