package P2801_2900.P2840_Check_if_Strings_Can_be_Made_Equal_With_Operations_II;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String s1 = "abcdba";
        String s2 = "cabdab";

        System.out.println(solution.checkStrings(s1, s2)); // true

        String s3 = "abe";
        String s4 = "bea";

        System.out.println(solution.checkStrings(s3, s4)); // false
    }

    public boolean checkStrings(String s1, String s2) {
        int n = s1.length();

        int[] even1 = new int[26];
        int[] odd1 = new int[26];
        int[] even2 = new int[26];
        int[] odd2 = new int[26];

        for (int i = 0; i < n; i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);

            if (i % 2 == 0) {
                even1[c1 - 'a']++;
                even2[c2 - 'a']++;
            } else {
                odd1[c1 - 'a']++;
                odd2[c2 - 'a']++;
            }
        }

        return Arrays.equals(even1, even2) && Arrays.equals(odd1, odd2);
    }

}

//Complexity:
// time - O(n)
// space - O(1)


//You are given two strings s1 and s2, both of length n, consisting of lowercase English letters.
//You can apply the following operation on any of the two strings any number of times:
//Choose any two indices i and j such that i < j and the difference j - i is even, then swap the two characters at
// those indices in the string.
//Return true if you can make the strings s1 and s2 equal, and false otherwise.

//Example 1:
//Input: s1 = "abcdba", s2 = "cabdab"
//Output: true
//Explanation: We can apply the following operations on s1:
//- Choose the indices i = 0, j = 2. The resulting string is s1 = "cbadba".
//- Choose the indices i = 2, j = 4. The resulting string is s1 = "cbbdaa".
//- Choose the indices i = 1, j = 5. The resulting string is s1 = "cabdab" = s2.

//Example 2:
//Input: s1 = "abe", s2 = "bea"
//Output: false
//Explanation: It is not possible to make the two strings equal.

//Constraints:
//n == s1.length == s2.length
//1 <= n <= 105
//s1 and s2 consist only of lowercase English letters.
