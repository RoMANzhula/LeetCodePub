package P3301_3400.P3341_Find_Minimum_Time_to_Reach_Last_Room_I;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] moveTime1 = {{0,4},{4,4}};
        System.out.println(solution.minTimeToReach(moveTime1)); // Output: 6

        int[][] moveTime2 = {{0,0,0},{0,0,0}};
        System.out.println(solution.minTimeToReach(moveTime2)); // Output: 3

        int[][] moveTime3 = {{0,1},{1,2}};
        System.out.println(solution.minTimeToReach(moveTime3)); // Output: 3
    }

    public int minTimeToReach(int[][] moveTime) {
        return dijkstra(moveTime, 0, 0, moveTime.length - 1, moveTime[0].length - 1);
    }

    private int dijkstra(int[][] moveTime, int srcRow, int srcCol, int dstRow, int dstCol) {
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int m = moveTime.length;
        int n = moveTime[0].length;
        int[][] dist = new int[m][n];
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);
        dist[srcRow][srcCol] = 0;

        PriorityQueue<Cell> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.time, b.time));
        minHeap.offer(new Cell(0, srcRow, srcCol));

        while (!minHeap.isEmpty()) {
            Cell curr = minHeap.poll();
            int d = curr.time;
            int i = curr.row;
            int j = curr.col;

            if (i == dstRow && j == dstCol) return d;
            if (d > dist[i][j]) continue;

            for (int[] dir : dirs) {
                int x = i + dir[0];
                int y = j + dir[1];
                if (x < 0 || x >= m || y < 0 || y >= n) continue;

                int newDist = Math.max(moveTime[x][y], d) + 1;
                if (newDist < dist[x][y]) {
                    dist[x][y] = newDist;
                    minHeap.offer(new Cell(newDist, x, y));
                }
            }
        }

        return -1;
    }

    private static class Cell {
        int time, row, col;

        Cell(int time, int row, int col) {
            this.time = time;
            this.row = row;
            this.col = col;
        }
    }

}

//Explanation:
//We use Dijkstra's algorithm to find the minimum time to reach the bottom-right room in the grid. Each move
// to an adjacent cell takes 1 second, but you can only enter a room at or after its moveTime[i][j]. So for
// each neighbor, we compute: newTime = max(currentTime, moveTime[neighbor]) + 1
//Complexity:
// time: O(m * n * log(m * n))
// space: O(m * n) (rows * columns)


//There is a dungeon with n x m rooms arranged as a grid.
//You are given a 2D array moveTime of size n x m, where moveTime[i][j] represents the minimum time in seconds
// when you can start moving to that room. You start from the room (0, 0) at time t = 0 and can move to
// an adjacent room. Moving between adjacent rooms takes exactly one second.
//Return the minimum time to reach the room (n - 1, m - 1).
//Two rooms are adjacent if they share a common wall, either horizontally or vertically.

//Example 1:
//Input: moveTime = [[0,4],[4,4]]
//Output: 6
//Explanation:
//The minimum time required is 6 seconds.
//At time t == 4, move from room (0, 0) to room (1, 0) in one second.
//At time t == 5, move from room (1, 0) to room (1, 1) in one second.

//Example 2:
//Input: moveTime = [[0,0,0],[0,0,0]]
//Output: 3
//Explanation:
//The minimum time required is 3 seconds.
//At time t == 0, move from room (0, 0) to room (1, 0) in one second.
//At time t == 1, move from room (1, 0) to room (1, 1) in one second.
//At time t == 2, move from room (1, 1) to room (1, 2) in one second.

//Example 3:
//Input: moveTime = [[0,1],[1,2]]
//Output: 3

//Constraints:
//2 <= n == moveTime.length <= 50
//2 <= m == moveTime[i].length <= 50
//0 <= moveTime[i][j] <= 109
