package P2801_2900.P2900_Longest_Unequal_Adjacent_Groups_Subsequence_I;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String[] words1 = {"e", "a", "b"};
        int[] groups1 = {0, 0, 1};
        System.out.println(solution.getLongestSubsequence(words1, groups1)); // Example Output: [e, b]

        String[] words2 = {"a", "b", "c", "d"};
        int[] groups2 = {1, 0, 1, 1};
        System.out.println(solution.getLongestSubsequence(words2, groups2)); // Example Output: [a, b, c]
    }

    public List<String> getLongestSubsequence(String[] words, int[] groups) {
        List<String> result = new ArrayList<>();

        if (words.length == 0) return result;

        result.add(words[0]);
        int lastGroup = groups[0];

        for (int i = 1; i < words.length; i++) {
            if (groups[i] != lastGroup) {
                result.add(words[i]);
                lastGroup = groups[i];
            }
        }

        return result;
    }

}

//Complexity:
// time - O(n)
// space - O(n)


//You are given a string array words and a binary array groups both of length n, where words[i] is
// associated with groups[i].
//Your task is to select the longest alternating subsequence from words. A subsequence of words is alternating if
// for any two consecutive strings in the sequence, their corresponding elements in the binary array groups
// differ. Essentially, you are to choose strings such that adjacent elements have non-matching corresponding
// bits in the groups array.
//Formally, you need to find the longest subsequence of an array of indices [0, 1, ..., n - 1] denoted
// as [i0, i1, ..., ik-1], such that groups[ij] != groups[ij+1] for each 0 <= j < k - 1 and then find the words
// corresponding to these indices.
//Return the selected subsequence. If there are multiple answers, return any of them.
//Note: The elements in words are distinct.

//Example 1:
//Input: words = ["e","a","b"], groups = [0,0,1]
//Output: ["e","b"]
//Explanation: A subsequence that can be selected is ["e","b"] because groups[0] != groups[2]. Another
// subsequence that can be selected is ["a","b"] because groups[1] != groups[2]. It can be demonstrated that
// the length of the longest subsequence of indices that satisfies the condition is 2.

//Example 2:
//Input: words = ["a","b","c","d"], groups = [1,0,1,1]
//Output: ["a","b","c"]
//Explanation: A subsequence that can be selected is ["a","b","c"] because groups[0] != groups[1] and
// groups[1] != groups[2]. Another subsequence that can be selected is ["a","b","d"] because
// groups[0] != groups[1] and groups[1] != groups[3]. It can be shown that the length of the longest
// subsequence of indices that satisfies the condition is 3.

//Constraints:
//1 <= n == words.length == groups.length <= 100
//1 <= words[i].length <= 10
//groups[i] is either 0 or 1.
//words consists of distinct strings.
//words[i] consists of lowercase English letters.
