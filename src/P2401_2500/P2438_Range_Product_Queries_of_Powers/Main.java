package P2401_2500.P2438_Range_Product_Queries_of_Powers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int n1 = 15;
        int[][] q1 = {{0, 1}, {2, 2}, {0, 3}};
        System.out.println(Arrays.toString(solution.productQueries(n1, q1))); // [2, 4, 64]

        int n2 = 2;
        int[][] q2 = {{0, 0}};
        System.out.println(Arrays.toString(solution.productQueries(n2, q2))); // [2]
    }

    static final long MOD = 1_000_000_007;

    public static int[] productQueries(int n, int[][] queries) {
        // build powers array
        List<Long> powers = new ArrayList<>();

        for (int i = 0; i < 31; i++) { // n <= 1e9 < 2^31
            if ((n & (1 << i)) != 0) {
                powers.add(1L << i);
            }
        }

        // precompute prefix products mod MOD
        int len = powers.size();
        long[] prefix = new long[len];
        prefix[0] = powers.get(0) % MOD;

        for (int i = 1; i < len; i++) {
            prefix[i] = (prefix[i - 1] * powers.get(i)) % MOD;
        }

        // answer queries
        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0], r = queries[i][1];

            if (l == 0) {
                result[i] = (int) prefix[r];
            } else {
                long inv = modPow(prefix[l - 1], MOD - 2, MOD);
                result[i] = (int) ((prefix[r] * inv) % MOD);
            }
        }

        return result;
    }

    // fast exponentiation
    private static long modPow(long base, long exp, long mod) {
        long res = 1;

        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }

        return res;
    }

}

//Complexity:
// time - O(log n + Q log MOD)      log MOD ~ 30
// space - O(log n + Q)


//Given a positive integer n, there exists a 0-indexed array called powers, composed of the minimum number of
// powers of 2 that sum to n. The array is sorted in non-decreasing order, and there is only one way to form the array.
//You are also given a 0-indexed 2D integer array queries, where queries[i] = [lefti, righti]. Each queries[i]
// represents a query where you have to find the product of all powers[j] with lefti <= j <= righti.
//Return an array answers, equal in length to queries, where answers[i] is the answer to the ith query. Since the
// answer to the ith query may be too large, each answers[i] should be returned modulo 109 + 7.

//Example 1:
//Input: n = 15, queries = [[0,1],[2,2],[0,3]]
//Output: [2,4,64]
//Explanation:
//For n = 15, powers = [1,2,4,8]. It can be shown that powers cannot be a smaller size.
//Answer to 1st query: powers[0] * powers[1] = 1 * 2 = 2.
//Answer to 2nd query: powers[2] = 4.
//Answer to 3rd query: powers[0] * powers[1] * powers[2] * powers[3] = 1 * 2 * 4 * 8 = 64.
//Each answer modulo 109 + 7 yields the same answer, so [2,4,64] is returned.

//Example 2:
//Input: n = 2, queries = [[0,0]]
//Output: [2]
//Explanation:
//For n = 2, powers = [2].
//The answer to the only query is powers[0] = 2. The answer modulo 109 + 7 is the same, so [2] is returned.

//Constraints:
//1 <= n <= 109
//1 <= queries.length <= 105
//0 <= starti <= endi < powers.length
