package P2301_2400.P2337_Move_Pieces_to_Obtain_a_String;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.canChange("_L__R__R_", "L______RR")); // true
        System.out.println(solution.canChange("R_L_", "__LR")); // false
        System.out.println(solution.canChange("_R", "R_")); // false
    }

    public boolean canChange(String start, String target) {
        // Remove blanks from both strings
        String startNoBlanks = start.replace("_", "");
        String targetNoBlanks = target.replace("_", "");

        // If the strings without blanks don't match, transformation is impossible
        if (!startNoBlanks.equals(targetNoBlanks)) {
            return false;
        }

        int n = start.length();
        int i = 0, j = 0;

        // Check movement constraints
        while (i < n && j < n) {
            // Skip blanks in `start`
            while (i < n && start.charAt(i) == '_') {
                i++;
            }
            // Skip blanks in `target`
            while (j < n && target.charAt(j) == '_') {
                j++;
            }

            // If both pointers are out of bounds, we're done
            if (i == n && j == n) {
                return true;
            }

            // If one pointer is out of bounds, but the other is not, fail
            if (i == n || j == n) {
                return false;
            }

            // Check the positions of 'L' and 'R'
            if (start.charAt(i) != target.charAt(j)) {
                return false;
            }

            if (start.charAt(i) == 'L' && i < j) {
                return false; // 'L' cannot move right
            }

            if (start.charAt(i) == 'R' && i > j) {
                return false; // 'R' cannot move left
            }

            // Move to the next piece
            i++;
            j++;
        }

        return true;
    }
}

//Explanation
//Blank Removal: The transformation is impossible if the order of 'L' and 'R' doesn't match after removing '_'.
//Pointer Check: Using two pointers (i and j), we traverse both start and target while skipping blank spaces.
//Constraint Validation:
//For 'L', ensure that it doesn't move to the right (i >= j).
//For 'R', ensure that it doesn't move to the left (i <= j).
//Complexity
//Time Complexity:
//O(n), where n is the length of the strings, as we iterate through the strings once.
//Space Complexity:
//O(n), due to the creation of strings without blanks.


//You are given two strings start and target, both of length n. Each string consists only of
// the characters 'L', 'R', and '_' where:
//
//The characters 'L' and 'R' represent pieces, where a piece 'L' can move to the left only if there is a
// blank space directly to its left, and a piece 'R' can move to the right only if there is a blank space
// directly to its right.
//The character '_' represents a blank space that can be occupied by any of the 'L' or 'R' pieces.
//Return true if it is possible to obtain the string target by moving the pieces of the string start any number of
// times. Otherwise, return false.
//
//Example 1:
//Input: start = "_L__R__R_", target = "L______RR"
//Output: true
//Explanation: We can obtain the string target from start by doing the following moves:
//- Move the first piece one step to the left, start becomes equal to "L___R__R_".
//- Move the last piece one step to the right, start becomes equal to "L___R___R".
//- Move the second piece three steps to the right, start becomes equal to "L______RR".
//Since it is possible to get the string target from start, we return true.

//Example 2:
//Input: start = "R_L_", target = "__LR"
//Output: false
//Explanation: The 'R' piece in the string start can move one step to the right to obtain "_RL_".
//After that, no pieces can move anymore, so it is impossible to obtain the string target from start.

//Example 3:
//Input: start = "_R", target = "R_"
//Output: false
//Explanation: The piece in the string start can move only to the right, so it is impossible to obtain the
// string target from start.
//
//Constraints:
//n == start.length == target.length
//1 <= n <= 105
//start and target consist of the characters 'L', 'R', and '_'.
