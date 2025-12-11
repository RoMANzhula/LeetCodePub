package P3501_3600.P3531_Count_Covered_Buildings;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int n1 = 3;
        int[][] b1 = {{1,2},{2,2},{3,2},{2,1},{2,3}};
        System.out.println(solution.countCoveredBuildings(n1, b1));  // Output: 1

        int n2 = 3;
        int[][] b2 = {{1,1},{1,2},{2,1},{2,2}};
        System.out.println(solution.countCoveredBuildings(n2, b2));  // Output: 0

        int n3 = 5;
        int[][] b3 = {{1,3},{3,2},{3,3},{3,5},{5,3}};
        System.out.println(solution.countCoveredBuildings(n3, b3));  // Output: 1
    }

    public int countCoveredBuildings(int n, int[][] buildings) {
        // to store all y-coordinates for each x-coordinate
        Map<Integer, List<Integer>> buildingsAtX = new HashMap<>();
        // to store all x-coordinates for each y-coordinate
        Map<Integer, List<Integer>> buildingsAtY = new HashMap<>();

        // group buildings by their x and y coordinates
        for (int[] b : buildings) {
            int x = b[0];
            int y = b[1];

            buildingsAtX.computeIfAbsent(x, k -> new ArrayList<>()).add(y);
            buildingsAtY.computeIfAbsent(y, k -> new ArrayList<>()).add(x);
        }

        // sort all y-coordinates for each x (vertical)
        for (List<Integer> yList : buildingsAtX.values()) {
            Collections.sort(yList);
        }

        // sort all x-coordinates for each y (horizontal)
        for (List<Integer> xList : buildingsAtY.values()) {
            Collections.sort(xList);
        }

        int coveredCount = 0;

        // check each building
        for (int[] b : buildings) {
            int x = b[0];
            int y = b[1];

            List<Integer> yList = buildingsAtX.get(x); // all y on same x
            List<Integer> xList = buildingsAtY.get(y); // all x on same y

            boolean hasLeft  = xList.get(0) < x;
            boolean hasRight = x < xList.get(xList.size() - 1);

            boolean hasBottom = yList.get(0) < y;
            boolean hasTop    = y < yList.get(yList.size() - 1);

            if (hasLeft && hasRight && hasBottom && hasTop) {
                coveredCount++;
            }
        }

        return coveredCount;
    }

}

//Complexity:
// time - O(m log m)
// space - O(m)


//You are given a positive integer n, representing an n x n city. You are also given a 2D grid buildings, where
// buildings[i] = [x, y] denotes a unique building located at coordinates [x, y].
//A building is covered if there is at least one building in all four directions: left, right, above, and below.
//Return the number of covered buildings.

//Example 1:
//Input: n = 3, buildings = [[1,2],[2,2],[3,2],[2,1],[2,3]]
//Output: 1
//Explanation:
//Only building [2,2] is covered as it has at least one building:
//above ([1,2])
//below ([3,2])
//left ([2,1])
//right ([2,3])
//Thus, the count of covered buildings is 1.

//Example 2:
//Input: n = 3, buildings = [[1,1],[1,2],[2,1],[2,2]]
//Output: 0
//Explanation:
//No building has at least one building in all four directions.

//Example 3:
//Input: n = 5, buildings = [[1,3],[3,2],[3,3],[3,5],[5,3]]
//Output: 1
//Explanation:
//Only building [3,3] is covered as it has at least one building:
//above ([1,3])
//below ([5,3])
//left ([3,2])
//right ([3,5])
//Thus, the count of covered buildings is 1.

//Constraints:
//2 <= n <= 105
//1 <= buildings.length <= 105
//buildings[i] = [x, y]
//1 <= x, y <= n
//All coordinates of buildings are unique.
