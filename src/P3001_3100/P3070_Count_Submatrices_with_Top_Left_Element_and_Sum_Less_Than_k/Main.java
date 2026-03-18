package P3001_3100.P3070_Count_Submatrices_with_Top_Left_Element_and_Sum_Less_Than_k;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] grid1 = {{7,6,3},{6,6,1}};
        int k1 = 18;
        System.out.println(solution.countSubmatrices(grid1, k1)); // output: 4

        int[][] grid2 = {{7,2,9},{1,5,0},{2,6,6}};
        int k2 = 20;
        System.out.println(solution.countSubmatrices(grid2, k2)); // output: 6
    }

    public int countSubmatrices(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] prefix = new int[m][n];
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                prefix[i][j] = grid[i][j];

                if (i > 0) {
                    prefix[i][j] += prefix[i - 1][j];
                }
                if (j > 0) {
                    prefix[i][j] += prefix[i][j - 1];
                }
                if (i > 0 && j > 0) {
                    prefix[i][j] -= prefix[i - 1][j - 1];
                }

                if (prefix[i][j] <= k) {
                    count++;
                }
            }
        }

        return count;
    }

}

//Complexity:
// time and space - O(n * m)


//You are given a 0-indexed integer matrix grid and an integer k.
//Return the number of submatrices that contain the top-left element of the grid, and have a sum less
// than or equal to k.

//Example 1:
//Input: grid = [[7,6,3],[6,6,1]], k = 18
//Output: 4
//Explanation: There are only 4 submatrices, shown in the image above, that contain the top-left element of grid, and
// have a sum less than or equal to 18.

//Example 2:
//Input: grid = [[7,2,9],[1,5,0],[2,6,6]], k = 20
//Output: 6
//Explanation: There are only 6 submatrices, shown in the image above, that contain the top-left element of grid, and
// have a sum less than or equal to 20.

//Constraints:
//m == grid.length
//n == grid[i].length
//1 <= n, m <= 1000
//0 <= grid[i][j] <= 1000
//1 <= k <= 109
