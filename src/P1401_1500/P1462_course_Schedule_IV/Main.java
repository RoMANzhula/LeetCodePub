package P1401_1500.P1462_course_Schedule_IV;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int numCourses1 = 2;
        int[][] prerequisites1 = {{1, 0}};
        int[][] queries1 = {{0, 1}, {1, 0}};
        System.out.println(solution.checkIfPrerequisite(numCourses1, prerequisites1, queries1)); // Output: [false, true]

        int numCourses2 = 2;
        int[][] prerequisites2 = {};
        int[][] queries2 = {{1, 0}, {0, 1}};
        System.out.println(solution.checkIfPrerequisite(numCourses2, prerequisites2, queries2)); // Output: [false, false]

        int numCourses3 = 3;
        int[][] prerequisites3 = {{1, 2}, {1, 0}, {2, 0}};
        int[][] queries3 = {{1, 0}, {1, 2}};
        System.out.println(solution.checkIfPrerequisite(numCourses3, prerequisites3, queries3)); // Output: [true, true]
    }

    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        // initialize a matrix to represent the transitive closure
        boolean[][] isPrerequisite = new boolean[numCourses][numCourses];

        // populate thw matrix based on the direct prerequisites
        for (int[] prerequisite : prerequisites) {
            isPrerequisite[prerequisite[0]][prerequisite[1]] = true;
        }

        // use the Floyd-Warshall algorithm to compute the transitive closure
        for (int k = 0; k < numCourses; k++) {
            for (int i = 0; i < numCourses; i++) {
                for (int j = 0; j < numCourses; j++) {
                    isPrerequisite[i][j] |= isPrerequisite[i][k] && isPrerequisite[k][j];
                }
            }
        }

        // answer the queries based on the transitive closure matrix
        List<Boolean> result = new ArrayList<>();
        for (int[] query : queries) {
            result.add(isPrerequisite[query[0]][query[1]]);
        }

        return result;
    }
}

//Explanation of the Solution
//Matrix Initialization:
//Create a boolean[][] isPrerequisite to represent the prerequisites between courses.
//If isPrerequisite[a][b] is true, it means course a is a prerequisite for course b.
//Direct Prerequisites:
//Populate the matrix based on the direct prerequisites given in the input.
//Floyd-Warshall Algorithm:
//Iterate through all course pairs (i, j) and check if there's an intermediate course k such that i -> k -> j. If so,
// mark isPrerequisite[i][j] as true.
//Answer Queries:
//For each query (u, v), simply return the value of isPrerequisite[u][v] from the matrix.
//Complexity Analysis
//Time Complexity:
//Floyd-Warshall algorithm runs in O(n^3), where n is the number of courses.
//Answering each query takes O(1), so for q queries, the total query time is O(q).
//Overall: O(n^3+q).
//Space Complexity:
//The isPrerequisite matrix requires O(n^2) space.
//This solution is efficient for n≤100, as stated in the constraints.


//There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an
// array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course ai first if
// you want to take course bi.
//For example, the pair [0, 1] indicates that you have to take course 0 before you can take course 1.
//Prerequisites can also be indirect. If course a is a prerequisite of course b, and course b is a prerequisite of
// course c, then course a is a prerequisite of course c.
//You are also given an array queries where queries[j] = [uj, vj]. For the jth query, you should answer whether
// course uj is a prerequisite of course vj or not.
//Return a boolean array answer, where answer[j] is the answer to the jth query.
//
//Example 1:
//Input: numCourses = 2, prerequisites = [[1,0]], queries = [[0,1],[1,0]]
//Output: [false,true]
//Explanation: The pair [1, 0] indicates that you have to take course 1 before you can take course 0.
//Course 0 is not a prerequisite of course 1, but the opposite is true.

//Example 2:
//Input: numCourses = 2, prerequisites = [], queries = [[1,0],[0,1]]
//Output: [false,false]
//Explanation: There are no prerequisites, and each course is independent.

//Example 3:
//Input: numCourses = 3, prerequisites = [[1,2],[1,0],[2,0]], queries = [[1,0],[1,2]]
//Output: [true,true]
//
//Constraints:
//2 <= numCourses <= 100
//0 <= prerequisites.length <= (numCourses * (numCourses - 1) / 2)
//prerequisites[i].length == 2
//0 <= ai, bi <= numCourses - 1
//ai != bi
//All the pairs [ai, bi] are unique.
//The prerequisites graph has no cycles.
//1 <= queries.length <= 104
//0 <= ui, vi <= numCourses - 1
//ui != vi