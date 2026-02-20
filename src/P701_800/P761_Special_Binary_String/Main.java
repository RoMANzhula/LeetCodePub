package P701_800.P761_Special_Binary_String;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String s1 = "11011000";
        System.out.println(solution.makeLargestSpecial(s1)); // 11100100

        String s2 = "10";
        System.out.println(solution.makeLargestSpecial(s2)); // 10
    }

    public String makeLargestSpecial(String s) {
        if (s.length() <= 2) {
            return s;
        }

        List<String> parts = new ArrayList<>();
        int balance = 0;
        int start = 0;

        //split into top-level special substrings
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                balance++;
            } else {
                balance--;
            }

            // when balance becomes 0 → we found one special substring
            if (balance == 0) {
                // Recursively process inner substring
                String inner = s.substring(start + 1, i);
                String processed = makeLargestSpecial(inner);
                parts.add("1" + processed + "0");
                start = i + 1;
            }
        }

        // sort descending to make lexicographically largest
        Collections.sort(parts, Collections.reverseOrder());

        // join all parts
        StringBuilder result = new StringBuilder();
        for (String part : parts) {
            result.append(part);
        }

        return result.toString();
    }

}

//Complexity:
// time and space - O(n^2)


//Special binary strings are binary strings with the following two properties:
//The number of 0's is equal to the number of 1's.
//Every prefix of the binary string has at least as many 1's as 0's.
//You are given a special binary string s.
//A move consists of choosing two consecutive, non-empty, special substrings of s, and swapping them. Two strings are
// consecutive if the last character of the first string is exactly one index before the first character of the
// second string.
//Return the lexicographically largest resulting string possible after applying the mentioned operations on the string.

//Example 1:
//Input: s = "11011000"
//Output: "11100100"
//Explanation: The strings "10" [occuring at s[1]] and "1100" [at s[3]] are swapped.
//This is the lexicographically largest string possible after some number of swaps.

//Example 2:
//Input: s = "10"
//Output: "10"

//Constraints:
//1 <= s.length <= 50
//s[i] is either '0' or '1'.
//s is a special binary string.
