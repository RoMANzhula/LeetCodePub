package P2201_2300.P2275_Largest_Combination_With_Bitwise_AND_Greater_Than_Zero;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] candidates1 = {16, 17, 71, 62, 12, 24, 14};
        System.out.println("Example 1 - Output: " + solution.largestCombination(candidates1));  // Expected Output: 4

        int[] candidates2 = {8, 8};
        System.out.println("Example 2 - Output: " + solution.largestCombination(candidates2));  // Expected Output: 2
    }

    public int largestCombination(int[] candidates) {
        int maxSubsetSize = 0;

        // Loop through each bit position (up to 24 because the max candidate is 10^7)
        for (int bit = 0; bit < 24; bit++) {
            int count = 0;

            // Count how many numbers have a 1 at the current bit position
            for (int num : candidates) {
                if ((num & (1 << bit)) != 0) {
                    count++;
                }
            }

            // Update max subset size if this bit position has more 1s
            maxSubsetSize = Math.max(maxSubsetSize, count);
        }

        return maxSubsetSize;
    }
}

//Explanation of the Code
//Bit Loop: We loop over each bit position from 0 to 23.
//Count: For each bit, we count how many numbers in candidates have a 1 at that bit position.
//Update Maximum: We keep track of the maximum count across all bit positions, as this is the largest subset
// size with a bitwise AND greater than zero.
//Complexity Analysis
//Time Complexity:
//O(n⋅m), where n is the number of elements in candidates, and m is the number of bit positions (24 for our constraint).
//Space Complexity:
//O(1), as we only need a few extra variables.


//The bitwise AND of an array nums is the bitwise AND of all integers in nums.
//For example, for nums = [1, 5, 3], the bitwise AND is equal to 1 & 5 & 3 = 1.
//Also, for nums = [7], the bitwise AND is 7.
//You are given an array of positive integers candidates. Evaluate the bitwise AND of every combination of
// numbers of candidates. Each number in candidates may only be used once in each combination.
//Return the size of the largest combination of candidates with a bitwise AND greater than 0.

//Example 1:
//Input: candidates = [16,17,71,62,12,24,14]
//Output: 4
//Explanation: The combination [16,17,62,24] has a bitwise AND of 16 & 17 & 62 & 24 = 16 > 0.
//The size of the combination is 4.
//It can be shown that no combination with a size greater than 4 has a bitwise AND greater than 0.
//Note that more than one combination may have the largest size.
//For example, the combination [62,12,24,14] has a bitwise AND of 62 & 12 & 24 & 14 = 8 > 0.

//Example 2:
//Input: candidates = [8,8]
//Output: 2
//Explanation: The largest combination [8,8] has a bitwise AND of 8 & 8 = 8 > 0.
//The size of the combination is 2, so we return 2.
//
//Constraints:
//1 <= candidates.length <= 105
//1 <= candidates[i] <= 107
