package P2601_2700.P2685_Count_the_Number_of_Complete_Components;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] edges1 = {{0,1},{0,2},{1,2},{3,4}};
        int[][] edges2 = {{0,1},{0,2},{1,2},{3,4},{3,5}};

        System.out.println(solution.countCompleteComponents(6, edges1)); // Output: 3
        System.out.println(solution.countCompleteComponents(6, edges2)); // Output: 1
    }

    public int countCompleteComponents(int n, int[][] edges) {
        // step 1: build adjacency list
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        // step 2: initialize visited set and count complete components
        boolean[] visited = new boolean[n];
        int completeComponents = 0;

        // sstep 3: explore each component using DFS
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                List<Integer> componentNodes = new ArrayList<>();
                int[] edgeCount = new int[1];  // Using array to modify in DFS
                dfs(i, graph, visited, componentNodes, edgeCount);

                int nodeCount = componentNodes.size();
                int expectedEdges = nodeCount * (nodeCount - 1) / 2;

                if (edgeCount[0] / 2 == expectedEdges) {  // Each edge is counted twice
                    completeComponents++;
                }
            }
        }
        return completeComponents;
    }

    private void dfs(int node, List<List<Integer>> graph, boolean[] visited,
                     List<Integer> componentNodes, int[] edgeCount) {
        visited[node] = true;
        componentNodes.add(node);
        for (int neighbor : graph.get(node)) {
            edgeCount[0]++;
            if (!visited[neighbor]) {
                dfs(neighbor, graph, visited, componentNodes, edgeCount);
            }
        }
    }

}

//Explanation:
//Graph Construction: Uses an adjacency list.
//DFS Traversal: Finds all nodes in a component and counts edges.
//Checking Completeness:
//-If a component has k nodes, it should have exactly k(k−1)/2 edges.
//-The edgeCount array stores the total edges found, but since every edge is counted twice (once from each node), we
// divide it by 2.
//Complexity Analysis:
//Graph Construction: O(n+m) where m is the number of edges.
//DFS Traversal: O(n+m).
//Total Complexity: O(n+m), which is efficient for n≤50.


//You are given an integer n. There is an undirected graph with n vertices, numbered from 0 to n - 1. You are given
// a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting
// vertices ai and bi.
//Return the number of complete connected components of the graph.
//A connected component is a subgraph of a graph in which there exists a path between any two vertices, and no
// vertex of the subgraph shares an edge with a vertex outside of the subgraph.
//A connected component is said to be complete if there exists an edge between every pair of its vertices.

//Example 1:
//Input: n = 6, edges = [[0,1],[0,2],[1,2],[3,4]]
//Output: 3
//Explanation: From the picture above, one can see that all of the components of this graph are complete.

//Example 2:
//Input: n = 6, edges = [[0,1],[0,2],[1,2],[3,4],[3,5]]
//Output: 1
//Explanation: The component containing vertices 0, 1, and 2 is complete since there is an edge between every pair
// of two vertices. On the other hand, the component containing vertices 3, 4, and 5 is not complete since there is
// no edge between vertices 4 and 5. Thus, the number of complete components in this graph is 1.

//Constraints:
//1 <= n <= 50
//0 <= edges.length <= n * (n - 1) / 2
//edges[i].length == 2
//0 <= ai, bi <= n - 1
//ai != bi
//There are no repeated edges.
