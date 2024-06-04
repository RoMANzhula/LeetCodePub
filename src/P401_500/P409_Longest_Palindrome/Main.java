package P401_500.P409_Longest_Palindrome;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        String s1 = "abccccdd";
        System.out.println("Longest palindrome length: " + solution.longestPalindrome(s1));  // Output: 7

        String s2 = "a";
        System.out.println("Longest palindrome length: " + solution.longestPalindrome(s2));  // Output: 1
    }

    public int longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        HashMap<Character, Integer> charCountMap = new HashMap<>();

        // Count the frequency of each character
        for (char c : s.toCharArray()) {
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        }

        int length = 0;
        boolean oddCountFound = false;

        // Calculate the maximum possible length of a palindrome
        for (int count : charCountMap.values()) {
            // Add the largest even number of the count
            length += (count / 2) * 2;
            // Check if there is an odd count character
            if (count % 2 == 1) {
                oddCountFound = true;
            }
        }

        // If there was any character with an odd count, we can place exactly one in the center
        if (oddCountFound) {
            length += 1;
        }

        return length;
    }
}


//Given a string s which consists of lowercase or uppercase letters, return the length of the longest
//palindrome that can be built with those letters.
//Letters are case sensitive, for example, "Aa" is not considered a palindrome.
//
//Example 1:
//Input: s = "abccccdd"
//Output: 7
//Explanation: One longest palindrome that can be built is "dccaccd", whose length is 7.

//Example 2:
//Input: s = "a"
//Output: 1
//Explanation: The longest palindrome that can be built is "a", whose length is 1.
//
//Constraints:
//1 <= s.length <= 2000
//s consists of lowercase and/or uppercase English letters only.