package P2901_3000.P2975_Maximum_Square_Area_by_Removing_Fences_From_a_Field;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int m1 = 4, n1 = 3;
        int[] h1 = {2, 3};
        int[] v1 = {2};
        System.out.println(solution.maximizeSquareArea(m1, n1, h1, v1)); // 4

        int m2 = 6, n2 = 7;
        int[] h2 = {2};
        int[] v2 = {4};
        System.out.println(solution.maximizeSquareArea(m2, n2, h2, v2)); // -1
    }

    private final long MOD = 1_000_000_007;

    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        // aadd boundary fences
        int[] h = addBoundaries(hFences, m);
        int[] v = addBoundaries(vFences, n);

        // all possible vertical distances (heights)
        Set<Long> heights = new HashSet<>();
        for (int i = 0; i < h.length; i++) {
            for (int j = i + 1; j < h.length; j++) {
                heights.add((long) h[j] - h[i]);
            }
        }

        long maxSide = -1;

        // check horizontal distances (widths)
        for (int i = 0; i < v.length; i++) {
            for (int j = i + 1; j < v.length; j++) {
                long width = (long) v[j] - v[i];

                if (heights.contains(width)) {
                    maxSide = Math.max(maxSide, width);
                }
            }
        }

        if (maxSide == -1) return -1;

        return (int) ((maxSide * maxSide) % MOD);
    }

    private int[] addBoundaries(int[] fences, int limit) {
        int[] res = new int[fences.length + 2];
        res[0] = 1;
        res[res.length - 1] = limit;
        System.arraycopy(fences, 0, res, 1, fences.length);
        Arrays.sort(res);

        return res;
    }

}

//Complexity:
// time - O(H^2 + V^2)
// space - O(H^2)


//There is a large (m - 1) x (n - 1) rectangular field with corners at (1, 1) and (m, n) containing some horizontal
// and vertical fences given in arrays hFences and vFences respectively.
//Horizontal fences are from the coordinates (hFences[i], 1) to (hFences[i], n) and vertical fences are from the
// coordinates (1, vFences[i]) to (m, vFences[i]).
//Return the maximum area of a square field that can be formed by removing some fences (possibly none) or -1 if it is
// impossible to make a square field.
//Since the answer may be large, return it modulo 109 + 7.
//Note: The field is surrounded by two horizontal fences from the coordinates (1, 1) to (1, n) and (m, 1) to
// (m, n) and two vertical fences from the coordinates (1, 1) to (m, 1) and (1, n) to (m, n). These fences
// cannot be removed.

//Example 1:
//Input: m = 4, n = 3, hFences = [2,3], vFences = [2]
//Output: 4
//Explanation: Removing the horizontal fence at 2 and the vertical fence at 2 will give a square field of area 4.

//Example 2:
//Input: m = 6, n = 7, hFences = [2], vFences = [4]
//Output: -1
//Explanation: It can be proved that there is no way to create a square field by removing fences.

//Constraints:
//3 <= m, n <= 109
//1 <= hFences.length, vFences.length <= 600
//1 < hFences[i] < m
//1 < vFences[i] < n
//hFences and vFences are unique.
