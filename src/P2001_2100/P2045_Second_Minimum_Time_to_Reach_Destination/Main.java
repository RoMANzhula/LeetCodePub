package P2001_2100.P2045_Second_Minimum_Time_to_Reach_Destination;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int n = 5;
        int[][] edges = {{1,2},{1,3},{1,4},{3,4},{4,5}};
        int time = 3;
        int change = 5;
        System.out.println(solution.secondMinimum(n, edges, time, change)); // Output: 13

        n = 2;
        edges = new int[][]{{1,2}};
        time = 3;
        change = 2;
        System.out.println(solution.secondMinimum(n, edges, time, change)); // Output: 11
    }

    public int secondMinimum(int n, int[][] edges, int time, int change) {
        // Create adjacency lists for all nodes
        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph[u].add(v);
            graph[v].add(u);
        }

        // Queue for breadth-first search (BFS)
        Queue<int[]> bfsQueue = new LinkedList<>();
        bfsQueue.offer(new int[]{1, 0}); // Start from node 1 with distance 0

        // Initialize distances with two slots per node to store the two smallest distances
        int[][] distances = new int[n + 1][2];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(distances[i], Integer.MAX_VALUE);
        }
        distances[1][0] = 0; // Distance to the starting node is zero

        // Perform BFS
        while (!bfsQueue.isEmpty()) {
            int[] nodeData = bfsQueue.poll();
            int currentNode = nodeData[0];
            int currentDistance = nodeData[1];

            // Explore neighbors
            for (int neighbor : graph[currentNode]) {
                // Record smallest distance
                if (currentDistance + 1 < distances[neighbor][0]) {
                    distances[neighbor][1] = distances[neighbor][0]; // Update second smallest
                    distances[neighbor][0] = currentDistance + 1;    // Update smallest
                    bfsQueue.offer(new int[]{neighbor, currentDistance + 1});
                }
                // Record second smallest distance
                else if (distances[neighbor][0] < currentDistance + 1 &&
                        currentDistance + 1 < distances[neighbor][1]) {
                    distances[neighbor][1] = currentDistance + 1;
                    bfsQueue.offer(new int[]{neighbor, currentDistance + 1});
                }
            }
        }

        // Calculate the total time to reach the destination node using the second smallest distance
        int totalTime = 0;
        for (int i = 0; i < distances[n][1]; ++i) {
            totalTime += time;
            // Adjust total time based on traffic signal change interval
            if (i < distances[n][1] - 1 && (totalTime / change) % 2 == 1) {
                totalTime += change - (totalTime % change);  // Wait for the green signal
            }
        }
        return totalTime;
    }

}

//Цей код знаходить другий мінімальний час для переміщення від вершини 1 до вершини n, враховуючи зміну
// сигналу світлофора на кожній вершині. BFS використовується для знаходження найкоротших і других найкоротших
// шляхів, а потім обчислюється загальний час з урахуванням червоних сигналів.


//A city is represented as a bi-directional connected graph with n vertices where each vertex is labeled from 1 to
// n (inclusive). The edges in the graph are represented as a 2D integer array edges, where each
// edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by
// at most one edge, and no vertex has an edge to itself. The time taken to traverse any edge is time minutes.
//Each vertex has a traffic signal which changes its color from green to red and vice versa every change minutes. All
// signals change at the same time. You can enter a vertex at any time, but can leave a vertex only when the signal
// is green. You cannot wait at a vertex if the signal is green.
//The second minimum value is defined as the smallest value strictly larger than the minimum value.
//For example the second minimum value of [2, 3, 4] is 3, and the second minimum value of [2, 2, 4] is 4.
//Given n, edges, time, and change, return the second minimum time it will take to go from vertex 1 to vertex n.
//Notes:
//You can go through any vertex any number of times, including 1 and n.
//You can assume that when the journey starts, all signals have just turned green.
//
//Example 1:
//       
//Input: n = 5, edges = [[1,2],[1,3],[1,4],[3,4],[4,5]], time = 3, change = 5
//Output: 13
//Explanation:
//The figure on the left shows the given graph.
//The blue path in the figure on the right is the minimum time path.
//The time taken is:
//- Start at 1, time elapsed=0
//- 1 -> 4: 3 minutes, time elapsed=3
//- 4 -> 5: 3 minutes, time elapsed=6
//Hence the minimum time needed is 6 minutes.
//
//The red path shows the path to get the second minimum time.
//- Start at 1, time elapsed=0
//- 1 -> 3: 3 minutes, time elapsed=3
//- 3 -> 4: 3 minutes, time elapsed=6
//- Wait at 4 for 4 minutes, time elapsed=10
//- 4 -> 5: 3 minutes, time elapsed=13
//Hence the second minimum time is 13 minutes.

//Example 2:
//Input: n = 2, edges = [[1,2]], time = 3, change = 2
//Output: 11
//Explanation:
//The minimum time path is 1 -> 2 with time = 3 minutes.
//The second minimum time path is 1 -> 2 -> 1 -> 2 with time = 11 minutes.
//
//Constraints:
//2 <= n <= 104
//n - 1 <= edges.length <= min(2 * 104, n * (n - 1) / 2)
//edges[i].length == 2
//1 <= ui, vi <= n
//ui != vi
//There are no duplicate edges.
//Each vertex can be reached directly or indirectly from every other vertex.
//1 <= time, change <= 103
