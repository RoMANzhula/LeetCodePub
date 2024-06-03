package P2401_2500.P2486_Append_Characters_to_String_to_Make_Subsequence;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.appendCharacters("coaching", "coding")); // Output: 4
        System.out.println(solution.appendCharacters("abcde", "a")); // Output: 0
        System.out.println(solution.appendCharacters("z", "abcde")); // Output: 5
    }

    public int appendCharacters(String s, String t) {
        int sLength = s.length();
        int tLength = t.length();

        int i = 0; // Pointer for s
        int j = 0; // Pointer for t

        // Traverse s with i and t with j
        while (i < sLength && j < tLength) {
            if (s.charAt(i) == t.charAt(j)) {
                j++;
            }
            i++;
        }

        // If j has traversed all of t, then t is already a subsequence of s
        if (j == tLength) {
            return 0;
        } else {
            // We need to append the remaining characters of t from j to end
            return tLength - j;
        }
    }
}

//Завдання: Необхідно знайти мінімальну кількість символів, які потрібно додати до кінця рядка s, щоб рядок t став
// його підпослідовністю. Підпослідовність — це рядок, який можна отримати з іншого рядка шляхом видалення
// деяких або жодного символа без зміни порядку решти символів.
//Ідея рішення:
//Використовуємо два покажчики: один (i) для рядка s і другий (j) для рядка t.
//Ми будемо проходити рядок s за допомогою покажчика i і одночасно шукати символи з рядка t за допомогою покажчика j.
//Процес проходження:
//Починаємо з обох покажчиків на початку рядків s та t.
//Кожен раз, коли символ у s збігається з поточним символом у t (тобто s.charAt(i) == t.charAt(j)), ми переміщаємо
// покажчик j вперед, оскільки ми знайшли цей символ з t у s.
//Незалежно від того, чи символи збігаються чи ні, ми завжди переміщуємо покажчик i вперед, щоб продовжити перевіряти
// інші символи в s.
//Кінець проходження:
//Якщо покажчик j досягає кінця рядка t під час проходження рядка s, це означає, що ми знайшли всі символи t в s у
// потрібному порядку. Отже, рядок t вже є підпослідовністю s, і додавати символи не потрібно (відповідь: 0).
//Якщо покажчик j не досяг кінця рядка t після проходження всього s, це означає, що деякі символи t ще не
// знайдені в s. У такому випадку нам потрібно додати ці залишкові символи t до кінця s.
//Визначення кількості символів для додавання:
//Кількість символів, які потрібно додати, дорівнює кількості символів, що залишилися в t, тобто від j до кінця t.
//Таким чином, рішення передбачає використання простого проходження рядків з двома покажчиками для визначення, які
// символи потрібно додати до s, щоб t стало його підпослідовністю.

//You are given two strings s and t consisting of only lowercase English letters.
//Return the minimum number of characters that need to be appended to the end of s so that t becomes a subsequence of s.
//A subsequence is a string that can be derived from another string by deleting some or no characters without
// changing the order of the remaining characters.
//
//Example 1:
//Input: s = "coaching", t = "coding"
//Output: 4
//Explanation: Append the characters "ding" to the end of s so that s = "coachingding".
//Now, t is a subsequence of s ("coachingding").
//It can be shown that appending any 3 characters to the end of s will never make t a subsequence.

//Example 2:
//Input: s = "abcde", t = "a"
//Output: 0
//Explanation: t is already a subsequence of s ("abcde").

//Example 3:
//Input: s = "z", t = "abcde"
//Output: 5
//Explanation: Append the characters "abcde" to the end of s so that s = "zabcde".
//Now, t is a subsequence of s ("zabcde").
//It can be shown that appending any 4 characters to the end of s will never make t a subsequence.
//
//Constraints:
//1 <= s.length, t.length <= 105
//s and t consist only of lowercase English letters.
