package P2485_Find_the_Pivot_Integer;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.pivotInteger(8)); // output 6
        System.out.println(solution.pivotInteger(1)); // output 1
        System.out.println(solution.pivotInteger(4)); // output -1
    }

    public int pivotInteger(int n) {
        int totalSum = n * (n + 1) / 2; // sum of all elements from 1 to n
        int leftSum = 0;

        for (int x = 1; x <= n; x++) {
            leftSum += x;
            int rightSum = totalSum - leftSum + x; // adjusting for x, as it's excluded from the right sum

            if (leftSum == rightSum) {
                return x;
            }
        }

        return -1; // if no pivot integer found
    }
}

//Given a positive integer n, find the pivot integer x such that:
//The sum of all elements between 1 and x inclusively equals the sum of all elements between x and n inclusively.
//Return the pivot integer x. If no such integer exists, return -1. It is guaranteed that there will be at most
// one pivot index for the given input.
//
//Example 1:
//Input: n = 8
//Output: 6
//Explanation: 6 is the pivot integer since: 1 + 2 + 3 + 4 + 5 + 6 = 6 + 7 + 8 = 21.

//Example 2:
//Input: n = 1
//Output: 1
//Explanation: 1 is the pivot integer since: 1 = 1.

//Example 3:
//Input: n = 4
//Output: -1
//Explanation: It can be proved that no such integer exist.
//
//Constraints:
//1 <= n <= 1000
