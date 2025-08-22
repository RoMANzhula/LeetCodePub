package P3101_3200.P3195_Find_the_Minimum_Area_to_Cover_All_Ones_I;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] grid1 = {{0,1,0},{1,0,1}};
        System.out.println(solution.minimumArea(grid1)); // Output: 6

        int[][] grid2 = {{1,0},{0,0}};
        System.out.println(solution.minimumArea(grid2)); // Output: 1
    }

    public int minimumArea(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int minRow = rows, maxRow = -1;
        int minCol = cols, maxCol = -1;

        // traverse the grid to find boundaries of 1's
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    minRow = Math.min(minRow, i);
                    maxRow = Math.max(maxRow, i);
                    minCol = Math.min(minCol, j);
                    maxCol = Math.max(maxCol, j);
                }
            }
        }

        // calculate rectangle area
        return (maxRow - minRow + 1) * (maxCol - minCol + 1);
    }

}

//Complexity:
// time - O(m * n)
// space - O(1)


//You are given a 2D binary array grid. Find a rectangle with horizontal and vertical sides with the smallest area,
// such that all the 1's in grid lie inside this rectangle.
//Return the minimum possible area of the rectangle.

//Example 1:
//Input: grid = [[0,1,0],[1,0,1]]
//Output: 6
//Explanation:
//The smallest rectangle has a height of 2 and a width of 3, so it has an area of 2 * 3 = 6.
//Example 2:
//Input: grid = [[1,0],[0,0]]
//Output: 1
//Explanation:
//The smallest rectangle has both height and width 1, so its area is 1 * 1 = 1.

//Constraints:
//1 <= grid.length, grid[i].length <= 1000
//grid[i][j] is either 0 or 1.
//The input is generated such that there is at least one 1 in grid.
