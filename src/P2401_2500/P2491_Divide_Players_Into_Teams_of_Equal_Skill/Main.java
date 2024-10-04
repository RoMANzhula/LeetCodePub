package P2401_2500.P2491_Divide_Players_Into_Teams_of_Equal_Skill;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] skill1 = {3, 2, 5, 1, 3, 4};
        System.out.println(solution.dividePlayers(skill1)); // Output: 22

        int[] skill2 = {3, 4};
        System.out.println(solution.dividePlayers(skill2)); // Output: 12

        int[] skill3 = {1, 1, 2, 3};
        System.out.println(solution.dividePlayers(skill3)); // Output: -1
    }

    public long dividePlayers(int[] skill) {
        // Sort the skill array
        Arrays.sort(skill);

        int n = skill.length;
        long totalChemistry = 0;

        // Calculate the expected total skill for each team
        int targetSkill = skill[0] + skill[n - 1];

        // Iterate over the first half and second half of the array
        for (int i = 0; i < n / 2; i++) {
            int teamSkill = skill[i] + skill[n - i - 1];

            // Check if the current pair's total skill matches the target skill
            if (teamSkill != targetSkill) {
                return -1; // Return -1 if not all teams have the same total skill
            }

            // Add the chemistry of the current pair (product of skills)
            totalChemistry += (long) skill[i] * skill[n - i - 1];
        }

        // Return the total chemistry
        return totalChemistry;
    }
}

//Explanation of Code:
//Sorting the array: We sort the skill array to make it easier to pair players optimally.
//Target skill for each team: The total skill for the first team is calculated as skill[0] + skill[n-1]. This sum
// should be the same for all teams.
//Pairing and checking: For each pair of players from the beginning and the end of the sorted array, we check if
// their total skill matches the target. If any mismatch is found, we return -1.
//Calculating chemistry: If all teams have the same total skill, we compute the chemistry as the product of
// the paired players and sum it up.
//Time Complexity:
//Sorting the array takes O(n logn).
//The pairing and chemistry calculation loop runs in O(n). Thus, the overall time complexity is
//O(n log n), which is efficient enough for the given constraints.


//You are given a positive integer array skill of even length n where skill[i] denotes the skill of the ith
// player. Divide the players into n / 2 teams of size 2 such that the total skill of each team is equal.
//The chemistry of a team is equal to the product of the skills of the players on that team.
//Return the sum of the chemistry of all the teams, or return -1 if there is no way to divide the players into
// teams such that the total skill of each team is equal.
//
//Example 1:
//Input: skill = [3,2,5,1,3,4]
//Output: 22
//Explanation:
//Divide the players into the following teams: (1, 5), (2, 4), (3, 3), where each team has a total skill of 6.
//The sum of the chemistry of all the teams is: 1 * 5 + 2 * 4 + 3 * 3 = 5 + 8 + 9 = 22.

//Example 2:
//Input: skill = [3,4]
//Output: 12
//Explanation:
//The two players form a team with a total skill of 7.
//The chemistry of the team is 3 * 4 = 12.

//Example 3:
//Input: skill = [1,1,2,3]
//Output: -1
//Explanation:
//There is no way to divide the players into teams such that the total skill of each team is equal.
//
//Constraints:
//2 <= skill.length <= 105
//skill.length is even.
//1 <= skill[i] <= 1000
