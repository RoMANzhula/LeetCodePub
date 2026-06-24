package P3601_3700.P3700_Number_of_ZigZag_Arrays_II;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.zigZagArrays(3, 4, 5)); // 2
        System.out.println(solution.zigZagArrays(3, 1, 3)); // 10
    }

    private final long MOD = 1_000_000_007L;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        int size = 2 * m;

        long[][] trans = new long[size][size];

        for (int y = 0; y < m; y++) {

            for (int z = 0; z < y; z++) {
                trans[m + z][y] = 1;
            }

            for (int z = y + 1; z < m; z++) {
                trans[z][m + y] = 1;
            }
        }

        long[] base = new long[size];

        // length = 2
        for (int v = 0; v < m; v++) {
            base[v] = v;                 // U[v]
            base[m + v] = m - 1 - v;    // D[v]
        }

        long[][] power = matrixPower(trans, n - 2);

        long[] result = multiply(power, base);

        long answer = 0;
        for (long x : result) {
            answer = (answer + x) % MOD;
        }

        return (int) answer;
    }

    private long[] multiply(long[][] mat, long[] vec) {
        int n = mat.length;
        long[] res = new long[n];

        for (int i = 0; i < n; i++) {
            long sum = 0;

            for (int j = 0; j < n; j++) {
                sum = (sum + mat[i][j] * vec[j]) % MOD;
            }

            res[i] = sum;
        }

        return res;
    }

    private long[][] matrixPower(long[][] mat, long exp) {
        int n = mat.length;

        long[][] result = new long[n][n];
        for (int i = 0; i < n; i++) {
            result[i][i] = 1;
        }

        long[][] cur = mat;

        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = multiply(result, cur);
            }

            cur = multiply(cur, cur);
            exp >>= 1;
        }

        return result;
    }

    private long[][] multiply(long[][] a, long[][] b) {
        int n = a.length;
        long[][] res = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {

                if (a[i][k] == 0) {
                    continue;
                }

                long aik = a[i][k];

                for (int j = 0; j < n; j++) {
                    if (b[k][j] == 0) {
                        continue;
                    }

                    res[i][j] = (res[i][j] + aik * b[k][j]) % MOD;
                }
            }
        }

        return res;
    }

}


//You are given three integers n, l, and r.
//A ZigZag array of length n is defined as follows:
//Each element lies in the range [l, r].
//No two adjacent elements are equal.
//No three consecutive elements form a strictly increasing or strictly decreasing sequence.
//Return the total number of valid ZigZag arrays.
//Since the answer may be large, return it modulo 109 + 7.
//A sequence is said to be strictly increasing if each element is strictly greater than its previous one (if exists).
//A sequence is said to be strictly decreasing if each element is strictly smaller than its previous one (if exists).

//Example 1:
//Input: n = 3, l = 4, r = 5
//Output: 2
//Explanation:
//There are only 2 valid ZigZag arrays of length n = 3 using values in the range [4, 5]:
//[4, 5, 4]
//[5, 4, 5]

//Example 2:
//Input: n = 3, l = 1, r = 3
//Output: 10
//Explanation:
//There are 10 valid ZigZag arrays of length n = 3 using values in the range [1, 3]:
//[1, 2, 1], [1, 3, 1], [1, 3, 2]
//[2, 1, 2], [2, 1, 3], [2, 3, 1], [2, 3, 2]
//[3, 1, 2], [3, 1, 3], [3, 2, 3]
//All arrays meet the ZigZag conditions.

//Constraints:
//3 <= n <= 109
//1 <= l < r <= 75​​​​​​​
