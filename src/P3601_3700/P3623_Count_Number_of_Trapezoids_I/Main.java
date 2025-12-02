package P3601_3700.P3623_Count_Number_of_Trapezoids_I;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] points1 = {
                {1,0},{2,0},{3,0},{2,2},{3,2}
        };

        int[][] points2 = {
                {0,0},{1,0},{0,1},{2,1}
        };

        System.out.println(solution.countTrapezoids(points1)); // 3
        System.out.println(solution.countTrapezoids(points2)); // 1
    }


    static final long MOD = 1_000_000_007;

    public int countTrapezoids(int[][] points) {
        Map<Integer, Integer> frequence = new HashMap<>();

        for (int[] point : points) {
            frequence.put(point[1], frequence.getOrDefault(point[1], 0) + 1);
        }

        long total = 0;
        long pairs = 0;

        for (Integer value : frequence.values()) {
            if (value <= 1) {
                continue;
            }

            long pair = ((long) value * (value - 1)) >> 1;

            total += pair;
            pairs += pair * pair;
        }

        return (int) ((total * total - pairs) / 2 % MOD);
    }

}

//Complexuity:
// time and space - O(n)


//You are given a 2D integer array points, where points[i] = [xi, yi] represents the coordinates of the ith point on
// the Cartesian plane.
//A horizontal trapezoid is a convex quadrilateral with at least one pair of horizontal sides (i.e. parallel to
// the x-axis). Two lines are parallel if and only if they have the same slope.
//Return the number of unique horizontal trapezoids that can be formed by choosing any four distinct points from points.
//Since the answer may be very large, return it modulo 109 + 7.

//Example 1:
//Input: points = [[1,0],[2,0],[3,0],[2,2],[3,2]]
//Output: 3
//Explanation:
//There are three distinct ways to pick four points that form a horizontal trapezoid:
//Using points [1,0], [2,0], [3,2], and [2,2].
//Using points [2,0], [3,0], [3,2], and [2,2].
//Using points [1,0], [3,0], [3,2], and [2,2].

//Example 2:
//Input: points = [[0,0],[1,0],[0,1],[2,1]]
//Output: 1
//Explanation:
//There is only one horizontal trapezoid that can be formed.

//Constraints:
//4 <= points.length <= 105
//–108 <= xi, yi <= 108
//All points are pairwise distinct.
