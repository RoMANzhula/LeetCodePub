package P401_500.P407_Trapping_Rain_Water_II;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] heightMap1 = {
                {1, 4, 3, 1, 3, 2},
                {3, 2, 1, 3, 2, 4},
                {2, 3, 3, 2, 3, 1}
        };

        int[][] heightMap2 = {
                {3, 3, 3, 3, 3},
                {3, 2, 2, 2, 3},
                {3, 2, 1, 2, 3},
                {3, 2, 2, 2, 3},
                {3, 3, 3, 3, 3}
        };

        System.out.println(solution.trapRainWater(heightMap1)); // Output: 4
        System.out.println(solution.trapRainWater(heightMap2)); // Output: 10
    }

    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length;
        int n = heightMap[0].length;

        if (m < 3 || n < 3) return 0; // no space to trap water

        PriorityQueue<Cell> minHeap = new PriorityQueue<>(Comparator.comparingInt(cell -> cell.height));
        boolean[][] visited = new boolean[m][n];

        // add all the boundary cells to the heap
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    minHeap.offer(new Cell(i, j, heightMap[i][j]));
                    visited[i][j] = true;
                }
            }
        }

        int totalWater = 0;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        while (!minHeap.isEmpty()) {
            Cell cell = minHeap.poll();

            for (int[] dir : directions) {
                int newRow = cell.row + dir[0];
                int newCol = cell.col + dir[1];

                // check bounds and if already visited
                if (newRow >= 0 && newRow < m && newCol >= 0&& newCol < n && !visited[newRow][newCol]) {
                    visited[newRow][newCol] = true;

                    // calculate water trapped
                    totalWater += Math.max(0, cell.height - heightMap[newRow][newCol]);

                    // push the current cell into the heap with the maximum height
                    minHeap.offer(new Cell(newRow, newCol, Math.max(heightMap[newRow][newCol], cell.height)));
                }
            }
        }

        return totalWater;
    }

    static class Cell {
        int row;
        int col;
        int height;

        public Cell(int row, int col, int height) {
            this.row = row;
            this.col = col;
            this.height = height;
        }
    }
}

//Explanation of the Code
//Boundary Cells Initialization:
//All cells on the boundary are added to a min-heap. These cells determine the initial height limits of the trapped water.
//Priority Queue:
//The queue processes the cell with the minimum height. This ensures water trapping calculations are done from the
// lowest boundary upward.
//Visited Array:
//Keeps track of whether a cell has already been processed to avoid redundant calculations.
//Water Calculation:
//For each cell, water trapped is calculated as the difference between the current cell's height and the
// neighbor's height (if the difference is positive).
//Updating Heights:
//If a neighbor cell is lower than the current height, the height of water is updated to maintain the trapping boundary.
//Complexity:
//This approach ensures efficiency and correctness with a time complexity of O(m×nlog(m×n)), where m and n are the
// dimensions of the grid.


//Given an m x n integer matrix heightMap representing the height of each unit cell in a 2D elevation map,
// return the volume of water it can trap after raining.
//
//Example 1:
//Input: heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
//Output: 4
//Explanation: After the rain, water is trapped between the blocks.
//We have two small ponds 1 and 3 units trapped.
//The total volume of water trapped is 4.

//Example 2:
//Input: heightMap = [[3,3,3,3,3],[3,2,2,2,3],[3,2,1,2,3],[3,2,2,2,3],[3,3,3,3,3]]
//Output: 10
//
//Constraints:
//m == heightMap.length
//n == heightMap[i].length
//1 <= m, n <= 200
//0 <= heightMap[i][j] <= 2 * 104
