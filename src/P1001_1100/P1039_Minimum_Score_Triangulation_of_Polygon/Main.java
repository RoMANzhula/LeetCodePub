package P1001_1100.P1039_Minimum_Score_Triangulation_of_Polygon;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] values1 = {1, 2, 3};
        System.out.println(solution.minScoreTriangulation(values1)); // 6

        int[] values2 = {3, 7, 4, 5};
        System.out.println(solution.minScoreTriangulation(values2)); // 144

        int[] values3 = {1, 3, 1, 4, 1, 5};
        System.out.println(solution.minScoreTriangulation(values3)); // 13
    }

    public int minScoreTriangulation(int[] values) {
        int len = values.length;
        int[][] dp = new int[len][len];

        // dp[i][j] = min score to triangulate polygon between vertex i and j
        for (int n = 2; n < len; n++) {
            for (int i = 0; i + n < len; i++) {
                int j = i + n;
                dp[i][j] = Integer.MAX_VALUE;

                // try all possible middle points k
                for (int k = i + 1; k < j; k++) {
                    int cost = dp[i][k] + dp[k][j] + values[i] * values[k] * values[j];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }

        return dp[0][len - 1];
    }

}

//Complexity:
// time - O(n^3)
// space - O(n^2)


//You have a convex n-sided polygon where each vertex has an integer value. You are given an integer array values
// where values[i] is the value of the ith vertex in clockwise order.
//Polygon triangulation is a process where you divide a polygon into a set of triangles and the vertices of
// each triangle must also be vertices of the original polygon. Note that no other shapes other than triangles
// are allowed in the division. This process will result in n - 2 triangles.
//You will triangulate the polygon. For each triangle, the weight of that triangle is the product of the values at
// its vertices. The total score of the triangulation is the sum of these weights over all n - 2 triangles.
//Return the minimum possible score that you can achieve with some triangulation of the polygon.

//Example 1:
//Input: values = [1,2,3]
//Output: 6
//Explanation: The polygon is already triangulated, and the score of the only triangle is 6.

//Example 2:
//Input: values = [3,7,4,5]
//Output: 144
//Explanation: There are two triangulations, with possible scores: 3*7*5 + 4*5*7 = 245, or 3*4*5 + 3*4*7 = 144.
//The minimum score is 144.

//Example 3:
//Input: values = [1,3,1,4,1,5]
//Output: 13
//Explanation: The minimum score triangulation is 1*1*3 + 1*1*4 + 1*1*5 + 1*1*1 = 13.

//Constraints:
//n == values.length
//3 <= n <= 50
//1 <= values[i] <= 100
