package P1201_1300.P1249_Minimum_Remove_to_Make_Valid_Parentheses;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.minRemoveToMakeValid("lee(t(c)o)de)")); // Output: "lee(t(c)o)de"
        System.out.println(solution.minRemoveToMakeValid("a)b(c)d")); // Output: "ab(c)d"
        System.out.println(solution.minRemoveToMakeValid("))((")); // Output: ""
    }

    public String minRemoveToMakeValid(String s) {
        StringBuilder sb = new StringBuilder(s);
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '(') {
                stack.push(i);
            } else if (sb.charAt(i) == ')') {
                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    sb.setCharAt(i, '*');
                }
            }
        }

        while (!stack.isEmpty()) {
            sb.setCharAt(stack.pop(), '*');
        }

        return sb.toString().replaceAll("\\*", "");
    }
}

//Задача полягає в тому, щоб видалити мінімальну кількість дужок '(' або ')', щоб результуючий рядок був
// допустимим. Допустимий рядок дужок має такі властивості:
//Це порожній рядок.
//Він містить лише малі літери.
//Він може бути записаний як AB, де A і B - допустимі рядки, або
//Він може бути записаний як (A), де A - допустимий рядок.
//Один з швидких способів це зробити - використовувати стек. Ми проходимося по рядку і для кожної дужки '(' додаємо
// її індекс в стек. Коли зустрічаємо дужку ')', перевіряємо, чи є в стеку відповідна дужка '('. Якщо так, видаляємо
// відповідну дужку зі стеку. Якщо ні, ми відзначаємо цю дужку для видалення, замінюючи її на '*'.
//Після того, як ми обробимо всі дужки, ми перевіряємо залишився лише валідний рядок з малих літер та
// дужок '(' і ')'. Ми видаляємо всі зазначені символи '*' і повертаємо результат.
//Це виконується у функції minRemoveToMakeValid. Вона проходиться по рядку, використовуючи стек, для визначення
// недопустимих дужок та заміни їх на '' символи. Після цього вона видаляє всі символи '' і повертає валідний рядок.

//Given a string s of '(' , ')' and lowercase English characters.
//Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting
// parentheses string is valid and return any valid string.
//Formally, a parentheses string is valid if and only if:
//It is the empty string, contains only lowercase characters, or
//It can be written as AB (A concatenated with B), where A and B are valid strings, or
//It can be written as (A), where A is a valid string.
//
//Example 1:
//Input: s = "lee(t(c)o)de)"
//Output: "lee(t(c)o)de"
//Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.

//Example 2:
//Input: s = "a)b(c)d"
//Output: "ab(c)d"

//Example 3:
//Input: s = "))(("
//Output: ""
//Explanation: An empty string is also valid.
//
//Constraints:
//1 <= s.length <= 105
//s[i] is either'(' , ')', or lowercase English letter.