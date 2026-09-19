package P1401_1500.P1401_Circle_and_Rectangle_Overlapping;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.checkOverlap(1, 0, 0, 1, -1, 3, 1)); // true
        System.out.println(solution.checkOverlap(1, 1, 1, 1, -3, 2, -1)); // false
        System.out.println(solution.checkOverlap(1, 0, 0, -1, 0, 0, 1)); // true
    }

    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        int closestX = Math.max(x1, Math.min(xCenter, x2));
        int closestY = Math.max(y1, Math.min(yCenter, y2));

        int dx = closestX - xCenter;
        int dy = closestY - yCenter;

        return dx * dx + dy * dy <= radius * radius;
    }

}

//Complexity:
// time and space - O(1)


//You are given a circle represented as (radius, xCenter, yCenter) and an axis-aligned rectangle represented
// as (x1, y1, x2, y2), where (x1, y1) are the coordinates of the bottom-left corner, and (x2, y2) are the coordinates
// of the top-right corner of the rectangle.
//Return true if the circle and rectangle are overlapped otherwise return false. In other words, check if there is
// any point (xi, yi) that belongs to the circle and the rectangle at the same time.

//Example 1:
//Input: radius = 1, xCenter = 0, yCenter = 0, x1 = 1, y1 = -1, x2 = 3, y2 = 1
//Output: true
//Explanation: Circle and rectangle share the point (1,0).

//Example 2:
//Input: radius = 1, xCenter = 1, yCenter = 1, x1 = 1, y1 = -3, x2 = 2, y2 = -1
//Output: false

//Example 3:
//Input: radius = 1, xCenter = 0, yCenter = 0, x1 = -1, y1 = 0, x2 = 0, y2 = 1
//Output: true

//Constraints:
//1 <= radius <= 2000
//-104 <= xCenter, yCenter <= 104
//-104 <= x1 < x2 <= 104
//-104 <= y1 < y2 <= 104
