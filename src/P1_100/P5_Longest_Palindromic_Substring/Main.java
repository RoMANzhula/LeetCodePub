package P1_100.P5_Longest_Palindromic_Substring;

public class Main {

    public static void main(String[] args) {
        String s1 = "babad";
        String s2 = "cbbd";

        System.out.println("Input: " + s1);
        System.out.println("Output: " + longestPalindrome(s1));

        System.out.println("Input: " + s2);
        System.out.println("Output: " + longestPalindrome(s2));
    }

    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }

        //preprocess the string to handle even-length palindromes
        StringBuilder modifiedString = new StringBuilder("^#");
        for (char c : s.toCharArray()) {
            modifiedString.append(c);
            modifiedString.append('#');
        }
        modifiedString.append("$");

        int[] p = new int[modifiedString.length()];
        int center = 0; //center of the rightmost palindromic substring
        int right = 0; //right boundary of the rightmost palindromic substring
        int maxLen = 0; //length of the longest palindromic substring
        int maxCenter = 0; //center of the longest palindromic substring

        for (int i = 1; i < modifiedString.length() - 1; i++) {
            int mirror = 2 * center - i; //mirror of the current index with respect to the center

            if (i < right) {
                p[i] = Math.min(right - i, p[mirror]);
            }

            //attempt to expand palindrome centered at i
            while (modifiedString.charAt(i + p[i] + 1) == modifiedString.charAt(i - p[i] - 1)) {
                p[i]++;
            }

            //if palindrome centered at i expands past right boundary, adjust center and right boundary
            if (i + p[i] > right) {
                center = i;
                right = i + p[i];

                //if the length of the palindrome is greater than the maxLen, update maxLen and maxCenter
                if (p[i] > maxLen) {
                    maxLen = p[i];
                    maxCenter = i;
                }
            }
        }

        int start = (maxCenter - maxLen) / 2;
        return s.substring(start, start + maxLen);
    }

}

//Given a string s, return the longest palindromic substring in s.
//
//Example 1:
//Input: s = "babad"
//Output: "bab"
//Explanation: "aba" is also a valid answer.

//Example 2:
//Input: s = "cbbd"
//Output: "bb"

//Constraints:
//1 <= s.length <= 1000
//s consist of only digits and English letters.
