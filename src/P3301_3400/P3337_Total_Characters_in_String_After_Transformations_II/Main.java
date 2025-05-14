package P3301_3400.P3337_Total_Characters_in_String_After_Transformations_II;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String s1 = "abcyy";
        int t1 = 2;
        List<Integer> nums1 = new ArrayList<>(Collections.nCopies(26, 1));
        nums1.set(25, 2); // z = 2
        System.out.println(solution.lengthAfterTransformation(s1, t1, nums1)); // Output: 7

        String s2 = "azbk";
        int t2 = 1;
        List<Integer> nums2 = new ArrayList<>(Collections.nCopies(26, 2));
        System.out.println(solution.lengthAfterTransformation(s2, t2, nums2)); // Output: 8
    }

    private static final int MOD = 1_000_000_007;

    public int lengthAfterTransformation(String s, int t, List<Integer> nums) {
        long[] current = new long[26];

        // initialize character counts from the string
        for (char c : s.toCharArray()) {
            current[c - 'a']++;
        }

        // build the transformation matrix based on nums
        long[][] transform = new long[26][26];
        for (int i = 0; i < 26; i++) {
            int count = nums.get(i);
            for (int j = 1; j <= count; j++) {
                transform[i][(i + j) % 26]++;
            }
        }

        //matrix exponentiation: transform^t
        long[][] resultMatrix = matrixPower(transform, t);

        // apply the transformation matrix to the initial character counts
        long[] finalCounts = new long[26];
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                finalCounts[j] = (finalCounts[j] + current[i] * resultMatrix[i][j]) % MOD;
            }
        }

        //sum all character counts to get the final string length
        long totalLength = 0;
        for (long count : finalCounts) {
            totalLength = (totalLength + count) % MOD;
        }

        return (int) totalLength;
    }

    private long[][] matrixPower(long[][] matrix, int power) {
        long[][] result = new long[26][26];

        // identity matrix
        for (int i = 0; i < 26; i++) {
            result[i][i] = 1;
        }

        while (power > 0) {
            if ((power & 1) == 1) {
                result = multiplyMatrices(result, matrix);
            }
            matrix = multiplyMatrices(matrix, matrix);
            power >>= 1;
        }

        return result;
    }

    private long[][] multiplyMatrices(long[][] a, long[][] b) {
        long[][] res = new long[26][26];
        for (int i = 0; i < 26; i++) {
            for (int k = 0; k < 26; k++) {
                for (int j = 0; j < 26; j++) {
                    res[i][j] = (res[i][j] + a[i][k] * b[k][j]) % MOD;
                }
            }
        }
        return res;
    }

}

//Complexity:
// time - O(n + log t )
// space - O(1)


//You are given a string s consisting of lowercase English letters, an integer t representing the number of
// transformations to perform, and an array nums of size 26. In one transformation, every character in s is
// replaced according to the following rules:
//Replace s[i] with the next nums[s[i] - 'a'] consecutive characters in the alphabet. For
// example, if s[i] = 'a' and nums[0] = 3, the character 'a' transforms into the next 3 consecutive characters
// ahead of it, which results in "bcd".
//The transformation wraps around the alphabet if it exceeds 'z'. For example, if s[i] = 'y' and nums[24] = 3, the
// character 'y' transforms into the next 3 consecutive characters ahead of it, which results in "zab".
//Return the length of the resulting string after exactly t transformations.
//Since the answer may be very large, return it modulo 109 + 7.

//Example 1:
//Input: s = "abcyy", t = 2, nums = [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2]
//Output: 7
//Explanation:
//First Transformation (t = 1):
//'a' becomes 'b' as nums[0] == 1
//'b' becomes 'c' as nums[1] == 1
//'c' becomes 'd' as nums[2] == 1
//'y' becomes 'z' as nums[24] == 1
//'y' becomes 'z' as nums[24] == 1
//String after the first transformation: "bcdzz"
//Second Transformation (t = 2):
//'b' becomes 'c' as nums[1] == 1
//'c' becomes 'd' as nums[2] == 1
//'d' becomes 'e' as nums[3] == 1
//'z' becomes 'ab' as nums[25] == 2
//'z' becomes 'ab' as nums[25] == 2
//String after the second transformation: "cdeabab"
//Final Length of the string: The string is "cdeabab", which has 7 characters.

//Example 2:
//Input: s = "azbk", t = 1, nums = [2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2]
//Output: 8
//Explanation:
//First Transformation (t = 1):
//'a' becomes 'bc' as nums[0] == 2
//'z' becomes 'ab' as nums[25] == 2
//'b' becomes 'cd' as nums[1] == 2
//'k' becomes 'lm' as nums[10] == 2
//String after the first transformation: "bcabcdlm"
//Final Length of the string: The string is "bcabcdlm", which has 8 characters.

//Constraints:
//1 <= s.length <= 105
//s consists only of lowercase English letters.
//1 <= t <= 109
//nums.length == 26
//1 <= nums[i] <= 25
