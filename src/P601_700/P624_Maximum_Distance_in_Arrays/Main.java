package P601_700.P624_Maximum_Distance_in_Arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        // Example 1
        List<List<Integer>> arrays1 = new ArrayList<>();
        arrays1.add(Arrays.asList(1, 2, 3));
        arrays1.add(Arrays.asList(4, 5));
        arrays1.add(Arrays.asList(1, 2, 3));
        int result1 = solution.maxDistance(arrays1);
        System.out.println("Output: " + result1); // Expected output: 4

        // Example 2
        List<List<Integer>> arrays2 = new ArrayList<>();
        arrays2.add(Arrays.asList(1));
        arrays2.add(Arrays.asList(1));
        int result2 = solution.maxDistance(arrays2);
        System.out.println("Output: " + result2); // Expected output: 0
    }

    public int maxDistance(List<List<Integer>> arrays) {
        int min_value = arrays.get(0).get(0);
        int max_value = arrays.get(0).get(arrays.get(0).size() - 1);
        int max_distance = 0;

        for (int i = 1; i < arrays.size(); i++) {
            List<Integer> currentArray = arrays.get(i);
            int currentMin = currentArray.get(0);
            int currentMax = currentArray.get(currentArray.size() - 1);

            // Calculate potential maximum distances
            max_distance = Math.max(max_distance, Math.abs(currentMax - min_value));
            max_distance = Math.max(max_distance, Math.abs(max_value - currentMin));

            // Update min_value and max_value for future iterations
            min_value = Math.min(min_value, currentMin);
            max_value = Math.max(max_value, currentMax);
        }

        return max_distance;
    }
}

//Explanation:
//Initial Values: We start by initializing min_value and max_value with the first array's minimum and
// maximum, respectively.
//Iterating Arrays: As we iterate through each array from the second one onwards:
//We compute the maximum possible distance by considering the difference between the current array's
// extremes (currentMin and currentMax) and the previously recorded min_value and max_value.
//We update max_distance accordingly.
//We also update min_value and max_value for future comparisons.
//Result: The maximum distance encountered during the iteration is returned.
//This approach ensures that we efficiently find the maximum distance by leveraging the fact that the arrays are
//already sorted, leading to an overall time complexity of O(m), where m is the number of arrays.


//You are given m arrays, where each array is sorted in ascending order.
//You can pick up two integers from two different arrays (each array picks one) and calculate the distance. We
// define the distance between two integers a and b to be their absolute difference |a - b|.
//Return the maximum distance.
//
//Example 1:
//Input: arrays = [[1,2,3],[4,5],[1,2,3]]
//Output: 4
//Explanation: One way to reach the maximum distance 4 is to pick 1 in the first or third array and
// pick 5 in the second array.

//Example 2:
//Input: arrays = [[1],[1]]
//Output: 0
//
//Constraints:
//m == arrays.length
//2 <= m <= 105
//1 <= arrays[i].length <= 500
//-104 <= arrays[i][j] <= 104
//arrays[i] is sorted in ascending order.
//There will be at most 105 integers in all the arrays.