package P3101_3200.P3170_Lexicographically_Minimum_String_After_Removing_Stars;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.clearStars("aaba*")); // Output: "aab"
        System.out.println(solution.clearStars("abc"));   // Output: "abc"
        System.out.println(solution.clearStars("cbacd*bb*")); // Output: "acbb"
        System.out.println(solution.clearStars("de*")); // Output: "e"
    }

    public String clearStars(String s) {
        char[] ans = s.toCharArray();
        List<Deque<Integer>> buckets = new ArrayList<>();

        // initialize 26 buckets for 'a' to 'z'
        for (int i = 0; i < 26; i++) {
            buckets.add(new ArrayDeque<>());
        }

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '*') {
                ans[i] = ' ';
                int j = 0;
                while (buckets.get(j).isEmpty()) {
                    j++;
                }
                int idx = buckets.get(j).removeLast(); // remove the most recent of smallest character
                ans[idx] = ' ';
            } else {
                buckets.get(ch - 'a').add(i);
            }
        }

        // build final string without ' ' characters
        StringBuilder result = new StringBuilder();
        for (char c : ans) {
            if (c != ' ') {
                result.append(c);
            }
        }

        return result.toString();
    }

}


//Explanation
//buckets[0] → stores indices of 'a'
//buckets[1] → 'b' … up to 'z'
//When we find '*', we:
//  Replace '*' with ' ' in the result array
//  Find the first non-empty smallest character bucket
//  Remove the most recent index (rightmost to left)
//  Set that index in result array to ' '
//Then remove all ' ' when building the final string.
//Complexity:
// time and space - O(n)


//You are given a string s. It may contain any number of '*' characters. Your task is to remove all '*' characters.
//While there is a '*', do the following operation:
//Delete the leftmost '*' and the smallest non-'*' character to its left. If there are several smallest characters,
// you can delete any of them.
//Return the lexicographically smallest resulting string after removing all '*' characters.

//Example 1:
//Input: s = "aaba*"
//Output: "aab"
//Explanation:
//We should delete one of the 'a' characters with '*'. If we choose s[3], s becomes the lexicographically smallest.

//Example 2:
//Input: s = "abc"
//Output: "abc"
//Explanation:
//There is no '*' in the string.

//Constraints:
//1 <= s.length <= 105
//s consists only of lowercase English letters and '*'.
//The input is generated such that it is possible to delete all '*' characters.
