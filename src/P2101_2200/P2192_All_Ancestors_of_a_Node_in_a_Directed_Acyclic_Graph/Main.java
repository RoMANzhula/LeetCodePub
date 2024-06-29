package P2101_2200.P2192_All_Ancestors_of_a_Node_in_a_Directed_Acyclic_Graph;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int n1 = 8;
        int[][] edges1 = { {0, 3}, {0, 4}, {1, 3}, {2, 4}, {2, 7}, {3, 5}, {3, 6}, {3, 7}, {4, 6} };
        System.out.println(solution.getAncestors(n1, edges1)); // Output: [[],[],[],[0,1],[0,2],[0,1,3],[0,1,2,3,4],[0,1,2,3]]

        int n2 = 5;
        int[][] edges2 = { {0, 1}, {0, 2}, {0, 3}, {0, 4}, {1, 2}, {1, 3}, {1, 4}, {2, 3}, {2, 4}, {3, 4} };
        System.out.println(solution.getAncestors(n2, edges2)); // Output: [[],[0],[0,1],[0,1,2],[0,1,2,3]]
    }

    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        // Initialize the graph
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        int[] inDegree = new int[n];
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            inDegree[edge[1]]++;
        }

        // Topological sorting
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        // List to store the ancestors of each node
        List<Set<Integer>> ancestors = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ancestors.add(new HashSet<>());
        }

        // Process nodes in topological order
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbor : graph.get(node)) {
                ancestors.get(neighbor).add(node);
                ancestors.get(neighbor).addAll(ancestors.get(node));
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        // Convert each set of ancestors to a sorted list
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> list = new ArrayList<>(ancestors.get(i));
            Collections.sort(list);
            result.add(list);
        }

        return result;
    }
}

//Explanation:
//Graph Initialization: Create an adjacency list and calculate the in-degree of each node.
//Topological Sorting: Use a queue to perform Kahn's algorithm for topological sorting.
//Dynamic Programming: For each node processed in topological order, update its neighbors' ancestors set
// with its ancestors and itself.
//Sorting Ancestors: Convert each set of ancestors into a sorted list before returning the result.
//This solution is more efficient as it processes each node and edge only once, making it well-suited for larger graphs.


//You are given a positive integer n representing the number of nodes of a Directed Acyclic Graph (DAG). The nodes
// are numbered from 0 to n - 1 (inclusive).
//You are also given a 2D integer array edges, where edges[i] = [fromi, toi] denotes that there is a unidirectional
// edge from fromi to toi in the graph.
//Return a list answer, where answer[i] is the list of ancestors of the ith node, sorted in ascending order.
//A node u is an ancestor of another node v if u can reach v via a set of edges.
//
//Example 1:
//Input: n = 8, edgeList = [[0,3],[0,4],[1,3],[2,4],[2,7],[3,5],[3,6],[3,7],[4,6]]
//Output: [[],[],[],[0,1],[0,2],[0,1,3],[0,1,2,3,4],[0,1,2,3]]
//Explanation:
//The above diagram represents the input graph.
//- Nodes 0, 1, and 2 do not have any ancestors.
//- Node 3 has two ancestors 0 and 1.
//- Node 4 has two ancestors 0 and 2.
//- Node 5 has three ancestors 0, 1, and 3.
//- Node 6 has five ancestors 0, 1, 2, 3, and 4.
//- Node 7 has four ancestors 0, 1, 2, and 3.

//Example 2:
//Input: n = 5, edgeList = [[0,1],[0,2],[0,3],[0,4],[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
//Output: [[],[0],[0,1],[0,1,2],[0,1,2,3]]
//Explanation:
//The above diagram represents the input graph.
//- Node 0 does not have any ancestor.
//- Node 1 has one ancestor 0.
//- Node 2 has two ancestors 0 and 1.
//- Node 3 has three ancestors 0, 1, and 2.
//- Node 4 has four ancestors 0, 1, 2, and 3.
//
//Constraints:
//1 <= n <= 1000
//0 <= edges.length <= min(2000, n * (n - 1) / 2)
//edges[i].length == 2
//0 <= fromi, toi <= n - 1
//fromi != toi
//There are no duplicate edges.
//The graph is directed and acyclic.
