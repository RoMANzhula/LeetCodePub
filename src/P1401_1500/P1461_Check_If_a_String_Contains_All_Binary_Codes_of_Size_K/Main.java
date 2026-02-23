package P1401_1500.P1461_Check_If_a_String_Contains_All_Binary_Codes_of_Size_K;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String s1 = "00110110";
        int k1 = 2;
        System.out.println(solution.hasAllCodes(s1, k1)); // true

        String s2 = "0110";
        int k2 = 1;
        System.out.println(solution.hasAllCodes(s2, k2)); // true

        String s3 = "0110";
        int k3 = 2;
        System.out.println(solution.hasAllCodes(s3, k3)); // false
    }

    public boolean hasAllCodes(String s, int k) {
        int n = s.length();

        // total possible binary codes of length k
        int total = 1 << k;   // 2^k

        // sf not enough length to contain all codes
        if (n < k + total - 1) {
            return false;
        }

        boolean[] seen = new boolean[total];
        int count = 0;
        int mask = total - 1;  // to keep only last k bits
        int hash = 0;

        for (int i = 0; i < n; i++) {
            // shift left and add current bit
            hash = ((hash << 1) & mask) | (s.charAt(i) - '0');

            // start checking once we have k characters
            if (i >= k - 1) {
                if (!seen[hash]) {
                    seen[hash] = true;
                    count++;

                    if (count == total) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

}

//Complexity:
// time - O(n)
// space - O(2^k)


//Given a binary string s and an integer k, return true if every binary code of length k is a substring of s. Otherwise,
// return false.

//Example 1:
//
//Input: s = "00110110", k = 2
//Output: true
//Explanation: The binary codes of length 2 are "00", "01", "10" and "11". They can be all found as substrings at
// indices 0, 1, 3 and 2 respectively.

//Example 2:
//Input: s = "0110", k = 1
//Output: true
//Explanation: The binary codes of length 1 are "0" and "1", it is clear that both exist as a substring.

//Example 3:
//Input: s = "0110", k = 2
//Output: false
//Explanation: The binary code "00" is of length 2 and does not exist in the array.

//Constraints:
//1 <= s.length <= 5 * 105
//s[i] is either '0' or '1'.
//1 <= k <= 20
