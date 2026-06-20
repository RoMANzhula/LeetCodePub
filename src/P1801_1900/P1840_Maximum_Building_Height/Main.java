package P1801_1900.P1840_Maximum_Building_Height;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int n1 = 5;
        int[][] restrictions1 = {
                {2, 1},
                {4, 1}
        };
        System.out.println(solution.maxBuilding(n1, restrictions1)); // 2

        int n2 = 6;
        int[][] restrictions2 = {};
        System.out.println(solution.maxBuilding(n2, restrictions2)); // 5

        int n3 = 10;
        int[][] restrictions3 = {
                {5, 3},
                {2, 5},
                {7, 4},
                {10, 3}
        };
        System.out.println(solution.maxBuilding(n3, restrictions3)); // 5
    }

    public int maxBuilding(int n, int[][] restrictions) {
        List<int[]> list = new ArrayList<>();

        // building 1 always has height 0
        list.add(new int[]{1, 0});

        for (int[] r : restrictions) {
            list.add(new int[]{r[0], r[1]});
        }

        // if building n has no restriction, add the maximum possible one
        boolean hasN = false;
        for (int[] r : restrictions) {
            if (r[0] == n) {
                hasN = true;
                break;
            }
        }

        if (!hasN) {
            list.add(new int[]{n, n - 1});
        }

        Collections.sort(list, (a, b) -> a[0] - b[0]);

        int m = list.size();

        // left -> right
        for (int i = 1; i < m; i++) {
            int dist = list.get(i)[0] - list.get(i - 1)[0];
            list.get(i)[1] = Math.min(
                    list.get(i)[1],
                    list.get(i - 1)[1] + dist
            );
        }

        // right -> left
        for (int i = m - 2; i >= 0; i--) {
            int dist = list.get(i + 1)[0] - list.get(i)[0];
            list.get(i)[1] = Math.min(
                    list.get(i)[1],
                    list.get(i + 1)[1] + dist
            );
        }

        int answer = 0;

        for (int i = 1; i < m; i++) {
            int x1 = list.get(i - 1)[0];
            int h1 = list.get(i - 1)[1];

            int x2 = list.get(i)[0];
            int h2 = list.get(i)[1];

            int dist = x2 - x1;

            int peak = (h1 + h2 + dist) / 2;

            answer = Math.max(answer, peak);
        }

        return answer;
    }

}

//Complexity:
// time - O(m log m)
// space - O(m)
// m - restrictions.length


//You want to build n new buildings in a city. The new buildings will be built in a line and are labeled from 1 to n.
//However, there are city restrictions on the heights of the new buildings:
//The height of each building must be a non-negative integer.
//The height of the first building must be 0.
//The height difference between any two adjacent buildings cannot exceed 1.
//Additionally, there are city restrictions on the maximum height of specific buildings. These restrictions are
// given as a 2D integer array restrictions where restrictions[i] = [idi, maxHeighti] indicates that building idi
// must have a height less than or equal to maxHeighti.
//It is guaranteed that each building will appear at most once in restrictions, and building 1 will not be in
// restrictions.
//Return the maximum possible height of the tallest building.

//Example 1:
//Input: n = 5, restrictions = [[2,1],[4,1]]
//Output: 2
//Explanation: The green area in the image indicates the maximum allowed height for each building.
//We can build the buildings with heights [0,1,2,1,2], and the tallest building has a height of 2.

//Example 2:
//Input: n = 6, restrictions = []
//Output: 5
//Explanation: The green area in the image indicates the maximum allowed height for each building.
//We can build the buildings with heights [0,1,2,3,4,5], and the tallest building has a height of 5.

//Example 3:
//Input: n = 10, restrictions = [[5,3],[2,5],[7,4],[10,3]]
//Output: 5
//Explanation: The green area in the image indicates the maximum allowed height for each building.
//We can build the buildings with heights [0,1,2,3,3,4,4,5,4,3], and the tallest building has a height of 5.

//Constraints:
//2 <= n <= 109
//0 <= restrictions.length <= min(n - 1, 105)
//2 <= idi <= n
//idi is unique.
//0 <= maxHeighti <= 109
