package P201_300.P241_Different_Ways_to_Add_Parentheses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        String expression1 = "2-1-1";
        System.out.println(solution.diffWaysToCompute(expression1)); // Output: [0, 2]

        String expression2 = "2*3-4*5";
        System.out.println(solution.diffWaysToCompute(expression2)); // Output: [-34, -14, -10, -10, 10]
    }

    // Memoization to store already computed sub-expression results
    private Map<String, List<Integer>> memo = new HashMap<>();

    public List<Integer> diffWaysToCompute(String expression) {
        // If the result for this expression is already computed, return it
        if (memo.containsKey(expression)) {
            return memo.get(expression);
        }

        List<Integer> result = new ArrayList<>();

        // Traverse through the expression
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            // If it's an operator, split the expression into two parts
            if (c == '+' || c == '-' || c == '*') {
                // Recursively solve for left and right sub-expressions
                List<Integer> left = diffWaysToCompute(expression.substring(0, i));
                List<Integer> right = diffWaysToCompute(expression.substring(i + 1));

                // Compute the results by combining the left and right parts using the operator
                for (int l : left) {
                    for (int r : right) {
                        switch (c) {
                            case '+':
                                result.add(l + r);
                                break;
                            case '-':
                                result.add(l - r);
                                break;
                            case '*':
                                result.add(l * r);
                                break;
                        }
                    }
                }
            }
        }

        // Base case: If no operators, the expression must be a number
        if (result.isEmpty()) {
            result.add(Integer.parseInt(expression));
        }

        // Store the computed result in the memoization map
        memo.put(expression, result);

        return result;
    }
}

//Explanation:
//Base Case:
//If the expression contains no operator (e.g., "2", "3"), it directly returns the integer value.
//Recursive Splitting:
//The expression is iterated character by character.
//Whenever an operator (+, -, *) is encountered, the expression is split into left and right sub-expressions.
//Each sub-expression is recursively solved to get all possible values for the left and right parts.
//Combining Results:
//For each combination of left and right sub-results, apply the current operator and store the results in a list.
//Memoization:
//
//To improve efficiency, sub-expression results are cached. This prevents recalculating the same
// sub-expressions multiple times.

//Time Complexity:
//The time complexity is difficult to calculate exactly, but it's influenced by the number of possible groupings
// of operators and sub-expressions. Each operator creates a division point, leading to recursive calls on each half,
// resulting in an exponential number of splits in the worst case.
//Space Complexity:
//The space complexity is mainly due to the recursion depth and the memoization map, which stores the results of
// each sub-expression.


//Given a string expression of numbers and operators, return all possible results from computing all the different
// possible ways to group numbers and operators. You may return the answer in any order.
//The test cases are generated such that the output values fit in a 32-bit integer and the number of
// different results does not exceed 104.
//
//Example 1:
//Input: expression = "2-1-1"
//Output: [0,2]
//Explanation:
//((2-1)-1) = 0
//(2-(1-1)) = 2

//Example 2:
//Input: expression = "2*3-4*5"
//Output: [-34,-14,-10,-10,10]
//Explanation:
//(2*(3-(4*5))) = -34
//((2*3)-(4*5)) = -14
//((2*(3-4))*5) = -10
//(2*((3-4)*5)) = -10
//(((2*3)-4)*5) = 10
//
//Constraints:
//1 <= expression.length <= 20
//expression consists of digits and the operator '+', '-', and '*'.
//All the integer values in the input expression are in the range [0, 99].
//The integer values in the input expression do not have a leading '-' or '+' denoting the sign.