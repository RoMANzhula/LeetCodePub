package P401_500.P446_Arithmetic_Slices_ll_Subsequence;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        int[] nums1 = {2, 4, 6, 8, 10};
        System.out.println(numberOfArithmeticSlices(nums1)); // Output: 7

        int[] nums2 = {7, 7, 7, 7, 7};
        System.out.println(numberOfArithmeticSlices(nums2)); // Output: 16
    }

    public static int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        int result = 0;

        Map<Integer, Integer>[] dp = new HashMap[n];

        for (int i = 0; i < n; i++) {
            dp[i] = new HashMap<>();
            for (int j = 0; j < i; j++) {
                long diff = (long) nums[i] - nums[j];

                if (diff >= Integer.MIN_VALUE && diff <= Integer.MAX_VALUE) {
                    int dpj = dp[j].getOrDefault((int) diff, 0);
                    dp[i].put((int) diff, dp[i].getOrDefault((int) diff, 0) + dpj + 1);
                    result += dpj;
                }
            }
        }

        return result;
    }
}

//Задача полягає в тому, щоб знайти кількість арифметичних підпослідовностей в заданому масиві цілих
// чисел. Послідовність вважається арифметичною, якщо вона складається щонайменше з трьох елементів, і
// різниця між будь-якими двома сусідніми елементами однакова.
//
//Ми вирішуємо цю задачу за допомогою динамічного програмування. Створюємо двовимірний масив dp, де
// dp[i][j] представляє кількість арифметичних підпослідовностей, які закінчуються на індексі j та мають різницю i.
//
//Потім ми проходимо всі пари індексів i та j в масиві та обчислюємо різницю між відповідними елементами. Якщо
// різниця знаходиться в допустимому діапазоні, то ми збільшуємо кількість арифметичних підпослідовностей та
// оновлюємо значення dp[i][j]. При цьому також збільшуємо результат на значення dp[j][різниця].
//
//Щоб уникнути перевищення області індексації масиву, ми використовуємо HashMap для збереження значень dp. Це
// дозволяє нам динамічно збільшувати розмір масиву, який використовується для конкретної різниці.
//
//У кінці обчислень сумарна кількість арифметичних підпослідовностей знаходиться у змінній result, яку ми
// повертаємо як результат функції.


//Given an integer array nums, return the number of all the arithmetic subsequences of nums.
//
//A sequence of numbers is called arithmetic if it consists of at least three elements and if the difference
// between any two consecutive elements is the same.
//
//For example, [1, 3, 5, 7, 9], [7, 7, 7, 7], and [3, -1, -5, -9] are arithmetic sequences.
//For example, [1, 1, 2, 5, 7] is not an arithmetic sequence.
//A subsequence of an array is a sequence that can be formed by removing some elements (possibly none) of the array.
//
//For example, [2,5,10] is a subsequence of [1,2,1,2,4,1,5,10].
//The test cases are generated so that the answer fits in 32-bit integer.

//Example 1:
//Input: nums = [2,4,6,8,10]
//Output: 7
//Explanation: All arithmetic subsequence slices are:
//[2,4,6]
//[4,6,8]
//[6,8,10]
//[2,4,6,8]
//[4,6,8,10]
//[2,4,6,8,10]
//[2,6,10]

//Example 2:
//Input: nums = [7,7,7,7,7]
//Output: 16
//Explanation: Any subsequence of this array is arithmetic.

//Constraints:
//1  <= nums.length <= 1000
//-231 <= nums[i] <= 231 - 1
