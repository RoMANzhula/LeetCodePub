package P2201_2300.P2273_Find_Resultant_Array_After_Removing_Anagrams;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String[] words1 = {"abba","baba","bbaa","cd","cd"};
        System.out.println(solution.removeAnagrams(words1)); // [abba, cd]

        String[] words2 = {"a","b","c","d","e"};
        System.out.println(solution.removeAnagrams(words2)); // [a, b, c, d, e]
    }

    public List<String> removeAnagrams(String[] words) {
        List<String> stack = new ArrayList<>();

        for (String word : words) {
            if (!stack.isEmpty() && isAnagram(stack.get(stack.size() - 1), word)) {
                // remove the current word (do nothing, just skip adding)
                continue;
            } else {
                stack.add(word);
            }
        }

        return stack;
    }

    private boolean isAnagram(String a, String b) {
        if (a.length() != b.length()) return false;

        int[] count = new int[26];
        for (char c : a.toCharArray()) count[c - 'a']++;
        for (char c : b.toCharArray()) count[c - 'a']--;

        for (int x : count)
            if (x != 0) return false;
        return true;
    }

}

//Complexity:
// time - O(n * m)
// space - O(n)


//You are given a 0-indexed string array words, where words[i] consists of lowercase English letters.
//In one operation, select any index i such that 0 < i < words.length and words[i - 1] and words[i] are anagrams, and
// delete words[i] from words. Keep performing this operation as long as you can select an index that satisfies
// the conditions.
//Return words after performing all operations. It can be shown that selecting the indices for each operation in
// any arbitrary order will lead to the same result.
//An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase using all the
// original letters exactly once. For example, "dacb" is an anagram of "abdc".

//Example 1:
//Input: words = ["abba","baba","bbaa","cd","cd"]
//Output: ["abba","cd"]
//Explanation:
//One of the ways we can obtain the resultant array is by using the following operations:
//- Since words[2] = "bbaa" and words[1] = "baba" are anagrams, we choose index 2 and delete words[2].
//  Now words = ["abba","baba","cd","cd"].
//- Since words[1] = "baba" and words[0] = "abba" are anagrams, we choose index 1 and delete words[1].
//  Now words = ["abba","cd","cd"].
//- Since words[2] = "cd" and words[1] = "cd" are anagrams, we choose index 2 and delete words[2].
//  Now words = ["abba","cd"].
//We can no longer perform any operations, so ["abba","cd"] is the final answer.

//Example 2:
//Input: words = ["a","b","c","d","e"]
//Output: ["a","b","c","d","e"]
//Explanation:
//No two adjacent strings in words are anagrams of each other, so no operations are performed.

//Constraints:
//1 <= words.length <= 100
//1 <= words[i].length <= 10
//words[i] consists of lowercase English letters.
