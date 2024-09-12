package P1601_1700.P1684_Count_the_Number_of_Consistent_Strings;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        String allowed1 = "ab";
        String[] words1 = {"ad", "bd", "aaab", "baa", "badab"};
        System.out.println(solution.countConsistentStrings(allowed1, words1)); // Output: 2

        String allowed2 = "abc";
        String[] words2 = {"a", "b", "c", "ab", "ac", "bc", "abc"};
        System.out.println(solution.countConsistentStrings(allowed2, words2)); // Output: 7

        String allowed3 = "cad";
        String[] words3 = {"cc", "acd", "b", "ba", "bac", "bad", "ac", "d"};
        System.out.println(solution.countConsistentStrings(allowed3, words3)); // Output: 4
    }

    public int countConsistentStrings(String allowed, String[] words) {
        // Create a set of allowed characters for fast lookup
        Set<Character> allowedSet = new HashSet<>();
        for (char c : allowed.toCharArray()) {
            allowedSet.add(c);
        }

        int count = 0;
        // Iterate over each word in the words array
        for (String word : words) {
            boolean isConsistent = true;
            // Check if all characters in the word are in the allowed set
            for (char c : word.toCharArray()) {
                if (!allowedSet.contains(c)) {
                    isConsistent = false;
                    break;
                }
            }
            if (isConsistent) {
                count++;
            }
        }
        return count;
    }

}

//Explanation:
//Allowed Set: First, we create a set of characters from the allowed string. This set allows us to efficiently
// check if a character is in allowed.
//Word Check: For each word in the words array, we check if all characters in the word exist in the allowed set. If
// any character in the word is not in the set, that word is marked as inconsistent.
//Count Consistent Words: For each consistent word, we increase the count.
//Time Complexity:
//Building the allowedSet takes O(allowed.length).
//Checking each word takes O(word.length), so the total complexity is O(n * m), where n is the number of words, and
// m is the average length of each word. This is efficient given the constraints.


//You are given a string allowed consisting of distinct characters and an array of strings words. A string is
// consistent if all characters in the string appear in the string allowed.
//Return the number of consistent strings in the array words.
//
//Example 1:
//Input: allowed = "ab", words = ["ad","bd","aaab","baa","badab"]
//Output: 2
//Explanation: Strings "aaab" and "baa" are consistent since they only contain characters 'a' and 'b'.

//Example 2:
//Input: allowed = "abc", words = ["a","b","c","ab","ac","bc","abc"]
//Output: 7
//Explanation: All strings are consistent.

//Example 3:
//Input: allowed = "cad", words = ["cc","acd","b","ba","bac","bad","ac","d"]
//Output: 4
//Explanation: Strings "cc", "acd", "ac", and "d" are consistent.
//
//Constraints:
//1 <= words.length <= 104
//1 <= allowed.length <= 26
//1 <= words[i].length <= 10
//The characters in allowed are distinct.
//words[i] and allowed contain only lowercase English letters.
