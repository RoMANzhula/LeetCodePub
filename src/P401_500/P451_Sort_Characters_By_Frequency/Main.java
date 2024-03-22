package P401_500.P451_Sort_Characters_By_Frequency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Main sorter = new Main();

        System.out.println(sorter.frequencySort("tree")); // Output: "eetr" or "eert"
        System.out.println(sorter.frequencySort("cccaaa")); // Output: "aaaccc" or "cccaaa"
        System.out.println(sorter.frequencySort("Aabb")); // Output: "bbAa" or "bbaA"
    }

    public String frequencySort(String s) {
        // count frequencies of character
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char ch : s.toCharArray()) {
            frequencyMap.put(ch, frequencyMap.getOrDefault(ch, 0) + 1);
        }

        // create list of chars sorted by frequency
        List<Character> sortedChars = new ArrayList<>(frequencyMap.keySet());
        sortedChars.sort((a, b) -> frequencyMap.get(b) - frequencyMap.get(a));


        // build the sorted str
        StringBuilder sortedStr = new StringBuilder();
        for (char ch : sortedChars) {
            int frequency = frequencyMap.get(ch);
            sortedStr.append(String.valueOf(ch).repeat(Math.max(0, frequency)));
        }
            // analog
        //StringBuilder sortedStr = new StringBuilder();
        //        for (char ch : sortedChars) {
        //            int frequency = frequencyMap.get(ch);
        //            for (int i = 0; i < frequency; i++) {
        //                sortedStr.append(ch);
        //            }
        //        }

        return sortedStr.toString();

    }
}

//Ця задача передбачає сортування символів у рядку за їх частотою зустрічання. Основна ідея полягає в тому,
// щоб підрахувати кількість кожного символу в рядку, потім відсортувати символи за їхніми частотами та зібрати
// рядок з урахуванням цього сортування.
//Спочатку створюємо мапу для зберігання частот кожного символу. Проходимося по рядку та для кожного
// символу збільшуємо відповідний лічильник у мапі.
//Потім створюємо список символів, який буде відсортований за їхніми частотами. Для цього копіюємо всі
// ключі (символи) з мапи у список та сортуємо їх, використовуючи зворотний порядок (від більшої до меншої частоти).
//Далі будуємо відсортований рядок. Проходимося по відсортованому списку символів і для кожного символу
// додаємо його до вихідного рядка з урахуванням його частоти.
//Це розв'язання ефективно працює, оскільки використовує лише один прохід по рядку та має лінійну складність.

//Given a string s, sort it in decreasing order based on the frequency of the characters. The frequency of
// a character is the number of times it appears in the string.
//Return the sorted string. If there are multiple answers, return any of them.

//Example 1:
//Input: s = "tree"
//Output: "eert"
//Explanation: 'e' appears twice while 'r' and 't' both appear once.
//So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.

//Example 2:
//Input: s = "cccaaa"
//Output: "aaaccc"
//Explanation: Both 'c' and 'a' appear three times, so both "cccaaa" and "aaaccc" are valid answers.
//Note that "cacaca" is incorrect, as the same characters must be together.

//Example 3:
//Input: s = "Aabb"
//Output: "bbAa"
//Explanation: "bbaA" is also a valid answer, but "Aabb" is incorrect.
//Note that 'A' and 'a' are treated as two different characters.

//Constraints:
//1 <= s.length <= 5 * 105
//s consists of uppercase and lowercase English letters and digits.
