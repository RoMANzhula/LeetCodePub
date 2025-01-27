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
