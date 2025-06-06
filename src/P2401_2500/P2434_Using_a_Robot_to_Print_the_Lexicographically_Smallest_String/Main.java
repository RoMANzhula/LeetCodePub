package P2401_2500.P2434_Using_a_Robot_to_Print_the_Lexicographically_Smallest_String;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.robotWithString("zza"));   // Output: "azz"
        System.out.println(solution.robotWithString("bac"));   // Output: "abc"
        System.out.println(solution.robotWithString("bdda"));  // Output: "addb"
    }

    public String robotWithString(String s) {
        int[] freq = new int[26]; // to store frequency of characters in s

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        StringBuilder t = new StringBuilder(); // robot's stack
        StringBuilder p = new StringBuilder(); // result string

        int minCharIndex = 0;

        for (char c : s.toCharArray()) {
            // move character to t
            t.append(c);
            freq[c - 'a']--;

            // update the smallest remaining character index
            while (minCharIndex < 26 && freq[minCharIndex] == 0) {
                minCharIndex++;
            }

            // pop from t to p while it's safe (t's last char <= smallest in s)
            while (!t.isEmpty() && t.charAt(t.length() - 1) - 'a' <= minCharIndex) {
                p.append(t.charAt(t.length() - 1));
                t.setLength(t.length() - 1); // pop last character
            }
        }

        return p.toString();
    }

}

//Explanation:
//We maintain a frequency array of the characters left in s.
//We always know the current smallest character still in s.
//When the top of t is smaller than or equal to the smallest character remaining in s, we safely pop it to p.
//Complexity:
// time and space - O(n)


//You are given a string s and a robot that currently holds an empty string t. Apply one of the following
// operations until s and t are both empty:
//Remove the first character of a string s and give it to the robot. The robot will append this character to
// the string t.
//Remove the last character of a string t and give it to the robot. The robot will write this character on paper.
//Return the lexicographically smallest string that can be written on the paper.

//Example 1:
//Input: s = "zza"
//Output: "azz"
//Explanation: Let p denote the written string.
//Initially p="", s="zza", t="".
//Perform first operation three times p="", s="", t="zza".
//Perform second operation three times p="azz", s="", t="".

//Example 2:
//Input: s = "bac"
//Output: "abc"
//Explanation: Let p denote the written string.
//Perform first operation twice p="", s="c", t="ba".
//Perform second operation twice p="ab", s="c", t="".
//Perform first operation p="ab", s="", t="c".
//Perform second operation p="abc", s="", t="".

//Example 3:
//Input: s = "bdda"
//Output: "addb"
//Explanation: Let p denote the written string.
//Initially p="", s="bdda", t="".
//Perform first operation four times p="", s="", t="bdda".
//Perform second operation four times p="addb", s="", t="".

//Constraints:
//1 <= s.length <= 105
//s consists of only English lowercase letters.
