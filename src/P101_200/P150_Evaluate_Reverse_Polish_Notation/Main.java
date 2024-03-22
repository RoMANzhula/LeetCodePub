package P101_200.P150_Evaluate_Reverse_Polish_Notation;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        String[] tokens1 = {"2", "1", "+", "3", "*"};
        System.out.println(evalRPN(tokens1));  // Output: 9

        String[] tokens2 = {"4", "13", "5", "/", "+"};
        System.out.println(evalRPN(tokens2));  // Output: 6

        String[] tokens3 = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        System.out.println(evalRPN(tokens3));  // Output: 22

        {
            int sum = 0;
            for (int i = 0, j = 10; i < 5 && j > 5; ++i, --j) {
                sum += i + j;
            }
            System.out.println(sum);
        }
    }

    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            if (isOperator(token)) {
                var operand2 = stack.pop();
                var operand1 = stack.pop();
                var result = applyOperator(token, operand1, operand2);
                stack.push(result);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }

    private static boolean isOperator(String token) {
        return  token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    private static int applyOperator(String operator, int operand1, int operand2) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}

//Задача полягає в оцінці арифметичного виразу в оберненій польській нотації (Reverse Polish Notation, RPN). В RPN
// оператори записуються після своїх операндів, що спрощує обчислення виразів.
//
//Алгоритм використовує стек для збереження операндів та виконання операцій, коли зустрічає оператори в
// вхідному масиві. Основні кроки реалізації на Java:
//
//Створюємо стек для зберігання операндів.
//Проходимо по кожному елементу вхідного масиву:
//Якщо елемент - оператор, виймаємо два останніх операнди зі стеку, виконуємо відповідну операцію та результат
// поміщаємо назад у стек.
//Якщо елемент - операнд, поміщаємо його у стек.
//Результатом буде єдиний елемент, залишений у стеці - це оцінка виразу.
//Також, ми визначаємо методи isOperator для перевірки, чи є елемент оператором, і applyOperator для застосування
// оператора до двох операндів.


//You are given an array of strings tokens that represents an arithmetic expression in a Reverse Polish Notation.
//Evaluate the expression. Return an integer that represents the value of the expression.
//Note that:
//The valid operators are '+', '-', '*', and '/'.
//Each operand may be an integer or another expression.
//The division between two integers always truncates toward zero.
//There will not be any division by zero.
//The input represents a valid arithmetic expression in a reverse polish notation.
//The answer and all the intermediate calculations can be represented in a 32-bit integer.

//Example 1:
//Input: tokens = ["2","1","+","3","*"]
//Output: 9
//Explanation: ((2 + 1) * 3) = 9

//Example 2:
//Input: tokens = ["4","13","5","/","+"]
//Output: 6
//Explanation: (4 + (13 / 5)) = 6

//Example 3:
//Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
//Output: 22
//Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
//= ((10 * (6 / (12 * -11))) + 17) + 5
//= ((10 * (6 / -132)) + 17) + 5
//= ((10 * 0) + 17) + 5
//= (0 + 17) + 5
//= 17 + 5
//= 22

//Constraints:
//1 <= tokens.length <= 104
//tokens[i] is either an operator: "+", "-", "*", or "/", or an integer in the range [-200, 200].
