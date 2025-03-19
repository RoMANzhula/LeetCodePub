package P1601_1700.P1624_Largest_Substring_Between_Two_Equal_Characters;

public class Main {
    public static void main(String[] args) {
        System.out.println(maxLengthBetweenEqualCharacters("aba"));
        System.out.println(maxLengthBetweenEqualCharacters("aa"));
        System.out.println(maxLengthBetweenEqualCharacters("abca"));
    }

    public static int maxLengthBetweenEqualCharacters(String s) {
        int[] firstOccurrence = new int[26];
        int maxSubstringLength = -1;

        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            int index = currentChar - 'a';

            if (firstOccurrence[index] == 0) {
                // First occurrence of the character
                firstOccurrence[index] = i + 1;
            } else {
                // Character is repeated, calculate substring length
                int substringLength = i - firstOccurrence[index];
                maxSubstringLength = Math.max(maxSubstringLength, substringLength);
            }
        }

        return maxSubstringLength;
    }
}

//Given a string s, return the length of the longest substring between two equal characters, excluding the
// two characters. If there is no such substring return -1.
//A substring is a contiguous sequence of characters within a string.

//Example 1:
//Input: s = "aa"
//Output: 0
//Explanation: The optimal substring here is an empty substring between the two 'a's.

//Example 2:
//Input: s = "abca"
//Output: 2
//Explanation: The optimal substring here is "bc".

//Example 3:
//Input: s = "cbzxy"
//Output: -1
//Explanation: There are no characters that appear twice in s.

//Constraints:
//1 <= s.length <= 300
//s contains only lowercase English letters.
