package P201_300.P201_Bitwisw_AND_of_Numbers_Range;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.rangeBitwiseAnd(5, 7)); // output: 4
        System.out.println(solution.rangeBitwiseAnd(0, 0)); // output: 0
        System.out.println(solution.rangeBitwiseAnd(1, 2147483647)); // output: 0
    }

    public int rangeBitwiseAnd(int left, int right) {
        int shift = 0;
        //find the common prefix of left and right
        while (left < right) {
            left >>= 1;
            right >>= 1;
            shift++;
        }

        return left << shift;
    }
}

//Ця задача передбачає використання побітової операції І ("AND") для всіх чисел у заданому діапазоні
// включно. Основна ідея полягає у виявленні спільного префіксу для лівого та правого країв діапазону, оскільки
// саме в цій частині будуть зберігатися спільні біти для всіх чисел у діапазоні.
//
//Ось як працює це рішення:
//Ми виконуємо побітове зсування вправо для left і right до тих пір, поки вони не стануть рівними.
//Кожен раз, коли ми зсуваємо, ми збільшуємо лічильник shift.
//Коли left і right стають рівними, ми зафіксували спільний префікс. Потім ліве число зсуваємо на shift позицій
// вліво, щоб отримати результат.
//Це розв'язок ефективний, оскільки ми проводимо лише кілька ітерацій, щоб знайти спільний префікс, а потім
// використовуємо цей префікс для отримання відповіді. Таким чином, ми уникнули побітової операції AND на
// всіх числах у діапазоні, що дозволяє швидко обробити навіть великі діапазони чисел.

//Given two integers left and right that represent the range [left, right], return the bitwise AND of all
// numbers in this range, inclusive.

//Example 1:
//Input: left = 5, right = 7
//Output: 4

//Example 2:
//Input: left = 0, right = 0
//Output: 0

//Example 3:
//Input: left = 1, right = 2147483647
//Output: 0
//
//Constraints:
//0 <= left <= right <= 231 - 1
