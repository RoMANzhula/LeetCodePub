package P2201_2300.P2220_Minimum_Bit_Flips_to_Convert_Number;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.minBitFlips(10, 7)); // Output: 3
        System.out.println(solution.minBitFlips(3, 4));  // Output: 3
    }

    public int minBitFlips(int start, int goal) {
        // XOR of start and goal will give us the bits that are different
        int xor = start ^ goal;

        // Count the number of 1s in the binary representation of xor
        int count = 0;

        // We use a loop to count the number of 1s
        while (xor > 0) {
            // Check if the least significant bit is 1
            count += xor & 1;
            // Shift bits to the right to check the next bit
            xor >>= 1;
        }

        return count;
    }
}

//Explanation:
//int xor = start ^ goal; computes the XOR of start and goal, giving a number where each 1 in the binary
// representation indicates a bit difference.
//The while (xor > 0) loop shifts through the binary representation of xor and counts how many 1s it
// contains using xor & 1.
//xor >>= 1; shifts xor to the right by one bit after each iteration, effectively moving through each bit of the number.
//Time Complexity:
//The time complexity is O(log N), where N is the larger of start and goal, as we are checking each bit of the numbers.


//A bit flip of a number x is choosing a bit in the binary representation of x and flipping it
// from either 0 to 1 or 1 to 0.
//For example, for x = 7, the binary representation is 111 and we may choose any bit (including any leading zeros
// not shown) and flip it. We can flip the first bit from the right to get 110, flip the second bit from the right to
// get 101, flip the fifth bit from the right (a leading zero) to get 10111, etc.
//Given two integers start and goal, return the minimum number of bit flips to convert start to goal.
//
//Example 1:
//Input: start = 10, goal = 7
//Output: 3
//Explanation: The binary representation of 10 and 7 are 1010 and 0111 respectively. We can convert 10 to 7 in 3 steps:
//- Flip the first bit from the right: 1010 -> 1011.
//- Flip the third bit from the right: 1011 -> 1111.
//- Flip the fourth bit from the right: 1111 -> 0111.
//It can be shown we cannot convert 10 to 7 in less than 3 steps. Hence, we return 3.

//Example 2:
//Input: start = 3, goal = 4
//Output: 3
//Explanation: The binary representation of 3 and 4 are 011 and 100 respectively. We can convert 3 to 4 in 3 steps:
//- Flip the first bit from the right: 011 -> 010.
//- Flip the second bit from the right: 010 -> 000.
//- Flip the third bit from the right: 000 -> 100.
//It can be shown we cannot convert 3 to 4 in less than 3 steps. Hence, we return 3.
//
//Constraints:
//0 <= start, goal <= 109