package P3601_3700.P3620_Network_Recovery_Pathways;

import java.util.*;

public class Main {


    public static void main(String[] args) {
        Main solution = new Main();

        int[][] edges1 = {
                {0, 1, 5},
                {1, 3, 10},
                {0, 2, 3},
                {2, 3, 4}
        };

        boolean[] online1 = {true, true, true, true};

        System.out.println(solution.findMaxPathScore(edges1, online1, 10)); // 3


        int[][] edges2 = {
                {0, 1, 7},
                {1, 4, 5},
                {0, 2, 6},
                {2, 3, 6},
                {3, 4, 2},
                {2, 4, 6}
        };

        boolean[] online2 = {true, true, true, false, true};

        System.out.println(solution.findMaxPathScore(edges2, online2, 12)); // 6
    }

    public int findMaxPathScore(int[][] edges, boolean[] online, int k) {
        int n = online.length;

        // collect distinct edge costs
        TreeSet<Integer> set = new TreeSet<>();
        for (int[] e : edges) {
            set.add(e[2]);
        }

        if (set.isEmpty()) {
            return -1;
        }

        int[] costs = new int[set.size()];
        int idx = 0;
        for (int c : set) {
            costs[idx++] = c;
        }

        int left = 0;
        int right = costs.length - 1;
        int ans = -1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (can(costs[mid], edges, online, k)) {
                ans = costs[mid];
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return ans;
    }

    private boolean can(int limit, int[][] edges, boolean[] online, long k) {

        int n = online.length;

        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();

        int[] indegree = new int[n];

        for (int[] e : edges) {

            int u = e[0];
            int v = e[1];
            int w = e[2];

            if (w < limit)
                continue;

            if (u != 0 && u != n - 1 && !online[u])
                continue;

            if (v != 0 && v != n - 1 && !online[v])
                continue;

            graph[u].add(new int[]{v, w});
            indegree[v]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0)
                queue.offer(i);
        }

        long INF = Long.MAX_VALUE / 4;

        long[] dist = new long[n];
        Arrays.fill(dist, INF);
        dist[0] = 0;

        while (!queue.isEmpty()) {

            int u = queue.poll();

            if (dist[u] != INF) {
                for (int[] next : graph[u]) {

                    int v = next[0];
                    int w = next[1];

                    if (dist[v] > dist[u] + w) {
                        dist[v] = dist[u] + w;
                    }
                }
            }

            for (int[] next : graph[u]) {
                int v = next[0];

                indegree[v]--;

                if (indegree[v] == 0)
                    queue.offer(v);
            }
        }

        return dist[n - 1] <= k;
    }

}

// Complexity:
// time - O((n+m) log m)
// space - O(n + m)
//n = number of nodes
//m = number of edges


//You are given a directed acyclic graph of n nodes numbered from 0 to n−1. This is
// represented by a 2D array edges of length m, where edges[i] = [ui, vi, cost i] indicates a one‑way communication from
// node ui to node vi with a recovery cost of cost i.
//Some nodes may be offline. You are given a boolean array online where online[i] = true means node i is online.
// Nodes 0 and n−1 are always online.
//A path from 0 to n-1 is valid if:
//All intermediate nodes on the path are online.
//The total recovery cost of all edges on the path does not exceed k.
//For each valid path, define its score as the minimum edge‑cost along that path.
//Return the maximum path score (i.e., the largest minimum-edge cost) among all valid paths. If no valid path exists,
// return -1.

//Example 1:
//Input: edges = [[0,1,5],[1,3,10],[0,2,3],[2,3,4]], online = [true,true,true,true], k = 10
//Output: 3
//Explanation:
//The graph has two possible routes from node 0 to node 3:
//Path 0 → 1 → 3
//Total cost = 5 + 10 = 15, which exceeds k (15 > 10), so this path is invalid.
//Path 0 → 2 → 3
//Total cost = 3 + 4 = 7 <= k, so this path is valid.
//The minimum edge‐cost along this path is min(3, 4) = 3.
//There are no other valid paths. Hence, the maximum among all valid path‐scores is 3.

//Example 2:
//Input: edges = [[0,1,7],[1,4,5],[0,2,6],[2,3,6],[3,4,2],[2,4,6]], online = [true,true,true,false,true], k = 12
//Output: 6
//Explanation:
//Node 3 is offline, so any path passing through 3 is invalid.
//Consider the remaining routes from 0 to 4:
//Path 0 → 1 → 4
//Total cost = 7 + 5 = 12 <= k, so this path is valid.
//The minimum edge‐cost along this path is min(7, 5) = 5.
//Path 0 → 2 → 3 → 4
//Node 3 is offline, so this path is invalid regardless of cost.
//Path 0 → 2 → 4
//Total cost = 6 + 6 = 12 <= k, so this path is valid.
//The minimum edge‐cost along this path is min(6, 6) = 6.
//Among the two valid paths, their scores are 5 and 6. Therefore, the answer is 6.

//Constraints:
//n == online.length
//2 <= n <= 5 * 104
//0 <= m == edges.length <= min(105, n * (n - 1) / 2)
//edges[i] = [ui, vi, costi]
//0 <= ui, vi < n
//ui != vi
//0 <= costi <= 109
//0 <= k <= 5 * 1013
//online[i] is either true or false, and both online[0] and online[n − 1] are true.
//The given graph is a directed acyclic graph.
