package P1301_1400.P1400_Construct_K_Palindrom_Strings;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.canConstruct("annabelle", 2)); // true
        System.out.println(solution.canConstruct("leetcode", 3)); // false
        System.out.println(solution.canConstruct("true", 4)); // true
    }

    public boolean canConstruct(String s, int k) {
        if (k > s.length()) return false;

        Map<Character, Integer> charFrequency = new HashMap<>();

        // count the frequency of each character
        for (char c : s.toCharArray()) {
            charFrequency.put(c, charFrequency.getOrDefault(c, 0) + 1);
        }

        // count characters with odd frequencies
        int oddCount = 0;
        for (int freq : charFrequency.values()) {
            if (freq % 2 != 0) oddCount++;
        }

        // check if we can form k palindromes
        return oddCount <= k;
    }
}

//Explanation:
//- Counting Characters: Use a HashMap to count the occurrences of each character.
//- Odd Frequencies: The number of odd-frequency characters is computed by iterating over the frequency map.
//- Validation: If k ≥ oddCount and k ≤ s.length(), we return true.
//Complexity:
//Time Complexity:
//O(n), where n is the length of s.
//Counting character frequencies takes O(n).
//Checking odd counts takes
//O(26)=O(1) since the input consists of only lowercase English letters.
//Space Complexity:
//O(1) or O(26)=O(1) for the frequency map.


//Given a string s and an integer k, return true if you can use all the characters in s to construct k palindrome
// strings or false otherwise.
//
//Example 1:
//Input: s = "annabelle", k = 2
//Output: true
//Explanation: You can construct two palindromes using all characters in s.
//Some possible constructions "anna" + "elble", "anbna" + "elle", "anellena" + "b"

//Example 2:
//Input: s = "leetcode", k = 3
//Output: false
//Explanation: It is impossible to construct 3 palindromes using all the characters of s.

//Example 3:
//Input: s = "true", k = 4
//Output: true
//Explanation: The only possible solution is to put each character in a separate string.
//
//Constraints:
//1 <= s.length <= 105
//s consists of lowercase English letters.
//1 <= k <= 105
