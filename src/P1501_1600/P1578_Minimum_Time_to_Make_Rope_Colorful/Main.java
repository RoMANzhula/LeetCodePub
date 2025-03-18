package P1501_1600.P1578_Minimum_Time_to_Make_Rope_Colorful;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String colors1 = "abaac";
        int[] neededTime1 = {1, 2, 3, 4, 5};
        System.out.println(minCost(colors1, neededTime1)); // Output: 3

        String colors2 = "abc";
        int[] neededTime2 = {1, 2, 3};
        System.out.println(minCost(colors2, neededTime2)); // Output: 0

        String colors3 = "aabaa";
        int[] neededTime3 = {1, 2, 3, 4, 1};
        System.out.println(minCost(colors3, neededTime3)); // Output: 2

        String colors = "aaabbbabbbb";
        int[] neededTime = {3, 5, 10, 7, 5, 3, 5, 5, 4, 8, 1};
        System.out.println(minCost(colors, neededTime)); // Output: 26
    }

    public static int minCost(String colors, int[] neededTime) {
        int totalCost = 0;  // Початковий витрачений час - час для першого балона
        int maxNeededTime = neededTime[0];  // Максимальний час для групи однакових балонів

        for (int i = 1; i < colors.length(); ++i) {
            char currentColor = colors.charAt(i);

            if (currentColor == colors.charAt(i - 1)) {
                // Якщо поточний балон має той самий колір, що і попередній
                // Додаємо мінімальний час між двома балонами до витрат
                totalCost += Math.min(maxNeededTime, neededTime[i]);
                // Для кожної групи послідовних балонів, Боб має видалити всі балони, крім того, який потребує найбільше часу
                maxNeededTime = Math.max(maxNeededTime, neededTime[i]);
            } else {
                // Максимальний час для нової групи - час для поточного балона
                maxNeededTime = neededTime[i];
            }
        }

        return totalCost;
    }

}

//Alice has n balloons arranged on a rope. You are given a 0-indexed string colors where colors[i] is the
// color of the ith balloon.
//Alice wants the rope to be colorful. She does not want two consecutive balloons to be of the same color, so
// she asks Bob for help. Bob can remove some balloons from the rope to make it colorful. You are given a 0-indexed
// integer array neededTime where neededTime[i] is the time (in seconds) that Bob needs to remove the
// ith balloon from the rope.
//Return the minimum time Bob needs to make the rope colorful.

//Example 1:
//Input: colors = "abaac", neededTime = [1,2,3,4,5]
//Output: 3
//Explanation: In the above image, 'a' is blue, 'b' is red, and 'c' is green.
//Bob can remove the blue balloon at index 2. This takes 3 seconds.
//There are no longer two consecutive balloons of the same color. Total time = 3.

//Example 2:
//Input: colors = "abc", neededTime = [1,2,3]
//Output: 0
//Explanation: The rope is already colorful. Bob does not need to remove any balloons from the rope.

//Example 3:
//Input: colors = "aabaa", neededTime = [1,2,3,4,1]
//Output: 2
//Explanation: Bob will remove the ballons at indices 0 and 4. Each ballon takes 1 second to remove.
//There are no longer two consecutive balloons of the same color. Total time = 1 + 1 = 2.

//Constraints:
//n == colors.length == neededTime.length
//1 <= n <= 105
//1 <= neededTime[i] <= 104
//colors contains only lowercase English letters.
