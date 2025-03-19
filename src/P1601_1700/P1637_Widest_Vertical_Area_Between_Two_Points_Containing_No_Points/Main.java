package P1601_1700.P1637_Widest_Vertical_Area_Between_Two_Points_Containing_No_Points;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[][] points1 = {{8, 7}, {9, 9}, {7, 4}, {9, 7}};
        System.out.println("Example 1 Output: " + maxWidthOfVerticalArea(points1));

        int[][] points2 = {{3, 1}, {9, 0}, {1, 0}, {1, 4}, {5, 3}, {8, 8}};
        System.out.println("Example 2 Output: " + maxWidthOfVerticalArea(points2));
    }

    public static int maxWidthOfVerticalArea(int[][] points) {
        // extract x-coordinates from points
        int[] xCoordinates = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            xCoordinates[i] = points[i][0];
        }

        // sort x-coordinates
        Arrays.sort(xCoordinates);

        // find the maximum width between consecutive x-coordinates
        int maxWidth = 0;
        for (int i = 1; i < xCoordinates.length; i++) {
            int width = xCoordinates[i] - xCoordinates[i - 1];
            maxWidth = Math.max(maxWidth, width);
        }

        return maxWidth;
    }

}

//Задача полягає в знаходженні найширшої вертикальної області між двома точками, так щоб в цій області не було
// інших точок.
//Спочатку ми витягуємо x-координати з точок із заданого масиву.
//Потім ми сортуємо ці x-координати в порядку зростання.
//Після цього ми обчислюємо різницю між кожною парою сусідніх x-координат і визначаємо максимальну ширину.
//Максимальна ширина - це і є відповідь на задачу.



//Given n points on a 2D plane where points[i] = [xi, yi], Return the widest vertical area between two points
// such that no points are inside the area.
//A vertical area is an area of fixed-width extending infinitely along the y-axis (i.e., infinite height). The
// widest vertical area is the one with the maximum width.
//Note that points on the edge of a vertical area are not considered included in the area.

//Example 1:
//Input: points = [[8,7],[9,9],[7,4],[9,7]]
//Output: 1
//Explanation: Both the red and the blue area are optimal.

//Example 2:
//Input: points = [[3,1],[9,0],[1,0],[1,4],[5,3],[8,8]]
//Output: 3

//Constraints:
//n == points.length
//2 <= n <= 105
//points[i].length == 2
//0 <= xi, yi <= 109
