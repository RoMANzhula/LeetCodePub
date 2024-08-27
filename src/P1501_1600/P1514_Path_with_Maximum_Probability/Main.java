package P1501_1600.P1514_Path_with_Maximum_Probability;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

// Example 1
        int n1 = 3;
        int[][] edges1 = {{0, 1}, {1, 2}, {0, 2}};
        double[] succProb1 = {0.5, 0.5, 0.2};
        int start1 = 0;
        int end1 = 2;
        System.out.println("Example 1: " + solution.maxProbability(n1, edges1, succProb1, start1, end1)); // Output: 0.25

        // Example 2
        int n2 = 3;
        int[][] edges2 = {{0, 1}, {1, 2}, {0, 2}};
        double[] succProb2 = {0.5, 0.5, 0.3};
        int start2 = 0;
        int end2 = 2;
        System.out.println("Example 2: " + solution.maxProbability(n2, edges2, succProb2, start2, end2)); // Output: 0.3

        // Example 3
        int n3 = 3;
        int[][] edges3 = {{0, 1}};
        double[] succProb3 = {0.5};
        int start3 = 0;
        int end3 = 2;
        System.out.println("Example 3: " + solution.maxProbability(n3, edges3, succProb3, start3, end3)); // Output: 0.0

    }

    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        // Create adjacency list
        List<List<Pair>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            double prob = succProb[i];
            graph.get(u).add(new Pair(v, prob));
            graph.get(v).add(new Pair(u, prob));
        }

        // Probability array to keep track of max probability to each node
        double[] prob = new double[n];
        prob[start_node] = 1.0;

        // Max-Heap to store the current node and the probability of reaching that node
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> Double.compare(b.probability, a.probability));
        pq.add(new Pair(start_node, 1.0));

        // Dijkstra-like algorithm
        while (!pq.isEmpty()) {
            Pair current = pq.poll();
            int node = current.node;
            double currProb = current.probability;

            if (node == end_node) {
                return currProb;
            }

            for (Pair neighbor : graph.get(node)) {
                int nextNode = neighbor.node;
                double edgeProb = neighbor.probability;
                double newProb = currProb * edgeProb;

                if (newProb > prob[nextNode]) {
                    prob[nextNode] = newProb;
                    pq.add(new Pair(nextNode, newProb));
                }
            }
        }

        return 0.0; // If no path found
    }

    // Pair class to store node and probability
    static class Pair {
        int node;
        double probability;

        Pair(int node, double probability) {
            this.node = node;
            this.probability = probability;
        }
    }
}


//You are given an undirected weighted graph of n nodes (0-indexed), represented by an edge list where
// edges[i] = [a, b] is an undirected edge connecting the nodes a and b with a probability of success of
// traversing that edge succProb[i].
//Given two nodes start and end, find the path with the maximum probability of success to go from start to end and
// return its success probability.
//If there is no path from start to end, return 0. Your answer will be accepted if it differs from the correct
// answer by at most 1e-5.
//
//Example 1:
//Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
//Output: 0.25000
//Explanation: There are two paths from start to end, one having a probability of success = 0.2 and the
// other has 0.5 * 0.5 = 0.25.

//Example 2:
//Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0, end = 2
//Output: 0.30000

//Example 3:
//Input: n = 3, edges = [[0,1]], succProb = [0.5], start = 0, end = 2
//Output: 0.00000
//Explanation: There is no path between 0 and 2.
//
//Constraints:
//2 <= n <= 10^4
//0 <= start, end < n
//start != end
//0 <= a, b < n
//a != b
//0 <= succProb.length == edges.length <= 2*10^4
//0 <= succProb[i] <= 1
//There is at most one edge between every two nodes.