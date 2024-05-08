package P501_600.P506_Relative_Ranks;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] score1 = {5, 4, 3, 2, 1};
        int[] score2 = {10, 3, 8, 9, 4};

        System.out.println("Example 1");
        System.out.println("Input: " + Arrays.toString(score1));
        System.out.println("Output: " + Arrays.toString(solution.findRelativeRanks(score1))); // output: ["Gold Medal","Silver Medal","Bronze Medal","4","5"]

        System.out.println("Example 2");
        System.out.println("Input: " + Arrays.toString(score2));
        System.out.println("Output: " + Arrays.toString(solution.findRelativeRanks(score2))); // output: ["Gold Medal","5","Bronze Medal","Silver Medal","4"]
    }

    public String[] findRelativeRanks(int[] score) {
        int n = score.length;
        String[] result = new String[n];
        Map<Integer, Integer> scoreIndexMap = new HashMap<>();

        // Create a map of scores and their corresponding indices
        for (int i = 0; i < n; i++) {
            scoreIndexMap.put(score[i], i);
        }

        // Sort the scores in descending order
        Arrays.sort(score);

        // Assign ranks to athletes based on their scores
        for (int i = 0; i < n; i++) {
            int index = scoreIndexMap.get(score[n - 1 - i]);
            if (i == 0) {
                result[index] = "Gold Medal";
            } else if (i == 1) {
                result[index] = "Silver Medal";
            } else if (i == 2) {
                result[index] = "Bronze Medal";
            } else {
                result[index] = String.valueOf(i + 1);
            }
        }

        return result;
    }
}

//Створення мапи індексів: Спочатку ми створюємо мапу scoreIndexMap, де ключами є оцінки, а значеннями - їх індекси
// в вхідному масиві. Це допомагає нам знаходити індекси відсортованих оцінок.
//Сортування оцінок: Ми сортуємо оцінки у вхідному масиві у порядку зростання.
//Присвоєння рангів: Проходимося по відсортованим оцінкам з кінця (тобто з останнього місця у змаганні) до
// початку. При цьому ми знаходимо відповідний індекс кожної оцінки за допомогою scoreIndexMap. За умовою,
// першому місцю присвоюється "Gold Medal", другому - "Silver Medal", третьому - "Bronze Medal". Для всіх інших
// місць використовуємо рядкове представлення їх рангу. При цьому враховується порядковий номер рангу (наприклад,
// четвертому місцю буде присвоєно "4", п'ятому - "5" і так далі).
//Повернення результату: Після присвоєння всім оцінкам відповідних рангів, ми повертаємо масив рядків result.
//Цей підхід забезпечує правильне призначення рангів для кожної оцінки у вхідному масиві. Не сортуючи вихідний масив,
// ми можемо використати інформацію про порядок оцінок, яка зберігається в scoreIndexMap, для визначення рангу
// кожної оцінки.

//You are given an integer array score of size n, where score[i] is the score of the ith athlete in a
// competition. All the scores are guaranteed to be unique.
//The athletes are placed based on their scores, where the 1st place athlete has the highest score, the 2nd place
// athlete has the 2nd highest score, and so on. The placement of each athlete determines their rank:
//The 1st place athlete's rank is "Gold Medal".
//The 2nd place athlete's rank is "Silver Medal".
//The 3rd place athlete's rank is "Bronze Medal".
//For the 4th place to the nth place athlete, their rank is their placement number (i.e., the xth place
// athlete's rank is "x").
//Return an array answer of size n where answer[i] is the rank of the ith athlete.
//
//Example 1:
//Input: score = [5,4,3,2,1]
//Output: ["Gold Medal","Silver Medal","Bronze Medal","4","5"]
//Explanation: The placements are [1st, 2nd, 3rd, 4th, 5th].

//Example 2:
//Input: score = [10,3,8,9,4]
//Output: ["Gold Medal","5","Bronze Medal","Silver Medal","4"]
//Explanation: The placements are [1st, 5th, 3rd, 2nd, 4th].
//
//Constraints:
//n == score.length
//1 <= n <= 104
//0 <= score[i] <= 106
//All the values in score are unique.