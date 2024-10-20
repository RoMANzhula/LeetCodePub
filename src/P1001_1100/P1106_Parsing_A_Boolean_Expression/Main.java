package P1001_1100.P1106_Parsing_A_Boolean_Expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.parseBoolExpr("&(|(f))")); // Output: false
        System.out.println(solution.parseBoolExpr("|(f,f,f,t)")); // Output: true
        System.out.println(solution.parseBoolExpr("!(&(f,t))")); // Output: true
    }

    public boolean parseBoolExpr(String expression) {
        Stack<Character> operators = new Stack<>();
        Stack<List<Boolean>> operands = new Stack<>();

        for (char ch : expression.toCharArray()) {
            if (ch == 't' || ch == 'f') {
                // Convert 't' and 'f' to boolean and add to the operands stack
                if (!operands.isEmpty()) {
                    operands.peek().add(ch == 't');
                }
            } else if (ch == '!' || ch == '&' || ch == '|') {
                // Push the operator to the stack
                operators.push(ch);
                operands.push(new ArrayList<>()); // New operand list for this operator
            } else if (ch == '(') {
                // Ignore '(' as we only need it as a marker
                continue;
            } else if (ch == ')') {
                // When we encounter a closing parenthesis, evaluate the current expression
                char operator = operators.pop();
                List<Boolean> currentOperands = operands.pop();

                boolean result;
                if (operator == '!') {
                    // NOT has exactly one operand
                    result = !currentOperands.get(0);
                } else if (operator == '&') {
                    // AND needs all operands to be true
                    result = true;
                    for (boolean operand : currentOperands) {
                        result &= operand;
                        if (!result) break; // Short-circuit if false is found
                    }
                } else {
                    // OR needs at least one operand to be true
                    result = false;
                    for (boolean operand : currentOperands) {
                        result |= operand;
                        if (result) break; // Short-circuit if true is found
                    }
                }

                // If the stack is empty, this is the final result
                if (operands.isEmpty()) {
                    return result;
                }

                // Otherwise, push the result to the current operand list on top of the stack
                operands.peek().add(result);
            }
        }

        // The final result will be the only value in the operands stack, if any
        return !operands.isEmpty() && operands.peek().get(0);
    }

}

//Explanation of the Fix:
//Check if the stack is empty before peek():
//After processing a sub-expression inside parentheses (i.e., when encountering )), if the stack is empty, this
// indicates that we've reached the final result. In that case, we directly return the result instead of
// pushing it back onto the stack.
//Return the result directly when the stack is empty:
//Once the full expression is evaluated, the stack will be empty, so we return the final result as
// soon as it's calculated.


//A boolean expression is an expression that evaluates to either true or false. It can be in one of the following shapes:
//'t' that evaluates to true.
//'f' that evaluates to false.
//'!(subExpr)' that evaluates to the logical NOT of the inner expression subExpr.
//'&(subExpr1, subExpr2, ..., subExprn)' that evaluates to the logical AND of the inner expressions subExpr1,
// subExpr2, ..., subExprn where n >= 1.
//'|(subExpr1, subExpr2, ..., subExprn)' that evaluates to the logical OR of the inner expressions subExpr1,
// subExpr2, ..., subExprn where n >= 1.
//Given a string expression that represents a boolean expression, return the evaluation of that expression.
//It is guaranteed that the given expression is valid and follows the given rules.
//
//Example 1:
//Input: expression = "&(|(f))"
//Output: false
//Explanation:
//First, evaluate |(f) --> f. The expression is now "&(f)".
//Then, evaluate &(f) --> f. The expression is now "f".
//Finally, return false.

//Example 2:
//Input: expression = "|(f,f,f,t)"
//Output: true
//Explanation: The evaluation of (false OR false OR false OR true) is true.

//Example 3:
//Input: expression = "!(&(f,t))"
//Output: true
//Explanation:
//First, evaluate &(f,t) --> (false AND true) --> false --> f. The expression is now "!(f)".
//Then, evaluate !(f) --> NOT false --> true. We return true.
//
//Constraints:
//1 <= expression.length <= 2 * 104
//expression[i] is one following characters: '(', ')', '&', '|', '!', 't', 'f', and ','.