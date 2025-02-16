package P1701_1800.P1718_Construct_the_Lexicographically_Largest_Valid_Sequence;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(Arrays.toString(solution.constructDistancedSequence(3))); // Output: [3,1,2,3,2]
        System.out.println(Arrays.toString(solution.constructDistancedSequence(5))); // Output: [5,3,1,4,3,5,2,4,2]
    }

    public int[] constructDistancedSequence(int n) {
        int len = 2 * n - 1;
        int[] result = new int[len];
        boolean[] used = new boolean[n + 1];

        backtrack(result, used, 0, n);
        return result;
    }

    private boolean backtrack(int[] result, boolean[] used, int index, int n) {
        if (index == result.length) {
            return true;
        }
        if (result[index] != 0) {
            return backtrack(result, used, index + 1, n);
        }
        for (int i = n; i >= 1; i--) {
            if (used[i]) continue;

            if (i == 1) {
                result[index] = 1;
                used[i] = true;
                if (backtrack(result, used, index + 1, n)) return true;
                result[index] = 0;
                used[i] = false;
            } else {
                int secondIndex = index + i;
                if (secondIndex < result.length && result[secondIndex] == 0) {
                    result[index] = i;
                    result[secondIndex] = i;
                    used[i] = true;

                    if (backtrack(result, used, index + 1, n)) return true;

                    result[index] = 0;
                    result[secondIndex] = 0;
                    used[i] = false;
                }
            }
        }
        return false;
    }
}

//This solution uses backtracking to construct the lexicographically largest sequence while ensuring all
// constraints are met. It follows these steps:
//-Initialize an array of size 2 * n - 1 and a boolean used array to track placed numbers.
//-Try placing numbers from n to 1 in descending order to maintain lexicographical order.
//-For each number i:
//--If i == 1, place it once.
//--Otherwise, place i at index and index + i if both positions are empty.
//-Recursively move to the next position, backtracking if a placement leads to failure.
//-The function ensures only valid sequences are constructed while prioritizing the largest numbers first.
//Complexity: O(n)


//Given an integer n, find a sequence that satisfies all of the following:
//The integer 1 occurs once in the sequence.
//Each integer between 2 and n occurs twice in the sequence.
//For every integer i between 2 and n, the distance between the two occurrences of i is exactly i.
//The distance between two numbers on the sequence, a[i] and a[j], is the absolute difference of their indices, |j - i|.
//Return the lexicographically largest sequence. It is guaranteed that under the given constraints, there is always a
// solution.
//A sequence a is lexicographically larger than a sequence b (of the same length) if in the first position where a
// and b differ, sequence a has a number greater than the corresponding number in b. For example, [0,1,9,0] is
// lexicographically larger than [0,1,5,6] because the first position they differ is at the third number, and
// 9 is greater than 5.
//
//Example 1:
//Input: n = 3
//Output: [3,1,2,3,2]
//Explanation: [2,3,2,1,3] is also a valid sequence, but [3,1,2,3,2] is the lexicographically largest valid sequence.

//Example 2:
//Input: n = 5
//Output: [5,3,1,4,3,5,2,4,2]
//
//Constraints:
//1 <= n <= 20