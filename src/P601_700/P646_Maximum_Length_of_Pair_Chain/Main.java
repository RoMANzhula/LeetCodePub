package P601_700.P646_Maximum_Length_of_Pair_Chain;

import java.util.Arrays;

public class Main {

    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> a[1] - b[1]); //sort by the second element in two pairs

        int count = 1; //we have as minimum one pair
        int currentRight = pairs[0][1]; //initialize with the right value(second element) of the first pair

        for (int i = 1; i < pairs.length; i++) { //iteration to next pairs after first
            if (pairs[i][0] > currentRight) { //compair first element of next pairs with second element of first pair
                count++; //plus 1 pair
                currentRight = pairs[i][1]; //reload second element of next pair
            }
        }

        return count; //congratulation
    }

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] pairs1 = {{1,2},{2,3},{3,4}};
        System.out.println(solution.findLongestChain(pairs1)); // Output: 2

        int[][] pairs2 = {{1,2},{7,8},{4,5}};
        System.out.println(solution.findLongestChain(pairs2)); // Output: 3
    }

}

//You are given an array of n pairs pairs where pairs[i] = [lefti, righti] and lefti < righti.
//A pair p2 = [c, d] follows a pair p1 = [a, b] if b < c. A chain of pairs can be formed in this fashion.
//Return the length longest chain which can be formed.
//You do not need to use up all the given intervals. You can select pairs in any order.

//Example 1:
//Input: pairs = [[1,2],[2,3],[3,4]]
//Output: 2
//Explanation: The longest chain is [1,2] -> [3,4].

//Example 2:
//Input: pairs = [[1,2],[7,8],[4,5]]
//Output: 3
//Explanation: The longest chain is [1,2] -> [4,5] -> [7,8].

//Constraints:
//n == pairs.length
//1 <= n <= 1000
//-1000 <= lefti < righti <= 1000
