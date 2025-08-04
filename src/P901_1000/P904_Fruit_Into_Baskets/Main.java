package P901_1000.P904_Fruit_Into_Baskets;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.totalFruit(new int[]{1, 2, 1}));       // Output: 3
        System.out.println(solution.totalFruit(new int[]{0, 1, 2, 2}));    // Output: 3
        System.out.println(solution.totalFruit(new int[]{1, 2, 3, 2, 2})); // Output: 4
    }

    public int totalFruit(int[] fruits) {
        Map<Integer, Integer> basket = new HashMap<>();
        int left = 0, maxFruits = 0;

        for (int right = 0; right < fruits.length; right++) {
            // add current fruit to basket
            basket.put(fruits[right], basket.getOrDefault(fruits[right], 0) + 1);

            // shrink window if more than 2 types
            while (basket.size() > 2) {
                basket.put(fruits[left], basket.get(fruits[left]) - 1);
                if (basket.get(fruits[left]) == 0) {
                    basket.remove(fruits[left]);
                }
                left++;
            }

            // update max fruits collected
            maxFruits = Math.max(maxFruits, right - left + 1);
        }

        return maxFruits;
    }

}

//Complexity:
// time - O(n)
// space - O(1)


//You are visiting a farm that has a single row of fruit trees arranged from left to right. The trees are
// represented by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.
//You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:
//You only have two baskets, and each basket can only hold a single type of fruit. There is no limit on the amount of
// fruit each basket can hold.
//Starting from any tree of your choice, you must pick exactly one fruit from every tree (including the start tree)
// while moving to the right. The picked fruits must fit in one of your baskets.
//Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
//Given the integer array fruits, return the maximum number of fruits you can pick.

//Example 1:
//Input: fruits = [1,2,1]
//Output: 3
//Explanation: We can pick from all 3 trees.

//Example 2:
//Input: fruits = [0,1,2,2]
//Output: 3
//Explanation: We can pick from trees [1,2,2].
//If we had started at the first tree, we would only pick from trees [0,1].

//Example 3:
//Input: fruits = [1,2,3,2,2]
//Output: 4
//Explanation: We can pick from trees [2,3,2,2].
//If we had started at the first tree, we would only pick from trees [1,2].

//Constraints:
//1 <= fruits.length <= 105
//0 <= fruits[i] < fruits.length
