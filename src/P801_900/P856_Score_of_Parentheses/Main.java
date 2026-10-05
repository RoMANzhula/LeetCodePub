package P801_900.P856_Score_of_Parentheses;

import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.scoreOfParentheses("()")); // 1
        System.out.println(solution.scoreOfParentheses("(())")); // 2
        System.out.println(solution.scoreOfParentheses("()()")); // 2
        System.out.println(solution.scoreOfParentheses("(()(()))")); // 6
    }

    public int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(0);
            } else {
                int innerScore = stack.pop();
                int score = innerScore == 0 ? 1 : 2 * innerScore;

                stack.push(stack.pop() + score);
            }
        }

        return stack.pop();
    }

}

//Complexity:
// time and space - O(n)


//Given a balanced parentheses string s, return the score of the string.
//The score of a balanced parentheses string is based on the following rule:
//"()" has score 1.
//AB has score A + B, where A and B are balanced parentheses strings.
//(A) has score 2 * A, where A is a balanced parentheses string.

//Example 1:
//Input: s = "()"
//Output: 1

//Example 2:
//Input: s = "(())"
//Output: 2

//Example 3:
//Input: s = "()()"
//Output: 2

//Constraints:
//2 <= s.length <= 50
//s consists of only '(' and ')'.
//s is a balanced parentheses string.
