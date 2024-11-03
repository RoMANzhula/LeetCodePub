package P701_800.P796_Rotate_String;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.rotateString("abcde", "cdeab")); // true
        System.out.println(solution.rotateString("abcde", "abced")); // false

    }

    public boolean rotateString(String s, String goal) {
        if (s.length() != goal.length()) return false;

        String doubleStringS = s + s;

        return doubleStringS.contains(goal);
    }

}

//Explanation:
//Length Check: If s and goal have different lengths, then s can never be rotated to match goal, so we return
// false immediately.
//Concatenate s with itself: By doing s + s, we create a string that includes all possible rotations of s.
//Check for Substring: If goal is a substring of doubleS, then s can be rotated to match
// goal. Otherwise, it can't, so we return false.


//Given two strings s and goal, return true if and only if s can become goal after some number of shifts on s.
//A shift on s consists of moving the leftmost character of s to the rightmost position.
//For example, if s = "abcde", then it will be "bcdea" after one shift.
//
//Example 1:
//Input: s = "abcde", goal = "cdeab"
//Output: true

//Example 2:
//Input: s = "abcde", goal = "abced"
//Output: false
//
//Constraints:
//1 <= s.length, goal.length <= 100
//s and goal consist of lowercase English letters.