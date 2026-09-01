package P3501_3600.P3568_Minimum_Moves_to_Clean_the_Classroom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String[] classroom1 = {
                "S.",
                "XL"
        };
        int energy1 = 2;
        System.out.println(solution.minMoves(classroom1, energy1)); // 2


        String[] classroom2 = {
                "LS",
                "RL"
        };
        int energy2 = 4;
        System.out.println(solution.minMoves(classroom2, energy2)); // 3

        String[] classroom3 = {
                "L.S",
                "RXL"
        };
        int energy3 = 3;
        System.out.println(solution.minMoves(classroom3, energy3)); // -1
    }

    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int startR = -1;
        int startC = -1;

        List<int[]> litter = new ArrayList<>();

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                char ch = classroom[r].charAt(c);

                if (ch == 'S') {
                    startR = r;
                    startC = c;
                } else if (ch == 'L') {
                    litter.add(new int[]{r, c});
                }
            }
        }

        int litterCount = litter.size();

        // no litter to collect
        if (litterCount == 0) {
            return 0;
        }

        int allCollected = (1 << litterCount) - 1;

        int[][] litterId = new int[m][n];

        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }

        for (int i = 0; i < litterCount; i++) {
            int r = litter.get(i)[0];
            int c = litter.get(i)[1];
            litterId[r][c] = i;
        }

        int totalStates = (1 << litterCount) * m * n;
        short[] bestEnergy = new short[totalStates];

        Arrays.fill(bestEnergy, (short) -1);

        int maxStates = totalStates * (energy + 1);

        int[] queue = new int[maxStates];
        int head = 0;
        int tail = 0;

        // initial state
        int startMask = 0;

        if (litterId[startR][startC] != -1) {
            startMask |= 1 << litterId[startR][startC];
        }

        int startIndex = ((startMask * m + startR) * n + startC);
        bestEnergy[startIndex] = (short) energy;

        queue[tail++] = encode(startR, startC, startMask, energy, m, n, energy);

        int moves = 0;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (head < tail) {

            int levelEnd = tail;

            while (head < levelEnd) {

                int state = queue[head++];

                // decode state
                int currentEnergy = state % (energy + 1);
                state /= (energy + 1);

                int currentC = state % n;
                state /= n;

                int currentR = state % m;
                int mask = state / m;

                // all litter collected
                if (mask == allCollected) {
                    return moves;
                }

                if (currentEnergy == 0) {
                    continue;
                }

                for (int d = 0; d < 4; d++) {

                    int nr = currentR + dr[d];
                    int nc = currentC + dc[d];

                    // outside grid
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                        continue;
                    }

                    // obstacle
                    if (classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }

                    // moving costs 1 energy
                    int newEnergy = currentEnergy - 1;

                    if (classroom[nr].charAt(nc) == 'R') {
                        newEnergy = energy;
                    }

                    // collect litter if this is a litter cell
                    int newMask = mask;

                    int litterIndex = litterId[nr][nc];

                    if (litterIndex != -1) {
                        newMask |= 1 << litterIndex;
                    }

                    int index = ((newMask * m + nr) * n + nc);

                    if (bestEnergy[index] >= newEnergy) {
                        continue;
                    }

                    bestEnergy[index] = (short) newEnergy;

                    queue[tail++] =
                            encode(nr, nc, newMask, newEnergy, m, n, energy);
                }
            }

            moves++;
        }

        return -1;
    }

    private int encode(
            int r,
            int c,
            int mask,
            int energyLeft,
            int m,
            int n,
            int maxEnergy) {

        int value = mask;

        value = value * m + r;
        value = value * n + c;
        value = value * (maxEnergy + 1) + energyLeft;

        return value;
    }

}



//You are given an m x n grid classroom where a student volunteer is tasked with cleaning up litter scattered
// around the room. Each cell in the grid is one of the following:
//'S': Starting position of the student
//'L': Litter that must be collected (once collected, the cell becomes empty)
//'R': Reset area that restores the student's energy to full capacity, regardless of their current energy
// level (can be used multiple times)
//'X': Obstacle the student cannot pass through
//'.': Empty space
//You are also given an integer energy, representing the student's maximum energy capacity. The student starts with
// this energy from the starting position 'S'.
//Each move to an adjacent cell (up, down, left, or right) costs 1 unit of energy. If the energy reaches 0, the
// student can only continue if they are on a reset area 'R', which resets the energy to its maximum capacity energy.
//Return the minimum number of moves required to collect all litter items, or -1 if it's impossible.

//Example 1:
//Input: classroom = ["S.", "XL"], energy = 2
//Output: 2
//Explanation:
//The student starts at cell (0, 0) with 2 units of energy.
//Since cell (1, 0) contains an obstacle 'X', the student cannot move directly downward.
//A valid sequence of moves to collect all litter is as follows:
//Move 1: From (0, 0) → (0, 1) with 1 unit of energy and 1 unit remaining.
//Move 2: From (0, 1) → (1, 1) to collect the litter 'L'.
//The student collects all the litter using 2 moves. Thus, the output is 2.

//Example 2:
//Input: classroom = ["LS", "RL"], energy = 4
//Output: 3
//Explanation:
//The student starts at cell (0, 1) with 4 units of energy.
//A valid sequence of moves to collect all litter is as follows:
//Move 1: From (0, 1) → (0, 0) to collect the first litter 'L' with 1 unit of energy used and 3 units remaining.
//Move 2: From (0, 0) → (1, 0) to 'R' to reset and restore energy back to 4.
//Move 3: From (1, 0) → (1, 1) to collect the second litter 'L'.
//The student collects all the litter using 3 moves. Thus, the output is 3.

//Example 3:
//Input: classroom = ["L.S", "RXL"], energy = 3
//Output: -1
//Explanation:
//No valid path collects all 'L'.

//Constraints:
//1 <= m == classroom.length <= 20
//1 <= n == classroom[i].length <= 20
//classroom[i][j] is one of 'S', 'L', 'R', 'X', or '.'
//1 <= energy <= 50
//There is exactly one 'S' in the grid.
//There are at most 10 'L' cells in the grid.
