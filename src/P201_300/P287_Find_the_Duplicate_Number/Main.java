package P201_300.P287_Find_the_Duplicate_Number;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 3, 4, 2, 2};
        System.out.println("Duplicate number: " + solution.findDuplicate(nums1)); // Output: 2

        int[] nums2 = {3, 1, 3, 4, 2};
        System.out.println("Duplicate number: " + solution.findDuplicate(nums2)); // Output: 3

        int[] nums3 = {3, 3, 3, 3, 3};
        System.out.println("Duplicate number: " + solution.findDuplicate(nums3)); // Output: 3
    }

    public int findDuplicate(int[] nums) {
        //initialize the slow and fast pointers
        int slow = nums[0];
        int fast = nums[0];

        //find the intersection point in the cycle
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        //find the entrance to the cycle
        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow; //bingo
    }
}

//Даний алгоритм використовує метод "Алгоритм кролика і черепахи" (Floyd's Tortoise and Hare algorithm) для
// виявлення циклів у зв'язному списку.
//Спочатку ми ініціалізуємо два вказівника, кролика (tortoise) і черепаху (hare), які починають рухатися
// з першого елемента масиву.
//Кролик переміщується на один крок за раз, а черепаха на два кроки.
//Оскільки в масиві є дубльоване число, рано чи пізно кролик і черепаха зустрінуться у якомусь елементі масиву.
//Після знаходження точки перетину ми знову розміщуємо один із вказівників на початок масиву і рухаємо обидва
// вказівники по одному кроку до тих пір, поки вони знову не зустрінуться. Точка зустрічі вказівників і є
// входом до циклу, що відповідає дубльованому числу.
//Нарешті, повертаємо значення цього дубльованого числа.
//Цей алгоритм має часову складність O(n) та просторову складність O(1), що відповідає вимогам задачі.

//Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
//There is only one repeated number in nums, return this repeated number.
//You must solve the problem without modifying the array nums and uses only constant extra space.
//
//Example 1:
//Input: nums = [1,3,4,2,2]
//Output: 2

//Example 2:
//Input: nums = [3,1,3,4,2]
//Output: 3

//Example 3:
//Input: nums = [3,3,3,3,3]
//Output: 3
//
//Constraints:
//1 <= n <= 105
//nums.length == n + 1
//1 <= nums[i] <= n
//All the integers in nums appear only once except for precisely one integer which appears two or more times.
//
//Follow up:
//How can we prove that at least one duplicate number must exist in nums?
//Can you solve the problem in linear runtime complexity?
