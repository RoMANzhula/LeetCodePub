package P3201_3300.P3203_Find_Minimum_Diameter_After_Merging_Two_Trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[][] edges1 = {{0, 1}, {0, 2}, {0, 3}};
        int[][] edges2 = {{0, 1}};
        System.out.println(solution.minimumDiameterAfterMerge(edges1, edges2)); // Output: 3

        // Example 2
        int[][] edges3 = {{0, 1}, {0, 2}, {0, 3}, {2, 4}, {2, 5}, {3, 6}, {2, 7}};
        int[][] edges4 = {{0, 1}, {0, 2}, {0, 3}, {2, 4}, {2, 5}, {3, 6}, {2, 7}};
        System.out.println(solution.minimumDiameterAfterMerge(edges3, edges4)); // Output: 5
    }

    public int minimumDiameterAfterMerge(int[][] edges1, int[][] edges2) {
        int d1 = calculateTreeDiameter(edges1);
        int d2 = calculateTreeDiameter(edges2);

        // Calculate the radii of both trees (radius = ceiling(diameter / 2))
        int radius1 = (d1 + 1) / 2;
        int radius2 = (d2 + 1) / 2;

        // Minimum diameter of the merged tree
        return Math.max(d1, Math.max(d2, radius1 + radius2 + 1));
    }

    private int calculateTreeDiameter(int[][] edges) {
        int n = edges.length + 1; // Number of nodes in the tree
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        // Find the farthest node from an arbitrary starting node (0)
        int[] farthestNodeInfo = bfs(0, adj);
        // Find the farthest node from the farthest node found in the previous step
        int[] diameterInfo = bfs(farthestNodeInfo[0], adj);
        return diameterInfo[1]; // Diameter is the distance to the farthest node
    }

    private int[] bfs(int start, List<List<Integer>> adj) {
        int n = adj.size();
        boolean[] visited = new boolean[n];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{start, 0});
        visited[start] = true;

        int farthestNode = start;
        int maxDistance = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int node = current[0];
            int distance = current[1];

            if (distance > maxDistance) {
                farthestNode = node;
                maxDistance = distance;
            }

            for (int neighbor : adj.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(new int[]{neighbor, distance + 1});
                }
            }
        }

        return new int[]{farthestNode, maxDistance};
    }
}

//Explanation:
//Tree Radius: The radius is calculated as half the diameter (rounded up). This is used to determine
// how the centers of the two trees contribute to the diameter of the merged tree.
//Merged Diameter Calculation:
//d1 and d2 are the diameters of the two trees.
//Connecting the two trees increases the diameter by radius1+radius2+1.
//The new diameter is the maximum of the original diameters and the new merged diameter.
//Efficiency: This approach is efficient and runs in O(n+m), where m are the sizes of the two trees.


//There exist two undirected trees with n and m nodes, numbered from 0 to n - 1 and from 0 to m - 1,
// respectively. You are given two 2D integer arrays edges1 and edges2 of lengths n - 1 and m - 1, respectively,
// where edges1[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the first
// tree and edges2[i] = [ui, vi] indicates that there is an edge between nodes ui and vi in the second tree.
//You must connect one node from the first tree with another node from the second tree with an edge.
//Return the minimum possible diameter of the resulting tree.
//The diameter of a tree is the length of the longest path between any two nodes in the tree.
//
//Example 1:
//Input: edges1 = [[0,1],[0,2],[0,3]], edges2 = [[0,1]]
//Output: 3
//Explanation:
//We can obtain a tree of diameter 3 by connecting node 0 from the first tree with any node from the second tree.
//
//Example 2:
//Input: edges1 = [[0,1],[0,2],[0,3],[2,4],[2,5],[3,6],[2,7]], edges2 = [[0,1],[0,2],[0,3],[2,4],[2,5],[3,6],[2,7]]
//Output: 5
//Explanation:
//We can obtain a tree of diameter 5 by connecting node 0 from the first tree with node 0 from the second tree.
//
//Constraints:
//1 <= n, m <= 105
//edges1.length == n - 1
//edges2.length == m - 1
//edges1[i].length == edges2[i].length == 2
//edges1[i] = [ai, bi]
//0 <= ai, bi < n
//edges2[i] = [ui, vi]
//0 <= ui, vi < m
//The input is generated such that edges1 and edges2 represent valid trees.