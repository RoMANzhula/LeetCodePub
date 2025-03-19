package P1601_1700.P1631_Path_With_Minimum_Effort;

public class Main {

    public int minimumEffortPath(int[][] heights) {
        int rows = heights.length;
        int columns = heights[0].length;
        int left = 0;
        int right = 1000000; //max possible effort

        while (left < right) {
            int mid = left + (right - left) / 2;
            boolean[][] visited = new boolean[rows][columns];

            if (dfs(0, 0, heights, visited, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean dfs(int x, int y, int[][] heights, boolean[][] visited, int mid) {
        int rows = heights.length;
        int columns = heights[0].length;

        if (x == rows - 1 && y == columns - 1) {
            return true;
        }

        visited[x][y] = true;
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for (int[] dir : directions) {
            int nx = x + dir[0];
            int ny = y + dir[1];

            if (nx >= 0 && nx < rows && ny >= 0 && ny < columns && !visited[nx][ny]) {
                int diff = Math.abs(heights[nx][ny] - heights[x][y]);

                if (diff <= mid && dfs(nx, ny, heights, visited, mid)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Main solution = new Main();
        int[][] heights1 = {{1, 2, 2}, {3, 8, 2}, {5, 3, 5}};
        System.out.println(solution.minimumEffortPath(heights1)); // Output: 2

        int[][] heights2 = {{1, 2, 3}, {3, 8, 4}, {5, 3, 5}};
        System.out.println(solution.minimumEffortPath(heights2)); // Output: 1

        int[][] heights3 = {{1, 2, 1, 1, 1}, {1, 2, 1, 2, 1}, {1, 2, 1, 2, 1}, {1, 2, 1, 2, 1}, {1, 1, 1, 2, 1}};
        System.out.println(solution.minimumEffortPath(heights3)); // Output: 0
    }

}


//You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, where
// heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), and
// you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left,
// or right, and you wish to find a route that requires the minimum effort.
//A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
//Return the minimum effort required to travel from the top-left cell to the bottom-right cell.

//Example 1:
//Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
//Output: 2
//Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
//This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.

//Example 2:
//Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
//Output: 1
//Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which
// is better than route [1,3,5,3,5].

//Example 3:
//Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
//Output: 0
//Explanation: This route does not require any effort.

//Constraints:
//rows == heights.length
//columns == heights[i].length
//1 <= rows, columns <= 100
//1 <= heights[i][j] <= 106
