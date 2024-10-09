package P901_1000.P921_Minimum_Add_to_Make_Parentheses_Valid;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        String s1 = "())";
        System.out.println("Minimum moves for '" + s1 + "': " + solution.minAddToMakeValid(s1)); // Output: 1

        String s2 = "(((";
        System.out.println("Minimum moves for '" + s2 + "': " + solution.minAddToMakeValid(s2)); // Output: 3
    }

    public int minAddToMakeValid(String s) {
        int openCount = 0;  // to count unmatched '('
        int closeCount = 0; // to count unmatched ')'

        for (char c : s.toCharArray()) {
            if (c == '(') {
                openCount++; // we have an unmatched opening parenthesis
            } else { // c == ')'
                if (openCount > 0) {
                    openCount--; // match this closing parenthesis with an unmatched opening
                } else {
                    closeCount++; // no opening parenthesis to match, so this is an unmatched closing
                }
            }
        }

        // The total moves needed to balance the string is the sum of unmatched '(' and unmatched ')'
        return openCount + closeCount;
    }
}

//Explanation:
//openCount tracks the number of unmatched opening parentheses (.
//closeCount tracks the number of unmatched closing parentheses ).
//For each (, increment openCount.
//For each ), check if there's an unmatched ( (i.e., openCount > 0). If there is, pair them by decrementing
// openCount. If there isn't, increment closeCount because this ) is unmatched.
//Finally, the total moves needed to balance the string is the sum of unmatched ( (in openCount) and
// unmatched ) (in closeCount).
//Time Complexity:
//The time complexity is O(n), where n is the length of the string, because we process each character once.
//Space Complexity:
//The space complexity is O(1) because we only use a constant amount of extra space to store the counts.


//A parentheses string is valid if and only if:
//It is the empty string,
//It can be written as AB (A concatenated with B), where A and B are valid strings, or
//It can be written as (A), where A is a valid string.
//You are given a parentheses string s. In one move, you can insert a parenthesis at any position of the string.
//
//For example, if s = "()))", you can insert an opening parenthesis to be "(()))" or a
// closing parenthesis to be "())))".
//Return the minimum number of moves required to make s valid.
//
//Example 1:
//Input: s = "())"
//Output: 1

//Example 2:
//Input: s = "((("
//Output: 3
//
//Constraints:
//1 <= s.length <= 1000
//s[i] is either '(' or ')'.
