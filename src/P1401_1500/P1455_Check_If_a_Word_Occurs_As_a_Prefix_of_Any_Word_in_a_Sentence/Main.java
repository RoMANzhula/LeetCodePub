package P1401_1500.P1455_Check_If_a_Word_Occurs_As_a_Prefix_of_Any_Word_in_a_Sentence;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.isPrefixOfWord("i love eating burger", "burg")); // Output: 4
        System.out.println(solution.isPrefixOfWord("this problem is an easy problem", "pro")); // Output: 2
        System.out.println(solution.isPrefixOfWord("i am tired", "you")); // Output: -1
    }

    public int isPrefixOfWord(String sentence, String searchWord) {
        String[] array = sentence.split(" ");

        for (int i = 0; i < array.length; i++) {
            if (array[i].startsWith(searchWord)) {
                return i + 1;
            }
        }

        return -1;
    }

}

//Complexity:
//Time Complexity:
//O(n⋅m), where n is the number of words in the sentence and m is the length of the searchWord.
//Space Complexity:
//O(n), for storing the words from the split operation.


//Given a sentence that consists of some words separated by a single space, and a searchWord, check if
// searchWord is a prefix of any word in sentence.
//Return the index of the word in sentence (1-indexed) where searchWord is a prefix of this word. If searchWord
// is a prefix of more than one word, return the index of the first word (minimum index). If there is
// no such word return -1.
//A prefix of a string s is any leading contiguous substring of s.
//
//Example 1:
//Input: sentence = "i love eating burger", searchWord = "burg"
//Output: 4
//Explanation: "burg" is prefix of "burger" which is the 4th word in the sentence.

//Example 2:
//Input: sentence = "this problem is an easy problem", searchWord = "pro"
//Output: 2
//Explanation: "pro" is prefix of "problem" which is the 2nd and the 6th word in the sentence, but we
// return 2 as it's the minimal index.

//Example 3:
//Input: sentence = "i am tired", searchWord = "you"
//Output: -1
//Explanation: "you" is not a prefix of any word in the sentence.
//
//Constraints:
//1 <= sentence.length <= 100
//1 <= searchWord.length <= 10
//sentence consists of lowercase English letters and spaces.
//searchWord consists of lowercase English letters.