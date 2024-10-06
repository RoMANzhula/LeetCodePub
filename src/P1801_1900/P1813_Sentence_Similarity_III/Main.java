package P1801_1900.P1813_Sentence_Similarity_III;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        String sentence1 = "My name is Haley";
        String sentence2 = "My Haley";
        System.out.println(solution.areSentencesSimilar(sentence1, sentence2)); // true

        sentence1 = "of";
        sentence2 = "A lot of words";
        System.out.println(solution.areSentencesSimilar(sentence1, sentence2)); // false

        sentence1 = "Eating right now";
        sentence2 = "Eating";
        System.out.println(solution.areSentencesSimilar(sentence1, sentence2)); // true
    }

    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        // Split both sentences into words
        String[] words1 = sentence1.split(" ");
        String[] words2 = sentence2.split(" ");

        // Determine which sentence is shorter
        if (words1.length > words2.length) {
            // Swap to always have words1 as the shorter sentence
            return areSentencesSimilar(sentence2, sentence1);
        }

        // Two pointers, one for the start, one for the end
        int start = 0;
        int end = 0;

        // Compare from the start
        while (start < words1.length && words1[start].equals(words2[start])) {
            start++;
        }

        // Compare from the end
        while (end < words1.length && words1[words1.length - 1 - end].equals(words2[words2.length - 1 - end])) {
            end++;
        }

        // Check if the entire shorter sentence matches in the range
        return start + end >= words1.length;
    }

}

//Explanation:
//Splitting the sentences: We split both sentence1 and sentence2 into arrays of words using split(" ").
//Finding common parts: We use two pointers, one starting from the beginning (start) and another starting from
// the end (end), to count how many words match from both directions.
//Checking conditions: The two sentences are similar if the number of matching words from the beginning plus
// the number of matching words from the end is greater than or equal to the length of the shorter sentence (words1).
//Time Complexity:
//The time complexity of the solution is O(n), where n is the number of words in the longer sentence. This is
// because we only make a single pass over the words from both the start and the end of the sentences.
//Space Complexity:
//The space complexity is O(n) due to the space required to store the split words from both sentences.


//You are given two strings sentence1 and sentence2, each representing a sentence composed of words. A sentence is
// a list of words that are separated by a single space with no leading or trailing spaces. Each word consists
// of only uppercase and lowercase English characters.
//Two sentences s1 and s2 are considered similar if it is possible to insert an arbitrary sentence (possibly empty)
// inside one of these sentences such that the two sentences become equal. Note that the inserted sentence must be
// separated from existing words by spaces.

//For example,
//s1 = "Hello Jane" and s2 = "Hello my name is Jane" can be made equal by inserting "my name is" between "Hello" and
// "Jane" in s1.
//s1 = "Frog cool" and s2 = "Frogs are cool" are not similar, since although there is a sentence "s are" inserted into
// s1, it is not separated from "Frog" by a space.
//Given two sentences sentence1 and sentence2, return true if sentence1 and sentence2 are similar.
// Otherwise, return false.
//
//Example 1:
//Input: sentence1 = "My name is Haley", sentence2 = "My Haley"
//Output: true
//Explanation:
//sentence2 can be turned to sentence1 by inserting "name is" between "My" and "Haley".
//
//Example 2:
//Input: sentence1 = "of", sentence2 = "A lot of words"
//Output: false
//Explanation:
//No single sentence can be inserted inside one of the sentences to make it equal to the other.
//
//Example 3:
//Input: sentence1 = "Eating right now", sentence2 = "Eating"
//Output: true
//Explanation:
//sentence2 can be turned to sentence1 by inserting "right now" at the end of the sentence.
//
//Constraints:
//1 <= sentence1.length, sentence2.length <= 100
//sentence1 and sentence2 consist of lowercase and uppercase English letters and spaces.
//The words in sentence1 and sentence2 are separated by a single space.
