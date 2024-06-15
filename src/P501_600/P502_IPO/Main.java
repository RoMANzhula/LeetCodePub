package P501_600.P502_IPO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int k1 = 2;
        int w1 = 0;
        int[] profits1 = {1, 2, 3};
        int[] capital1 = {0, 1, 1};
        System.out.println(solution.findMaximizedCapital(k1, w1, profits1, capital1)); // Output: 4

        int k2 = 3;
        int w2 = 0;
        int[] profits2 = {1, 2, 3};
        int[] capital2 = {0, 1, 2};
        System.out.println(solution.findMaximizedCapital(k2, w2, profits2, capital2)); // Output: 6
    }

    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        // Create a list of projects
        List<int[]> projects = new ArrayList<>();
        for (int i = 0; i < profits.length; i++) {
            projects.add(new int[]{capital[i], profits[i]});
        }

        // Sort projects based on the capital required (ascending order)
        Collections.sort(projects, (a, b) -> a[0] - b[0]);

        // Max heap for storing profits of the projects that can be started
        PriorityQueue<Integer> maxProfitHeap = new PriorityQueue<>(Collections.reverseOrder());

        int currentCapital = w;
        int projectIndex = 0;

        for (int i = 0; i < k; i++) {
            // Move all projects that can be started with the current capital to the max heap
            while (projectIndex < projects.size() && projects.get(projectIndex)[0] <= currentCapital) {
                maxProfitHeap.add(projects.get(projectIndex)[1]);
                projectIndex++;
            }

            // If there are no projects that can be started, break out of the loop
            if (maxProfitHeap.isEmpty()) {
                break;
            }

            // Select the project with the maximum profit
            currentCapital += maxProfitHeap.poll();
        }

        return currentCapital;
    }
}

//Explanation:
//Sorting Projects: Projects are first sorted by their capital requirements. This helps in efficiently finding
// all projects that can be started with the current capital.
//Max Heap for Profits: We use a max heap to always select the most profitable project that can currently be started.
//Loop for k Projects: We iterate up to k times, each time adding the most profitable project that can be started to
// the current capital.
//Adding Profitable Projects: Within the loop, we add all projects that can currently be started to the max
// heap and then pick the most profitable one to execute.
//This solution ensures that at each step, the most profitable project is chosen, thus maximizing the
// capital efficiently.


//Suppose LeetCode will start its IPO soon. In order to sell a good price of its shares to Venture Capital,
// LeetCode would like to work on some projects to increase its capital before the IPO. Since it has limited
// resources, it can only finish at most k distinct projects before the IPO. Help LeetCode design the best
// way to maximize its total capital after finishing at most k distinct projects.
//You are given n projects where the ith project has a pure profit profits[i] and a minimum capital of capital[i]
// is needed to start it.
//Initially, you have w capital. When you finish a project, you will obtain its pure profit and the profit will be
//added to your total capital.
//Pick a list of at most k distinct projects from given projects to maximize your final capital, and return the
// final maximized capital.
//The answer is guaranteed to fit in a 32-bit signed integer.
//
//Example 1:
//Input: k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]
//Output: 4
//Explanation: Since your initial capital is 0, you can only start the project indexed 0.
//After finishing it you will obtain profit 1 and your capital becomes 1.
//With capital 1, you can either start the project indexed 1 or the project indexed 2.
//Since you can choose at most 2 projects, you need to finish the project indexed 2 to get the maximum capital.
//Therefore, output the final maximized capital, which is 0 + 1 + 3 = 4.

//Example 2:
//Input: k = 3, w = 0, profits = [1,2,3], capital = [0,1,2]
//Output: 6
//
//Constraints:
//1 <= k <= 105
//0 <= w <= 109
//n == profits.length
//n == capital.length
//1 <= n <= 105
//0 <= profits[i] <= 104
//0 <= capital[i] <= 109
