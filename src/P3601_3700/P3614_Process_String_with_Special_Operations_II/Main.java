package P3601_3700.P3614_Process_String_with_Special_Operations_II;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.processStr("a#b%*", 1));   // a
        System.out.println(solution.processStr("cd%#*#", 3));  // d
        System.out.println(solution.processStr("z*#", 0));     // .
    }

    public char processStr(String s, long k) {
        int n = s.length();
        long[] len = new long[n + 1];

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            long cur = len[i];

            if (c >= 'a' && c <= 'z') {
                len[i + 1] = cur + 1;
            } else if (c == '*') {
                len[i + 1] = Math.max(0, cur - 1);
            } else if (c == '#') {
                len[i + 1] = cur * 2;
            } else { // '%'
                len[i + 1] = cur;
            }
        }

        if (k >= len[n]) {
            return '.';
        }

        long idx = k;

        for (int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);
            long before = len[i];
            long after = len[i + 1];

            if (c >= 'a' && c <= 'z') {
                if (idx == before) {
                    return c;
                }
            } else if (c == '#') {
                if (before > 0) {
                    idx %= before;
                }
            } else if (c == '%') {
                idx = after - 1 - idx;
            }
            // '*' does not change idx when going backwards.
        }

        return '.';
    }

}

//Complexity:
// time and space - O(n)


//You are given a string s consisting of lowercase English letters and the special characters: '*', '#', and '%'.
//You are also given an integer k.
//Build a new string result by processing s according to the following rules from left to right:
//If the letter is a lowercase English letter append it to result.
//A '*' removes the last character from result, if it exists.
//A '#' duplicates the current result and appends it to itself.
//A '%' reverses the current result.
//Return the kth character of the final string result. If k is out of the bounds of result, return '.'.

//Example 1:
//Input: s = "a#b%*", k = 1
//Output: "a"
//Explanation:
//i	s[i]	Operation	                Current result
//0	'a'	        Append 'a'	                "a"
//1	'#'	        Duplicate result	        "aa"
//2	'b'	        Append 'b'	                "aab"
//3	'%'	        Reverse result	            "baa"
//4	'*'	        Remove the last character	"ba"
//The final result is "ba". The character at index k = 1 is 'a'.

//Example 2:
//Input: s = "cd%#*#", k = 3
//Output: "d"
//Explanation:
//i	s[i]	Operation	                        Current result
//0	'c'	        Append 'c'	                        "c"
//1	'd'	        Append 'd'	                        "cd"
//2	'%'	        Reverse result	                    "dc"
//3	'#'	        Duplicate result	                "dcdc"
//4	'*'	        Remove the last character	        "dcd"
//5	'#'	        Duplicate result	                "dcddcd"
//The final result is "dcddcd". The character at index k = 3 is 'd'.

//Example 3:
//Input: s = "z*#", k = 0
//Output: "."
//Explanation:
//i	s[i]	Operation	                        Current result
//0	'z'     	Append 'z'	                        "z"
//1	'*'     	Remove the last character	        ""
//2	'#'     	Duplicate the string	            ""
//The final result is "". Since index k = 0 is out of bounds, the output is '.'.

//Constraints:
//1 <= s.length <= 105
//s consists of only lowercase English letters and special characters '*', '#', and '%'.
//0 <= k <= 1015
//The length of result after processing s will not exceed 1015.
