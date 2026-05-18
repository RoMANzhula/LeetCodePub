package P1301_1400.P1345_Jump_Game_IV;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] arr1 = {100,-23,-23,404,100,23,23,23,3,404};
        int[] arr2 = {7};
        int[] arr3 = {7,6,9,6,9,6,9,7};

        System.out.println(solution.minJumps(arr1)); // 3
        System.out.println(solution.minJumps(arr2)); // 0
        System.out.println(solution.minJumps(arr3)); // 1
    }

    public int minJumps(int[] arr) {
        int n = arr.length;
        if (n == 1) return 0;

        // map value -> list of indices
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];

        queue.offer(0);
        visited[0] = true;

        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int s = 0; s < size; s++) {
                int i = queue.poll();

                if (i == n - 1) return steps;

                // jump to i - 1
                if (i - 1 >= 0 && !visited[i - 1]) {
                    visited[i - 1] = true;
                    queue.offer(i - 1);
                }

                // jump to i + 1
                if (i + 1 < n && !visited[i + 1]) {
                    visited[i + 1] = true;
                    queue.offer(i + 1);
                }

                // jump to same-value indices
                List<Integer> same = map.get(arr[i]);
                if (same != null) {
                    for (int next : same) {
                        if (!visited[next]) {
                            visited[next] = true;
                            queue.offer(next);
                        }
                    }
                    // clear to avoid reprocessing
                    same.clear();
                }
            }

            steps++;
        }

        return -1;
    }

}

//Complexity:
// time and space - O(n)


//Given an array of integers arr, you are initially positioned at the first index of the array.
//In one step you can jump from index i to index:
//i + 1 where: i + 1 < arr.length.
//i - 1 where: i - 1 >= 0.
//j where: arr[i] == arr[j] and i != j.
//Return the minimum number of steps to reach the last index of the array.
//Notice that you can not jump outside of the array at any time.

//Example 1:
//Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
//Output: 3
//Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9. Note that index 9 is the last index of the array.

//Example 2:
//Input: arr = [7]
//Output: 0
//Explanation: Start index is the last index. You do not need to jump.

//Example 3:
//Input: arr = [7,6,9,6,9,6,9,7]
//Output: 1
//Explanation: You can jump directly from index 0 to index 7 which is last index of the array.

//Constraints:
//1 <= arr.length <= 5 * 104
//-108 <= arr[i] <= 108
