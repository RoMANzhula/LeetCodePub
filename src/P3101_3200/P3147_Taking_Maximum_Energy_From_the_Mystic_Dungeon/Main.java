package P3101_3200.P3147_Taking_Maximum_Energy_From_the_Mystic_Dungeon;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] energy1 = {5, 2, -10, -5, 1};
        int k1 = 3;
        System.out.println(solution.maximumEnergy(energy1, k1)); // Output: 3

        int[] energy2 = {-2, -3, -1};
        int k2 = 2;
        System.out.println(solution.maximumEnergy(energy2, k2)); // output: -1
    }

    public int maximumEnergy(int[] energy, int k) {
        int n = energy.length;
        int[] dp = new int[n];
        int maxEnergy = Integer.MIN_VALUE;

        // fill DP from right to left
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = energy[i];

            if (i + k < n) {
                dp[i] += dp[i + k];
            }

            maxEnergy = Math.max(maxEnergy, dp[i]);
        }

        return maxEnergy;
    }

}

//Complexity:
// time and space - O(n)


//In a mystic dungeon, n magicians are standing in a line. Each magician has an attribute that gives you
// energy. Some magicians can give you negative energy, which means taking energy from you.
//You have been cursed in such a way that after absorbing energy from magician i, you will be instantly transported to
// magician (i + k). This process will be repeated until you reach the magician where (i + k) does not exist.
//In other words, you will choose a starting point and then teleport with k jumps until you reach the end of the
// magicians' sequence, absorbing all the energy during the journey.
//You are given an array energy and an integer k. Return the maximum possible energy you can gain.
//Note that when you are reach a magician, you must take energy from them, whether it is negative or positive energy.

//Example 1:
//Input: energy = [5,2,-10,-5,1], k = 3
//Output: 3
//Explanation: We can gain a total energy of 3 by starting from magician 1 absorbing 2 + 1 = 3.

//Example 2:
//Input: energy = [-2,-3,-1], k = 2
//Output: -1
//Explanation: We can gain a total energy of -1 by starting from magician 2.

//Constraints:
//1 <= energy.length <= 105
//-1000 <= energy[i] <= 1000
//1 <= k <= energy.length - 1
