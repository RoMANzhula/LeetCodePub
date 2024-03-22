package P601_700.P645_Set_Mismatch;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2, 4}; // output [2,3]
        int[] nums2 = {1, 1}; // output [1,2]
        int[] nums3 = {2, 2}; // output [2,1]

        System.out.println(Arrays.toString(findErrorNums(nums1)));
        System.out.println(Arrays.toString(findErrorNums(nums2)));
        System.out.println(Arrays.toString(findErrorNums(nums3)));

    }

    public static int[] findErrorNums(int[] nums) {
        int n = nums.length;
        int[] result = new int[2];

        // Iterate through each element in the array
        for (int i = 0; i < n; i++) {
            int index = Math.abs(nums[i]) - 1;

            // If the corresponding element at index is negative, it means we have found the duplicate
            if (nums[index] < 0) {
                result[0] = Math.abs(nums[i]);
            } else {
                // Mark the element at index as negative to indicate that it has been visited
                nums[index] = -nums[index];
            }
        }

        // Iterate through the array again to find the missing number
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                result[1] = i + 1;
                break;
            }
        }

        return result;
    }
}

//Задача полягає в тому, щоб знайти число, яке повторюється у заданому масиві, і число, яке пропущено від 1 до n.
//
//У виправленому підході використовується ідея зміни значень в самому масиві для визначення повторюваних
// та пропущених чисел.
//
//Пошук повторюваного числа:
//Проходимося по елементам масиву. Для кожного елемента визначаємо індекс, в якому має знаходитися відповідне
// число (індексація від 1 до n). Якщо елемент вже має від'ємне значення, то ми знаходимо повторюючеся число.
//
//Пошук пропущеного числа:
//Знову проходимося по масиву. Якщо зустрічаємо позитивний елемент, то відповідне число (індекс + 1) є пропущеним.
//
//Таким чином, в результаті роботи алгоритму ми отримуємо два числа: повторюючеся та пропущене, і повертаємо їх
// у вигляді масиву. Це рішення ефективно використовує масив для зберігання інформації про присутність та
// відсутність чисел у масиві.


//You have a set of integers s, which originally contains all the numbers from 1 to n. Unfortunately, due to
// some error, one of the numbers in s got duplicated to another number in the set, which results in repetition
// of one number and loss of another number.
//
//You are given an integer array nums representing the data status of this set after the error.
//
//Find the number that occurs twice and the number that is missing and return them in the form of an array.

//Example 1:
//Input: nums = [1,2,2,4]
//Output: [2,3]

//Example 2:
//Input: nums = [1,1]
//Output: [1,2]

//Constraints:
//2 <= nums.length <= 104
//1 <= nums[i] <= 104
