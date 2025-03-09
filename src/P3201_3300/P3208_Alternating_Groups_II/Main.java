package P3201_3300.P3208_Alternating_Groups_II;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] colors1 = {0, 1, 0, 1, 0};
        int k1 = 3;
        System.out.println(solution.numberOfAlternatingGroups(colors1, k1)); // Output: 3

        int[] colors2 = {0, 1, 0, 0, 1, 0, 1};
        int k2 = 6;
        System.out.println(solution.numberOfAlternatingGroups(colors2, k2)); // Output: 2

        int[] colors3 = {1, 1, 0, 1};
        int k3 = 4;
        System.out.println(solution.numberOfAlternatingGroups(colors3, k3)); // Output: 0
    }

    public int numberOfAlternatingGroups(int[] colors, int k) {
        int n = colors.length;
        int count = 0;

        // Step 1: precompute alternating pattern
        boolean[] isAlternating = new boolean[n];
        for (int i = 0; i < n; i++) {
            isAlternating[i] = (colors[i] != colors[(i + 1) % n]);
        }

        // step 2: use sliding window to count valid groups
        int validCount = 0;

        // initial window: count how many true values in first (k - 1) range
        for (int i = 0; i < k - 1; i++) {
            if (isAlternating[i]) validCount++;
        }

        // sliding window: move through the entire array
        for (int i = 0; i < n; i++) {
            // check if the current window is valid
            if (validCount == k - 1) count++;

            // remove the old element from the window
            if (isAlternating[i]) validCount--;

            // add the new element (wrap around)
            if (isAlternating[(i + k - 1) % n]) validCount++;
        }

        return count;
    }

}

//Efficient Algorithm
//Step 1: Compute an isAlternating array where isAlternating[i] is true if colors[i] and colors[i+1] (modulo n for
// circular check) are different.
//Step 2: Use a sliding window to count valid k-length alternating sequences.
//Time Complexity
//Precompute alternation check: O(n)
//Sliding window validation: O(n)
//Total Complexity: O(n) (linear time)


//There is a circle of red and blue tiles. You are given an array of integers colors and an integer k. The color of
// tile i is represented by colors[i]:
//colors[i] == 0 means that tile i is red.
//colors[i] == 1 means that tile i is blue.
//An alternating group is every k contiguous tiles in the circle with alternating colors (each tile in the group
// except the first and last one has a different color from its left and right tiles).
//Return the number of alternating groups.
//Note that since colors represents a circle, the first and the last tiles are considered to be next to each other.

//Example 1: on screen
//Input: colors = [0,1,0,1,0], k = 3
//Output: 3
//Explanation: on screen

//Alternating groups: on screen
//Example 2: on screen
//Input: colors = [0,1,0,0,1,0,1], k = 6
//Output: 2
//Explanation: on screen

//Alternating groups: on screen
//Example 3: on screen
//Input: colors = [1,1,0,1], k = 4
//Output: 0
//Explanation: on screen

//Constraints:
//3 <= colors.length <= 105
//0 <= colors[i] <= 1
//3 <= k <= colors.length
