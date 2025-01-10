package P901_1000.P916_Word_Subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        String[] words1_1 = {"amazon","apple","facebook","google","leetcode"};
        String[] words1_2 = {"e","o"};
        System.out.println(solution.wordSubsets(words1_1, words1_2)); // ["facebook","google","leetcode"]

        String[] words2_1 = {"amazon","apple","facebook","google","leetcode"};
        String[] words2_2 = {"l","e"};
        System.out.println(solution.wordSubsets(words2_1, words2_2)); // ["apple","google","leetcode"]
    }

    public List<String> wordSubsets(String[] words1, String[] words2) {
        // Step 1: Calculate the maximum frequency of each characters int words2
        int[] maxCharFreq = new int[26];
        for (String word : words2) {
            int[] charFreq = getCharFrequency(word);
            for (int i = 0; i < 26; i++) {
                maxCharFreq[i] = Math.max(maxCharFreq[i], charFreq[i]);
            }
        }

        // step 2: Find all universal strings in words1
        List<String> result = new ArrayList<>();
        for (String word : words1) {
            int[] charFreq = getCharFrequency(word);

            if (isUniversal(charFreq, maxCharFreq)) {
                result.add(word);
            }
        }

        return result;
    }

    private boolean isUniversal(int[] wordFreq, int[] maxFreq) {
        for (int i = 0; i < 26; i++) {
            if (wordFreq[i] < maxFreq[i]) {
                return false;
            }
        }

        return true;
    }

    private int[] getCharFrequency(String word) {
        int[] freq = new int[26];

        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }

        return freq;
    }
}


//You are given two string arrays words1 and words2.
//A string b is a subset of string a if every letter in b occurs in a including multiplicity.
//For example, "wrr" is a subset of "warrior" but is not a subset of "world".
//A string a from words1 is universal if for every string b in words2, b is a subset of a.
//Return an array of all the universal strings in words1. You may return the answer in any order.
//
//Example 1:
//Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["e","o"]
//Output: ["facebook","google","leetcode"]

//Example 2:
//Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["l","e"]
//Output: ["apple","google","leetcode"]
//
//Constraints:
//1 <= words1.length, words2.length <= 104
//1 <= words1[i].length, words2[i].length <= 10
//words1[i] and words2[i] consist only of lowercase English letters.
//All the strings of words1 are unique.