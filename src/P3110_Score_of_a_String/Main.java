package P3110_Score_of_a_String;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        String s1 = "hello";
        System.out.println("Score of \"" + s1 + "\": " + solution.scoreOfString(s1));  // Output: 13

        // Test case 2
        String s2 = "zaz";
        System.out.println("Score of \"" + s2 + "\": " + solution.scoreOfString(s2));  // Output: 50
    }

    public int scoreOfString(String s) {
        int score = 0;
        // Iterate over the string from the first to the second last character
        for (int i = 0; i < s.length() - 1; i++) {
            // Get the ASCII values of adjacent characters and calculate the absolute difference
            int diff = Math.abs(s.charAt(i) - s.charAt(i + 1));
            // Add the difference to the score
            score += diff;
        }
        return score;
    }
}

//You are given a string s. The score of a string is defined as the sum of the absolute difference between the
// ASCII values of adjacent characters.
//Return the score of s.
//
//Example 1:
//Input: s = "hello"
//Output: 13
//Explanation:
//The ASCII values of the characters in s are: 'h' = 104, 'e' = 101, 'l' = 108, 'o' = 111. So, the score of s would
// be |104 - 101| + |101 - 108| + |108 - 108| + |108 - 111| = 3 + 7 + 0 + 3 = 13.
//
//Example 2:
//Input: s = "zaz"
//Output: 50
//Explanation:
//The ASCII values of the characters in s are: 'z' = 122, 'a' = 97. So, the score of s would
// be |122 - 97| + |97 - 122| = 25 + 25 = 50.
//
//Constraints:
//2 <= s.length <= 100
//s consists only of lowercase English letters.