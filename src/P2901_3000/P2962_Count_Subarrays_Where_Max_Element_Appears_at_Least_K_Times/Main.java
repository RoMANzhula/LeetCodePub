package P2901_3000.P2962_Count_Subarrays_Where_Max_Element_Appears_at_Least_K_Times;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        // Example 1
        int[] nums1 = {1, 3, 2, 3, 3};
        int k1 = 2;
        System.out.println("Example 1 Output: " + solution.countSubarrays(nums1, k1)); // Output: 6

        // Example 2
        int[] nums2 = {1, 4, 2, 1};
        int k2 = 3;
        System.out.println("Example 2 Output: " + solution.countSubarrays(nums2, k2)); // Output: 0
    }

//    public long countSubarrays(int[] nums, int k) {
//        int maxNum = Arrays.stream(nums).max().getAsInt();
//        long ans = 0;
//        int count = 0;
//
//        for (int l = 0, r = 0; r < nums.length; ++r) {
//            if (nums[r] == maxNum)
//                ++count;
//            // keep the window to include k - 1 times of the maximum number
//            for (; count == k; ++l) {
//                if (nums[l] == maxNum) {
//                    --count;
//                }
//            }
//
//            ans += l;
//        }
//
//        return ans;
//    }

    //a little faster solve
    public long countSubarrays(int[] nums, int k) {
        int maxNum = Arrays.stream(nums).max().getAsInt();
        long ans = 0;
        int count = 0;
        int left = 0;
        int right = 0;

        while (right < nums.length) {
            if (nums[right] == maxNum)
                ++count;

            while (count == k) {
                ans += nums.length - right;
                if (nums[left] == maxNum)
                    --count;
                ++left;
            }

            ++right;
        }

        return ans;
    }

}

//Задача полягає в тому, щоб знайти кількість підмасивів (subarrays) у заданому масиві nums, в яких кількість
// входжень максимального елемента дорівнює k.
//Початковий код використовує два вказівника, l і r, щоб утримувати поточне вікно в масиві. Внутрішній цикл перевіряє,
// чи кількість входжень максимального елемента у вікні дорівнює k. Якщо так, він збільшує лічильник count. Якщо
// count дорівнює k, то цикл збільшує значення l (зміщує вікно вправо) та зменшує значення count, якщо елемент
// у вікні є максимальним.
//Але цей підхід не є найоптимальнішим, оскільки він потребує перевірки кожного елементу у вікні, що призводить до
// надмірної складності обчислення.
//Покращене рішення замінює внутрішній цикл на розрахунок кількості підмасивів без зайвих ітерацій. Ми
// використовуємо два вказівника, left і right, щоб визначити вікно у масиві. За допомогою вказівника right
// ми розширюємо вікно, додаючи елементи. Якщо кількість входжень максимального елемента у вікні дорівнює k, то
// ми додаємо до результату кількість підмасивів, які можуть бути сформовані у всьому масиві, починаючи з поточного
// right і закінчуючи останнім елементом масиву (це означає, що ми можемо включити будь-який елемент від right до
// кінця масиву у підмасиви, які мають кількість входжень максимального елемента дорівнює k). Потім ми зміщуємо
// вказівник left, щоб зменшити вікно, якщо це потрібно.
//Цей підхід дозволяє уникнути зайвих перевірок кожного елементу у вікні та зробити алгоритм більш ефективним.

//You are given an integer array nums and a positive integer k.
//Return the number of subarrays where the maximum element of nums appears at least k times in that subarray.
//A subarray is a contiguous sequence of elements within an array.
//
//Example 1:
//Input: nums = [1,3,2,3,3], k = 2
//Output: 6
//Explanation: The subarrays that contain the element 3 at least 2 times are:
// [1,3,2,3], [1,3,2,3,3], [3,2,3], [3,2,3,3], [2,3,3] and [3,3].

//Example 2:
//Input: nums = [1,4,2,1], k = 3
//Output: 0
//Explanation: No subarray contains the element 4 at least 3 times.
//
//Constraints:
//1 <= nums.length <= 105
//1 <= nums[i] <= 106
//1 <= k <= 105
