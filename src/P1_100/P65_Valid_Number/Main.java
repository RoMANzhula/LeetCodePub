package P1_100.P65_Valid_Number;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String[] tests = {
                "2", "0089", "-0.1", "+3.14", "4.", "-.9",
                "2e10", "-90E3", "3e+7", "+6e-1",
                "53.5e93", "-123.456e789",
                "abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"
        };

        for (String t : tests) {
            System.out.println(t + " -> " + solution.isNumber(t));
        }
    }

    public boolean isNumber(String s) {
        s = s.trim(); // remove leading/trailing spaces
        boolean seenDigit = false;
        boolean seenDot = false;
        boolean seenExp = false;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                seenDigit = true;
            } else if (c == '+' || c == '-') {
                // sign can only appear at start or just after exponent
                if (i > 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E') {
                    return false;
                }
            } else if (c == '.') {
                // dot cannot appear after dot or exponent
                if (seenDot || seenExp) {
                    return false;
                }
                seenDot = true;
            } else if (c == 'e' || c == 'E') {
                // exponent must follow a number, cannot appear twice
                if (seenExp || !seenDigit) {
                    return false;
                }
                seenExp = true;
                seenDigit = false; // need digit after exponent
            } else {
                return false; // invalid character
            }
        }

        return seenDigit; // must end with a digit (not just "e" or ".")
    }

}

//Complexity:
// time - O(n)
// space - O(1)


//Given a string s, return whether s is a valid number.
//For example, all the following are valid numbers: "2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3",
// "3e+7", "+6e-1", "53.5e93", "-123.456e789", while the following are not valid numbers: "abc", "1a", "1e", "e3",
// "99e2.5", "--6", "-+3", "95a54e53".

//Formally, a valid number is defined using one of the following definitions:
//An integer number followed by an optional exponent.
//A decimal number followed by an optional exponent.
//An integer number is defined with an optional sign '-' or '+' followed by digits.
//
//A decimal number is defined with an optional sign '-' or '+' followed by one of the following definitions:
//
//Digits followed by a dot '.'.
//Digits followed by a dot '.' followed by digits.
//A dot '.' followed by digits.
//An exponent is defined with an exponent notation 'e' or 'E' followed by an integer number.
//The digits are defined as one or more digits.

//Example 1:
//Input: s = "0"
//Output: true

//Example 2:
//Input: s = "e"
//Output: false

//Example 3:
//Input: s = "."
//Output: false

//Constraints:
//1 <= s.length <= 20
//s consists of only English letters (both uppercase and lowercase), digits (0-9), plus '+', minus '-', or dot '.'.
