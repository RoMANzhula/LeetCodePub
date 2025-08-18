package P601_700.P679_24_Game;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] cards1 = {4, 1, 8, 7};
        System.out.println(solution.judgePoint24(cards1)); // true

        int[] cards2 = {1, 2, 1, 2};
        System.out.println(solution.judgePoint24(cards2)); // false
    }

    private static final double EPSILON = 1e-6; // tolerance for floating point comparison
    private static final int TARGET = 24;

    public boolean judgePoint24(int[] cards) {
        List<Double> nums = new ArrayList<>();

        for (int card : cards) {
            nums.add((double) card);
        }

        return solve(nums);
    }

    private boolean solve(List<Double> nums) {
        if (nums.size() == 1) {
            return Math.abs(nums.get(0) - TARGET) < EPSILON;
        }

        // try all pairs of numbers
        for (int i = 0; i < nums.size(); i++) {
            for (int j = 0; j < nums.size(); j++) {
                if (i == j) continue;

                List<Double> next = new ArrayList<>();
                // keep the rest of the numbers
                for (int k = 0; k < nums.size(); k++) {
                    if (k != i && k != j) {
                        next.add(nums.get(k));
                    }
                }

                // try all 4 operations
                for (double val : compute(nums.get(i), nums.get(j))) {
                    next.add(val);

                    if (solve(next)) {
                        return true;
                    }

                    next.remove(next.size() - 1); // backtrack
                }
            }
        }

        return false;
    }

    private List<Double> compute(double a, double b) {
        List<Double> results = new ArrayList<>();
        results.add(a + b);
        results.add(a - b);
        results.add(b - a);
        results.add(a * b);

        if (Math.abs(b) > EPSILON) results.add(a / b);

        if (Math.abs(a) > EPSILON) results.add(b / a);

        return results;
    }

}


//You are given an integer array cards of length 4. You have four cards, each containing a number in the
// range [1, 9]. You should arrange the numbers on these cards in a mathematical expression using the
// operators ['+', '-', '*', '/'] and the parentheses '(' and ')' to get the value 24.
//You are restricted with the following rules:
//The division operator '/' represents real division, not integer division.
//For example, 4 / (1 - 2 / 3) = 4 / (1 / 3) = 12.
//Every operation done is between two numbers. In particular, we cannot use '-' as a unary operator.
//For example, if cards = [1, 1, 1, 1], the expression "-1 - 1 - 1 - 1" is not allowed.
//You cannot concatenate numbers together
//For example, if cards = [1, 2, 1, 2], the expression "12 + 12" is not valid.
//Return true if you can get such expression that evaluates to 24, and false otherwise.

//Example 1:
//Input: cards = [4,1,8,7]
//Output: true
//Explanation: (8-4) * (7-1) = 24

//Example 2:
//Input: cards = [1,2,1,2]
//Output: false

//Constraints:
//cards.length == 4
//1 <= cards[i] <= 9
