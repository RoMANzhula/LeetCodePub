package P2201_2300.P2251_Number_of_Flowers_in_Fool_Bloom;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        int[][] flowers1 = {{1, 6}, {3, 7}, {9, 12}, {4, 13}};
        int[] people1 = {2, 3, 7, 11};
        int[] result1 = bloomFlowers(flowers1, people1);
        System.out.println(Arrays.toString(result1)); // Expected output: [1, 2, 2, 2]

        int[][] flowers2 = {{1, 10}, {3, 3}};
        int[] people2 = {3, 3, 2};
        int[] result2 = bloomFlowers(flowers2, people2);
        System.out.println(Arrays.toString(result2)); // Expected output: [2, 2, 1]
    }

    public static int[] bloomFlowers(int[][] flowers, int[] people) {
        int n = people.length;
        int[] result = new int[n];

        TreeMap<Integer, Integer> bloomCount = new TreeMap<>();

        for (int[] flower : flowers) {
            bloomCount.put(flower[0], bloomCount.getOrDefault(flower[0], 0) + 1);
            bloomCount.put(flower[1] + 1, bloomCount.getOrDefault(flower[1] + 1, 0) - 1);
        }

        int count = 0;
        for (Map.Entry<Integer, Integer> entry : bloomCount.entrySet()) {
            count += entry.getValue();
            entry.setValue(count);
        }

        for (int i = 0; i < n; i++) {
            int arrival = people[i];
            Map.Entry<Integer, Integer> floorEntry = bloomCount.floorEntry(arrival);
            result[i] = (floorEntry != null) ? floorEntry.getValue() : 0;
        }

        return result;
    }

}

//You are given a 0-indexed 2D integer array flowers, where flowers[i] = [starti, endi] means the ith flower will
// be in full bloom from starti to endi (inclusive). You are also given a 0-indexed integer array people of size n,
// where people[i] is the time that the ith person will arrive to see the flowers.
//Return an integer array answer of size n, where answer[i] is the number of flowers that are in full bloom when the
// ith person arrives.

//Example 1:
//Input: flowers = [[1,6],[3,7],[9,12],[4,13]], poeple = [2,3,7,11]
//Output: [1,2,2,2]
//Explanation: The figure above shows the times when the flowers are in full bloom and when the people arrive.
//For each person, we return the number of flowers in full bloom during their arrival.

//Example 2:
//Input: flowers = [[1,10],[3,3]], poeple = [3,3,2]
//Output: [2,2,1]
//Explanation: The figure above shows the times when the flowers are in full bloom and when the people arrive.
//For each person, we return the number of flowers in full bloom during their arrival.

//Constraints:
//1 <= flowers.length <= 5 * 104
//flowers[i].length == 2
//1 <= starti <= endi <= 109
//1 <= people.length <= 5 * 104
//1 <= people[i] <= 109
