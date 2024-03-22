package P1201_1300.P1207_Unique_Number_of_Occurrences;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 2, 1, 1, 3};
        System.out.println(uniqueOccurrences(arr1)); // Output: true

        int[] arr2 = {1, 2};
        System.out.println(uniqueOccurrences(arr2)); // Output: false

        int[] arr3 = {-3, 0, 1, -3, 1, 1, 1, -3, 10, 0};
        System.out.println(uniqueOccurrences(arr3)); // Output: true
    }

    public static boolean uniqueOccurrences(int[] arr) {
        // Map to store the count of occurrences for each value in the array
        Map<Integer, Integer> occurrencesMap = new HashMap<>();

        // Populate the occurrencesMap
        for (int num : arr) {
            occurrencesMap.put(num, occurrencesMap.getOrDefault(num, 0) + 1);
        }

        // Set to store unique occurrences
        Set<Integer> uniqueOccurrencesSet = new HashSet<>(occurrencesMap.values());

        // If the size of the set is equal to the size of the map, all occurrences are unique
        return uniqueOccurrencesSet.size() == occurrencesMap.size();
    }
}

//Задача полягає в тому, щоб перевірити, чи є кількість входжень кожного числа в масив унікальною для
// всіх чисел. Якщо так, то повертається true, інакше - false.
//
//Ось як працює розв'язок на Java:
//
//Створюємо Map<Integer, Integer> occurrencesMap для того, щоб відстежувати кількість входжень кожного числа у масиві.
//
//Проходимо по масиву arr і для кожного числа збільшуємо його лічильник у occurrencesMap.
//
//Створюємо Set<Integer> uniqueOccurrencesSet, в якому будемо зберігати унікальні значення кількостей входжень.
//
//Перевіряємо, чи кількість унікальних значень у uniqueOccurrencesSet дорівнює кількості унікальних чисел у occurrencesMap.
//
//Якщо кількості різні, повертаємо false, інакше повертаємо true.
//
//Наприклад, для масиву arr = [1, 2, 2, 1, 1, 3], у нас є три унікальні числа: 1, 2 і 3. Кількість входжень
// для них відповідно 3, 2 і 1. Це унікально, тому вивід - true. Аналогічно для інших прикладів.


//Given an array of integers arr, return true if the number of occurrences of each value in the array is
// unique or false otherwise.

//Example 1:
//
//Input: arr = [1,2,2,1,1,3]
//Output: true
//Explanation: The value 1 has 3 occurrences, 2 has 2 and 3 has 1. No two values have the same number of occurrences.
//Example 2:
//
//Input: arr = [1,2]
//Output: false
//Example 3:
//
//Input: arr = [-3,0,1,-3,1,1,1,-3,10,0]
//Output: true
//
//
//Constraints:
//
//1 <= arr.length <= 1000
//-1000 <= arr[i] <= 1000