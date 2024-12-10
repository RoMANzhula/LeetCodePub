package P2901_3000.P2981_Find_Longest_Special_Substring_That_Occurs_Thrice_I;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.maximumLength("aaaa"));   // Output: 2
        System.out.println(solution.maximumLength("abcdef")); // Output: -1
        System.out.println(solution.maximumLength("abcaba")); // Output: 1
    }

//    public int maximumLength(String s) {
//        int n = s.length();
//        int maxLength = -1;
//
//        // Iterate over all possible lengths for substrings
//        for (int len = 1; len <= n; len++) {
//            // Use a map to count occurrences of substrings
//            Map<String, Integer> substringCount = new HashMap<>();
//
//            // Generate substrings of the current length
//            for (int i = 0; i <= n - len; i++) {
//                String substring = s.substring(i, i + len);
//
//                // Check if the substring is "special"
//                if (isSpecial(substring)) {
//                    substringCount.put(substring, substringCount.getOrDefault(substring, 0) + 1);
//                }
//            }
//
//            // Check for substrings that occur at least three times
//            for (Map.Entry<String, Integer> entry : substringCount.entrySet()) {
//                if (entry.getValue() >= 3) {
//                    maxLength = Math.max(maxLength, entry.getKey().length());
//                }
//            }
//        }
//
//        return maxLength;
//    }
//
//    // Helper function to check if a substring is "special"
//    private static boolean isSpecial(String str) {
//        char firstChar = str.charAt(0);
//        for (char c : str.toCharArray()) {
//            if (c != firstChar) {
//                return false;
//            }
//        }
//        return true;
//    }

    // faster solution

    public int maximumLength(String s) {
        int n = s.length();
        int left = 1, right = n, result = -1;

        // Perform binary search on substring length
        while (left <= right) {
            int mid = (left + right) / 2;

            if (hasSpecialSubstringOfLength(s, mid)) {
                result = mid; // Update result if valid substring found
                left = mid + 1; // Search for a longer substring
            } else {
                right = mid - 1; // Search for a shorter substring
            }
        }

        return result;
    }

    // Check if a special substring of the given length occurs at least 3 times
    private static boolean hasSpecialSubstringOfLength(String s, int len) {
        Map<String, Integer> substringCount = new HashMap<>();
        int n = s.length();

        for (int i = 0; i <= n - len; i++) {
            String substring = s.substring(i, i + len);

            if (isSpecial(substring)) {
                substringCount.put(substring, substringCount.getOrDefault(substring, 0) + 1);

                if (substringCount.get(substring) >= 3) {
                    return true;
                }
            }
        }

        return false;
    }

    // Helper function to check if a substring is "special"
    private static boolean isSpecial(String str) {
        char firstChar = str.charAt(0);
        for (char c : str.toCharArray()) {
            if (c != firstChar) {
                return false;
            }
        }
        return true;
    }
}

//Key Optimizations:
//Binary Search on Length:
//Instead of testing all lengths from 1 to
//n, we use binary search to find the longest valid length, reducing complexity.
//Efficient Counting with Hashing:
//By storing substring counts in a hash map, we avoid recomputing and focus only on valid substrings.
//Special Substring Check:
//Only substrings composed of a single character are considered, drastically reducing the number of checks.
//Complexity:
//Time Complexity:
//Binary search runs inO(log n).
//For each length, substring extraction and counting take
//O(n), leading to a total complexity ofO(n log n).
//Space Complexity:
//Hash map storage uses O(n).


//You are given a string s that consists of lowercase English letters.
//A string is called special if it is made up of only a single character. For example, the string "abc" is
// not special, whereas the strings "ddd", "zz", and "f" are special.
//Return the length of the longest special substring of s which occurs at least thrice, or -1 if no special
// substring occurs at least thrice.
//A substring is a contiguous non-empty sequence of characters within a string.
//
//Example 1:
//Input: s = "aaaa"
//Output: 2
//Explanation: The longest special substring which occurs thrice is "aa": substrings "aaaa", "aaaa", and "aaaa".
//It can be shown that the maximum length achievable is 2.

//Example 2:
//Input: s = "abcdef"
//Output: -1
//Explanation: There exists no special substring which occurs at least thrice. Hence return -1.

//Example 3:
//Input: s = "abcaba"
//Output: 1
//Explanation: The longest special substring which occurs thrice is "a": substrings "abcaba", "abcaba", and "abcaba".
//It can be shown that the maximum length achievable is 1.
//
//Constraints:
//3 <= s.length <= 50
//s consists of only lowercase English letters.
