package P801_900.P885_Spiral_Matrix_lll;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int rows = 5;
        int cols = 6;
        int rStart = 1;
        int cStart = 4;

        int[][] result = solution.spiralMatrixIII(rows, cols, rStart, cStart);

        // Print the result
        for (int[] cell : result) {
            System.out.println("[" + cell[0] + "," + cell[1] + "]");
        }
    }

    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        int[][] result = new int[rows * cols][2];
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // right, down, left, up
        int dirIndex = 0; // Start by moving right
        int steps = 1; // The number of steps we need to take in the current direction
        int idx = 0; // Index to track the result array

        result[idx++] = new int[]{rStart, cStart};

        while (idx < rows * cols) {
            for (int i = 0; i < 2; i++) { // Two directions at each "layer" (e.g., right then down, left then up)
                for (int j = 0; j < steps; j++) {
                    rStart += directions[dirIndex][0];
                    cStart += directions[dirIndex][1];

                    // Check if the new position is within bounds
                    if (rStart >= 0 && rStart < rows && cStart >= 0 && cStart < cols) {
                        result[idx++] = new int[]{rStart, cStart};
                    }
                }
                dirIndex = (dirIndex + 1) % 4; // Change direction
            }
            steps++; // Increase steps after a full round (two directions)
        }

        return result;
    }
}

//Explanation:
//Direction Array: The directions array defines the movement in four possible directions (right, down, left, up).
//Index Tracking: dirIndex keeps track of the current direction.
//Steps: The variable steps controls how many steps to take in the current direction before changing direction.
//Two Iterations Per Layer: For each layer of the spiral, we need to move in two directions (e.g., right and down),
// hence the inner loop runs twice.
//Result Construction: As we move, we only record positions that are within the grid boundaries. The spiral expands
// by incrementing the number of steps after each full cycle of two directions.


//You start at the cell (rStart, cStart) of an rows x cols grid facing east. The northwest corner is at the first
// row and column in the grid, and the southeast corner is at the last row and column.
//You will walk in a clockwise spiral shape to visit every position in this grid. Whenever you move outside the
// grid's boundary, we continue our walk outside the grid (but may return to the grid boundary later.). Eventually, we
// reach all rows * cols spaces of the grid.
//Return an array of coordinates representing the positions of the grid in the order you visited them.
//
//Example 1:
//Input: rows = 1, cols = 4, rStart = 0, cStart = 0
//Output: [[0,0],[0,1],[0,2],[0,3]]

//Example 2:
//Input: rows = 5, cols = 6, rStart = 1, cStart = 4
//Output: [[1,4],[1,5],[2,5],[2,4],[2,3],[1,3],[0,3],[0,4],[0,5],[3,5],[3,4],[3,3],[3,2],[2,2],[1,2],[0,2],[4,5],
// [4,4],[4,3],[4,2],[4,1],[3,1],[2,1],[1,1],[0,1],[4,0],[3,0],[2,0],[1,0],[0,0]]
//
//Constraints:
//1 <= rows, cols <= 100
//0 <= rStart < rows
//0 <= cStart < cols