package P1301_1400.P1320_Minimum_Distance_to_Type_a_Word_Using_Two_Fingers;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.minimumDistance("CAKE"));  // 3
        System.out.println(solution.minimumDistance("HAPPY")); // 6
    }

    public int minimumDistance(String word) {
        int n = word.length();
        int[][] dp = new int[n][27];

        // initialize with large values
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 27; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        // first char: no cost, second finger unused
        dp[0][26] = 0;

        for (int i = 1; i < n; i++) {
            int curr = word.charAt(i) - 'A';
            int prev = word.charAt(i - 1) - 'A';

            for (int j = 0; j < 27; j++) {
                if (dp[i - 1][j] == Integer.MAX_VALUE) continue;

                // same finger types current char
                int cost1 = dp[i - 1][j] + getDist(prev, curr);
                dp[i][j] = Math.min(dp[i][j], cost1);

                // other finger types current char
                int cost2 = dp[i - 1][j] + getDist(j, curr);
                dp[i][prev] = Math.min(dp[i][prev], cost2);
            }
        }

        int res = Integer.MAX_VALUE;
        for (int j = 0; j < 27; j++) {
            res = Math.min(res, dp[n - 1][j]);
        }

        return res;
    }

    private int getDist(int a, int b) {
        if (a == 26) return 0; // unused finger

        int x1 = a / 6, y1 = a % 6;
        int x2 = b / 6, y2 = b % 6;

        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

}

//Complexity:
// time and space - O(n * 26)


//You have a keyboard layout as shown above in the X-Y plane, where each English uppercase letter is located at
// some coordinate.
//For example, the letter 'A' is located at coordinate (0, 0), the letter 'B' is located at
// coordinate (0, 1), the letter 'P' is located at coordinate (2, 3) and the letter 'Z' is located at coordinate (4, 1).
//Given the string word, return the minimum total distance to type such string using only two fingers.
//The distance between coordinates (x1, y1) and (x2, y2) is |x1 - x2| + |y1 - y2|.
//Note that the initial positions of your two fingers are considered free so do not count towards your total distance,
// also your two fingers do not have to start at the first letter or the first two letters.

//Example 1:
//Input: word = "CAKE"
//Output: 3
//Explanation: Using two fingers, one optimal way to type "CAKE" is:
//Finger 1 on letter 'C' -> cost = 0
//Finger 1 on letter 'A' -> cost = Distance from letter 'C' to letter 'A' = 2
//Finger 2 on letter 'K' -> cost = 0
//Finger 2 on letter 'E' -> cost = Distance from letter 'K' to letter 'E' = 1
//Total distance = 3

//Example 2:
//Input: word = "HAPPY"
//Output: 6
//Explanation: Using two fingers, one optimal way to type "HAPPY" is:
//Finger 1 on letter 'H' -> cost = 0
//Finger 1 on letter 'A' -> cost = Distance from letter 'H' to letter 'A' = 2
//Finger 2 on letter 'P' -> cost = 0
//Finger 2 on letter 'P' -> cost = Distance from letter 'P' to letter 'P' = 0
//Finger 1 on letter 'Y' -> cost = Distance from letter 'A' to letter 'Y' = 4
//Total distance = 6

//Constraints:
//2 <= word.length <= 300
//word consists of uppercase English letters.
