package P1201_1300.P1267_Count_Servers_that_Communicate;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] grid1 = {{1, 0}, {0, 1}};
        System.out.println("Output: " + solution.countServers(grid1)); // Expected: 0

        int[][] grid2 = {{1, 0}, {1, 1}};
        System.out.println("Output: " + solution.countServers(grid2)); // Expected: 3

        int[][] grid3 = {{1, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
        System.out.println("Output: " + solution.countServers(grid3)); // Expected: 4
    }

    public int countServers(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // arrays to store the count of servers in each row and column
        int[] rowCount = new int[m];
        int[] columnCount = new int[n];

        // count servers in each row and column
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    rowCount[i]++;
                    columnCount[j]++;
                }
            }
        }

        // count servers than can communicate
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && (rowCount[i] > 1 || columnCount[j] > 1)) {
                    count++;
                }
            }
        }

        return count;
    }
}

//Explanation
//Counting Servers:
//-rowCount[i] keeps track of the number of servers in row i.
//-colCount[j] keeps track of the number of servers in column j.
//Communicating Servers:
//A server at (i, j) can communicate if rowCount[i] > 1 (other servers in the same row) or
// colCount[j] > 1 (other servers in the same column).
//Efficiency:
//The algorithm iterates over the grid twice, resulting in O(m×n) time complexity.
//Space complexity is O(m+n) due to the arrays rowCount and colCount.


//You are given a map of a server center, represented as a m * n integer matrix grid, where 1 means that on that
// cell there is a server and 0 means that it is no server. Two servers are said to communicate if they are on
// the same row or on the same column.
//Return the number of servers that communicate with any other server.
//
//Example 1:
//Input: grid = [[1,0],[0,1]]
//Output: 0
//Explanation: No servers can communicate with others.

//Example 2:
//Input: grid = [[1,0],[1,1]]
//Output: 3
//Explanation: All three servers can communicate with at least one other server.

//Example 3:
//Input: grid = [[1,1,0,0],[0,0,1,0],[0,0,1,0],[0,0,0,1]]
//Output: 4
//Explanation: The two servers in the first row can communicate with each other. The two servers in the third
// column can communicate with each other. The server at right bottom corner can't communicate with any other server.
//
//Constraints:
//m == grid.length
//n == grid[i].length
//1 <= m <= 250
//1 <= n <= 250
//grid[i][j] == 0 or 1