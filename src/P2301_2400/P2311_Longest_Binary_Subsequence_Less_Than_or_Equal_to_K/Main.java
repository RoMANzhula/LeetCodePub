package P2301_2400.P2311_Longest_Binary_Subsequence_Less_Than_or_Equal_to_K;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.longestSubsequence("1001010", 5));     // Output: 5
        System.out.println(solution.longestSubsequence("00101001", 1));    // Output: 6
    }

    public int longestSubsequence(String s, int k) {
        int oneCount = 0;
        int num = 0;
        int pow = 1;
        int n = s.length();

        // take as many '1's as possible from the right
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '1') {
                if (pow > k || num + pow > k) {
                    break;
                }
                num += pow;
                oneCount++;
            }
            if (pow <= k) {
                pow <<= 1; // equivalent to pow *= 2
            }
        }

        // count all zeros in the string
        int zeroCount = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') {
                zeroCount++;
            }
        }

        return zeroCount + oneCount;
    }

}

//Complexity:
// time - O(n)
// space - O(1)


//You are given a binary string s and a positive integer k.
//Return the length of the longest subsequence of s that makes up a binary number less than or equal to k.

//Note:
//The subsequence can contain leading zeroes.
//The empty string is considered to be equal to 0.
//A subsequence is a string that can be derived from another string by deleting some or no characters without
// changing the order of the remaining characters.

//Example 1:
//Input: s = "1001010", k = 5
//Output: 5
//Explanation: The longest subsequence of s that makes up a binary number less than or equal to 5 is "00010", as
// this number is equal to 2 in decimal.
//Note that "00100" and "00101" are also possible, which are equal to 4 and 5 in decimal, respectively.
//The length of this subsequence is 5, so 5 is returned.

//Example 2:
//Input: s = "00101001", k = 1
//Output: 6
//Explanation: "000001" is the longest subsequence of s that makes up a binary number less than or equal to 1, as
// this number is equal to 1 in decimal.
//The length of this subsequence is 6, so 6 is returned.

//Constraints:
//1 <= s.length <= 1000
//s[i] is either '0' or '1'.
//1 <= k <= 109
