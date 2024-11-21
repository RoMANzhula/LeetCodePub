package P2201_2300.P2257_Count_Unguarded_Cells_in_the_Grid;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int m1 = 4, n1 = 6;
        int[][] guards1 = {{0, 0}, {1, 1}, {2, 3}};
        int[][] walls1 = {{0, 1}, {2, 2}, {1, 4}};
        System.out.println(solution.countUnguarded(m1, n1, guards1, walls1)); // Output: 7

        int m2 = 3, n2 = 3;
        int[][] guards2 = {{1, 1}};
        int[][] walls2 = {{0, 1}, {1, 0}, {2, 1}, {1, 2}};
        System.out.println(solution.countUnguarded(m2, n2, guards2, walls2)); // Output: 4
    }

    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        // Grid to track guarded cells
        boolean[][] guarded = new boolean[m][n];
        boolean[][] occupied = new boolean[m][n];

        // Mark guards and walls as occupied
        for (int[] guard : guards) {
            occupied[guard[0]][guard[1]] = true;
        }
        for (int[] wall : walls) {
            occupied[wall[0]][wall[1]] = true;
        }

        // Directions: up, right, down, left
        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        // Mark cells guarded by each guard
        for (int[] guard : guards) {
            for (int[] dir : directions) {
                int x = guard[0];
                int y = guard[1];

                while (true) {
                    x += dir[0];
                    y += dir[1];

                    // Stop if out of bounds or encounters an obstacle
                    if (x < 0 || x >= m || y < 0 || y >= n || occupied[x][y]) break;

                    guarded[x][y] = true;
                }
            }
        }

        // Count unguarded and unoccupied cells
        int unguardedCount = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!guarded[i][j] && !occupied[i][j]) {
                    unguardedCount++;
                }
            }
        }

        return unguardedCount;
    }
}

//Explanation
//Initialization:
//Use occupied to mark cells that are either a guard or a wall.
//Use guarded to track cells guarded by any guard.
//Marking Guarded Cells:
//For each guard, traverse in all four directions. If a cell is unoccupied and within bounds, mark it as guarded.
//Count Unguarded Cells:
//Simply count cells that are neither guarded nor occupied.

//Time Complexity:
//Marking guarded cells:
//O(k⋅max(m,n)), where k=guards.length, as each guard explores up to the size of the grid in four directions.
//Counting unguarded cells: O(m⋅n).
//Total:
//O(k⋅max(m,n)+m⋅n).
//Space Complexity:
//O(m⋅n) for guarded and occupied arrays.


//You are given two integers m and n representing a 0-indexed m x n grid. You are also given two 2D integer
// arrays guards and walls where guards[i] = [rowi, coli] and walls[j] = [rowj, colj] represent the positions of
// the ith guard and jth wall respectively.
//A guard can see every cell in the four cardinal directions (north, east, south, or west) starting from
// their position unless obstructed by a wall or another guard. A cell is guarded if there is at least
// one guard that can see it.
//Return the number of unoccupied cells that are not guarded.
//
//Example 1:
//Input: m = 4, n = 6, guards = [[0,0],[1,1],[2,3]], walls = [[0,1],[2,2],[1,4]]
//Output: 7
//Explanation: The guarded and unguarded cells are shown in red and green respectively in the above diagram.
//There are a total of 7 unguarded cells, so we return 7.

//Example 2:
//Input: m = 3, n = 3, guards = [[1,1]], walls = [[0,1],[1,0],[2,1],[1,2]]
//Output: 4
//Explanation: The unguarded cells are shown in green in the above diagram.
//There are a total of 4 unguarded cells, so we return 4.
//
//
//Constraints:
//1 <= m, n <= 105
//2 <= m * n <= 105
//1 <= guards.length, walls.length <= 5 * 104
//2 <= guards.length + walls.length <= m * n
//guards[i].length == walls[j].length == 2
//0 <= rowi, rowj < m
//0 <= coli, colj < n
//All the positions in guards and walls are unique.