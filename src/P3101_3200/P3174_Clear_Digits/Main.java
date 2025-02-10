package P3101_3200.P3174_Clear_Digits;

import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String str1 = "abc";
        System.out.println(solution.clearDigits(str1)); // Output "abc"

        String str2 = "cb34";
        System.out.println(solution.clearDigits(str2));
    }

    public String clearDigits(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                if (!stack.isEmpty()) {
                    stack.pop();  // Remove the nearest symbol on the left
                }
            } else {
                stack.push(c); // add symbols
            }
        }

        StringBuilder result = new StringBuilder();
        for (char c : stack) {
            result.append(c);
        }
        return result.toString();
    }

}

//Complexity: O(n)


//You are given a string s.
//Your task is to remove all digits by doing this operation repeatedly:
//Delete the first digit and the closest non-digit character to its left.
//Return the resulting string after removing all digits.
//
//Example 1:
//Input: s = "abc"
//Output: "abc"
//Explanation:
//There is no digit in the string.
//
//Example 2:
//Input: s = "cb34"
//Output: ""
//Explanation:
//First, we apply the operation on s[2], and s becomes "c4".
//Then we apply the operation on s[1], and s becomes "".
//
//Constraints:
//1 <= s.length <= 100
//s consists only of lowercase English letters and digits.
//The input is generated such that it is possible to delete all digits.
