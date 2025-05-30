package P2301_2400.P2359_Find_Closest_Node_to_Given_Two_Nodes;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] edges1 = {2, 2, 3, -1};
        int node1 = 0, node2 = 1;
        System.out.println(solution.closestMeetingNode(edges1, node1, node2)); // Output: 2

        int[] edges2 = {1, 2, -1};
        node1 = 0; node2 = 2;
        System.out.println(solution.closestMeetingNode(edges2, node1, node2)); // Output: 2
    }

    public int closestMeetingNode(int[] edges, int node1, int node2) {
        int len = edges.length;
        int[] dist1 = getDistances(edges, node1);
        int[] dist2 = getDistances(edges, node2);

        int minDistance = Integer.MAX_VALUE;
        int answer = -1;

        for (int i = 0; i < len; i++) {
            if (dist1[i] != -1 && dist2[i] != -1) {
                int maxDist = Math.max(dist1[i], dist2[i]);

                if (maxDist < minDistance) {
                    minDistance = maxDist;
                    answer = i;
                }
            }
        }

        return answer;
    }

    private int[] getDistances(int[] edges, int start) {
        int len = edges.length;
        int[] dist = new int[len];
        Arrays.fill(dist, -1);
        boolean[] visited = new boolean[len];

        int current = start;
        int distance = 0;

        while (current != -1 && !visited[current]) {
            dist[current] = distance;
            visited[current] = true;
            current = edges[current];

            distance++;
        }

        return dist;
    }

}

//Complexity:
// time and space - O(n)


//You are given a directed graph of n nodes numbered from 0 to n - 1, where each node has at most one outgoing edge.
//The graph is represented with a given 0-indexed array edges of size n, indicating that there is a directed edge
// from node i to node edges[i]. If there is no outgoing edge from i, then edges[i] == -1.
//You are also given two integers node1 and node2.
//Return the index of the node that can be reached from both node1 and node2, such that the maximum between the
// distance from node1 to that node, and from node2 to that node is minimized. If there are multiple answers, return
// the node with the smallest index, and if no possible answer exists, return -1.
//Note that edges may contain cycles.

//Example 1:
//Input: edges = [2,2,3,-1], node1 = 0, node2 = 1
//Output: 2
//Explanation: The distance from node 0 to node 2 is 1, and the distance from node 1 to node 2 is 1.
//The maximum of those two distances is 1. It can be proven that we cannot get a node with a smaller maximum
// distance than 1, so we return node 2.

//Example 2:
//Input: edges = [1,2,-1], node1 = 0, node2 = 2
//Output: 2
//Explanation: The distance from node 0 to node 2 is 2, and the distance from node 2 to itself is 0.
//The maximum of those two distances is 2. It can be proven that we cannot get a node with a smaller maximum
// distance than 2, so we return node 2.

//Constraints:
//n == edges.length
//2 <= n <= 105
//-1 <= edges[i] < n
//edges[i] != i
//0 <= node1, node2 < n
