package P801_900.P880_Decoded_String_at_Index;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();
        String str1 = "leet2code3";
        int k1 = 10;
        System.out.println(solution.decodeAtIndex(str1, k1));
    }

    public String decodeAtIndex(String s, int k) {
        long size = 0;
        int i = 0;

        //calculate the total size of the decoded string
        while (i < s.length()) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int num = Character.getNumericValue(c);
                size *= num;
            } else {
                size++;
            }
            i++;
        }

        //start from the end of the string and backtrack to find the k-th letter
        for (i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            k %= size; //adjust k to its equivalent position within the current size

            if (k == 0 && Character.isLetter(c)) {
                return String.valueOf(c); //convert the character to a String -- it's our bingo
            }

            if (Character.isDigit(c)) {
                int num = Character.getNumericValue(c);
                size /= num;
            } else {
                size--;
            }
        }

        return ""; // Return an empty string (should not happen)
    }
}
//You are given an encoded string s. To decode the string to a tape, the encoded string is read one character at
// a time and the following steps are taken:
//If the character read is a letter, that letter is written onto the tape.
//If the character read is a digit d, the entire current tape is repeatedly written d - 1 more times in total.
//Given an integer k, return the kth letter (1-indexed) in the decoded string.

//Example 1:
//Input: s = "leet2code3", k = 10
//Output: "o"
//Explanation: The decoded string is "leetleetcodeleetleetcodeleetleetcode".
//The 10th letter in the string is "o".

//Example 2:
//Input: s = "ha22", k = 5
//Output: "h"
//Explanation: The decoded string is "hahahaha".
//The 5th letter is "h".

//Example 3:
//Input: s = "a2345678999999999999999", k = 1
//Output: "a"
//Explanation: The decoded string is "a" repeated 8301530446056247680 times.
//The 1st letter is "a".

//Constraints:
//2 <= s.length <= 100
//s consists of lowercase English letters and digits 2 through 9.
//s starts with a letter.
//1 <= k <= 109
//It is guaranteed that k is less than or equal to the length of the decoded string.
//The decoded string is guaranteed to have less than 263 letters.
