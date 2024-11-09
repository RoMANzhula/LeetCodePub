package P3101_3200.P3133_Minimum_Array_End;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int n1 = 3, x1 = 4;
        long result1 = solution.minEnd(n1, x1);
        System.out.println("Example 1 - Expected: 6, Output: " + result1);

        int n2 = 2, x2 = 7;
        long result2 = solution.minEnd(n2, x2);
        System.out.println("Example 2 - Expected: 15, Output: " + result2);
    }

    public long minEnd(int n, int x) {
        // Determine the maximum number of bits required by considering n and x
        final int maxBit = (int) (Math.log(n) / Math.log(2)) + (int) (Math.log(x) / Math.log(2)) + 2;
        long k = n - 1;
        long ans = x;
        int kBinaryIndex = 0;

        for (int i = 0; i < maxBit; ++i) {
            // Check if the current bit of ans is 0
            if ((ans >> i & 1) == 0) {
                // Set ans's bit to 1 if the corresponding bit in k is 1
                if ((k >> kBinaryIndex & 1) == 1) {
                    ans |= 1L << i;
                }
                kBinaryIndex++;
            }
        }

        return ans;
    }
}

//Explanation:
//maxBit Calculation:
//Calculated using Math.log to determine the number of bits necessary for both n and x.
//Loop through maxBit:
//For each bit position, check if the current bit in ans is 0.
//If it is 0 and the corresponding bit in k (in its binary representation) is 1, set this bit in ans
// by using the bitwise OR operation with 1L << i.
//Return ans:
//After the loop, ans contains the minimum possible value for the last element that satisfies the conditions.


//You are given two integers n and x. You have to construct an array of positive integers nums of
// size n where for every 0 <= i < n - 1, nums[i + 1] is greater than nums[i], and the result of the
// bitwise AND operation between all elements of nums is x.
//Return the minimum possible value of nums[n - 1].

//Example 1:
//Input: n = 3, x = 4
//Output: 6
//Explanation:
//nums can be [4,5,6] and its last element is 6.

//Example 2:
//Input: n = 2, x = 7
//Output: 15
//Explanation:
//nums can be [7,15] and its last element is 15.
//
//Constraints:
//1 <= n, x <= 108