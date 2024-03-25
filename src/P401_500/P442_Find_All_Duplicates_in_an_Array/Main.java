package P401_500.P442_Find_All_Duplicates_in_an_Array;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {4,3,2,7,8,2,3,1};
        int[] nums2 = {1,1,2};
        int[] nums3 = {1};

        System.out.println(solution.findDuplicates(nums1));
        System.out.println(solution.findDuplicates(nums2));
        System.out.println(solution.findDuplicates(nums3));
    }

    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();

        for (int num : nums) {
            int index = Math.abs(num) - 1;
            if (nums[index] < 0) {
                result.add(index + 1);
            } else {
                nums[index] = -nums[index];
            }
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = Math.abs(nums[i]); // restore the array
        }

        return result;
    }
}

//Проходимося по масиву чисел.
//Для кожного числа ми перевіряємо його індекс у масиві (за допомогою числа, віднятого на одиницю).
//Якщо значення на цьому індексі вже від'ємне, це означає, що ми зустріли число двічі, тому додаємо його до результату.
//В іншому випадку, якщо число зустрілося вперше, ми робимо його значення від'ємним.
//Після того як пройшли всі числа, повертаємо значення масиву до додатніх чисел (беремо модуль кожного елемента).
//Повертаємо результат - масив із дубльованими числами.
//Це розв'язання працює з часовою складністю O(n), оскільки ми проходимося по масиву лише один раз, і використовує
// лише константну кількість додаткової пам'яті для зберігання результату.


//Given an integer array nums of length n where all the integers of nums are in the range [1, n] and each
// integer appears once or twice, return an array of all the integers that appears twice.
//You must write an algorithm that runs in O(n) time and uses only constant extra space.
//Example 1:
//Input: nums = [4,3,2,7,8,2,3,1]
//Output: [2,3]

//Example 2:
//Input: nums = [1,1,2]
//Output: [1]

//Example 3:
//Input: nums = [1]
//Output: []
//
//Constraints:
//n == nums.length
//1 <= n <= 105
//1 <= nums[i] <= n
//Each element in nums appears once or twice.