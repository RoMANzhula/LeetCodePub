package P401_500.P403_Frog_Jump;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        int[] array1 = {0,1,3,5,6,8,12,17};
        int[] array2 = {0,1,2,3,4,8,9,11};

        System.out.println(canCross(array1));
        System.out.println(canCross(array2));
    }

    public static boolean canCross(int[] stones) {
        int numStones = stones.length; //the number of stones in the river
        Set<Integer>[] sizeOfJumps = new Set[numStones]; //array for set with difference sizes of jumps
        for (int i = 0; i < numStones; i++) {
            sizeOfJumps[i] = new HashSet<>(); //for all jumps to i-stone
        }
        sizeOfJumps[0].add(0); //start for frog

        for (int i = 0; i < numStones; i++) { //double loop for each stone
            for (int j : sizeOfJumps[i]) { //and for each jump
                for (int step = j - 1; step <= j + 1; step++) { //nested loop for each possible size of jump
                    if (step > 0) { //check the jump size
                        int nextStone = stones[i] + step; //calculate the position of the next stone after a jump at a
                        // step distance from the current stone
                        int index = Arrays.binarySearch(stones, nextStone); //searching for the index of the next stone
                        // in the stones array
                        if (index >= 0) { //if positive
                            sizeOfJumps[index].add(step); //we find stone and add it in our array
                        }
                    }
                }
            }
        }

        return !sizeOfJumps[numStones - 1].isEmpty(); //check last element - if not empty - OK
    }
}

//A frog is crossing a river. The river is divided into some number of units, and at each unit, there may or may not
// exist a stone. The frog can jump on a stone, but it must not jump into the water.
//Given a list of stones' positions (in units) in sorted ascending order, determine if the frog can cross the river by
// landing on the last stone. Initially, the frog is on the first stone and assumes the first jump must be 1 unit.
//If the frog's last jump was k units, its next jump must be either k - 1, k, or k + 1 units. The frog can only jump in
// the forward direction.

//Example 1:
//Input: stones = [0,1,3,5,6,8,12,17]
//Output: true
//Explanation: The frog can jump to the last stone by jumping 1 unit to the 2nd stone, then 2 units to the 3rd stone,
// then 2 units to the 4th stone, then 3 units to the 6th stone, 4 units to the 7th stone, and 5 units to the 8th stone.

//Example 2:
//Input: stones = [0,1,2,3,4,8,9,11]
//Output: false
//Explanation: There is no way to jump to the last stone as the gap between the 5th and 6th stone is too large.

//Constraints:
//2 <= stones.length <= 2000
//0 <= stones[i] <= 231 - 1
//stones[0] == 0
//stones is sorted in a strictly increasing order.
