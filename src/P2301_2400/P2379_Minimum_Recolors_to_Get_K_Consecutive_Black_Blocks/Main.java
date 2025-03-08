package P2301_2400.P2379_Minimum_Recolors_to_Get_K_Consecutive_Black_Blocks;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.minimumRecolors("WBBWWBBWBW", 7)); // Output: 3
        System.out.println(solution.minimumRecolors("WBWBBBW", 2)); // Output: 0
    }

    public int minimumRecolors(String blocks, int k) {
        int n = blocks.length();
        int whiteCount = 0, minOps = Integer.MAX_VALUE;

        // count white blocks in the first window of size k
        for (int i = 0; i < k; i++) {
            if (blocks.charAt(i) == 'W') {
                whiteCount++;
            }
        }
        minOps = whiteCount; // initialize minOps with the first window's count

        // slide the window across the string
        for (int i = k; i < n; i++) {
            // remove the leftmost block from the previous window
            if (blocks.charAt(i - k) == 'W') {
                whiteCount--;
            }
            // add the new block to the window
            if (blocks.charAt(i) == 'W') {
                whiteCount++;
            }
            // update the minimum operations needed
            minOps = Math.min(minOps, whiteCount);
        }

        return minOps;
    }

}

//Explanation
//We initialize a sliding window of size k and count the number of 'W' blocks in it.
//As we slide the window, we update the count efficiently by removing the effect of the leftmost element and
// adding the effect of the new rightmost element.
//We track the minimum number of 'W' blocks across all windows, which gives us the minimum operations needed.
//Complexity analytics:
//Time Complexity: O(n): we iterate through the string only once.
//Space Complexity: O(1): only a few integer variables are used.


//You are given a 0-indexed string blocks of length n, where blocks[i] is either 'W' or 'B', representing the
// color of the ith block. The characters 'W' and 'B' denote the colors white and black, respectively.
//You are also given an integer k, which is the desired number of consecutive black blocks.
//In one operation, you can recolor a white block such that it becomes a black block.
//Return the minimum number of operations needed such that there is at least one occurrence of k consecutive
// black blocks.
//
//Example 1:
//Input: blocks = "WBBWWBBWBW", k = 7
//Output: 3
//Explanation:
//One way to achieve 7 consecutive black blocks is to recolor the 0th, 3rd, and 4th blocks
//so that blocks = "BBBBBBBWBW".
//It can be shown that there is no way to achieve 7 consecutive black blocks in less than 3 operations.
//Therefore, we return 3.

//Example 2:
//Input: blocks = "WBWBBBW", k = 2
//Output: 0
//Explanation:
//No changes need to be made, since 2 consecutive black blocks already exist.
//Therefore, we return 0.

//Constraints:
//n == blocks.length
//1 <= n <= 100
//blocks[i] is either 'W' or 'B'.
//1 <= k <= n
