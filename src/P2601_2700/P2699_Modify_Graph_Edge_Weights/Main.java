package P2601_2700.P2699_Modify_Graph_Edge_Weights;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        // Example 1
        int n1 = 5;
        int[][] edges1 = {
                {4, 1, -1}, {2, 0, -1}, {0, 3, -1}, {4, 3, -1}
        };
        int source1 = 0;
        int destination1 = 1;
        int target1 = 5;
        int[][] result1 = solution.modifiedGraphEdges(n1, edges1, source1, destination1, target1);
        System.out.println("Example 1:");
        System.out.println(Arrays.deepToString(result1));

        // Example 2
        int n2 = 3;
        int[][] edges2 = {
                {0, 1, -1}, {0, 2, 5}
        };
        int source2 = 0;
        int destination2 = 2;
        int target2 = 6;
        int[][] result2 = solution.modifiedGraphEdges(n2, edges2, source2, destination2, target2);
        System.out.println("Example 2:");
        System.out.println(Arrays.deepToString(result2));

        // Example 3
        int n3 = 4;
        int[][] edges3 = {
                {1, 0, 4}, {1, 2, 3}, {2, 3, 5}, {0, 3, -1}
        };
        int source3 = 0;
        int destination3 = 2;
        int target3 = 6;
        int[][] result3 = solution.modifiedGraphEdges(n3, edges3, source3, destination3, target3);
        System.out.println("Example 3:");
        System.out.println(Arrays.deepToString(result3));
    }

    private static final int kMax = 2_000_000_000;

    public int[][] modifiedGraphEdges(int n, int[][] edges, int source, int destination, int target) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; ++i)
            graph.add(new ArrayList<>());

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            if (w == -1) continue;
            graph.get(u).add(new int[]{v, w});
            graph.get(v).add(new int[]{u, w});
        }

        int distToDestination = dijkstra(graph, n, source, destination);
        if (distToDestination < target) return new int[0][];
        if (distToDestination == target) {
            for (int[] edge : edges)
                if (edge[2] == -1)
                    edge[2] = kMax;
            return edges;
        }

        for (int i = 0; i < edges.length; ++i) {
            int u = edges[i][0], v = edges[i][1];
            int w = edges[i][2];
            if (w != -1) continue;

            w = 1;
            edges[i][2] = 1;
            graph.get(u).add(new int[]{v, 1});
            graph.get(v).add(new int[]{u, 1});
            distToDestination = dijkstra(graph, n, source, destination);
            if (distToDestination <= target) {
                edges[i][2] += target - distToDestination;
                for (int j = i + 1; j < edges.length; ++j)
                    if (edges[j][2] == -1)
                        edges[j][2] = kMax;
                return edges;
            }
        }

        return new int[0][];
    }

    private int dijkstra(List<List<int[]>> graph, int n, int src, int dst) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        minHeap.offer(new int[]{0, src});

        while (!minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            int d = curr[0], u = curr[1];
            if (d > dist[u]) continue;
            for (int[] neighbor : graph.get(u)) {
                int v = neighbor[0], w = neighbor[1];
                if (d + w < dist[v]) {
                    dist[v] = d + w;
                    minHeap.offer(new int[]{dist[v], v});
                }
            }
        }

        return dist[dst];
    }
}


//How This Code Works:
//Graph Construction: We first create a graph with a list of neighbors for each node, including only those edges
// that have a weight different from -1.
//Distance Calculation Using Dijkstra's Algorithm: Dijkstra's algorithm is used to find the shortest path from the
// source to the destination.
//Checking: If the initial distance is already greater than or equal to the target, we either change the weights of
// all edges with -1 weights or return an empty result if it is impossible to reach the target distance.
//Edge Modification: If after setting the minimum weight to 1, the distance is still less than the target, we adjust
// the edge weight to achieve the desired distance.
//Output: The code returns the updated edges or an empty array if no suitable solution was found.


//You are given an undirected weighted connected graph containing n nodes labeled from 0 to n - 1, and an
// integer array edges where edges[i] = [ai, bi, wi] indicates that there is an edge between nodes ai and
// bi with weight wi.
//Some edges have a weight of -1 (wi = -1), while others have a positive weight (wi > 0).
//Your task is to modify all edges with a weight of -1 by assigning them positive integer values in the
// range [1, 2 * 109] so that the shortest distance between the nodes source and destination becomes equal to an
// integer target. If there are multiple modifications that make the shortest distance between source and
// destination equal to target, any of them will be considered correct.
//Return an array containing all edges (even unmodified ones) in any order if it is possible to make the
// shortest distance from source to destination equal to target, or an empty array if it's impossible.
//Note: You are not allowed to modify the weights of edges with initial positive weights.
//
//Example 1:
//Input: n = 5, edges = [[4,1,-1],[2,0,-1],[0,3,-1],[4,3,-1]], source = 0, destination = 1, target = 5
//Output: [[4,1,1],[2,0,1],[0,3,3],[4,3,1]]
//Explanation: The graph above shows a possible modification to the edges, making the distance from 0 to 1 equal to 5.

//Example 2:
//Input: n = 3, edges = [[0,1,-1],[0,2,5]], source = 0, destination = 2, target = 6
//Output: []
//Explanation: The graph above contains the initial edges. It is not possible to make the distance from 0 to 2 equal
// to 6 by modifying the edge with weight -1. So, an empty array is returned.

//Example 3:
//Input: n = 4, edges = [[1,0,4],[1,2,3],[2,3,5],[0,3,-1]], source = 0, destination = 2, target = 6
//Output: [[1,0,4],[1,2,3],[2,3,5],[0,3,1]]
//Explanation: The graph above shows a modified graph having the shortest distance from 0 to 2 as 6.
//
//Constraints:
//1 <= n <= 100
//1 <= edges.length <= n * (n - 1) / 2
//edges[i].length == 3
//0 <= ai, bi < n
//wi = -1 or 1 <= wi <= 107
//ai != bi
//0 <= source, destination < n
//source != destination
//1 <= target <= 109
//The graph is connected, and there are no self-loops or repeated edges