package P2401_2500.P2452_Words_Within_Two_Edits_of_Dictionary;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String[] queries1 = {"word", "note", "ants", "wood"};
        String[] dict1 = {"wood", "joke", "moat"};
        System.out.println(solution.twoEditWords(queries1, dict1)); // Output: [word, note, wood]

        String[] queries2 = {"yes"};
        String[] dict2 = {"not"};
        System.out.println(solution.twoEditWords(queries2, dict2)); // output: []
    }

    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> result = new ArrayList<>();

        for (String query : queries) {
            if (canMatch(query, dictionary)) {
                result.add(query);
            }
        }

        return result;
    }

    private boolean canMatch(String query, String[] dictionary) {
        for (String word : dictionary) {
            int diff = 0;

            for (int i = 0; i < query.length(); i++) {
                if (query.charAt(i) != word.charAt(i)) {
                    diff++;
                    if (diff > 2) break; // early stop
                }
            }

            if (diff <= 2) return true;
        }

        return false;
    }

}

//Complexity:
// time - O(queries.length * dictionary.length * word.length)
// space - O(1)


//You are given two string arrays, queries and dictionary. All words in each array comprise of lowercase English
// letters and have the same length.
//In one edit you can take a word from queries, and change any letter in it to any other letter. Find all
// words from queries that, after a maximum of two edits, equal some word from dictionary.
//Return a list of all words from queries, that match with some word from dictionary after a maximum of two
// edits. Return the words in the same order they appear in queries.

//Example 1:
//Input: queries = ["word","note","ants","wood"], dictionary = ["wood","joke","moat"]
//Output: ["word","note","wood"]
//Explanation:
//- Changing the 'r' in "word" to 'o' allows it to equal the dictionary word "wood".
//- Changing the 'n' to 'j' and the 't' to 'k' in "note" changes it to "joke".
//- It would take more than 2 edits for "ants" to equal a dictionary word.
//- "wood" can remain unchanged (0 edits) and match the corresponding dictionary word.
//Thus, we return ["word","note","wood"].

//Example 2:
//Input: queries = ["yes"], dictionary = ["not"]
//Output: []
//Explanation:
//Applying any two edits to "yes" cannot make it equal to "not". Thus, we return an empty array.

//Constraints:
//1 <= queries.length, dictionary.length <= 100
//n == queries[i].length == dictionary[j].length
//1 <= n <= 100
//All queries[i] and dictionary[j] are composed of lowercase English letters.
