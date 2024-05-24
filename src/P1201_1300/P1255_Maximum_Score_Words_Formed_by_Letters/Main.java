package P1201_1300.P1255_Maximum_Score_Words_Formed_by_Letters;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        String[] words1 = {"dog", "cat", "dad", "good"};
        char[] letters1 = {'a', 'a', 'c', 'd', 'd', 'd', 'g', 'o', 'o'};
        int[] score1 = {1, 0, 9, 5, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        System.out.println(solution.maxScoreWords(words1, letters1, score1)); // Output: 23

        String[] words2 = {"xxxz", "ax", "bx", "cx"};
        char[] letters2 = {'z', 'a', 'b', 'c', 'x', 'x', 'x'};
        int[] score2 = {4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 10};
        System.out.println(solution.maxScoreWords(words2, letters2, score2)); // output:27

        String[] words3 = {"leetcode"};
        char[] letters3 = {'l', 'e', 't', 'c', 'o', 'd'};
        int[] score3 = {0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0};
        System.out.println(solution.maxScoreWords(words3, letters3, score3)); // output: 0

    }

    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        int[] letterCount = new int[26];
        for (char letter : letters) {
            letterCount[letter - 'a']++;
        }

        return backtrack(words, letterCount, score, 0);
    }

    private int backtrack(String[] words, int[] letterCount, int[] score, int index) {
        if (index == words.length) {
            return 0;
        }

        // Skip the current word
        int maxScore = backtrack(words, letterCount, score, index + 1);

        // Try to use the current word if possible
        String word = words[index];
        boolean canUse = true;
        int wordScore = 0;
        int[] tempCount = Arrays.copyOf(letterCount, letterCount.length);

        for (char c : word.toCharArray()) {
            if (tempCount[c - 'a'] <= 0) {
                canUse = false;
                break;
            }
            tempCount[c - 'a']--;
            wordScore += score[c - 'a'];
        }

        if (canUse) {
            maxScore = Math.max(maxScore, wordScore + backtrack(words, tempCount, score, index + 1));
        }

        return maxScore;
    }
}

//Підготовка даних:
//підрахунок кількості доступних літер. Для цього ми можемо використовувати масив, де індекси відповідають
// номерам літер у алфавіті (наприклад, a = 0, b = 1, ..., z = 25), а значення відображають кількість
// доступних цих літер.
//Перебір всіх можливих комбінацій слів:
//Ми можемо використовувати рекурсивний підхід для перебору всіх можливих комбінацій слів. На кожному кроці
// ми маємо дві можливості: або використати поточне слово, або пропустити його.
//Перевірка можливості використання слова:
//Перш ніж використовувати слово, ми повинні перевірити, чи маємо ми достатньо літер для його утворення. Це означає,
// що кількість кожної літери в слові не може перевищувати кількість доступних літер.
//Рекурсивний виклик:
//Після того, як ми вирішимо, чи можна використати слово, ми рекурсивно викликаємо ту ж функцію для наступного
// слова з оновленим набором доступних літер та підрахунком поточного скору.
//Повернення результату:
//На кожному кроці ми вибираємо максимальний скор, який можна отримати, обираючи або пропускаючи кожне слово. Коли
// ми дійшли до кінця списку слів, ми повертаємо цей максимальний скор як результат.
//Цей підхід ефективний, оскільки він перебирає всі можливі комбінації слів, враховуючи доступні літери, та
// обчислює максимальний скор, який можна отримати для кожної комбінації.

//Given a list of words, list of  single letters (might be repeating) and score of every character.
//Return the maximum score of any valid set of words formed by using the given letters (words[i] cannot be
// used two or more times).
//It is not necessary to use all characters in letters and each letter can only be used once. Score of
// letters 'a', 'b', 'c', ... ,'z' is given by score[0], score[1], ... , score[25] respectively.
//
//Example 1:
//Input: words = ["dog","cat","dad","good"], letters = ["a","a","c","d","d","d","g","o","o"],
// score = [1,0,9,5,0,0,3,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0]
//Output: 23
//Explanation:
//Score  a=1, c=9, d=5, g=3, o=2
//Given letters, we can form the words "dad" (5+1+5) and "good" (3+2+2+5) with a score of 23.
//Words "dad" and "dog" only get a score of 21.

//Example 2:
//Input: words = ["xxxz","ax","bx","cx"], letters = ["z","a","b","c","x","x","x"],
// score = [4,4,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,10]
//Output: 27
//Explanation:
//Score  a=4, b=4, c=4, x=5, z=10
//Given letters, we can form the words "ax" (4+5), "bx" (4+5) and "cx" (4+5) with a score of 27.
//Word "xxxz" only get a score of 25.

//Example 3:
//Input: words = ["leetcode"], letters = ["l","e","t","c","o","d"],
// score = [0,0,1,1,1,0,0,0,0,0,0,1,0,0,1,0,0,0,0,1,0,0,0,0,0,0]
//Output: 0
//Explanation:
//Letter "e" can only be used once.
//
//Constraints:
//1 <= words.length <= 14
//1 <= words[i].length <= 15
//1 <= letters.length <= 100
//letters[i].length == 1
//score.length == 26
//0 <= score[i] <= 10
//words[i], letters[i] contains only lower case English letters.