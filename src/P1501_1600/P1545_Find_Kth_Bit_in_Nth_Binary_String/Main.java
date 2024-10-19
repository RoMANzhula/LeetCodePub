package P1501_1600.P1545_Find_Kth_Bit_in_Nth_Binary_String;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int n1 = 3;
        int k1 = 1;
        System.out.println("The " + k1 + "th bit in S" + n1 + " is: " + solution.findKthBit(n1, k1));  // Output: "0"

        int n2 = 4;
        int k2 = 11;
        System.out.println("The " + k2 + "th bit in S" + n2 + " is: " + solution.findKthBit(n2, k2));  // Output: "1"
    }

    public char findKthBit(int n, int k) {
        return findKthBitHelper(n, k);
    }

    private char findKthBitHelper(int n, int k) {
        // Base case: when n = 1, S1 is "0", so return '0'.
        if (n == 1) {
            return '0';
        }

        // Length of Sn is 2^n - 1
        int length = (1 << n) - 1;
        int mid = length / 2 + 1;

        if (k == mid) {
            // The middle element is always '1'
            return '1';
        } else if (k < mid) {
            // If k is in the first half, it's the same as S(n-1)
            return findKthBitHelper(n - 1, k);
        } else {
            // If k is in the second half, find the mirrored index in S(n-1)
            // Mirrored index = length - k + 1, and we need to invert the result
            char result = findKthBitHelper(n - 1, length - k + 1);
            return result == '0' ? '1' : '0';
        }
    }

}

//Explanation:
//Base Case: When n == 1, the string S1 is just "0", so we return '0'.
//Middle Element: For any n, the middle element of S_n is always "1".
//First Half: If k is in the first half (i.e., k < mid), the result is the same as S_(n-1) for the same k.
//Second Half: If k is in the second half (i.e., k > mid), we recursively find the corresponding element in
// S_(n-1) but mirrored and inverted.
//Time Complexity:
//Since the recursion reduces the problem size by roughly half at each step, the time complexity is O(n), where
// n is the given integer n.


//Given two positive integers n and k, the binary string Sn is formed as follows:
//S1 = "0"
//Si = Si - 1 + "1" + reverse(invert(Si - 1)) for i > 1
//Where + denotes the concatenation operation, reverse(x) returns the reversed string x, and invert(x) inverts
// all the bits in x (0 changes to 1 and 1 changes to 0).
//For example, the first four strings in the above sequence are:
//S1 = "0"
//S2 = "011"
//S3 = "0111001"
//S4 = "011100110110001"
//Return the kth bit in Sn. It is guaranteed that k is valid for the given n.
//
//Example 1:
//Input: n = 3, k = 1
//Output: "0"
//Explanation: S3 is "0111001".
//The 1st bit is "0".

//Example 2:
//Input: n = 4, k = 11
//Output: "1"
//Explanation: S4 is "011100110110001".
//The 11th bit is "1".
//
//Constraints:
//1 <= n <= 20
//1 <= k <= 2n - 1
