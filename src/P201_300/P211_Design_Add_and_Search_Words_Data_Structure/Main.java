package P201_300.P211_Design_Add_and_Search_Words_Data_Structure;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        solution.addWord("bad");
        solution.addWord("dad");
        solution.addWord("mad");

        System.out.println(solution.search("pad")); // false
        System.out.println(solution.search("bad")); // true
        System.out.println(solution.search(".ad")); // true
        System.out.println(solution.search("b..")); // true
    }

    private static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEndOfWord;
    }

    private final TrieNode root;

    public Main() {
        root = new TrieNode();
    }

    public void addWord(String word) {
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
        return search(word, 0, root);
    }

    private boolean search(String word, int index, TrieNode node) {
        if (index == word.length()) {
            return node.isEndOfWord;
        }

        char c = word.charAt(index);

        if (c == '.') {
            for (TrieNode child : node.children) {
                if (child != null && search(word, index + 1, child)) {
                    return true;
                }
            }

            return false;
        }

        int childIndex = c - 'a';
        TrieNode child = node.children[childIndex];

        return child != null && search(word, index + 1, child);
    }

}

//Complexity:
// time - O(26^d * l)
// space - O(n)
// l - length of the word
// n - total number of characters across all inserted words
// d - number of dots (.) in the search word (maximum 2)


//Design a data structure that supports adding new words and finding if a string matches any previously added string.
//Implement the WordDictionary class:
//WordDictionary() Initializes the object.
//void addWord(word) Adds word to the data structure, it can be matched later.
//bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word
// may contain dots '.' where dots can be matched with any letter.

//Example:
//Input
//["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
//[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
//Output
//[null,null,null,null,false,true,true,true]
//Explanation
//WordDictionary wordDictionary = new WordDictionary();
//wordDictionary.addWord("bad");
//wordDictionary.addWord("dad");
//wordDictionary.addWord("mad");
//wordDictionary.search("pad"); // return False
//wordDictionary.search("bad"); // return True
//wordDictionary.search(".ad"); // return True
//wordDictionary.search("b.."); // return True

//Constraints:
//1 <= word.length <= 25
//word in addWord consists of lowercase English letters.
//word in search consist of '.' or lowercase English letters.
//There will be at most 2 dots in word for search queries.
//At most 104 calls will be made to addWord and search.
