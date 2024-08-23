package P501_600.P592_Fraction_Addition_and_Substraction;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.fractionAddition("-1/2+1/2")); // Output: "0/1"
        System.out.println(solution.fractionAddition("-1/2+1/2+1/3")); // Output: "1/3"
        System.out.println(solution.fractionAddition("1/3-1/2")); // Output: "-1/6"
    }

    public String fractionAddition(String expression) {
        int numerator = 0, denominator = 1;
        int i = 0, n = expression.length();

        while (i < n) {
            int sign = 1;
            if (expression.charAt(i) == '-' || expression.charAt(i) == '+') {
                sign = expression.charAt(i) == '-' ? -1 : 1;
                i++;
            }

            int num = 0;
            while (i < n && Character.isDigit(expression.charAt(i))) {
                num = num * 10 + (expression.charAt(i++) - '0');
            }
            num *= sign;

            i++; // Skip the '/'

            int den = 0;
            while (i < n && Character.isDigit(expression.charAt(i))) {
                den = den * 10 + (expression.charAt(i++) - '0');
            }

            // Calculate the new numerator and denominator
            numerator = numerator * den + num * denominator;
            denominator *= den;

            // Reduce the fraction
            int gcd = gcd(Math.abs(numerator), denominator);
            numerator /= gcd;
            denominator /= gcd;
        }

        return numerator + "/" + denominator;
    }

    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}

//Explanation:
//Input Parsing: The program reads the input expression and identifies the sign (+ or -), numerator, and
// denominator of each fraction.
//Numerator and Denominator Update: It calculates the result by adding/subtracting the fractions. This is
// done by updating the numerator and denominator using the common denominator formula.
//Fraction Reduction: The greatest common divisor (GCD) is used to reduce the fraction to its simplest form.
//Final Output: The final result is returned in the format numerator/denominator.


//Given a string expression representing an expression of fraction addition and subtraction, return the
// calculation result in string format.
//The final result should be an irreducible fraction. If your final result is an integer, change it to the format
// of a fraction that has a denominator 1. So in this case, 2 should be converted to 2/1.
//
//Example 1:
//Input: expression = "-1/2+1/2"
//Output: "0/1"

//Example 2:
//Input: expression = "-1/2+1/2+1/3"
//Output: "1/3"

//Example 3:
//Input: expression = "1/3-1/2"
//Output: "-1/6"
//
//Constraints:
//The input string only contains '0' to '9', '/', '+' and '-'. So does the output.
//Each fraction (input and output) has the format ±numerator/denominator. If the first input fraction or the
// output is positive, then '+' will be omitted.
//The input only contains valid irreducible fractions, where the numerator and denominator of each fraction will
// always be in the range [1, 10]. If the denominator is 1, it means this fraction is actually an integer in
// a fraction format defined above.
//The number of given fractions will be in the range [1, 10].
//The numerator and denominator of the final result are guaranteed to be valid and in the range of 32-bit int.