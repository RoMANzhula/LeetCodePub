package P2601_2700.P2610_Convert_an_Array_Into_a_2D_Array_With_Conditions;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        int[] nums1 = {1, 3, 4, 1, 2, 3, 1};
        int[] nums2 = {1, 2, 3, 4};

        List<List<Integer>> result1 = create2DArray(nums1);
        List<List<Integer>> result2 = create2DArray(nums2);

        System.out.println("Output 1: " + result1);
        System.out.println("Output 2: " + result2);
    }

    public static List<List<Integer>> create2DArray(int[] nums) {
        //Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        for (int num : nums) {
            boolean added = false;
            for (List<Integer> row : result) {
                if (!row.contains(num)) {
                    row.add(num);
                    added = true;
                    break;
                }
            }

            if (!added) {
                List<Integer> newRow = new ArrayList<>();
                newRow.add(num);
                result.add(newRow);
            }
        }

        return result;
    }

}

//Основна ідея розв'язку полягає в тому, щоб ітерувати через елементи масиву nums та додавати їх до вже існуючих
// рядків двовимірного масиву, якщо ці елементи вже не зустрічалися в цих рядках. Якщо елемент вже є в рядку,
// його пропускаємо, інакше додаємо його до поточного рядка.
//Основні кроки:
//Пройтися по масиву і додавати елементи до рядків двовимірного масиву, забезпечуючи унікальність.
//Якщо елемент вже є в рядку, перейти до наступного елементу.
//Якщо елемент не є унікальним в жодному із існуючих рядків, створити новий рядок і додати елемент до нього.
//На завершення отримаємо двовимірний масив, який відповідає умовам задачі. Важливо відзначити, що порядок елементів
// у вихідному масиві може бути будь-яким, оскільки умови задачі дозволяють повертати будь-яку коректну відповідь.


//You are given an integer array nums. You need to create a 2D array from nums satisfying the following conditions:
//The 2D array should contain only the elements of the array nums.
//Each row in the 2D array contains distinct integers.
//The number of rows in the 2D array should be minimal.
//Return the resulting array. If there are multiple answers, return any of them.
//Note that the 2D array can have a different number of elements on each row.

//Example 1:
//Input: nums = [1,3,4,1,2,3,1]
//Output: [[1,3,4,2],[1,3],[1]]
//Explanation: We can create a 2D array that contains the following rows:
//- 1,3,4,2
//- 1,3
//- 1
//All elements of nums were used, and each row of the 2D array contains distinct integers, so it is a valid answer.
//It can be shown that we cannot have less than 3 rows in a valid array

//Example 2:
//Input: nums = [1,2,3,4]
//Output: [[4,3,2,1]]
//Explanation: All elements of the array are distinct, so we can keep all of them in the first row of the 2D array.

//Constraints:
//1 <= nums.length <= 200
//1 <= nums[i] <= nums.length
