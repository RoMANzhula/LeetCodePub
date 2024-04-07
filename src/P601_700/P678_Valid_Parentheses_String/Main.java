package P601_700.P678_Valid_Parentheses_String;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.checkValidString("()"));     // true
        System.out.println(solution.checkValidString("(*)"));    // true
        System.out.println(solution.checkValidString("(*))"));   // true
    }

    public boolean checkValidString(String s) {
        Stack<Integer> leftParenStack = new Stack<>();
        Stack<Integer> starStack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                leftParenStack.push(i);
            } else if (c == '*') {
                starStack.push(i);
            } else {
                if (!leftParenStack.isEmpty()) {
                    leftParenStack.pop();
                } else if (!starStack.isEmpty()) {
                    starStack.pop();
                } else {
                    return false;
                }
            }
        }

        while (!leftParenStack.isEmpty() && !starStack.isEmpty()) {
            if (leftParenStack.pop() > starStack.pop()) {
                return false;
            }
        }

        return leftParenStack.isEmpty();
    }

}

//Задача полягає у визначенні того, чи є рядок допустимим згідно з умовами задачі. У рядку можуть зустрічатися тільки
// три типи символів: '(', ')', і '*'. Допустимий рядок має відповідати наступним умовам:
//Будь-яка ліва дужка '(' має відповідати правій дужці ')'.
//Будь-яка права дужка ')' має відповідати лівій дужці '('.
//Ліва дужка '(' має стояти перед відповідною правою дужкою ')'.
//Символ '*' може інтерпретуватися як одна права дужка ')', одна ліва дужка '(' або пустий рядок "".
//Для вирішення цієї задачі використовується стек для відстеження позицій лівих дужок і '*' в рядку. Алгоритм
// працює наступним чином:
//Проходимося по рядку.
//Якщо зустрічаємо '(' - додаємо його позицію до стеку лівих дужок.
//Якщо зустрічаємо '' - додаємо його позицію до стеку ''.
//Якщо зустрічаємо ')':
//Якщо стек лівих дужок не порожній - видаляємо верхній елемент (позицію відповідної лівої дужки).
//Інакше, якщо стек '' не порожній - видаляємо верхній елемент (позицію '').
//Якщо обидва стеки порожні - рядок недопустимий і повертаємо false.
//Після проходження рядка, перевіряємо стеки на наявність відповідних елементів.
//Якщо у стеку лівих дужок залишилися елементи, а в стеку '' немає відповідних '' для кожної лівої дужки - рядок
// також недопустимий і повертаємо false.
//В іншому випадку, якщо стек лівих дужок порожній - рядок допустимий і повертаємо true.


//Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.
//The following rules define a valid string:
//Any left parenthesis '(' must have a corresponding right parenthesis ')'.
//Any right parenthesis ')' must have a corresponding left parenthesis '('.
//Left parenthesis '(' must go before the corresponding right parenthesis ')'.
//'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".
//
//Example 1:
//Input: s = "()"
//Output: true

//Example 2:
//Input: s = "(*)"
//Output: true

//Example 3:
//Input: s = "(*))"
//Output: true
//
//Constraints:
//1 <= s.length <= 100
//s[i] is '(', ')' or '*'.
