package P401_500.P459_Repeated_Substring_Pattern;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();
        System.out.println(solution.repeatedSubstringPattern("abab"));         // Output: true
        System.out.println(solution.repeatedSubstringPattern("aba"));          // Output: false
        System.out.println(solution.repeatedSubstringPattern("abcabcabcabc")); // Output: true
    }

    public boolean repeatedSubstringPattern(String s) {
        int stringLength = s.length();

        for (int length = 1; length <= stringLength / 2; length++) { //repeat substring can't be large than half of all string
            if (stringLength % length == 0) { //if divided without a remainder
                String substring = s.substring(0, length); //give repeat substring in variable
                if (substring.repeat(stringLength / length).equals(s)) { //if we have repeat substring in "s"
                    return true; //have true
                }
            }
        }

        return false; //else - false
    }

}


//Given a string s, check if it can be constructed by taking a substring of it and appending multiple copies of
// the substring together.

//Example 1:
//Input: s = "abab"
//Output: true
//Explanation: It is the substring "ab" twice.

//Example 2:
//Input: s = "aba"
//Output: false

//Example 3:
//Input: s = "abcabcabcabc"
//Output: true
//Explanation: It is the substring "abc" four times or the substring "abcabc" twice.

//Constraints:
//1 <= s.length <= 104
//s consists of lowercase English letters.
