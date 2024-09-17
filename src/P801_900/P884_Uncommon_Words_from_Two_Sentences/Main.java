package P801_900.P884_Uncommon_Words_from_Two_Sentences;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        String s1 = "this apple is sweet";
        String s2 = "this apple is sour";
        System.out.println(Arrays.toString(solution.uncommonFromSentences(s1, s2))); // Output: ["sweet", "sour"]

        String s1_2 = "apple apple";
        String s2_2 = "banana";
        System.out.println(Arrays.toString(solution.uncommonFromSentences(s1_2, s2_2))); // Output: ["banana"]
    }

    public String[] uncommonFromSentences(String s1, String s2) {
        // Step 1: Split both sentences into words
        String[] words1 = s1.split(" ");
        String[] words2 = s2.split(" ");

        // Step 2: Use a HashMap to store the frequency of each word
        Map<String, Integer> wordCount = new HashMap<>();

        // Step 3: Count words in both sentences
        for (String word : words1) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        for (String word : words2) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        // Step 4: Collect words that appear exactly once
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            if (entry.getValue() == 1) {
                result.add(entry.getKey());
            }
        }

        // Step 5: Convert the list to a String array and return it
        return result.toArray(new String[0]);
    }
}


//A sentence is a string of single-space separated words where each word consists only of lowercase letters.
//A word is uncommon if it appears exactly once in one of the sentences, and does not appear in the other sentence.
//Given two sentences s1 and s2, return a list of all the uncommon words. You may return the answer in any order.
//
//Example 1:
//Input: s1 = "this apple is sweet", s2 = "this apple is sour"
//Output: ["sweet","sour"]
//Explanation:
//The word "sweet" appears only in s1, while the word "sour" appears only in s2.
//
//Example 2:
//Input: s1 = "apple apple", s2 = "banana"
//Output: ["banana"]
//
//Constraints:
//1 <= s1.length, s2.length <= 200
//s1 and s2 consist of lowercase English letters and spaces.
//s1 and s2 do not have leading or trailing spaces.
//All the words in s1 and s2 are separated by a single space.
