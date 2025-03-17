package P1401_1500.P1424_Diagonal_Traverse_II;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<List<Integer>> nums1 = new ArrayList<>();
        nums1.add(List.of(1, 2, 3));
        nums1.add(List.of(4, 5, 6));
        nums1.add(List.of(7, 8, 9));

        List<List<Integer>> nums2 = new ArrayList<>();
        nums2.add(List.of(1, 2, 3, 4, 5));
        nums2.add(List.of(6, 7));
        nums2.add(List.of(8));
        nums2.add(List.of(9, 10, 11));
        nums2.add(List.of(12, 13, 14, 15, 16));

        int[] output1 = findDiagonalOrder(nums1);
        int[] output2 = findDiagonalOrder(nums2);

        // Output: [1, 4, 2, 7, 5, 3, 8, 6, 9]
        for (int num : output1) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Output: [1, 6, 2, 8, 7, 3, 9, 4, 12, 10, 5, 13, 11, 14, 15, 16]
        for (int num : output2) {
            System.out.print(num + " ");
        }
    }

    public static int[] findDiagonalOrder(List<List<Integer>> nums) {
        List<Integer> ans = new ArrayList<>();
        Map<Integer, List<Integer>> keyToNums = new HashMap<>();

        for (int i = 0; i < nums.size(); ++i) {
            for (int j = 0; j < nums.get(i).size(); ++j) {
                int key = i + j;
                keyToNums.computeIfAbsent(key, k -> new ArrayList<>()).add(nums.get(i).get(j));
            }
        }

        for (int i = 0; i < keyToNums.size(); ++i) {
            List<Integer> diagonal = keyToNums.get(i);
            for (int j = diagonal.size() - 1; j >= 0; --j) {
                ans.add(diagonal.get(j));
            }
        }

        return ans.stream().mapToInt(Integer::intValue).toArray();

    }

    //    public int[] findDiagonalOrder(List<List<Integer>> nums) {
    //        if (nums == null || nums.isEmpty()) {
    //            return new int[0];
    //        }
    //
    //        int rows = nums.size();
    //        int cols = nums.get(0).size();
    //
    //        List<Integer> result = new ArrayList<>();
    //
    //        for (int i = 0; i < rows + cols - 1; i++) {
    //            List<Integer> currentDiagonal = new ArrayList<>();
    //
    //            int startRow = Math.min(i, rows - 1);
    //            int startCol = Math.max(0, i - rows + 1);
    //
    //            while (startRow >= 0 && startCol < cols) {
    //                currentDiagonal.add(nums.get(startRow).get(startCol));
    //                startRow--;
    //                startCol++;
    //            }
    //
    //            if (i % 2 == 0) {
    //                Collections.reverse(currentDiagonal);
    //            }
    //
    //            result.addAll(currentDiagonal);
    //        }
    //
    //        int[] output = new int[result.size()];
    //        for (int i = 0; i < result.size(); i++) {
    //            output[i] = result.get(i);
    //        }
    //
    //        return output;
    //    }
}

//Створення ArrayList та HashMap: Спочатку ми створюємо дві змінні - ans для збереження результатів у порядку
// діагоналей та keyToNums для відображення ключів (суми індексів рядка і стовпця) на список чисел.
//Перший прохід (рядки і стовпці): Ми проходимо по кожному елементу у матриці nums та визначаємо його ключ
// (сума індексів рядка і стовпця). За допомогою computeIfAbsent ми створюємо новий список для ключа, якщо
// він ще не існує, та додаємо поточне число до цього списку.
//Другий прохід (формування результату): Після того як всі елементи матриці були розміщені за ключами у keyToNums,
// ми проходимо по кожному ключу та його асоційованому списку. Додаємо числа до результату в зворотньому
// порядку (від останнього до першого).
//Перетворення в масив int[]: Завершальний крок - ми перетворюємо ArrayList ans в масив int[],
// використовуючи stream та mapToInt.
//Цей підхід дозволяє зберігати числа у відсортованому порядку за діагоналями і враховувати порядок вставки
// до результату.

//Given a 2D integer array nums, return all elements of nums in diagonal order as shown in the below images.

//Example 1:
//Input: nums = [[1,2,3],[4,5,6],[7,8,9]]
//Output: [1,4,2,7,5,3,8,6,9]

//Example 2:
//Input: nums = [[1,2,3,4,5],[6,7],[8],[9,10,11],[12,13,14,15,16]]
//Output: [1,6,2,8,7,3,9,4,12,10,5,13,11,14,15,16]

//Constraints:
//1 <= nums.length <= 105
//1 <= nums[i].length <= 105
//1 <= sum(nums[i].length) <= 105
//1 <= nums[i][j] <= 105
