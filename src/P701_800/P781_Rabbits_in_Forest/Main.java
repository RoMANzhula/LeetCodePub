package P701_800.P781_Rabbits_in_Forest;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] answers1 = {1, 1, 2};
        System.out.println(solution.numRabbits(answers1)); // Output: 5

        int[] answers2 = {10, 10, 10};
        System.out.println(solution.numRabbits(answers2)); // Output: 11
    }

    public int numRabbits(int[] answers) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int totalRabbits = 0;

        for (int answer : answers) {
            countMap.put(answer, countMap.getOrDefault(answer, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            int answer = entry.getKey();
            int count = entry.getValue();

            // each group can have (answer + 1) rabbits
            int groupSize = answer + 1;

            // we need enough groups to cover all rabbits giving this answer
            int numberOfGroups = (count + answer) / groupSize;

            totalRabbits += numberOfGroups * groupSize;
        }

        return totalRabbits;
    }

}

//Explanation:
//We count how many rabbits gave each answer.
//For each answer k, we calculate how many full groups of size k + 1 we need to cover all rabbits that gave that answer.
//Then we multiply the number of groups by group size to get the minimum number of rabbits.
//Complexity:
//time and space - O(n)


//There is a forest with an unknown number of rabbits. We asked n rabbits "How many rabbits have the same
// color as you?" and collected the answers in an integer array answers where answers[i] is the answer of
// the ith rabbit.
//Given the array answers, return the minimum number of rabbits that could be in the forest.

//Example 1:
//Input: answers = [1,1,2]
//Output: 5
//Explanation:
//The two rabbits that answered "1" could both be the same color, say red.
//The rabbit that answered "2" can't be red or the answers would be inconsistent.
//Say the rabbit that answered "2" was blue.
//Then there should be 2 other blue rabbits in the forest that didn't answer into the array.
//The smallest possible number of rabbits in the forest is therefore 5: 3 that answered plus 2 that didn't.

//Example 2:
//Input: answers = [10,10,10]
//Output: 11

//Constraints:
//1 <= answers.length <= 1000
//0 <= answers[i] < 1000
