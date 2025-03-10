package P1_100.P3_Longest_Substring_Without_Repeating_Characters;

import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.lengthOfLongestSubstring("abcabcbb")); // Output: 3
        System.out.println(solution.lengthOfLongestSubstring("bbbbb")); // Output: 1
        System.out.println(solution.lengthOfLongestSubstring("pwwkew")); // Output: 3
    }

    public int lengthOfLongestSubstring(String s) {
        int numberLettersInString = s.length(); // create a variable equal to the number of characters in the string we're working with
        Set<Character> setForUnique = new HashSet<>(); // create a Set for unique characters, which we'll check for duplicates
        int maxLengthForUniqueRow = 0; // container to store the length of the longest unique substring (in numerical value)
        int startPositionSubstring = 0; // variable to store the starting position of the substring we're looking for
        int endPositionSubstring = 0; // variable to store the ending position of the substring we're looking for

        while (startPositionSubstring < numberLettersInString && endPositionSubstring < numberLettersInString) { // start a loop,
            // which will run as long as the starting and ending positions of the substring are less than the length of the string
            // we're working with – i.e., we run the loop to find a substring without repeating characters
            if (!setForUnique.contains(s.charAt(endPositionSubstring))) { // if the current character is not in our unique Set, then
                setForUnique.add(s.charAt(endPositionSubstring++)); // add it to the Set and increase the value of the end position of the substring
                maxLengthForUniqueRow = Math.max(maxLengthForUniqueRow, endPositionSubstring - startPositionSubstring); // assign
                // the value of the variable for storing the maximum length of the unique string = the length of the current substring without repeating
                // characters
            } else { // otherwise
                setForUnique.remove(s.charAt(startPositionSubstring++)); // remove the character from our Set and increase the value
                // of the variable for storing the starting position of the desired substring to get the next substring
            }
        }

        return maxLengthForUniqueRow; // return the longest substring without repeating characters
    }

}


//Given a string s, find the length of the longest substring without duplicate characters.

//Example 1:
//Input: s = "abcabcbb"
//Output: 3
//Explanation: The answer is "abc", with the length of 3.

//Example 2:
//Input: s = "bbbbb"
//Output: 1
//Explanation: The answer is "b", with the length of 1.

//Example 3:
//Input: s = "pwwkew"
//Output: 3
//Explanation: The answer is "wke", with the length of 3.
//Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.

//Constraints:
//0 <= s.length <= 5 * 104
//s consists of English letters, digits, symbols and spaces.
