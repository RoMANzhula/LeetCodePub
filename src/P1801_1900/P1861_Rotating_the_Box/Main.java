package P1801_1900.P1861_Rotating_the_Box;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        // Example 1
        char[][] box1 = { {'#', '.', '#'} };
        System.out.println("Output: " + Arrays.deepToString(solution.rotateTheBox(box1)));

        // Example 2
        char[][] box2 = {
                {'#', '.', '*', '.'},
                {'#', '#', '*', '.'}
        };
        System.out.println("Output: " + Arrays.deepToString(solution.rotateTheBox(box2)));

        // Example 3
        char[][] box3 = {
                {'#', '#', '*', '.', '*', '.'},
                {'#', '#', '#', '*', '.', '.'},
                {'#', '#', '#', '.', '#', '.'}
        };
        System.out.println("Output: " + Arrays.deepToString(solution.rotateTheBox(box3)));

    }

    public char[][] rotateTheBox(char[][] box) {
        int rows = box.length;
        int cols = box[0].length;

        // Initialize the rotated box with dimensions swapped
        char[][] rotatedBox = new char[cols][rows];

        // Rotate the box 90 degrees clockwise
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                rotatedBox[col][rows - row - 1] = box[row][col];
            }
        }

        // Apply gravity to each column of the rotated box
        for (int col = 0; col < rows; col++) {
            Deque<Integer> emptySlots = new ArrayDeque<>();
            for (int row = cols - 1; row >= 0; row--) {
                if (rotatedBox[row][col] == '*') {
                    // Obstacle: clear the queue
                    emptySlots.clear();
                } else if (rotatedBox[row][col] == '.') {
                    // Empty slot: record its position
                    emptySlots.add(row);
                } else if (rotatedBox[row][col] == '#') {
                    // Stone: move it to the lowest available position if possible
                    if (!emptySlots.isEmpty()) {
                        int newRow = emptySlots.poll(); // Get the lowest empty slot
                        rotatedBox[newRow][col] = '#';  // Move the stone down
                        rotatedBox[row][col] = '.';     // Mark the current position as empty
                        emptySlots.add(row);           // The old position is now empty
                    }
                }
            }
        }

        return rotatedBox;
    }
}

//Rotation Logic:
//Each element in box[row][col] is mapped to rotatedBox[col][rows - row - 1], effectively
// performing a 90-degree clockwise rotation.
//Gravity Simulation:
//A Deque<Integer> (similar to Python's deque) is used to track empty positions (.) in each column.
//Stones (#) are moved to the lowest available position (from the deque) if one exists, while obstacles (*) clear
// the deque to reset available positions.
//Complexity Analysis
//Time Complexity:
// Rotation: O(m×n), where m is the number of rows and n is the number of columns.
// Gravity Simulation: O(m×n), as each element is processed once.
// Total: O(m×n).
//Space Complexity:
// Rotated matrix: O(m×n).
// Deque for gravity: O(min(m,n)).
// Total: O(m×n).


//You are given an m x n matrix of characters box representing a side-view of a box. Each cell of the
// box is one of the following:
//A stone '#'
//A stationary obstacle '*'
//Empty '.'
//The box is rotated 90 degrees clockwise, causing some of the stones to fall due to gravity. Each stone
// falls down until it lands on an obstacle, another stone, or the bottom of the box. Gravity does not
// affect the obstacles' positions, and the inertia from the box's rotation does not affect
// the stones' horizontal positions.
//It is guaranteed that each stone in box rests on an obstacle, another stone, or the bottom of the box.
//Return an n x m matrix representing the box after the rotation described above.
//
//Example 1:
//Input: box = [["#",".","#"]]
//Output: [["."],
//         ["#"],
//         ["#"]]

//Example 2:
//Input: box = [["#",".","*","."],
//              ["#","#","*","."]]
//Output: [["#","."],
//         ["#","#"],
//         ["*","*"],
//         [".","."]]

//Example 3:
//Input: box = [["#","#","*",".","*","."],
//              ["#","#","#","*",".","."],
//              ["#","#","#",".","#","."]]
//Output: [[".","#","#"],
//         [".","#","#"],
//         ["#","#","*"],
//         ["#","*","."],
//         ["#",".","*"],
//         ["#",".","."]]
//
//Constraints:
//m == box.length
//n == box[i].length
//1 <= m, n <= 500
//box[i][j] is either '#', '*', or '.'.
