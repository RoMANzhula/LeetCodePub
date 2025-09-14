package P901_1000.P966_Vowel_Spellchecker;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String[] wordlist1 = {"KiTe","kite","hare","Hare"};
        String[] queries1 = {"kite","Kite","KiTe","Hare","HARE","Hear","hear","keti","keet","keto"};
        System.out.println(Arrays.toString(solution.spellchecker(wordlist1, queries1))); // Output: ["kite","KiTe","KiTe","Hare","hare","","","KiTe","","KiTe"]

        String[] wordlist2 = {"yellow"};
        String[] queries2 = {"YellOw"};
        System.out.println(Arrays.toString(solution.spellchecker(wordlist2, queries2))); // Output: ["yellow"]
    }

    private static final Set<Character> VOWELS = new HashSet<>(
            Arrays.asList('a', 'e', 'i', 'o', 'u'));

    public static String[] spellchecker(String[] wordlist, String[] queries) {
        Set<String> exactMatch = new HashSet<>();
        Map<String, String> caseInsensitive = new HashMap<>();
        Map<String, String> vowelInsensitive = new HashMap<>();

        // preprocess wordlist
        for (String word : wordlist) {
            exactMatch.add(word);

            String lower = word.toLowerCase();
            caseInsensitive.putIfAbsent(lower, word);

            String devoweled = devowel(lower);
            vowelInsensitive.putIfAbsent(devoweled, word);
        }

        String[] result = new String[queries.length];

        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];
            if (exactMatch.contains(query)) {
                // rule 1: exact match
                result[i] = query;
            } else {
                String lower = query.toLowerCase();
                if (caseInsensitive.containsKey(lower)) {
                    // rule 2: case insensitive match
                    result[i] = caseInsensitive.get(lower);
                } else {
                    String devoweled = devowel(lower);
                    if (vowelInsensitive.containsKey(devoweled)) {
                        // rule 3: vowel error match
                        result[i] = vowelInsensitive.get(devoweled);
                    } else {
                        // rule 4: no match
                        result[i] = "";
                    }
                }
            }
        }

        return result;
    }

    private static String devowel(String word) {
        StringBuilder sb = new StringBuilder();

        for (char c : word.toCharArray()) {
            if (VOWELS.contains(c)) {
                sb.append('*');
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

}

//Complexity:
// time - O((N + M) * L)
// space - O(N * L)


//Given a wordlist, we want to implement a spellchecker that converts a query word into a correct word.
//For a given query word, the spell checker handles two categories of spelling mistakes:
//Capitalization: If the query matches a word in the wordlist (case-insensitive), then the query word is
// returned with the same case as the case in the wordlist.
//Example: wordlist = ["yellow"], query = "YellOw": correct = "yellow"
//Example: wordlist = ["Yellow"], query = "yellow": correct = "Yellow"
//Example: wordlist = ["yellow"], query = "yellow": correct = "yellow"
//Vowel Errors: If after replacing the vowels ('a', 'e', 'i', 'o', 'u') of the query word with any vowel
// individually, it matches a word in the wordlist (case-insensitive), then the query word is returned with
// the same case as the match in the wordlist.
//Example: wordlist = ["YellOw"], query = "yollow": correct = "YellOw"
//Example: wordlist = ["YellOw"], query = "yeellow": correct = "" (no match)
//Example: wordlist = ["YellOw"], query = "yllw": correct = "" (no match)
//In addition, the spell checker operates under the following precedence rules:
//When the query exactly matches a word in the wordlist (case-sensitive), you should return the same word back.
//When the query matches a word up to capitlization, you should return the first such match in the wordlist.
//When the query matches a word up to vowel errors, you should return the first such match in the wordlist.
//If the query has no matches in the wordlist, you should return the empty string.
//Given some queries, return a list of words answer, where answer[i] is the correct word for query = queries[i].

//Example 1:
//Input: wordlist = ["KiTe","kite","hare","Hare"],
// queries = ["kite","Kite","KiTe","Hare","HARE","Hear","hear","keti","keet","keto"]
//Output: ["kite","KiTe","KiTe","Hare","hare","","","KiTe","","KiTe"]
//Example 2:
//Input: wordlist = ["yellow"], queries = ["YellOw"]
//Output: ["yellow"]

//Constraints:
//1 <= wordlist.length, queries.length <= 5000
//1 <= wordlist[i].length, queries[i].length <= 7
//wordlist[i] and queries[i] consist only of only English letters.
