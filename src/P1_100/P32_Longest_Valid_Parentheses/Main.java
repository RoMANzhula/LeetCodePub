package P1_100.P32_Longest_Valid_Parentheses;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        System.out.println(longestValidParentheses("(()")); // 2
        System.out.println(longestValidParentheses(")()())")); // 4
        System.out.println(longestValidParentheses(")(")); // 0
    }

    public static int longestValidParentheses(String s) {
        int leftCount = 0;
        int rightCount = 0;
        int maxLength = 0;

        // Left to Right Pass
        for (char c : s.toCharArray()) {
            if (c == '(') {
                leftCount++;
            } else {
                rightCount++;
            }

            if (leftCount == rightCount) {
                maxLength = Math.max(maxLength, 2 * rightCount);
            } else if (rightCount > leftCount) {
                leftCount = 0;
                rightCount = 0;
            }
        }

        leftCount = 0;
        rightCount = 0;

        // Right to Left Pass
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);

            if (c == '(') {
                leftCount++;
            } else {
                rightCount++;
            }

            if (leftCount == rightCount) {
                maxLength = Math.max(maxLength, 2 * leftCount);
            } else if (leftCount > rightCount) {
                leftCount = 0;
                rightCount = 0;
            }
        }

        return maxLength;
    }
}

//Given a string containing just the characters '(' and ')', return the length of the longest valid (well-formed)
// parentheses substring.

//Example 1:
//Input: s = "(()"
//Output: 2
//Explanation: The longest valid parentheses substring is "()".

//Example 2:
//Input: s = ")()())"
//Output: 4
//Explanation: The longest valid parentheses substring is "()()".

//Example 3:
//Input: s = ""
//Output: 0

//Constraints:
//0 <= s.length <= 3 * 104
//s[i] is '(', or ')'.
