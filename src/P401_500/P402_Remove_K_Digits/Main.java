package P401_500.P402_Remove_K_Digits;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.removeKdigits("1432219", 3)); // Output: "1219"
        System.out.println(solution.removeKdigits("10200", 1));   // Output: "200"
        System.out.println(solution.removeKdigits("10", 2));      // Output: "0"
    }

//    public String removeKdigits(String num, int k) {
//        if (num.length() == k) return "0"; // if k is equal to the length of num, return "0"
//
//        Stack<Character> stack = new Stack<>();
//
//        // iterate through each digit in num
//        for (char digit : num.toCharArray()) {
//            // while k is greater than 0 and the stack is not empty and the top of the stack is greater than the current digit
//            while (k > 0 && !stack.isEmpty() && stack.peek() > digit) {
//                stack.pop(); // remove the top of the stack
//                k--; // decrement k
//            }
//            stack.push(digit); // push the current digit onto the stack
//        }
//
//        // remove the remaining k digits from the end of the stack
//        for (int i = 0; i < k; i++) {
//            stack.pop();
//        }
//
//        // construct the result string from the stack
//        StringBuilder result = new StringBuilder();
//        while (!stack.isEmpty()) {
//            result.insert(0, stack.pop()); // insert each digit at the beginning of the resolt string
//        }
//
//        // remove leading zeros from the result string
//        while (result.length() > 1 && result.charAt(0) == '0') {
//            result.deleteCharAt(0);
//        }
//
//        return result.toString();
//    }

    //faster solve
    public String removeKdigits(String num, int k) {
        if (num.length() == k) return "0"; // if k is equal to the length of num, return "0"

        StringBuilder result = new StringBuilder();

        // iterate through each digit in num
        for (char digit : num.toCharArray()) {
            // while k is greater than 0 and result is not empty and the last digit of result is greater than the current digit
            while (k > 0 && result.length() > 0 && result.charAt(result.length() - 1) > digit) {
                result.deleteCharAt(result.length() - 1); // remove the last digit of result
                k--; // decrement k
            }
            result.append(digit); // append the current digit to result
        }

        // remove the remaining k digits from the end of result
        result.setLength(result.length() - k);

        // remove leading zeros from result
        while (result.length() > 1 && result.charAt(0) == '0') {
            result.deleteCharAt(0);
        }

        return result.toString(); // bingo
    }
}

//Ця програма призначена для вирішення задачі видалення k цифр з числа таким чином, щоб отримати найменше можливе число.
//Ми використовуємо цикл, щоб пройтися по кожній цифрі у вхідному числі. Під час проходження ми перевіряємо, чи можемо
// ми видалити якісь цифри, щоб зменшити число. Це робиться шляхом порівняння поточної цифри з попередньою цифрою.
// Якщо поточна цифра менша за попередню і у нас ще є можливість видалити цифри (k > 0), ми видаляємо попередню цифру.
//Оптимізована версія програми використовує StringBuilder, щоб уникнути використання стеку, що дозволяє прискорити
// роботу програми. Після видалення k цифр ми перевіряємо, чи є передовідною нулі, які потрібно видалити.

//Given string num representing a non-negative integer num, and an integer k, return the smallest possible integer
// after removing k digits from num.
//
//Example 1:
//Input: num = "1432219", k = 3
//Output: "1219"
//Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.

//Example 2:
//Input: num = "10200", k = 1
//Output: "200"
//Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.

//Example 3:
//Input: num = "10", k = 2
//Output: "0"
//Explanation: Remove all the digits from the number and it is left with nothing which is 0.
//
//Constraints:
//1 <= k <= num.length <= 105
//num consists of only digits.
//num does not have any leading zeros except for the zero itself.
