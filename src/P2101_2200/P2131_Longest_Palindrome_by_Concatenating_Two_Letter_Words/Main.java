package P2101_2200.P2131_Longest_Palindrome_by_Concatenating_Two_Letter_Words;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.longestPalindrome(new String[]{"lc", "cl", "gg"})); // 6
        System.out.println(solution.longestPalindrome(new String[]{"ab", "ty", "yt", "lc", "cl", "ab"})); // 8
        System.out.println(solution.longestPalindrome(new String[]{"cc", "ll", "xx"})); // 2
    }

    public int longestPalindrome(String[] words) {
        Map<String, Integer> wordCount = new HashMap<>();
        int length = 0;
        boolean hasCentral = false;

        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        for (String word : wordCount.keySet()) {
            String reversed = new StringBuilder(word).reverse().toString();

            if (!word.equals(reversed)) {
                if (wordCount.containsKey(reversed)) {
                    int pairCount = Math.min(wordCount.get(word), wordCount.get(reversed));
                    length += pairCount * 4;
                    wordCount.put(word, wordCount.get(word) - pairCount);
                    wordCount.put(reversed, wordCount.get(reversed) - pairCount);
                }
            } else {
                int count = wordCount.get(word);
                length += (count / 2) * 4;

                if (count % 2 == 1) {
                    hasCentral = true;
                }
            }
        }

        if (hasCentral) {
            length += 2; // central word like "cc"
        }

        return length;
    }

}

//Complexity:
// time and space - O(n)


//You are given an array of strings words. Each element of words consists of two lowercase English letters.
//Create the longest possible palindrome by selecting some elements from words and concatenating them in any
// order. Each element can be selected at most once.
//Return the length of the longest palindrome that you can create. If it is impossible to create any
// palindrome, return 0.
//A palindrome is a string that reads the same forward and backward.

//Example 1:
//Input: words = ["lc","cl","gg"]
//Output: 6
//Explanation: One longest palindrome is "lc" + "gg" + "cl" = "lcggcl", of length 6.
//Note that "clgglc" is another longest palindrome that can be created.

//Example 2:
//Input: words = ["ab","ty","yt","lc","cl","ab"]
//Output: 8
//Explanation: One longest palindrome is "ty" + "lc" + "cl" + "yt" = "tylcclyt", of length 8.
//Note that "lcyttycl" is another longest palindrome that can be created.

//Example 3:
//Input: words = ["cc","ll","xx"]
//Output: 2
//Explanation: One longest palindrome is "cc", of length 2.
//Note that "ll" is another longest palindrome that can be created, and so is "xx".

//Constraints:
//1 <= words.length <= 105
//words[i].length == 2
//words[i] consists of lowercase English letters.
