package P1501_1600.P1584_Min_Cost_to_Connect_All_Points;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();
        int[][] points = {{0,0},{2,2},{3,10},{5,2},{7,0}};

        System.out.println(solution.minCostConnectPoints(points));
    }
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        List<Edge> edges = new ArrayList<>();

        // Calculate Manhattan distances and create edges
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int cost = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                edges.add(new Edge(i, j, cost));
            }
        }

        // Sort edges by cost
        Collections.sort(edges, (a, b) -> Integer.compare(a.cost, b.cost));

        int minCost = 0;
        int[] parent = new int[n];
        Arrays.fill(parent, -1); // Initialize parent array

        for (Edge edge : edges) {
            int root1 = find(parent, edge.from);
            int root2 = find(parent, edge.to);

            if (root1 != root2) {
                minCost += edge.cost;
                parent[root1] = root2; // Merge components
            }
        }

        return minCost;
    }

    private int find(int[] parent, int i) {
        if (parent[i] == -1) {
            return i;
        }
        return find(parent, parent[i]);
    }

}

class Edge {
    int from, to, cost;

    public Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }
}


//You are given an array points representing integer coordinates of some points on a 2D-plane,
// where points[i] = [xi, yi].
//The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between
// them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.
//Return the minimum cost to make all points connected. All points are connected if there is exactly one simple
// path between any two points.

//Example 1:
//Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
//Output: 20
//Explanation:
//We can connect the points as shown above to get the minimum cost of 20.
//Notice that there is a unique path between every pair of points.

//Example 2:
//Input: points = [[3,12],[-2,5],[-4,1]]
//Output: 18

//Constraints:
//1 <= points.length <= 1000
//-106 <= xi, yi <= 106
//All pairs (xi, yi) are distinct.