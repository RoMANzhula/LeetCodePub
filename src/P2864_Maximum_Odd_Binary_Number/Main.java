package P2864_Maximum_Odd_Binary_Number;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.maximumOddBinaryNumber("010")); // output 001
        System.out.println(solution.maximumOddBinaryNumber("0101")); // output 1001
    }

//    public String maximumOddBinaryNumber(String s) {
//        int onesCount = s.length() - s.replace("1", "").length();
//        int zerosCount = s.length() - s.replace("0", "").length();
//        return "1".repeat(Math.max(0, onesCount - 1)) + "0".repeat(zerosCount) + "1";
//    }

    public String maximumOddBinaryNumber(String s) {
        int onesCount = 0;
        int zerosCount = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                onesCount++;
            } else {
                zerosCount++;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("1".repeat(Math.max(0, onesCount - 1)));
        sb.append("0".repeat(zerosCount));
        sb.append('1');

        return sb.toString();
    }
}

//Підрахунок кількості '1' та '0': Ми використовуємо цикл for, щоб пройтися по кожному символу у вхідному
// рядку s. Під час цього проходження ми перевіряємо, чи символ є '1' або '0'. Якщо він є '1', то збільшуємо
// лічильник onesCount, а якщо він є '0', то збільшуємо лічильник zerosCount. Це дозволяє нам ефективно підрахувати
// кількість '1' та '0' у рядку, пройшовшись по ньому тільки один раз.
//Побудова результату за допомогою StringBuilder: Після підрахунку кількості '1' та '0' ми створюємо об'єкт
// StringBuilder, який допоможе нам побудувати результуючий рядок. Додаємо '1' до початку рядка, оскільки
// це є обов'язковою умовою для парсера непарного числа в двійковій системі числення. Потім додаємо '0' для
// кожного '0' у вхідному рядку, що забезпечує, що всі '0' залишаються на своїх місцях. Нарешті,
// додаємо '1' в кінець рядка.
//Повернення результуючого рядка: Після побудови результуючого рядка за допомогою об'єкта StringBuilder ми
// використовуємо метод toString(), щоб отримати результуючий рядок та повернути його як результат функції.

//You are given a binary string s that contains at least one '1'.
//You have to rearrange the bits in such a way that the resulting binary number is the maximum odd binary
// number that can be created from this combination.
//Return a string representing the maximum odd binary number that can be created from the given combination.
//Note that the resulting string can have leading zeros.

//Example 1:
//Input: s = "010"
//Output: "001"
//Explanation: Because there is just one '1', it must be in the last position. So the answer is "001".

//Example 2:
//Input: s = "0101"
//Output: "1001"
//Explanation: One of the '1's must be in the last position. The maximum number that can be made with
// the remaining digits is "100". So the answer is "1001".
//
//Constraints:
//1 <= s.length <= 100
//s consists only of '0' and '1'.
//s contains at least one '1'.
