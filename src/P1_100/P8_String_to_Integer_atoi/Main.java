package P1_100.P8_String_to_Integer_atoi;

public class Main {

    public static void main(String[] args) {
        String str = "1234 aasddf"; // example string
        System.out.println(myAtoi(str));
    }

    public static int myAtoi(String s) { // method that converts the string to an integer considering the sign (+ or -)
        s = s.trim(); // using the trim() method to remove any spaces at the beginning and end of the string
        if (s.isEmpty()) { // if the string is empty
            return 0; // return zero
        }
        int sign = 1; // variable for the "+" sign
        int index = 0; // index variable
        if (s.charAt(index) == '+' || s.charAt(index) == '-') { // check if the string contains the "+" or "-" sign
            sign = s.charAt(index) == '+' ? 1 : -1; // if it's a plus, set to 1; if minus, set to -1
            index++; // increase the index by 1
        }
        int result = 0; // create an integer variable to store the result of the method
        while (index < s.length() && Character.isDigit(s.charAt(index))) { // loop until the index is less than the string's length and
            // the character at the current index is a digit (the loop stops otherwise)
            int digit = Character.getNumericValue(s.charAt(index)); // extract the character from the string s at index, then
            // convert it to an integer value using the Character.getNumericValue() method
            if (result > (Integer.MAX_VALUE - digit) / 10) { // this if block checks if the current result exceeds the
                // Integer.MAX_VALUE or Integer.MIN_VALUE (depending on the sign) after adding the next digit from the string. This
                // check is done to prevent overflow that could occur if the result exceeds the maximum or minimum value of Integer.
                // If the condition is met, the function returns Integer.MAX_VALUE or Integer.MIN_VALUE
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE; // if the result exceeds the maximum possible Integer value,
                // return the appropriate value based on the sign of the number (+1 for positive, -1 for negative).
            }
            result = result * 10 + digit; // add the next digit to the current result. Since integer division in Java discards the fractional part,
            // (Integer.MAX_VALUE - digit) / 10 gives the maximum value that can be added to the current result without overflow.
            index++; // increase the index by 1
        }
        return result * sign; // return the number considering the sign (* sign)
    }
}



//Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer.
//The algorithm for myAtoi(string s) is as follows:
//Whitespace: Ignore any leading whitespace (" ").
//Signedness: Determine the sign by checking if the next character is '-' or '+', assuming positivity if
// neither present.
//Conversion: Read the integer by skipping leading zeros until a non-digit character is encountered or the end of
// the string is reached. If no digits were read, then the result is 0.
//Rounding: If the integer is out of the 32-bit signed integer range [-231, 231 - 1], then round the integer to
// remain in the range. Specifically, integers less than -231 should be rounded to -231, and integers greater
// than 231 - 1 should be rounded to 231 - 1.
//Return the integer as the final result.

//Example 1:
//Input: s = "42"
//Output: 42
//Explanation:
//The underlined characters are what is read in and the caret is the current reader position.
//Step 1: "42" (no characters read because there is no leading whitespace)
//         ^
//Step 2: "42" (no characters read because there is neither a '-' nor '+')
//         ^
//Step 3: "42" ("42" is read in)
//           ^

//Example 2:
//Input: s = " -042"
//Output: -42
//Explanation:
//Step 1: "   -042" (leading whitespace is read and ignored)
//            ^
//Step 2: "   -042" ('-' is read, so the result should be negative)
//             ^
//Step 3: "   -042" ("042" is read in, leading zeros ignored in the result)
//               ^

//Example 3:
//Input: s = "1337c0d3"
//Output: 1337
//Explanation:
//Step 1: "1337c0d3" (no characters read because there is no leading whitespace)
//         ^
//Step 2: "1337c0d3" (no characters read because there is neither a '-' nor '+')
//         ^
//Step 3: "1337c0d3" ("1337" is read in; reading stops because the next character is a non-digit)
//             ^

//Example 4:
//Input: s = "0-1"
//Output: 0
//Explanation:
//Step 1: "0-1" (no characters read because there is no leading whitespace)
//         ^
//Step 2: "0-1" (no characters read because there is neither a '-' nor '+')
//         ^
//Step 3: "0-1" ("0" is read in; reading stops because the next character is a non-digit)
//          ^
//Example 5:
//Input: s = "words and 987"
//Output: 0
//Explanation:
//Reading stops at the first non-digit character 'w'.

//Constraints:
//0 <= s.length <= 200
//s consists of English letters (lower-case and upper-case), digits (0-9), ' ', '+', '-', and '.'.