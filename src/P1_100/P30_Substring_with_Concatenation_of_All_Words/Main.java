package P1_100.P30_Substring_with_Concatenation_of_All_Words;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String s = "barfoofoobarthefoobarman";
        String[] words = {"bar", "foo", "the"};
        System.out.println(solution.findSubstring(s, words)); // Output: [6, 9, 12]
    }

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();

        if (s == null || words == null || words.length == 0) return result;

        int wordLength = words[0].length();
        int wordsCount = words.length;
        int totalLength = wordLength * wordsCount;

        if (s.length() < totalLength) return result;

        // build frequency map for the words array
        Map<String, Integer> wordMap = new HashMap<>();
        for (String word : words)
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);

        // we check for all possible starting points in wordLength range
        for (int i = 0; i < wordLength; i++) {
            int left = i, count = 0;
            Map<String, Integer> windowMap = new HashMap<>();

            // slide over the string
            for (int right = i; right + wordLength <= s.length(); right += wordLength) {
                String word = s.substring(right, right + wordLength);

                if (wordMap.containsKey(word)) {
                    windowMap.put(word, windowMap.getOrDefault(word, 0) + 1);
                    count++;

                    // if more than required, move the left pointer
                    while (windowMap.get(word) > wordMap.get(word)) {
                        String leftWord = s.substring(left, left + wordLength);
                        windowMap.put(leftWord, windowMap.get(leftWord) - 1);
                        count--;
                        left += wordLength;
                    }

                    // found valid concatenation
                    if (count == wordsCount)
                        result.add(left);
                } else {
                    //reset window if word not in words list
                    windowMap.clear();
                    count = 0;
                    left = right + wordLength;
                }
            }
        }

        return result;
    }

}

//Explanation:
//wordMap stores the frequency of each word.
//The window slides across the string in steps of wordLength.
//If a word not in words[] is encountered, the window resets.
//If a valid window is found (all words used exactly as required), the starting index is recorded.
//Complexity:
// time - O(n * words.length)
// space - O(m)  m-uniqie words


//You are given a string s and an array of strings words. All the strings of words are of the same length.
//A concatenated string is a string that exactly contains all the strings of any permutation of words concatenated.
//For example, if words = ["ab","cd","ef"], then "abcdef", "abefcd", "cdabef", "cdefab", "efabcd", and "efcdab" are
// all concatenated strings. "acdbef" is not a concatenated string because it is not the concatenation of any
// permutation of words.
//Return an array of the starting indices of all the concatenated substrings in s. You can return the
// answer in any order.

//Example 1:
//Input: s = "barfoothefoobarman", words = ["foo","bar"]
//Output: [0,9]
//Explanation:
//The substring starting at 0 is "barfoo". It is the concatenation of ["bar","foo"] which is a permutation of words.
//The substring starting at 9 is "foobar". It is the concatenation of ["foo","bar"] which is a permutation of words.

//Example 2:
//Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
//Output: []
//Explanation:
//There is no concatenated substring.

//Example 3:
//Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
//Output: [6,9,12]
//Explanation:
//The substring starting at 6 is "foobarthe". It is the concatenation of ["foo","bar","the"].
//The substring starting at 9 is "barthefoo". It is the concatenation of ["bar","the","foo"].
//The substring starting at 12 is "thefoobar". It is the concatenation of ["the","foo","bar"].

//Constraints:
//1 <= s.length <= 104
//1 <= words.length <= 5000
//1 <= words[i].length <= 30
//s and words[i] consist of lowercase English letters.
