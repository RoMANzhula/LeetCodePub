package P1_100.P43_Multiply_Strings;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.multiply("2", "3"));       // Output: 6
        System.out.println(solution.multiply("123", "456"));   // Output: 56088
    }

    public String multiply(String num1, String num2) {
        int n = num1.length(), m = num2.length();
        int[] result = new int[n + m]; // max possible length of result

        // reverse multiply digits
        for (int i = n - 1; i >= 0; i--) {
            int digit1 = num1.charAt(i) - '0';

            for (int j = m - 1; j >= 0; j--) {
                int digit2 = num2.charAt(j) - '0';

                int product = digit1 * digit2;
                int posLow = i + j + 1;
                int posHigh = i + j;

                product += result[posLow]; // add to current digit

                result[posLow] = product % 10;
                result[posHigh] += product / 10;
            }
        }

        // convert result array to string
        StringBuilder sb = new StringBuilder();
        for (int num : result) {
            if (sb.length() == 0 && num == 0) continue; // skip leading zeros

            sb.append(num);
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }

}

//Complexity:
// time - O(n * m)
// space - O(n + m)


//Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also
// represented as a string.
//Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.

//Example 1:
//Input: num1 = "2", num2 = "3"
//Output: "6"

//Example 2:
//Input: num1 = "123", num2 = "456"
//Output: "56088"

//Constraints:
//1 <= num1.length, num2.length <= 200
//num1 and num2 consist of digits only.
//Both num1 and num2 do not contain any leading zero, except the number 0 itself.
