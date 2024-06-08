package P501_600.P523_Continues_Subarray_Sum;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {23, 2, 4, 6, 7};
        int k1 = 6;
        System.out.println(solution.checkSubarraySum(nums1, k1)); // Output: true

        int[] nums2 = {23, 2, 6, 4, 7};
        int k2 = 6;
        System.out.println(solution.checkSubarraySum(nums2, k2)); // Output: true

        int[] nums3 = {23, 2, 6, 4, 7};
        int k3 = 13;
        System.out.println(solution.checkSubarraySum(nums3, k3)); // Output: false
    }

    public boolean checkSubarraySum(int[] nums, int k) {
        // Map to store the remainder and the index
        HashMap<Integer, Integer> map = new HashMap<>();
        // Initialize with remainder 0 at index -1 to handle edge cases
        map.put(0, -1);
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int remainder = sum % k;

            // To handle negative remainders, we adjust it to be within [0, k-1]
            if (remainder < 0) remainder += k;

            if (map.containsKey(remainder)) {
                // Check if the subarray length is at least 2
                if (i - map.get(remainder) > 1) {
                    return true;
                }
            } else {
                map.put(remainder, i);
            }
        }
        return false;
    }
}

//Основна ідея:
//Ми хочемо знайти підмасив (підрядок) в масиві чисел, сума елементів якого є кратною числу k. При цьому підмасив
// повинен мати довжину хоча б 2 елементи.
//Підхід:
//Використання префіксної суми та операції модуло (залишок від ділення):
//Префіксна сума до індексу i — це сума всіх елементів масиву від початку до індексу i.
//Ми використовуємо операцію модуло, щоб зберігати залишки від ділення префіксної суми на k. Якщо залишок від
// ділення префіксної суми на k однаковий для двох різних індексів i та j, то сума елементів між цими
// індексами буде кратною k.
//Використання хеш-таблиці (мапи):
//Зберігаємо перше входження кожного залишку в хеш-таблиці разом з відповідним індексом.
//Якщо знову зустрічаємо той самий залишок, і різниця між індексами більша або дорівнює 2, це означає, що ми
// знайшли підмасив, сума якого кратна k.
//Кроки алгоритму:
//Ініціалізуємо хеш-таблицю для зберігання залишків і їхніх індексів.
//Проходимо по масиву, обчислюючи префіксну суму на кожному кроці.
//Для кожної префіксної суми обчислюємо залишок від ділення на k.
//Перевіряємо, чи вже зустрічали такий залишок раніше:
//Якщо так, і різниця між індексами більша або дорівнює 2, повертаємо true (тобто знайшли підмасив).
//Якщо ні, додаємо залишок та індекс в хеш-таблицю.
//Якщо після проходження всього масиву не знайшли жодного підмасиву, що задовольняє умову, повертаємо false.
//Чому це працює?
//Використання префіксної суми та залишків від ділення дозволяє нам ефективно виявляти підмасиви з потрібними властивостями.
//Хеш-таблиця забезпечує швидкий доступ до збережених залишків та їхніх індексів, що робить алгоритм ефективним за часом.
//Ефективність:
//Цей підхід забезпечує лінійну складність O(n), де n — кількість елементів у масиві. Це досягається завдяки тому,
// що ми проходимо по масиву лише один раз і використовуємо хеш-таблицю для зберігання проміжних результатів.


//Given an integer array nums and an integer k, return true if nums has a good subarray or false otherwise.
//A good subarray is a subarray where:
//its length is at least two, and
//the sum of the elements of the subarray is a multiple of k.
//Note that:
//A subarray is a contiguous part of the array.
//An integer x is a multiple of k if there exists an integer n such that x = n * k. 0 is always a multiple of k.
//
//Example 1:
//Input: nums = [23,2,4,6,7], k = 6
//Output: true
//Explanation: [2, 4] is a continuous subarray of size 2 whose elements sum up to 6.

//Example 2:
//Input: nums = [23,2,6,4,7], k = 6
//Output: true
//Explanation: [23, 2, 6, 4, 7] is an continuous subarray of size 5 whose elements sum up to 42.
//42 is a multiple of 6 because 42 = 7 * 6 and 7 is an integer.

//Example 3:
//Input: nums = [23,2,6,4,7], k = 13
//Output: false
//
//Constraints:
//1 <= nums.length <= 105
//0 <= nums[i] <= 109
//0 <= sum(nums[i]) <= 231 - 1
//1 <= k <= 231 - 1