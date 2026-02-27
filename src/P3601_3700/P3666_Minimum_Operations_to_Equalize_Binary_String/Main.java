package P3601_3700.P3666_Minimum_Operations_to_Equalize_Binary_String;

import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.minOperations("110", 1));     // 1
        System.out.println(solution.minOperations("0101", 3));    // 2
        System.out.println(solution.minOperations("101", 2));     // -1
        System.out.println(solution.minOperations("0000", 3));    // 4
        System.out.println(solution.minOperations("001", 2));     // 1
        System.out.println(solution.minOperations("001", 3));     // -1
    }

    public int minOperations(String s, int k) {
        int n = s.length();

        TreeSet<Integer>[] ts = new TreeSet[2];
        ts[0] = new TreeSet<>();
        ts[1] = new TreeSet<>();

        for (int i = 0; i <= n; i++) {
            ts[i % 2].add(i);
        }

        int cnt0 = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') cnt0++;
        }

        ts[cnt0 % 2].remove(cnt0);

        Queue<Integer> q = new LinkedList<>();
        q.offer(cnt0);

        int ans = 0;

        while (!q.isEmpty()) {

            int size = q.size();

            for (int i = 0; i < size; i++) {
                int cur = q.poll();

                if (cur == 0) {
                    return ans;
                }

                int l = cur + k - 2 * Math.min(cur, k);
                int r = cur + k - 2 * Math.max(k - n + cur, 0);

                TreeSet<Integer> set = ts[l % 2];

                Integer next = set.ceiling(l);

                while (next != null && next <= r) {
                    q.offer(next);
                    set.remove(next);
                    next = set.ceiling(l);
                }
            }

            ans++;
        }

        return -1;
    }

}

//Complexity:
// time - O(n log n)
// space - O(n)


//You are given a binary string s, and an integer k.
//In one operation, you must choose exactly k different indices and flip each '0' to '1' and each '1' to '0'.
//Return the minimum number of operations required to make all characters in the string equal to '1'. If it is not
// possible, return -1.

//Example 1:
//Input: s = "110", k = 1
//Output: 1
//Explanation:
//There is one '0' in s.
//Since k = 1, we can flip it directly in one operation.

//Example 2:
//Input: s = "0101", k = 3
//Output: 2
//Explanation:
//One optimal set of operations choosing k = 3 indices in each operation is:
//Operation 1: Flip indices [0, 1, 3]. s changes from "0101" to "1000".
//Operation 2: Flip indices [1, 2, 3]. s changes from "1000" to "1111".
//Thus, the minimum number of operations is 2.

//Example 3:
//Input: s = "101", k = 2
//Output: -1
//Explanation:
//Since k = 2 and s has only one '0', it is impossible to flip exactly k indices to make all '1'. Hence, the answer is -1.

//Constraints:
//1 <= s.length <= 10​​​​​​​5
//s[i] is either '0' or '1'.
//1 <= k <= s.length
