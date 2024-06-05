package P1001_1100.P1002_Find_Common_Characters;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        String[] words1 = {"bella", "label", "roller"};
        System.out.println(solution.commonChars(words1)); // Output: [e, l, l]

        String[] words2 = {"cool", "lock", "cook"};
        System.out.println(solution.commonChars(words2)); // Output: [c, o]
    }

    public List<String> commonChars(String[] words) {
        // Initialize the array to keep track of minimum frequency of each character
        int[] minFreq = new int[26];
        for (int i = 0; i < 26; i++) {
            minFreq[i] = Integer.MAX_VALUE;
        }

        // Iterate over each word to update the minimum frequency array
        for (String word : words) {
            int[] charCount = new int[26];
            for (char c : word.toCharArray()) {
                charCount[c - 'a']++;
            }
            for (int i = 0; i < 26; i++) {
                minFreq[i] = Math.min(minFreq[i], charCount[i]);
            }
        }

        // Construct the result list using the minimum frequency array
        List<String> result = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < minFreq[i]; j++) {
                result.add(String.valueOf((char) (i + 'a')));
            }
        }

        return result;
    }
}


//Given a string array words, return an array of all characters that show up in all strings within the
// words (including duplicates). You may return the answer in any order.
//
//Example 1:
//Input: words = ["bella","label","roller"]
//Output: ["e","l","l"]

//Example 2:
//Input: words = ["cool","lock","cook"]
//Output: ["c","o"]
//
//Constraints:
//1 <= words.length <= 100
//1 <= words[i].length <= 100
//words[i] consists of lowercase English letters.