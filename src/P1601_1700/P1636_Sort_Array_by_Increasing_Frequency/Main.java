package P1601_1700.P1636_Sort_Array_by_Increasing_Frequency;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 1, 2, 2, 2, 3};
        int[] result1 = solution.frequencySort(nums1);
        System.out.println(Arrays.toString(result1)); // Output: [3, 1, 1, 2, 2, 2]

        int[] nums2 = {2, 3, 1, 3, 2};
        int[] result2 = solution.frequencySort(nums2);
        System.out.println(Arrays.toString(result2)); // Output: [1, 3, 3, 2, 2]

        int[] nums3 = {-1, 1, -6, 4, 5, -6, 1, 4, 1};
        int[] result3 = solution.frequencySort(nums3);
        System.out.println(Arrays.toString(result3)); // Output: [5, -1, 4, 4, -6, -6, 1, 1, 1]
    }

//    public int[] frequencySort(int[] nums) {
//        // Step 1: Calculate frequency of each number
//        Map<Integer, Integer> frequencyMap = new HashMap<>();
//        for (int num : nums) {
//            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
//        }
//
//        // Step 2: Convert int array to Integer array for sorting
//        Integer[] numsInteger = Arrays.stream(nums).boxed().toArray(Integer[]::new);
//
//        // Step 3: Sort the array based on frequency and value
//        Arrays.sort(numsInteger, (a, b) -> {
//            int freqA = frequencyMap.get(a);
//            int freqB = frequencyMap.get(b);
//            if (freqA != freqB) {
//                return Integer.compare(freqA, freqB);
//            } else {
//                return Integer.compare(b, a);
//            }
//        });
//
//        // Step 4: Convert the sorted Integer array back to int array
//        return Arrays.stream(numsInteger).mapToInt(Integer::intValue).toArray();
//    }

    //little bit faster solution
//    public static int[] frequencySort(int[] nums) {
//        // Крок 1: Підрахунок частоти кожного числа
//        int[] frequency = new int[201];
//        for (int num : nums) {
//            frequency[num + 100]++;
//        }
//
//        // Крок 2: Конвертація int масиву в Integer масив для сортування
//        Integer[] numsInteger = Arrays.stream(nums).boxed().toArray(Integer[]::new);
//
//        // Крок 3: Сортування масиву на основі частоти та значення
//        Arrays.sort(numsInteger, (a, b) -> {
//            int freqA = frequency[a + 100];
//            int freqB = frequency[b + 100];
//            if (freqA != freqB) {
//                return Integer.compare(freqA, freqB);
//            } else {
//                return Integer.compare(b, a);
//            }
//        });
//
//        // Крок 4: Конвертація відсортованого Integer масиву назад в int масив
//        return Arrays.stream(numsInteger).mapToInt(Integer::intValue).toArray();
//    }

    //faster solution
    public int[] frequencySort(int[] nums) {
        // Крок 1: Підрахунок частоти кожного числа
        int[] frequency = new int[201];
        for (int num : nums) {
            frequency[num + 100]++;
        }

        // Крок 2: Створення списку пар (значення, частота)
        List<int[]> valueFreqPairs = new ArrayList<>();
        for (int num : nums) {
            valueFreqPairs.add(new int[]{num, frequency[num + 100]});
        }

        // Крок 3: Сортування списку пар на основі частоти і значення
        valueFreqPairs.sort((a, b) -> {
            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            } else {
                return Integer.compare(b[0], a[0]);
            }
        });

        // Крок 4: Запис результату в масив
        for (int i = 0; i < nums.length; i++) {
            nums[i] = valueFreqPairs.get(i)[0];
        }

        return nums;
    }


}

//Цей підхід використовує такі кроки:
//
//Масив frequency для підрахунку частоти кожного числа.
//Створення списку пар, де кожна пара складається з числа та його частоти.
//Сортування списку пар на основі частоти і значення.
//Запис відсортованих чисел назад у початковий масив.
//Це рішення мінімізує кількість операцій і не вимагає зайвих перетворень між int та Integer, що підвищує продуктивність.


//Given an array of integers nums, sort the array in increasing order based on the frequency of the
// values. If multiple values have the same frequency, sort them in decreasing order.

//Return the sorted array.

//Example 1:
//Input: nums = [1,1,2,2,2,3]
//Output: [3,1,1,2,2,2]
//Explanation: '3' has a frequency of 1, '1' has a frequency of 2, and '2' has a frequency of 3.

//Example 2:
//Input: nums = [2,3,1,3,2]
//Output: [1,3,3,2,2]
//Explanation: '2' and '3' both have a frequency of 2, so they are sorted in decreasing order.

//Example 3:
//Input: nums = [-1,1,-6,4,5,-6,1,4,1]
//Output: [5,-1,4,4,-6,-6,1,1,1]
//
//Constraints:
//1 <= nums.length <= 100
//-100 <= nums[i] <= 100