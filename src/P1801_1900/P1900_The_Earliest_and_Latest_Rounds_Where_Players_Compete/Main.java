package P1801_1900.P1900_The_Earliest_and_Latest_Rounds_Where_Players_Compete;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] res1 = solution.earliestAndLatest(11, 2, 4);
        System.out.println(Arrays.toString(res1)); // Output: [3, 4]

        int[] res2 = solution.earliestAndLatest(5, 1, 5);
        System.out.println(Arrays.toString(res2)); // Output: [1, 1]
    }

    public int[] earliestAndLatest(int n, int firstPlayer, int secondPlayer) {
        // memoization array: 3D array of Pair<Integer, Integer>
        @SuppressWarnings("unchecked")
        Pair<Integer, Integer>[][][] memo = new Pair[n + 1][n + 1][n + 1];

        int a = firstPlayer;
        int b = secondPlayer;

        Pair<Integer, Integer> result = solve(Math.min(a, n - b + 1), Math.max(a, n - b + 1), n, memo);
        return new int[]{result.first, result.second};
    }

    private Pair<Integer, Integer> solve(int l, int r, int k, Pair<Integer, Integer>[][][] memo) {
        if (l == r) return new Pair<>(1, 1);

        if (l > r) {
            int temp = l;
            l = r;
            r = temp;
        }

        if (memo[l][r][k] != null) return memo[l][r][k];

        int earliest = Integer.MAX_VALUE;
        int latest = Integer.MIN_VALUE;

        for (int i = 1; i <= l; i++) {
            for (int j = l - i + 1; j <= r - i; j++) {
                if (i + j > (k + 1) / 2 || i + j < l + r - k / 2) continue;

                Pair<Integer, Integer> next = solve(i, j, (k + 1) / 2, memo);
                earliest = Math.min(earliest, next.first + 1);
                latest = Math.max(latest, next.second + 1);
            }
        }

        memo[l][r][k] = new Pair<>(earliest, latest);
        return memo[l][r][k];
    }

    private static class Pair<F, S> {
        public F first;
        public S second;

        public Pair(F f, S s) {
            first = f;
            second = s;
        }
    }

}

//Complexity:
// time - O(n^5)
// space - O(n^3)


//There is a tournament where n players are participating. The players are standing in a single row and are
// numbered from 1 to n based on their initial standing position (player 1 is the first player in the row,
// player 2 is the second player in the row, etc.).
//The tournament consists of multiple rounds (starting from round number 1). In each round, the ith player from
// the front of the row competes against the ith player from the end of the row, and the winner advances to the
// next round. When the number of players is odd for the current round, the player in the middle automatically
// advances to the next round.
//For example, if the row consists of players 1, 2, 4, 6, 7
//Player 1 competes against player 7.
//Player 2 competes against player 6.
//Player 4 automatically advances to the next round.
//After each round is over, the winners are lined back up in the row based on the original ordering assigned to
// them initially (ascending order).
//The players numbered firstPlayer and secondPlayer are the best in the tournament. They can win against any
// other player before they compete against each other. If any two other players compete against each other,
// either of them might win, and thus you may choose the outcome of this round.
//Given the integers n, firstPlayer, and secondPlayer, return an integer array containing two values, the
// earliest possible round number and the latest possible round number in which these two players will
// compete against each other, respectively.

//Example 1:
//Input: n = 11, firstPlayer = 2, secondPlayer = 4
//Output: [3,4]
//Explanation:
//One possible scenario which leads to the earliest round number:
//First round: 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11
//Second round: 2, 3, 4, 5, 6, 11
//Third round: 2, 3, 4
//One possible scenario which leads to the latest round number:
//First round: 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11
//Second round: 1, 2, 3, 4, 5, 6
//Third round: 1, 2, 4
//Fourth round: 2, 4

//Example 2:
//Input: n = 5, firstPlayer = 1, secondPlayer = 5
//Output: [1,1]
//Explanation: The players numbered 1 and 5 compete in the first round.
//There is no way to make them compete in any other round.

//Constraints:
//2 <= n <= 28
//1 <= firstPlayer < secondPlayer <= n
