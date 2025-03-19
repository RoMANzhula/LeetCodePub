package P1601_1700.P1630_Arithmetic_Subarrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        int[] nums1 = {4, 6, 5, 9, 3, 7};
        int[] l1 = {0, 0, 2};
        int[] r1 = {2, 3, 5};
        System.out.println(checkArithmeticSubarrays(nums1, l1, r1));

        int[] nums2 = {-12, -9, -3, -12, -6, 15, 20, -25, -20, -15, -10};
        int[] l2 = {0, 1, 6, 4, 8, 7};
        int[] r2 = {4, 4, 9, 7, 9, 10};
        System.out.println(checkArithmeticSubarrays(nums2, l2, r2));
    }

    public static List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        int m = l.length;
        List<Boolean> result = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int[] subarray = Arrays.copyOfRange(nums, l[i], r[i] + 1);
            Arrays.sort(subarray);

            result.add(isArithmetic(subarray));
        }

        return result;
    }

    private static boolean isArithmetic(int[] subarray) {
        int diff = subarray[1] - subarray[0];

        for (int i = 2; i < subarray.length; i++) {
            if (subarray[i] - subarray[i - 1] != diff) {
                return false;
            }
        }

        return true;
    }

}

//Цей метод приймає три масиви як вхідні дані: nums (масив чисел), l (масив лівих індексів для підмасивів),
// та r (масив правих індексів для підмасивів).
//Метод ініціалізує пустий список List<Boolean> під назвою result, щоб зберігати результати для кожного підмасиву.
//Потім він проходить через кожен запит (кожну пару лівого та правого індексу) за допомогою циклу for.
//Для кожного запиту він створює підмасив за допомогою Arrays.copyOfRange(nums, l[i], r[i] + 1). Цей підмасив
// представляє частину вихідного масиву, визначену поточним запитом.
//Він сортує підмасив за допомогою Arrays.sort(subarray), оскільки для арифметичної послідовності порядок елементів
// не має значення.
//Потім він викликає метод isArithmetic для перевірки того, чи є підмасив арифметичною послідовністю, і додає
// результат до списку result.
//isArithmetic Метод:
//Цей метод приймає масив як вхід і перевіряє, чи він формує арифметичну послідовність.
//Він обчислює різницю між другим і першим елементами (int diff = subarray[1] - subarray[0]).
//Потім він пробігається по підмасиву, починаючи з третього елемента, перевіряючи, чи різниця між послідовними
// елементами однакова, як розрахована diff. Якщо на якій-небудь ітерації різниця не однакова, метод повертає false.
//Якщо цикл завершується без повернення false, це означає, що підмасив є арифметичною послідовністю, і метод
// повертає true.
//Тестування в головному методі:
//Головний метод демонструє, як використовувати метод checkArithmeticSubarrays з прикладами вхідних масивів та
// виводить результати.

//A sequence of numbers is called arithmetic if it consists of at least two elements, and the difference between
// every two consecutive elements is the same. More formally, a sequence s is arithmetic if and only if
// s[i+1] - s[i] == s[1] - s[0] for all valid i.
//For example, these are arithmetic sequences:
//1, 3, 5, 7, 9
//7, 7, 7, 7
//3, -1, -5, -9
//The following sequence is not arithmetic:
//1, 1, 2, 5, 7
//You are given an array of n integers, nums, and two arrays of m integers each, l and r, representing the m
// range queries, where the ith query is the range [l[i], r[i]]. All the arrays are 0-indexed.
//Return a list of boolean elements answer, where answer[i] is true if the subarray
// nums[l[i]], nums[l[i]+1], ... , nums[r[i]] can be rearranged to form an arithmetic sequence, and false otherwise.

//Example 1:
//Input: nums = [4,6,5,9,3,7], l = [0,0,2], r = [2,3,5]
//Output: [true,false,true]
//Explanation:
//In the 0th query, the subarray is [4,6,5]. This can be rearranged as [6,5,4], which is an arithmetic sequence.
//In the 1st query, the subarray is [4,6,5,9]. This cannot be rearranged as an arithmetic sequence.
//In the 2nd query, the subarray is [5,9,3,7]. This can be rearranged as [3,5,7,9], which is an arithmetic sequence.

//Example 2:
//Input: nums = [-12,-9,-3,-12,-6,15,20,-25,-20,-15,-10], l = [0,1,6,4,8,7], r = [4,4,9,7,9,10]
//Output: [false,true,false,false,true,true]

//Constraints:
//n == nums.length
//m == l.length
//m == r.length
//2 <= n <= 500
//1 <= m <= 500
//0 <= l[i] < r[i] < n
//-105 <= nums[i] <= 105