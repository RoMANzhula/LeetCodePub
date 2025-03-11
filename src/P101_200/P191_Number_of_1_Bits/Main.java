package P101_200.P191_Number_of_1_Bits;

public class Main {
    public static void main(String[] args) {

        int num2 = 00000000000000000000000010000000 ;
        int num3 = 00000000000000000000000000001011;

//        System.out.println(hammingWeight(num1));
        System.out.println(hammingWeight(num2));
        System.out.println(hammingWeight(num3));
    }

    public static int hammingWeight(int n) {
//        int res = 0;
//        String fromIntToString = Integer.toBinaryString(n);
//        System.out.println(fromIntToString);
//        for (char ch : fromIntToString.toCharArray()) {
//            if (ch == '1') res++;
//        }
//        return res;

        //some quickly
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }
}


//Given a positive integer n, write a function that returns the number of set bits in its binary
// representation (also known as the Hamming weight).

//Example 1:
//Input: n = 11
//Output: 3
//Explanation:
//The input binary string 1011 has a total of three set bits.

//Example 2:
//Input: n = 128
//Output: 1
//Explanation:
//The input binary string 10000000 has a total of one set bit.

//Example 3:
//Input: n = 2147483645
//Output: 30
//Explanation:
//The input binary string 1111111111111111111111111111101 has a total of thirty set bits.

//Constraints:
//1 <= n <= 231 - 1