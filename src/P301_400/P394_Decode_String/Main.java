package P301_400.P394_Decode_String;

import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.decodeString("3[a]2[bc]"));      // Output: aaabcbc
        System.out.println(solution.decodeString("3[a2[c]]"));       // output: accaccacc
        System.out.println(solution.decodeString("2[abc]3[cd]ef"));  // Output: abcabccdcdcdef
    }

    public String decodeString(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> stringStack = new Stack<>();
        StringBuilder currentStr = new StringBuilder();
        int k = 0;

        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                k = k * 10 + (ch - '0');
            } else if (ch == '[') {
                countStack.push(k);
                stringStack.push(currentStr);
                currentStr = new StringBuilder();
                k = 0;
            } else if (ch == ']') {
                StringBuilder decoded = stringStack.pop();
                int repeatTimes = countStack.pop();
                for (int i = 0; i < repeatTimes; i++) {
                    decoded.append(currentStr);
                }
                currentStr = decoded;
            } else {
                currentStr.append(ch);
            }
        }

        return currentStr.toString();
    }

}

//Explanation:
//If the character is a digit, build the repeat count k.
//If you see '[', push the current k and current string into stacks and reset them.
//If you see ']', pop from the stacks and repeat the substring.
//If it's a letter, just append it to the current substring
//Complexity:
// time - O(n)
// space -  O(n)


//Given an encoded string, return its decoded string.
//The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated
// exactly k times. Note that k is guaranteed to be a positive integer.
//You may assume that the input string is always valid; there are no extra white spaces, square brackets are
// well-formed, etc. Furthermore, you may assume that the original data does not contain any digits and that digits
// are only for those repeat numbers, k. For example, there will not be input like 3a or 2[4].
//The test cases are generated so that the length of the output will never exceed 105.

//Example 1:
//Input: s = "3[a]2[bc]"
//Output: "aaabcbc"

//Example 2:
//Input: s = "3[a2[c]]"
//Output: "accaccacc"

//Example 3:
//Input: s = "2[abc]3[cd]ef"
//Output: "abcabccdcdcdef"

//Constraints:
//1 <= s.length <= 30
//s consists of lowercase English letters, digits, and square brackets '[]'.
//s is guaranteed to be a valid input.
//All the integers in s are in the range [1, 300].
