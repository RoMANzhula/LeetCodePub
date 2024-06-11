package P1101_1200.P1122_Relative_Sort_Array;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] arr1 = {2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19};
        int[] arr2 = {2, 1, 4, 3, 9, 6};
        System.out.println(Arrays.toString(solution.relativeSortArray(arr1, arr2))); // Output: [2, 2, 2, 1, 4, 3, 3, 9, 6, 7, 19]

        int[] arr1_2 = {28, 6, 22, 8, 44, 17};
        int[] arr2_2 = {22, 28, 8, 6};
        System.out.println(Arrays.toString(solution.relativeSortArray(arr1_2, arr2_2))); // Output: [22, 28, 8, 6, 17, 44]

    }

//    public int[] relativeSortArray(int[] arr1, int[] arr2) {
//        // Step 1: Count frequencies of each element in arr1
//        Map<Integer, Integer> frequencyMap = new HashMap<>();
//        for (int num : arr1) {
//            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
//        }
//
//        // Step 2: Create the result array and index to track the position
//        int[] result = new int[arr1.length];
//        int index = 0;
//
//        // Step 3: Place elements from arr2 in the order they appear in arr2
//        for (int num : arr2) {
//            if (frequencyMap.containsKey(num)) {
//                int count = frequencyMap.get(num);
//                for (int i = 0; i < count; i++) {
//                    result[index++] = num;
//                }
//                frequencyMap.remove(num);
//            }
//        }
//
//        // Step 4: Collect remaining elements and sort them
//        List<Integer> remainingElements = new ArrayList<>();
//        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
//            int num = entry.getKey();
//            int count = entry.getValue();
//            for (int i = 0; i < count; i++) {
//                remainingElements.add(num);
//            }
//        }
//        Collections.sort(remainingElements);
//
//        // Step 5: Add the sorted remaining elements to the result array
//        for (int num : remainingElements) {
//            result[index++] = num;
//        }
//
//        return result;
//    }

    // faster solution with array
    public static int[] relativeSortArray(int[] arr1, int[] arr2) {
        // Step 1: Count frequencies of each element in arr1 using an array
        int[] frequency = new int[1001];
        for (int num : arr1) {
            frequency[num]++;
        }

        // Step 2: Create the result array and index to track the position
        int[] result = new int[arr1.length];
        int index = 0;

        // Step 3: Place elements from arr2 in the order they appear in arr2
        for (int num : arr2) {
            while (frequency[num] > 0) {
                result[index++] = num;
                frequency[num]--;
            }
        }

        // Step 4: Place the remaining elements in ascending order
        for (int num = 0; num < frequency.length; num++) {
            while (frequency[num] > 0) {
                result[index++] = num;
                frequency[num]--;
            }
        }

        return result;
    }

}

//Пояснення:
//Підрахунок частот: Використовуємо масив frequency розміром 1001 для зберігання частот кожного елемента з arr1.
//Порядок за arr2: Ітеруємо через arr2 і додаємо відповідні елементи в результатний масив стільки разів, скільки
// вони з'являються в arr1.
//Решта елементів: Після обробки елементів arr2, ітеруємо через масив frequency і додаємо решту елементів в
// порядку зростання в результатний масив.
//Цей підхід забезпечує більш швидку роботу програми завдяки використанню масиву замість мапи для підрахунку частот.

//Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements in arr2 are also in arr1.
//Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2. Elements that
// do not appear in arr2 should be placed at the end of arr1 in ascending order.
//
//Example 1:
//Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
//Output: [2,2,2,1,4,3,3,9,6,7,19]

//Example 2:
//Input: arr1 = [28,6,22,8,44,17], arr2 = [22,28,8,6]
//Output: [22,28,8,6,17,44]
//
//Constraints:
//1 <= arr1.length, arr2.length <= 1000
//0 <= arr1[i], arr2[i] <= 1000
//All the elements of arr2 are distinct.
//Each arr2[i] is in arr1.
