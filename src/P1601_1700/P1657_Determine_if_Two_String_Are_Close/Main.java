package P1601_1700.P1657_Determine_if_Two_String_Are_Close;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(closeStrings("abc", "bca")); // Output: true
        System.out.println(closeStrings("a", "aa")); // Output: false
        System.out.println(closeStrings("cabbba", "abbccc")); // Output: true
    }

//    public static boolean closeStrings(String word1, String word2) {
//        if (word1.length() != word2.length()) {
//            return false;
//        }
//
//        Map<Character, Integer> freqMap1 = new HashMap<>();
//        Map<Character, Integer> freqMap2 = new HashMap<>();
//
//        for (char c : word1.toCharArray()) {
//            freqMap1.put(c, freqMap1.getOrDefault(c, 0) + 1);
//        }
//
//        for (char c : word2.toCharArray()) {
//            freqMap2.put(c, freqMap2.getOrDefault(c, 0) + 1);
//        }
//
//        if (!freqMap1.keySet().equals(freqMap2.keySet())) {
//            return false;
//        }
//
//        int[] freqCount1 = new int[freqMap1.size()];
//        int[] freqCount2 = new int[freqMap2.size()];
//
//        int i = 0;
//        for (int count : freqMap1.values()) {
//            freqCount1[i++] = count;
//        }
//
//        i = 0;
//        for (int count : freqMap2.values()) {
//            freqCount2[i++] = count;
//        }
//
//        Arrays.sort(freqCount1);
//        Arrays.sort(freqCount2);
//
//        return Arrays.equals(freqCount1, freqCount2);
//    }

    //faster solution
    public static boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }

        int[] freqCount1 = new int[26];  // Assuming lowercase English letters
        int[] freqCount2 = new int[26];

        for (char c : word1.toCharArray()) {
            freqCount1[c - 'a']++;
        }

        for (char c : word2.toCharArray()) {
            freqCount2[c - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if ((freqCount1[i] == 0 && freqCount2[i] > 0) || (freqCount1[i] > 0 && freqCount2[i] == 0)) {
                return false;
            }
        }

        Arrays.sort(freqCount1);
        Arrays.sort(freqCount2);

        return Arrays.equals(freqCount1, freqCount2);
    }
}

//Задача полягає в тому, щоб визначити, чи можна перетворити одне слово у інше, використовуючи дві операції:
// обмін будь-яких двох існуючих символів або трансформацію кожного входження одного символу в інший символ, і
// зробити те саме з іншим символом.
//
//Наприклад, для слів "abc" і "bca" відповідь повинна бути true, оскільки можна виконати дві операції:
// "abc" -> "acb" -> "bca".
//
//Основна ідея розв'язку полягає у визначенні, чи мають обидва слова однакові символи та чи мають вони
// однакові частоти цих символів. Я використовую масиви для підрахунку частот символів у словах та
// порівнюю їхні масиви частот.
//
//Оптимізації включають використання масивів для підрахунку частот, уникання зайвого сортування, і
// відразу перевірка умов, які можуть вказувати на неможливість перетворення слів.


//Two strings are considered close if you can attain one from the other using the following operations:
//
//Operation 1: Swap any two existing characters.
//For example, abcde -> aecdb
//Operation 2: Transform every occurrence of one existing character into another existing character, and do
// the same with the other character.
//For example, aacabb -> bbcbaa (all a's turn into b's, and all b's turn into a's)
//You can use the operations on either string as many times as necessary.
//
//Given two strings, word1 and word2, return true if word1 and word2 are close, and false otherwise.

//Example 1:
//Input: word1 = "abc", word2 = "bca"
//Output: true
//Explanation: You can attain word2 from word1 in 2 operations.
//Apply Operation 1: "abc" -> "acb"
//Apply Operation 1: "acb" -> "bca"

//Example 2:
//Input: word1 = "a", word2 = "aa"
//Output: false
//Explanation: It is impossible to attain word2 from word1, or vice versa, in any number of operations.

//Example 3:
//Input: word1 = "cabbba", word2 = "abbccc"
//Output: true
//Explanation: You can attain word2 from word1 in 3 operations.
//Apply Operation 1: "cabbba" -> "caabbb"
//Apply Operation 2: "caabbb" -> "baaccc"
//Apply Operation 2: "baaccc" -> "abbccc"

//Constraints:
//1 <= word1.length, word2.length <= 105
//word1 and word2 contain only lowercase English letters.
