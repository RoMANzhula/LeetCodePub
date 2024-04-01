package P1_100.P58_Length_of_Last_Word;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        String s1 = "Hello World";
        System.out.println("Output for \"" + s1 + "\": " + solution.lengthOfLastWord(s1)); // output 5

        String s2 = "   fly me   to   the moon  ";
        System.out.println("Output for \"" + s2 + "\": " + solution.lengthOfLastWord(s2)); //output 4

        String s3 = "luffy is still joyboy";
        System.out.println("Output for \"" + s3 + "\": " + solution.lengthOfLastWord(s3)); //output 6
    }

//    public int lengthOfLastWord(String s) {
//        String[] res = s.split(" ");
//        int result = res[res.length - 1].length();
//        return result;
//    }

    //the faster solve
    public int lengthOfLastWord(String s) {
        // remove trailing and leading spaces
        s = s.trim();

        int length = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                break; // we've reached the end of the last word
            }
            length++;
        }

        return length;
    }


}

//Given a string s consisting of words and spaces, return the length of the last word in the string.
//A word is a maximal
//substring
// consisting of non-space characters only.
//
//Example 1:
//Input: s = "Hello World"
//Output: 5
//Explanation: The last word is "World" with length 5.

//Example 2:
//Input: s = "   fly me   to   the moon  "
//Output: 4
//Explanation: The last word is "moon" with length 4.

//Example 3:
//Input: s = "luffy is still joyboy"
//Output: 6
//Explanation: The last word is "joyboy" with length 6.
//
//Constraints:
//1 <= s.length <= 104
//s consists of only English letters and spaces ' '.
//There will be at least one word in s.
