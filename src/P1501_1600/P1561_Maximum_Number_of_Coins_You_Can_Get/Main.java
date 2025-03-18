package P1501_1600.P1561_Maximum_Number_of_Coins_You_Can_Get;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] piles1 = {2, 4, 1, 2, 7, 8};
        System.out.println("Output 1: " + maxCoins(piles1)); // Output: 9

        int[] piles2 = {2, 4, 5};
        System.out.println("Output 2: " + maxCoins(piles2)); // Output: 4

        int[] piles3 = {9, 8, 7, 6, 5, 1, 2, 3, 4};
        System.out.println("Output 3: " + maxCoins(piles3)); // Output: 18
    }

    public static int maxCoins(int[] piles) {
        Arrays.sort(piles);
        int n = piles.length / 3;
        int maxCoins = 0;

        for (int i = piles.length - 2; i >= n; i -= 2) {
            maxCoins += piles[i];
        }

        return maxCoins;
    }

}

//Рішення:
//Спочатку відсортуйте масив куп монет у порядку спадання.
//Після цього обирайте кожен третій елемент (враховуючи, що індексація починається з 0) і додаєте його до змінної, яка
// представляє ваш вибір.
//Максимальна кількість монет, яку ви отримаєте, дорівнює сумі всіх обраних вами куп.

//There are 3n piles of coins of varying size, you and your friends will take piles of coins as follows:
//In each step, you will choose any 3 piles of coins (not necessarily consecutive).
//Of your choice, Alice will pick the pile with the maximum number of coins.
//You will pick the next pile with the maximum number of coins.
//Your friend Bob will pick the last pile.
//Repeat until there are no more piles of coins.
//Given an array of integers piles where piles[i] is the number of coins in the ith pile.
//Return the maximum number of coins that you can have.

//Example 1:
//Input: piles = [2,4,1,2,7,8]
//Output: 9
//Explanation: Choose the triplet (2, 7, 8), Alice Pick the pile with 8 coins, you the pile with 7 coins and
// Bob the last one.
//Choose the triplet (1, 2, 4), Alice Pick the pile with 4 coins, you the pile with 2 coins and Bob the last one.
//The maximum number of coins which you can have are: 7 + 2 = 9.
//On the other hand if we choose this arrangement (1, 2, 8), (2, 4, 7) you only get 2 + 4 = 6 coins
// which is not optimal.

//Example 2:
//Input: piles = [2,4,5]
//Output: 4

//Example 3:
//Input: piles = [9,8,7,6,5,1,2,3,4]
//Output: 18

//Constraints:
//3 <= piles.length <= 105
//piles.length % 3 == 0
//1 <= piles[i] <= 104