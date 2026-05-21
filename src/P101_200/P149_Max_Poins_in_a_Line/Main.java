package P101_200.P149_Max_Poins_in_a_Line;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] points1 = {{1,1},{2,2},{3,3}};
        System.out.println(solution.maxPoints(points1)); // 3

        int[][] points2 = {{1,1},{3,2},{5,3},{4,1},{2,3},{1,4}};
        System.out.println(solution.maxPoints(points2)); // 4
    }

    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n <= 2) return n;

        int result = 0;

        for (int i = 0; i < n; i++) {
            Map<String, Integer> map = new HashMap<>();
            int max = 0;

            for (int j = i + 1; j < n; j++) {
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];

                int gcd = gcd(dx, dy);
                dx /= gcd;
                dy /= gcd;

                // normalize sign
                if (dx < 0) {
                    dx = -dx;
                    dy = -dy;
                } else if (dx == 0) {
                    dy = 1;
                } else if (dy == 0) {
                    dx = 1;
                }

                String key = dy + "/" + dx;
                map.put(key, map.getOrDefault(key, 0) + 1);

                max = Math.max(max, map.get(key));
            }

            result = Math.max(result, max + 1);
        }

        return result;
    }

    private static int gcd(int a, int b) {
        if (b == 0) return Math.abs(a);
        return gcd(b, a % b);
    }

}

//Complexity:
// time - O(n^2)
// space - O(n)


//Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane, return the maximum
// number of points that lie on the same straight line.

//Example 1:
//Input: points = [[1,1],[2,2],[3,3]]
//Output: 3

//Example 2:
//Input: points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
//Output: 4

//Constraints:
//1 <= points.length <= 300
//points[i].length == 2
//-104 <= xi, yi <= 104
//All the points are unique.
