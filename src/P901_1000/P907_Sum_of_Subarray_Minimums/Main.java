package P901_1000.P907_Sum_of_Subarray_Minimums;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        int[] arr1 = {3, 1, 2, 4};
        int[] arr2 = {11, 81, 94, 43, 3};
        int[] arr3 = {71, 55, 82, 55};


        System.out.println("Output for arr1: " + sumSubarrayMins(arr1)); // Output: 17
        System.out.println("Output for arr2: " + sumSubarrayMins(arr2)); // Output: 444
        System.out.println("Output for arr3: " + sumSubarrayMins(arr3)); // Output: 593
    }

    public static int sumSubarrayMins(int[] arr) {
        int MOD = 1000000007;
        int n = arr.length;
        long result = 0;

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i <= n; i++) {
            while (!stack.isEmpty() && (i == n || arr[i] < arr[stack.peek()])) {
                int h = i == n ? 0 : arr[i];
                int j = stack.pop();
                int k = stack.isEmpty() ? -1 : stack.peek();
                result = (result + (long) arr[j] * (i - j) * (j - k)) % MOD;
            }
            stack.push(i);
        }

        return (int) result;
    }
}

//Задача полягає в обчисленні суми мінімальних елементів для всіх можливих
// підмасивів (підрядків) заданого масиву цілих чисел.
//
//В розв'язку використовується стек для зберігання індексів елементів масиву. Алгоритм обчислює внесок
// кожного елемента масиву до суми мінімальних елементів усіх підмасивів.
//
//Створюється стек stack для визначення попереднього та наступного менших елементів для кожного елемента.
//Проходимо по масиву зліва направо та знаходимо попередній менший елемент для кожного елемента за допомогою стеку.
//Проходимо по масиву справа наліво та знаходимо наступний менший елемент для кожного елемента за допомогою стеку.
//Обчислюємо внесок кожного елемента до суми мінімальних елементів усіх підмасивів за
// формулою (arr[j] * (i - j) * (j - k)), де i - індекс поточного елемента, j - індекс попереднього меншого
// елемента, k - індекс елемента перед попереднім меншим елементом.
//Підсумовуємо внески кожного елемента для отримання кінцевого результату.
//Цей підхід дозволяє ефективно знаходити суму мінімальних елементів для всіх підмасивів за лінійний час.

//Given an array of integers arr, find the sum of min(b), where b ranges over every (contiguous) subarray of arr. Since the answer may be large, return the answer modulo 109 + 7.
//
//
//
//Example 1:
//
//Input: arr = [3,1,2,4]
//Output: 17
//Explanation:
//Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
//Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
//Sum is 17.
//Example 2:
//
//Input: arr = [11,81,94,43,3]
//Output: 444
//
//
//Constraints:
//
//1 <= arr.length <= 3 * 104
//1 <= arr[i] <= 3 * 104