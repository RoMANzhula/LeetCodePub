package P1_100.P89_Gray_Code;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println("Gray Code Sequence:" + solution.grayCode(2)); // output: [0,1,3,2]
        System.out.println("Gray Code Sequence:" + solution.grayCode(1)); // output: [0,1]
    }

    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();
        int total = 1 << n; // 2^n

        for (int i = 0; i < total; i++) {
            result.add(i ^ (i >> 1));
        }

        return result;
    }

}

//This guarantees that adjacent values differ by exactly one bit, which is the core requirement of a Gray Code sequence.
//Complexity:
// time/space - O(2^n)


//An n-bit gray code sequence is a sequence of 2n integers where:
//Every integer is in the inclusive range [0, 2n - 1],
//The first integer is 0,
//An integer appears no more than once in the sequence,
//The binary representation of every pair of adjacent integers differs by exactly one bit, and
//The binary representation of the first and last integers differs by exactly one bit.
//Given an integer n, return any valid n-bit gray code sequence.

//Example 1:
//Input: n = 2
//Output: [0,1,3,2]
//Explanation:
//The binary representation of [0,1,3,2] is [00,01,11,10].
//- 00 and 01 differ by one bit
//- 01 and 11 differ by one bit
//- 11 and 10 differ by one bit
//- 10 and 00 differ by one bit
//[0,2,3,1] is also a valid gray code sequence, whose binary representation is [00,10,11,01].
//- 00 and 10 differ by one bit
//- 10 and 11 differ by one bit
//- 11 and 01 differ by one bit
//- 01 and 00 differ by one bit

//Example 2:
//Input: n = 1
//Output: [0,1]

//Constraints:
//1 <= n <= 16
