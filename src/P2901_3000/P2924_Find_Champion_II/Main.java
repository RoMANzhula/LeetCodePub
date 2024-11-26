package P2901_3000.P2924_Find_Champion_II;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int n1 = 3;
        int[][] edges1 = {{0, 1}, {1, 2}};
        System.out.println(solution.findChampion(n1, edges1)); // Output: 0

        int n2 = 4;
        int[][] edges2 = {{0, 2}, {1, 3}, {1, 2}};
        System.out.println(solution.findChampion(n2, edges2)); // Output: -1
    }

    public int findChampion(int n, int[][] edges) {
        // Step 1: Represent the graph using an adjacency list
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegree = new int[n];
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // Populate the graph and calculate in-degrees
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            graph.get(from).add(to);
            inDegree[to]++;
        }

        // Step 2: Find the node with in-degree 0
        int champion = -1;
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                if (champion == -1) {
                    champion = i; // First candidate for champion
                } else {
                    return -1; // More than one node with in-degree 0
                }
            }
        }

        // If there's no node with in-degree 0, return -1
        if (champion == -1) return -1;

        // Step 3: Validate that the champion can reach all other nodes
        boolean[] visited = new boolean[n];
        dfs(champion, graph, visited);

        for (int i = 0; i < n; i++) {
            if (i != champion && !visited[i]) {
                return -1; // Champion cannot reach all nodes
            }
        }

        return champion;
    }

    // DFS helper method to traverse the graph
    private void dfs(int node, List<List<Integer>> graph, boolean[] visited) {
        visited[node] = true;
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, graph, visited);
            }
        }
    }
}

//Explanation:
//Graph Construction: The adjacency list helps efficiently manage the graph structure and in-degree array
// tracks how many edges point to each node.
//Candidate Check: Identifies the potential champion based on zero in-degree.
//Reachability Validation: A DFS confirms if the potential champion can reach all other nodes in the graph,
// proving its dominance.
//Edge Cases: If no node or multiple nodes have zero in-degree, or if the champion cannot reach all nodes, we return -1.
//This solution is efficient with a time complexity of O(n+m), where n is the number of nodes and
//m is the number of edges.


//There are n teams numbered from 0 to n - 1 in a tournament; each team is also a node in a DAG.
//You are given the integer n and a 0-indexed 2D integer array edges of length m representing the DAG,
// where edges[i] = [ui, vi] indicates that there is a directed edge from team ui to team vi in the graph.
//A directed edge from a to b in the graph means that team a is stronger than team b and team b is weaker than team a.
//Team a will be the champion of the tournament if there is no team b that is stronger than team a.
//Return the team that will be the champion of the tournament if there is a unique champion, otherwise, return -1.
//Notes
//A cycle is a series of nodes a1, a2, ..., an, an+1 such that node a1 is the same node as
// node an+1, the nodes a1, a2, ..., an are distinct, and there is a directed edge from the node
// ai to node ai+1 for every i in the range [1, n].
//A DAG is a directed graph that does not have any cycle.
//
//Example 1:
//Input: n = 3, edges = [[0,1],[1,2]]
//Output: 0
//Explanation: Team 1 is weaker than team 0. Team 2 is weaker than team 1. So the champion is team 0.

//Example 2:
//Input: n = 4, edges = [[0,2],[1,3],[1,2]]
//Output: -1
//Explanation: Team 2 is weaker than team 0 and team 1. Team 3 is weaker than team 1. But team 1 and
// team 0 are not weaker than any other teams. So the answer is -1.
//
//Constraints:
//1 <= n <= 100
//m == edges.length
//0 <= m <= n * (n - 1) / 2
//edges[i].length == 2
//0 <= edge[i][j] <= n - 1
//edges[i][0] != edges[i][1]
//The input is generated such that if team a is stronger than team b, team b is not stronger than team a.
//The input is generated such that if team a is stronger than team b and team b is stronger than
// team c, then team a is stronger than team c.
