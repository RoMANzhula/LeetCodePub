package P1601_1700.P1611_Minimum_One_Bit_Operations_to_Make_Integer_Zero;

public class Main {

    public static void main(String[] args) {
        int n1 = 3;
        System.out.println("Input: " + n1);
        System.out.println("Output: " + minOperations(n1));

        int n2 = 6;
        System.out.println("Input: " + n2);
        System.out.println("Output: " + minOperations(n2));
    }


    public static int minOperations(int n) {
        if (n == 0) {
            return 0;
        }

        int x = 1;
        while (x * 2 <= n) {
            x <<= 1;
        }

        return minOneBitOperations(n ^ (x | x >> 1)) + 1 + x - 1;
    }

    private static int minOneBitOperations(int n) {
        if (n == 0) {
            return 0;
        }

        int x = 1;
        while (x * 2 <= n) {
            x <<= 1;
        }

        return minOperations(n ^ (x | x >> 1)) + 1 + x - 1;
    }

}

//У цьому коді використовується рекурсивний підхід для вирішення завдання. Основна ідея полягає в тому, щоб
// кожен раз визначати найбільший біт (зліва) числа n і використовувати його для оптимізації операцій.
//Якщо n вже дорівнює 0, то повертаємо 0, оскільки операції не потрібні.
//Знаходимо найбільший біт x (зліва) в числі n. Це відбувається у циклі, де x збільшується удвічі, доки не
// стане більше або рівним n.
//Використовуючи значення x, ми застосовуємо певні бітові операції, щоб перетворити n у 0. Рекурсивно викликаємо
// ту ж саму функцію для знайомого типу операцій. Після рекурсивного виклику враховується кількість операцій, яку
// визначено у виразі 1 + x - 1.
//Основнафункція minOperations визначає мінімальну кількість операцій для перетворення n у 0.
//Цей підхід гарантує оптимальність, оскільки використовує маскуючі операції для обробки бітів числа та рекурсивний
// підхід для вирішення подібних підзадач.

//Given an integer n, you must transform it into 0 using the following operations any number of times:
//Change the rightmost (0th) bit in the binary representation of n.
//Change the ith bit in the binary representation of n if the (i-1)th bit is set to 1 and the (i-2)th through
// 0th bits are set to 0.
//Return the minimum number of operations to transform n into 0.

//Example 1:
//Input: n = 3
//Output: 2
//Explanation: The binary representation of 3 is "11".
//"11" -> "01" with the 2nd operation since the 0th bit is 1.
//"01" -> "00" with the 1st operation.

//Example 2:
//Input: n = 6
//Output: 4
//Explanation: The binary representation of 6 is "110".
//"110" -> "010" with the 2nd operation since the 1st bit is 1 and 0th through 0th bits are 0.
//"010" -> "011" with the 1st operation.
//"011" -> "001" with the 2nd operation since the 0th bit is 1.
//"001" -> "000" with the 1st operation.

//Constraints:
//0 <= n <= 109
