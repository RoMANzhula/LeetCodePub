package P3301_3400.P3342_Find_Minimum_Time_to_Reach_Last_Room_II;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] moveTime1 = {{0,4},{4,4}};
        System.out.println(solution.minTimeToReach(moveTime1)); // Output: 7

        int[][] moveTime2 = {{0,0,0,0},{0,0,0,0}};
        System.out.println(solution.minTimeToReach(moveTime2)); // Output: 6

        int[][] moveTime3 = {{0,1},{1,2}};
        System.out.println(solution.minTimeToReach(moveTime3)); // Output: 4
    }

    private static final int[][] kDirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int minTimeToReach(int[][] moveTime) {
        return dijkstra(moveTime, new int[]{0, 0},
                new int[]{moveTime.length - 1, moveTime[0].length - 1});
    }

    private int dijkstra(int[][] moveTime, int[] src, int[] dst) {
        int m = moveTime.length;
        int n = moveTime[0].length;
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        dist[src[0]][src[1]] = 0;
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
                (a, b) -> Integer.compare(a[0], b[0])
        );
        minHeap.offer(new int[]{dist[src[0]][src[1]], src[0], src[1]});

        while (!minHeap.isEmpty()) {
            int[] current = minHeap.poll();
            int d = current[0];
            int i = current[1];
            int j = current[2];

            if (i == dst[0] && j == dst[1]) {
                return d;
            }
            if (d > dist[i][j]) {
                continue;
            }
            for (int[] dir : kDirs) {
                int x = i + dir[0];
                int y = j + dir[1];
                if (x < 0 || x >= m || y < 0 || y >= n) {
                    continue;
                }
                int newDist = Math.max(moveTime[x][y], d) + ((i + j) % 2 + 1);
                if (newDist < dist[x][y]) {
                    dist[x][y] = newDist;
                    minHeap.offer(new int[]{newDist, x, y});
                }
            }
        }

        return -1;
    }

}

//Complexity:
// time - O(m * n log(m * n))
// space - O(m * n)


//There is a dungeon with n x m rooms arranged as a grid.
//You are given a 2D array moveTime of size n x m, where moveTime[i][j] represents the minimum time in seconds
// when you can start moving to that room. You start from the room (0, 0) at time t = 0 and can move to an
// adjacent room. Moving between adjacent rooms takes one second for one move and two seconds for the next,
// alternating between the two.
//Return the minimum time to reach the room (n - 1, m - 1).
//Two rooms are adjacent if they share a common wall, either horizontally or vertically.

//Example 1:
//Input: moveTime = [[0,4],[4,4]]
//Output: 7
//Explanation:
//The minimum time required is 7 seconds.
//At time t == 4, move from room (0, 0) to room (1, 0) in one second.
//At time t == 5, move from room (1, 0) to room (1, 1) in two seconds.

//Example 2:
//Input: moveTime = [[0,0,0,0],[0,0,0,0]]
//Output: 6
//Explanation:
//The minimum time required is 6 seconds.
//At time t == 0, move from room (0, 0) to room (1, 0) in one second.
//At time t == 1, move from room (1, 0) to room (1, 1) in two seconds.
//At time t == 3, move from room (1, 1) to room (1, 2) in one second.
//At time t == 4, move from room (1, 2) to room (1, 3) in two seconds.

//Example 3:
//Input: moveTime = [[0,1],[1,2]]
//Output: 4

//Constraints:
//2 <= n == moveTime.length <= 750
//2 <= m == moveTime[i].length <= 750
//0 <= moveTime[i][j] <= 109
