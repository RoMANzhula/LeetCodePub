package P2966_Divide_Array_Into_Arrays_With_Max_Difference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[] nums1 = {1, 3, 4, 8, 7, 9, 3, 5, 1};
        int k1 = 2;
        printArray(divideArray(nums1, k1)); // output [[1,1,3],[3,4,5],[7,8,9]]

        int[] nums2 = {1, 3, 3, 2, 7, 3};
        int k2 = 3;
        printArray(divideArray(nums2, k2)); // output []

        int[] nums3 = {15,13,12,13,12,14,12,2,3,13,12,14,14,13,5,12,12,2,13,2,2};
        int k3 = 2;
        printArray(divideArray(nums3, k3)); // output []
    }

    private static void printArray(int[][] array) {
        for (int[] row : array) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static int[][] divideArray(int[] nums, int k) {
        Arrays.sort(nums);
        List<int[]> result = new ArrayList<>();

        for (int i = 0; i < nums.length; i += 3) {
            if (i + 2 < nums.length && nums[i + 2] - nums[i] <= k) {
                int[] currentArray = {nums[i], nums[i + 1], nums[i + 2]};
                result.add(currentArray);
            } else {
                // If it's not possible to create an array with the given conditions, return an empty array.
                return new int[0][];
            }
        }

        return result.toArray(new int[0][]);

    }
}

//Задача передбачає поділ масиву чисел nums на набори розміром 3 з такими умовами: кожен елемент повинен
// належати рівно одному набору, а різниця між будь-якими двома елементами в одному наборі повинна
// бути менше або дорівнювати k.
//
//Основна ідея у сортуванні масиву та розгляді наборів по 3 елементи з послідовними значеннями. Якщо
// різниця між першим і останнім елементами в наборі менше або дорівнює k, то такий набір можливий ійого можна
// додати до результату. Якщо такого набору не знайдено, повертається порожній масив, оскільки умови не виконані.
//
//Наприклад, для вхідного масиву nums = [1,3,4,8,7,9,3,5,1] і k = 2:
//
//Сортуємо масив: [1, 1, 3, 3, 4, 5, 7, 8, 9].
//Розглядаємо набори по 3 елементи: [1, 1, 3], [3, 4, 5], [7, 8, 9].
//У кожному наборі різниця між елементами менше або дорівнює k, тому ці набори підходять.
//Повертаємо результат: [[1, 1, 3], [3, 4, 5], [7, 8, 9]].
//Для випадку nums = [1,3,3,2,7,3] і k = 3 не існує наборів, які задовольняли б умови, тому результат - порожній масив.


//You are given an integer array nums of size n and a positive integer k.
//Divide the array into one or more arrays of size 3 satisfying the following conditions:
//Each element of nums should be in exactly one array.
//The difference between any two elements in one array is less than or equal to k.
//Return a 2D array containing all the arrays. If it is impossible to satisfy the conditions, return an
// empty array. And if there are multiple answers, return any of them.

//Example 1:
//Input: nums = [1,3,4,8,7,9,3,5,1], k = 2
//Output: [[1,1,3],[3,4,5],[7,8,9]]
//Explanation: We can divide the array into the following arrays: [1,1,3], [3,4,5] and [7,8,9].
//The difference between any two elements in each array is less than or equal to 2.
//Note that the order of elements is not important.

//Example 2:
//Input: nums = [1,3,3,2,7,3], k = 3
//Output: []
//Explanation: It is not possible to divide the array satisfying all the conditions.

//Constraints:
//n == nums.length
//1 <= n <= 105
//n is a multiple of 3.
//1 <= nums[i] <= 105
//1 <= k <= 105
