package P3401_3500.P3445_Maximum_Difference_Between_Even_and_Odd_Frequency_II;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String s1 = "12233";
        int k1 = 4;
        System.out.println(solution.maxDifference(s1, k1)); // Output: -1

        String s2 = "1122211";
        int k2 = 3;
        System.out.println(solution.maxDifference(s2, k2)); // Output: 1

        String s3 = "110";
        int k3 = 3;
        System.out.println(solution.maxDifference(s3, k3)); // Output: -1
    }

    public int maxDifference(String s, int k) {
        int ans = Integer.MIN_VALUE;

        for (char a = '0'; a <= '4'; a++) {
            for (char b = '0'; b <= '4'; b++) {
                if (a == b) continue;

                // minDiff[parityA][parityB] represents min(a-b) for prefix sums
                int[][] minDiff = new int[2][2];
                for (int[] row : minDiff) Arrays.fill(row, Integer.MAX_VALUE / 2);

                List<Integer> prefixA = new ArrayList<>();
                List<Integer> prefixB = new ArrayList<>();
                prefixA.add(0);
                prefixB.add(0);

                int l = 0;

                for (int r = 0; r < s.length(); r++) {
                    prefixA.add(prefixA.get(r) + (s.charAt(r) == a ? 1 : 0));
                    prefixB.add(prefixB.get(r) + (s.charAt(r) == b ? 1 : 0));

                    while (r - l + 1 >= k &&
                            prefixA.get(l) < prefixA.get(r + 1) && // 'a' appears in window
                            prefixB.get(l) < prefixB.get(r + 1)) { // 'b' appears in window

                        int parityA = prefixA.get(l) % 2;
                        int parityB = prefixB.get(l) % 2;
                        int diff = prefixA.get(l) - prefixB.get(l);
                        minDiff[parityA][parityB] = Math.min(minDiff[parityA][parityB], diff);
                        l++;
                    }

                    int currA = prefixA.get(r + 1);
                    int currB = prefixB.get(r + 1);
                    int parityA = currA % 2;
                    int parityB = currB % 2;

                    // try to get the best answer using opposite parity for 'a'
                    int candidate = (currA - currB) - minDiff[1 - parityA][parityB];
                    ans = Math.max(ans, candidate);
                }
            }
        }

        return ans == Integer.MIN_VALUE ? -1 : ans;
    }

}

//Complexity:
// time and space - O(n)


//You are given a string s and an integer k. Your task is to find the maximum difference between the frequency of
// two characters, freq[a] - freq[b], in a substring subs of s, such that:
//subs has a size of at least k.
//Character a has an odd frequency in subs.
//Character b has an even frequency in subs.
//Return the maximum difference.
//Note that subs can contain more than 2 distinct characters.

//Example 1:
//Input: s = "12233", k = 4
//Output: -1
//Explanation:
//For the substring "12233", the frequency of '1' is 1 and the frequency of '3' is 2. The difference is 1 - 2 = -1.

//Example 2:
//Input: s = "1122211", k = 3
//Output: 1
//Explanation:
//For the substring "11222", the frequency of '2' is 3 and the frequency of '1' is 2. The difference is 3 - 2 = 1.

//Example 3:
//Input: s = "110", k = 3
//Output: -1

//Constraints:
//3 <= s.length <= 3 * 104
//s consists only of digits '0' to '4'.
//The input is generated that at least one substring has a character with an even frequency and a character with an odd frequency.
//1 <= k <= s.length