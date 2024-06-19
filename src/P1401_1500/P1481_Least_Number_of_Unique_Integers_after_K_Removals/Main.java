package P1401_1500.P1481_Least_Number_of_Unique_Integers_after_K_Removals;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();
        // Example 1
        int[] arr1 = {5, 5, 4};
        int k1 = 1;
        System.out.println("Example 1 Output: " + solution.findLeastNumOfUniqueInts(arr1, k1)); // Output: 1

        // Example 2
        int[] arr2 = {4, 3, 1, 1, 3, 3, 2};
        int k2 = 3;
        System.out.println("Example 2 Output: " + solution.findLeastNumOfUniqueInts(arr2, k2)); // Output: 2
    }

    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        // Count the frequencies of each integer
        for (int num : arr) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Sort frequencies in ascending order
        List<Integer> frequencies = new ArrayList<>(frequencyMap.values());
        Collections.sort(frequencies);

        int uniqueIntegers = frequencyMap.size();
        int removed = 0;
        int i = 0;

        // Remove the least frequent integers until the total removed equals k
        while (removed < k && i < frequencies.size()) {
            int frequency = frequencies.get(i);
            if (k - removed >= frequency) {
                removed += frequency;
                uniqueIntegers--;
            } else {
                break;
            }
            i++;
        }

        return uniqueIntegers;
    }
}

//Спочатку ми можемо використати хеш-таблицю (Map) для підрахунку кількості входжень кожного числа в масиві arr.
//Потім ми можемо відсортувати ці значення від найменшого до найбільшого.
//Далі можемо послідовно видаляти елементи, починаючи з найменших, і віднімати їх кількість від k.
//Коли k стає менше або рівне нулю, ми можемо зупинитися, оскільки ми вже видалили необхідну кількість елементів.
//Найменша кількість унікальних елементів, яку залишили після видалення k елементів, і буде нашим результатом.

//Given an array of integers arr and an integer k. Find the least number of unique integers after
// removing exactly k elements.

//Example 1:
//Input: arr = [5,5,4], k = 1
//Output: 1
//Explanation: Remove the single 4, only 5 is left.

//Example 2:
//Input: arr = [4,3,1,1,3,3,2], k = 3
//Output: 2
//Explanation: Remove 4, 2 and either one of the two 1s or three 3s. 1 and 3 will be left.
//
//Constraints:
//1 <= arr.length <= 10^5
//1 <= arr[i] <= 10^9
//0 <= k <= arr.length
