package P2401_2500.P2416_Sum_of_Prefix_Scores_of_Strings;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        String[] words1 = {"abc", "ab", "bc", "b"};
        int[] result1 = solution.sumPrefixScores(words1);
        System.out.println("Test case 1: ");
        for (int score : result1) {
            System.out.print(score + " ");
        }
        System.out.println(); // Output: [5, 4, 3, 2]

        String[] words2 = {"abcd"};
        int[] result2 = solution.sumPrefixScores(words2);
        System.out.println("Test case 2: ");
        for (int score : result2) {
            System.out.print(score + " ");
        }
        System.out.println(); // Output: [4]
    }

    public int[] sumPrefixScores(String[] words) {
        Trie trie = new Trie();

        // Insert all words into the trie
        for (String word : words) {
            trie.insert(word);
        }

        // Calculate the score for each word based on its prefixes
        int[] result = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            result[i] = trie.getPrefixScore(words[i]);
        }

        return result;
    }
}

// TrieNode class representing each node in the trie
class TrieNode {
    Map<Character, TrieNode> children;
    int count; // To store how many words have passed through this node (prefix count)

    TrieNode() {
        children = new HashMap<>();
        count = 0;
    }
}

// Trie class to insert words and calculate prefix counts
class Trie {
    TrieNode root;

    Trie() {
        root = new TrieNode();
    }

    // Insert a word into the trie and update prefix counts
    void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (!node.children.containsKey(c)) {
                node.children.put(c, new TrieNode());
            }
            node = node.children.get(c);
            node.count++; // Increment count for each prefix
        }
    }

    // Get the sum of counts for all prefixes of the word
    int getPrefixScore(String word) {
        TrieNode node = root;
        int score = 0;
        for (char c : word.toCharArray()) {
            if (node.children.containsKey(c)) {
                node = node.children.get(c);
                score += node.count;
            } else {
                break;
            }
        }
        return score;
    }
}

//Explanation:
//Trie Structure:
//We define a TrieNode class with two fields:
//children: A map that points to the child nodes corresponding to each character.
//count: The number of words passing through this node, which helps track how many words have this particular prefix.
//Insert Operation:
//For each word, we traverse through the characters and add them to the Trie. At each node, we increment the
// count to record how many words share the current prefix.
//Prefix Score Calculation:
//
//To compute the score of a word, we traverse the Trie for each character of the word and accumulate the
// counts stored at the nodes corresponding to each prefix.
//Complexity:
//Time complexity:
//Building the Trie takes O(n×m), where
//n is the number of words and
//m is the average length of the words.
//Calculating the prefix score for all words also takes O(n×m), as we traverse each word's characters.
//Thus, the total time complexity is O(n×m), which is efficient given the constraints.


//You are given an array words of size n consisting of non-empty strings.
//We define the score of a string word as the number of strings words[i] such that word is a prefix of words[i].
//For example, if words = ["a", "ab", "abc", "cab"], then the score of "ab" is 2, since "ab" is a
// prefix of both "ab" and "abc".
//Return an array answer of size n where answer[i] is the sum of scores of every non-empty prefix of words[i].
//Note that a string is considered as a prefix of itself.
//
//Example 1:
//Input: words = ["abc","ab","bc","b"]
//Output: [5,4,3,2]
//Explanation: The answer for each string is the following:
//- "abc" has 3 prefixes: "a", "ab", and "abc".
//- There are 2 strings with the prefix "a", 2 strings with the prefix "ab", and 1 string with the prefix "abc".
//The total is answer[0] = 2 + 2 + 1 = 5.
//- "ab" has 2 prefixes: "a" and "ab".
//- There are 2 strings with the prefix "a", and 2 strings with the prefix "ab".
//The total is answer[1] = 2 + 2 = 4.
//- "bc" has 2 prefixes: "b" and "bc".
//- There are 2 strings with the prefix "b", and 1 string with the prefix "bc".
//The total is answer[2] = 2 + 1 = 3.
//- "b" has 1 prefix: "b".
//- There are 2 strings with the prefix "b".
//The total is answer[3] = 2.

//Example 2:
//Input: words = ["abcd"]
//Output: [4]
//Explanation:
//"abcd" has 4 prefixes: "a", "ab", "abc", and "abcd".
//Each prefix has a score of one, so the total is answer[0] = 1 + 1 + 1 + 1 = 4.
//
//Constraints:
//1 <= words.length <= 1000
//1 <= words[i].length <= 1000
//words[i] consists of lowercase English letters.
