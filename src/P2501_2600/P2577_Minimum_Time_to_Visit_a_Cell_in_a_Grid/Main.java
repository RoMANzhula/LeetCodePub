package P2501_2600.P2577_Minimum_Time_to_Visit_a_Cell_in_a_Grid;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] grid1 = {{0, 1, 3, 2}, {5, 1, 2, 5}, {4, 3, 8, 6}};
        System.out.println(solution.minimumTime(grid1)); // Output: 7

        int[][] grid2 = {{0, 2, 4}, {3, 2, 1}, {1, 0, 4}};
        System.out.println(solution.minimumTime(grid2)); // Output: -1
    }

    public int minimumTime(int[][] grid) {
        // Check if the path is blocked right from the start
        if (grid[0][1] > 1 && grid[1][0] > 1) {
            return -1;
        }

        int m = grid.length; // Number of rows
        int n = grid[0].length; // Number of columns

        // Distance array to store the minimum time to reach each cell
        int[][] dist = new int[m][n];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dist[0][0] = 0; // Starting cell has a time of 0

        // Priority queue to process cells based on the shortest time
        PriorityQueue<Cell> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(new Cell(0, 0, 0)); // Add the starting cell

        // Movement directions: up, right, down, left
        int[] directions = {-1, 0, 1, 0, -1};

        while (!priorityQueue.isEmpty()) {
            Cell cell = priorityQueue.poll();
            int time = cell.time, x = cell.row, y = cell.col;

            // If we've reached the bottom-right corner, return the time
            if (x == m - 1 && y == n - 1) {
                return time;
            }

            // Check all adjacent cells
            for (int k = 0; k < 4; k++) {
                int nextX = x + directions[k];
                int nextY = y + directions[k + 1];

                // Validate the boundaries of the grid
                if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n) {
                    int nextTime = time + 1;

                    // Wait if necessary
                    if (nextTime < grid[nextX][nextY]) {
                        nextTime = grid[nextX][nextY] + (grid[nextX][nextY] - nextTime) % 2;
                    }

                    // If a shorter path to the neighboring cell is found
                    if (nextTime < dist[nextX][nextY]) {
                        dist[nextX][nextY] = nextTime;
                        priorityQueue.offer(new Cell(nextX, nextY, nextTime));
                    }
                }
            }
        }

        // If the bottom-right corner is unreachable
        return -1;
    }
}

class Cell implements Comparable<Cell> {
    int row, col, time;

    Cell(int row, int col, int time) {
        this.row = row;
        this.col = col;
        this.time = time;
    }

    @Override
    public int compareTo(Cell other) {
        return Integer.compare(this.time, other.time);
    }
}


//You are given a m x n matrix grid consisting of non-negative integers where grid[row][col] represents
// the minimum time required to be able to visit the cell (row, col), which means you can visit the cell (row, col)
// only when the time you visit it is greater than or equal to grid[row][col].
//You are standing in the top-left cell of the matrix in the 0th second, and you must move to
// any adjacent cell in the four directions: up, down, left, and right. Each move you make takes 1 second.
//Return the minimum time required in which you can visit the bottom-right cell of the matrix. If you
// cannot visit the bottom-right cell, then return -1.
//
//Example 1:
//Input: grid = [[0,1,3,2],[5,1,2,5],[4,3,8,6]]
//Output: 7
//Explanation: One of the paths that we can take is the following:
//- at t = 0, we are on the cell (0,0).
//- at t = 1, we move to the cell (0,1). It is possible because grid[0][1] <= 1.
//- at t = 2, we move to the cell (1,1). It is possible because grid[1][1] <= 2.
//- at t = 3, we move to the cell (1,2). It is possible because grid[1][2] <= 3.
//- at t = 4, we move to the cell (1,1). It is possible because grid[1][1] <= 4.
//- at t = 5, we move to the cell (1,2). It is possible because grid[1][2] <= 5.
//- at t = 6, we move to the cell (1,3). It is possible because grid[1][3] <= 6.
//- at t = 7, we move to the cell (2,3). It is possible because grid[2][3] <= 7.
//The final time is 7. It can be shown that it is the minimum time possible.

//Example 2:
//Input: grid = [[0,2,4],[3,2,1],[1,0,4]]
//Output: -1
//Explanation: There is no path from the top left to the bottom-right cell.
//
//Constraints:
//m == grid.length
//n == grid[i].length
//2 <= m, n <= 1000
//4 <= m * n <= 105
//0 <= grid[i][j] <= 105
//grid[0][0] == 0
