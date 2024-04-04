package P1601_1700.P1614_Maimum_Nesting_Depth_of_the_Parentheses;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        String s1 = "(1+(2*3)+((8)/4))+1";
        String s2 = "(1)+((2))+(((3)))";
        System.out.println("Depth of " + s1 + ": " + solution.maxDepth(s1)); // Output: 3
        System.out.println("Depth of " + s2 + ": " + solution.maxDepth(s2)); // Output: 3
    }

    public int maxDepth(String s) {
        int depth = 0;
        int maxDepth = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                depth++;
                maxDepth = Math.max(maxDepth, depth);
            } else if (c == ')') {
                depth--;
            }
        }

        return maxDepth;
    }
}

//Задача полягає в тому, щоб обчислити глибину вкладеності дужок у виразі s. Для цього ми проходимося по кожному
// символу у рядку s і визначаємо поточну глибину, збільшуючи її, коли зустрічаємо відкриваючу дужку ( і зменшуючи,
// коли зустрічаємо закриваючу дужку ). Під час цього процесу ми також визначаємо максимальну глибину, яку ми
// знаходимо. По завершенні проходження по рядку повертаємо знайдену максимальну глибину.

//A string is a valid parentheses string (denoted VPS) if it meets one of the following:
//It is an empty string "", or a single character not equal to "(" or ")",
//It can be written as AB (A concatenated with B), where A and B are VPS's, or
//It can be written as (A), where A is a VPS.
//We can similarly define the nesting depth depth(S) of any VPS S as follows:
//depth("") = 0
//depth(C) = 0, where C is a string with a single character not equal to "(" or ")".
//depth(A + B) = max(depth(A), depth(B)), where A and B are VPS's.
//depth("(" + A + ")") = 1 + depth(A), where A is a VPS.
//For example, "", "()()", and "()(()())" are VPS's (with nesting depths 0, 1, and 2), and ")(" and "(()" are not VPS's.
//
//Given a VPS represented as string s, return the nesting depth of s.
//
//Example 1:
//Input: s = "(1+(2*3)+((8)/4))+1"
//Output: 3
//Explanation: Digit 8 is inside of 3 nested parentheses in the string.

//Example 2:
//Input: s = "(1)+((2))+(((3)))"
//Output: 3
//
//Constraints:
//1 <= s.length <= 100
//s consists of digits 0-9 and characters '+', '-', '*', '/', '(', and ')'.
//It is guaranteed that parentheses expression s is a VPS.