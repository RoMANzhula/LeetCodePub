package P1201_1300.P1239_Maximum_Length_of_a_Concatenated_String_with_Unique_Characters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> arr1 = Arrays.asList("un", "iq", "ue");
        System.out.println(maxLength(arr1)); // Output: 4

        List<String> arr2 = Arrays.asList("cha", "r", "act", "ers");
        System.out.println(maxLength(arr2)); // Output: 6

        List<String> arr3 = Collections.singletonList("abcdefghijklmnopqrstuvwxyz");
        System.out.println(maxLength(arr3)); // Output: 26
    }

    public static int maxLength(List<String> arr) {
        List<Integer> masks = new ArrayList<>();

        // Convert each string to a bitmask
        for (String s : arr) {
            int mask = 0;
            for (char c : s.toCharArray()) {
                int bit = 1 << (c - 'a');
                // Check if the character is repeated in the string
                if ((mask & bit) != 0) {
                    mask = 0; // Set mask to 0 if repeated characters are found
                    break;
                }
                mask |= bit;
            }
            if (mask != 0) {
                masks.add(mask);
            }
        }

        return backtrack(0, 0, masks);
    }

    private static int backtrack(int index, int currentMask, List<Integer> masks) {
        int maxLength = Integer.bitCount(currentMask);

        for (int i = index; i < masks.size(); i++) {
            // Check if the current mask and the new mask have no common characters
            if ((currentMask & masks.get(i)) == 0) {
                maxLength = Math.max(maxLength, backtrack(i + 1, currentMask | masks.get(i), masks));
            }
        }

        return maxLength;
    }
}

//Задача полягає у знаходженні максимально можливої довжини рядка, який можна сформувати, конкатенуючи
// підрядки заданого масиву зі стрічок так, що кожен підрядок містить унікальні символи.
//
//Для вирішення цієї задачі використовується підхід з бітовими масками для представлення наявності символів
// у підрядках. Ідея полягає в тому, щоб пройти через всі можливі підрядки та перевірити, чи мають вони унікальні
// символи. Якщо так, то оновлюємо максимальну довжину відповідно.

//You are given an array of strings arr. A string s is formed by the concatenation of a subsequence of
// arr that has unique characters.
//
//Return the maximum possible length of s.
//
//A subsequence is an array that can be derived from another array by deleting some or no elements without
// changing the order of the remaining elements.

//Example 1:
//Input: arr = ["un","iq","ue"]
//Output: 4
//Explanation: All the valid concatenations are:
//- ""
//- "un"
//- "iq"
//- "ue"
//- "uniq" ("un" + "iq")
//- "ique" ("iq" + "ue")
//Maximum length is 4.

//Example 2:
//Input: arr = ["cha","r","act","ers"]
//Output: 6
//Explanation: Possible longest valid concatenations are "chaers" ("cha" + "ers") and "acters" ("act" + "ers").
//Example 3:
//
//Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
//Output: 26
//Explanation: The only string in arr has all 26 characters.

//Constraints:
//1 <= arr.length <= 16
//1 <= arr[i].length <= 26
//arr[i] contains only lowercase English letters.