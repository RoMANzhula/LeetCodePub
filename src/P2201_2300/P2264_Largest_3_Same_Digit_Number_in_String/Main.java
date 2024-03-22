package P2201_2300.P2264_Largest_3_Same_Digit_Number_in_String;

public class Main {
    public static void main(String[] args) {
        String num1 = "6777133339";
        String num2 = "2300019";

        System.out.println(largestGoodInteger(num1));
    }

    public static String largestGoodInteger(String num) {
        String maxGoodInteger = "";

        for (int i = 0; i <= num.length() - 3; i++) {
            String substring = num.substring(i, i + 3);
            if (isGoodInteger(substring) && substring.compareTo(maxGoodInteger) > 0) {
                maxGoodInteger = substring;
            }
        }

        return maxGoodInteger;
    }

    private static boolean isGoodInteger(String s) {
        return s.charAt(0) == s.charAt(1) && s.charAt(1) == s.charAt(2);
    }

//    public static String largestGoodInteger(String num) {
//        int count = 1;
//        String result = "";
//
//        for (char ch : num.toCharArray()) {
//            for (int i = 0; i < num.toCharArray().length - 2; i++) {
//                if (ch == num.toCharArray()[i] && ch == num.toCharArray()[i + 1] && ch == num.toCharArray()[i + 2]) {
//                    count++;
//                    if (count == 2)
//                    result = result.concat(String.valueOf(ch)).concat(String.valueOf(ch)).concat(String.valueOf(ch));
//                }
//            }
//        }
//
//        return result;
//    }
}

//You are given a string num representing a large integer. An integer is good if it meets the following conditions:
//
//It is a substring of num with length 3.
//It consists of only one unique digit.
//Return the maximum good integer as a string or an empty string "" if no such integer exists.
//
//Note:
//
//A substring is a contiguous sequence of characters within a string.
//There may be leading zeroes in num or a good integer.
//
//
//Example 1:
//
//Input: num = "6777133339"
//Output: "777"
//Explanation: There are two distinct good integers: "777" and "333".
//"777" is the largest, so we return "777".
//Example 2:
//
//Input: num = "2300019"
//Output: "000"
//Explanation: "000" is the only good integer.
//Example 3:
//
//Input: num = "42352338"
//Output: ""
//Explanation: No substring of length 3 consists of only one unique digit. Therefore, there are no good integers.
//
//
//Constraints:
//
//3 <= num.length <= 1000
//num only consists of digits.
