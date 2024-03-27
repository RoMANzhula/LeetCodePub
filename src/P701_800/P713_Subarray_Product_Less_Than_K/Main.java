package P701_800.P713_Subarray_Product_Less_Than_K;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {10, 5, 2, 6};
        int k1 = 100;
        System.out.println("Output for Example 1: " + solution.numSubarrayProductLessThanK(nums1, k1)); // Output: 8

        int[] nums2 = {1, 2, 3};
        int k2 = 0;
        System.out.println("Output for Example 2: " + solution.numSubarrayProductLessThanK(nums2, k2)); // Output: 0
    }


    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) return 0;

        int count = 0;
        int product = 1;
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            product *= nums[right];
            while (product >= k) {
                product /= nums[left++];
            }
            count += right - left + 1;
        }

        return count;
    }
}

//Ця задача вирішена за допомогою техніки ковзного вікна. Основна ідея полягає в тому, щоб тримати вікно, яке
// зберігає добуток всіх елементів у підмасиві, та розширювати його, доки добуток залишається меншим за k. Як
// тільки добуток перевищує k, ми зменшуємо вікно, видаляючи лівий елемент, доки добуток знову не стане меншим за k.
//Отже, ми маємо два показники, left та right, які вказують на початок і кінець поточного підмасиву. Ми рухаємо
// показник right вправо по масиву. Кожного разу, коли ми додаємо новий елемент до поточного вікна, ми множимо
// його на поточний добуток. Якщо цей добуток перевищує k, ми починаємо зменшувати розмір вікна, зсуваючи
// показник left вправо та ділячи добуток на значення лівого елемента вікна, поки добуток знову не стане меншим за k.
//Кількість допустимих підмасивів, які ми знайшли, дорівнює різниці між показниками right та left, доданою на 1, тому
// що кожен підмасив між ними також є допустимим.


//Given an array of integers nums and an integer k, return the number of contiguous subarrays where the product of
// all the elements in the subarray is strictly less than k.
//
//Example 1:
//Input: nums = [10,5,2,6], k = 100
//Output: 8
//Explanation: The 8 subarrays that have product less than 100 are:
//[10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6]
//Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.

//Example 2:
//Input: nums = [1,2,3], k = 0
//Output: 0
//
//Constraints:
//1 <= nums.length <= 3 * 104
//1 <= nums[i] <= 1000
//0 <= k <= 106
