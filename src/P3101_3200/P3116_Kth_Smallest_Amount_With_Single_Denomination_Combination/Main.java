package P3101_3200.P3116_Kth_Smallest_Amount_With_Single_Denomination_Combination;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] coins1 = {3, 6, 9};
        int k1 = 3;

        System.out.println(solution.findKthSmallest(coins1, k1)); // 9


        int[] coins2 = {5, 2};
        int k2 = 7;

        System.out.println(solution.findKthSmallest(coins2, k2)); // 12
    }

    public long findKthSmallest(int[] coins, int k) {
        long left = 1;

        // the answer cannot be greater than min(coins) * k
        long right = Long.MAX_VALUE;

        for (int coin : coins) {
            right = Math.min(right, (long) coin * k);
        }

        while (left < right) {
            long mid = left + (right - left) / 2;

            if (count(mid, coins) >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private long count(long x, int[] coins) {
        int n = coins.length;
        long total = 0;

        // inclusion-exclusion
        for (int mask = 1; mask < (1 << n); mask++) {

            long lcm = 1;
            int bits = 0;
            boolean valid = true;

            for (int i = 0; i < n; i++) {

                if ((mask & (1 << i)) != 0) {
                    bits++;

                    lcm = lcm(lcm, coins[i]);

                    // this subset has no multiples <= x
                    if (lcm > x) {
                        valid = false;
                        break;
                    }
                }
            }

            if (!valid) {
                continue;
            }

            long multiples = x / lcm;

            if ((bits & 1) == 1) {
                // odd number of coins -> add
                total += multiples;
            } else {
                // even number of coins -> subtract
                total -= multiples;
            }
        }

        return total;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }

    private long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

}

//Complexity:
// time - O(2^n * n * log(min(coins) * k))
// space - O(1)


//You are given an integer array coins representing coins of different denominations and an integer k.
//You have an infinite number of coins of each denomination. However, you are not allowed to combine coins of
// different denominations.
//Return the kth smallest amount that can be made using these coins.

//Example 1:
//Input: coins = [3,6,9], k = 3
//Output: 9
//Explanation: The given coins can make the following amounts:
//Coin 3 produces multiples of 3: 3, 6, 9, 12, 15, etc.
//Coin 6 produces multiples of 6: 6, 12, 18, 24, etc.
//Coin 9 produces multiples of 9: 9, 18, 27, 36, etc.
//All of the coins combined produce: 3, 6, 9, 12, 15, etc.

//Example 2:
//Input: coins = [5,2], k = 7
//Output: 12
//Explanation: The given coins can make the following amounts:
//Coin 5 produces multiples of 5: 5, 10, 15, 20, etc.
//Coin 2 produces multiples of 2: 2, 4, 6, 8, 10, 12, etc.
//All of the coins combined produce: 2, 4, 5, 6, 8, 10, 12, 14, 15, etc.

//Constraints:
//1 <= coins.length <= 15
//1 <= coins[i] <= 25
//1 <= k <= 2 * 109
//coins contains pairwise distinct integers.
