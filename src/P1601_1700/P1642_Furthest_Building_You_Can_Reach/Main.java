package P1601_1700.P1642_Furthest_Building_You_Can_Reach;

import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] heights1 = {4,2,7,6,9,14,12};
        int bricks1 = 5;
        int ladders1 = 1;

        int[] heights2 = {4,12,2,7,3,18,20,3,19};
        int bricks2 = 10;
        int ladders2 = 2;

        int[] heights3 = {14,3,19,3};
        int bricks3 = 17;
        int ladders3 = 0;

        System.out.println(solution.furthestBuilding(heights1, bricks1, ladders1)); // output 4
        System.out.println(solution.furthestBuilding(heights2, bricks2, ladders2)); // output 7
        System.out.println(solution.furthestBuilding(heights3, bricks3, ladders3)); // output 3

    }

    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> ladderUsed = new PriorityQueue<>();

        for (int i = 0; i < heights.length - 1; i++) {
            int diff = heights[i + 1] - heights[i];
            if (diff > 0) {
                if (ladders > 0) {
                    ladderUsed.offer(diff);
                    ladders--;
                } else if (!ladderUsed.isEmpty() && diff > ladderUsed.peek()) {
                    bricks -= ladderUsed.poll();
                    ladderUsed.offer(diff);
                } else {
                    bricks -= diff;
                }
                if (bricks < 0) return i;
            }
        }

        return heights.length - 1;
    }
}

//У цьому завданні ми маємо пересуватися вздовж будівельних споруд, починаючи з першої будівлі і переходячи до
// наступної. Під час переходу з будівлі i на будівлю i+1 можливі два варіанти:
//
//Якщо висота поточної будівлі більша або рівна висоті наступної будівлі, нам не потрібно використовувати ні
// лідери, ні цеглини.
//Якщо висота поточної будівлі менша від висоти наступної будівлі, ми можемо використовувати або один лідер,
// або кілька цеглин.
//Мета полягає в тому, щоб максимально далеко продовжити шлях, використовуючи дану кількість лідерів і цеглин. Однак
// нам потрібно робити це оптимально.
//
//Ми використовуємо чергу пріоритетів для зберігання висот, які ми можемо покрити за допомогою лідерів. Якщо
// у нас є доступні лідери, ми використовуємо їх. Якщо немає, ми перевіряємо, чи можемо покрити висоту будівлі
// за допомогою цеглин. Якщо ні, ми повертаємо індекс поточної будівлі, оскільки не можемо продовжити шлях
// далі. Якщо у нас є цеглини, ми використовуємо їх і продовжуємо. У разі успішного проходження циклу ми
// повертаємо останній індекс як найвіддаленішу будівлю.


//You are given an integer array heights representing the heights of buildings, some bricks, and some ladders.
//You start your journey from building 0 and move to the next building by possibly using bricks or ladders.
//While moving from building i to building i+1 (0-indexed),
//If the current building's height is greater than or equal to the next building's height, you do not
// need a ladder or bricks.
//If the current building's height is less than the next building's height, you can either use one
// ladder or (h[i+1] - h[i]) bricks.
//Return the furthest building index (0-indexed) you can reach if you use the given ladders and bricks optimally.

//Example 1:
//Input: heights = [4,2,7,6,9,14,12], bricks = 5, ladders = 1
//Output: 4
//Explanation: Starting at building 0, you can follow these steps:
//- Go to building 1 without using ladders nor bricks since 4 >= 2.
//- Go to building 2 using 5 bricks. You must use either bricks or ladders because 2 < 7.
//- Go to building 3 without using ladders nor bricks since 7 >= 6.
//- Go to building 4 using your only ladder. You must use either bricks or ladders because 6 < 9.
//It is impossible to go beyond building 4 because you do not have any more bricks or ladders.

//Example 2:
//Input: heights = [4,12,2,7,3,18,20,3,19], bricks = 10, ladders = 2
//Output: 7
//Example 3:
//Input: heights = [14,3,19,3], bricks = 17, ladders = 0
//Output: 3
//
//Constraints:
//1 <= heights.length <= 105
//1 <= heights[i] <= 106
//0 <= bricks <= 109
//0 <= ladders <= heights.length