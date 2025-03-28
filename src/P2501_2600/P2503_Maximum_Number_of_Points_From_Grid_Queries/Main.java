package P2501_2600.P2503_Maximum_Number_of_Points_From_Grid_Queries;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] grid1 = {{1, 2, 3}, {2, 5, 7}, {3, 5, 1}};
        int[] queries1 = {5, 6, 2};
        System.out.println(Arrays.toString(solution.maxPoints(grid1, queries1))); // Output: [5, 8, 1]

        int[][] grid2 = {{5, 2, 1}, {1, 1, 2}};
        int[] queries2 = {3};
        System.out.println(Arrays.toString(solution.maxPoints(grid2, queries2))); // Output: [0]
    }

    public int[] maxPoints(int[][] grid, int[] queries) {
        int m = grid.length, n = grid[0].length;
        int k = queries.length;
        int[] answer = new int[k];

        //sort queries while maintaining their original indices
        int[][] sortedQueries = new int[k][2];
        for (int i = 0; i < k; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }
        Arrays.sort(sortedQueries, Comparator.comparingInt(a -> a[0]));

        // min-heap to expand cells in increasing order of value
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        minHeap.offer(new int[]{grid[0][0], 0, 0}); // {value, row, col}

        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true;

        int points = 0, index = 0;
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (index < k) {
            int query = sortedQueries[index][0];

            // expand the grid while the current min value is less than the query
            while (!minHeap.isEmpty() && minHeap.peek()[0] < query) {
                int[] cell = minHeap.poll();
                int val = cell[0], r = cell[1], c = cell[2];

                points++; // count visited cells

                // explore 4 directions
                for (int[] dir : directions) {
                    int nr = r + dir[0], nc = c + dir[1];
                    if (nr >= 0 && nr < m && nc >= 0 && nc < n && !visited[nr][nc]) {
                        visited[nr][nc] = true;
                        minHeap.offer(new int[]{grid[nr][nc], nr, nc});
                    }
                }
            }

            // store result for all queries with the same value
            while (index < k && sortedQueries[index][0] == query) {
                answer[sortedQueries[index][1]] = points;
                index++;
            }
        }

        return answer;
    }

}

//Explanation of the Code:
//Sort the queries to process them in increasing order.
//Use a priority queue (min-heap) to always expand the smallest value cell first.
//Start from (0,0) and expand to adjacent cells when their values are less than the current query.
//For each query, count the number of newly visited cells and store the result.
//Since queries are sorted, we avoid reprocessing already counted cells.
//Time Complexity: O(k log k + (m * n)log(m * n))
//Space Complexity: O(m * n + k)


//You are given an m x n integer matrix grid and an array queries of size k.
//Find an array answer of size k such that for each integer queries[i] you start in the top left cell of the
// matrix and repeat the following process:
//If queries[i] is strictly greater than the value of the current cell that you are in, then you get one point if
// it is your first time visiting this cell, and you can move to any adjacent cell in
// all 4 directions: up, down, left, and right.
//Otherwise, you do not get any points, and you end this process.
//After the process, answer[i] is the maximum number of points you can get. Note that for each query you are
// allowed to visit the same cell multiple times.
//Return the resulting array answer.

//Example 1:
//Input: grid = [[1,2,3],[2,5,7],[3,5,1]], queries = [5,6,2]
//Output: [5,8,1]
//Explanation: The diagrams above show which cells we visit to get points for each query.

//Example 2:
//Input: grid = [[5,2,1],[1,1,2]], queries = [3]
//Output: [0]
//Explanation: We can not get any points because the value of the top left cell is already greater than or equal to 3.

//Constraints:
//m == grid.length
//n == grid[i].length
//2 <= m, n <= 1000
//4 <= m * n <= 105
//k == queries.length
//1 <= k <= 104
//1 <= grid[i][j], queries[i] <= 106
