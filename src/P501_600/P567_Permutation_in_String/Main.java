package P501_600.P567_Permutation_in_String;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.checkInclusion("ab", "eidbaooo")); // Output: true
        System.out.println(solution.checkInclusion("ab", "eidboaoo")); // Output: false
    }

    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();

        if (len1 > len2) {
            return false;
        }

        // Frequency arrays for s1 and the current window in s2
        int[] countS1 = new int[26];
        int[] countS2 = new int[26];

        // Fill the frequency array for s1 and the first window of s2
        for (int i = 0; i < len1; i++) {
            countS1[s1.charAt(i) - 'a']++;
            countS2[s2.charAt(i) - 'a']++;
        }

        // Check if the first window is a permutation
        if (Arrays.equals(countS1, countS2)) {
            return true;
        }

        // Sliding window
        for (int i = len1; i < len2; i++) {
            // Add the next character to the window
            countS2[s2.charAt(i) - 'a']++;
            // Remove the character that's no longer in the window
            countS2[s2.charAt(i - len1) - 'a']--;

            // Check if the current window is a permutation
            if (Arrays.equals(countS1, countS2)) {
                return true;
            }
        }

        return false;
    }

}

//Explanation:
//Initialization:
//We create two arrays countS1 and countS2 of size 26 (for the 26 lowercase English letters) to track the
// frequency of characters in s1 and the current window of s2.
//First window check:
//We initially populate the frequency arrays with the characters of s1 and the first window of s2 of
// the same length as s1.
//If the frequency arrays are equal, it means we have found a permutation, and we return true.
//Sliding window:
//As we slide through s2, we update the frequency array by adding the next character and removing the character
// that's sliding out of the window.
//After each update, we check if the frequency arrays match.
//Return:
//If no permutation is found by the end of the loop, return false.

//Time Complexity:
//O(n) where n is the length of s2, because we are iterating through s2 with a sliding window of fixed size.
//Space Complexity:
//O(1) because the size of the frequency arrays is constant (26 elements).


//Given two strings s1 and s2, return true if s2 contains a
//permutation
// of s1, or false otherwise.
//In other words, return true if one of s1's permutations is the substring of s2.
//
//Example 1:
//Input: s1 = "ab", s2 = "eidbaooo"
//Output: true
//Explanation: s2 contains one permutation of s1 ("ba").

//Example 2:
//Input: s1 = "ab", s2 = "eidboaoo"
//Output: false
//
//Constraints:
//1 <= s1.length, s2.length <= 104
//s1 and s2 consist of lowercase English letters.
