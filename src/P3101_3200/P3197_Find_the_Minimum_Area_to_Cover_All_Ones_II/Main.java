package P3101_3200.P3197_Find_the_Minimum_Area_to_Cover_All_Ones_II;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] g1 = {{1,0,1},{1,1,1}};
        System.out.println(solution.minimumSum(g1)); // 5

        int[][] g2 = {{1,0,1,0},{0,1,0,1}};
        System.out.println(solution.minimumSum(g2)); // 5
    }

    public int minimumSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int ans = m * n;

        // case 1: top + (bottom split into left + right)
        for (int i = 0; i < m; ++i) {
            int top = minimumArea(grid, 0, i, 0, n - 1);
            for (int j = 0; j < n; ++j) {
                ans = Math.min(ans,
                        top
                                + minimumArea(grid, i + 1, m - 1, 0, j)   // bottom-left
                                + minimumArea(grid, i + 1, m - 1, j + 1, n - 1)); // bottom-right
            }
        }

        // case 2: bottom + (top split into left + right)
        for (int i = 0; i < m; ++i) {
            int bottom = minimumArea(grid, i, m - 1, 0, n - 1);
            for (int j = 0; j < n; ++j) {
                ans = Math.min(ans,
                        bottom
                                + minimumArea(grid, 0, i - 1, 0, j)    // top-left
                                + minimumArea(grid, 0, i - 1, j + 1, n - 1)); // top-right
            }
        }

        // case 3: left + (right split into top + bottom)
        for (int j = 0; j < n; ++j) {
            int left = minimumArea(grid, 0, m - 1, 0, j);
            for (int i = 0; i < m; ++i) {
                ans = Math.min(ans,
                        left
                                + minimumArea(grid, 0, i, j + 1, n - 1)   // right-top
                                + minimumArea(grid, i + 1, m - 1, j + 1, n - 1)); // right-bottom
            }
        }

        // case 4: right + (left split into top + bottom)
        for (int j = 0; j < n; ++j) {
            int right = minimumArea(grid, 0, m - 1, j, n - 1);
            for (int i = 0; i < m; ++i) {
                ans = Math.min(ans,
                        right
                                + minimumArea(grid, 0, i, 0, j - 1)     // left-top
                                + minimumArea(grid, i + 1, m - 1, 0, j - 1)); // left-bottom
            }
        }

        // case 5: 3 horizontal strips
        for (int i1 = 0; i1 < m; ++i1) {
            for (int i2 = i1 + 1; i2 < m; ++i2) {
                ans = Math.min(ans,
                        minimumArea(grid, 0, i1, 0, n - 1) +
                                minimumArea(grid, i1 + 1, i2, 0, n - 1) +
                                minimumArea(grid, i2 + 1, m - 1, 0, n - 1));
            }
        }

        // case 6: 3 vertical strips
        for (int j1 = 0; j1 < n; ++j1) {
            for (int j2 = j1 + 1; j2 < n; ++j2) {
                ans = Math.min(ans,
                        minimumArea(grid, 0, m - 1, 0, j1) +
                                minimumArea(grid, 0, m - 1, j1 + 1, j2) +
                                minimumArea(grid, 0, m - 1, j2 + 1, n - 1));
            }
        }

        return ans;
    }

    private int minimumArea(int[][] grid, int si, int ei, int sj, int ej) {
        int m = grid.length;
        int n = grid[0].length;

        int x1 = Integer.MAX_VALUE, y1 = Integer.MAX_VALUE;
        int x2 = -1, y2 = -1;

        for (int i = Math.max(0, si); i <= Math.min(ei, m - 1); i++) {
            for (int j = Math.max(0, sj); j <= Math.min(ej, n - 1); j++) {
                if (grid[i][j] == 1) {
                    x1 = Math.min(x1, i);
                    y1 = Math.min(y1, j);
                    x2 = Math.max(x2, i);
                    y2 = Math.max(y2, j);
                }
            }
        }

        return (x2 == -1) ? 0 : (x2 - x1 + 1) * (y2 - y1 + 1);
    }

}


//You are given a 2D binary array grid. You need to find 3 non-overlapping rectangles having non-zero areas
// with horizontal and vertical sides such that all the 1's in grid lie inside these rectangles.
//Return the minimum possible sum of the area of these rectangles.
//Note that the rectangles are allowed to touch.

//Example 1:
//Input: grid = [[1,0,1],[1,1,1]]
//Output: 5
//Explanation:
//The 1's at (0, 0) and (1, 0) are covered by a rectangle of area 2.
//The 1's at (0, 2) and (1, 2) are covered by a rectangle of area 2.
//The 1 at (1, 1) is covered by a rectangle of area 1.

//Example 2:
//Input: grid = [[1,0,1,0],[0,1,0,1]]
//Output: 5
//Explanation:
//The 1's at (0, 0) and (0, 2) are covered by a rectangle of area 3.
//The 1 at (1, 1) is covered by a rectangle of area 1.
//The 1 at (1, 3) is covered by a rectangle of area 1.

//Constraints:
//1 <= grid.length, grid[i].length <= 30
//grid[i][j] is either 0 or 1.
//The input is generated such that there are at least three 1's in grid.
