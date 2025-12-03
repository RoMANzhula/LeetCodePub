package P3601_3700.P3625_Count_Number_of_Trapezoids_II;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] points1 = {
                {-3, 2}, {3, 0}, {2, 3}, {3, 2}, {2, -3}
        };

        int[][] points2 = {
                {0, 0}, {1, 0}, {0, 1}, {2, 1}
        };

        int[][] points3 = {
                {-32,12},{-32,-94},{-32,-15},{-30,88}
        };

        System.out.println(solution.countTrapezoids(points1)); // Expected: 2
        System.out.println(solution.countTrapezoids(points2)); // Expected: 1
        System.out.println(solution.countTrapezoids(points3)); // Expected: 0
    }

    public int countTrapezoids(int[][] points) {
        int n = points.length;

        Map<Double, Map<Double, Integer>> cnt1 = new HashMap<>(n * n);
        Map<Integer, Map<Double, Integer>> cnt2 = new HashMap<>(n * n);

        for (int i = 0; i < n; ++i) {
            int x1 = points[i][0], y1 = points[i][1];

            for (int j = 0; j < i; ++j) {
                int x2 = points[j][0], y2 = points[j][1];
                int dx = x2 - x1, dy = y2 - y1;

                // slope
                double k = (dx == 0 ? Double.MAX_VALUE : (double) dy / dx);

                // line "intercept" depending on vertical or not
                double b = (dx == 0 ? x1 : (double) (y1 * dx - x1 * dy) / dx);

                // normalize -0.0 to +0.0
                if (k == -0.0) k = 0.0;
                if (b == -0.0) b = 0.0;

                cnt1.computeIfAbsent(k, _ -> new HashMap<>()).merge(b, 1, Integer::sum);

                // midpoint encoding
                int p = (x1 + x2 + 2000) * 4000 + (y1 + y2 + 2000);
                cnt2.computeIfAbsent(p, _ -> new HashMap<>()).merge(k, 1, Integer::sum);
            }
        }

        int ans = 0;

        // ccount pairs of parallel lines (add)
        for (Map<Double, Integer> e : cnt1.values()) {
            int s = 0;
            for (int t : e.values()) {
                ans += s * t;
                s += t;
            }
        }

        // remove pairs from same midpoint (subtract)
        for (Map<Double, Integer> e : cnt2.values()) {
            int s = 0;
            for (int t : e.values()) {
                ans -= s * t;
                s += t;
            }
        }

        return ans;
    }

}

//Complexity:
// time and space - O(n^2)


//You are given a 2D integer array points where points[i] = [xi, yi] represents the coordinates of the ith point on
// the Cartesian plane.
//Return the number of unique trapezoids that can be formed by choosing any four distinct points from points.
//A trapezoid is a convex quadrilateral with at least one pair of parallel sides. Two lines are parallel if and only
// if they have the same slope.

//Example 1:
//Input: points = [[-3,2],[3,0],[2,3],[3,2],[2,-3]]
//Output: 2
//Explanation:
//There are two distinct ways to pick four points that form a trapezoid:
//The points [-3,2], [2,3], [3,2], [2,-3] form one trapezoid.
//The points [2,3], [3,2], [3,0], [2,-3] form another trapezoid.

//Example 2:
//Input: points = [[0,0],[1,0],[0,1],[2,1]]
//Output: 1
//Explanation:
//There is only one trapezoid which can be formed.

//Constraints:
//4 <= points.length <= 500
//–1000 <= xi, yi <= 1000
//All points are pairwise distinct.
