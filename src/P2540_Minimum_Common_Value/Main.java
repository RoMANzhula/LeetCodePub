package P2540_Minimum_Common_Value;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 2, 3, 6};
        int[] nums2 = {2, 3, 4, 5};
        int result1 = solution.getCommon(nums1, nums2);
        System.out.println("Minimum common integer: " + result1); // output 2

        int[] nums3 = {1,2,3,6};
        int[] nums4 = {2,3,4,5};
        int result2 = solution.getCommon(nums3, nums4);
        System.out.println("Minimum common integer: " + result2); // output 2
    }

    public int getCommon(int[] nums1, int[] nums2) {
        int i = 0, j = 0;

        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                return nums1[i];
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }

        return -1; // No common integer found
    }
}

//У цій задачі ми маємо два відсортованих масиви цілих чисел nums1 та nums2, які можуть містити спільні елементи. Наша
// мета - знайти найменше спільне ціле число у цих двох масивах. Якщо ж жодного спільного числа не знайдено,
// повертаємо -1.
//Основна ідея розв'язку полягає в тому, що ми проходимося одночасно по обох масивах. Ми маємо два показники,
// один для кожного масиву: i для nums1 та j для nums2. Починаємо з першого елементу в кожному масиві.
//Проходимося по масивам, порівнюючи елементи на кожній позиції. Якщо елементи на поточних позиціях в обох
// масивах рівні, це означає, що ми знайшли спільне число, і повертаємо його. Якщо елемент в nums1[i] менший,
// ніж елемент в nums2[j], то збільшуємо i, щоб перевірити наступний елемент в nums1. Аналогічно, якщо nums2[j] менший,
// ніж nums1[i], то збільшуємо j.
//Якщо ми дійшли до кінця хоча б одного з масивів і не знайшли спільного числа, то повертаємо -1, оскільки
// жодного спільного числа між масивами немає.
//В цьому розв'язку ми використовуємо лише один цикл, що проймається через обидва масиви одночасно, що дозволяє
// нам знайти мінімальне спільне число зі складності O(n), де n - це сума довжин двох масивів.


//Given two integer arrays nums1 and nums2, sorted in non-decreasing order, return the minimum integer common
// to both arrays. If there is no common integer amongst nums1 and nums2, return -1.
//Note that an integer is said to be common to nums1 and nums2 if both arrays have at least one occurrence
// of that integer.
//
//Example 1:
//Input: nums1 = [1,2,3], nums2 = [2,4]
//Output: 2
//Explanation: The smallest element common to both arrays is 2, so we return 2.

//Example 2:
//Input: nums1 = [1,2,3,6], nums2 = [2,3,4,5]
//Output: 2
//Explanation: There are two common elements in the array 2 and 3 out of which 2 is the smallest, so 2 is returned.
//
//Constraints:
//1 <= nums1.length, nums2.length <= 105
//1 <= nums1[i], nums2[j] <= 109
//Both nums1 and nums2 are sorted in non-decreasing order.