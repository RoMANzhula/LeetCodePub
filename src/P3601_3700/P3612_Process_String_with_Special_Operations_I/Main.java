package P3601_3700.P3612_Process_String_with_Special_Operations_I;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.processStr("a#b%*")); // ba
        System.out.println(solution.processStr("z*#"));   // ""
        System.out.println(solution.processStr("abc"));   // abc
        System.out.println(solution.processStr("ab#"));   // abab
        System.out.println(solution.processStr("ab%"));   // ba
    }

    public String processStr(String s) {
        StringBuilder result = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                // append character
                result.append(c);
            } else if (c == '*') {
                // remove last character if exists
                if (result.length() > 0) {
                    result.deleteCharAt(result.length() - 1);
                }
            } else if (c == '#') {
                // duplicate current string
                result.append(result.toString());
            } else if (c == '%') {
                // reverse current string
                result.reverse();
            }
        }

        return result.toString();
    }

}

//Complexity:
// time - O(n * m)
// space - O(m)
// m is the length of the final constructed string


//You are given a string s consisting of lowercase English letters and the special characters: *, #, and %.
//Build a new string result by processing s according to the following rules from left to right:
//If the letter is a lowercase English letter append it to result.
//A '*' removes the last character from result, if it exists.
//A '#' duplicates the current result and appends it to itself.
//A '%' reverses the current result.
//Return the final string result after processing all characters in s.

//Example 1:
//Input: s = "a#b%*"
//Output: "ba"
//Explanation:
//i	s[i]	Operation	                Current result
//0	'a'	    Append 'a'	                    "a"
//1	'#'	    Duplicate result	            "aa"
//2	'b'	    Append 'b'	                    "aab"
//3	'%'	    Reverse result	                "baa"
//4	'*'	    Remove the last character	    "ba"
//Thus, the final result is "ba".

//Example 2:
//Input: s = "z*#"
//Output: ""
//Explanation:
//i	s[i]	Operation	                    Current result
//0	'z'	        Append 'z'	                    "z"
//1	'*'	        Remove the last character	    ""
//2	'#'	        Duplicate the string	        ""
//Thus,the final result is "".

//Constraints:
//1 <= s.length <= 20
//s consists of only lowercase English letters and special characters *, #, and %.
