package P2001_2100.P2014_Longest_Subsequence_Repeated_k_Times;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.longestSubsequenceRepeatedK("letsleetcode", 2));  // Output: "let"
        System.out.println(solution.longestSubsequenceRepeatedK("bb", 2));            // Output: "b"
        System.out.println(solution.longestSubsequenceRepeatedK("ab", 2));            // Output: ""
    }

    public String longestSubsequenceRepeatedK(String s, int k) {
        // count frequency
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // candidates with enough frequency
        List<Character> candidates = new ArrayList<>();
        for (int i = 25; i >= 0; i--) {
            if (freq[i] >= k) {
                candidates.add((char) (i + 'a'));
            }
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer("");

        String result = "";

        while (!queue.isEmpty()) {
            String current = queue.poll();

            for (char c : candidates) {
                String next = current + c;

                // prune if next is not promising
                if (isKSubsequence(s, next, k)) {
                    queue.offer(next);

                    // update best result if next is longer or lexicographically larger
                    if (next.length() > result.length() ||
                            (next.length() == result.length() && next.compareTo(result) > 0)) {
                        result = next;
                    }
                }
            }
        }

        return result;
    }

    // check if t repeated k times is a subsequence of s
    private boolean isKSubsequence(String s, String t, int k) {
        int n = s.length();
        int m = t.length();
        int i = 0, j = 0, count = 0;

        while (i < n && count < k) {
            if (s.charAt(i) == t.charAt(j)) {
                j++;
                if (j == m) {
                    count++;
                    j = 0;
                }
            }

            i++;
        }

        return count == k;
    }

}

//Complexity:
// time - O(C^7 * n)    (C - chars <= 26)
// space - O(C^7 + n)


//You are given a string s of length n, and an integer k. You are tasked to find the longest subsequence repeated k
// times in string s.
//A subsequence is a string that can be derived from another string by deleting some or no characters without
// changing the order of the remaining characters.
//A subsequence seq is repeated k times in the string s if seq * k is a subsequence of s, where seq * k represents a
// string constructed by concatenating seq k times.
//For example, "bba" is repeated 2 times in the string "bababcba", because the string "bbabba", constructed by
// concatenating "bba" 2 times, is a subsequence of the string "bababcba".
//Return the longest subsequence repeated k times in string s. If multiple such subsequences are found, return the
// lexicographically largest one. If there is no such subsequence, return an empty string.

//Example 1:
//example 1
//Input: s = "letsleetcode", k = 2
//Output: "let"
//Explanation: There are two longest subsequences repeated 2 times: "let" and "ete".
//"let" is the lexicographically largest one.

//Example 2:
//Input: s = "bb", k = 2
//Output: "b"
//Explanation: The longest subsequence repeated 2 times is "b".

//Example 3:
//Input: s = "ab", k = 2
//Output: ""
//Explanation: There is no subsequence repeated 2 times. Empty string is returned.

//Constraints:
//n == s.length
//2 <= n, k <= 2000
//2 <= n < k * 8
//s consists of lowercase English letters.
