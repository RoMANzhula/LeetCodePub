package P2801_2900.P2839_Check_if_Strings_Can_be_Made_Equal_With_Operations_I;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.canBeEqual("abcd", "cdab")); // true
        System.out.println(solution.canBeEqual("abcd", "dacb")); // false
    }

    public boolean canBeEqual(String s1, String s2) {
        // indices 0 and 2
        char[] g1_s1 = new char[]{s1.charAt(0), s1.charAt(2)};
        char[] g1_s2 = new char[]{s2.charAt(0), s2.charAt(2)};

        // indices 1 and 3
        char[] g2_s1 = new char[]{s1.charAt(1), s1.charAt(3)};
        char[] g2_s2 = new char[]{s2.charAt(1), s2.charAt(3)};

        // sort groups
        Arrays.sort(g1_s1);
        Arrays.sort(g1_s2);
        Arrays.sort(g2_s1);
        Arrays.sort(g2_s2);

        //compare both groups
        return Arrays.equals(g1_s1, g1_s2) && Arrays.equals(g2_s1, g2_s2);
    }

}

//Complexity:
// time and space - O(1)


//You are given two strings s1 and s2, both of length 4, consisting of lowercase English letters.
//You can apply the following operation on any of the two strings any number of times:
//Choose any two indices i and j such that j - i = 2, then swap the two characters at those indices in the string.
//Return true if you can make the strings s1 and s2 equal, and false otherwise.

//Example 1:
//Input: s1 = "abcd", s2 = "cdab"
//Output: true
//Explanation: We can do the following operations on s1:
//- Choose the indices i = 0, j = 2. The resulting string is s1 = "cbad".
//- Choose the indices i = 1, j = 3. The resulting string is s1 = "cdab" = s2.

//Example 2:
//Input: s1 = "abcd", s2 = "dacb"
//Output: false
//Explanation: It is not possible to make the two strings equal.

//Constraints:
//s1.length == s2.length == 4
//s1 and s2 consist only of lowercase English letters.
