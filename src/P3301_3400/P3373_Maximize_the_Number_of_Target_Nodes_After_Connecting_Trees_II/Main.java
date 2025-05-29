package P3301_3400.P3373_Maximize_the_Number_of_Target_Nodes_After_Connecting_Trees_II;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] edges1 = {{0, 1}, {0, 2}, {2, 3}, {2, 4}};
        int[][] edges2 = {{0, 1}, {0, 2}, {0, 3}, {2, 7}, {1, 4}, {4, 5}, {4, 6}};
        System.out.println(Arrays.toString(solution.maxTargetNodes(edges1, edges2))); // [8, 7, 7, 8, 8]

        int[][] edges3 = {{0, 1}, {0, 2}, {0, 3}, {0, 4}};
        int[][] edges4 = {{0, 1}, {1, 2}, {2, 3}};
        System.out.println(Arrays.toString(solution.maxTargetNodes(edges3, edges4))); // [3, 6, 6, 6, 6]
    }

    // BFS that returns the number of nodes at even levels and optionally marks them
    private int bfs(int start, List<List<Integer>> adj, boolean[] included) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start, -1});
        int level = 0;
        int count = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            if (level % 2 == 0) {
                count += size;
            }

            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int node = curr[0], parent = curr[1];

                if (level % 2 == 0 && included != null) {
                    included[node] = true;
                }

                for (int neighbor : adj.get(node)) {
                    if (neighbor != parent) {
                        queue.offer(new int[]{neighbor, node});
                    }
                }
            }

            level++;
        }

        return count;
    }

    public int[] maxTargetNodes(int[][] edges1, int[][] edges2) {
        int n1 = edges1.length + 1;
        int n2 = edges2.length + 1;

        List<List<Integer>> adj1 = new ArrayList<>();
        List<List<Integer>> adj2 = new ArrayList<>();

        for (int i = 0; i <= n1; i++) adj1.add(new ArrayList<>());
        for (int i = 0; i <= n2; i++) adj2.add(new ArrayList<>());

        for (int[] edge : edges1) {
            adj1.get(edge[0]).add(edge[1]);
            adj1.get(edge[1]).add(edge[0]);
        }

        for (int[] edge : edges2) {
            adj2.get(edge[0]).add(edge[1]);
            adj2.get(edge[1]).add(edge[0]);
        }

        // count even-level nodes in tree2
        int evenCount2 = bfs(0, adj2, null);
        int oddCount2 = n2 - evenCount2;
        int best2 = Math.max(evenCount2, oddCount2);

        // count even-level nodes in tree1 and mark which nodes are at even level
        boolean[] included = new boolean[n1]; // included[i] == true if node i is at even level
        int evenCount1 = bfs(0, adj1, included);

        // build the answer
        int[] ans = new int[n1];
        for (int i = 0; i < n1; i++) {
            if (included[i]) {
                ans[i] = evenCount1 + best2;
            } else {
                ans[i] = (n1 - evenCount1) + best2;
            }
        }

        return ans;
    }

}

//Complexity:
// time - O(n + m)
// space - O(n + m)


//There exist two undirected trees with n and m nodes, labeled from [0, n - 1] and [0, m - 1], respectively.
//You are given two 2D integer arrays edges1 and edges2 of lengths n - 1 and m - 1, respectively, where
// edges1[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the first tree and
// edges2[i] = [ui, vi] indicates that there is an edge between nodes ui and vi in the second tree.
//Node u is target to node v if the number of edges on the path from u to v is even. Note that a node is always
// target to itself.
//Return an array of n integers answer, where answer[i] is the maximum possible number of nodes that are target to
// node i of the first tree if you had to connect one node from the first tree to another node in the second tree.
//Note that queries are independent from each other. That is, for every query you will remove the added edge before
// proceeding to the next query.

//Example 1:
//Input: edges1 = [[0,1],[0,2],[2,3],[2,4]], edges2 = [[0,1],[0,2],[0,3],[2,7],[1,4],[4,5],[4,6]]
//Output: [8,7,7,8,8]
//Explanation:
//For i = 0, connect node 0 from the first tree to node 0 from the second tree.
//For i = 1, connect node 1 from the first tree to node 4 from the second tree.
//For i = 2, connect node 2 from the first tree to node 7 from the second tree.
//For i = 3, connect node 3 from the first tree to node 0 from the second tree.
//For i = 4, connect node 4 from the first tree to node 4 from the second tree.

//Example 2:
//Input: edges1 = [[0,1],[0,2],[0,3],[0,4]], edges2 = [[0,1],[1,2],[2,3]]
//Output: [3,6,6,6,6]
//Explanation:
//For every i, connect node i of the first tree with any node of the second tree.

//Constraints:
//2 <= n, m <= 105
//edges1.length == n - 1
//edges2.length == m - 1
//edges1[i].length == edges2[i].length == 2
//edges1[i] = [ai, bi]
//0 <= ai, bi < n
//edges2[i] = [ui, vi]
//0 <= ui, vi < m
//The input is generated such that edges1 and edges2 represent valid trees.
