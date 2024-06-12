package P1_100.P75_Sort_Colors;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {2, 0, 2, 1, 1, 0};
        solution.sortColors(nums1);
        System.out.println(java.util.Arrays.toString(nums1)); // Output: [0, 0, 1, 1, 2, 2]

        int[] nums2 = {2, 0, 1};
        solution.sortColors(nums2);
        System.out.println(java.util.Arrays.toString(nums2)); // Output: [0, 1, 2]
    }

    public void sortColors(int[] nums) {
        int low = 0;
        int mid = 0;
        int high = nums.length - 1;

        while (mid <= high) {
            switch (nums[mid]) {
                case 0:
                    // Swap nums[low] and nums[mid]
                    int temp0 = nums[low];
                    nums[low] = nums[mid];
                    nums[mid] = temp0;
                    low++;
                    mid++;
                    break;
                case 1:
                    mid++;
                    break;
                case 2:
                    // Swap nums[mid] and nums[high]
                    int temp2 = nums[mid];
                    nums[mid] = nums[high];
                    nums[high] = temp2;
                    high--;
                    break;
            }
        }
    }
}

//Задачу сортування масиву, що містить тільки числа 0, 1 і 2, можна вирішити за допомогою алгоритму "Голландський
// національний прапор", запропонованого Едсгером Дейкстрою. Цей алгоритм дозволяє відсортувати масив за один
// прохід і використовує лише постійний обсяг додаткової пам'яті.
//Пояснення алгоритму
//Ініціалізація:
//Ми вводимо три індекси: low (початково вказує на початок масиву), mid (також початково вказує на початок масиву) і
// high (вказує на кінець масиву).
//Індекс low відстежує позицію для наступного нуля (0), mid переміщається по масиву, а high відстежує позицію для
// наступної двійки (2).
//Прохід по масиву:
//Ми проходимо масив за допомогою індексу mid. В залежності від значення елемента nums[mid] виконуємо різні дії:
//Якщо nums[mid] дорівнює 0, то ми міняємо місцями елементи за індексами low та mid, потім збільшуємо обидва
// індекси low і mid.
//Якщо nums[mid] дорівнює 1, то просто збільшуємо індекс mid.
//Якщо nums[mid] дорівнює 2, то ми міняємо місцями елементи за індексами mid та high, потім зменшуємо індекс
// high (але не збільшуємо mid, оскільки елемент, що замінили, потрібно перевірити).
//Завершення:
//Цикл завершується, коли індекс mid перевищує high, забезпечуючи таким чином, що всі нулі (0) знаходяться
// перед усіма одиницями (1), а ті, в свою чергу, перед усіма двійками (2).
//Ключові моменти
//Однопрохідний алгоритм: Алгоритм проходить масив лише один раз, тому його часова складність становить O(n),
// де n — кількість елементів у масиві.
//Постійний обсяг додаткової пам'яті: Використовуються лише кілька змінних для відстеження позицій, тому просторову
// складність алгоритму можна вважати O(1).
//Сортування на місці: Масив змінюється безпосередньо без використання додаткової пам'яті для зберігання копій.
//Цей метод є ефективним для сортування масивів, що містять лише три різні значення, і забезпечує оптимальну
// продуктивність як за часом, так і за використанням пам'яті.

//Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the
// same color are adjacent, with the colors in the order red, white, and blue.
//We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
//You must solve this problem without using the library's sort function.
//
//Example 1:
//Input: nums = [2,0,2,1,1,0]
//Output: [0,0,1,1,2,2]

//Example 2:
//Input: nums = [2,0,1]
//Output: [0,1,2]
//
//Constraints:
//n == nums.length
//1 <= n <= 300
//nums[i] is either 0, 1, or 2.