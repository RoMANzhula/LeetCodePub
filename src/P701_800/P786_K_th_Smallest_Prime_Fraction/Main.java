package P701_800.P786_K_th_Smallest_Prime_Fraction;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] arr1 = {1, 2, 3, 5};
        int k1 = 3;
        System.out.println(Arrays.toString(solution.kthSmallestPrimeFraction(arr1, k1))); // output: [2, 5]

        int[] arr2 = {1, 7};
        int k2 = 1;
        System.out.println(Arrays.toString(solution.kthSmallestPrimeFraction(arr2, k2))); // output: [1, 7]
    }

    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int n = arr.length;
        double left = 0, right = 1;
        int[] result = new int[2];

        while (left < right) {
            double mid = (left + right) / 2;
            int count = 0;
            int numerator = 0;
            int denominator = 1;
            int j = 0;
            for (int i = 0; i < n; i++) {
                while (j < n && arr[i] > mid * arr[j]) {
                    j++;
                }
                count += (n - j);
                if (j < n && numerator * arr[j] < arr[i] * denominator) {
                    numerator = arr[i];
                    denominator = arr[j];
                }
            }
            if (count < k) {
                left = mid;
            } else if (count > k) {
                right = mid;
            } else {
                result[0] = numerator;
                result[1] = denominator;
                return result;
            }
        }

        double maxFraction = 0;
        for (int i = 0; i < n; i++) {
            int numerator = arr[i];
            int index = Arrays.binarySearch(arr, (int) (numerator / right));
            if (index < 0) {
                index = -index - 2;
            }
            if (index >= 0 && index < n) {
                double fraction = (double) numerator / arr[index];
                if (fraction > maxFraction) {
                    maxFraction = fraction;
                    result[0] = numerator;
                    result[1] = arr[index];
                }
            }
        }

        return result;
    }
}

//У цій задачі ми шукаємо
//k-ту найменшу дробу серед всіх можливих дробів, що можуть бути утворені з чисел масиву arr. Масив arr вже
// відсортований, тому ми можемо використовувати це для спрощення розв'язку.
//Основна ідея полягає в використанні бінарного пошуку для знаходження цільного значення, яке є можливим значенням
// дробу, і потім використовуємо двійковий пошук для знаходження самих чисел, які створюють цю дробу.
//Ми використовуємо двійковий пошук для знаходження значення, що задовольняє кількість дробів, менших або рівних
// за кількість k. Це значення є потенційним значенням для нашої k-тій найменшій дробі.
//За допомогою цього значення, ми обчислюємо кількість дробів, що менше або рівні цьому значенню. Якщо кількість
// дробів менше за k, то ми збільшуємо значення і виконуємо наступний крок. Якщо кількість дробів більша за k, то
// ми зменшуємо значення і виконуємо наступний крок. Якщо кількість дробів дорівнює k, ми повертаємо пару чисел, що
// утворюють цю дробу.
//Коли ми знаходимо потрібне значення, ми застосовуємо ще один двійковий пошук для знаходження чисел, які
// утворюють цю дробу.

//You are given a sorted integer array arr containing 1 and prime numbers, where all the integers of arr are
// unique. You are also given an integer k.
//For every i and j where 0 <= i < j < arr.length, we consider the fraction arr[i] / arr[j].
//Return the kth smallest fraction considered. Return your answer as an array of integers of size 2, where
// answer[0] == arr[i] and answer[1] == arr[j].
//
//Example 1:
//Input: arr = [1,2,3,5], k = 3
//Output: [2,5]
//Explanation: The fractions to be considered in sorted order are:
//1/5, 1/3, 2/5, 1/2, 3/5, and 2/3.
//The third fraction is 2/5.

//Example 2:
//Input: arr = [1,7], k = 1
//Output: [1,7]
//
//Constraints:
//2 <= arr.length <= 1000
//1 <= arr[i] <= 3 * 104
//arr[0] == 1
//arr[i] is a prime number for i > 0.
//All the numbers of arr are unique and sorted in strictly increasing order.
//1 <= k <= arr.length * (arr.length - 1) / 2