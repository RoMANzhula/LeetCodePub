package P1401_1500.P1408_String_Matching_an_an_Array;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        String[] words1 = {"mass", "as", "hero", "superhero"};
        System.out.println(solution.stringMatching(words1)); // Output: ["as", "hero"]

        String[] words2 = {"leetcode", "et", "code"};
        System.out.println(solution.stringMatching(words2)); // Output: ["et", "code"]

        String[] words3 = {"blue", "green", "bu"};
        System.out.println(solution.stringMatching(words3)); // Output: []

    }

    public List<String> stringMatching(String[] words) {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (i != j && words[j].contains(words[i])) {
                    result.add(words[i]);
                    break;
                }
            }
        }


        return result;
    }
}

//Complexity:
//Time Complexity:
//O(n^2 * k), where n is the number of words and k is the average length of words. The O(k) comes from
// the contains() method.
//Space Complexity:
///O(n), where n is the number of words added to the result list.


//Given an array of string words, return all strings in words that is a substring of another word. You can
// return the answer in any order.
//A substring is a contiguous sequence of characters within a string
//
//Example 1:
//Input: words = ["mass","as","hero","superhero"]
//Output: ["as","hero"]
//Explanation: "as" is substring of "mass" and "hero" is substring of "superhero".
//["hero","as"] is also a valid answer.

//Example 2:
//Input: words = ["leetcode","et","code"]
//Output: ["et","code"]
//Explanation: "et", "code" are substring of "leetcode".

//Example 3:
//Input: words = ["blue","green","bu"]
//Output: []
//Explanation: No string of words is substring of another string.
//
//Constraints:
//1 <= words.length <= 100
//1 <= words[i].length <= 30
//words[i] contains only lowercase English letters.
//All the strings of words are unique.