package P1501_1600.P1552_Magnetic_Force_Between_Two_Balls;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] position1 = {1, 2, 3, 4, 7};
        int m1 = 3;
        System.out.println(solution.maxDistance(position1, m1)); // Output: 3

        int[] position2 = {5, 4, 3, 2, 1, 1000000000};
        int m2 = 2;
        System.out.println(solution.maxDistance(position2, m2)); // Output: 999999999
    }

    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int left = 1, right = position[position.length - 1] - position[0];
        int result = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (canPlaceBalls(position, m, mid)) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    private boolean canPlaceBalls(int[] position, int m, int minDist) {
        int count = 1;
        int lastPos = position[0];

        for (int i = 1; i < position.length; i++) {
            if (position[i] - lastPos >= minDist) {
                count++;
                lastPos = position[i];
                if (count == m) {
                    return true;
                }
            }
        }

        return false;
    }
}

//Explanation:
//Sorting: Arrays.sort(position); sorts the basket positions.
//Binary Search: The while loop performs binary search over the possible distances.
//Greedy Placement Check: The canPlaceBalls function checks if it is possible to place m balls with at least
// minDist distance between any two balls.
//The result is the largest minimum distance that allows placing all m balls.
//This solution ensures that the minimum magnetic force between any two balls is maximized by leveraging binary
// search combined with a greedy algorithm.


//In the universe Earth C-137, Rick discovered a special form of magnetic force between two balls if they are put
// in his new invented basket. Rick has n empty baskets, the ith basket is at position[i], Morty has m balls and
// needs to distribute the balls into the baskets such that the minimum magnetic force between any two balls is maximum.
//Rick stated that magnetic force between two different balls at positions x and y is |x - y|.
//Given the integer array position and the integer m. Return the required force.
//
//Example 1:
//Input: position = [1,2,3,4,7], m = 3
//Output: 3
//Explanation: Distributing the 3 balls into baskets 1, 4 and 7 will make the magnetic force between ball
// pairs [3, 3, 6]. The minimum magnetic force is 3. We cannot achieve a larger minimum magnetic force than 3.

//Example 2:
//Input: position = [5,4,3,2,1,1000000000], m = 2
//Output: 999999999
//Explanation: We can use baskets 1 and 1000000000.
//
//Constraints:
//n == position.length
//2 <= n <= 105
//1 <= position[i] <= 109
//All integers in position are distinct.
//2 <= m <= position.length
