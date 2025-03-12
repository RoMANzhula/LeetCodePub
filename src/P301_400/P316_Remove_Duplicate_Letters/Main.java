package P301_400.P316_Remove_Duplicate_Letters;

import java.util.Stack;


public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String str1 = "absdasss";

        System.out.println(solution.removeDuplicateLetters(str1));
    }

    public String removeDuplicateLetters(String s) {

        int[] count = new int[26]; // variable to count the number of lettera in the string 's'

        boolean[] added = new boolean[26]; // array to determine if the letter has already been added to the result

        Stack<Character> stack = new Stack<>(); //for store the result

        //count the number of each letter in the string
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        for (char c : s.toCharArray()) {
            //decrease amount of letters this type
            count[c - 'a']--;

            //if letter already was added - let's skip it
            if (added[c - 'a']) {
                continue;
            }

            //as long as the stack is not empty and the last letter in the stack is greater than the current letter
            // and there are more letters of this type in the string, then remove the letter from the stack
            while (!stack.isEmpty() && c < stack.peek() && count[stack.peek() - 'a'] > 0) {
                added[stack.pop() - 'a'] = false;
            }

            //add current letter to Stack and mark it
            stack.push(c);
            added[c - 'a'] = true;
        }

        // stack to String
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.insert(0, stack.pop());
        }

        return result.toString(); //bingo
    }

}

//Given a string s, remove duplicate letters so that every letter appears once and only once. You must make sure
// your result is the smallest in lexicographical order
// among all possible results.

//Example 1:
//Input: s = "bcabc"
//Output: "abc"

//Example 2:
//Input: s = "cbacdcbc"
//Output: "acdb"

//Constraints:
//1 <= s.length <= 104
//s consists of lowercase English letters.
