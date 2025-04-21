package P2101_2200.P2145_Count_the_Hidden_Sequences;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.numberOfArrays(new int[]{1, -3, 4}, 1, 6)); // Output: 2

        System.out.println(solution.numberOfArrays(new int[]{3, -4, 5, 1, -2}, -4, 5)); // Output: 4

        System.out.println(solution.numberOfArrays(new int[]{4, -7, 2}, 3, 6)); // Output: 0
    }

    public int numberOfArrays(int[] differences, int lower, int upper) {
        long minSum = 0, maxSum = 0, current = 0;

        for (int difference : differences) {
            current += difference;
            minSum = Math.min(minSum, current);
            maxSum = Math.max(maxSum, current);
        }

        long possibleStartMin = lower;
        long possibleStartMax = upper;

        long minStart = possibleStartMin - minSum;
        long maxStart = possibleStartMax - maxSum;

        long count = maxStart - minStart + 1;

        return (int) Math.max(0, count);
    }

}

//Explanation:
//current keeps the prefix sum of the differences.
//minSum and maxSum track the lowest and highest values the hidden sequence can reach (relative to the starting point).
//To ensure the whole hidden sequence is in the [lower, upper] range, we determine what values the starting
// number (hidden[0]) can take.
//The result is the number of valid values that hidden[0] can be.
//Complexity:
//  time - O(n)
//  space - O(1)


//You are given a 0-indexed array of n integers differences, which describes the differences between each pair
// of consecutive integers of a hidden sequence of length (n + 1). More formally, call the hidden sequence hidden,
// then we have that differences[i] = hidden[i + 1] - hidden[i].
//You are further given two integers lower and upper that describe the inclusive range of values [lower, upper] that
// the hidden sequence can contain.
//For example, given differences = [1, -3, 4], lower = 1, upper = 6, the hidden sequence is a sequence of length 4
// whose elements are in between 1 and 6 (inclusive).
//[3, 4, 1, 5] and [4, 5, 2, 6] are possible hidden sequences.
//[5, 6, 3, 7] is not possible since it contains an element greater than 6.
//[1, 2, 3, 4] is not possible since the differences are not correct.
//Return the number of possible hidden sequences there are. If there are no possible sequences, return 0.

//Example 1:
//Input: differences = [1,-3,4], lower = 1, upper = 6
//Output: 2
//Explanation: The possible hidden sequences are:
//- [3, 4, 1, 5]
//- [4, 5, 2, 6]
//Thus, we return 2.

//Example 2:
//Input: differences = [3,-4,5,1,-2], lower = -4, upper = 5
//Output: 4
//Explanation: The possible hidden sequences are:
//- [-3, 0, -4, 1, 2, 0]
//- [-2, 1, -3, 2, 3, 1]
//- [-1, 2, -2, 3, 4, 2]
//- [0, 3, -1, 4, 5, 3]
//Thus, we return 4.

//Example 3:
//Input: differences = [4,-7,2], lower = 3, upper = 6
//Output: 0
//Explanation: There are no possible hidden sequences. Thus, we return 0.

//Constraints:
//n == differences.length
//1 <= n <= 105
//-105 <= differences[i] <= 105
//-105 <= lower <= upper <= 105
