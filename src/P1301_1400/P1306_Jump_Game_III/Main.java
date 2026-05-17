package P1301_1400.P1306_Jump_Game_III;

import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] arr1 = {4, 2, 3, 0, 3, 1, 2};
        int start1 = 5;
        System.out.println(solution.canReach(arr1, start1)); // true

        int[] arr2 = {4, 2, 3, 0, 3, 1, 2};
        int start2 = 0;
        System.out.println(solution.canReach(arr2, start2)); // true

        int[] arr3 = {3, 0, 2, 1, 2};
        int start3 = 2;
        System.out.println(solution.canReach(arr3, start3)); // false
    }

    public boolean canReach(int[] arr, int start) {
        int len = arr.length;
        boolean[] visited = new boolean[len];
        Queue<Integer> queueReach = new LinkedList<>();

        queueReach.offer(start);
        visited[start] = true;

        while (!queueReach.isEmpty()) {
            int current = queueReach.poll();

            //found zero
            if (arr[current] == 0) return true;

            int forward = current + arr[current];
            int backward = current - arr[current];

            // check forward jump
            if (forward < len && !visited[forward]) {
                visited[forward] = true;
                queueReach.offer(forward);
            }

            //check backward jump
            if (backward >= 0 && !visited[backward]) {
                visited[backward] = true;
                queueReach.offer(backward);
            }
        }

        return false;
    }

}

//Complexity:
// time and space - O(n)


//Given an array of non-negative integers arr, you are initially positioned at start index of the array. When you
// are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach any index with value 0.
//Notice that you can not jump outside of the array at any time.

//Example 1:
//Input: arr = [4,2,3,0,3,1,2], start = 5
//Output: true
//Explanation:
//All possible ways to reach at index 3 with value 0 are:
//index 5 -> index 4 -> index 1 -> index 3
//index 5 -> index 6 -> index 4 -> index 1 -> index 3

//Example 2:
//Input: arr = [4,2,3,0,3,1,2], start = 0
//Output: true
//Explanation:
//One possible way to reach at index 3 with value 0 is:
//index 0 -> index 4 -> index 1 -> index 3

//Example 3:
//Input: arr = [3,0,2,1,2], start = 2
//Output: false
//Explanation: There is no way to reach at index 1 with value 0.

//Constraints:
//1 <= arr.length <= 5 * 104
//0 <= arr[i] < arr.length
//0 <= start < arr.length
