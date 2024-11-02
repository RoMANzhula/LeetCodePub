package P2401_2500.P2490_Circular_Sentence;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.isCircularSentence("leetcode exercises sound delightful")); // true
        System.out.println(solution.isCircularSentence("eetcode")); // true
        System.out.println(solution.isCircularSentence("Leetcode is cool")); // false
    }

//    public boolean isCircularSentence(String sentence) {
//        // Split the sentence into words
//        String[] words = sentence.split(" ");
//
//        // Iterate through each word
//        for (int i = 0; i < words.length; i++) {
//            // Get the last character of the current word
//            char lastCharCurrent = words[i].charAt(words[i].length() - 1);
//            // Get the first character of the next word (with wrap-around for circular condition)
//            char firstCharNext = words[(i + 1) % words.length].charAt(0);
//
//            // Check if last character of current word matches the first character of the next word
//            if (lastCharCurrent != firstCharNext) {
//                return false; // Not circular if any condition fails
//            }
//        }
//        return true; // The sentence is circular if all checks pass
//    }

    //faster solution
    public boolean isCircularSentence(String sentence) {
        // Initialize pointers and variables
        int length = sentence.length();

        // Check if the sentence contains only one word
        if (sentence.charAt(0) != sentence.charAt(length - 1)) {
            return false;
        }

        // Iterate through the sentence to check consecutive word boundaries
        for (int i = 0; i < length - 1; i++) {
            // Find each space, which indicates a boundary between two words
            if (sentence.charAt(i) == ' ') {
                // Check if the last character of the previous word matches the first character of the next word
                if (sentence.charAt(i - 1) != sentence.charAt(i + 1)) {
                    return false;
                }
            }
        }

        return true; // The sentence is circular if all checks pass
    }

}

//Explanation
//Initial Check for One-Word Circularity: We first check if the first character of the sentence matches
// the last character, which is needed if the sentence contains only one word.
//Iterate for Boundaries: We go through each character, checking for spaces to identify word boundaries.
//Check Character Match: For each boundary (where there's a space), we compare the last character of the
//preceding word and the first character of the following word.


//A sentence is a list of words that are separated by a single space with no leading or trailing spaces.
//For example, "Hello World", "HELLO", "hello world hello world" are all sentences.
//Words consist of only uppercase and lowercase English letters. Uppercase and lowercase English letters are
// considered different.
//A sentence is circular if:
//The last character of a word is equal to the first character of the next word.
//The last character of the last word is equal to the first character of the first word.
//For example, "leetcode exercises sound delightful", "eetcode", "leetcode eats soul" are all circular
// sentences. However, "Leetcode is cool", "happy Leetcode", "Leetcode" and "I like Leetcode" are not
// circular sentences.
//Given a string sentence, return true if it is circular. Otherwise, return false.

//Example 1:
//Input: sentence = "leetcode exercises sound delightful"
//Output: true
//Explanation: The words in sentence are ["leetcode", "exercises", "sound", "delightful"].
//- leetcode's last character is equal to exercises's first character.
//- exercises's last character is equal to sound's first character.
//- sound's last character is equal to delightful's first character.
//- delightful's last character is equal to leetcode's first character.
//The sentence is circular.

//Example 2:
//Input: sentence = "eetcode"
//Output: true
//Explanation: The words in sentence are ["eetcode"].
//- eetcode's last character is equal to eetcode's first character.
//The sentence is circular.

//Example 3:
//Input: sentence = "Leetcode is cool"
//Output: false
//Explanation: The words in sentence are ["Leetcode", "is", "cool"].
//- Leetcode's last character is not equal to is's first character.
//The sentence is not circular.
//
//Constraints:
//1 <= sentence.length <= 500
//sentence consist of only lowercase and uppercase English letters and spaces.
//The words in sentence are separated by a single space.
//There are no leading or trailing spaces.
