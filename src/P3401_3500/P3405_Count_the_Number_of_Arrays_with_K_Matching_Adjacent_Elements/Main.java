package P3401_3500.P3405_Count_the_Number_of_Arrays_with_K_Matching_Adjacent_Elements;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.countGoodArrays(3, 2, 1)); // Output: 4
        System.out.println(solution.countGoodArrays(4, 2, 2)); // Output: 6
        System.out.println(solution.countGoodArrays(5, 2, 0)); // Output: 2
    }

    static final int MOD = 1_000_000_007;

    public int countGoodArrays(int n, int m, int k) {
        long[] fact = new long[n + 1];
        long[] invFact = new long[n + 1];
        preprocessFactorials(n, fact, invFact);

        long waysToChooseEqualPairs = nCk(n - 1, k, fact, invFact);
        long waysToFillRemaining = modPow(m - 1, n - k - 1);

        // first element: m choices
        long ans = waysToChooseEqualPairs * waysToFillRemaining % MOD;
        ans = ans * m % MOD;

        return (int) ans;
    }

    private static void preprocessFactorials(int n, long[] fact, long[] invFact) {
        fact[0] = invFact[0] = 1;

        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }

        // invFact[n] = modInverse(fact[n])
        invFact[n] = modPow(fact[n], MOD - 2);

        for (int i = n - 1; i >= 1; i--) {
            invFact[i] = invFact[i + 1] * (i + 1) % MOD;
        }
    }

    private static long modPow(long x, long n) {
        long result = 1;
        x %= MOD;

        while (n > 0) {
            if ((n & 1) == 1) result = result * x % MOD;
            x = x * x % MOD;
            n >>= 1;
        }

        return result;
    }

    private static long nCk(int n, int k, long[] fact, long[] invFact) {
        if (k < 0 || k > n) return 0;

        return fact[n] * invFact[k] % MOD * invFact[n - k] % MOD;
    }

}

//Complexity:
// time - O(n + log MOD)
// space - O(n)


//You are given three integers n, m, k. A good array arr of size n is defined as follows:
//Each element in arr is in the inclusive range [1, m].
//Exactly k indices i (where 1 <= i < n) satisfy the condition arr[i - 1] == arr[i].
//Return the number of good arrays that can be formed.
//Since the answer may be very large, return it modulo 109 + 7.

//Example 1:
//Input: n = 3, m = 2, k = 1
//Output: 4
//Explanation:
//There are 4 good arrays. They are [1, 1, 2], [1, 2, 2], [2, 1, 1] and [2, 2, 1].
//Hence, the answer is 4.

//Example 2:
//Input: n = 4, m = 2, k = 2
//Output: 6
//Explanation:
//The good arrays are [1, 1, 1, 2], [1, 1, 2, 2], [1, 2, 2, 2], [2, 1, 1, 1], [2, 2, 1, 1] and [2, 2, 2, 1].
//Hence, the answer is 6.

//Example 3:
//Input: n = 5, m = 2, k = 0
//Output: 2
//Explanation:
//The good arrays are [1, 2, 1, 2, 1] and [2, 1, 2, 1, 2]. Hence, the answer is 2.

//Constraints:
//1 <= n <= 105
//1 <= m <= 105
//0 <= k <= n - 1
