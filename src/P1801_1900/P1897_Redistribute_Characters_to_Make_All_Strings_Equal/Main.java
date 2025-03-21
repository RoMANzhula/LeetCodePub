package P1801_1900.P1897_Redistribute_Characters_to_Make_All_Strings_Equal;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String[] words1 = {"abc", "aabc", "bc"};
        System.out.println(canMakeEqual(words1));  // Output: true

        String[] words2 = {"ab", "a"};
        System.out.println(canMakeEqual(words2));  // Output: false
    }

//    public static boolean canMakeEqual(String[] words) {
//        Map<Character, Integer> charCount = new HashMap<>();
//
//        // Count occurrences of each character in all strings
//        for (String word : words) {
//            for (char c : word.toCharArray()) {
//                charCount.put(c, charCount.getOrDefault(c, 0) + 1);
//            }
//        }
//
//        // Check if the counts are the same for all characters
//        for (int count : charCount.values()) {
//            if (count % words.length != 0) {
//                return false;
//            }
//        }
//
//        return true;
//    }

    public static boolean canMakeEqual(String[] words) {
        int[] charCount = new int[26]; // Масив фіксованого розміру для лічильників символів

        // Count occurrences of each character in all strings
        for (String word : words) {
            for (char c : word.toCharArray()) {
                charCount[c - 'a']++;
            }
        }

        // Check if the counts are the same for all characters
        for (int count : charCount) {
            if (count % words.length != 0) {
                return false;
            }
        }

        return true;
    }

}


//Задача полягає в тому, щоб перевірити, чи можна зробити всі рядки однаковими, переміщуючи символи між рядками.
//Ми використовуємо мапу (charCount), щоб підрахувати кількість кожного символу у всіх рядках. Спочатку ми
// проходимо всі рядки та для кожного символу в кожному рядку збільшуємо його лічильник у мапі.
//Після цього ми перевіряємо, чи кількість кожного символу є однаковою для всіх рядків, і якщо ні, то
// повертаємо false, оскільки не можна зробити всі рядки однаковими. Якщо кількість кожного символу ділиться
// на кількість рядків без залишку, то ми продовжуємо перевірку.
//Якщо жоден символ не має непарної кількості входжень серед усіх рядків, то можна зробити всі рядки
// однаковими, і повертаємо true.
//Наприклад, для вхідних даних ["abc", "aabc", "bc"], ми підраховуємо кількість кожного
// символу: {'a': 2, 'b': 3, 'c': 3}. У цьому випадку кількість кожного символу ділиться на кількість рядків
// без залишку, тому можна зробити всі рядки однаковими.


//You are given an array of strings words (0-indexed).
//In one operation, pick two distinct indices i and j, where words[i] is a non-empty string, and move any
// character from words[i] to any position in words[j].
//Return true if you can make every string in words equal using any number of operations, and false otherwise.

//Example 1:
//Input: words = ["abc","aabc","bc"]
//Output: true
//Explanation: Move the first 'a' in words[1] to the front of words[2],
//to make words[1] = "abc" and words[2] = "abc".
//All the strings are now equal to "abc", so return true.

//Example 2:
//Input: words = ["ab","a"]
//Output: false
//Explanation: It is impossible to make all the strings equal using the operation.

//Constraints:
//1 <= words.length <= 100
//1 <= words[i].length <= 100
//words[i] consists of lowercase English letters.
