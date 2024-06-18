package P801_900.P826_Most_Profit_Assigning_Work;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] difficulty1 = {2,4,6,8,10};
        int[] profit1 = {10,20,30,40,50};
        int[] worker1 = {4,5,6,7};
        System.out.println(solution.maxProfitAssignment(difficulty1, profit1, worker1)); // Output 100

        int[] difficulty2 = {85,47,57};
        int[] profit2 = {24,66,99};
        int[] worker2 = {40,25,25};
        System.out.println(solution.maxProfitAssignment(difficulty2, profit2, worker2)); // Output: 0
    }

    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int n = difficulty.length;
        int[][] jobs = new int[n][2];
        for (int i = 0; i < n; i++) {
            jobs[i][0] = difficulty[i];
            jobs[i][1] = profit[i];
        }

        // Sort jobs by their difficulty
        Arrays.sort(jobs, (a, b) -> Integer.compare(a[0], b[0]));

        // Sort workers by their ability
        Arrays.sort(worker);

        int maxProfit = 0;
        int bestProfit = 0;
        int jobIndex = 0;

        // Iterate through each worker
        for (int ability : worker) {
            // Find the best profit this worker can achieve
            while (jobIndex < n && jobs[jobIndex][0] <= ability) {
                bestProfit = Math.max(bestProfit, jobs[jobIndex][1]);
                jobIndex++;
            }
            maxProfit += bestProfit;
        }

        return maxProfit; // bingo
    }
}

//Explanation of the Code:
//Pairing Jobs: We create a jobs array where each element is a pair of difficulty and profit.
//Sorting Jobs and Workers: The jobs are sorted by difficulty, and the workers are sorted by their abilities.
//Max Profit Calculation:
//Initialize maxProfit to keep track of the total profit.
//Initialize bestProfit to keep track of the best profit a worker can achieve for their ability.
//Iterate through each worker and for each worker, update the bestProfit to the maximum profit they can get by
// checking jobs whose difficulty is less than or equal to the worker's ability.
//Add bestProfit to maxProfit for each worker.
//This approach ensures that each worker is assigned the most profitable job they can handle, resulting in the
// maximum total profit. The overall time complexity is O(n logn + m logm) due to the sorting steps, which is
// efficient for the given constraints.


//You have n jobs and m workers. You are given three arrays: difficulty, profit, and worker where:
//difficulty[i] and profit[i] are the difficulty and the profit of the ith job, and
//worker[j] is the ability of jth worker (i.e., the jth worker can only complete a job with difficulty at
// most worker[j]).
//Every worker can be assigned at most one job, but one job can be completed multiple times.
//For example, if three workers attempt the same job that pays $1, then the total profit will be $3. If a worker
// cannot complete any job, their profit is $0.
//Return the maximum profit we can achieve after assigning the workers to the jobs.
//
//Example 1:
//Input: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
//Output: 100
//Explanation: Workers are assigned jobs of difficulty [4,4,6,6] and they get a profit of [20,20,30,30] separately.

//Example 2:
//Input: difficulty = [85,47,57], profit = [24,66,99], worker = [40,25,25]
//Output: 0
//
//Constraints:
//n == difficulty.length
//n == profit.length
//m == worker.length
//1 <= n, m <= 104
//1 <= difficulty[i], profit[i], worker[i] <= 105