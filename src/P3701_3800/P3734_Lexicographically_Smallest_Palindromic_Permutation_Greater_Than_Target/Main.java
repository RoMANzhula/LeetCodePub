package P3701_3800.P3734_Lexicographically_Smallest_Palindromic_Permutation_Greater_Than_Target;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String s1 = "baba";
        String target1 = "abba";
        System.out.println(solution.lexPalindromicPermutation(s1, target1)); // baab

        String s2 = "baba";
        String target2 = "bbaa";
        System.out.println(solution.lexPalindromicPermutation(s2, target2)); // ""
    }

    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();

        // count characters in s
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // check whether a palindrome is possible
        int oddCount = 0;
        char middle = 0;

        for (int i = 0; i < 26; i++) {
            if ((count[i] & 1) == 1) {
                oddCount++;
                middle = (char) ('a' + i);
            }
        }

        if (oddCount > 1) {
            return "";
        }

        // build counts for the left half
        int halfLen = n / 2;
        int[] halfCount = new int[26];

        for (int i = 0; i < 26; i++) {
            halfCount[i] = count[i] / 2;
        }

        char[] left = new char[halfLen];

        for (int i = 0; i < halfLen; i++) {
            left[i] = target.charAt(i);
        }

        if (canBuild(left, halfCount)) {
            String palindrome = buildPalindrome(left, middle, n);

            if (palindrome.compareTo(target) > 0) {
                return palindrome;
            }
        }

        for (int pos = halfLen - 1; pos >= 0; pos--) {
            // characters used before 'pos'
            int[] remaining = halfCount.clone();

            boolean possible = true;

            for (int i = 0; i < pos; i++) {
                int ch = target.charAt(i) - 'a';

                if (remaining[ch] == 0) {
                    possible = false; break;
                }

                remaining[ch]--;
            }

            if (!possible) {
                continue;
            }

            // at position pos, choose the smallest character strictly greater than target[pos]
            int targetChar = target.charAt(pos) - 'a';

            for (int ch = targetChar + 1; ch < 26; ch++) {
                if (remaining[ch] == 0) {
                    continue;
                }


                remaining[ch]--;

                char[] candidate = new char[halfLen];

                // prefix equal to target
                for (int i = 0; i < pos; i++) {
                    candidate[i] = target.charAt(i);
                }

                // increased character
                candidate[pos] = (char) ('a' + ch);

                // fill the rest with smallest possible characters
                int index = pos + 1;

                for (int c = 0; c < 26; c++) {
                    while (remaining[c] > 0) {
                        candidate[index++] = (char) ('a' + c);
                        remaining[c]--;
                    }
                }

                return buildPalindrome(candidate, middle, n);
            }

        }

        return "";

    }
    // checks whether the given left half can be constructed from the available character counts
    private boolean canBuild(char[] left, int[] count) {
        int[] remaining = count.clone();

        for (char c : left) {
            int idx = c - 'a';

            if (remaining[idx] == 0) {
                return false;
            }

            remaining[idx]--;
        }

        return true;
    }

    // builds the complete palindrome from its left half.
    private String buildPalindrome(char[] left, char middle, int n) {
        StringBuilder result = new StringBuilder(n);

        // left half
        for (char c : left) { result.append(c); }

        // middle character for odd length
        if ((n & 1) == 1) {
            result.append(middle);
        }

        // right half = reverse(left half)
        for (int i = left.length - 1; i >= 0; i--) {
            result.append(left[i]);
        }

        return result.toString();

    }

}

//Complexity:
// time - O(n * 26)
// space - O(n)


//You are given two strings s and target, each of length n, consisting of lowercase English letters.
//Return the lexicographically smallest string that is both a palindromic permutation of s and strictly greater than
// target. If no such permutation exists, return an empty string.

//Example 1:
//Input: s = "baba", target = "abba"
//Output: "baab"
//Explanation:
//The palindromic permutations of s (in lexicographical order) are "abba" and "baab".
//The lexicographically smallest permutation that is strictly greater than target is "baab".

//Example 2:
//Input: s = "baba", target = "bbaa"
//Output: ""
//Explanation:
//The palindromic permutations of s (in lexicographical order) are "abba" and "baab".
//None of them is lexicographically strictly greater than target. Therefore, the answer is "".

//Example 3:
//Input: s = "abc", target = "abb"
//Output: ""
//Explanation:
//s has no palindromic permutations. Therefore, the answer is "".

//Example 4:
//Input: s = "aac", target = "abb"
//Output: "aca"
//Explanation:
//The only palindromic permutation of s is "aca".
//"aca" is strictly greater than target. Therefore, the answer is "aca".

//Constraints:
//1 <= n == s.length == target.length <= 300
//s and target consist of only lowercase English letters.
