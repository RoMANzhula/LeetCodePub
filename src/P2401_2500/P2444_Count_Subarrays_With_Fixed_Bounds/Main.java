package P2401_2500.P2444_Count_Subarrays_With_Fixed_Bounds;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1, 3, 5, 2, 7, 5};
        int minK1 = 1;
        int maxK1 = 5;
        System.out.println("Example 1 Output: " + solution.countSubarrays(nums1, minK1, maxK1)); //output 2

        int[] nums2 = {1, 1, 1, 1};
        int minK2 = 1;
        int maxK2 = 1;
        System.out.println("Example 2 Output: " + solution.countSubarrays(nums2, minK2, maxK2)); //output 10
    }

    public long countSubarrays(int[] nums, int minK, int maxK) {
        long answer = 0;
        int j = -1;
        int prevMinKIndex = -1;
        int prevMaxKIndex = -1;

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];

            if (num < minK || num > maxK) {
                j = i;
            }

            if (num == minK) {
                prevMinKIndex = i;
            }

            if (num == maxK) {
                prevMaxKIndex = i;
            }

            answer += Math.max(0, Math.min(prevMinKIndex, prevMaxKIndex) - j);

        }

        return answer;
    }
}

//Ця функція призначена для підрахунку кількості підмасивів у вхідному масиві nums, що містять елементи, що не
// менші за minK та не більші за maxK. Однак передбачається, що nums містить цілі числа.
//Основна ідея полягає в тому, щоб визначити всі можливі підмасиви, які задовольняють умовам minK та
// maxK. Використовується техніка двох вказівників для відстеження границь поточного підмасиву.
//Змінна j вказує на останній елемент, що не задовольняє умові (тобто елемент, менший за minK або більший за maxK).
//Змінні prevMinKIndex та prevMaxKIndex вказують на останній елемент, який дорівнює minK або maxK, відповідно.
//Таким чином, кожен раз, коли зустрічається новий елемент, перевіряється, чи він задовольняє умови minK або maxK, і
// відповідно оновлюється значення prevMinKIndex або prevMaxKIndex. Після цього рахується кількість потенційних
// підмасивів, що можуть бути створені між позиціями j та Math.min(prevMinKIndex, prevMaxKIndex).
//Врешті-решт, результат додається до змінної answer, яка потім повертається.
//Це рішення ефективно, оскільки ми перебираємо кожен елемент масиву nums лише один раз, тому часова складність
// цієї функції становить O(n), де n - довжина масиву nums.

//You are given an integer array nums and two integers minK and maxK.
//A fixed-bound subarray of nums is a subarray that satisfies the following conditions:
//The minimum value in the subarray is equal to minK.
//The maximum value in the subarray is equal to maxK.
//Return the number of fixed-bound subarrays.
//A subarray is a contiguous part of an array.
//
//Example 1:
//Input: nums = [1,3,5,2,7,5], minK = 1, maxK = 5
//Output: 2
//Explanation: The fixed-bound subarrays are [1,3,5] and [1,3,5,2].

//Example 2:
//Input: nums = [1,1,1,1], minK = 1, maxK = 1
//Output: 10
//Explanation: Every subarray of nums is a fixed-bound subarray. There are 10 possible subarrays.
//
//Constraints:
//2 <= nums.length <= 105
//1 <= nums[i], minK, maxK <= 106