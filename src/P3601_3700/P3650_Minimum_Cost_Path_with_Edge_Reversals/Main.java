package P3601_3700.P3650_Minimum_Cost_Path_with_Edge_Reversals;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int n1 = 4;
        int[][] edges1 = {
                {0,1,3},
                {3,1,1},
                {2,3,4},
                {0,2,2}
        };
        System.out.println(solution.minCost(n1, edges1)); // 5

        int n2 = 4;
        int[][] edges2 = {
                {0,2,1},
                {2,1,1},
                {1,3,1},
                {2,3,3}
        };
        System.out.println(solution.minCost(n2, edges2)); // 3
    }

    public int minCost(int n, int[][] edges) {
        class Edge {
            int to, cost;
            Edge(int t, int c) {
                to = t;
                cost = c;
            }
        }

        class State {
            int node, used, dist;
            State(int node, int used, int dist) {
                this.node = node;
                this.used = used;
                this.dist = dist;
            }
        }

        List<Edge>[] graph = new ArrayList[n];
        List<Edge>[] incoming = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            incoming[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            graph[u].add(new Edge(v, w));
            incoming[v].add(new Edge(u, w));
        }

        int INF = Integer.MAX_VALUE / 4;
        int[][] dist = new int[n][2];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
        }

        PriorityQueue<State> pq =
                new PriorityQueue<>(Comparator.comparingInt(a -> a.dist));

        dist[0][0] = 0;
        pq.offer(new State(0, 0, 0));

        while (!pq.isEmpty()) {
            State cur = pq.poll();
            int u = cur.node;
            int used = cur.used;
            int d = cur.dist;

            if (d != dist[u][used]) continue;

            // normal outgoing edges
            for (Edge e : graph[u]) {
                int nd = d + e.cost;
                if (nd < dist[e.to][0]) {
                    dist[e.to][0] = nd;
                    pq.offer(new State(e.to, 0, nd));
                }
            }

            // reverse ONE incoming edge (if switch unused)
            if (used == 0) {
                for (Edge e : incoming[u]) {
                    int nd = d + e.cost * 2;
                    if (nd < dist[e.to][0]) {
                        dist[e.to][0] = nd;
                        pq.offer(new State(e.to, 0, nd));
                    }
                }
            }
        }

        int ans = Math.min(dist[n - 1][0], dist[n - 1][1]);

        return ans >= INF ? -1 : ans;
    }

}


//Complexity:
// time - O((n + m) log n)
// space - O(n + m)


//You are given a directed, weighted graph with n nodes labeled from 0 to n - 1, and an array edges where
// edges[i] = [ui, vi, wi] represents a directed edge from node ui to node vi with cost wi.
//Each node ui has a switch that can be used at most once: when you arrive at ui and have not yet used its switch, you
// may activate it on one of its incoming edges vi → ui reverse that edge to ui → vi and immediately traverse it.
//The reversal is only valid for that single move, and using a reversed edge costs 2 * wi.
//Return the minimum total cost to travel from node 0 to node n - 1. If it is not possible, return -1.

//Example 1:
//Input: n = 4, edges = [[0,1,3],[3,1,1],[2,3,4],[0,2,2]]
//Output: 5
//Explanation:
//Use the path 0 → 1 (cost 3).
//At node 1 reverse the original edge 3 → 1 into 1 → 3 and traverse it at cost 2 * 1 = 2.
//Total cost is 3 + 2 = 5.

//Example 2:
//Input: n = 4, edges = [[0,2,1],[2,1,1],[1,3,1],[2,3,3]]
//Output: 3
//Explanation:
//No reversal is needed. Take the path 0 → 2 (cost 1), then 2 → 1 (cost 1), then 1 → 3 (cost 1).
//Total cost is 1 + 1 + 1 = 3.

//Constraints:
//2 <= n <= 5 * 104
//1 <= edges.length <= 105
//edges[i] = [ui, vi, wi]
//0 <= ui, vi <= n - 1
//1 <= wi <= 1000
