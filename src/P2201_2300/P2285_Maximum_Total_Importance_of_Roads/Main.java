package P2201_2300.P2285_Maximum_Total_Importance_of_Roads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int n1 = 5;
        int[][] roads1 = {{0,1},{1,2},{2,3},{0,2},{1,3},{2,4}};
        System.out.println(solution.maximumImportance(n1, roads1)); // Output: 43

        int n2 = 5;
        int[][] roads2 = {{0,3},{2,4},{1,3}};
        System.out.println(solution.maximumImportance(n2, roads2)); // Output: 20
    }

    public long maximumImportance(int n, int[][] roads) {
        // Step 1: Count the number of roads each city is part of
        int[] degree = new int[n];
        for (int[] road : roads) {
            degree[road[0]]++;
            degree[road[1]]++;
        }

        // Step 2: Create a list of cities with their degrees
        List<int[]> cities = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            cities.add(new int[]{degree[i], i});
        }

        // Step 3: Sort cities by their degrees in descending order
        Collections.sort(cities, (a, b) -> b[0] - a[0]);

        // Step 4: Assign values to cities, highest to lowest degree
        int[] value = new int[n];
        for (int i = 0; i < n; i++) {
            value[cities.get(i)[1]] = n - i;
        }

        // Step 5: Calculate the total importance of all roads
        long totalImportance = 0;
        for (int[] road : roads) {
            totalImportance += value[road[0]] + value[road[1]];
        }

        return totalImportance;
    }
}

//Explanation
//Count the number of roads each city is part of: We use an array degree to count how many roads each
// city is connected to.
//Sort cities by their degrees: We create a list of cities and sort them based on their degrees in descending
// order. This way, cities with more connections come first.
//Assign values to cities: We assign the highest values to the cities with the most connections.
//Calculate the total importance: We compute the total importance by summing the importances of all roads
// based on the assigned values.
//This approach ensures that we maximize the total importance of all roads by assigning higher values
// to the most connected cities.


//You are given an integer n denoting the number of cities in a country. The cities are numbered from 0 to n - 1.
//You are also given a 2D integer array roads where roads[i] = [ai, bi] denotes that there exists a bidirectional
// road connecting cities ai and bi.
//You need to assign each city with an integer value from 1 to n, where each value can only be used once. The
// importance of a road is then defined as the sum of the values of the two cities it connects.
//Return the maximum total importance of all roads possible after assigning the values optimally.
//
//Example 1:
//Input: n = 5, roads = [[0,1],[1,2],[2,3],[0,2],[1,3],[2,4]]
//Output: 43
//Explanation: The figure above shows the country and the assigned values of [2,4,5,3,1].
//- The road (0,1) has an importance of 2 + 4 = 6.
//- The road (1,2) has an importance of 4 + 5 = 9.
//- The road (2,3) has an importance of 5 + 3 = 8.
//- The road (0,2) has an importance of 2 + 5 = 7.
//- The road (1,3) has an importance of 4 + 3 = 7.
//- The road (2,4) has an importance of 5 + 1 = 6.
//The total importance of all roads is 6 + 9 + 8 + 7 + 7 + 6 = 43.
//It can be shown that we cannot obtain a greater total importance than 43.

//Example 2:
//Input: n = 5, roads = [[0,3],[2,4],[1,3]]
//Output: 20
//Explanation: The figure above shows the country and the assigned values of [4,3,2,5,1].
//- The road (0,3) has an importance of 4 + 5 = 9.
//- The road (2,4) has an importance of 2 + 1 = 3.
//- The road (1,3) has an importance of 3 + 5 = 8.
//The total importance of all roads is 9 + 3 + 8 = 20.
//It can be shown that we cannot obtain a greater total importance than 20.
//
//Constraints:
//2 <= n <= 5 * 104
//1 <= roads.length <= 5 * 104
//roads[i].length == 2
//0 <= ai, bi <= n - 1
//ai != bi
//There are no duplicate roads.