package P3501_3600.P3539_Find_Sum_of_Array_Product_of_Magical_Sequences;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.magicalSum(5, 5, new int[]{1, 10, 100, 10000, 1000000})); // 991600007
        System.out.println(solution.magicalSum(2, 2, new int[]{5, 4, 3, 2, 1})); // 170
        System.out.println(solution.magicalSum(1, 1, new int[]{28})); // 28
    }

    static final int MOD = 1_000_000_007;

    public int magicalSum(int m, int k, int[] nums) {
        int n = nums.length;

        // Precompute factorials and inv factorials up to m
        long[] fact = new long[m + 1];
        long[] invFact = new long[m + 1];
        fact[0] = 1;
        for (int i = 1; i <= m; i++) fact[i] = fact[i - 1] * i % MOD;
        invFact[m] = modInverse(fact[m]);
        for (int i = m - 1; i >= 0; i--) invFact[i] = invFact[i + 1] * (i + 1) % MOD;

        // Precompute pow(nums[i], c) for c = 0..m
        long[][] pow = new long[n][m + 1];
        for (int i = 0; i < n; i++) {
            pow[i][0] = 1;
            long base = nums[i] % MOD;
            for (int c = 1; c <= m; c++) {
                pow[i][c] = (pow[i][c - 1] * base) % MOD;
            }
        }

        // DP rolling arrays: dp[used][carry][ones] = sum of partial contributions (product of nums^c * invFact[c])
        int maxUsed = m;
        int maxCarry = m; // carry can't exceed m
        int maxOnes = Math.min(k, m); // we only need to track ones up to k (prune)
        long[][][] curr = new long[maxUsed + 1][maxCarry + 1][maxOnes + 1];
        long[][][] next = new long[maxUsed + 1][maxCarry + 1][maxOnes + 1];

        curr[0][0][0] = 1;

        for (int pos = 0; pos < n; pos++) {
            // zero next
            for (int u = 0; u <= maxUsed; u++)
                for (int ca = 0; ca <= maxCarry; ca++)
                    Arrays.fill(next[u][ca], 0L);

            for (int used = 0; used <= maxUsed; used++) {
                for (int carry = 0; carry <= maxCarry; carry++) {
                    for (int ones = 0; ones <= maxOnes; ones++) {
                        long val = curr[used][carry][ones];
                        if (val == 0) continue;

                        int remain = maxUsed - used;
                        // choose c occurrences for this index
                        for (int c = 0; c <= remain; c++) {
                            int newUsed = used + c;
                            int sum = c + carry;
                            int bit = sum & 1;
                            int newOnes = ones + bit;
                            if (newOnes > k) break; // pruning: can't reduce ones later
                            int newCarry = sum >>> 1;
                            if (newCarry > maxCarry) continue; // shouldn't happen, but safe

                            long mult = (pow[pos][c] * invFact[c]) % MOD;
                            long add = (val * mult) % MOD;

                            next[newUsed][newCarry][newOnes] += add;
                            if (next[newUsed][newCarry][newOnes] >= MOD) next[newUsed][newCarry][newOnes] -= MOD;
                        }
                    }
                }
            }

            // swap
            long[][][] tmp = curr;
            curr = next;
            next = tmp;
        }

        // Now curr[m][carry][ones] holds partial sums (without multiplying by m!)
        long ans = 0;
        for (int carry = 0; carry <= maxCarry; carry++) {
            int pop = Integer.bitCount(carry);
            for (int ones = 0; ones <= maxOnes; ones++) {
                long val = curr[maxUsed][carry][ones];
                if (val == 0) continue;
                if (ones + pop == k) {
                    ans += val;
                    if (ans >= MOD) ans -= MOD;
                }
            }
        }

        // multiply by m! to account for ordered sequences (multinomial factor)
        ans = (ans * fact[m]) % MOD;
        return (int) ans;
    }

    // modular inverse using Fermat (MOD prime)
    private long modInverse(long x) {
        return modPow(x, MOD - 2);
    }

    private long modPow(long a, long e) {
        long res = 1;
        a %= MOD;
        while (e > 0) {
            if ((e & 1) == 1) res = (res * a) % MOD;
            a = (a * a) % MOD;
            e >>= 1;
        }
        return res;
    }

}

//Complexity:
// time - O(n * m^3 * k)
// space - O(m^2 * k)


//You are given two integers, m and k, and an integer array nums.
//A sequence of integers seq is called magical if:
//seq has a size of m.
//0 <= seq[i] < nums.length
//The binary representation of 2seq[0] + 2seq[1] + ... + 2seq[m - 1] has k set bits.
//The array product of this sequence is defined as prod(seq) = (nums[seq[0]] * nums[seq[1]] * ... * nums[seq[m - 1]]).
//Return the sum of the array products for all valid magical sequences.
//Since the answer may be large, return it modulo 109 + 7.
//A set bit refers to a bit in the binary representation of a number that has a value of 1.

//Example 1:
//Input: m = 5, k = 5, nums = [1,10,100,10000,1000000]
//Output: 991600007
//Explanation:
//All permutations of [0, 1, 2, 3, 4] are magical sequences, each with an array product of 1013.

//Example 2:
//Input: m = 2, k = 2, nums = [5,4,3,2,1]
//Output: 170
//Explanation:
//The magical sequences are [0, 1], [0, 2], [0, 3], [0, 4], [1, 0], [1, 2], [1, 3], [1, 4], [2, 0],
// [2, 1], [2, 3], [2, 4], [3, 0], [3, 1], [3, 2], [3, 4], [4, 0], [4, 1], [4, 2], and [4, 3].

//Example 3:
//Input: m = 1, k = 1, nums = [28]
//Output: 28
//Explanation:
//The only magical sequence is [0].

//Constraints:
//1 <= k <= m <= 30
//1 <= nums.length <= 50
//1 <= nums[i] <= 108
