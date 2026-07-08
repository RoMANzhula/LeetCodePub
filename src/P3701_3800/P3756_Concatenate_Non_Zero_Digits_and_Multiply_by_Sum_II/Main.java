package P3701_3800.P3756_Concatenate_Non_Zero_Digits_and_Multiply_by_Sum_II;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String s1 = "10203004";
        int[][] queries1 = {
                {0, 7},
                {1, 3},
                {4, 6}
        };

        System.out.println(Arrays.toString(
                solution.sumAndMultiply(s1, queries1)
        ));
        // [12340, 4, 9]


        String s2 = "1000";
        int[][] queries2 = {
                {0, 3},
                {1, 1}
        };

        System.out.println(Arrays.toString(
                solution.sumAndMultiply(s2, queries2)
        ));
        // [1, 0]


        String s3 = "9876543210";
        int[][] queries3 = {
                {0, 9}
        };

        System.out.println(Arrays.toString(
                solution.sumAndMultiply(s3, queries3)
        ));
        // [444444137]
    }

    private static final long MOD = 1_000_000_007L;

    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();

        List<Integer> digitsList = new ArrayList<>();
        List<Integer> posList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int d = s.charAt(i) - '0';
            if (d != 0) {
                digitsList.add(d);
                posList.add(i);
            }
        }

        int k = digitsList.size();

        int[] pos = new int[k];
        long[] prefixSum = new long[k + 1];
        long[] prefixValue = new long[k + 1];
        long[] pow10 = new long[k + 1];

        pow10[0] = 1;

        for (int i = 0; i < k; i++) {
            pos[i] = posList.get(i);

            prefixSum[i + 1] = prefixSum[i] + digitsList.get(i);

            prefixValue[i + 1] =
                    (prefixValue[i] * 10 + digitsList.get(i)) % MOD;

            pow10[i + 1] = (pow10[i] * 10) % MOD;
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int l = queries[i][0];
            int r = queries[i][1];

            int left = lowerBound(pos, l);
            int right = upperBound(pos, r) - 1;

            if (left > right) {
                ans[i] = 0;
                continue;
            }

            long sum = prefixSum[right + 1] - prefixSum[left];

            int len = right - left + 1;

            long value = prefixValue[right + 1]
                    - (prefixValue[left] * pow10[len]) % MOD;

            value %= MOD;
            if (value < 0)
                value += MOD;

            ans[i] = (int) ((value * sum) % MOD);
        }

        return ans;
    }

    private int lowerBound(int[] arr, int target) {
        int l = 0;
        int r = arr.length;

        while (l < r) {
            int mid = (l + r) >>> 1;

            if (arr[mid] < target)
                l = mid + 1;
            else
                r = mid;
        }

        return l;
    }

    private int upperBound(int[] arr, int target) {
        int l = 0;
        int r = arr.length;

        while (l < r) {
            int mid = (l + r) >>> 1;

            if (arr[mid] <= target)
                l = mid + 1;
            else
                r = mid;
        }

        return l;
    }

}

//Complexity:
// time - O(m + q log m)
// space - O(m)
// m = s.length()


//You are given a string s of length m consisting of digits. You are also given a 2D integer array queries, where
// queries[i] = [li, ri].
//For each queries[i], extract the substring s[li..ri]. Then, perform the following:
//Form a new integer x by concatenating all the non-zero digits from the substring in their original order. If there
// are no non-zero digits, x = 0.
//Let sum be the sum of digits in x. The answer is x * sum.
//Return an array of integers answer where answer[i] is the answer to the ith query.
//Since the answers may be very large, return them modulo 109 + 7.

//Example 1:
//Input: s = "10203004", queries = [[0,7],[1,3],[4,6]]
//Output: [12340, 4, 9]
//Explanation:
//s[0..7] = "10203004"
//x = 1234
//sum = 1 + 2 + 3 + 4 = 10
//Therefore, answer is 1234 * 10 = 12340.
//s[1..3] = "020"
//x = 2
//sum = 2
//Therefore, the answer is 2 * 2 = 4.
//s[4..6] = "300"
//x = 3
//sum = 3
//Therefore, the answer is 3 * 3 = 9.

//Example 2:
//Input: s = "1000", queries = [[0,3],[1,1]]
//Output: [1, 0]
//Explanation:
//s[0..3] = "1000"
//x = 1
//sum = 1
//Therefore, the answer is 1 * 1 = 1.
//s[1..1] = "0"
//x = 0
//sum = 0
//Therefore, the answer is 0 * 0 = 0.

//Example 3:
//Input: s = "9876543210", queries = [[0,9]]
//Output: [444444137]
//Explanation:
//s[0..9] = "9876543210"
//x = 987654321
//sum = 9 + 8 + 7 + 6 + 5 + 4 + 3 + 2 + 1 = 45
//Therefore, the answer is 987654321 * 45 = 44444444445.
//We return 44444444445 modulo (109 + 7) = 444444137.

//Constraints:
//1 <= m == s.length <= 105
//s consists of digits only.
//1 <= queries.length <= 105
//queries[i] = [li, ri]
//0 <= li <= ri < m
