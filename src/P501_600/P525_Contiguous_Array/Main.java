package P501_600.P525_Contiguous_Array;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {0, 1};
        System.out.println("Output for nums1: " + solution.findMaxLength(nums1)); // Output: 2

        int[] nums2 = {0, 1, 0};
        System.out.println("Output for nums2: " + solution.findMaxLength(nums2)); // Output: 2
    }

    public int findMaxLength(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // initialize the map with 0 count at index -1
        int maxLen = 0;
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            // increment count for 1, decrement for 0
            count += (nums[i] == 1) ? 1 : -1;

            if (map.containsKey(count)) {
                // if the count is encountered again, calculate the length of the subarray
                maxLen = Math.max(maxLen, i - map.get(count));
            } else {
                // store the count and its corresponding index
                map.put(count, i);
            }
        }

        return maxLen;
    }
}

//У цьому завданні ми використовуємо хеш-таблицю (HashMap) для відстеження кумулятивної кількості нулів і одиниць,
// які зустрічаються під час проходження масиву. Ось як працює код:
//Ми створюємо HashMap для відстеження кількості (count) та індексу, де ця кількість була досягнута.
//Початково ми встановлюємо значення 0 та відповідний індекс -1, щоб врахувати ситуацію, коли масив починається і
// закінчується рівними кількостями 0 і 1.
//Проходимося по масиву і на кожній ітерації збільшуємо кількість для одиниць та зменшуємо для нулів.
//Якщо вже була зустрічена така сама кількість (count) раніше, ми обчислюємо довжину підмасиву та порівнюємо з
// максимальною знайденою довжиною.
//Якщо такої кількості в HashMap ще не було, ми записуємо її разом з поточним індексом.
//Отже, ми шукаємо найбільшу довжину послідовного підмасиву, де кількість нулів дорівнює кількості одиниць,
// використовуючи хеш-таблицю для відстеження кумулятивної кількості та її індексу.

//Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.
//
//Example 1:
//Input: nums = [0,1]
//Output: 2
//Explanation: [0, 1] is the longest contiguous subarray with an equal number of 0 and 1.

//Example 2:
//Input: nums = [0,1,0]
//Output: 2
//Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
//
//Constraints:
//1 <= nums.length <= 105
//nums[i] is either 0 or 1.
