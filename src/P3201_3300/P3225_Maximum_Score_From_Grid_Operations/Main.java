package P3201_3300.P3225_Maximum_Score_From_Grid_Operations;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] grid1 = {
                {0,0,0,0,0},
                {0,0,3,0,0},
                {0,1,0,0,0},
                {5,0,0,3,0},
                {0,0,0,0,2}
        };

        int[][] grid2 = {
                {10,9,0,0,15},
                {7,1,0,8,0},
                {5,20,0,11,0},
                {0,0,0,1,2},
                {8,12,1,10,3}
        };

        System.out.println(solution.maximumScore(grid1)); //  11
        System.out.println(solution.maximumScore(grid2)); //  94
    }

    public long maximumScore(int[][] grid) {
        int n = grid.length;

        // prefix[j][i] = sum of first i elements in column j
        long[][] prefix = new long[n][n + 1];

        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                prefix[j][i + 1] = prefix[j][i] + grid[i][j];
            }
        }

        long[] prevPick = new long[n + 1];
        long[] prevSkip = new long[n + 1];

        for (int j = 1; j < n; j++) {
            long[] currPick = new long[n + 1];
            long[] currSkip = new long[n + 1];

            for (int curr = 0; curr <= n; curr++) {
                for (int prev = 0; prev <= n; prev++) {

                    if (curr > prev) {
                        // if current column goes deeper
                        long score = prefix[j - 1][curr] - prefix[j - 1][prev];

                        currPick[curr] = Math.max(
                                currPick[curr],
                                prevSkip[prev] + score
                        );

                        currSkip[curr] = Math.max(
                                currSkip[curr],
                                prevSkip[prev] + score
                        );

                    } else {
                        // if previous column goes deeper
                        long score = prefix[j][prev] - prefix[j][curr];

                        currPick[curr] = Math.max(
                                currPick[curr],
                                prevPick[prev] + score
                        );

                        currSkip[curr] = Math.max(
                                currSkip[curr],
                                prevPick[prev]
                        );
                    }
                }
            }

            prevPick = currPick;
            prevSkip = currSkip;
        }

        long res = 0;
        for (long val : prevPick) {
            res = Math.max(res, val);
        }

        return res;
    }

}

//Complexity:
// time - O(n^3)
// space - O(n^2)


// You are given a 2D matrix grid of size n x n. Initially, all cells of the grid are colored
// white. In one operation, you can select any cell of indices (i, j), and color black all the cells of the jth
// column starting from the top row down to the ith row.
//The grid score is the sum of all grid[i][j] such that cell (i, j) is white and it has a horizontally adjacent
// black cell.

//Return the maximum score that can be achieved after some number of operations.

//Example 1:
//Input: grid = [[0,0,0,0,0],[0,0,3,0,0],[0,1,0,0,0],[5,0,0,3,0],[0,0,0,0,2]]
//Output: 11
//Explanation:
//In the first operation, we color all cells in column 1 down to row 3, and in the second operation, we color all
// cells in column 4 down to the last row. The score of the resulting grid is grid[3][0] + grid[1][2] + grid[3][3]
// which is equal to 11.

//Example 2:
//Input: grid = [[10,9,0,0,15],[7,1,0,8,0],[5,20,0,11,0],[0,0,0,1,2],[8,12,1,10,3]]
//Output: 94
//Explanation:
//We perform operations on 1, 2, and 3 down to rows 1, 4, and 0, respectively. The score of the resulting grid is
// grid[0][0] + grid[1][0] + grid[2][1] + grid[4][1] + grid[1][3] + grid[2][3] + grid[3][3] + grid[4][3] + grid[0][4]
// which is equal to 94.

//Constraints:
//1 <= n == grid.length <= 100
//n == grid[i].length
//0 <= grid[i][j] <= 109
