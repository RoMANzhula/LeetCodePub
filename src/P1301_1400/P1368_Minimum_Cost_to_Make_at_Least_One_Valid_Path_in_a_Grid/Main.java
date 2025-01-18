package P1301_1400.P1368_Minimum_Cost_to_Make_at_Least_One_Valid_Path_in_a_Grid;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] grid1 = {
                {1, 1, 1, 1},
                {2, 2, 2, 2},
                {1, 1, 1, 1},
                {2, 2, 2, 2}
        };
        System.out.println(solution.minCost(grid1)); // Output: 3

        int[][] grid2 = {
                {1, 1, 3},
                {3, 2, 2},
                {1, 1, 4}
        };
        System.out.println(solution.minCost(grid2)); // Output: 0

        int[][] grid3 = {
                {1, 2},
                {4, 3}
        };
        System.out.println(solution.minCost(grid3)); // Output: 1
    }

    private static final int[][] DIRECTIONS = {
            {0, 1}, // Right
            {0, -1}, // Left
            {1, 0}, //Down
            {-1, 0} //Up
    };

    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Deque<int[]> deque = new ArrayDeque<>();
        int[][] cost = new int[m][n];

        for (int[] row : cost) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        deque.offer(new int[]{0, 0});
        cost[0][0] = 0;

        while (!deque.isEmpty()) {
            int[] current = deque.pollFirst();
            int x = current[0];
            int y = current[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + DIRECTIONS[d][0];
                int ny = y + DIRECTIONS[d][1];
                int newCost = cost[x][y] + (grid[x][y] == d + 1 ? 0 : 1);

                if (nx >= 0 && nx < m && ny >= 0 && ny < n && newCost < cost[nx][ny]) {
                    cost[nx][ny] = newCost;

                    if (grid[x][y] == d + 1) {
                        deque.offerFirst(new int[]{nx, ny});
                    } else {
                        deque.offerLast(new int[]{nx, ny});
                    }
                }
            }
        }

        return cost[m - 1][n - 1];
    }

}

//Explanation:
//Grid Representation and Directions:
//-Each cell in the grid has four possible directions (right, left, down, up), encoded as {1, 2, 3, 4}.
//-The DIRECTIONS array maps these directions to coordinate changes.
//Deque for 0-1 BFS:
//The deque is used to prioritize zero-cost moves (offerFirst) over moves with a cost of 1 (offerLast).
//Cost Array:
//Tracks the minimum cost to reach each cell. Initialize it with Integer.MAX_VALUE.
//Iterative BFS:
//-For each cell, evaluate all four possible directions. If the move follows the grid's direction, it's zero
// cost; otherwise, it incurs a cost of 1.
//-Update the cost array and add the new position to the deque accordingly.
//Output:
//After processing all possible paths, the cost to reach the bottom-right cell (cost[m-1][n-1]) is returned.
//Complexity:
//This approach ensures an efficient traversal of the grid with a time complexity of O(m×n), making it suitable
// for the given constraints.


//Given an m x n grid. Each cell of the grid has a sign pointing to the next cell you should visit if you are
// currently in this cell. The sign of grid[i][j] can be:
//1 which means go to the cell to the right. (i.e go from grid[i][j] to grid[i][j + 1])
//2 which means go to the cell to the left. (i.e go from grid[i][j] to grid[i][j - 1])
//3 which means go to the lower cell. (i.e go from grid[i][j] to grid[i + 1][j])
//4 which means go to the upper cell. (i.e go from grid[i][j] to grid[i - 1][j])
//Notice that there could be some signs on the cells of the grid that point outside the grid.
//You will initially start at the upper left cell (0, 0). A valid path in the grid is a path that starts from the
// upper left cell (0, 0) and ends at the bottom-right cell (m - 1, n - 1) following the signs on the grid. The
// valid path does not have to be the shortest.
//You can modify the sign on a cell with cost = 1. You can modify the sign on a cell one time only.
//Return the minimum cost to make the grid have at least one valid path.
//
//Example 1:
//Input: grid = [[1,1,1,1],[2,2,2,2],[1,1,1,1],[2,2,2,2]]
//Output: 3
//Explanation: You will start at point (0, 0).
//The path to (3, 3) is as follows. (0, 0) --> (0, 1) --> (0, 2) --> (0, 3) change the arrow to down
// with cost = 1 --> (1, 3) --> (1, 2) --> (1, 1) --> (1, 0) change the arrow to down
// with cost = 1 --> (2, 0) --> (2, 1) --> (2, 2) --> (2, 3) change the arrow to down
// with cost = 1 --> (3, 3)
//The total cost = 3.

//Example 2:
//Input: grid = [[1,1,3],[3,2,2],[1,1,4]]
//Output: 0
//Explanation: You can follow the path from (0, 0) to (2, 2).

//Example 3:
//Input: grid = [[1,2],[4,3]]
//Output: 1
//
//Constraints:
//m == grid.length
//n == grid[i].length
//1 <= m, n <= 100
//1 <= grid[i][j] <= 4
