package P2801_2900.P2849_Determine_if_a_Cell_Is_Reachable_at_a_Given_Time;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();
        int sx1 = 2, sy1 = 4, fx1 = 7, fy1 = 7, t1 = 6;
        System.out.println(solution.canReach(sx1, sy1, fx1, fy1, t1)); // Should return true

        int sx2 = 3, sy2 = 1, fx2 = 7, fy2 = 3, t2 = 3;
        System.out.println(solution.canReach(sx2, sy2, fx2, fy2, t2)); // Should return false
    }

//    public boolean canReach(int sx, int sy, int fx, int fy, int t) {
//        final int minStep = Math.max(Math.abs(sx - fx), Math.abs(sy - fy));
//        return minStep == 0 ? t != 1 : minStep <= t;
//    }

    public boolean canReach(int sx, int sy, int fx, int fy, int t) {
        int minStep = Math.max(Math.abs(sx - fx), Math.abs(sy - fy));

        if (minStep == 0) {
            return t != 1;
        } else {
            return minStep <= t;
        }
    }

}

//You are given four integers sx, sy, fx, fy, and a non-negative integer t.
//In an infinite 2D grid, you start at the cell (sx, sy). Each second, you must move to any of its adjacent cells.
//Return true if you can reach cell (fx, fy) after exactly t seconds, or false otherwise.
//A cell's adjacent cells are the 8 cells around it that share at least one corner with it. You can visit the
// same cell several times.

//Example 1:
//Input: sx = 2, sy = 4, fx = 7, fy = 7, t = 6
//Output: true
//Explanation: Starting at cell (2, 4), we can reach cell (7, 7) in exactly 6 seconds by going through the cells
// depicted in the picture above.

//Example 2:
//Input: sx = 3, sy = 1, fx = 7, fy = 3, t = 3
//Output: false
//Explanation: Starting at cell (3, 1), it takes at least 4 seconds to reach cell (7, 3) by going through the cells
// depicted in the picture above. Hence, we cannot reach cell (7, 3) at the third second.

//Constraints:
//1 <= sx, sy, fx, fy <= 109
//0 <= t <= 109

//int minStep = Math.max(Math.abs(sx - fx), Math.abs(sy - fy));: Спочатку ми визначаємо minStep, який визначає
// мінімальну кількість кроків, необхідних для досягнення цільової комірки з початкової. Ми використовуємо Math.abs
// для обчислення різниці між координатами sx і fx (по горизонталі) і sy і fy (по вертикалі), а потім Math.max обирає
// більше з цих двох значень як minStep.

//if (minStep == 0) { return t != 1; }: У цій умові перевіряється, чи minStep рівний 0. Якщо minStep дорівнює 0, це
// означає, що початкова і цільова комірки збігаються, і нам потрібно перевірити, чи t не дорівнює 1. Це важливо,
// оскільки якщо початкова і цільова комірки ідентичні, нам потрібен хоча б 2 часових одиниці для досягнення їх.
//else { return minStep <= t; }: Якщо minStep не дорівнює 0, ми перевіряємо, чи minStep менше або дорівнює t. Якщо
// це вірно, це означає, що ми можемо досягти цільової комірки протягом часу t, і функція повертає true. Інакше,
// якщо t недостатньо для досягнення цілі, функція повертає false.
