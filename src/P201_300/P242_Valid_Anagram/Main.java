package P201_300.P242_Valid_Anagram;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        System.out.println(isAnagram("anagram", "nagaram")); // true
        System.out.println(isAnagram("rat", "car")); // false
    }

    public static boolean isAnagram(String s, String t) {
        char[] chArray1 = s.toCharArray();
        char[] chArray2 = t.toCharArray();

        Arrays.sort(chArray1);
        Arrays.sort(chArray2);

        return Arrays.equals(chArray1, chArray2);
    }
}

//Given two strings s and t, return true if t is an anagram of s, and false otherwise.
//An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically
// using all the original letters exactly once.

//Example 1:
//Input: s = "anagram", t = "nagaram"
//Output: true

//Example 2:
//Input: s = "rat", t = "car"
//Output: false

//Constraints:
//1 <= s.length, t.length <= 5 * 104
//s and t consist of lowercase English letters.
