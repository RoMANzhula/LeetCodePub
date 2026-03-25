package P3501_3600.P3546_Equal_Sum_Grid_Partition_I;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] grid1 = {{1, 4}, {2, 3}};
        int[][] grid2 = {{1, 3}, {2, 4}};

        System.out.println(solution.canPartitionGrid(grid1)); // true
        System.out.println(solution.canPartitionGrid(grid2)); // false
    }

    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        long totalSum = 0;

        // calculate total sum
        for (int[] row : grid) {
            for (int val : row) {
                totalSum += val;
            }
        }

        // ff total sum is odd -> impossible
        if (totalSum % 2 != 0) return false;

        long target = totalSum / 2;

        // check horizontal cuts
        long currentSum = 0;

        for (int i = 0; i < m - 1; i++) { // must leave at least one row below
            for (int j = 0; j < n; j++) {
                currentSum += grid[i][j];
            }

            if (currentSum == target) return true;
        }

        //check vertical cuts
        currentSum = 0;

        for (int j = 0; j < n - 1; j++) { // must leave at least one column right
            for (int i = 0; i < m; i++) {
                currentSum += grid[i][j];
            }

            if (currentSum == target) return true;
        }

        return false;
    }

}

//Complexity:
// time - O(m * n)
// space - O(1)


//You are given an m x n matrix grid of positive integers. Your task is to determine if it is possible to make either
// one horizontal or one vertical cut on the grid such that:
//Each of the two resulting sections formed by the cut is non-empty.
//The sum of the elements in both sections is equal.
//Return true if such a partition exists; otherwise return false.

//Example 1:
//Input: grid = [[1,4],[2,3]]
//Output: true
//Explanation:
//A horizontal cut between row 0 and row 1 results in two non-empty sections, each with a sum of 5. Thus, the answer
// is true.

//Example 2:
//Input: grid = [[1,3],[2,4]]
//Output: false
//Explanation:
//No horizontal or vertical cut results in two non-empty sections with equal sums. Thus, the answer is false.

//Constraints:
//1 <= m == grid.length <= 105
//1 <= n == grid[i].length <= 105
//2 <= m * n <= 105
//1 <= grid[i][j] <= 105
