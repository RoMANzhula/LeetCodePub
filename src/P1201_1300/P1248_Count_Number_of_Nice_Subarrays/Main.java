package P1201_1300.P1248_Count_Number_of_Nice_Subarrays;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 1, 2, 1, 1};
        int k1 = 3;
        System.out.println(solution.numberOfSubarrays(nums1, k1));  // Output: 2

        int[] nums2 = {2, 4, 6};
        int k2 = 1;
        System.out.println(solution.numberOfSubarrays(nums2, k2));  // Output: 0

        int[] nums3 = {2, 2, 2, 1, 2, 2, 1, 2, 2, 2};
        int k3 = 2;
        System.out.println(solution.numberOfSubarrays(nums3, k3));  // Output: 16
    }

//    public int numberOfSubarrays(int[] nums, int k) {
//        // Map to store the prefix sums and their counts
//        Map<Integer, Integer> prefixSumCount = new HashMap<>();
//        prefixSumCount.put(0, 1);  // Initial condition
//
//        int count = 0;
//        int currentSum = 0;
//
//        for (int num : nums) {
//            // If num is odd, add 1 to currentSum, else add 0
//            currentSum += num % 2;
//
//            // If there exists a prefix sum that equals currentSum - k, it means
//            // there is a subarray ending at current position with exactly k odd numbers
//            if (prefixSumCount.containsKey(currentSum - k)) {
//                count += prefixSumCount.get(currentSum - k);
//            }
//
//            // Add the currentSum to the map
//            prefixSumCount.put(currentSum, prefixSumCount.getOrDefault(currentSum, 0) + 1);
//        }
//
//        return count;
//    }

    // faster solution
    public int numberOfSubarrays(int[] nums, int k) {
        int max = nums.length;
        int[] prefixSumCount = new int[max + 1];
        prefixSumCount[0] = 1;  // Initial condition

        int count = 0;
        int currentSum = 0;

        for (int num : nums) {
            // Якщо num непарне, додаємо 1 до currentSum, інакше додаємо 0
            currentSum += num % 2;

            // Якщо існує префіксна сума, яка дорівнює currentSum - k
            if (currentSum >= k) {
                count += prefixSumCount[currentSum - k];
            }

            // Додаємо поточну суму до масиву
            prefixSumCount[currentSum]++;
        }

        return count;
    }

}

        //first solution
//Explanation
//Prefix Sum Map: We use a HashMap to keep track of the counts of prefix sums. The key is the prefix sum, and the
// value is the count of how many times that prefix sum has been seen.
//Iterate Through Array: As we iterate through the array, we update the currentSum by adding 1 if the number is
// odd (i.e., num % 2 == 1) or 0 if the number is even.
//Check for Valid Subarrays: For each position, we check if there is a prefix sum that, when subtracted from currentSum,
// equals k. If so, it means there are subarrays ending at the current position that have exactly k odd numbers.
//Update Map: After checking for valid subarrays, we update the prefix sum map with the current sum.
//This approach ensures we efficiently count the number of "nice" subarrays in linear time, which is suitable
// given the problem constraints.

        //faster solution
//Пояснення
//Масив prefixSumCount: Замість HashMap використовується масив prefixSumCount, де індекс масиву відповідає
// префіксній сумі, а значення - кількості разів, коли ця сума зустрічалася.
//Ініціалізація: Масив ініціалізується нулями, і ми встановлюємо prefixSumCount[0] = 1, оскільки початкова
// префіксна сума дорівнює 0.
//Ітерація по масиву: Для кожного елемента в nums ми оновлюємо currentSum, додаючи 1, якщо число
// непарне, або 0, якщо парне.
//Перевірка: Якщо currentSum більше або дорівнює k, ми додаємо до count значення з масиву prefixSumCount для
// індексу currentSum - k.
//Оновлення: Збільшуємо значення в prefixSumCount для індексу currentSum на 1.
//Цей підхід значно покращує швидкодію програми, оскільки доступ до масиву є константним за часом.


//Given an array of integers nums and an integer k. A continuous subarray is called nice if
// there are k odd numbers on it.
//Return the number of nice sub-arrays.
//
//Example 1:
//Input: nums = [1,1,2,1,1], k = 3
//Output: 2
//Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].

//Example 2:
//Input: nums = [2,4,6], k = 1
//Output: 0
//Explanation: There are no odd numbers in the array.

//Example 3:
//Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
//Output: 16
//
//Constraints:
//1 <= nums.length <= 50000
//1 <= nums[i] <= 10^5
//1 <= k <= nums.length