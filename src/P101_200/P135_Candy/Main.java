package P101_200.P135_Candy;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] ratings1 = {1, 0, 2}; //5
        int[] ratings2 = {1, 2, 2}; //4

        System.out.println(solution.minCandies(ratings1));
        System.out.println(solution.minCandies(ratings2));
    }

    public int minCandies(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];

        //initialize all children with 1 candy
        Arrays.fill(candies, 1);

        //traverse from left to right
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }

        //traverse from right to left and update if it necessary
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }

        //sum up the candies array to get the total minimum candies required
        int totalCandies = 0;
        for (int candy : candies) {
            totalCandies += candy;
        }

        return totalCandies; //bingo
    }

}

//There are n children standing in a line. Each child is assigned a rating value given in the integer array ratings.
//You are giving candies to these children subjected to the following requirements:
//Each child must have at least one candy.
//Children with a higher rating get more candies than their neighbors.
//Return the minimum number of candies you need to have to distribute the candies to the children.

//Example 1:
//Input: ratings = [1,0,2]
//Output: 5
//Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.

//Example 2:
//Input: ratings = [1,2,2]
//Output: 4
//Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
//The third child gets 1 candy because it satisfies the above two conditions.

//Constraints:
//n == ratings.length
//1 <= n <= 2 * 104
//0 <= ratings[i] <= 2 * 104