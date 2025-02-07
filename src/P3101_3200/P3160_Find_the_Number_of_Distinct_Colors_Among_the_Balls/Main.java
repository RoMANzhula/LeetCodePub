package P3101_3200.P3160_Find_the_Number_of_Distinct_Colors_Among_the_Balls;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int limit1 = 4;
        int[][] queries1 = {{1, 4}, {2, 5}, {1, 3}, {3, 4}};
        System.out.println(Arrays.toString(solution.queryResults(limit1, queries1))); // Output: [1, 2, 2, 3]

        int limit2 = 4;
        int[][] queries2 = {{0, 1}, {1, 2}, {2, 2}, {3, 4}, {4, 5}};
        System.out.println(Arrays.toString(solution.queryResults(limit2, queries2))); // Output: [1, 2, 2, 3, 4]
    }

    public int[] queryResults(int limit, int[][] queries) {
        Map<Integer, Integer> ballColors = new HashMap<>();  // maps ball to its color
        Map<Integer, Integer> colorCount = new HashMap<>();  // maps color to its frequency
        List<Integer> result = new ArrayList<>();
        int distinctColors = 0;

        for (int[] query : queries) {
            int ball = query[0], newColor = query[1];

            if (ballColors.containsKey(ball)) {
                int oldColor = ballColors.get(ball);
                colorCount.put(oldColor, colorCount.get(oldColor) - 1);

                // remove from distinct colors if count drops to zero
                if (colorCount.get(oldColor) == 0) {
                    colorCount.remove(oldColor);
                    distinctColors--;
                }
            }

            ballColors.put(ball, newColor);
            colorCount.put(newColor, colorCount.getOrDefault(newColor, 0) + 1);

            if (colorCount.get(newColor) == 1) {
                distinctColors++;
            }

            result.add(distinctColors);
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

}

//O(n) Complexity: Each operation (insert/update) is O(1), making the overall complexity O(n).


//You are given an integer limit and a 2D array queries of size n x 2.
//There are limit + 1 balls with distinct labels in the range [0, limit]. Initially, all balls are uncolored. For
// every query in queries that is of the form [x, y], you mark ball x with the color y. After each query, you
// need to find the number of distinct colors among the balls.
//Return an array result of length n, where result[i] denotes the number of distinct colors after ith query.
//Note that when answering a query, lack of a color will not be considered as a color.
//
//Example 1:
//Input: limit = 4, queries = [[1,4],[2,5],[1,3],[3,4]]
//Output: [1,2,2,3]
//Explanation:
//After query 0, ball 1 has color 4.
//After query 1, ball 1 has color 4, and ball 2 has color 5.
//After query 2, ball 1 has color 3, and ball 2 has color 5.
//After query 3, ball 1 has color 3, ball 2 has color 5, and ball 3 has color 4.

//Example 2:
//Input: limit = 4, queries = [[0,1],[1,2],[2,2],[3,4],[4,5]]
//Output: [1,2,2,3,4]
//
//Explanation:
//After query 0, ball 0 has color 1.
//After query 1, ball 0 has color 1, and ball 1 has color 2.
//After query 2, ball 0 has color 1, and balls 1 and 2 have color 2.
//After query 3, ball 0 has color 1, balls 1 and 2 have color 2, and ball 3 has color 4.
//After query 4, ball 0 has color 1, balls 1 and 2 have color 2, ball 3 has color 4, and ball 4 has color 5.
//
//Constraints:
//1 <= limit <= 109
//1 <= n == queries.length <= 105
//queries[i].length == 2
//0 <= queries[i][0] <= limit
//1 <= queries[i][1] <= 109