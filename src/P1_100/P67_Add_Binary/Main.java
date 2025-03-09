package P1_100.P67_Add_Binary;

import java.math.BigInteger;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String str1 = "11";
        String str2 = "1";

        System.out.println(solution.addBinary(str1, str2));
    }

    public String addBinary(String a, String b) {
        BigInteger num1 = new BigInteger(a, 2);
        BigInteger num2 = new BigInteger(b, 2);
        BigInteger sum = num1.add(num2);
        return sum.toString(2);
    }

    //    public String addBinary(String a, String b) {
    //        StringBuilder result = new StringBuilder();
    //        int carry = 0;
    //        int i = a.length() - 1;
    //        int j = b.length() - 1;
    //
    //        while (i >= 0 || j >= 0) {
    //            int sum = carry;
    //            if (i >= 0) {
    //                sum += a.charAt(i) - '0';
    //                i--;
    //            }
    //            if (j >= 0) {
    //                sum += b.charAt(j) - '0';
    //                j--;
    //            }
    //
    //            result.insert(0, sum % 2);
    //            carry = sum / 2;
    //        }
    //
    //        if (carry > 0) {
    //            result.insert(0, carry);
    //        }
    //
    //        return result.toString();
    //    }

}


//Given two binary strings a and b, return their sum as a binary string.

//Example 1:
//Input: a = "11", b = "1"
//Output: "100"

//Example 2:
//Input: a = "1010", b = "1011"
//Output: "10101"
//
//Constraints:
//1 <= a.length, b.length <= 104
//a and b consist only of '0' or '1' characters.
//Each string does not contain leading zeros except for the zero itself.