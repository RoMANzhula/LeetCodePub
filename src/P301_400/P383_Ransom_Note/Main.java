package P301_400.P383_Ransom_Note;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        System.out.println(canConstruct("caa", "aca"));
    }

    public static boolean canConstruct(String ransomNote, String magazine) {

        Map<Character, Integer> charCount = new HashMap<>();

        for (char c : magazine.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }

        for (char c : ransomNote.toCharArray()) {
            if (!charCount.containsKey(c) || charCount.get(c) == 0) {
                return false;
            }
            charCount.put(c, charCount.get(c) - 1);
        }

        return true;

    }

}


//Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from
// magazine and false otherwise.
//Each letter in magazine can only be used once in ransomNote.

//Example 1:
//Input: ransomNote = "a", magazine = "b"
//Output: false

//Example 2:
//Input: ransomNote = "aa", magazine = "ab"
//Output: false

//Example 3:
//Input: ransomNote = "aa", magazine = "aab"
//Output: true

//Constraints:
//1 <= ransomNote.length, magazine.length <= 105
//ransomNote and magazine consist of lowercase English letters.