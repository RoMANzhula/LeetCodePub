package P201_300.P214_Shortest_Palindrome;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        String s1 = "aacecaaa";
        System.out.println(solution.shortestPalindrome(s1));  // Output: "aaacecaaa"

        String s2 = "abcd";
        System.out.println(solution.shortestPalindrome(s2));  // Output: "dcbabcd"
    }

    public String shortestPalindrome(String s) {
        // Step 1: Reverse the string and concatenate it with the original string using a separator.
        String reversed = new StringBuilder(s).reverse().toString();
        String combined = s + "#" + reversed;

        // Step 2: Build the LPS array for the combined string.
        int[] lps = buildLPS(combined);

        // Step 3: Determine the length of the longest palindromic prefix.
        int longestPalindromicPrefixLength = lps[combined.length() - 1];

        // Step 4: Add the necessary characters to the front of the original string.
        String nonPalindromicSuffix = s.substring(longestPalindromicPrefixLength);

        return new StringBuilder(nonPalindromicSuffix).reverse() + s;
    }

    // Helper function to build the LPS (Longest Prefix Suffix) array
    private int[] buildLPS(String str) {
        int n = str.length();
        int[] lps = new int[n];
        int length = 0;  // Length of the previous longest prefix suffix
        int i = 1;

        while (i < n) {
            if (str.charAt(i) == str.charAt(length)) {
                length++;
                lps[i] = length;
                i++;
            } else {
                if (length != 0) {
                    length = lps[length - 1];  // Fall back in the pattern
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

}

//Explanation:
//Reverse and Concatenate:
//For example, if the input string is "abcd", the combined string will be "abcd#dcba".
//LPS Array:
//The LPS array computes the length of the longest prefix of the combined string that is also a suffix. This
// helps us find the longest palindrome that starts from the beginning of the original string.
//Using the LPS Array:
//If the LPS value is x, this means the first x characters of the original string form a palindrome. The remaining
// characters (from index x onward) need to be mirrored at the front to complete the palindrome.
//Output:
//For the input "abcd", the LPS array helps us identify that we need to add "dcb" to the front to make it
// a palindrome: "dcbabcd".
//Time Complexity:
//The time complexity of this solution is O(n), where n is the length of the string. Building the LPS array is
// linear, and reversing the string also takes linear time.


//You are given a string s. You can convert s to a
//palindrome
// by adding characters in front of it.
//Return the shortest palindrome you can find by performing this transformation.
//
//Example 1:
//Input: s = "aacecaaa"
//Output: "aaacecaaa"

//Example 2:
//Input: s = "abcd"
//Output: "dcbabcd"
//
//Constraints:
//0 <= s.length <= 5 * 104
//s consists of lowercase English letters only.