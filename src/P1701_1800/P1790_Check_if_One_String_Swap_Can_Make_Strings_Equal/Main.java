package P1701_1800.P1790_Check_if_One_String_Swap_Can_Make_Strings_Equal;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.areAlmostEqual("bank", "kanb")); // true
        System.out.println(solution.areAlmostEqual("attack", "defend")); // false
        System.out.println(solution.areAlmostEqual("kelb", "kelb")); // true
        System.out.println(solution.areAlmostEqual("abcd", "abdc")); // true
        System.out.println(solution.areAlmostEqual("abcd", "acbd")); // false
    }

    public boolean areAlmostEqual(String s1, String s2) {
        if (s1.equals(s2)) return true;

        int first = -1, second = -1, diffCount = 0;

        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diffCount++;

                if (first == -1) first = i;
                else if (second == -1) second = i;
                else return false; // more than 2 differences - impossible
            }
        }

        return diffCount == 2
                && s1.charAt(first) == s2.charAt(second)
                && s1.charAt(second) == s2.charAt(first)
        ;
    }
}

//Complexity:
// time complexity: O(n)
// space complexity: O(1)


//You are given two strings s1 and s2 of equal length. A string swap is an operation where you choose two
// indices in a string (not necessarily different) and swap the characters at these indices.
//Return true if it is possible to make both strings equal by performing at most one string swap on exactly one
// of the strings. Otherwise, return false.
//
//Example 1:
//Input: s1 = "bank", s2 = "kanb"
//Output: true
//Explanation: For example, swap the first character with the last character of s2 to make "bank".
//Example 2:
//Input: s1 = "attack", s2 = "defend"
//Output: false
//Explanation: It is impossible to make them equal with one string swap.

//Example 3:
//Input: s1 = "kelb", s2 = "kelb"
//Output: true
//Explanation: The two strings are already equal, so no string swap operation is required.
//
//Constraints:
//1 <= s1.length, s2.length <= 100
//s1.length == s2.length
//s1 and s2 consist of only lowercase English letters.