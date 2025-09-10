package P1701_1800.P1733_Minimum_Number_of_People_to_Teach;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int n1 = 2;
        int[][] languages1 = {{1}, {2}, {1, 2}};
        int[][] friendships1 = {{1, 2}, {1, 3}, {2, 3}};
        System.out.println(solution.minimumTeachings(n1, languages1, friendships1)); // Output: 1

        int n2 = 3;
        int[][] languages2 = {{2}, {1, 3}, {1, 2}, {3}};
        int[][] friendships2 = {{1, 4}, {1, 2}, {3, 4}, {2, 3}};
        System.out.println(solution.minimumTeachings(n2, languages2, friendships2)); // Output: 2
    }

    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        int m = languages.length;

        // store each user's known languages in a HashSet for fast lookup
        List<Set<Integer>> userLanguages = new ArrayList<>();

        for (int[] langArr : languages) {
            Set<Integer> set = new HashSet<>();
            for (int lang : langArr) set.add(lang);
            userLanguages.add(set);
        }

        // find problematic friendships
        Set<Integer> candidates = new HashSet<>();
        for (int[] f : friendships) {
            int u = f[0] - 1; // convert to 0-based
            int v = f[1] - 1;

            // check if u and v share a language
            boolean canTalk = false;

            for (int lang : userLanguages.get(u)) {
                if (userLanguages.get(v).contains(lang)) {
                    canTalk = true;
                    break;
                }
            }

            if (!canTalk) {
                candidates.add(u);
                candidates.add(v);
            }
        }

        // if no problematic friendships, no need to teach anyone
        if (candidates.isEmpty()) return 0;

        // try each language
        int minTeach = Integer.MAX_VALUE;
        for (int lang = 1; lang <= n; lang++) {
            int teachCount = 0;

            for (int user : candidates) {
                if (!userLanguages.get(user).contains(lang)) {
                    teachCount++;
                }
            }

            minTeach = Math.min(minTeach, teachCount);
        }

        return minTeach;
    }

}

//Complexity:
// time - O(n * (m + F))
// space - O(m * n)


//On a social network consisting of m users and some friendships between users, two users can communicate with each
// other if they know a common language.
//You are given an integer n, an array languages, and an array friendships where:
//There are n languages numbered 1 through n,
//languages[i] is the set of languages the i​​​​​​th​​​​ user knows, and
//friendships[i] = [u​​​​​​i​​​, v​​​​​​i] denotes a friendship between
// the users u​​​​​​​​​​​i​​​​​ and vi.
//You can choose one language and teach it to some users so that all friends can communicate with each other. Return
// the minimum number of users you need to teach.
//Note that friendships are not transitive, meaning if x is a friend of y and y is a friend of z, this doesn't
// guarantee that x is a friend of z.

//Example 1:
//Input: n = 2, languages = [[1],[2],[1,2]], friendships = [[1,2],[1,3],[2,3]]
//Output: 1
//Explanation: You can either teach user 1 the second language or user 2 the first language.

//Example 2:
//Input: n = 3, languages = [[2],[1,3],[1,2],[3]], friendships = [[1,4],[1,2],[3,4],[2,3]]
//Output: 2
//Explanation: Teach the third language to users 1 and 3, yielding two users to teach.

//Constraints:
//2 <= n <= 500
//languages.length == m
//1 <= m <= 500
//1 <= languages[i].length <= n
//1 <= languages[i][j] <= n
//1 <= u​​​​​​i < v​​​​​​i <= languages.length
//1 <= friendships.length <= 500
//All tuples (u​​​​​i, v​​​​​​i) are unique
//languages[i] contains only unique values
