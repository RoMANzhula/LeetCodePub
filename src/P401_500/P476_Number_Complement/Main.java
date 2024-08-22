package P401_500.P476_Number_Complement;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.findComplement(5));  // Output: 2
        System.out.println(solution.findComplement(1));  // Output: 0
    }

    public int findComplement(int num) {
        // Find the number of bits in the binary representation of num
        int bitLength = Integer.toBinaryString(num).length();

        // Create a bitmask with all bits set to 1 of the same length as the binary number
        int bitmask = (1 << bitLength) - 1;

        // Return the complement using XOR
        return num ^ bitmask;
    }
}

//Explanation:
//Integer.toBinaryString(num).length(): Finds the number of bits in the binary representation of num.
//(1 << bitLength) - 1: Creates a bitmask of the same length as num with all bits set to 1.
//num ^ bitmask: Performs the XOR operation between num and the bitmask to get the complement.


//The complement of an integer is the integer you get when you flip all the 0's to 1's and all the 1's to 0's in
// its binary representation.
//For example, The integer 5 is "101" in binary and its complement is "010" which is the integer 2.
//Given an integer num, return its complement.
//
//Example 1:
//Input: num = 5
//Output: 2
//Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. So you
// need to output 2.

//Example 2:
//Input: num = 1
//Output: 0
//Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. So you
// need to output 0.
//
//Constraints:
//1 <= num < 231
