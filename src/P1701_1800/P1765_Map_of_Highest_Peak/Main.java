package P1701_1800.P1765_Map_of_Highest_Peak;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[][] isWater1 = {
                {0, 1},
                {0, 0}
        };
        System.out.println("Example 1 Output:");
        printMatrix(solution.highestPeak(isWater1));

        int[][] isWater2 = {
                {0, 0, 1},
                {1, 0, 0},
                {0, 0, 0}
        };
        System.out.println("Example 2 Output:");
        printMatrix(solution.highestPeak(isWater2));
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    public int[][] highestPeak(int[][] isWater) {
        int m = isWater.length;
        int n = isWater[0].length;

        int[][] height = new int[m][n];
        for (int[] row : height) {
            Arrays.fill(row, -1); // initialize all cells as unvisited
        }

        Queue<int[]> queue = new LinkedList<>();

        // initialize queue with all water cells and set their height to 0
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isWater[i][j] == 1) {
                    queue.add(new int[]{i, j});
                    height[i][j] = 0;
                }
            }
        }

        // directions for moving north, east, south, and west
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // perform BFS
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int currentHeight = height[cell[0]][cell[1]];

            for (int[] dir : directions) {
                int newRow = cell[0] + dir[0];
                int newCol = cell[1] + dir[1];

                // check if the new cell is within bounds and unvisited
                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && height[newRow][newCol] == -1) {
                    height[newRow][newCol] = currentHeight + 1;
                    queue.add(new int[]{newRow, newCol});
                }
            }
        }

        return height;
    }
}

//Explanation of the Code:
//Initialization:
//-Create a height matrix initialized to -1 to mark unvisited cells.
//-Add all water cells (with height 0) to the BFS queue.
//BFS Traversal:
//-Use BFS starting from water cells. Each step increments the height of adjacent unvisited cells by 1.
//-Ensure that you only process cells within bounds and not already visited.
//Directional Movement:
//Use a directions array to facilitate moving north, east, south, and west.
//Output:
//The BFS guarantees that the height difference between adjacent cells is at most 1.
//Complexity:
// Time complexity: O(m*n)
// Space complexity: O(m*n)


//You are given an integer matrix isWater of size m x n that represents a map of land and water cells.
//If isWater[i][j] == 0, cell (i, j) is a land cell.
//If isWater[i][j] == 1, cell (i, j) is a water cell.
//You must assign each cell a height in a way that follows these rules:
//The height of each cell must be non-negative.
//If the cell is a water cell, its height must be 0.
//Any two adjacent cells must have an absolute height difference of at most 1. A cell is adjacent to
// another cell if the former is directly north, east, south, or west of the latter (i.e., their sides are touching).
//Find an assignment of heights such that the maximum height in the matrix is maximized.
//Return an integer matrix height of size m x n where height[i][j] is cell (i, j)'s height. If there are
// multiple solutions, return any of them.
//
//Example 1:
//Input: isWater = [[0,1],[0,0]]
//Output: [[1,0],[2,1]]
//Explanation: The image shows the assigned heights of each cell.
//The blue cell is the water cell, and the green cells are the land cells.

//Example 2:
//Input: isWater = [[0,0,1],[1,0,0],[0,0,0]]
//Output: [[1,1,0],[0,1,1],[1,2,2]]
//Explanation: A height of 2 is the maximum possible height of any assignment.
//Any height assignment that has a maximum height of 2 while still meeting the rules will also be accepted.
//
//Constraints:
//m == isWater.length
//n == isWater[i].length
//1 <= m, n <= 1000
//isWater[i][j] is 0 or 1.
//There is at least one water cell.
