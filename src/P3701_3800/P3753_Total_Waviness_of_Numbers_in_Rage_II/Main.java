package P3701_3800.P3753_Total_Waviness_of_Numbers_in_Rage_II;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.totalWaviness(120, 130)); // 3
        System.out.println(solution.totalWaviness(198, 202)); // 3
        System.out.println(solution.totalWaviness(4848, 4848)); // 2
    }

    private static class Node {
        long count;
        long waviness;

        Node(long count, long waviness) {
            this.count = count;
            this.waviness = waviness;
        }
    }

    private String digits;
    private Node[][][][][] memo;

    public long totalWaviness(long num1, long num2) {
        return solve(num2) - solve(num1 - 1);
    }

    private long solve(long n) {
        if (n <= 0) {
            return 0;
        }

        digits = Long.toString(n);

        memo = new Node[digits.length()][2][2][11][11];

        return dfs(0, 1, 0, 10, 10).waviness;
    }

    private Node dfs(int pos,
                     int tight,
                     int started,
                     int prev1,
                     int prev2
    ) {

        if (pos == digits.length()) {
            return new Node(1, 0);
        }

        if (memo[pos][tight][started][prev1][prev2] != null) {
            return memo[pos][tight][started][prev1][prev2];
        }

        int limit = tight == 1
                ? digits.charAt(pos) - '0'
                : 9;

        long totalCount = 0;
        long totalWaviness = 0;

        for (int d = 0; d <= limit; d++) {

            int nextTight =
                    (tight == 1 && d == limit) ? 1 : 0;

            if (started == 0 && d == 0) {

                Node next =
                        dfs(pos + 1,
                                nextTight,
                                0,
                                10,
                                10);

                totalCount += next.count;
                totalWaviness += next.waviness;

            } else {

                int add = 0;

                if (started == 1 && prev2 != 10) {

                    boolean peak =
                            prev1 > prev2 &&
                                    prev1 > d;

                    boolean valley =
                            prev1 < prev2 &&
                                    prev1 < d;

                    if (peak || valley) {
                        add = 1;
                    }
                }

                int newPrev1;
                int newPrev2;

                if (started == 0) {
                    newPrev1 = d;
                    newPrev2 = 10;
                } else {
                    newPrev2 = prev1;
                    newPrev1 = d;
                }

                Node next =
                        dfs(pos + 1,
                                nextTight,
                                1,
                                newPrev1,
                                newPrev2);

                totalCount += next.count;

                totalWaviness +=
                        next.waviness +
                                next.count * add;
            }
        }

        return memo[pos][tight][started][prev1][prev2] =
                new Node(totalCount, totalWaviness);
    }

}


//You are given two integers num1 and num2 representing an inclusive range [num1, num2].
//The waviness of a number is defined as the total count of its peaks and valleys:
//A digit is a peak if it is strictly greater than both of its immediate neighbors.
//A digit is a valley if it is strictly less than both of its immediate neighbors.
//The first and last digits of a number cannot be peaks or valleys.
//Any number with fewer than 3 digits has a waviness of 0.
//Return the total sum of waviness for all numbers in the range [num1, num2].

//Example 1:
//Input: num1 = 120, num2 = 130
//Output: 3
//Explanation:
//In the range [120, 130]:
//120: middle digit 2 is a peak, waviness = 1.
//121: middle digit 2 is a peak, waviness = 1.
//130: middle digit 3 is a peak, waviness = 1.
//All other numbers in the range have a waviness of 0.
//Thus, total waviness is 1 + 1 + 1 = 3.

//Example 2:
//Input: num1 = 198, num2 = 202
//Output: 3
//Explanation:
//In the range [198, 202]:
//198: middle digit 9 is a peak, waviness = 1.
//201: middle digit 0 is a valley, waviness = 1.
//202: middle digit 0 is a valley, waviness = 1.
//All other numbers in the range have a waviness of 0.
//Thus, total waviness is 1 + 1 + 1 = 3.

//Example 3:
//Input: num1 = 4848, num2 = 4848
//Output: 2
//Explanation:
//Number 4848: the second digit 8 is a peak, and the third digit 4 is a valley, giving a waviness of 2.

//Constraints:
//1 <= num1 <= num2 <= 1015​​​​​​​
