package P1501_1600.P1544_Make_The_String_Great;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.makeGood("leEeetcode")); // Output: "leetcode"
        System.out.println(solution.makeGood("abBAcC")); // Output: ""
        System.out.println(solution.makeGood("s")); // Output: "s"
    }

    public String makeGood(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && Math.abs(c - stack.peek()) == 32) {
                stack.pop(); // remove the top character if it forms a pair with the current character
            } else {
                stack.push(c); // otherwise, add the current character to the stack
            }
        }

        // construct the resulting string from the characters left in the stack
        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }

        return sb.toString(); // bingo
    }
}


//Given a string s of lower and upper case English letters.
//A good string is a string which doesn't have two adjacent characters s[i] and s[i + 1] where:
//0 <= i <= s.length - 2
//s[i] is a lower-case letter and s[i + 1] is the same letter but in upper-case or vice-versa.
//To make the string good, you can choose two adjacent characters that make the string bad and remove them. You
// can keep doing this until the string becomes good.
//Return the string after making it good. The answer is guaranteed to be unique under the given constraints.
//Notice that an empty string is also good
//
//Example 1:
//Input: s = "leEeetcode"
//Output: "leetcode"
//Explanation: In the first step, either you choose i = 1 or i = 2, both will result "leEeetcode" to be
// reduced to "leetcode".

//Example 2:
//Input: s = "abBAcC"
//Output: ""
//Explanation: We have many possible scenarios, and all lead to the same answer. For example:
//"abBAcC" --> "aAcC" --> "cC" --> ""
//"abBAcC" --> "abBA" --> "aA" --> ""

//Example 3:
//Input: s = "s"
//Output: "s"
//
//
//Constraints:
//
//1 <= s.length <= 100
//s contains only lower and upper case English letters.