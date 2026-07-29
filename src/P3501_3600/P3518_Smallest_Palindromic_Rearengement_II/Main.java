package P3501_3600.P3518_Smallest_Palindromic_Rearengement_II;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.smallestPalindrome("abba", 2));   // baab
        System.out.println(solution.smallestPalindrome("aa", 2));     // ""
        System.out.println(solution.smallestPalindrome("bacab", 1));  // abcba
    }

    private static final long MAX = 1_000_001L;

    public String smallestPalindrome(String s, int k) {
        int[] count = new int[26];

        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        if (!isPalindromePossible(count)) {
            return "";
        }

        int[] halfCount = new int[26];
        String midLetter = "";

        for (int i = 0; i < 26; i++) {
            halfCount[i] = count[i] / 2;
            if ((count[i] & 1) == 1) {
                midLetter = String.valueOf((char) ('a' + i));
            }
        }

        long totalPerm = countArrangements(halfCount);

        if (k > totalPerm) {
            return "";
        }

        StringBuilder left = generateLeftHalf(halfCount, k);

        StringBuilder ans = new StringBuilder();
        ans.append(left);
        ans.append(midLetter);
        ans.append(new StringBuilder(left).reverse());

        return ans.toString();
    }

    private boolean isPalindromePossible(int[] count) {
        int odd = 0;

        for (int x : count) {
            if ((x & 1) == 1) {
                odd++;
            }
        }

        return odd <= 1;
    }

    private StringBuilder generateLeftHalf(int[] halfCount, long k) {

        int halfLen = 0;
        for (int x : halfCount) {
            halfLen += x;
        }

        StringBuilder left = new StringBuilder();

        for (int pos = 0; pos < halfLen; pos++) {

            for (int c = 0; c < 26; c++) {

                if (halfCount[c] == 0) {
                    continue;
                }

                halfCount[c]--;

                long arrangements = countArrangements(halfCount);

                if (arrangements >= k) {
                    left.append((char) ('a' + c));
                    break;
                } else {
                    k -= arrangements;
                    halfCount[c]++;
                }
            }
        }

        return left;
    }

    private long countArrangements(int[] count) {

        int total = 0;
        for (int x : count) {
            total += x;
        }

        long res = 1;

        for (int freq : count) {

            if (freq == 0) {
                continue;
            }

            res *= nCk(total, freq);

            if (res >= MAX) {
                return MAX;
            }

            total -= freq;
        }

        return res;
    }

    private long nCk(int n, int k) {

        k = Math.min(k, n - k);

        long res = 1;

        for (int i = 1; i <= k; i++) {
            res = res * (n - i + 1) / i;

            if (res >= MAX) {
                return MAX;
            }
        }

        return res;
    }

}

// Complexity:
// time - O(26 * n^2)
// space - O(26)


//You are given a palindromic string s and an integer k.
//Return the k-th lexicographically smallest palindromic permutation of s. If there are fewer than k distinct
// palindromic permutations, return an empty string.
//Note: Different rearrangements that yield the same palindromic string are considered identical and are counted once.

//Example 1:
//Input: s = "abba", k = 2
//Output: "baab"
//Explanation:
//The two distinct palindromic rearrangements of "abba" are "abba" and "baab".
//Lexicographically, "abba" comes before "baab". Since k = 2, the output is "baab".

//Example 2:
//Input: s = "aa", k = 2
//Output: ""
//Explanation:
//There is only one palindromic rearrangement: "aa".
//The output is an empty string since k = 2 exceeds the number of possible rearrangements.

//Example 3:
//Input: s = "bacab", k = 1
//Output: "abcba"
//Explanation:
//The two distinct palindromic rearrangements of "bacab" are "abcba" and "bacab".
//Lexicographically, "abcba" comes before "bacab". Since k = 1, the output is "abcba".

//Constraints:
//1 <= s.length <= 104
//s consists of lowercase English letters.
//s is guaranteed to be palindromic.
//1 <= k <= 106
