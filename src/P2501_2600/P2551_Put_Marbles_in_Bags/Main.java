package P2501_2600.P2551_Put_Marbles_in_Bags;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.putMarbles(new int[]{1, 3, 5, 1}, 2)); // Output: 4
        System.out.println(solution.putMarbles(new int[]{1, 3}, 2));       // Output: 0
    }

    public long putMarbles(int[] weights, int k) {
        int n = weights.length;
        if (k == 1) return 0; // if only one bag, min and max score are the same

        List<Integer> gaps = new ArrayList<>();

        //compute adjacent gaps
        for (int i = 0; i < n - 1; i++) {
            gaps.add(weights[i] + weights[i + 1]);
        }

        // sort gaps in ascending order
        Collections.sort(gaps);

        long minScore = 0, maxScore = 0;

        // sum the smallest (k-1) gaps for min score
        for (int i = 0; i < k - 1; i++) {
            minScore += gaps.get(i);
        }

        // sum the largest (k-1) gaps for max score
        for (int i = n - 2; i >= n - k; i--) {
            maxScore += gaps.get(i);
        }

        return maxScore - minScore;
    }

}

// Time complexity: O(n log n)
// Space complexity: O(n)


//You have k bags. You are given a 0-indexed integer array weights where weights[i] is the weight of the i-th
// marble. You are also given the integer k.
//Divide the marbles into the k bags according to the following rules:
//No bag is empty.
//If the ith marble and jth marble are in a bag, then all marbles with an index between the ith and j-th
// indices should also be in that same bag.
//If a bag consists of all the marbles with an index from i to j inclusively, then the cost of the bag is
// weights[i] + weights[j].
//The score after distributing the marbles is the sum of the costs of all the k bags.
//Return the difference between the maximum and minimum scores among marble distributions.

//Example 1:
//Input: weights = [1,3,5,1], k = 2
//Output: 4
//Explanation:
//The distribution [1],[3,5,1] results in the minimal score of (1+1) + (3+1) = 6.
//The distribution [1,3],[5,1], results in the maximal score of (1+3) + (5+1) = 10.
//Thus, we return their difference 10 - 6 = 4.

//Example 2:
//Input: weights = [1, 3], k = 2
//Output: 0
//Explanation: The only distribution possible is [1],[3].
//Since both the maximal and minimal score are the same, we return 0.

//Constraints:
//1 <= k <= weights.length <= 105
//1 <= weights[i] <= 109
