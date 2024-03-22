package P201_300.P238_Product_of_Array_Except_Self;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 2, 3, 4};
        int[] nums2 = {-1, 1, 0, -3, 3};

        int[] result1 = solution.productExceptSelf(nums1);
        int[] result2 = solution.productExceptSelf(nums2);

        // Output [24,12,8,6]
        System.out.println("Output for nums1: ");
        for (int num : result1) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Output: [0,0,9,0,0]
        System.out.println("Output for nums2: ");
        for (int num : result2) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] prefixProducts = new int[n];
        int[] suffixProducts = new int[n];
        int[] result = new int[n];

        // Calculate prefix products
        prefixProducts[0] = 1;
        for (int i = 1; i < n; i++) {
            prefixProducts[i] = prefixProducts[i - 1] * nums[i - 1];
        }

        // Calculate suffix products
        suffixProducts[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            suffixProducts[i] = suffixProducts[i + 1] * nums[i + 1];
        }

        // Calculate result
        for (int i = 0; i < n; i++) {
            result[i] = prefixProducts[i] * suffixProducts[i];
        }

        return result;
    }
}

//Задача полягає в тому, щоб знайти для кожного елементу масиву добуток всіх елементів масиву, окрім самого
// цього елемента. Ми можжемо вирішити цю задачу за O(n) часу і без використання операції ділення.
//Один із способів розв'язання - використання попередніх та наступних добутків. Ми можемо обчислити добутки
// елементів, які знаходяться перед кожним елементом та добутки елементів, які знаходяться після кожного
// елемента. Потім просто помножимо відповідні попередні та наступні добутки, щоб отримати результат.
//Отже, ми обчислюємо два допоміжних масиви: один для попередніх добутків (prefixProducts) та один для
// наступних добутків (suffixProducts). Потім перемножимо відповідні елементи цих масивів для отримання результату.
//Цей підхід гарантує виконання у часовому порядку O(n) без використання операції ділення, що відповідає умовам задачі.

//Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the
// elements of nums except nums[i].
//The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
//You must write an algorithm that runs in O(n) time and without using the division operation.
//
//Example 1:
//Input: nums = [1,2,3,4]
//Output: [24,12,8,6]

//Example 2:
//Input: nums = [-1,1,0,-3,3]
//Output: [0,0,9,0,0]
//
//Constraints:
//2 <= nums.length <= 105
//-30 <= nums[i] <= 30
//The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
