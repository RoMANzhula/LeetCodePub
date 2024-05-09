package P3001_3100.P3075_Maximize_Happiness_of_Selected_Children;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] happiness1 = {1, 2, 3};
        int k1 = 2;
        System.out.println(solution.maximumHappinessSum(happiness1, k1)); // output: 4

        int[] happiness2 = {1, 1, 1, 1};
        int k2 = 2;
        System.out.println(solution.maximumHappinessSum(happiness2, k2)); // output: 1

        int[] happiness3 = {2, 3, 4, 5};
        int k3 = 1;
        System.out.println(solution.maximumHappinessSum(happiness3, k3)); // output: 5
    }

//    public int maximumHappinessSum(int[] happiness, int k) {
////        int[] dp = new int[k + 1];
////
////        for (int value : happiness) {
////            for (int j = k; j >= 1; j--) {
////                dp[j] = Math.max(dp[j], dp[j - 1] + Math.max(value - (j - 1), 0));
////            }
////        }
////
////        return dp[k];
//
//        int ans = 0;
//        int decremented = 0;
//
//        Arrays.sort(happiness);
//        reverse(happiness);
//
//        for (int i = 0; i < k; i++) {
//            ans += Math.max(0, happiness[i] - decremented);
//            decremented++;
//        }
//
//        return ans;
//    }
//
//    private void reverse(int[] arr) {
//        int start = 0;
//        int end = arr.length - 1;
//        while (start < end) {
//            int temp = arr[start];
//            arr[start] = arr[end];
//            arr[end] = temp;
//            start++;
//            end--;
//        }
//    }

    //faster solve
    public long maximumHappinessSum(int[] happiness, int k) {
        long ans = 0;
        int decremented = 0;

        Arrays.sort(happiness);

        for (int i = 0; i < k; i++) {
            ans += Math.max(0, happiness[happiness.length - 1 - i] - decremented);
            decremented++;
        }

        return ans;
    }
}

//Ця програма призначена для максимізації суми щастя серед вибраних дітей зі списку, з урахуванням обмеження
// кількості вибраних дітей k.
//рішення:
//Ми починаємо з сортування масиву happiness, щоб мати можливість вибирати дітей з найбільшим щастям.
//Змінна ans відповідає за загальну суму щастя вибраних дітей, і початкове значення цієї змінної - 0.
//Змінна decremented відповідає за кількість зменшень щастя для невибраних дітей. Її початкове значення також - 0.
//Ми проходимося по перших k елементах масиву happiness (це найбільші значення щастя).
//Для кожної вибраної дитини ми додаємо до ans максимум із нуля та різниці між щастям цієї дитини та decremented. Це
// гарантує, що ми додаємо тільки невід'ємні значення до ans.
//Після вибору кожної дитини ми збільшуємо decremented на 1, оскільки всі невибрані діти отримують на 1 менше щастя.
//Після завершення циклу ми повертаємо значення ans, яке і буде максимальною сумою щастя серед вибраних дітей.

//You are given an array happiness of length n, and a positive integer k.
//There are n children standing in a queue, where the ith child has happiness value happiness[i]. You want to
// select k children from these n children in k turns.
//In each turn, when you select a child, the happiness value of all the children that have not been selected till
// now decreases by 1. Note that the happiness value cannot become negative and gets decremented only if it is positive.
//Return the maximum sum of the happiness values of the selected children you can achieve by selecting k children.
//
//Example 1:
//Input: happiness = [1,2,3], k = 2
//Output: 4
//Explanation: We can pick 2 children in the following way:
//- Pick the child with the happiness value == 3. The happiness value of the remaining children becomes [0,1].
//- Pick the child with the happiness value == 1. The happiness value of the remaining child becomes [0]. Note that
// the happiness value cannot become less than 0.
//The sum of the happiness values of the selected children is 3 + 1 = 4.

//Example 2:
//Input: happiness = [1,1,1,1], k = 2
//Output: 1
//Explanation: We can pick 2 children in the following way:
//- Pick any child with the happiness value == 1. The happiness value of the remaining children becomes [0,0,0].
//- Pick the child with the happiness value == 0. The happiness value of the remaining child becomes [0,0].
//The sum of the happiness values of the selected children is 1 + 0 = 1.

//Example 3:
//Input: happiness = [2,3,4,5], k = 1
//Output: 5
//Explanation: We can pick 1 child in the following way:
//- Pick the child with the happiness value == 5. The happiness value of the remaining children becomes [1,2,3].
//The sum of the happiness values of the selected children is 5.
//
//Constraints:
//1 <= n == happiness.length <= 2 * 105
//1 <= happiness[i] <= 108
//1 <= k <= n
