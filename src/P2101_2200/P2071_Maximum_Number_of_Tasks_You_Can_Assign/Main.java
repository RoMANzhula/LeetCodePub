package P2101_2200.P2071_Maximum_Number_of_Tasks_You_Can_Assign;

import java.util.Arrays;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.maxTaskAssign(new int[]{3, 2, 1}, new int[]{0, 3, 3}, 1, 1)); // Output: 3
        System.out.println(solution.maxTaskAssign(new int[]{5, 4}, new int[]{0, 0, 0}, 1, 5));     // Output: 1
        System.out.println(solution.maxTaskAssign(new int[]{10, 15, 30}, new int[]{0, 10, 10, 10, 10}, 3, 10)); // Output: 2
    }

    public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
        Arrays.sort(tasks);
        Arrays.sort(workers);

        int low = 0, high = Math.min(tasks.length, workers.length), result = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canAssign(mid, tasks, workers, pills, strength)) {
                result = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    private boolean canAssign(int k, int[] tasks, int[] workers, int pills, int strength) {
        TreeMap<Integer, Integer> workerMap = new TreeMap<>();
        int n = workers.length;

        // add k strongest workers to TreeMap
        for (int i = n - k; i < n; i++) {
            workerMap.put(workers[i], workerMap.getOrDefault(workers[i], 0) + 1);
        }

        for (int i = k - 1; i >= 0; i--) {
            int task = tasks[i];

            //try to find a worker without pill
            Integer noPillWorker = workerMap.ceilingKey(task);
            if (noPillWorker != null) {
                removeWorker(workerMap, noPillWorker);
            } else {
                // try to find a worker who can do it with a pill
                Integer pillWorker = workerMap.ceilingKey(task - strength);
                if (pillWorker != null && pills > 0) {
                    removeWorker(workerMap, pillWorker);
                    pills--;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    private void removeWorker(TreeMap<Integer, Integer> map, int key) {
        int count = map.get(key);
        if (count == 1) {
            map.remove(key);
        } else {
            map.put(key, count - 1);
        }
    }

}

//Explanation
//We want to assign up to k tasks to k workers.
//To check if it’s possible to do so:
//-Sort the hardest k tasks and strongest k workers.
//-Use a TreeMap to efficiently track available workers.
//-For each task (from hardest to easiest), try to:
//  -Assign a worker directly (without pill), or
//  -Use a pill to boost a weaker worker (if pills remain).
//We binary search on k to find the maximum number of tasks we can assign.
//Complexity:
// time - O(n log n)
// space - O(n)


//You have n tasks and m workers. Each task has a strength requirement stored in a 0-indexed integer array tasks,
// with the ith task requiring tasks[i] strength to complete. The strength of each worker is stored in a 0-indexed
// integer array workers, with the jth worker having workers[j] strength. Each worker can only be assigned to
// a single task and must have a strength greater than or equal to the task's strength
// requirement (i.e., workers[j] >= tasks[i]).
//Additionally, you have pills magical pills that will increase a worker's strength by strength. You can decide
// which workers receive the magical pills, however, you may only give each worker at most one magical pill.
//Given the 0-indexed integer arrays tasks and workers and the integers pills and strength, return the maximum
// number of tasks that can be completed.

//Example 1:
//Input: tasks = [3,2,1], workers = [0,3,3], pills = 1, strength = 1
//Output: 3
//Explanation:
//We can assign the magical pill and tasks as follows:
//- Give the magical pill to worker 0.
//- Assign worker 0 to task 2 (0 + 1 >= 1)
//- Assign worker 1 to task 1 (3 >= 2)
//- Assign worker 2 to task 0 (3 >= 3)

//Example 2:
//Input: tasks = [5,4], workers = [0,0,0], pills = 1, strength = 5
//Output: 1
//Explanation:
//We can assign the magical pill and tasks as follows:
//- Give the magical pill to worker 0.
//- Assign worker 0 to task 0 (0 + 5 >= 5)

//Example 3:
//Input: tasks = [10,15,30], workers = [0,10,10,10,10], pills = 3, strength = 10
//Output: 2
//Explanation:
//We can assign the magical pills and tasks as follows:
//- Give the magical pill to worker 0 and worker 1.
//- Assign worker 0 to task 0 (0 + 10 >= 10)
//- Assign worker 1 to task 1 (10 + 10 >= 15)
//The last pill is not given because it will not make any worker strong enough for the last task.

//Constraints:
//n == tasks.length
//m == workers.length
//1 <= n, m <= 5 * 104
//0 <= pills <= m
//0 <= tasks[i], workers[j], strength <= 109
