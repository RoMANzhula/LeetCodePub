package P3001_3100.P3047_Find_the_Largest_Area_of_Square_Inside_Two_Rectangles;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] bottomLeft1 = {{1,1},{2,2},{3,1}};
        int[][] topRight1   = {{3,3},{4,4},{6,6}};
        System.out.println(solution.largestSquareArea(bottomLeft1, topRight1)); // 1

        int[][] bottomLeft2 = {{1,1},{1,3},{1,5}};
        int[][] topRight2   = {{5,5},{5,7},{5,9}};
        System.out.println(solution.largestSquareArea(bottomLeft2, topRight2)); // 4

        int[][] bottomLeft3 = {{1,1},{2,2},{1,2}};
        int[][] topRight3   = {{3,3},{4,4},{3,4}};
        System.out.println(solution.largestSquareArea(bottomLeft3, topRight3)); // 1

        int[][] bottomLeft4 = {{1,1},{3,3},{3,1}};
        int[][] topRight4   = {{2,2},{4,4},{4,2}};
        System.out.println(solution.largestSquareArea(bottomLeft4, topRight4)); // 0
    }

    public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        int n = bottomLeft.length;
        long maxArea = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                int left   = Math.max(bottomLeft[i][0], bottomLeft[j][0]);
                int right  = Math.min(topRight[i][0], topRight[j][0]);
                int bottom = Math.max(bottomLeft[i][1], bottomLeft[j][1]);
                int top    = Math.min(topRight[i][1], topRight[j][1]);

                int width = right - left;
                int height = top - bottom;

                if (width > 0 && height > 0) {
                    long side = Math.min(width, height);
                    long area = side * side;
                    maxArea = Math.max(maxArea, area);
                }
            }
        }

        return maxArea;
    }

}

//Complexity:
// time - O(n^2)
// space - O(n)


//There exist n rectangles in a 2D plane with edges parallel to the x and y axis. You are given two 2D integer
// arrays bottomLeft and topRight where bottomLeft[i] = [a_i, b_i] and topRight[i] = [c_i, d_i] represent the
// bottom-left and top-right coordinates of the ith rectangle, respectively.
//You need to find the maximum area of a square that can fit inside the intersecting region of at least two
// rectangles. Return 0 if such a square does not exist.

//Example 1:
//Input: bottomLeft = [[1,1],[2,2],[3,1]], topRight = [[3,3],[4,4],[6,6]]
//Output: 1
//Explanation:
//A square with side length 1 can fit inside either the intersecting region of rectangles 0 and 1 or the intersecting
// region of rectangles 1 and 2. Hence the maximum area is 1. It can be shown that a square with a greater side length
// can not fit inside any intersecting region of two rectangles.

//Example 2:
//Input: bottomLeft = [[1,1],[1,3],[1,5]], topRight = [[5,5],[5,7],[5,9]]
//Output: 4
//Explanation:
//A square with side length 2 can fit inside either the intersecting region of rectangles 0 and 1 or the intersecting
// region of rectangles 1 and 2. Hence the maximum area is 2 * 2 = 4. It can be shown that a square with a greater
// side length can not fit inside any intersecting region of two rectangles.

//Example 3:
//Input: bottomLeft = [[1,1],[2,2],[1,2]], topRight = [[3,3],[4,4],[3,4]]
//Output: 1
//Explanation:
//A square with side length 1 can fit inside the intersecting region of any two rectangles. Also, no larger square can,
// so the maximum area is 1. Note that the region can be formed by the intersection of more than 2 rectangles.

//Example 4:
//Input: bottomLeft = [[1,1],[3,3],[3,1]], topRight = [[2,2],[4,4],[4,2]]
//Output: 0
//Explanation:
//No pair of rectangles intersect, hence, the answer is 0.

//Constraints:
//n == bottomLeft.length == topRight.length
//2 <= n <= 103
//bottomLeft[i].length == topRight[i].length == 2
//1 <= bottomLeft[i][0], bottomLeft[i][1] <= 107
//1 <= topRight[i][0], topRight[i][1] <= 107
//bottomLeft[i][0] < topRight[i][0]
//bottomLeft[i][1] < topRight[i][1]
