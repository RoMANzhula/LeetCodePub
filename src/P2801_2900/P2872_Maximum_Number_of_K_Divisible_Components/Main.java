package P2801_2900.P2872_Maximum_Number_of_K_Divisible_Components;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        // Example 1
        int n1 = 5;
        int[][] edges1 = {{0, 2}, {1, 2}, {1, 3}, {2, 4}};
        int[] values1 = {1, 8, 1, 4, 4};
        int k1 = 6;
        System.out.println(solution.maxKDivisibleComponents(n1, edges1, values1, k1)); // Output: 2

        // example 2
        int n2 = 7;
        int[][] edges2 = {{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}};
        int[] values2 = {3, 0, 6, 1, 5, 2, 1};
        int k2 = 3;
        System.out.println(solution.maxKDivisibleComponents(n2, edges2, values2, k2)); // Output: 3
    }

    public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
        // Step 1: Build the tree using an adjacency list
        List<List<Integer>> tree = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tree.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            tree.get(edge[0]).add(edge[1]);
            tree.get(edge[1]).add(edge[0]);
        }

        // Step 2: Initialize variables
        int[] result = new int[1]; // To track the count of valid components
        boolean[] visited = new boolean[n];

        // Step 3: Perform DFS to compute valid components
        dfs(0, -1, tree, values, k, result, visited);

        return result[0];
    }

    private long dfs(int node, int parent, List<List<Integer>> tree, int[] values, int k, int[] result, boolean[] visited) {
        visited[node] = true;
        long sum = values[node]; // Start with the node's value

        for (int neighbor : tree.get(node)) {
            if (neighbor != parent && !visited[neighbor]) {
                sum += dfs(neighbor, node, tree, values, k, result, visited);
            }
        }

        // If the sum of the current subtree is divisible by k, it forms a valid component
        if (sum % k == 0) {
            result[0]++;
        }

        return sum;
    }

}

//Explanation:
//Tree Representation:
//The tree is represented as an adjacency list (List<List<Integer>>), where each node has a list of
// its connected neighbors.
//DFS Traversal:
//- The dfs function recursively traverses the tree, computing the sum of values for each subtree.
//- If the sum of a subtree's values is divisible by k, it is considered a valid component, and
// the count (result[0]) is incremented.
//Logic Flow:
//-Starting from the root node (node 0), the dfs function visits all connected nodes (ignoring the parent to avoid cycles).
//-At each node, the value of the node is added to the total subtree sum.
//- The sum is returned to the parent node to compute the larger subtree.
//Base Case for Valid Components:
//If the total sum of values in a subtree is divisible by k, it indicates a valid split, and the component is counted.
//Main Functionality:
//- Builds the tree from the edges input.
//- Initiates a DFS traversal starting from node 0.
//- Returns the total number of valid components.
//Example Outputs:
//- For the input where n = 5, edges = [[0,2],[1,2],[1,3],[2,4]], values = [1,8,1,4,4], and k = 6, the output is 2.
//- The code correctly identifies that removing certain edges creates valid components.

//Time complexity:
//1. Tree Construction:
//- Constructing the adjacency list takes O(n) since there are n−1 edges.
//2. DFS Traversal:
//- The DFS visits each node exactly once and processes all edges once, resulting in O(n) time.
//Overall Time Complexity: O(n).
//
//Space Complexity:
//1. Tree Storage:
//  -The adjacency list representation of the tree takes O(n) space.
//2. Visited Array:
// -The visited array takes O(n) space to keep track of visited nodes.
//3. Recursive Stack:
//  -The recursive DFS function uses O(h) space, where h is the height of the tree. For a balanced
// tree, h=O(logn), but in the worst case of a skewed tree, h=O(n).
//Overall Space Complexity: O(n).



//There is an undirected tree with n nodes labeled from 0 to n - 1. You are given the integer n and a 2D
// integer array edges of length n - 1, where edges[i] = [ai, bi] indicates that there is an edge
// between nodes ai and bi in the tree.
//You are also given a 0-indexed integer array values of length n, where values[i] is the value associated
// with the ith node, and an integer k.
//A valid split of the tree is obtained by removing any set of edges, possibly empty, from the tree such that the
// resulting components all have values that are divisible by k, where the value of a connected component is
// the sum of the values of its nodes.
//Return the maximum number of components in any valid split.
//
//Example 1:
//Input: n = 5, edges = [[0,2],[1,2],[1,3],[2,4]], values = [1,8,1,4,4], k = 6
//Output: 2
//Explanation: We remove the edge connecting node 1 with 2. The resulting split is valid because:
//- The value of the component containing nodes 1 and 3 is values[1] + values[3] = 12.
//- The value of the component containing nodes 0, 2, and 4 is values[0] + values[2] + values[4] = 6.
//It can be shown that no other valid split has more than 2 connected components.

//Example 2:
//Input: n = 7, edges = [[0,1],[0,2],[1,3],[1,4],[2,5],[2,6]], values = [3,0,6,1,5,2,1], k = 3
//Output: 3
//Explanation: We remove the edge connecting node 0 with 2, and the edge connecting node 0 with 1. The resulting
// split is valid because:
//- The value of the component containing node 0 is values[0] = 3.
//- The value of the component containing nodes 2, 5, and 6 is values[2] + values[5] + values[6] = 9.
//- The value of the component containing nodes 1, 3, and 4 is values[1] + values[3] + values[4] = 6.
//It can be shown that no other valid split has more than 3 connected components.
//
//Constraints:
//1 <= n <= 3 * 104
//edges.length == n - 1
//edges[i].length == 2
//0 <= ai, bi < n
//values.length == n
//0 <= values[i] <= 109
//1 <= k <= 109
//Sum of values is divisible by k.
//The input is generated such that edges represents a valid tree.
