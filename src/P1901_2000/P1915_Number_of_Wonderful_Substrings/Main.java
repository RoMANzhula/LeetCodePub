package P1901_2000.P1915_Number_of_Wonderful_Substrings;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        String word1 = "aba";
        String word2 = "aabb";
        String word3 = "he";

        System.out.println("Output for word1: " + solution.wonderfulSubstrings(word1)); // Output: 4
        System.out.println("Output for word2: " + solution.wonderfulSubstrings(word2)); // Output: 9
        System.out.println("Output for word3: " + solution.wonderfulSubstrings(word3)); // Output: 2
    }

    public long wonderfulSubstrings(String word) {
        int[] count = new int[1024]; // 2^10 for lowercase English letters from 'a' to 'j'
        count[0] = 1; // Empty string is wonderful
        long result = 0;
        int mask = 0;
        for (char c : word.toCharArray()) {
            mask ^= 1 << (c - 'a'); // Toggle the bit corresponding to the current character
            result += count[mask]; // Add the count of substrings ending at the current position
            for (int i = 0; i < 10; i++) {
                result += count[mask ^ (1 << i)]; // Add the count of substrings with at most one odd frequency
            }
            count[mask]++; // Increment the count of substrings ending at the current position
        }

        return result; // bingo
    }
}


//A wonderful string is a string where at most one letter appears an odd number of times.
//For example, "ccjjc" and "abab" are wonderful, but "ab" is not.
//Given a string word that consists of the first ten lowercase English letters ('a' through 'j'), return
// the number of wonderful non-empty substrings in word. If the same substring appears multiple times in
// word, then count each occurrence separately.
//A substring is a contiguous sequence of characters in a string.
//
//Example 1:
//Input: word = "aba"
//Output: 4
//Explanation: The four wonderful substrings are underlined below:
//- "aba" -> "a"
//- "aba" -> "b"
//- "aba" -> "a"
//- "aba" -> "aba"

//Example 2:
//Input: word = "aabb"
//Output: 9
//Explanation: The nine wonderful substrings are underlined below:
//- "aabb" -> "a"
//- "aabb" -> "aa"
//- "aabb" -> "aab"
//- "aabb" -> "aabb"
//- "aabb" -> "a"
//- "aabb" -> "abb"
//- "aabb" -> "b"
//- "aabb" -> "bb"
//- "aabb" -> "b"

//Example 3:
//Input: word = "he"
//Output: 2
//Explanation: The two wonderful substrings are underlined below:
//- "he" -> "h"
//- "he" -> "e"
//
//Constraints:
//1 <= word.length <= 105
//word consists of lowercase English letters from 'a' to 'j'.