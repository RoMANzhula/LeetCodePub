package P1301_1400.P1399_Count_Largest_Group;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.countLargestGroup(13)); // Output: 4
        System.out.println(solution.countLargestGroup(2));  // Output: 2
    }

    public int countLargestGroup(int n) {
        HashMap<Integer, Integer> map = new HashMap<>();

        //fill the map with digit sums and their counts
        for (int i = 1; i <= n; i++) {
            int sum = digitSum(i);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        // find the maximum group size
        int maxSize = 0;
        for (int count : map.values()) {
            maxSize = Math.max(maxSize, count);
        }

        // count how many groups have this maximum size
        int result = 0;
        for (int count : map.values()) {
            if (count == maxSize) {
                result++;
            }
        }

        return result;
    }

    private static int digitSum(int num) {
        int sum = 0;
        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

}

//Complexity:
// time - O(n * log n)
// space - O(2)


//You are given an integer n.
//Each number from 1 to n is grouped according to the sum of its digits.
//Return the number of groups that have the largest size.

//Example 1:
//Input: n = 13
//Output: 4
//Explanation: There are 9 groups in total, they are grouped according sum of its digits of numbers from 1 to 13:
//[1,10], [2,11], [3,12], [4,13], [5], [6], [7], [8], [9].
//There are 4 groups with largest size.

//Example 2:
//Input: n = 2
//Output: 2
//Explanation: There are 2 groups [1], [2] of size 1.

//Constraints:
//1 <= n <= 104
