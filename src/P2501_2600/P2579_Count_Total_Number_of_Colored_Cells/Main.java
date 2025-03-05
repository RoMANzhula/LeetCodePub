package P2501_2600.P2579_Count_Total_Number_of_Colored_Cells;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.coloredCells(1)); // Output: 1
        System.out.println(solution.coloredCells(2)); // Output: 5
        System.out.println(solution.coloredCells(3)); // Output: 13
        System.out.println(solution.coloredCells(4)); // Output: 25
    }

    public long coloredCells(int n) {
        return 1 + 2L * (n - 1) * n;
    }

}

//Observing the Growth Pattern:
//At minute 1, there is 1 colored cell.
//At minute 2, the initial cell is surrounded by 4 more cells, making the total 5.
//At minute 3, each of the outer cells gains new adjacent cells, expanding in a diamond shape:
//The new cells form a larger diamond around the existing shape.
//This pattern continues every minute.
//The number of colored cells follows this formula:
//Colored cells at minute
//n = 1 + 4 * ((n − 1) × n) / 2)  ==  1 + 2L * ((n - 1) * n)
//Complexity Analysis:
//Time Complexity: O(1), since we use a direct formula.
//Space Complexity: /O(1), as we only use a single variable


//There exists an infinitely large two-dimensional grid of uncolored unit cells. You are given a positive integer n,
// indicating that you must do the following routine for n minutes:
//At the first minute, color any arbitrary unit cell blue.
//Every minute thereafter, color blue every uncolored cell that touches a blue cell.
//Below is a pictorial representation of the state of the grid after minutes 1, 2, and 3.
//
//Return the number of colored cells at the end of n minutes.
//
//Example 1:
//Input: n = 1
//Output: 1
//Explanation: After 1 minute, there is only 1 blue cell, so we return 1.

//Example 2:
//Input: n = 2
//Output: 5
//Explanation: After 2 minutes, there are 4 colored cells on the boundary and 1 in the center, so we return 5.
//
//Constraints:
//1 <= n <= 105