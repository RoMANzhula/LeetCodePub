package P801_900.P802_Find_Eventual_Safe_States;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[][] graph1 = {{1, 2}, {2, 3}, {5}, {0}, {5}, {}, {}};
        System.out.println(solution.eventualSafeNodes(graph1)); // Output: [2, 4, 5, 6]

        int[][] graph2 = {{1, 2, 3, 4}, {1, 2}, {3, 4}, {0, 4}, {}};
        System.out.println(solution.eventualSafeNodes(graph2)); // Output: [4]
    }

    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        List<List<Integer>> reverseGraph = new ArrayList<>();
        int[] inDegree = new int[n];

        // initialize the reverse graph
        for (int i = 0; i < n; i++) {
            reverseGraph.add(new ArrayList<>());
        }

        // build the reverse graph and compute in-degrees
        for (int i = 0; i < n; i++) {
            for (int neighbor : graph[i]) {
                reverseGraph.get(neighbor).add(i);
                inDegree[i]++;
            }
        }

        // queue ti process nodes with zero in-degree
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // process nodes
        List<Integer> safeNodes = new ArrayList<>();
        while (!queue.isEmpty()) {
            int node = queue.poll();
            safeNodes.add(node);

            for (Integer neighbor : reverseGraph.get(node)) {
                inDegree[neighbor]--;

                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // sort the safe nodes in ascending order
        Collections.sort(safeNodes);

        return safeNodes;
    }
}

//Explanation:
//Reverse the Graph: This simplifies the problem by allowing us to focus on nodes that have no incoming edges in
// the reversed graph.
//Topological Sorting: Using BFS ensures that we process nodes with zero in-degree first, marking them as safe
// and reducing dependencies on other nodes.
//Sorting and Returning: The safe nodes are added to the result list, which is then sorted before being returned.
//Complexity:
//Time Complexity:
//O(V+E), where V is the number of vertices and E is the number of edges, as we traverse each edge and vertex once.
//Space Complexity:
//O(V+E), for the reverse graph and in-degree array.


//There is a directed graph of n nodes with each node labeled from 0 to n - 1. The graph is represented by a
// 0-indexed 2D integer array graph where graph[i] is an integer array of nodes adjacent to node i, meaning
// there is an edge from node i to each node in graph[i].
//A node is a terminal node if there are no outgoing edges. A node is a safe node if every possible path starting
// from that node leads to a terminal node (or another safe node).
//Return an array containing all the safe nodes of the graph. The answer should be sorted in ascending order.
//
//Example 1:
//Illustration of graph
//Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
//Output: [2,4,5,6]
//Explanation: The given graph is shown above.
//Nodes 5 and 6 are terminal nodes as there are no outgoing edges from either of them.
//Every path starting at nodes 2, 4, 5, and 6 all lead to either node 5 or 6.

//Example 2:
//Input: graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
//Output: [4]
//Explanation:
//Only node 4 is a terminal node, and every path starting at node 4 leads to node 4.
//
//Constraints:
//n == graph.length
//1 <= n <= 104
//0 <= graph[i].length <= n
//0 <= graph[i][j] <= n - 1
//graph[i] is sorted in a strictly increasing order.
//The graph may contain self-loops.
//The number of edges in the graph will be in the range [1, 4 * 104].