package P1201_1300.P1266_Minimum_Time_Visiting_All_Points;

public class Main {

    public static void main(String[] args) {
        int[][] points1 = {{1, 1}, {3, 4}, {-1, 0}};
        int result1 = minTimeToVisitAllPoints(points1);
        System.out.println("Example 1: " + result1); // Output: 7

        int[][] points2 = {{3, 2}, {-2, 2}};
        int result2 = minTimeToVisitAllPoints(points2);
        System.out.println("Example 2: " + result2); // Output: 5
    }

    public static int minTimeToVisitAllPoints(int[][] points) {
        int totalTime = 0;

        for (int i = 0; i < points.length - 1; i++) {
            int[] currentPoint = points[i];
            int[] nextPoint = points[i + 1];

            int horizontalDistance = Math.abs(nextPoint[0] - currentPoint[0]);
            int verticalDistance = Math.abs(nextPoint[1] - currentPoint[1]);

            // Diagonal distance is the maximum of horizontal and vertical distances
            int diagonalDistance = Math.max(horizontalDistance, verticalDistance);

            totalTime += diagonalDistance;
        }

        return totalTime;
    }

}

//Рішення:
//Щоб розв'язати цю задачу, ми ітеруємося через всі точки і обчислюємо відстань між кожною парою сусідніх
// точок. Час для переміщення від однієї точки до іншої - це максимум з горизонтальної, вертикальної та
// діагональної відстаней. Ми додаємо цей час до загального часу. Наш код реалізовує цю логіку
// в методі minTimeToVisitAllPoints.
//Примітка: Важливо вказати, що в даному випадку, оскільки переміщення дозволяється по діагоналі на відстань sqrt(2),
// то найкоротший час витратиться на діагональний рух, якщо це можливо.


//On a 2D plane, there are n points with integer coordinates points[i] = [xi, yi]. Return the minimum time in seconds
// to visit all the points in the order given by points.
//You can move according to these rules:
//In 1 second, you can either:
//move vertically by one unit,
//move horizontally by one unit, or
//move diagonally sqrt(2) units (in other words, move one unit vertically then one unit horizontally in 1 second).
//You have to visit the points in the same order as they appear in the array.
//You are allowed to pass through points that appear later in the order, but these do not count as visits.

//Example 1:
//Input: points = [[1,1],[3,4],[-1,0]]
//Output: 7
//Explanation: One optimal path is [1,1] -> [2,2] -> [3,3] -> [3,4] -> [2,3] -> [1,2] -> [0,1] -> [-1,0]
//Time from [1,1] to [3,4] = 3 seconds
//Time from [3,4] to [-1,0] = 4 seconds
//Total time = 7 seconds

//Example 2:
//Input: points = [[3,2],[-2,2]]
//Output: 5

//Constraints:
//points.length == n
//1 <= n <= 100
//points[i].length == 2
//-1000 <= points[i][0], points[i][1] <= 1000