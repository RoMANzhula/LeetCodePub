package P1501_1600.P1536_Minimum_Swaps_to_Arrange_a_Binary_Grid;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] grid1 = {
                {0, 0, 1},
                {1, 1, 0},
                {1, 0, 0}
        };

        int[][] grid2 = {
                {0, 1, 1, 0},
                {0, 1, 1, 0},
                {0, 1, 1, 0},
                {0, 1, 1, 0}
        };

        int[][] grid3 = {
                {1, 0, 0},
                {1, 1, 0},
                {1, 1, 1}
        };

        System.out.println(solution.minSwaps(grid1)); // 3
        System.out.println(solution.minSwaps(grid2)); // -1
        System.out.println(solution.minSwaps(grid3)); // 0
    }

    public int minSwaps(int[][] grid) {
        int n = grid.length;
        int[] trailingZeros = new int[n];

        // count trailing zeros for each row
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 0) {
                    count++;
                } else {
                    break;
                }
            }
            trailingZeros[i] = count;
        }

        int swaps = 0;

        // greedy row placement
        for (int i = 0; i < n; i++) {
            int requiredZeros = n - i - 1;
            int j = i;

            //find row with enough trailing zeros
            while (j < n && trailingZeros[j] < requiredZeros) {
                j++;
            }

            if (j == n) {
                return -1; // impossible
            }

            // bring row j to position i using adjacent swaps
            while (j > i) {
                int temp = trailingZeros[j];
                trailingZeros[j] = trailingZeros[j - 1];
                trailingZeros[j - 1] = temp;

                swaps++;
                j--;
            }
        }

        return swaps;
    }

}

//Complexity:
// time - O(n^2)
// space - O(n)


//Given an n x n binary grid, in one step you can choose two adjacent rows of the grid and swap them.
//A grid is said to be valid if all the cells above the main diagonal are zeros.
//Return the minimum number of steps needed to make the grid valid, or -1 if the grid cannot be valid.
//The main diagonal of a grid is the diagonal that starts at cell (1, 1) and ends at cell (n, n).

//Example 1:
//Input: grid = [[0,0,1],[1,1,0],[1,0,0]]
//Output: 3

//Example 2:
//Input: grid = [[0,1,1,0],[0,1,1,0],[0,1,1,0],[0,1,1,0]]
//Output: -1
//Explanation: All rows are similar, swaps have no effect on the grid.

//Example 3:
//Input: grid = [[1,0,0],[1,1,0],[1,1,1]]
//Output: 0

//Constraints:
//n == grid.length == grid[i].length
//1 <= n <= 200
//grid[i][j] is either 0 or 1
