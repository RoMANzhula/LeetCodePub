package P1301_1400.P1358_Number_of_Substrings_Containing_All_Three_Characters;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.numberOfSubstrings("abcabc")); // Output: 10
        System.out.println(solution.numberOfSubstrings("aaacb"));  // Output: 3
        System.out.println(solution.numberOfSubstrings("abc"));    // Output: 1
    }

    public int numberOfSubstrings(String s) {
        int[] count = new int[3]; // to count occurrences of 'a', 'b', 'c'
        int len = s.length();
        int left = 0, total = 0;

        for (int right = 0; right < len; right++) {
            count[s.charAt(right) - 'a']++; // increase the count of current character

            // check if the window contains at least one of each character
            while (count[0] > 0 && count[1] > 0 && count[2] > 0) {
                total += len - right; // count substrings
                count[s.charAt(left) - 'a']--; // shrink window
                left++;
            }
        }

        return total;
    }

}

//Explanation with Example "abcabc"
//Start expanding right and counting occurrences of 'a', 'b', 'c'.
//Once a valid window [left, right] is found, count all substrings that start within this window and end at right.
//Move left to try and shrink the window while maintaining the condition.
//Complexity: O(n)


//Given a string s consisting only of characters a, b and c.
//Return the number of substrings containing at least one occurrence of all these characters a, b and c.

//Example 1:
//Input: s = "abcabc"
//Output: 10
//Explanation: The substrings containing at least one occurrence of the characters a, b and c are
// "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again).

//Example 2:
//Input: s = "aaacb"
//Output: 3
//Explanation: The substrings containing at least one occurrence of the characters a, b and c are
// "aaacb", "aacb" and "acb".

//Example 3:
//Input: s = "abc"
//Output: 1

//Constraints:
//3 <= s.length <= 5 x 10^4
//s only consists of a, b or c characters.
