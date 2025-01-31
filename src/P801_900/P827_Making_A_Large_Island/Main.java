package P801_900.P827_Making_A_Large_Island;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] grid1 = {{1, 0}, {0, 1}};
        System.out.println(solution.largestIsland(grid1)); // Output: 3

        int[][] grid2 = {{1, 1}, {1, 0}};
        System.out.println(solution.largestIsland(grid2)); // Output: 4

        int[][] grid3 = {{1, 1}, {1, 1}};
        System.out.println(solution.largestIsland(grid3)); // Output: 4

    }

    private static final int[][] DIRECTIONS = {{0,1}, {1,0}, {0,-1}, {-1,0}};

    public int largestIsland(int[][] grid) {
        int n = grid.length;
        int maxIsland = 0;
        int islandId = 2;
        Map<Integer, Integer> islandSizes = new HashMap<>();

        // find all islands and assign unique ids
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int size = dfs(grid, i, j, islandId);
                    islandSizes.put(islandId, size);
                    maxIsland = Math.max(maxIsland, size);
                    islandId++;
                }
            }
        }

        // check every 0 cell and calculate possible max island size
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    Set<Integer> neighborIslands = new HashSet<>();
                    for (int[] dir : DIRECTIONS) {
                        int ni = i + dir[0], nj = j + dir[1];
                        if (ni >= 0 && nj >= 0 && ni < n && nj < n && grid[ni][nj] > 1) {
                            neighborIslands.add(grid[ni][nj]);
                        }
                    }

                    int newSize = 1;
                    for (int id : neighborIslands) {
                        newSize += islandSizes.get(id);
                    }
                    maxIsland = Math.max(maxIsland, newSize);
                }
            }
        }

        return maxIsland;
    }

    private int dfs(int[][] grid, int i, int j, int islandId) {
        int n = grid.length;
        if (i < 0 || j < 0 || i >= n || j >= n || grid[i][j] != 1) {
            return 0;
        }
        grid[i][j] = islandId;
        int size = 1;
        for (int[] dir : DIRECTIONS) {
            size += dfs(grid, i + dir[0], j + dir[1], islandId);
        }
        return size;
    }
}

//Explanation:
//Identify Existing Islands:
//-Traverse the grid and find all islands using Depth-First Search (DFS).
//-Assign a unique ID to each island and store its size in a hash map (islandSizes).
//Find the Maximum Island Size:
//-Initialize a variable maxIsland to store the largest island found so far.
//-If the grid is all 1s, return n × n since we cannot change any 0.
//-Check Every 0 and Try to Expand:
//-For each 0, check the neighboring islands and calculate the potential size if this 0 were changed to 1.
//-Use a Set to track unique island IDs to avoid double counting.
//-Compute the new island size by summing up the sizes of connected islands.
//Return the Maximum Possible Island Size:
//-The answer is the maximum size found by either existing islands or by merging islands through a 0 -> 1 change.
//Time Complexity Analysis
//Finding all islands (DFS): O(n^2)
//Checking each 0 to compute max size: O(n^2)
//Total Complexity: O(n^2), which is efficient for n≤500.


//You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.
//Return the size of the largest island in grid after applying this operation.
//An island is a 4-directionally connected group of 1s.
//
//Example 1:
//Input: grid = [[1,0],[0,1]]
//Output: 3
//Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.

//Example 2:
//Input: grid = [[1,1],[1,0]]
//Output: 4
//Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.

//Example 3:
//Input: grid = [[1,1],[1,1]]
//Output: 4
//Explanation: Can't change any 0 to 1, only one island with area = 4.
//
//Constraints:
//n == grid.length
//n == grid[i].length
//1 <= n <= 500
//grid[i][j] is either 0 or 1.