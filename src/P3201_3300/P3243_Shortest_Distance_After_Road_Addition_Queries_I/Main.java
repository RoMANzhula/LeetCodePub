package P3201_3300.P3243_Shortest_Distance_After_Road_Addition_Queries_I;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int n1 = 5;
        int[][] queries1 = {{2, 4}, {0, 2}, {0, 4}};
        System.out.println(Arrays.toString(solution.shorterDistanceAfterQueries(n1, queries1))); // Output: [3, 2, 1]

        int n2 = 4;
        int[][] queries2 = {{0, 3}, {0, 2}};
        System.out.println(Arrays.toString(solution.shorterDistanceAfterQueries(n2, queries2))); // Output: [1, 1]

    }

    public int[] shorterDistanceAfterQueries(int n, int[][] queries) {
        // Initialize graph
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // Add initial unidirectional roads
        for (int i = 0; i < n - 1; i++) {
            graph.get(i).add(new int[]{i + 1, 1}); // road from i to i+1 with weight 1
        }

        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int u = query[0];
            int v = query[1];

            // Add new road
            graph.get(u).add(new int[]{v, 1});

            // Compute shortest path from 0 to n-1
            result[i] = dijkstra(graph, n);
        }

        return result;
    }

    private int dijkstra(List<List<int[]>> graph, int n) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        pq.offer(new int[]{0, 0}); // {city, distance}

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int city = curr[0];
            int distance = curr[1];

            if (distance > dist[city]) continue;

            for (int[] neighbor : graph.get(city)) {
                int nextCity = neighbor[0];
                int weight = neighbor[1];
                if (dist[city] + weight < dist[nextCity]) {
                    dist[nextCity] = dist[city] + weight;
                    pq.offer(new int[]{nextCity, dist[nextCity]});
                }
            }
        }

        return dist[n - 1];
    }
}

//Explanation:
//Graph Initialization:
//We create a directed graph with n cities. Initially, each city i has a road to i + 1 with a weight of 1.
//Processing Queries:
//For each query (u, v), we add a new road from city u to city v with weight 1.
//Shortest Path Calculation:
//After processing each query, we calculate the shortest path from city 0 to city n - 1 using Dijkstra’s algorithm.
//A priority queue is used to ensure the algorithm processes nodes in increasing order of distance.
//Result Storage:
//After finding the shortest path for each query, the result is stored in an array.
//Complexity:
//For each query, the graph is updated, and Dijkstra’s algorithm runs.
//Time complexity for each query is O((E+V)logV), where E is the number of edges and V=n.
//Total complexity is O(Q⋅(E+V)logV), where Q is the number of queries.


//You are given an integer n and a 2D integer array queries.
//There are n cities numbered from 0 to n - 1. Initially, there is a unidirectional road from city i to
// city i + 1 for all 0 <= i < n - 1.
//queries[i] = [ui, vi] represents the addition of a new unidirectional road from city ui to city vi. After
// each query, you need to find the length of the shortest path from city 0 to city n - 1.
//Return an array answer where for each i in the range [0, queries.length - 1], answer[i] is the length of
// the shortest path from city 0 to city n - 1 after processing the first i + 1 queries.
//
//Example 1:
//Input: n = 5, queries = [[2,4],[0,2],[0,4]]
//Output: [3,2,1]
//Explanation:
//After the addition of the road from 2 to 4, the length of the shortest path from 0 to 4 is 3.
//After the addition of the road from 0 to 2, the length of the shortest path from 0 to 4 is 2.
//After the addition of the road from 0 to 4, the length of the shortest path from 0 to 4 is 1.
//
//Example 2:
//Input: n = 4, queries = [[0,3],[0,2]]
//Output: [1,1]
//Explanation:
//After the addition of the road from 0 to 3, the length of the shortest path from 0 to 3 is 1.
//After the addition of the road from 0 to 2, the length of the shortest path remains 1.

//Constraints:
//3 <= n <= 500
//1 <= queries.length <= 500
//queries[i].length == 2
//0 <= queries[i][0] < queries[i][1] < n
//1 < queries[i][1] - queries[i][0]
//There are no repeated roads among the queries.
