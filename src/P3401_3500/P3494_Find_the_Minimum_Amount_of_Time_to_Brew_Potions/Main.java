package P3401_3500.P3494_Find_the_Minimum_Amount_of_Time_to_Brew_Potions;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] skill1 = {1, 5, 2, 4};
        int[] mana1 = {5, 1, 4, 2};
        System.out.println(solution.minTime(skill1, mana1)); // 110

        int[] skill2 = {1, 1, 1};
        int[] mana2 = {1, 1, 1};
        System.out.println(solution.minTime(skill2, mana2)); // 5

        int[] skill3 = {1, 2, 3, 4};
        int[] mana3 = {1, 2};
        System.out.println(solution.minTime(skill3, mana3)); // 21
    }

    public long minTime(int[] skill, int[] mana) {
        int n = skill.length;
        int m = mana.length;

        // prefixPrev[i] = completion time at wizard i for the previous potion if that potion started at
        // time 0 (i.e. cumulative sums of p_i for that potion).
        long[] prefixPrev = new long[n];
        prefixPrev[0] = (long) skill[0] * mana[0];

        for (int i = 1; i < n; i++) {
            prefixPrev[i] = prefixPrev[i - 1] + (long) skill[i] * mana[0];
        }

        long S_prev = 0L; // start time of previous potion on wizard 0

        for (int j = 1; j < m; j++) {
            long[] prefixCurr = new long[n];
            long prefixCurrStart = 0L; // sum of p_t for current potion up to t = i-1
            long maxDelta = Long.MIN_VALUE;

            for (int i = 0; i < n; i++) {
                // A = completion at wizard i for previous potion (if it started at 0)
                long A = prefixPrev[i];

                // B = start at wizard i for current potion (if it started at 0) = sum_{t=0}^{i-1} p_t_curr = prefixCurrStart
                long B = prefixCurrStart;

                long candidate = A - B;
                if (candidate > maxDelta) maxDelta = candidate;

                long p = (long) skill[i] * mana[j]; // processing time of wizard i on potion j
                prefixCurrStart += p;
                prefixCurr[i] = prefixCurrStart; // completion at i for current potion (if started at 0)

            }

            long S_curr = S_prev + maxDelta;
            // move curr -> prev for next iteration
            prefixPrev = prefixCurr;
            S_prev = S_curr;
        }

        // makespan = start of last potion on wizard0 + total processing time of last potion
        return S_prev + prefixPrev[n - 1];
    }

}

//Complexity:
// time - O(n * m)
// space - O(n)


//You are given two integer arrays, skill and mana, of length n and m, respectively.
//In a laboratory, n wizards must brew m potions in order. Each potion has a mana capacity mana[j] and must pass
// through all the wizards sequentially to be brewed properly. The time taken by the ith wizard on the jth potion is
// timeij = skill[i] * mana[j].
//Since the brewing process is delicate, a potion must be passed to the next wizard immediately after the current
// wizard completes their work. This means the timing must be synchronized so that each wizard begins working on a
// potion exactly when it arrives.
//Return the minimum amount of time required for the potions to be brewed properly.

//Example 1:
//Input: skill = [1,5,2,4], mana = [5,1,4,2]
//Output: 110
//Explanation:
//Potion Number	Start time	Wizard 0 done by	Wizard 1 done by	Wizard 2 done by	Wizard 3 done by
//      0	          0             5	           30	                  40	               60
//      1	         52	           53	           58	                  60	               64
//      2	         54	           58	           78	                  86	               102
//      3	         86	           88	           98	                  102	               110
//As an example for why wizard 0 cannot start working on the 1st potion before time t = 52, consider the case
// where the wizards started preparing the 1st potion at time t = 50. At time t = 58, wizard 2 is done with
// the 1st potion, but wizard 3 will still be working on the 0th potion till time t = 60.

//Example 2:
//Input: skill = [1,1,1], mana = [1,1,1]
//Output: 5
//Explanation:
//Preparation of the 0th potion begins at time t = 0, and is completed by time t = 3.
//Preparation of the 1st potion begins at time t = 1, and is completed by time t = 4.
//Preparation of the 2nd potion begins at time t = 2, and is completed by time t = 5.

//Example 3:
//Input: skill = [1,2,3,4], mana = [1,2]
//Output: 21

//Constraints:
//n == skill.length
//m == mana.length
//1 <= n, m <= 5000
//1 <= mana[i], skill[i] <= 5000
