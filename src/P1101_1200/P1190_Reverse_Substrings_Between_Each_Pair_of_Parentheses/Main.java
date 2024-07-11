package P1101_1200.P1190_Reverse_Substrings_Between_Each_Pair_of_Parentheses;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        String s1 = "(abcd)";
        System.out.println(solution.reverseParentheses(s1)); // Output: "dcba"

        String s2 = "(u(love)i)";
        System.out.println(solution.reverseParentheses(s2)); // Output: "iloveu"

        String s3 = "(ed(et(oc))el)";
        System.out.println(solution.reverseParentheses(s3)); // Output: "leetcode"
    }

    public String reverseParentheses(String s) {
        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (ch == ')') {
                // Temporary list to hold the characters to be reversed
                List<Character> temp = new ArrayList<>();
                while (stack.peek() != '(') {
                    temp.add(stack.pop());
                }
                // Remove the '(' from the stack
                stack.pop();
                // Push the reversed characters back onto the stack
                for (char c : temp) {
                    stack.push(c);
                }
            } else {
                // Push all other characters including '(' onto the stack
                stack.push(ch);
            }
        }

        // Build the result string from the stack
        StringBuilder result = new StringBuilder();
        for (char ch : stack) {
            result.append(ch);
        }

        return result.toString();
    }
}

//Explanation:
//Reading Characters: We iterate through each character in the string s.
//Handling Closing Parenthesis: When we encounter a closing parenthesis ), we pop characters from the stack until
// we encounter an opening parenthesis (. These popped characters are stored in a temporary list, reversed, and
// then pushed back onto the stack.
//Building the Result: After processing all characters, we build the result string by concatenating all characters
// remaining in the stack.
//This solution ensures that the strings within the parentheses are reversed correctly, starting from the innermost
// pair and working outward, as required by the problem constraints.


//You are given a string s that consists of lower case English letters and brackets.
//Reverse the strings in each pair of matching parentheses, starting from the innermost one.
//Your result should not contain any brackets.
//
//Example 1:
//Input: s = "(abcd)"
//Output: "dcba"

//Example 2:
//Input: s = "(u(love)i)"
//Output: "iloveu"
//Explanation: The substring "love" is reversed first, then the whole string is reversed.

//Example 3:
//Input: s = "(ed(et(oc))el)"
//Output: "leetcode"
//Explanation: First, we reverse the substring "oc", then "etco", and finally, the whole string.
//
//Constraints:
//1 <= s.length <= 2000
//s only contains lower case English characters and parentheses.
//It is guaranteed that all parentheses are balanced.
