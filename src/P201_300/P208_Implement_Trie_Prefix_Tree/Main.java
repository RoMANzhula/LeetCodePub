package P201_300.P208_Implement_Trie_Prefix_Tree;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        solution.insert("apple");

        System.out.println(solution.search("apple"));    // true
        System.out.println(solution.search("app"));      // false
        System.out.println(solution.startsWith("app"));  // true

        solution.insert("app");

        System.out.println(solution.search("app"));      // true
    }

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEndOfWord;
    }

    private final TrieNode root;

    public Main() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode current = root;

        for (char c : word.toCharArray()) {
            int index = c - 'a';

            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }

            current = current.children[index];
        }

        current.isEndOfWord = true;
    }

    public boolean search(String word) {
        TrieNode node = findNode(word);

        return node != null && node.isEndOfWord;
    }

    public boolean startsWith(String prefix) {
        return findNode(prefix) != null;
    }

    private TrieNode findNode(String str) {
        TrieNode current = root;

        for (char c : str.toCharArray()) {
            int index = c - 'a';

            if (current.children[index] == null) {
                return null;
            }

            current = current.children[index];
        }

        return current;
    }

}

//Complexity:
// time (average) - O(length of input string)
// space - O(n * 26)


//A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys
//in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.
//Implement the Trie class:
//Trie() Initializes the trie object.
//void insert(String word) Inserts the string word into the trie.
//boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
//boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix
// prefix, and false otherwise.

//Example 1:
//Input
//["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
//[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
//Output
//[null, null, true, false, true, null, true]
//Explanation
//Trie trie = new Trie();
//trie.insert("apple");
//trie.search("apple");   // return True
//trie.search("app");     // return False
//trie.startsWith("app"); // return True
//trie.insert("app");
//trie.search("app");     // return True

//Constraints:
//1 <= word.length, prefix.length <= 2000
//word and prefix consist only of lowercase English letters.
//At most 3 * 104 calls in total will be made to insert, search, and startsWith.
