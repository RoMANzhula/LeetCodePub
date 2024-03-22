package P1_100.P76_Minimum_Window_Substring;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC")); // Output: "BANC"
        System.out.println(minWindow("a", "a")); // Output: "a"
        System.out.println(minWindow("a", "aa")); // Output: ""
    }

    public static String minWindow(String s, String t) {
        if (s == null || t == null || s.isEmpty() || t.isEmpty() || s.length() < t.length()) {
            return "";
        }

        Map<Character, Integer> tFreqMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            tFreqMap.put(c, tFreqMap.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0;
        int minLen = Integer.MAX_VALUE;
        int minLeft = 0;
        int requiredChars = tFreqMap.size();

        while (right < s.length()) {
            char currentChar = s.charAt(right);

            if (tFreqMap.containsKey(currentChar)) {
                tFreqMap.put(currentChar, tFreqMap.get(currentChar) - 1);
                if (tFreqMap.get(currentChar) == 0) {
                    requiredChars--;
                }
            }

            while (requiredChars == 0) {
                if (right - left < minLen) {
                    minLen = right - left;
                    minLeft = left;
                }

                char leftChar = s.charAt(left);

                if (tFreqMap.containsKey(leftChar)) {
                    tFreqMap.put(leftChar, tFreqMap.get(leftChar) + 1);
                    if (tFreqMap.get(leftChar) > 0) {
                        requiredChars++;
                    }
                }

                left++;
            }

            right++;
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLen + 1);
    }
}

//Задача полягає у знаходженні найменшої підстрічки у рядку s, такої, що містить всі символи рядку t у
// будь-якому порядку (включаючи повторення). Якщи така підстрічка не знайдена, повертається порожній рядок.
//
//Алгоритм використовує два вказівника, left та right, які обходять рядок s. Ми використовуємо мапу для
// відстеження кількості символів, які мають бути знайдені у рядку t. Основна ідея полягає у тому, щоб
// розширювати вікно (right), доки не будуть знайдені всі символи з t, а потім зменшувати вікно (left),
// збільшуючи його, доки не будуть знову взяті всі необхідні символи.
//
//Коли знайдено вікно, його довжина порівнюється з мінімальною довжиною, збереженою до цього моменту.
// Якщо вікно коротше, воно стає новим кандидатом на відповідь.
//
//На завершення повертається знайдена мінімальна підстрічка або порожній рядок, якщо така підстрічка не існує.

//Given two strings s and t of lengths m and n respectively, return the minimum window
//substring
// of s such that every character in t (including duplicates) is included in the window. If there is no such
// substring, return the empty string "".
//The testcases will be generated such that the answer is unique.

//Example 1:
//Input: s = "ADOBECODEBANC", t = "ABC"
//Output: "BANC"
//Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.

//Example 2:
//Input: s = "a", t = "a"
//Output: "a"
//Explanation: The entire string s is the minimum window.

//Example 3:
//Input: s = "a", t = "aa"
//Output: ""
//Explanation: Both 'a's from t must be included in the window.
//Since the largest window of s only has one 'a', return empty string.

//Constraints:
//m == s.length
//n == t.length
//1 <= m, n <= 105
//s and t consist of uppercase and lowercase English letters.
