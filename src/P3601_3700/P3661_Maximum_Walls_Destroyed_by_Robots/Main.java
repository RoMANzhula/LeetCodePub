package P3601_3700.P3661_Maximum_Walls_Destroyed_by_Robots;

import java.util.Arrays;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] robots1 = {4};
        int[] distance1 = {3};
        int[] walls1 = {1, 10};
        System.out.println(solution.maxWalls(robots1, distance1, walls1)); // 1

        int[] robots2 = {10, 2};
        int[] distance2 = {5, 1};
        int[] walls2 = {5, 2, 7};
        System.out.println(solution.maxWalls(robots2, distance2, walls2)); // 3

        int[] robots3 = {1, 2};
        int[] distance3 = {100, 1};
        int[] walls3 = {10};
        System.out.println(solution.maxWalls(robots3, distance3, walls3)); // 0
    }

    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;

        // pair robots with distances
        int[][] robotInfo = new int[n][2];
        for (int i = 0; i < n; i++) {
            robotInfo[i][0] = robots[i];
            robotInfo[i][1] = distance[i];
        }

        // sort robots by position
        Arrays.sort(robotInfo, Comparator.comparingInt(a -> a[0]));

        Arrays.sort(walls);

        // DP memo
        int[][] dp = new int[n][2];
        for (int[] row : dp) Arrays.fill(row, -1);

        return solve(n - 1, 1, robotInfo, walls, dp);
    }

    private int solve(int i, int prevDir, int[][] robots, int[] walls, int[][] dp) {
        if (i < 0) return 0;

        if (dp[i][prevDir] != -1) return dp[i][prevDir];

        int pos = robots[i][0];
        int dist = robots[i][1];

        int res = 0;

        // LEFT
        int leftBound = pos - dist;
        if (i > 0) {
            leftBound = Math.max(leftBound, robots[i - 1][0] + 1);
        }

        int leftCount = count(walls, leftBound, pos);

        res = solve(i - 1, 0, robots, walls, dp) + leftCount;

        // RIGHT
        int rightBound = pos + dist;

        if (i + 1 < robots.length) {
            if (prevDir == 0) {
                // next robot goes LEFT → blocks more
                rightBound = Math.min(rightBound,
                        robots[i + 1][0] - robots[i + 1][1] - 1);
            } else {
                // next robot goes RIGHT
                rightBound = Math.min(rightBound,
                        robots[i + 1][0] - 1);
            }
        }

        int rightCount = count(walls, pos, rightBound);

        res = Math.max(res,
                solve(i - 1, 1, robots, walls, dp) + rightCount);

        return dp[i][prevDir] = res;
    }

    // count walls in [L, R]
    private int count(int[] walls, int L, int R) {
        int left = lowerBound(walls, L);
        int right = upperBound(walls, R);

        return right - left;
    }

    private int lowerBound(int[] arr, int target) {
        int l = 0, r = arr.length;

        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] < target) l = mid + 1;
            else r = mid;
        }

        return l;
    }

    private int upperBound(int[] arr, int target) {
        int l = 0, r = arr.length;

        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] <= target) l = mid + 1;
            else r = mid;
        }

        return l;
    }

}

//Complexity:
// time - O(n log n + m log m + n log m)
// space - O(n)


//There is an endless straight line populated with some robots and walls. You are given integer arrays robots,
// distance, and walls:
//robots[i] is the position of the ith robot.
//distance[i] is the maximum distance the ith robot's bullet can travel.
//walls[j] is the position of the jth wall.
//Every robot has one bullet that can either fire to the left or the right at most distance[i] meters.
//A bullet destroys every wall in its path that lies within its range. Robots are fixed obstacles: if a bullet hits
// another robot before reaching a wall, it immediately stops at that robot and cannot continue.
//Return the maximum number of unique walls that can be destroyed by the robots.
//Notes:
//A wall and a robot may share the same position; the wall can be destroyed by the robot at that position.
//Robots are not destroyed by bullets.

//Example 1:
//Input: robots = [4], distance = [3], walls = [1,10]
//Output: 1
//Explanation:
//robots[0] = 4 fires left with distance[0] = 3, covering [1, 4] and destroys walls[0] = 1.
//Thus, the answer is 1.

//Example 2:
//Input: robots = [10,2], distance = [5,1], walls = [5,2,7]
//Output: 3
//Explanation:
//robots[0] = 10 fires left with distance[0] = 5, covering [5, 10] and destroys walls[0] = 5 and walls[2] = 7.
//robots[1] = 2 fires left with distance[1] = 1, covering [1, 2] and destroys walls[1] = 2.
//Thus, the answer is 3.

//Example 3:
//Input: robots = [1,2], distance = [100,1], walls = [10]
//Output: 0
//Explanation:
//In this example, only robots[0] can reach the wall, but its shot to the right is blocked by robots[1]; thus the
// answer is 0.

//Constraints:
//1 <= robots.length == distance.length <= 105
//1 <= walls.length <= 105
//1 <= robots[i], walls[j] <= 109
//1 <= distance[i] <= 105
//All values in robots are unique
//All values in walls are unique
