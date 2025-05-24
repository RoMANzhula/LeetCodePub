package P2901_3000.P2942_Find_Words_Containing_Character;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String[] words1 = {"leet","code"};
        char x1 = 'e';
        System.out.println(solution.findWordsContaining(words1, x1)); // output: [0,1]

        String[] words2 = {"abc","bcd","aaaa","cbc"};
        char x2 = 'a';
        System.out.println(solution.findWordsContaining(words2, x2)); // output: [0,2]

        String[] words3 = {"abc","bcd","aaaa","cbc"};
        char x3 = 'z';
        System.out.println(solution.findWordsContaining(words3, x3)); // output: []
    }

    public List<Integer> findWordsContaining(String[] words, char x) {
        List<Integer> wordsContainingChar = new ArrayList<>();

        for (int i = 0; i <= words.length - 1; i++) {
            if (words[i].indexOf(x) != -1) {
                wordsContainingChar.add(i);
            }
        }

        return wordsContainingChar;
    }

}

//Complexity:
// time - O(n * m) (m - for all elements of world)
// space - O(n)


//You are given a 0-indexed array of strings words and a character x.
//Return an array of indices representing the words that contain the character x.
//Note that the returned array may be in any order.

//Example 1:
//Input: words = ["leet","code"], x = "e"
//Output: [0,1]
//Explanation: "e" occurs in both words: "leet", and "code". Hence, we return indices 0 and 1.

//Example 2:
//Input: words = ["abc","bcd","aaaa","cbc"], x = "a"
//Output: [0,2]
//Explanation: "a" occurs in "abc", and "aaaa". Hence, we return indices 0 and 2.

//Example 3:
//Input: words = ["abc","bcd","aaaa","cbc"], x = "z"
//Output: []
//Explanation: "z" does not occur in any of the words. Hence, we return an empty array.

//Constraints:
//1 <= words.length <= 50
//1 <= words[i].length <= 50
//x is a lowercase English letter.
//words[i] consists only of lowercase English letters.
