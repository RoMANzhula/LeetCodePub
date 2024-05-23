package P2501_2600.P2597_The_Number_of_Beautiful_Subsets;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {2, 4, 6};
        int k1 = 2;
        System.out.println(solution.beautifulSubsets(nums1, k1)); // Output: 4

        int[] nums2 = {1};
        int k2 = 1;
        System.out.println(solution.beautifulSubsets(nums2, k2)); // Output: 1
    }

//    public int beautifulSubsets(int[] nums, int k) {
//        int[] result = new int[1];
//        Arrays.sort(nums);
//        backtrack(nums, k, 0, new ArrayList<>(), result);
//        return result[0];
//    }
//
//    private static void backtrack(int[] nums, int k, int start, List<Integer> currentSubset, int[] result) {
//        if (!currentSubset.isEmpty()) {
//            result[0]++;
//        }
//
//        for (int i = start; i < nums.length; i++) {
//            if (isValid(currentSubset, nums[i], k)) {
//                currentSubset.add(nums[i]);
//                backtrack(nums, k, i + 1, currentSubset, result);
//                currentSubset.remove(currentSubset.size() - 1);
//            }
//        }
//    }
//
//    private static boolean isValid(List<Integer> currentSubset, int num, int k) {
//        for (int n : currentSubset) {
//            if (Math.abs(n - num) == k) {
//                return false;
//            }
//        }
//        return true;
//    }

    //faster solution
    public int beautifulSubsets(int[] nums, int k) {
        int[] count = new int[1001]; // Given constraints (1 <= nums[i] <= 1000)
        return backtrack(nums, k, 0, count);
    }

    private static int backtrack(int[] nums, int k, int start, int[] count) {
        int result = 0;

        for (int i = start; i < nums.length; i++) {
            boolean isBeautiful = true;
            if (nums[i] - k >= 0 && count[nums[i] - k] > 0) {
                isBeautiful = false;
            }
            if (nums[i] + k <= 1000 && count[nums[i] + k] > 0) {
                isBeautiful = false;
            }

            if (isBeautiful) {
                count[nums[i]]++;
                result += 1 + backtrack(nums, k, i + 1, count);
                count[nums[i]]--;
            }
        }

        return result;
    }

}

//Ініціалізація:
//Ми створюємо масив count розміром 1001, щоб зберігати кількість кожного числа у поточній підмножині. Розмір
// обрано таким, тому що за умовами задачі всі числа в nums знаходяться в межах від 1 до 1000.
//Функція beautifulSubsets:
//Ця функція починає процес зворотнього відстеження, викликаючи функцію backtrack з початковими параметрами.
//Функція backtrack:
//Ми починаємо з 0 підмножин (result = 0).
//Проходимо по всіх елементах масиву nums, починаючи з індексу start.
//Для кожного елемента перевіряємо, чи може він бути доданий до поточної підмножини без порушення умови "красивості".
//Перевірка умови "красивості":
//Якщо різниця між поточним числом і k не менша за 0 та кількість елементів, рівних nums[i] - k, більше 0, то
// додавання цього числа порушує умову "красивості".
//Аналогічно, якщо сума поточного числа і k не перевищує 1000 та кількість елементів, рівних nums[i] + k, більше 0, то
// додавання цього числа також порушує умову "красивості".
//Додавання та видалення елементів:
//Якщо число може бути додане до підмножини, ми збільшуємо його кількість у масиві count і викликаємо backtrack для
// наступного індексу.
//Після повернення з рекурсії, ми видаляємо число з підмножини (зменшуємо його кількість у count).
//Повернення результату:
//Ми додаємо 1 до результату для кожної "красивої" підмножини, яку ми знайшли, включаючи новоутворену підмножину,
// і повертаємо загальну кількість "красивих" підмножин.

//You are given an array nums of positive integers and a positive integer k.
//A subset of nums is beautiful if it does not contain two integers with an absolute difference equal to k.
//Return the number of non-empty beautiful subsets of the array nums.
//A subset of nums is an array that can be obtained by deleting some (possibly none) elements from nums. Two
// subsets are different if and only if the chosen indices to delete are different.
//
//Example 1:
//Input: nums = [2,4,6], k = 2
//Output: 4
//Explanation: The beautiful subsets of the array nums are: [2], [4], [6], [2, 6].
//It can be proved that there are only 4 beautiful subsets in the array [2,4,6].
//Це рішення забезпечує ефективний підхід до перевірки та підрахунку всіх "красивих" підмножин масиву nums.

//Example 2:
//Input: nums = [1], k = 1
//Output: 1
//Explanation: The beautiful subset of the array nums is [1].
//It can be proved that there is only 1 beautiful subset in the array [1].
//
//Constraints:
//1 <= nums.length <= 20
//1 <= nums[i], k <= 1000
