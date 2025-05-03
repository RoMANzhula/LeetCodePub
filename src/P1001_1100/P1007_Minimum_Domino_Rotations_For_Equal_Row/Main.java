package P1001_1100.P1007_Minimum_Domino_Rotations_For_Equal_Row;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] tops1 = {2,1,2,4,2,2};
        int[] bottoms1 = {5,2,6,2,3,2};
        System.out.println(solution.minDominoRotations(tops1, bottoms1)); // Output: 2

        int[] tops2 = {3,5,1,2,3};
        int[] bottoms2 = {3,6,3,3,4};
        System.out.println(solution.minDominoRotations(tops2, bottoms2)); // Output: -1
    }

    public int minDominoRotations(int[] tops, int[] bottoms) {
        int firstT = tops[0];
        int firstB = bottoms[0];

        int rotations = checkData(tops, bottoms, firstT);

        if (rotations != -1) return rotations;

        // only check firstB if it's different
        if (firstT != firstB) {
            rotations = checkData(tops, bottoms, firstB);
        }

        return rotations;
    }

    private int checkData(int[] tops, int[] bottoms, int firstT) {
        int lenT = tops.length;

        int topRotations = 0, bottomRotations = 0;

        for (int i = 0; i < lenT; i++) {
            if (tops[i] != firstT && bottoms[i] != firstT) {
                return -1; // can't match target
            } else if (tops[i] != firstT) {
                topRotations++; // need to rotate to bring target(firstF) to top
            } else if (bottoms[i] != firstT) {
                bottomRotations++; // need to rotate to bring target(firstT) to bottom
            }
        }

        return Math.min(topRotations, bottomRotations);
    }

}

//Explanation:
//We check how many rotations are needed to make all values equal to target.
//If it's not possible at any index (neither top nor bottom has the target), return -1.
//Otherwise, return the minimum of the rotations needed for tops or bottoms.
//Complexity:
// time - O(n)
// space - O(1)


//In a row of dominoes, tops[i] and bottoms[i] represent the top and bottom halves of the ith
// domino. (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)
//We may rotate the ith domino, so that tops[i] and bottoms[i] swap values.
//Return the minimum number of rotations so that all the values in tops are the same, or all the values in
// bottoms are the same.
//If it cannot be done, return -1.

//Example 1:
//Input: tops = [2,1,2,4,2,2], bottoms = [5,2,6,2,3,2]
//Output: 2
//Explanation:
//The first figure represents the dominoes as given by tops and bottoms: before we do any rotations.
//If we rotate the second and fourth dominoes, we can make every value in the top row equal to 2, as indicated by
// the second figure.

//Example 2:
//Input: tops = [3,5,1,2,3], bottoms = [3,6,3,3,4]
//Output: -1
//Explanation:
//In this case, it is not possible to rotate the dominoes to make one row of values equal.

//Constraints:
//2 <= tops.length <= 2 * 104
//bottoms.length == tops.length
//1 <= tops[i], bottoms[i] <= 6
