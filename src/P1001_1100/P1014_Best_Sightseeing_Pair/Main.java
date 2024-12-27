package P1001_1100.P1014_Best_Sightseeing_Pair;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] values1 = {8, 1, 5, 2, 6};
        System.out.println("Maximum Score (Example 1): " + solution.maxScoreSightseeingPair(values1)); // 11

        int[] values2 = {1, 2};
        System.out.println("Maximum Score (Example 2): " + solution.maxScoreSightseeingPair(values2)); // 2
    }

    public int maxScoreSightseeingPair(int[] values) {
        int maxScore = 0;
        int maxLeft = values[0]; // keep track of the maximum values[i] + i seen so far

        for (int i = 1; i < values.length; i++) {
            //calculate the score for the current pair
            maxScore = Math.max(maxScore, maxLeft + values[i] - i);

            // update maxLeft to include the current index i
            maxLeft = Math.max(maxLeft, values[i] + i);
        }

        return maxScore;
    }
}

//Explanation of the Code
//Maintain Maximum values[i] + i:
//-We keep track of the maximum values[i]+i as maxLeft while iterating through the array.
//Calculate Score for Each j:
//-For every j, calculate the score using maxLeft + values maxLeft+values[j]−j, and update the maximum score if this
// score is greater.
//Update maxLeft:
//-Update maxLeft to include the contribution of the current j, which is values[j]+j.
//Complexity:
//-Time Complexity:
//O(n), where n is the size of the array.
//-Space Complexity:
//O(1), as we only use a few variables for tracking the maximum score and maxLeft.


//You are given an integer array values where values[i] represents the value of the ith sightseeing spot. Two
// sightseeing spots i and j have a distance j - i between them.
//The score of a pair (i < j) of sightseeing spots is values[i] + values[j] + i - j: the sum of the values of
// the sightseeing spots, minus the distance between them.
//Return the maximum score of a pair of sightseeing spots.
//
//Example 1:
//Input: values = [8,1,5,2,6]
//Output: 11
//Explanation: i = 0, j = 2, values[i] + values[j] + i - j = 8 + 5 + 0 - 2 = 11

//Example 2:
//Input: values = [1,2]
//Output: 2
//
//Constraints:
//2 <= values.length <= 5 * 104
//1 <= values[i] <= 1000
