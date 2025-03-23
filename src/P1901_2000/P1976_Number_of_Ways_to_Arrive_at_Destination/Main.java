package P1901_2000.P1976_Number_of_Ways_to_Arrive_at_Destination;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int n1 = 7;
        int[][] roads1 = {
                {0,6,7}, {0,1,2}, {1,2,3}, {1,3,3}, {6,3,3}, {3,5,1},
                {6,5,1}, {2,5,1}, {0,4,5}, {4,6,2}
        };
        System.out.println("Output: " + solution.countPaths(n1, roads1)); // output: 4

        int n2 = 2;
        int[][] roads2 = {
                {1,0,10}
        };
        System.out.println("Output: " + solution.countPaths(n2, roads2)); // output: 1
    }

    public int countPaths(int n, int[][] roads) {
        final int MOD = 1_000_000_007;

        // adjacency list representation of the graph
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] road : roads) {
            int u = road[0], v = road[1], time = road[2];
            graph.get(u).add(new int[]{v, time});
            graph.get(v).add(new int[]{u, time});
        }

        // min-heap to store {time, node}
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        pq.offer(new long[]{0, 0}); // {time, node}

        // distance array to store the shortest time to each node
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;

        //ways array to count the number of shortest paths to each node
        int[] ways = new int[n];
        ways[0] = 1;

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            long currTime = curr[0];
            int node = (int) curr[1];

            if (currTime > dist[node]) continue;

            for (int[] neighbor : graph.get(node)) {
                int nextNode = neighbor[0];
                long newTime = currTime + neighbor[1];

                if (newTime < dist[nextNode]) {
                    dist[nextNode] = newTime;
                    ways[nextNode] = ways[node];
                    pq.offer(new long[]{newTime, nextNode});
                } else if (newTime == dist[nextNode]) {
                    ways[nextNode] = (ways[nextNode] + ways[node]) % MOD;
                }
            }
        }

        return ways[n - 1];
    }

}

//Explanation:
//Graph Construction:
//-Convert the roads array into an adjacency list.
//Dijkstra’s Algorithm with Path Counting:
//-Use a priority queue (min-heap) to explore the shortest paths first.
//-Track dist (shortest time to reach each node).
//-Track ways (number of ways to reach each node using the shortest time).
//-When a new shortest path to a node is found, update dist and reset ways count.
//-If another shortest path to a node is found, add to ways[node].
//Return the count of shortest paths to n-1
//-This gives the number of ways to reach the last intersection in minimum time.
//Time Complexity Analysis
//Building the Graph: O(n+e) where e is the number of edges.
//Dijkstra’s Algorithm: O((n+e)log n) using a priority queue.
//Overall Complexity: O((n+e)log n), which is efficient for n <= 200.


//You are in a city that consists of n intersections numbered from 0 to n - 1 with bi-directional roads between
// some intersections. The inputs are generated such that you can reach any intersection from any other
// intersection and that there is at most one road between any two intersections.
//You are given an integer n and a 2D integer array roads where roads[i] = [ui, vi, timei] means that there is a
// road between intersections ui and vi that takes timei minutes to travel. You want to know in how many
// ways you can travel from intersection 0 to intersection n - 1 in the shortest amount of time.
//Return the number of ways you can arrive at your destination in the shortest amount of time. Since the answer
// may be large, return it modulo 109 + 7.

//Example 1:
//Input: n = 7, roads = [[0,6,7],[0,1,2],[1,2,3],[1,3,3],[6,3,3],[3,5,1],[6,5,1],[2,5,1],[0,4,5],[4,6,2]]
//Output: 4
//Explanation: The shortest amount of time it takes to go from intersection 0 to intersection 6 is 7 minutes.
//The four ways to get there in 7 minutes are:
//- 0 ➝ 6
//- 0 ➝ 4 ➝ 6
//- 0 ➝ 1 ➝ 2 ➝ 5 ➝ 6
//- 0 ➝ 1 ➝ 3 ➝ 5 ➝ 6

//Example 2:
//Input: n = 2, roads = [[1,0,10]]
//Output: 1
//Explanation: There is only one way to go from intersection 0 to intersection 1, and it takes 10 minutes.

//Constraints:
//1 <= n <= 200
//n - 1 <= roads.length <= n * (n - 1) / 2
//roads[i].length == 3
//0 <= ui, vi <= n - 1
//1 <= timei <= 109
//ui != vi
//There is at most one road connecting any two intersections.
//You can reach any intersection from any other intersection.
