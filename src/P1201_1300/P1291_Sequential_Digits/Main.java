package P1201_1300.P1291_Sequential_Digits;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Example 1
        int low1 = 100, high1 = 300;
        System.out.println(sequentialDigits(low1, high1)); // output [123,234]

        // Example 2
        int low2 = 1000, high2 = 13000;
        System.out.println(sequentialDigits(low2, high2)); // output [1234,2345,3456,4567,5678,6789,12345]
    }

    public static List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result = new ArrayList<>();

        for (int digit = 1; digit <= 9; digit++) {
            int num = digit;
            int nextDigit = digit + 1;

            while (num <= high && nextDigit <= 9) {
                num = num * 10 + nextDigit;
                if (num >= low && num <= high) {
                    result.add(num);
                }
                nextDigit++;
            }
        }

        result.sort(null);
        return result;
    }
}

//Мета задачі полягає в тому, щоб знайти всі цілі числа в заданому діапазоні, у яких цифри у числі є
// послідовними (кожна цифра більша на одиницю від попередньої).
//
//Ми використовуємо два вкладені цикли. Перший цикл проходить починаючи від кожної цифри від 1 до 9, а
// другий цикл додає наступні цифри до числа, поки вони не стануть більшими за 9 або поки число не
// виходить за верхню межу діапазону. Якщо число потрапляє в заданий діапазон, ми додаємо його до результату.
//
//На кожному кроці ми також перевіряємо, чи воно не менше нижньої межі діапазону, оскільки ми шукаємо
// лише числа в цьому діапазоні.
//
//В кінці програма сортує результат і повертає відсортований список цілих чисел з послідовними цифрами.


//An integer has sequential digits if and only if each digit in the number is one more than the previous digit.
//Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.

//Example 1:
//Input: low = 100, high = 300
//Output: [123,234]

//Example 2:
//Input: low = 1000, high = 13000
//Output: [1234,2345,3456,4567,5678,6789,12345]

//Constraints:
//10 <= low <= high <= 10^9
