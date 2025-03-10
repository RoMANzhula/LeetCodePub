package P801_900.P847_Shortest_Path_Visiting_All_Nodes;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public int shortestPathLength(int[][] graph) {
        int n = graph.length; //number of vertices(hills) in graph
        int fullMask = (1 << n) - 1; //full bitmask to represent all nodes visited (full bitmask used to represent
        // all visited vertices)
        int[][] dp = new int[n][fullMask + 1]; //to save shorter ways
        Queue<int[]> queue = new LinkedList<>(); //collection for Breadth-First Search (BFS)

        //initialize the queue with all nodes as starting points
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            dp[i][1 << i] = 0; //for first(starting) hill
            queue.offer(new int[] { i, 1 << i });
        }

        //Breadth-First Search (BFS)
        while (!queue.isEmpty()) {
            int[] nodeState = queue.poll();
            int node = nodeState[0];
            int state = nodeState[1];

            for (int neighbor : graph[node]) {
                int nextState = state | (1 << neighbor);

                if (dp[neighbor][nextState] > dp[node][state] + 1) {
                    dp[neighbor][nextState] = dp[node][state] + 1;
                    queue.offer(new int[] { neighbor, nextState });
                }
            }
        }

        //searching minimal way
        int minPath = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            minPath = Math.min(minPath, dp[i][fullMask]);
        }

        return minPath; //bingo
    }

    public static void main(String[] args) {
        Main solution = new Main();
        int[][] graph1 = {{1,2,3},{0},{0},{0}};
        System.out.println(solution.shortestPathLength(graph1)); // Output: 4

        int[][] graph2 = {{1},{0,2,4},{1,3,4},{2},{1,2}};
        System.out.println(solution.shortestPathLength(graph2)); // Output: 4
    }
}

//You have an undirected, connected graph of n nodes labeled from 0 to n - 1. You are given an array graph where
// graph[i] is a list of all the nodes connected with node i by an edge.
//Return the length of the shortest path that visits every node. You may start and stop at any node, you may revisit
// nodes multiple times, and you may reuse edges.

//Example 1:
//Input: graph = [[1,2,3],[0],[0],[0]]
//Output: 4
//Explanation: One possible path is [1,0,2,0,3]

//Example 2:
//Input: graph = [[1],[0,2,4],[1,3,4],[2],[1,2]]
//Output: 4
//Explanation: One possible path is [0,1,4,2,3]

//Constraints:
//n == graph.length
//1 <= n <= 12
//0 <= graph[i].length < n
//graph[i] does not contain i.
//If graph[a] contains b, then graph[b] contains a.
//The input graph is always connected.
