package P201_300.P260_Single_Number_III;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1,2,1,3,2,5};
        System.out.println(Arrays.toString(solution.singleNumber(nums1))); // output: [3,5] (or[5,3])

        int[] nums2 = {-1,0};
        System.out.println(Arrays.toString(solution.singleNumber(nums2))); // output: [-1,0]

        int[] nums3 = {0,1};
        System.out.println(Arrays.toString(solution.singleNumber(nums3))); // output: [1,0]

    }

    public int[] singleNumber(int[] nums) {
        // Step 1: XOR all elements to get the XOR of the two unique numbers
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }

        // Step 2: Find a set bit (rightmost set bit in this case)
        int diff = xor & (-xor);

        // Step 3: Divide numbers into two groups and XOR within each group
        int[] result = new int[2];
        for (int num : nums) {
            if ((num & diff) == 0) {
                result[0] ^= num; // the bit is not set
            } else {
                result[1] ^= num; // the bit is set
            }
        }

        return result;
    }
}

//Кроки розв'язання:
//Виконати операцію XOR над усіма елементами: Спочатку ми виконуємо операцію XOR над усіма елементами масиву. Оскільки
// XOR двох однакових чисел дорівнює 0, а XOR будь-якого числа з 0 дорівнює самому числу, то результат цієї
// операції дасть нам XOR двох унікальних чисел, які ми шукаємо.
//Знайти наймолодший встановлений біт: Потім ми визначаємо будь-який біт, який встановлений (рівний 1) у результаті
// операції XOR. Це можна зробити за допомогою операції result & -result, яка ізолює наймолодший встановлений
// біт. Цей біт дозволить нам розділити всі числа на дві групи, оскільки два унікальні числа відрізняються
// хоча б в одному біті.
//Розділити числа на дві групи і виконати операцію XOR знову: Тепер ми розділяємо всі числа масиву на дві групи
// за допомогою знайденого біта. Одну групу складатимуть числа, у яких цей біт встановлений (рівний 1), іншу — ті,
// у яких цей біт не встановлений (рівний 0). Після цього ми знову виконуємо операцію XOR у кожній групі окремо. У
// кожній групі залишиться по одному унікальному числу, оскільки всі інші числа знову знищаться через XOR.
//Пояснення:
//Крок 1: Виконуючи операцію XOR над усіма елементами масиву, ми отримуємо результат, який дорівнює XOR двох
// унікальних чисел, оскільки всі числа, які з'являються двічі, знищаться (перетворяться на 0).
//Крок 2: Знаходимо наймолодший встановлений біт у результаті операції XOR. Цей біт допоможе нам розділити
// числа на дві групи так, щоб у кожній групі було по одному унікальному числу.
//Крок 3: Розділивши числа на дві групи, виконуємо операцію XOR у кожній групі. У результаті в кожній групі
// залишиться по одному унікальному числу, оскільки всі інші числа знищаться через XOR.
//Цей підхід забезпечує лінійну складність за часом (O(n)) і використовує тільки постійну кількість додаткової
// пам'яті, що робить його ефективним і придатним для даних умов задачі.

//Given an integer array nums, in which exactly two elements appear only once and all the other elements
// appear exactly twice. Find the two elements that appear only once. You can return the answer in any order.
//You must write an algorithm that runs in linear runtime complexity and uses only constant extra space.
//
//Example 1:
//Input: nums = [1,2,1,3,2,5]
//Output: [3,5]
//Explanation:  [5, 3] is also a valid answer.

//Example 2:
//Input: nums = [-1,0]
//Output: [-1,0]

//Example 3:
//Input: nums = [0,1]
//Output: [1,0]
//
//Constraints:
//2 <= nums.length <= 3 * 104
//-231 <= nums[i] <= 231 - 1
//Each integer in nums will appear twice, only two integers will appear once.