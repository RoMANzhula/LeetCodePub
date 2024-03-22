package P401_500.P452_Minimum_Number_of_Arrows_to_Burst_Balloons;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[][] points1 = {{10,16},{2,8},{1,6},{7,12}};
        System.out.println("Output for points1: " + solution.findMinArrowShots(points1)); // Output: 2

        int[][] points2 = {{1,2},{3,4},{5,6},{7,8}};
        System.out.println("Output for points2: " + solution.findMinArrowShots(points2)); // Output: 4

        int[][] points3 = {{1,2},{2,3},{3,4},{4,5}};
        System.out.println("Output for points3: " + solution.findMinArrowShots(points3)); // Output: 2
    }

    public int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0)
            return 0;

        // sort the balloons by their ending points
        Arrays.sort(points, Comparator.comparingInt(a -> a[1]));

        int arrows = 1; // At least one arrow will be needed
        int end = points[0][1]; // Ending point of the first balloon

        // sterate through all balloons to find the minimum number of arrows needed
        for (int i = 1; i < points.length; i++) {
            // if the current balloon's start point is beyond the end point of the previous balloon,
            // we need a new arrow (because the previous arrow cannot burst the current balloon)
            if (points[i][0] > end) {
                arrows++;
                end = points[i][1]; // update the end point for the next arrow
            }
        }

        return arrows;
    }
}

//Задача полягає в тому, щоб знайти мінімальну кількість стріл, які необхідно вистрілити, щоб лопнули всі кульки,
// розташовані на стіні. Кульки представлені 'у' вигляді двовимірного масиву points, де кожен елемент масиву points[i]
// має вигляд [xstart, xend], що вказує на кульку, яка займає горизонтальний діаметр від xstart до xend. Нам
// потрібно знати точне значення координати 'y' кульок не потрібно.
//Ми вирішуємо цю задачу за допомогою жадного алгоритму. Спочатку ми сортуємо кульки за їх кінцевими
// точками. Потім ми проходимо по всіх кульках і перевіряємо, чи початкова точка поточної кульки знаходиться
// поза кінцевою точкою попередньої кульки. Якщо так, це означає, що нам потрібен новий постріл, оскільки
// попередній постріл не може лопнути поточну кульку. У кінці ми повертаємо загальну кількість стріл, яка
// потрібна для лопання всіх кульок.

//There are some spherical balloons taped onto a flat wall that represents the XY-plane. The balloons are
// represented as a 2D integer array points where points[i] = [xstart, xend] denotes a balloon whose horizontal
// diameter stretches between xstart and xend. You do not know the exact y-coordinates of the balloons.
//Arrows can be shot up directly vertically (in the positive y-direction) from different points along the
// x-axis. A balloon with xstart and xend is burst by an arrow shot at x if xstart <= x <= xend. There is no
// limit to the number of arrows that can be shot. A shot arrow keeps traveling up infinitely, bursting any
// balloons in its path.
//Given the array points, return the minimum number of arrows that must be shot to burst all balloons.
//
//Example 1:
//Input: points = [[10,16],[2,8],[1,6],[7,12]]
//Output: 2
//Explanation: The balloons can be burst by 2 arrows:
//- Shoot an arrow at x = 6, bursting the balloons [2,8] and [1,6].
//- Shoot an arrow at x = 11, bursting the balloons [10,16] and [7,12].

//Example 2:
//Input: points = [[1,2],[3,4],[5,6],[7,8]]
//Output: 4
//Explanation: One arrow needs to be shot for each balloon for a total of 4 arrows.

//Example 3:
//Input: points = [[1,2],[2,3],[3,4],[4,5]]
//Output: 2
//Explanation: The balloons can be burst by 2 arrows:
//- Shoot an arrow at x = 2, bursting the balloons [1,2] and [2,3].
//- Shoot an arrow at x = 4, bursting the balloons [3,4] and [4,5].
//
//Constraints:
//1 <= points.length <= 105
//points[i].length == 2
//-231 <= xstart < xend <= 231 - 1