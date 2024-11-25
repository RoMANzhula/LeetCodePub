package P701_800.P773_Sliding_Puzzle;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[][] board1 = {{1, 2, 3}, {4, 0, 5}};
        System.out.println(solution.slidingPuzzle(board1)); // Output: 1

        int[][] board2 = {{1, 2, 3}, {5, 4, 0}};
        System.out.println(solution.slidingPuzzle(board2)); // Output: -1

        int[][] board3 = {{4, 1, 2}, {5, 0, 3}};
        System.out.println(solution.slidingPuzzle(board3)); // Output: 5
    }

    public int slidingPuzzle(int[][] board) {
        // Convert board to string representation
        StringBuilder start = new StringBuilder();
        for (int[] row : board) {
            for (int num : row) {
                start.append(num);
            }
        }

        String target = "123450";

        // Possible moves based on the position of 0
        int[][] directions = {
                {1, 3},     // 0 can move to 1, 3
                {0, 2, 4},  // 1 can move to 0, 2, 4
                {1, 5},     // 2 can move to 1, 5
                {0, 4},     // 3 can move to 0, 4
                {1, 3, 5},  // 4 can move to 1, 3, 5
                {2, 4}      // 5 can move to 2, 4
        };

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(start.toString());
        visited.add(start.toString());

        int moves = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                if (current.equals(target)) {
                    return moves;
                }

                // Find the position of '0'
                int zeroIndex = current.indexOf('0');

                // Try all possible moves
                for (int dir : directions[zeroIndex]) {
                    String next = swap(current, zeroIndex, dir);
                    if (!visited.contains(next)) {
                        queue.offer(next);
                        visited.add(next);
                    }
                }
            }
            moves++;
        }

        return -1; // If no solution is found
    }

    private String swap(String str, int i, int j) {
        char[] chars = str.toCharArray();
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        return new String(chars);
    }

}

//Explanation of the Code:
//Initial Setup:
//The start string represents the initial board state.
//The target string is the solved state.
//The directions array defines possible moves for each index in the 1D representation of the board.
//BFS:
//Use a queue to explore states level by level (minimizing moves).
//Track visited states in a Set to avoid cycles.
//Swapping:
//The swap helper function swaps two characters in the string to simulate a tile move.
//Output:
//If the target state is reached, return the number of moves.
//If the queue is exhausted, return -1.


//On an 2 x 3 board, there are five tiles labeled from 1 to 5, and an empty square represented by 0. A move
// consists of choosing 0 and a 4-directionally adjacent number and swapping it.
//The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].
//Given the puzzle board board, return the least number of moves required so that the state of
// the board is solved. If it is impossible for the state of the board to be solved, return -1.
//
//Example 1:
//Input: board = [[1,2,3],[4,0,5]]
//Output: 1
//Explanation: Swap the 0 and the 5 in one move.

//Example 2:
//Input: board = [[1,2,3],[5,4,0]]
//Output: -1
//Explanation: No number of moves will make the board solved.

//Example 3:
//Input: board = [[4,1,2],[5,0,3]]
//Output: 5
//Explanation: 5 is the smallest number of moves that solves the board.
//An example path:
//After move 0: [[4,1,2],[5,0,3]]
//After move 1: [[4,1,2],[0,5,3]]
//After move 2: [[0,1,2],[4,5,3]]
//After move 3: [[1,0,2],[4,5,3]]
//After move 4: [[1,2,0],[4,5,3]]
//After move 5: [[1,2,3],[4,5,0]]
//
//Constraints:
//board.length == 2
//board[i].length == 3
//0 <= board[i][j] <= 5
//Each value board[i][j] is unique.
