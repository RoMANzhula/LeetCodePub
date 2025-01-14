package P2501_2600.P2657_Find_the_Prefix_Common_Array_of_Two_Arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] A1 = {1, 3, 2, 4};
        int[] B1 = {3, 1, 2, 4};
        System.out.println(Arrays.toString(solution.findThePrefixCommonArray(A1, B1))); // Output: [0, 2, 3, 4]

        int[] A2 = {2, 3, 1};
        int[] B2 = {3, 1, 2};
        System.out.println(Arrays.toString(solution.findThePrefixCommonArray(A2, B2))); // Output: [0, 1, 3]
    }

    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int len = A.length;
        int[] result = new int[len];
        Set<Integer> seenInA = new HashSet<>();
        Set<Integer> seenInB = new HashSet<>();

        int commonCount = 0;

        for (int i = 0; i < len; i++) {
            // add current elements to the respective sets
            if (seenInB.contains(A[i])) commonCount++;

            seenInA.add(A[i]);

            if (seenInA.contains(B[i])) commonCount++;

            seenInB.add(B[i]);

            //store the current count of common elements
            result[i] = commonCount;
        }

        return result;
    }
}

//Explanation:
//Input Analysis: The problem guarantees that A and B are permutations of integers from 1 to n.
//
//Sets for Tracking:
//Two sets (seenInA and seenInB) are used to keep track of the elements encountered in A and B up to the current index.
//Common Count Calculation:
//-For each index i, the algorithm checks whether the current element of A exists in seenInB or the current
// element of B exists in seenInA.
//-If so, the common count is incremented.
//Result Storage:
//The cumulative common count is stored in the result array for each index.
//Output:
//The program prints the prefix common array for the provided input permutations.
//Complexity:
//Time Complexity:
//O(n), where n is the length of the arrays, because each index is processed once.
//Space Complexity:
//O(n), due to the sets used for tracking elements.


//Hi there!
//I need your professional help!
//Please give me the solve to this problem by Java:
//You are given two 0-indexed integer permutations A and B of length n.
//A prefix common array of A and B is an array C such that C[i] is equal to the count of numbers that are
// present at or before the index i in both A and B.
//Return the prefix common array of A and B.
//A sequence of n integers is called a permutation if it contains all integers from 1 to n exactly once.
//
//Example 1:
//Input: A = [1,3,2,4], B = [3,1,2,4]
//Output: [0,2,3,4]
//Explanation: At i = 0: no number is common, so C[0] = 0.
//At i = 1: 1 and 3 are common in A and B, so C[1] = 2.
//At i = 2: 1, 2, and 3 are common in A and B, so C[2] = 3.
//At i = 3: 1, 2, 3, and 4 are common in A and B, so C[3] = 4.

//Example 2:
//Input: A = [2,3,1], B = [3,1,2]
//Output: [0,1,3]
//Explanation: At i = 0: no number is common, so C[0] = 0.
//At i = 1: only 3 is common in A and B, so C[1] = 1.
//At i = 2: 1, 2, and 3 are common in A and B, so C[2] = 3.
//
//Constraints:
//1 <= A.length == B.length == n <= 50
//1 <= A[i], B[i] <= n
//It is guaranteed that A and B are both a permutation of n integers.