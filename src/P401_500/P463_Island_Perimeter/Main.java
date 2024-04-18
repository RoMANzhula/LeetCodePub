package P401_500.P463_Island_Perimeter;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[][] grid1 = {{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}};
        System.out.println("Output for grid1: " + solution.islandPerimeter(grid1)); // Output: 16

        int[][] grid2 = {{1}};
        System.out.println("Output for grid2: " + solution.islandPerimeter(grid2)); // Output: 4

        int[][] grid3 = {{1,0}};
        System.out.println("Output for grid3: " + solution.islandPerimeter(grid3)); // Output: 4
    }

    public int islandPerimeter(int[][] grid) {
        int perimeter = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    perimeter += 4; //initialize the perimetr assuming all sides are land

                    //check neighboring cells
                    if (i > 0 && grid[i - 1][j] == 1) { //subtracting 2 if top neighbor is land
                        perimeter -= 2;
                    }
                    if (j > 0 && grid[i][j - 1] == 1) { //subtracting 2 if left neighbor is land
                        perimeter -= 2;
                    }
                }
            }
        }

        return perimeter; //bingo
    }
}

//You are given row x col grid representing a map where grid[i][j] = 1 represents land and
// grid[i][j] = 0 represents water.
//Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and
// there is exactly one island (i.e., one or more connected land cells).
//
//The island doesn't have "lakes", meaning the water inside isn't connected to the water around the island. One
// cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine
// he perimeter of the island.
//
//Example 1:
//Input: grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
//Output: 16
//Explanation: The perimeter is the 16 yellow stripes in the image above.

//Example 2:
//Input: grid = [[1]]
//Output: 4

//Example 3:
//Input: grid = [[1,0]]
//Output: 4
//
//Constraints:
//row == grid.length
//col == grid[i].length
//1 <= row, col <= 100
//grid[i][j] is 0 or 1.
//There is exactly one island in grid.