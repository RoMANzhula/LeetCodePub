package P101_200.P166_Fraction_to_Recurring_Decimal;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.fractionToDecimal(1, 2));     // Output: "0.5"
        System.out.println(solution.fractionToDecimal(2, 1));     // Output: "2"
        System.out.println(solution.fractionToDecimal(4, 333));   // Output: "0.(012)"
        System.out.println(solution.fractionToDecimal(1, 6));     // Output: "0.1(6)"
        System.out.println(solution.fractionToDecimal(-50, 8));   // Output: "-6.25"
    }

    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";

        StringBuilder result = new StringBuilder();

        // handle sign
        if ((numerator < 0) ^ (denominator < 0)) {
            result.append("-");
        }

        // convert to long to avoid overflow
        long dividend = Math.abs((long) numerator);
        long divisor = Math.abs((long) denominator);

        // integer part
        result.append(dividend / divisor);
        long remainder = dividend % divisor;

        if (remainder == 0) return result.toString();

        result.append(".");
        Map<Long, Integer> map = new HashMap<>();

        // process fractional part
        while (remainder != 0) {
            if (map.containsKey(remainder)) {
                // innsert "(" at index where repetition starts
                result.insert(map.get(remainder), "(");
                result.append(")");

                break;
            }

            map.put(remainder, result.length());
            remainder *= 10;
            result.append(remainder / divisor);
            remainder %= divisor;
        }

        return result.toString();
    }

}

//Complexity:
// time - O(denominator)
// space - O(denominator)


//Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
//If the fractional part is repeating, enclose the repeating part in parentheses.
//If multiple answers are possible, return any of them.
//It is guaranteed that the length of the answer string is less than 104 for all the given inputs.

//Example 1:
//Input: numerator = 1, denominator = 2
//Output: "0.5"

//Example 2:
//Input: numerator = 2, denominator = 1
//Output: "2"

//Example 3:
//Input: numerator = 4, denominator = 333
//Output: "0.(012)"

//Constraints:
//-231 <= numerator, denominator <= 231 - 1
//denominator != 0
