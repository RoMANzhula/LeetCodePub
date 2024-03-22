package P1001_1100.P1043_Partition_Array_for_Maximum_Sum;

public class Main {
    public static void main(String[] args) {
        int[] arr1 = {1, 15, 7, 9, 2, 5, 10};
        int k1 = 3;
        System.out.println(maxSumAfterPartitioning(arr1, k1)); // Output: 84

        int[] arr2 = {1, 4, 1, 5, 7, 3, 6, 1, 9, 9, 3};
        int k2 = 4;
        System.out.println(maxSumAfterPartitioning(arr2, k2)); // Output: 83

        int[] arr3 = {1};
        int k3 = 1;
        System.out.println(maxSumAfterPartitioning(arr3, k3)); // Output: 1
    }

    public static int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            int max = 0;
            for (int j = 1; j <= k && i - j + 1 >= 0; j++) {
                max = Math.max(max, arr[i - j + 1]);
                dp[i] = Math.max(dp[i], (i >= j ? dp[i - j] : 0) + max * j);
            }
        }

        return dp[n - 1];
    }
}

//Задача вирішується за допомогою динамічного програмування. Основна ідея полягає в тому, щоб розглядати
// кожен елемент масиву та розглядати всі можливі розбиття на підмасиви довжиною не більше k. Для кожного
// елементу обчислюється максимальна сума, яка може бути досягнута, враховуючи цей елемент та попередні елементи.


//Given an integer array arr, partition the array into (contiguous) subarrays of length at most k. After
// partitioning, each subarray has their values changed to become the maximum value of that subarray.
//Return the largest sum of the given array after partitioning. Test cases are generated so that the
// answer fits in a 32-bit integer.

//Example 1:
//Input: arr = [1,15,7,9,2,5,10], k = 3
//Output: 84
//Explanation: arr becomes [15,15,15,9,10,10,10]

//Example 2:
//Input: arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4
//Output: 83

//Example 3:
//Input: arr = [1], k = 1
//Output: 1

//Constraints:
//1 <= arr.length <= 500
//0 <= arr[i] <= 109
//1 <= k <= arr.length
