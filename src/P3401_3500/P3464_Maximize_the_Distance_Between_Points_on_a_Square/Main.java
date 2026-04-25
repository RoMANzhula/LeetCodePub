package P3401_3500.P3464_Maximize_the_Distance_Between_Points_on_a_Square;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] points1 = {{0,2},{2,0},{2,2},{0,0}};
        System.out.println(solution.maxDistance(2, points1, 4)); // 2

        int[][] points2 = {{0,0},{1,2},{2,0},{2,2},{2,1}};
        System.out.println(solution.maxDistance(2, points2, 4)); // 1

        int[][] points3 = {{0,0},{0,1},{0,2},{1,2},{2,0},{2,2},{2,1}};
        System.out.println(solution.maxDistance(2, points3, 5)); // 1
    }


    public int maxDistance(int side, int[][] points, int k) {
        List<int[]> ordered = getOrderedPoints(side, points);

        int left = 0, right = side;

        while (left < right) {
            int mid = (left + right + 1) / 2;
            if (isValidDistance(ordered, k, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    private boolean isValidDistance(List<int[]> ordered, int k, int d) {
        Deque<Sequence> dq = new ArrayDeque<>();

        int[] first = ordered.get(0);
        dq.offerLast(new Sequence(first[0], first[1], first[0], first[1], 1));

        int maxLength = 1;

        for (int i = 1; i < ordered.size(); i++) {
            int x = ordered.get(i)[0];
            int y = ordered.get(i)[1];

            int startX = x;
            int startY = y;
            int length = 1;

            while (!dq.isEmpty() &&
                    manhattan(x, y, dq.peekFirst().endX, dq.peekFirst().endY) >= d) {

                Sequence front = dq.peekFirst();

                if (manhattan(x, y, front.startX, front.startY) >= d &&
                        front.length + 1 >= length) {

                    startX = front.startX;
                    startY = front.startY;
                    length = front.length + 1;

                    maxLength = Math.max(maxLength, length);
                }

                dq.pollFirst();
            }

            dq.offerLast(new Sequence(startX, startY, x, y, length));
        }

        return maxLength >= k;
    }

    private int manhattan(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    private List<int[]> getOrderedPoints(int side, int[][] points) {
        List<int[]> left = new ArrayList<>();
        List<int[]> top = new ArrayList<>();
        List<int[]> right = new ArrayList<>();
        List<int[]> bottom = new ArrayList<>();

        for (int[] p : points) {
            int x = p[0], y = p[1];

            if (x == 0 && y > 0)
                left.add(p);
            else if (x > 0 && y == side)
                top.add(p);
            else if (x == side && y < side)
                right.add(p);
            else
                bottom.add(p);
        }

        left.sort(Comparator.comparingInt(a -> a[1])); // sort by y
        top.sort(Comparator.comparingInt(a -> a[0]));  // sort by x
        right.sort((a, b) -> Integer.compare(b[1], a[1])); // reverse by y
        bottom.sort((a, b) -> Integer.compare(b[0], a[0])); // reverse by x

        List<int[]> result = new ArrayList<>();
        result.addAll(left);
        result.addAll(top);
        result.addAll(right);
        result.addAll(bottom);

        return result;
    }


    class Sequence {
        int startX, startY;
        int endX, endY;
        int length;

        Sequence(int sx, int sy, int ex, int ey, int len) {
            this.startX = sx;
            this.startY = sy;
            this.endX = ex;
            this.endY = ey;
            this.length = len;
        }
    }

}

//Complexity:
// time - O(n log n + n log side)
// space - O(n)


//You are given an integer side, representing the edge length of a square with corners at (0, 0), (0, side), (side, 0),
// and (side, side) on a Cartesian plane.
//You are also given a positive integer k and a 2D integer array points, where points[i] = [xi, yi] represents the
// coordinate of a point lying on the boundary of the square.
//You need to select k elements among points such that the minimum Manhattan distance between any two points
// is maximized.
//Return the maximum possible minimum Manhattan distance between the selected k points.
//The Manhattan Distance between two cells (xi, yi) and (xj, yj) is |xi - xj| + |yi - yj|.

//Example 1:
//Input: side = 2, points = [[0,2],[2,0],[2,2],[0,0]], k = 4
//Output: 2
//Explanation:
//Select all four points.

//Example 2:
//Input: side = 2, points = [[0,0],[1,2],[2,0],[2,2],[2,1]], k = 4
//Output: 1
//Explanation:
//Select the points (0, 0), (2, 0), (2, 2), and (2, 1).

//Example 3:
//Input: side = 2, points = [[0,0],[0,1],[0,2],[1,2],[2,0],[2,2],[2,1]], k = 5
//Output: 1
//Explanation:
//Select the points (0, 0), (0, 1), (0, 2), (1, 2), and (2, 2).

//Constraints:
//1 <= side <= 109
//4 <= points.length <= min(4 * side, 15 * 103)
//points[i] == [xi, yi]
//The input is generated such that:
//points[i] lies on the boundary of the square.
//All points[i] are unique.
//4 <= k <= min(25, points.length)
