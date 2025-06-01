package P2901_3000.P2929_Distribute_Candies_Among_Children_II;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.distributeCandies(5, 2)); // Output: 3
        System.out.println(solution.distributeCandies(3, 3)); // Output: 10
    }

    public long distributeCandies(int n, int limit) {
        long total = comb(n + 2, 2);

        total -= 3 * combNonNegative(n - (limit + 1), 2);
        total += 3 * combNonNegative(n - 2 * (limit + 1), 2);
        total -= combNonNegative(n - 3 * (limit + 1), 2);

        return total;
    }

    // calculates C(n, k)
    private static long comb(int n, int k) {
        if (n < k) return 0;

        return (long) n * (n - 1) / 2;
    }

    //calculates number of non-negative integer solutions (a + b + c = n) which is C(n+2, 2)
    private static long combNonNegative(int n, int k) {
        if (n < 0) return 0;

        return comb(n + k, k);
    }

}

//Complexity:
// time and space - O(1)


//You are given two positive integers n and limit.
//Return the total number of ways to distribute n candies among 3 children such that no child gets more than
// limit candies.

//Example 1:
//Input: n = 5, limit = 2
//Output: 3
//Explanation: There are 3 ways to distribute 5 candies such that no child gets more
// than 2 candies: (1, 2, 2), (2, 1, 2) and (2, 2, 1).

//Example 2:
//Input: n = 3, limit = 3
//Output: 10
//Explanation: There are 10 ways to distribute 3 candies such that no child gets more than 3 candies:
// (0, 0, 3), (0, 1, 2), (0, 2, 1), (0, 3, 0), (1, 0, 2), (1, 1, 1), (1, 2, 0), (2, 0, 1), (2, 1, 0) and (3, 0, 0).

//Constraints:
//1 <= n <= 106
//1 <= limit <= 106
