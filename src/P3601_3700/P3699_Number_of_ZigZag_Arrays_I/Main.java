package P3601_3700.P3699_Number_of_ZigZag_Arrays_I;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.zigZagArrays(3, 4, 5)); // 2
        System.out.println(solution.zigZagArrays(3, 1, 3)); // 10
    }

    private final long MOD = 1_000_000_007L;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;

        long[] up = new long[m + 1];
        long[] down = new long[m + 1];

        // length = 2
        for (int v = 1; v <= m; v++) {
            up[v] = v - 1;
            down[v] = m - v;
        }

        // build lengths 3..n
        for (int len = 3; len <= n; len++) {

            long[] newUp = new long[m + 1];
            long[] newDown = new long[m + 1];

            long prefix = 0;

            // newUp[v] = sum(down[u]) for u < v
            for (int v = 1; v <= m; v++) {
                newUp[v] = prefix;
                prefix = (prefix + down[v]) % MOD;
            }

            long suffix = 0;

            // newDown[v] = sum(up[u]) for u > v
            for (int v = m; v >= 1; v--) {
                newDown[v] = suffix;
                suffix = (suffix + up[v]) % MOD;
            }

            up = newUp;
            down = newDown;
        }

        long answer = 0;

        if (n == 2) {
            for (int v = 1; v <= m; v++) {
                answer = (answer + up[v] + down[v]) % MOD;
            }
        } else {
            for (int v = 1; v <= m; v++) {
                answer = (answer + up[v] + down[v]) % MOD;
            }
        }

        return (int) answer;
    }

}

//Complexity:
// time - O(n · (r - l + 1))
// space - O(r - l + 1)


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
//[5, 4, 5

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
//3 <= n <= 2000
//1 <= l < r <= 2000
