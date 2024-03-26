package P1_100.P41_First_Missing_Positive;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        // Example 1
        int[] nums1 = {1, 2, 0};
        System.out.println("Example 1 Output: " + solution.firstMissingPositive(nums1)); // Output: 3

        // Example 2
        int[] nums2 = {3, 4, -1, 1};
        System.out.println("Example 2 Output: " + solution.firstMissingPositive(nums2)); // Output: 2

        // Example 3
        int[] nums3 = {7, 8, 9, 11, 12};
        System.out.println("Example 3 Output: " + solution.firstMissingPositive(nums3)); // Output: 1
    }

    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return n + 1;
    }
}

//Для розв'язання цієї задачі ми використовуємо алгоритм, що базується на сортуванні "циклічним" методом. Основна
// ідея полягає в тому, щоб переставляти елементи масиву на їхні правильні позиції, доки масив не буде містити всі
// позитивні цілі числа у порядку зростання.
//Першим кроком є проходження по масиву і переставлення кожного елемента на його правильне місце. Це означає, що
// елемент зі значенням i має бути на позиції i - 1 у масиві, якщо воно є позитивним і меншим або рівним розміру
// масиву. При цьому ми переставляємо елементи, щоб забезпечити це.
//Після цього ми знову проходимо по масиву і перевіряємо, чи елемент i має значення i + 1. Якщо ні, то i + 1 є
// першим недостаючим позитивним цілим числом. Якщо всі елементи масиву вже знаходяться на своїх правильних місцях,
// то наступним недостаючим буде число n + 1, де n - це розмір масиву.
//Цей алгоритм має часову складність O(n), оскільки ми проходимо по масиву не більше, ніж двічі, і просторову
// складність O(1), оскільки ми не використовуємо додаткову пам'ять, що залежить від розміру масиву.

//Given an unsorted integer array nums. Return the smallest positive integer that is not present in nums.
//You must implement an algorithm that runs in O(n) time and uses O(1) auxiliary space.
//
//Example 1:
//Input: nums = [1,2,0]
//Output: 3
//Explanation: The numbers in the range [1,2] are all in the array.

//Example 2:
//Input: nums = [3,4,-1,1]
//Output: 2
//Explanation: 1 is in the array but 2 is missing.

//Example 3:
//Input: nums = [7,8,9,11,12]
//Output: 1
//Explanation: The smallest positive integer 1 is missing.
//
//Constraints:
//1 <= nums.length <= 105
//-231 <= nums[i] <= 231 - 1
