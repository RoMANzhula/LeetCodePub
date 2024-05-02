package P2401_2500.P2441_Largest_Positive_Integer_That_Exists_With_Its_Negative;

import java.util.HashMap;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {-1, 2, -3, 3};
        int[] nums2 = {-1, 10, 6, 7, -7, 1};
        int[] nums3 = {-10, 8, 6, 7, -2, -3};

        System.out.println("Example 1: " + solution.findMaxK(nums1)); // Output: 3
        System.out.println("Example 2: " + solution.findMaxK(nums2)); // Output: 7
        System.out.println("Example 3: " + solution.findMaxK(nums3)); // Output: -1
    }

//    public int findMaxK(int[] nums) {
//        HashSet<Integer> set = new HashSet<>();
//        int max = -1;
//
//        for (int num : nums) {
//            if (set.contains(-num)) {
//                max = Math.max(max, Math.abs(num));
//            }
//            set.add(num);
//        }
//
//        return max;
//    }

    public int findMaxK(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = -1;

        for (int num : nums) {
            if (map.containsKey(-num)) {
                max = Math.max(max, Math.abs(num));
            }
            map.put(num, 1);
        }

        return max;
    }

}

//Ініціалізація допоміжних змінних: Спочатку ми ініціалізуємо змінну max на -1. Ця змінна буде використовуватися для
// збереження максимального знайденого позитивного числа, якому відповідає від'ємний елемент у масиві.
//Ітерація по масиву: Ми проходимося по всіх елементах масиву.
//Перевірка наявності пари: Для кожного елемента перевіряємо, чи є в масиві його від'ємний еквівалент. Якщо так, ми
// перевіряємо, чи це найбільше позитивне число, яке ми знайшли. Якщо так, оновлюємо max.
//Оновлення стану: Після перевірки поточного елементу, ми можемо додати його до структури даних (у нашому випадку,
// ми використовуємо мапу), щоб зберегти інформацію про зустрічені елементи.
//Повернення результату: На кінці ітерації повертаємо значення max.
//Ця стратегія дозволяє зменшити складність алгоритму до O(n), де n - розмір вхідного масиву, оскільки ми проходимо
// масив лише один раз. Використання мапи (або хеш-множини, як у початковому рішенні) дозволяє нам швидко перевіряти
// наявність елементів, що дозволяє нам знайти пару для кожного елемента масиву.

//Given an integer array nums that does not contain any zeros, find the largest positive integer k such that -k
// also exists in the array.
//Return the positive integer k. If there is no such integer, return -1.
//
//Example 1:
//Input: nums = [-1,2,-3,3]
//Output: 3
//Explanation: 3 is the only valid k we can find in the array.

//Example 2:
//Input: nums = [-1,10,6,7,-7,1]
//Output: 7
//Explanation: Both 1 and 7 have their corresponding negative values in the array. 7 has a larger value.

//Example 3:
//Input: nums = [-10,8,6,7,-2,-3]
//Output: -1
//Explanation: There is no a single valid k, we return -1.
//
//Constraints:
//1 <= nums.length <= 1000
//-1000 <= nums[i] <= 1000
//nums[i] != 0