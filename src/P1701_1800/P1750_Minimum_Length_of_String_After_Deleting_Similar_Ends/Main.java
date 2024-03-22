package P1701_1800.P1750_Minimum_Length_of_String_After_Deleting_Similar_Ends;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.minimumLength("ca"));        // Output: 2
        System.out.println(solution.minimumLength("cabaabac"));  // Output: 0
        System.out.println(solution.minimumLength("aabccabba")); // Output: 3
    }

    public int minimumLength(String s) {
        int left = 0, right = s.length() - 1;

        while (left < right && s.charAt(left) == s.charAt(right)) {
            char ch = s.charAt(left);

            // Move left pointer to the right until it finds a different character
            while (left <= right && s.charAt(left) == ch) {
                left++;
            }

            // Move right pointer to the left until it finds a different character
            while (right >= left && s.charAt(right) == ch) {
                right--;
            }
        }

        // Minimum length will be the remaining length between left and right pointers
        return right - left + 1;
    }
}

//Спочатку ми встановлюємо два вказівника: left на початку рядка і right на його кінці.
//Потім ми порівнюємо символи, на які вказують ці вказівники. Якщо вони однакові, ми переходимо до кроку 3. Якщо ні,
// ми завершуємо роботу.
//Запам'ятовуємо символ ch, на який вказує left.
//Переміщаємо вказівник left вправо, поки він вказує на ідентичний символ ch.
//Переміщаємо вказівник right вліво, поки він вказує на ідентичний символ ch.
//Повторюємо кроки 3-5, доки left і right вказують на однакові символи.
//Мінімальна довжина буде різницею між значеннями вказівників left і right плюс одиниця (так як індекси починаються з 0).
//Це дає нам мінімальну довжину рядка після застосування вказаних операцій.

//Given a string s consisting only of characters 'a', 'b', and 'c'. You are asked to apply the following
// algorithm on the string any number of times:
//Pick a non-empty prefix from the string s where all the characters in the prefix are equal.
//Pick a non-empty suffix from the string s where all the characters in this suffix are equal.
//The prefix and the suffix should not intersect at any index.
//The characters from the prefix and suffix must be the same.
//Delete both the prefix and the suffix.
//Return the minimum length of s after performing the above operation any number of times (possibly zero times).

//Example 1:
//Input: s = "ca"
//Output: 2
//Explanation: You can't remove any characters, so the string stays as is.

//Example 2:
//Input: s = "cabaabac"
//Output: 0
//Explanation: An optimal sequence of operations is:
//- Take prefix = "c" and suffix = "c" and remove them, s = "abaaba".
//- Take prefix = "a" and suffix = "a" and remove them, s = "baab".
//- Take prefix = "b" and suffix = "b" and remove them, s = "aa".
//- Take prefix = "a" and suffix = "a" and remove them, s = "".

//Example 3:
//Input: s = "aabccabba"
//Output: 3
//Explanation: An optimal sequence of operations is:
//- Take prefix = "aa" and suffix = "a" and remove them, s = "bccabb".
//- Take prefix = "b" and suffix = "bb" and remove them, s = "cca".
//
//Constraints:
//1 <= s.length <= 105
//s only consists of characters 'a', 'b', and 'c'.
