package P2401_2500.P2429_Minimize_XOR;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int num1 = 3, num2 = 5;
        System.out.println("Output: " + solution.minimizeXor(num1, num2)); // Output 3

        num1 = 3;
        num2 = 5;
        System.out.println("Output: " + solution.minimizeXor(num1, num2)); // Output: 3
    }

    public int minimizeXor(int num1, int num2) {
        // count the number of set bits in num2
        int setBitNum2 = Integer.bitCount(num2);

        //start with num1
        int x = 0;

        // use set bits from num1
        for (int i = 31; i >= 0 && setBitNum2 > 0; i--) {
            if ((num1 & (1 << i)) != 0) {
                x |= (1 << i);
                setBitNum2--;
            }
        }

        //if more set bits are needed, we use the lowest bits
        for (int i = 0; i <= 31 && setBitNum2 > 0; i++) {
            if ((x & (1 << i)) == 0) {
                x |= (1 << i);
                setBitNum2--;
            }
        }

        return x;
    }
}

//Explanation of the Code:
//Counting Set Bits in num2:
//Use Integer.bitCount(num2) to determine the number of 1s in the binary representation of num2.
//Constructing x Based on num1:
//-Iterate through the bits of num1 from most significant to least significant.
//-Copy set bits from num1 into x while decrementing the count of required set bits (setBitsNum2).
//Filling Remaining Set Bits:
//If more set bits are required after processing num1, start filling the lowest unset bits in x to ensure it
// has the exact required number of set bits.
//Return x:
//The constructed x satisfies the constraints of having the same number of set bits as num2 while minimizing
// the XOR with num1.
//This approach ensures efficiency with a time complexity of O(32), which is constant since integers have a
// fixed number of bits.


//Given two positive integers num1 and num2, find the positive integer x such that:
//-x has the same number of set bits as num2, and
//-The value x XOR num1 is minimal.
//Note that XOR is the bitwise XOR operation.
//Return the integer x. The test cases are generated such that x is uniquely determined.
//The number of set bits of an integer is the number of 1's in its binary representation.
//
//Example 1:
//Input: num1 = 3, num2 = 5
//Output: 3
//Explanation:
//The binary representations of num1 and num2 are 0011 and 0101, respectively.
//The integer 3 has the same number of set bits as num2, and the value 3 XOR 3 = 0 is minimal.

//Example 2:
//Input: num1 = 1, num2 = 12
//Output: 3
//Explanation:
//The binary representations of num1 and num2 are 0001 and 1100, respectively.
//The integer 3 has the same number of set bits as num2, and the value 3 XOR 1 = 2 is minimal.
//
//Constraints:
//1 <= num1, num2 <= 109
