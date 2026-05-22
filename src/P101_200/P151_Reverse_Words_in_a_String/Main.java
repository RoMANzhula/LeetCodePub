package P101_200.P151_Reverse_Words_in_a_String;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.reverseWords("the sky is blue")); // Output: blue is sky the
        System.out.println(solution.reverseWords("  hello world  ")); // Output: world hello
        System.out.println(solution.reverseWords("a good   example")); // Output: example good a
    }

    public String reverseWords(String s) {
        String[] words = s.trim().split("\\s+");
        StringBuilder result = new StringBuilder();
        int len = words.length;

        for (int i = len - 1; i >= 0; i--) {
            result.append(words[i]);

            if (i > 0) result.append(" ");
        }

        return result.toString();

    }

}

//Complexity:
// time and space - O(n)


//Given an input string s, reverse the order of the words.
//A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
//Return a string of the words in reverse order concatenated by a single space.
//Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should
// only have a single space separating the words. Do not include any extra spaces.

//Example 1:
//Input: s = "the sky is blue"
//Output: "blue is sky the"

//Example 2:
//Input: s = "  hello world  "
//Output: "world hello"
//Explanation: Your reversed string should not contain leading or trailing spaces.

//Example 3:
//Input: s = "a good   example"
//Output: "example good a"
//Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.

//Constraints:
//1 <= s.length <= 104
//s contains English letters (upper-case and lower-case), digits, and spaces ' '.
//There is at least one word in s.
