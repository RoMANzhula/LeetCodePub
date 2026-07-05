package P1301_1400.P1301_Number_of_Paths_with_Max_Score;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        java.util.List<String> board1 = java.util.Arrays.asList(
                "E23",
                "2X2",
                "12S"
        );

        java.util.List<String> board2 = java.util.Arrays.asList(
                "E12",
                "1X1",
                "21S"
        );

        java.util.List<String> board3 = java.util.Arrays.asList(
                "E11",
                "XXX",
                "11S"
        );

        System.out.println(java.util.Arrays.toString(solution.pathsWithMaxScore(board1))); // [7, 1]
        System.out.println(java.util.Arrays.toString(solution.pathsWithMaxScore(board2))); // [4, 2]
        System.out.println(java.util.Arrays.toString(solution.pathsWithMaxScore(board3))); // [0, 0]
    }

    private static final int MOD = 1_000_000_007;

    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();

        int[][] score = new int[n][n];
        int[][] ways = new int[n][n];

        for (int i = 0; i < n; i++) {
            java.util.Arrays.fill(score[i], -1);
        }

        score[n - 1][n - 1] = 0;
        ways[n - 1][n - 1] = 1;

        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {

                char c = board.get(i).charAt(j);

                if (c == 'X')
                    continue;

                if (i == n - 1 && j == n - 1)
                    continue;

                int bestScore = -1;
                long count = 0;

                // down
                if (i + 1 < n && score[i + 1][j] != -1) {
                    if (score[i + 1][j] > bestScore) {
                        bestScore = score[i + 1][j];
                        count = ways[i + 1][j];
                    } else if (score[i + 1][j] == bestScore) {
                        count += ways[i + 1][j];
                    }
                }

                // rright
                if (j + 1 < n && score[i][j + 1] != -1) {
                    if (score[i][j + 1] > bestScore) {
                        bestScore = score[i][j + 1];
                        count = ways[i][j + 1];
                    } else if (score[i][j + 1] == bestScore) {
                        count += ways[i][j + 1];
                    }
                }

                // down-right
                if (i + 1 < n && j + 1 < n && score[i + 1][j + 1] != -1) {
                    if (score[i + 1][j + 1] > bestScore) {
                        bestScore = score[i + 1][j + 1];
                        count = ways[i + 1][j + 1];
                    } else if (score[i + 1][j + 1] == bestScore) {
                        count += ways[i + 1][j + 1];
                    }
                }

                if (bestScore == -1)
                    continue;

                int value = 0;
                if (c >= '1' && c <= '9')
                    value = c - '0';

                score[i][j] = bestScore + value;
                ways[i][j] = (int) (count % MOD);
            }
        }

        if (ways[0][0] == 0)
            return new int[]{0, 0};

        return new int[]{score[0][0], ways[0][0]};
    }

}

//Complexity:
// time and space - O(n^2)


//You are given a square board of characters. You can move on the board starting at the bottom right square marked
// with the character 'S'.
//You need to reach the top left square marked with the character 'E'. The rest of the squares are labeled either
// with a numeric character 1, 2, ..., 9 or with an obstacle 'X'. In one move you can go up, left or
// up-left (diagonally) only if there is no obstacle there.
//Return a list of two integers: the first integer is the maximum sum of numeric characters you can collect, and the
// second is the number of such paths that you can take to get that maximum sum, taken modulo 10^9 + 7.
//In case there is no path, return [0, 0].

//Example 1:
//Input: board = ["E23","2X2","12S"]
//Output: [7,1]

//Example 2:
//Input: board = ["E12","1X1","21S"]
//Output: [4,2]

//Example 3:
//Input: board = ["E11","XXX","11S"]
//Output: [0,0]

//Constraints:
//2 <= board.length == board[i].length <= 100
