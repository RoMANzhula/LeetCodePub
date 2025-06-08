package P1_100.P22_Generate_Parentheses;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int n = 3;
        List<String> combinations = solution.generateParenthesis(n);
        System.out.println(combinations);
    }

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);

        return result;
    }

    private void backtrack(List<String> result, String current, int open, int close, int max) {
        if (current.length() == max * 2) {
            result.add(current);
            return;
        }

        if (open < max) {
            backtrack(result, current + "(", open + 1, close, max);
        }

        if (close < open) {
            backtrack(result, current + ")", open, close + 1, max);
        }
    }

}

//Explanation:
//We use a helper method backtrack to build the string step-by-step.
//We can only add '(' if we still have some left to add (open < max).
//we can only add ')' if there are more '(' already placed (close < open).
//When the length of the current string reaches n * 2, it means we used all parentheses.
//Complexity:
// time and space - O(4^n / √n)


//Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

//Example 1:
//Input: n = 3
//Output: ["((()))","(()())","(())()","()(())","()()()"]

//Example 2:
//Input: n = 1
//Output: ["()"]

//Constraints:
//1 <= n <= 8
