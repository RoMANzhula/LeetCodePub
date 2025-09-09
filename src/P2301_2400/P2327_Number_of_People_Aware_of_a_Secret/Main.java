package P2301_2400.P2327_Number_of_People_Aware_of_a_Secret;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.peopleAwareOfSecret(6, 2, 4)); // Output: 5
        System.out.println(solution.peopleAwareOfSecret(4, 1, 3)); // Output: 6
    }

    private static final int MOD = 1_000_000_007;

    public int peopleAwareOfSecret(int n, int delay, int forget) {
        long[] dp = new long[n + 1];
        dp[1] = 1; // day 1, first person knows the secret

        for (int i = 1; i <= n; i++) {
            if (dp[i] == 0) continue;

            // this person starts sharing from day (i + delay)
            for (int j = i + delay; j < i + forget && j <= n; j++) {
                dp[j] = (dp[j] + dp[i]) % MOD;
            }
        }

        // count all who still remember at day n
        long result = 0;
        for (int i = n - forget + 1; i <= n; i++) {
            if (i > 0) {
                result = (result + dp[i]) % MOD;
            }
        }

        return (int) result;
    }

}

//Complexity:
// time - O(n * forget)
// space - O(1)


//On day 1, one person discovers a secret.
//You are given an integer delay, which means that each person will share the secret with a new person every day,
// starting from delay days after discovering the secret. You are also given an integer forget, which means that
// each person will forget the secret forget days after discovering it. A person cannot share the secret on
// the same day they forgot it, or on any day afterwards.
//Given an integer n, return the number of people who know the secret at the end of day n. Since the answer may be
// very large, return it modulo 109 + 7.

//Example 1:
//Input: n = 6, delay = 2, forget = 4
//Output: 5
//Explanation:
//Day 1: Suppose the first person is named A. (1 person)
//Day 2: A is the only person who knows the secret. (1 person)
//Day 3: A shares the secret with a new person, B. (2 people)
//Day 4: A shares the secret with a new person, C. (3 people)
//Day 5: A forgets the secret, and B shares the secret with a new person, D. (3 people)
//Day 6: B shares the secret with E, and C shares the secret with F. (5 people)

//Example 2:
//Input: n = 4, delay = 1, forget = 3
//Output: 6
//Explanation:
//Day 1: The first person is named A. (1 person)
//Day 2: A shares the secret with B. (2 people)
//Day 3: A and B share the secret with 2 new people, C and D. (4 people)
//Day 4: A forgets the secret. B, C, and D share the secret with 3 new people. (6 people)

//Constraints:
//2 <= n <= 1000
//1 <= delay < forget <= n
