package P1201_1300.P1208_Get_Equal_Substrings_Within_Budget;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.equalSubstring("abcd", "bcdf", 3)); // Output: 3
        System.out.println(solution.equalSubstring("abcd", "cdef", 3)); // Output: 1
        System.out.println(solution.equalSubstring("abcd", "acde", 0)); // Output: 1
    }

    public int equalSubstring(String s, String t, int maxCost) {
        int n = s.length();
        int start = 0, end = 0;
        int currentCost = 0;
        int maxLength = 0;

        while (end < n) {
            currentCost += Math.abs(s.charAt(end) - t.charAt(end));

            while (currentCost > maxCost) {
                currentCost -= Math.abs(s.charAt(start) - t.charAt(start));
                start++;
            }

            maxLength = Math.max(maxLength, end - start + 1);
            end++;
        }

        return maxLength;
    }
}

//Використовуємо техніку ковзного вікна. Ця техніка допомагає нам знайти максимальну довжину підрядка, який
// задовольняє певні умови, в даному випадку, що загальна вартість зміни підрядка не перевищує maxCost.
//Пояснення рішення:
//Ініціалізація вказівників та змінних:
//Ми використовуємо два вказівники (start і end), які визначають поточне вікно підрядка.
//Також використовуємо змінну (currentCost), щоб відстежувати загальну вартість зміни символів у поточному вікні.
//Проходження по рядку:
//Ми проходимо по рядку з вказівником end, який рухається від початку до кінця рядка.
//Для кожного символу на позиції end ми обчислюємо вартість його зміни до відповідного символу в другому рядку t і
// додаємо цю вартість до currentCost.
//Перевірка вартості:
//Якщо currentCost перевищує maxCost, ми рухаємо вказівник start праворуч, поки currentCost не стане меншим або
// рівним maxCost. Це зменшує вікно підрядка, поки загальна вартість змін не буде відповідати обмеженню.
//Відстеження максимальної довжини:
//Ми постійно оновлюємо максимальну довжину вікна (maxLength), яке задовольняє умову, що currentCost
// не перевищує maxCost.
//Висновок:
//Ця техніка дозволяє ефективно знайти максимальну довжину підрядка, який можна перетворити в інший підрядок з
// витратами, що не перевищують заданий ліміт. Ми рухаємося по рядку один раз з вказівником end і коригуємо
// початок вікна з вказівником start, що робить алгоритм ефективним.

//You are given two strings s and t of the same length and an integer maxCost.
//You want to change s to t. Changing the ith character of s to ith character of t costs |s[i] - t[i]| (i.e., the
// absolute difference between the ASCII values of the characters).
//Return the maximum length of a substring of s that can be changed to be the same as the corresponding substring
// of t with a cost less than or equal to maxCost. If there is no substring from s that can be changed to its
// corresponding substring from t, return 0.
//
//Example 1:
//Input: s = "abcd", t = "bcdf", maxCost = 3
//Output: 3
//Explanation: "abc" of s can change to "bcd".
//That costs 3, so the maximum length is 3.

//Example 2:
//Input: s = "abcd", t = "cdef", maxCost = 3
//Output: 1
//Explanation: Each character in s costs 2 to change to character in t,  so the maximum length is 1.

//Example 3:
//Input: s = "abcd", t = "acde", maxCost = 0
//Output: 1
//Explanation: You cannot make any change, so the maximum length is 1.
//
//Constraints:
//1 <= s.length <= 105
//t.length == s.length
//0 <= maxCost <= 106
//s and t consist of only lowercase English letters.