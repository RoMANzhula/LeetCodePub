package P1101_1200.P1160_Find_Words_That_Can_Be_Formed_by_Characters;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String[] words1 = {"cat", "bt", "hat", "tree"};
        String chars1 = "atach";
        System.out.println(countCharacters(words1, chars1)); // Output: 6

        String[] words2 = {"hello", "world", "leetcode"};
        String chars2 = "welldonehoneyr";
        System.out.println(countCharacters(words2, chars2)); // Output: 10
    }

//    public static int countCharacters(String[] words, String chars) {
//        int result = 0;
//
//        Map<Character, Integer> charCount = new HashMap<>();
//
//        // Count the characters in chars
//        for (char c : chars.toCharArray()) {
//            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
//        }
//
//        // Check each word if it can be formed from chars
//        for (String word : words) {
//            if (canFormWord(word, new HashMap<>(charCount))) {
//                result += word.length();
//            }
//        }
//
//        return result;
//    }
//
//    private static boolean canFormWord(String word, Map<Character, Integer> charCount) {
//        for (char c : word.toCharArray()) {
//            if (!charCount.containsKey(c) || charCount.get(c) == 0) {
//                return false;
//            }
//            charCount.put(c, charCount.get(c) - 1);
//        }
//        return true;
//    }

    //// More quickly solution
    public static int countCharacters(String[] words, String chars) {
        int result = 0;

        int[] charCount = new int[26]; // Assuming only lowercase English letters

        // Count the characters in chars
        for (char c : chars.toCharArray()) {
            charCount[c - 'a']++;
        }

        // Check each word if it can be formed from chars
        for (String word : words) {
            if (canFormWord(word, charCount.clone())) {
                result += word.length();
            }
        }

        return result;
    }

    private static boolean canFormWord(String word, int[] charCount) {
        for (char c : word.toCharArray()) {
            if (charCount[c - 'a'] == 0) {
                return false;
            }
            charCount[c - 'a']--;
        }
        return true;
    }

}

//You are given an array of strings words and a string chars.
//A string is good if it can be formed by characters from chars (each character can only be used once).
//Return the sum of lengths of all good strings in words.

//Example 1:
//Input: words = ["cat","bt","hat","tree"], chars = "atach"
//Output: 6
//Explanation: The strings that can be formed are "cat" and "hat" so the answer is 3 + 3 = 6.

//Example 2:
//Input: words = ["hello","world","leetcode"], chars = "welldonehoneyr"
//Output: 10
//Explanation: The strings that can be formed are "hello" and "world" so the answer is 5 + 5 = 10.
//Constraints:
//1 <= words.length <= 1000
//1 <= words[i].length, chars.length <= 100
//words[i] and chars consist of lowercase English letters.
