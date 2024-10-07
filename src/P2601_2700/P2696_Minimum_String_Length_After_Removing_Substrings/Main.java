package P2601_2700.P2696_Minimum_String_Length_After_Removing_Substrings;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        String s1 = "ABFCACDB";
        System.out.println(solution.minLength(s1));  // Output: 2

        String s2 = "ACBBD";
        System.out.println(solution.minLength(s2));  // Output: 5
    }

    public int minLength(String s) {
        // Create a stack to store characters
        Stack<Character> stack = new Stack<>();

        // Iterate over each character in the string
        for (char c : s.toCharArray()) {
            // Check if the stack has at least one character and forms "AB" or "CD"
            if (!stack.isEmpty() &&
                    ((stack.peek() == 'A' && c == 'B') || (stack.peek() == 'C' && c == 'D'))) {
                // Pop the stack since we found a valid substring
                stack.pop();
            } else {
                // Otherwise, push the current character onto the stack
                stack.push(c);
            }
        }

        // The remaining characters in the stack represent the minimized string
        return stack.size();
    }

}

//Explanation:
//We use a Stack to simulate the removal process.
//For each character in the string:
//If the top of the stack forms "AB" or "CD" with the current character, we pop the stack to
// simulate removing the substring.
//Otherwise, we push the current character onto the stack.
//After processing the string, the size of the stack represents the length of the minimized string.
//Complexity:
//This approach runs in linear time O(n), where n is the length of the string, and it is efficient for
// the input constraints.


//You are given a string s consisting only of uppercase English letters.
//You can apply some operations to this string where, in one operation, you can remove any occurrence of one of the
// substrings "AB" or "CD" from s.
//Return the minimum possible length of the resulting string that you can obtain.
//Note that the string concatenates after removing the substring and could produce new "AB" or "CD" substrings.
//
//Example 1:
//Input: s = "ABFCACDB"
//Output: 2
//Explanation: We can do the following operations:
//- Remove the substring "ABFCACDB", so s = "FCACDB".
//- Remove the substring "FCACDB", so s = "FCAB".
//- Remove the substring "FCAB", so s = "FC".
//So the resulting length of the string is 2.
//It can be shown that it is the minimum length that we can obtain.

//Example 2:
//Input: s = "ACBBD"
//Output: 5
//Explanation: We cannot do any operations on the string so the length remains the same.
//
//Constraints:
//1 <= s.length <= 100
//s consists only of uppercase English letters.
