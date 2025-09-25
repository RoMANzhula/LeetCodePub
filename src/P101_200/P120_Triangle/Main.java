package P101_200.P120_Triangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        List<List<Integer>> triangle1 = new ArrayList<>();
        triangle1.add(Arrays.asList(2));
        triangle1.add(Arrays.asList(3, 4));
        triangle1.add(Arrays.asList(6, 5, 7));
        triangle1.add(Arrays.asList(4, 1, 8, 3));

        System.out.println("Minimum path sum1: " + solution.minimumTotal(triangle1)); // 11

        List<List<Integer>> triangle2 = new ArrayList<>();
        triangle2.add(Arrays.asList(-10));

        System.out.println("Minimum path sum2: " + solution.minimumTotal(triangle2)); // -10
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        // copy the last row as our initial DP array
        int n = triangle.size();
        int[] dp = new int[n];

        // initialize with the bottom row
        for (int i = 0; i < n; i++) {
            dp[i] = triangle.get(n - 1).get(i);
        }

        // bottom-up calculation
        for (int row = n - 2; row >= 0; row--) {
            for (int col = 0; col <= row; col++) {
                dp[col] = triangle.get(row).get(col) + Math.min(dp[col], dp[col + 1]);
            }
        }

        return dp[0];
    }

}


//Given a triangle array, return the minimum path sum from top to bottom.
//For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on
// the current row, you may move to either index i or index i + 1 on the next row.

//Example 1:
//Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
//Output: 11
//Explanation: The triangle looks like:
//   2
//  3 4
// 6 5 7
//4 1 8 3
//The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).

//Example 2:
//Input: triangle = [[-10]]
//Output: -10

//Constraints:
//1 <= triangle.length <= 200
//triangle[0].length == 1
//triangle[i].length == triangle[i - 1].length + 1
//-104 <= triangle[i][j] <= 104
