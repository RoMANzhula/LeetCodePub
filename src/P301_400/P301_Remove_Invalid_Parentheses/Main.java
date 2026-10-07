package P301_400.P301_Remove_Invalid_Parentheses;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String s1 = "()())()";
        String s2 = "(a)())()";
        String s3 = ")(";

        System.out.println(solution.removeInvalidParentheses(s1));
        System.out.println(solution.removeInvalidParentheses(s2));
        System.out.println(solution.removeInvalidParentheses(s3));
    }

    public List<String> removeInvalidParentheses(String s) {
        int leftRemovals = 0;
        int rightRemovals = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                leftRemovals++;
            } else if (c == ')') {
                if (leftRemovals > 0) {
                    leftRemovals--;
                } else {
                    rightRemovals++;
                }
            }
        }

        Set<String> result = new HashSet<>();

        backtrack(
                s,
                0,
                0,
                leftRemovals,
                rightRemovals,
                new StringBuilder(),
                result
        );

        return new ArrayList<>(result);
    }

    private void backtrack(
            String s,
            int index,
            int balance,
            int leftRemovals,
            int rightRemovals,
            StringBuilder current,
            Set<String> result
    ) {
        if (index == s.length()) {
            if (balance == 0 && leftRemovals == 0 && rightRemovals == 0) {
                result.add(current.toString());
            }
            return;
        }

        char c = s.charAt(index);

        if (c == '(') {
            if (leftRemovals > 0) {
                backtrack(
                        s,
                        index + 1,
                        balance,
                        leftRemovals - 1,
                        rightRemovals,
                        current,
                        result
                );
            }

            current.append(c);

            backtrack(
                    s,
                    index + 1,
                    balance + 1,
                    leftRemovals,
                    rightRemovals,
                    current,
                    result
            );

            current.deleteCharAt(current.length() - 1);

        } else if (c == ')') {
            if (rightRemovals > 0) {
                backtrack(
                        s,
                        index + 1,
                        balance,
                        leftRemovals,
                        rightRemovals - 1,
                        current,
                        result
                );
            }

            if (balance > 0) {
                current.append(c);

                backtrack(
                        s,
                        index + 1,
                        balance - 1,
                        leftRemovals,
                        rightRemovals,
                        current,
                        result
                );

                current.deleteCharAt(current.length() - 1);
            }

        } else {
            current.append(c);

            backtrack(
                    s,
                    index + 1,
                    balance,
                    leftRemovals,
                    rightRemovals,
                    current,
                    result
            );

            current.deleteCharAt(current.length() - 1);
        }
    }

}

//Complexity:
// time and space - O(2^n * n)



//Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses to make
// the input string valid.
//Return a list of unique strings that are valid with the minimum number of removals. You may return the answer in
// any order.

//Example 1:
//Input: s = "()())()"
//Output: ["(())()","()()()"]

//Example 2:
//Input: s = "(a)())()"
//Output: ["(a())()","(a)()()"]

//Example 3:
//Input: s = ")("
//Output: [""]

//Constraints:
//1 <= s.length <= 25
//s consists of lowercase English letters and parentheses '(' and ')'.
//There will be at most 20 parentheses in s.
