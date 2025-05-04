package P1101_1200.P1128_Number_of_Equivalent_Domino_Pairs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] dominoes1 = {{1,2},{2,1},{3,4},{5,6}};
        int[][] dominoes2 = {{1,2},{1,2},{1,1},{1,2},{2,2}};

        System.out.println(solution.numEquivDominoPairs(dominoes1)); // Output: 1
        System.out.println(solution.numEquivDominoPairs(dominoes2)); // Output: 3
    }

//    public int numEquivDominoPairs(int[][] dominoes) {
//        Map<Integer, Integer> map = new HashMap<>();
//        int count = 0;
//
//        for (int[] domino : dominoes) {
//            int a = domino[0];
//            int b = domino[1];
//            // normalize the domino: smaller number first
//            int key = a < b ? a * 10 + b : b * 10 + a;
//
//            // jf this key already exists, it means we've seen it `val` times before
//            // so we can make `val` new pairs with this one
//            count += map.getOrDefault(key, 0);
//
//            //increment count for this key
//            map.put(key, map.getOrDefault(key, 0) + 1);
//        }
//
//        return count;
//    }

    public int numEquivDominoPairs(int[][] dominoes) {
        int[] count = new int[100];
        int result = 0;

        for (int i = 0; i < dominoes.length; i++) {
            int a = dominoes[i][0];
            int b = dominoes[i][1];

            int key = a < b ? a * 10 + b : b * 10 + a;

            result += count[key];
            count[key]++;
        }

        return result;
    }

}

//Complexity:
// time - O(n)
// space - O(1)


//Given a list of dominoes, dominoes[i] = [a, b] is equivalent to dominoes[j] = [c, d] if and only if
// either (a == c and b == d), or (a == d and b == c) - that is, one domino can be rotated to
// be equal to another domino.
//Return the number of pairs (i, j) for which 0 <= i < j < dominoes.length, and dominoes[i] is
// equivalent to dominoes[j].

//Example 1:
//Input: dominoes = [[1,2],[2,1],[3,4],[5,6]]
//Output: 1

//Example 2:
//Input: dominoes = [[1,2],[1,2],[1,1],[1,2],[2,2]]
//Output: 3

//Constraints:
//1 <= dominoes.length <= 4 * 104
//dominoes[i].length == 2
//1 <= dominoes[i][j] <= 9
