package P1401_1500.P1404_Number_of_Steps_to_Reduce_a_Number_in_Binary_Representation_to_One;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.numSteps("1101")); // Output: 6
        System.out.println(solution.numSteps("10"));   // Output: 1
        System.out.println(solution.numSteps("1"));    // Output: 0
    }

//    public int numSteps(String s) {
//        BigInteger number = new BigInteger(s, 2);  // Convert binary string to BigInteger
//        int steps = 0;
//
//        while (!number.equals(BigInteger.ONE)) {
//            if (number.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
//                // If the number is even, divide it by 2
//                number = number.divide(BigInteger.TWO);
//            } else {
//                // If the number is odd, add 1 to it
//                number = number.add(BigInteger.ONE);
//            }
//            steps++;
//        }
//
//        return steps;
//    }

    // the faster solution
    public static int numSteps(String s) {
        int steps = 0;
        int carry = 0;

        // Traverse the string from end to start
        for (int i = s.length() - 1; i > 0; i--) {
            int bit = s.charAt(i) - '0';

            if (bit + carry == 1) {
                // If current bit + carry is 1, it's odd, so we need to add 1 (effectively increasing carry)
                carry = 1;
                steps += 2; // One for making it even (bit + 1) and one for division by 2
            } else {
                // If current bit + carry is 0, it's even, so just divide by 2
                steps += 1;
            }
        }

        // Finally, handle the most significant bit (the leftmost '1' bit)
        return steps + carry;
    }
}

//int carry = 0;: Це змінна для відстеження переносу, який виникає при додаванні 1 до числа.
//Ми проходимо рядок з кінця до початку, обробляючи кожен біт.
//Якщо біт разом з переносом (carry) дорівнює 1, це означає, що число непарне, тому нам потрібно
// додати 1 (що збільшує перенос) і потім розділити на 2 (2 кроки).
//Якщо біт разом з переносом дорівнює 0, число парне, тому ми просто ділимо на 2 (1 крок).
//В кінці ми додаємо carry до кроків, щоб врахувати обробку старшого біта.
//Останнє рішення більш ефективне, оскільки воно працює з рядком напряму і не використовує великі цілі числа.

//Given the binary representation of an integer as a string s, return the number of steps to reduce it to 1 under
// the following rules:
//If the current number is even, you have to divide it by 2.
//If the current number is odd, you have to add 1 to it.
//It is guaranteed that you can always reach one for all test cases.
//
//Example 1:
//Input: s = "1101"
//Output: 6
//Explanation: "1101" corressponds to number 13 in their decimal representation.
//Step 1) 13 is odd, add 1 and obtain 14.
//Step 2) 14 is even, divide by 2 and obtain 7.
//Step 3) 7 is odd, add 1 and obtain 8.
//Step 4) 8 is even, divide by 2 and obtain 4.
//Step 5) 4 is even, divide by 2 and obtain 2.
//Step 6) 2 is even, divide by 2 and obtain 1.

//Example 2:
//Input: s = "10"
//Output: 1
//Explanation: "10" corressponds to number 2 in their decimal representation.
//Step 1) 2 is even, divide by 2 and obtain 1.

//Example 3:
//Input: s = "1"
//Output: 0
//
//Constraints:
//1 <= s.length <= 500
//s consists of characters '0' or '1'
//s[0] == '1'
