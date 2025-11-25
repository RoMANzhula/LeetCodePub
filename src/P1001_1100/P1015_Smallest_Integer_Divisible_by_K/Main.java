package P1001_1100.P1015_Smallest_Integer_Divisible_by_K;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.smallestRepunitDivByK(1));  // 1
        System.out.println(solution.smallestRepunitDivByK(2));  // -1
        System.out.println(solution.smallestRepunitDivByK(3));  // 3
        System.out.println(solution.smallestRepunitDivByK(7));  // 6 → 111111
    }

    public int smallestRepunitDivByK(int k) {
        // if k has factor 2 or 5, impossible
        if (k % 2 == 0 || k % 5 == 0) return -1;

        int remainder = 0;

        // at most k iterations (Pigeonhole principle)
        for (int length = 1; length <= k; length++) {
            remainder = (remainder * 10 + 1) % k;

            if (remainder == 0) {
                return length; // length of the repunit number
            }
        }

        return -1; // should never happen due to the check above
    }

}


//Given a positive integer k, you need to find the length of the smallest positive integer n such that n is
// divisible by k, and n only contains the digit 1.
//Return the length of n. If there is no such n, return -1.
//Note: n may not fit in a 64-bit signed integer.

//Example 1:
//Input: k = 1
//Output: 1
//Explanation: The smallest answer is n = 1, which has length 1.

//Example 2:
//Input: k = 2
//Output: -1
//Explanation: There is no such positive integer n divisible by 2.

//Example 3:
//Input: k = 3
//Output: 3
//Explanation: The smallest answer is n = 111, which has length 3.

//Constraints:
//1 <= k <= 105
