package P1_100.P68_Text_Justification;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String[] words1 = {
                "Science", "is", "what", "we", "understand", "well", "enough", "to", "explain",
                "to", "a", "computer.", "Art", "is", "everything", "else", "we", "do"
        };
        int maxWidth1 = 20;
        System.out.println(fullJustify(words1, maxWidth1));
    }

    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>(); // creating a collection for the output result
        int startIndex = 0; // defining the starting index

        while (startIndex < words.length) { // performing calculations until we reach the end of the input words array
            int endIndex = findEndIndex(words, startIndex, maxWidth); // defining a variable and finding the last index in the line
            result.add(justifyLine(words, startIndex, endIndex, maxWidth)); // adding justified lines to the result collection
            // according to the conditions
            startIndex = endIndex + 1; // moving to the next line
        }

        return result; // returning the obtained result
    }

    private static int findEndIndex(String[] words, int startIndex, int maxWidth) { // method for finding the last word in a line
        int endIndex = startIndex; // setting the last word index as the first one (for cases with only one word)
        int currentWidth = words[endIndex].length(); // defining the current width of the line as the length of the last word

        // iteratively adding words to currentWidth as long as possible and until they do not exceed the maximum width -
        // this defines the last word in the line
        while (endIndex + 1 < words.length && currentWidth + words[endIndex + 1].length() + 1 <= maxWidth) {
            currentWidth += words[endIndex + 1].length() + 1;
            endIndex++;
        }

        return endIndex; // returning the index of the last word in the line
    }

    private static String justifyLine(String[] words, int startIndex, int endIndex, int maxWidth) { // method for justifying
        // the line according to the conditions
        int totalWordsWidth = getTotalWordsWidth(words, startIndex, endIndex); // getting the total width of words
        int totalSpaces = maxWidth - totalWordsWidth; // getting the total number of spaces
        int numGaps = endIndex - startIndex; // getting the total number of gaps between words

        StringBuilder sb = new StringBuilder(); // forming the current lines with words and spaces
        if (numGaps == 0 || endIndex == words.length - 1) { // if there are no gaps or only one word
            sb.append(leftJustify(words, startIndex, endIndex, maxWidth)); // left-align the line
        } else {
            int baseSpaces = totalSpaces / numGaps; // determining the base number of spaces
            int extraSpaces = totalSpaces % numGaps; // determining the additional number of spaces

            for (int i = startIndex; i < endIndex; i++) { // iterating over the words in the line
                sb.append(words[i]); // adding the word
                sb.append(getSpaces(baseSpaces)); // adding spaces

                if (i - startIndex < extraSpaces) { // if the word is longer than the first word and less than the number of extra spaces
                    sb.append(' '); // adding one more space
                }
            }

            sb.append(words[endIndex]); // adding the last word in the line
        }

        return sb.toString(); // returning the result
    }

    private static String leftJustify(String[] words, int startIndex, int endIndex, int maxWidth) { // method for left-aligning
        // the line
        StringBuilder sb = new StringBuilder(); // container for the result
        sb.append(words[startIndex]); // adding the first word

        for (int i = startIndex + 1; i <= endIndex; i++) { // iterating from the first word to the last word in the line
            sb.append(' '); // adding spaces
            sb.append(words[i]); // adding words
        }

        while (sb.length() < maxWidth) { // if the resulting line is shorter than the allowed length, then
            sb.append(' '); // adding spaces
        }

        return sb.toString(); // returning the resulting line
    }

    private static int getTotalWordsWidth(String[] words, int startIndex, int endIndex) { // method for getting the width of the line
        int totalWidth = 0;
        for (int i = startIndex; i <= endIndex; i++) { // adding the lengths of words in the line
            totalWidth += words[i].length();
        }
        return totalWidth;
    }

    private static String getSpaces(int count) { // method for getting spaces in a line (used for creating
        // a string of gaps of the specified length, which is added between words when justifying text)
        StringBuilder spaces = new StringBuilder();
        for (int i = 0; i < count; i++) {
            spaces.append(' ');
        }
        return spaces.toString();
    }
}



//Given an array of strings words and a width maxWidth, format the text such that each line has exactly maxWidth
// characters and is fully (left and right) justified.
//You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra
// spaces ' ' when necessary so that each line has exactly maxWidth characters.
//Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line does not
// divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
//For the last line of text, it should be left-justified, and no extra space is inserted between words.
//
//Note:
//A word is defined as a character sequence consisting of non-space characters only.
//Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
//The input array words contains at least one word.
//
//Example 1:
//Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
//Output:
//[
//   "This    is    an",
//   "example  of text",
//   "justification.  "
//]

//Example 2:
//Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
//Output:
//[
//  "What   must   be",
//  "acknowledgment  ",
//  "shall be        "
//]
//Explanation: Note that the last line is "shall be    " instead of "shall     be", because the last line must be
// left-justified instead of fully-justified.
//Note that the second line is also left-justified because it contains only one word.

//Example 3:
//Input: words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.",
//                  "Art","is","everything","else","we","do"], maxWidth = 20
//Output:
//[
//  "Science  is  what we",
//  "understand      well",
//  "enough to explain to",
//  "a  computer.  Art is",
//  "everything  else  we",
//  "do                  "
//]
//
//Constraints:
//1 <= words.length <= 300
//1 <= words[i].length <= 20
//words[i] consists of only English letters and symbols.
//1 <= maxWidth <= 100
//words[i].length <= maxWidth