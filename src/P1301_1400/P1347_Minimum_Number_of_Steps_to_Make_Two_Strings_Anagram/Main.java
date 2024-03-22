package P1301_1400.P1347_Minimum_Number_of_Steps_to_Make_Two_Strings_Anagram;

public class Main {
    public static void main(String[] args) {
        System.out.println(minSteps("bab", "aba")); // Output: 1
        System.out.println(minSteps("leetcode", "practice")); // Output: 5
        System.out.println(minSteps("anagram", "mangaar")); // Output: 0
    }

    public static int minSteps(String s, String t) {
        if (s.length() != t.length()) {
            throw new IllegalArgumentException("Input strings must have the same length");
        }

//        // Create a frequency map for characters in s
//        Map<Character, Integer> frequencyMap = new HashMap<>();
//        for (char ch : s.toCharArray()) {
//            frequencyMap.put(ch, frequencyMap.getOrDefault(ch, 0) + 1);
//        }
//
//        // Count the differences in character frequencies between s and t
//        int steps = 0;
//        for (char ch : t.toCharArray()) {
//            if (frequencyMap.containsKey(ch) && frequencyMap.get(ch) > 0) {
//                frequencyMap.put(ch, frequencyMap.get(ch) - 1);
//            } else {
//                steps++; // Increment steps if the character is not present or already used up
//            }
//        }

            //faster solve
        // Create an array to store character frequencies for s
        int[] frequencyArray = new int[26]; // Assuming lowercase English letters only

        // Count the character frequencies in s
        for (char ch : s.toCharArray()) {
            frequencyArray[ch - 'a']++;
        }

        // Count the differences in character frequencies between s and t
        int steps = 0;
        for (char ch : t.toCharArray()) {
            int index = ch - 'a';
            if (index >= 0 && index < 26 && frequencyArray[index] > 0) {
                frequencyArray[index]--;
            } else {
                steps++; // Increment steps if the character is not present or already used up
            }
        }

        return steps;
    }

}

//You are given two strings of the same length s and t. In one step you can choose any character of
// t and replace it with another character.
//Return the minimum number of steps to make t an anagram of s.
//An Anagram of a string is a string that contains the same characters with a different (or the same) ordering.

//Example 1:
//Input: s = "bab", t = "aba"
//Output: 1
//Explanation: Replace the first 'a' in t with b, t = "bba" which is anagram of s.
//Example 2:
//Input: s = "leetcode", t = "practice"
//Output: 5
//Explanation: Replace 'p', 'r', 'a', 'i' and 'c' from t with proper characters to make t anagram of s.
//Example 3:
//Input: s = "anagram", t = "mangaar"
//Output: 0
//Explanation: "anagram" and "mangaar" are anagrams.

//Constraints:
//1 <= s.length <= 5 * 104
//s.length == t.length
//s and t consist of lowercase English letters only.
