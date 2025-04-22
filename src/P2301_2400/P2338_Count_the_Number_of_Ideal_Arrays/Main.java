package P2301_2400.P2338_Count_the_Number_of_Ideal_Arrays;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.idealArrays(2, 5)); // Output: 10
        System.out.println(solution.idealArrays(5, 3)); // Output: 11
    }

    int maxValue, sequenceLength;
    final int MOD = (int) 1e9 + 7;
    int[][] f;
    int[][] c;

    public int idealArrays(int n, int maxValue) {
        this.maxValue = maxValue;
        this.sequenceLength = n;

        // memoization table
        f = new int[maxValue + 1][16];
        for (int i = 0; i <= maxValue; i++) {
            Arrays.fill(f[i], -1);
        }

        // precompute combinations c[n][k] = nCk
        c = new int[n][16];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i && j < 16; j++) {
                if (j == 0) {
                    c[i][j] = 1;
                } else {
                    c[i][j] = (c[i - 1][j] + c[i - 1][j - 1]) % MOD;
                }
            }
        }

        int ans = 0;
        for (int i = 1; i <= maxValue; i++) {
            ans = (ans + dfs(i, 1)) % MOD;
        }

        return ans;
    }

    // DFS: sequences ending with `i`, having `cnt` unique elements
    private int dfs(int i, int cnt) {
        if (f[i][cnt] != -1) return f[i][cnt];

        // number of sequences of length `sequenceLength` with `cnt` parts
        int res = c[sequenceLength - 1][cnt - 1];

        if (cnt < sequenceLength) {
            for (int k = 2; i * k <= maxValue; k++) {
                res = (res + dfs(i * k, cnt + 1)) % MOD;
            }
        }

        f[i][cnt] = res;
        return res;
    }

}

//Explanation:
//We are counting ideal arrays of length n where:
//Every element is between 1 and maxValue.
//Every next element is divisible by the previous one.
//To solve this:
//  We fix a starting value i from 1 to maxValue.
//  Using DFS with memoization, we build valid sequences by multiplying i with 2, 3, 4… up to maxValue.
//  For each valid sequence of length cnt, we count how many ways we can place it in an array of length n using
//  combinations: C(n-1, cnt-1).
//Complexity:
// time - O(M * log M * D), where D = 15 (max depth)
// space - O(N * D + M * D) = O(10^5) – totally acceptable


//You are given two integers n and maxValue, which are used to describe an ideal array.
//A 0-indexed integer array arr of length n is considered ideal if the following conditions hold:
//Every arr[i] is a value from 1 to maxValue, for 0 <= i < n.
//Every arr[i] is divisible by arr[i - 1], for 0 < i < n.
//Return the number of distinct ideal arrays of length n. Since the answer may be very large, return it modulo 109 + 7.

//Example 1:
//Input: n = 2, maxValue = 5
//Output: 10
//Explanation: The following are the possible ideal arrays:
//- Arrays starting with the value 1 (5 arrays): [1,1], [1,2], [1,3], [1,4], [1,5]
//- Arrays starting with the value 2 (2 arrays): [2,2], [2,4]
//- Arrays starting with the value 3 (1 array): [3,3]
//- Arrays starting with the value 4 (1 array): [4,4]
//- Arrays starting with the value 5 (1 array): [5,5]
//There are a total of 5 + 2 + 1 + 1 + 1 = 10 distinct ideal arrays.

//Example 2:
//Input: n = 5, maxValue = 3
//Output: 11
//Explanation: The following are the possible ideal arrays:
//- Arrays starting with the value 1 (9 arrays):
//   - With no other distinct values (1 array): [1,1,1,1,1]
//   - With 2nd distinct value 2 (4 arrays): [1,1,1,1,2], [1,1,1,2,2], [1,1,2,2,2], [1,2,2,2,2]
//   - With 2nd distinct value 3 (4 arrays): [1,1,1,1,3], [1,1,1,3,3], [1,1,3,3,3], [1,3,3,3,3]
//- Arrays starting with the value 2 (1 array): [2,2,2,2,2]
//- Arrays starting with the value 3 (1 array): [3,3,3,3,3]
//There are a total of 9 + 1 + 1 = 11 distinct ideal arrays.

//Constraints:
//2 <= n <= 104
//1 <= maxValue <= 104