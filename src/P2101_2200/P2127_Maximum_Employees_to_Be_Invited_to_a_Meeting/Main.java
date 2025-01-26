package P2101_2200.P2127_Maximum_Employees_to_Be_Invited_to_a_Meeting;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.maximumInvitations(new int[]{2, 2, 1, 2})); // Output: 3
        System.out.println(solution.maximumInvitations(new int[]{1, 2, 0}));    // Output: 3
        System.out.println(solution.maximumInvitations(new int[]{3, 0, 1, 4, 1})); // Output: 4
    }

    public int maximumInvitations(int[] favorite) {
        int n = favorite.length;
        int[] inDegree = new int[n];

        for (int f : favorite) {
            inDegree[f]++;
        }

        // find all chains leading to mutual favorite pairs
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        int[] chainLength = new int[n];

        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) queue.offer(i);
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();
            visited[current] = true;
            int favElement = favorite[current];
            chainLength[favElement] = Math.max(chainLength[favElement], chainLength[current] + 1);

            if (--inDegree[favElement] == 0) queue.offer(favElement);
        }

        // find cycles and calculate the max result
        int maxCycleSize = 0, chainSum = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int cycleSize = 0;
                int current = i;

                while (!visited[current]) {
                    visited[current] = true;
                    current = favorite[current];
                    cycleSize++;
                }

                if (cycleSize == 2) {
                    // mutual favorite pair
                    chainSum += 2 + chainLength[i] + chainLength[favorite[i]];
                } else {
                    maxCycleSize = Math.max(maxCycleSize, cycleSize);
                }
            }
        }

        return Math.max(maxCycleSize, chainSum);
    }
}

//Explanation:
//Initialization:
//compute the indegree of each node to detect employees who are not part of any cycle.
//Handle Chains:
//process nodes with indegree == 0 to calculate chain lengths leading to mutual favorite pairs.
//Find Cycles:
//-Identify cycles among unvisited nodes.
//-Handle special cases for mutual favorite pairs (size 2 cycles).
//Result:
//return the maximum of maxCycleSize or chainSum.
//Complexity:
//Time Complexity:
//O(n), as each node and edge is processed once.
//Space Complexity:
//O(n), for visited, indegree, and chain length arrays.


//A company is organizing a meeting and has a list of n employees, waiting to be invited. They have arranged for a
// large circular table, capable of seating any number of employees.
//The employees are numbered from 0 to n - 1. Each employee has a favorite person and they will attend the
// meeting only if they can sit next to their favorite person at the table. The favorite person of an
// employee is not themself.
//Given a 0-indexed integer array favorite, where favorite[i] denotes the favorite person of the ith employee, return
// the maximum number of employees that can be invited to the meeting.
//
//Example 1:
//Input: favorite = [2,2,1,2]
//Output: 3
//Explanation:
//The above figure shows how the company can invite employees 0, 1, and 2, and seat them at the round table.
//All employees cannot be invited because employee 2 cannot sit beside employees 0, 1, and 3, simultaneously.
//Note that the company can also invite employees 1, 2, and 3, and give them their desired seats.
//The maximum number of employees that can be invited to the meeting is 3.

//Example 2:
//Input: favorite = [1,2,0]
//Output: 3
//Explanation:
//Each employee is the favorite person of at least one other employee, and the only way the company can invite
// them is if they invite every employee.
//The seating arrangement will be the same as that in the figure given in example 1:
//- Employee 0 will sit between employees 2 and 1.
//- Employee 1 will sit between employees 0 and 2.
//- Employee 2 will sit between employees 1 and 0.
//The maximum number of employees that can be invited to the meeting is 3.

//Example 3:
//Input: favorite = [3,0,1,4,1]
//Output: 4
//Explanation:
//The above figure shows how the company will invite employees 0, 1, 3, and 4, and seat them at the round table.
//Employee 2 cannot be invited because the two spots next to their favorite employee 1 are taken.
//So the company leaves them out of the meeting.
//The maximum number of employees that can be invited to the meeting is 4.
//
//Constraints:
//n == favorite.length
//2 <= n <= 105
//0 <= favorite[i] <= n - 1
//favorite[i] != i