package P601_700.P648_Replace_Words;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        List<String> dictionary1 = Arrays.asList("cat", "bat", "rat");
        String sentence1 = "the cattle was rattled by the battery";
        System.out.println(solution.replaceWords(dictionary1, sentence1)); // Output: "the cat was rat by the bat"

        List<String> dictionary2 = Arrays.asList("a", "b", "c");
        String sentence2 = "aadsfasf absbs bbab cadsfafs";
        System.out.println(solution.replaceWords(dictionary2, sentence2)); // Output: "a a b c"
    }

//    public String replaceWords(List<String> dictionary, String sentence) {
//        // Create a set for fast lookup of roots
//        Set<String> roots = new HashSet<>(dictionary);
//
//        // Split the sentence into words
//        String[] words = sentence.split(" ");
//
//        // StringBuilder to build the resulting sentence
//        StringBuilder result = new StringBuilder();
//
//        // Iterate through each word in the sentence
//        for (String word : words) {
//            String prefix = "";
//            // Check each prefix of the word to see if it's in the roots set
//            for (int i = 1; i <= word.length(); i++) {
//                prefix = word.substring(0, i);
//                if (roots.contains(prefix)) {
//                    break;
//                }
//            }
//            // If no root was found, prefix will be the word itself
//            result.append(prefix).append(" ");
//        }
//
//        // Remove the trailing space and return the result
//        return result.toString().trim();
//    }

        // Faster solution
    // Trie Node definition
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word;
    }

    // Function to insert a word into the Trie
    private static void insertTrie(TrieNode root, String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new TrieNode();
            }
            node = node.children[c - 'a'];
        }
        node.word = word;
    }

    // Function to find the shortest root for a word
    private static String searchTrie(TrieNode root, String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.children[c - 'a'] == null) {
                return word;
            }
            node = node.children[c - 'a'];
            if (node.word != null) {
                return node.word;
            }
        }
        return word;
    }

    public static String replaceWords(List<String> dictionary, String sentence) {
        TrieNode root = new TrieNode();

        // Build the Trie from the dictionary
        for (String word : dictionary) {
            insertTrie(root, word);
        }

        // Split the sentence into words
        String[] words = sentence.split(" ");

        // Use a StringBuilder to construct the result
        StringBuilder result = new StringBuilder();

        // Replace each word in the sentence with the shortest root
        for (String word : words) {
            if (result.length() > 0) {
                result.append(" ");
            }
            result.append(searchTrie(root, word));
        }

        return result.toString();
    }
}

//Пояснення:
//Префіксне дерево (Trie):
//Створюється клас TrieNode, який містить масив дочірніх вузлів і слово.
//Функція insertTrie вставляє слово в Trie.
//Функція searchTrie шукає найкоротший префікс для даного слова.
//Побудова Trie:
//Для кожного слова з словника викликається insertTrie, щоб додати його в Trie.
//Заміна слів:
//Розбиваємо речення на слова.
//Для кожного слова шукаємо найкоротший префікс за допомогою searchTrie.
//Будуємо нове речення, додаючи слова або їх префікси.
//Цей підхід значно ефективніший, особливо для великих вхідних даних, оскільки пошук в Trie виконується швидше, ніж
// перевірка кожного можливого префіксу окремо.

//In English, we have a concept called root, which can be followed by some other word to form another longer
// word - let's call this word derivative. For example, when the root "help" is followed by the word "ful", we
// can form a derivative "helpful".
//Given a dictionary consisting of many roots and a sentence consisting of words separated by spaces, replace
// all the derivatives in the sentence with the root forming it. If a derivative can be replaced by more than
// one root, replace it with the root that has the shortest length.
//Return the sentence after the replacement.
//
//Example 1:
//Input: dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
//Output: "the cat was rat by the bat"

//Example 2:
//Input: dictionary = ["a","b","c"], sentence = "aadsfasf absbs bbab cadsfafs"
//Output: "a a b c"
//
//Constraints:
//1 <= dictionary.length <= 1000
//1 <= dictionary[i].length <= 100
//dictionary[i] consists of only lower-case letters.
//1 <= sentence.length <= 106
//sentence consists of only lower-case letters and spaces.
//The number of words in sentence is in the range [1, 1000]
//The length of each word in sentence is in the range [1, 1000]
//Every two consecutive words in sentence will be separated by exactly one space.
//sentence does not have leading or trailing spaces.