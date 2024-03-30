package P901_1000.P992_Subarrays_with_K_Different_Integers;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 2, 1, 2, 3};
        int k1 = 2;
        System.out.println("Output for Example 1: " + solution.subarraysWithKDistinct(nums1, k1)); // Output: 7

        int[] nums2 = {1, 2, 1, 3, 4};
        int k2 = 3;
        System.out.println("Output for Example 2: " + solution.subarraysWithKDistinct(nums2, k2)); // Output: 3
    }

    public int subarraysWithKDistinct(int[] nums, int k) {
        return subarraysWithAtMostKDistinct(nums, k) - subarraysWithAtMostKDistinct(nums, k - 1);
    }

            //with econom memory
//    public int subarraysWithAtMostKDistinct(int[] nums, int k) {
//        int answer = 0;
//        HashMap<Integer, Integer> count = new HashMap<>();
//
//        int left = 0;
//        for (int right = 0; right < nums.length; right++) {
//            int num = nums[right];
//            count.put(num, count.getOrDefault(num, 0) + 1);
//            if (count.get(num) == 1) {
//                k--;
//            }
//            while (k < 0) {
//                int leftNum = nums[left];
//                count.put(leftNum, count.get(leftNum) - 1);
//                if (count.get(leftNum) == 0) {
//                    k++;
//                }
//                left++;
//            }
//            answer += right - left + 1;
//        }
//        return answer;
//    }

    //little faster solve
    public int subarraysWithAtMostKDistinct(int[] nums, int k) {
        int count = 0;
        int left = 0;
        HashMap<Integer, Integer> freqMap = new HashMap<>();

        for (int right = 0; right < nums.length; right++) {
            int num = nums[right];
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);

            while (freqMap.size() > k) {
                int leftNum = nums[left++];
                freqMap.put(leftNum, freqMap.get(leftNum) - 1);
                if (freqMap.get(leftNum) == 0) {
                    freqMap.remove(leftNum);
                }
            }
            count += right - left + 1;
        }

        return count;
    }
}

//Ми визначаємо метод subarraysWithKDistinct, який приймає масив nums та цільове значення k, що позначає кількість
// різних елементів у підмасиві.
//У методі subarraysWithKDistinct, ми викликаємо метод atMostKDistinct двічі: один раз з параметром k, а інший
// раз з параметром k - 1. Це дозволяє нам отримати кількість підмасивів з точно k різними елементами.
//У методі atMostKDistinct, ми використовуємо два показники - left та right, для визначення поточного вікна у
// масиві. Початково обидва показники встановлені на початок масиву.
//Ми також використовуємо HashMap, щоб відстежувати кількість кожного елемента у поточному вікні. Ключами у цьому
// HashMap є числа з масиву nums, а значеннями є їх кількість.
//Ми рухаємо правий показник right вправо по масиву. Під час цього процесу ми додаємо кожний елемент у HashMap та
// оновлюємо його кількість.
//Коли кількість різних елементів у вікні перевищує k, ми рухаємо лівий показник left вправо, видаляючи елементи
// з HashMap, поки кількість різних елементів не стане рівною k знову.
//Після кожного кроку ми додаємо кількість підмасивів, які можна сформувати з поточним вікном, до загальної
// кількості. Це робиться за допомогою формули (right - left + 1), оскільки кожне значення right утворює підмасиви
// з лівим краєм від left до right.
//Після завершення всього процесу повертаємо загальну кількість підмасивів, які містять рівно k різних елементів.

//Given an integer array nums and an integer k, return the number of good subarrays of nums.
//A good array is an array where the number of different integers in that array is exactly k.
//For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.
//A subarray is a contiguous part of an array.
//
//Example 1:
//Input: nums = [1,2,1,2,3], k = 2
//Output: 7
//Explanation: Subarrays formed with exactly 2 different integers:
// [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]

//Example 2:
//Input: nums = [1,2,1,3,4], k = 3
//Output: 3
//Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].
//
//Constraints:
//1 <= nums.length <= 2 * 104
//1 <= nums[i], k <= nums.length