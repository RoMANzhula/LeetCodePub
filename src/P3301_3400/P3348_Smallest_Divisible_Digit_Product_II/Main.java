package P3301_3400.P3348_Smallest_Divisible_Digit_Product_II;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.smallestNumber("1234", 256)); // 1488

        System.out.println(solution.smallestNumber("12355", 50)); // 12355

        System.out.println(solution.smallestNumber("11111", 26)); // -1

        System.out.println(solution.smallestNumber("100", 1)); // 111

    }

    // factor contribution of digits 1...9
    static final int[][] FACTOR_COUNTS  = {
            {0, 0, 0, 0}, // 0 - never used
            {0, 0, 0, 0}, // 1
            {1, 0, 0, 0}, // 2
            {0, 1, 0, 0}, // 3
            {2, 0, 0, 0}, // 4
            {0, 0, 1, 0}, // 5
            {1, 1, 0, 0}, // 6
            {0, 0, 0, 1}, // 7
            {3, 0, 0, 0}, // 8
            {0, 2, 0, 0}  // 9
    };

    private static int[] getPrimeCount(long t) {

        int[] count = new int[4];

        int[] primes = {2, 3, 5, 7};

        for (int i = 0; i < 4; i++) {
            int prime = primes[i];

            while (t % prime == 0) {
                t /= prime;
                count[i]++;
            }
        }

        if (t != 1) {
            return null;
        }

        return count;
    }

    private static int[] getFactorCount(int[] count) {

        int count8 = count[0] / 3;
        int remaining2 = count[0] % 3;

        int count9 = count[1] / 2;
        int count3 = count[1] % 2;

        int count4 = remaining2 / 2;
        int count2 = remaining2 % 2;

        int count6 = 0;

        if (count2 == 1 && count3 == 1) {
            count2 = 0;
            count3 = 0;
            count6 = 1;
        }

        if (count3 == 1 && count4 == 1) {
            count2 = 1;
            count6 = 1;
            count3 = 0;
            count4 = 0;
        }

        return new int[]{
                count2,
                count3,
                count4,
                count[2],
                count6,
                count[3],
                count8,
                count9
        };
    }

    private static int getFactorCountSize(int[] factorCount) {

        int result = 0;

        for (int count : factorCount) {
            result += count;
        }

        return result;
    }

    private static String factorCountToString(int[] factorCount) {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < factorCount.length; i++) {

            int digit = i + 2;

            for (int j = 0; j < factorCount[i]; j++) {
                result.append(digit);
            }
        }

        return result.toString();
    }


    private static int[] getRequiredFactorCount(
            int[] primeCount,
            int[] prefixFactors,
            int[] replacementFactors) {

        int[] remaining = new int[4];

        for (int i = 0; i < 4; i++) {

            remaining[i] =
                    primeCount[i]
                            - prefixFactors[i]
                            - replacementFactors[i];

            remaining[i] = Math.max(0, remaining[i]);
        }

        return getFactorCount(remaining);
    }

    public String smallestNumber(String num, long t) {

        int[] primeCount = getPrimeCount(t);

        if (primeCount == null) {
            return "-1";
        }


        int[] factorCount = getFactorCount(primeCount);

        if (getFactorCountSize(factorCount) > num.length()) {

            return factorCountToString(factorCount);
        }

        int n = num.length();


        int[][] prefix = new int[n + 1][4];

        for (int i = 0; i < n; i++) {

            int digit = num.charAt(i) - '0';

            for (int j = 0; j < 4; j++) {

                prefix[i + 1][j] =
                        prefix[i][j]
                                + FACTOR_COUNTS[digit][j];
            }
        }

        int firstZeroIndex = n;

        for (int i = 0; i < n; i++) {

            if (num.charAt(i) == '0') {
                firstZeroIndex = i;
                break;
            }
        }

        if (firstZeroIndex == n) {

            boolean divisible = true;

            for (int j = 0; j < 4; j++) {

                if (primeCount[j] > prefix[n][j]) {
                    divisible = false;
                    break;
                }
            }

            if (divisible) {
                return num;
            }
        }

        int[] prefixFactors = prefix[n].clone();

        for (int i = n - 1; i >= 0; i--) {

            int currentDigit = num.charAt(i) - '0';

            for (int j = 0; j < 4; j++) {
                prefixFactors[j] -= FACTOR_COUNTS[currentDigit][j];
            }

            int spaceAfterThisDigit = n - 1 - i;

            if (i <= firstZeroIndex) {

                for (int biggerDigit = currentDigit + 1;
                     biggerDigit <= 9;
                     biggerDigit++) {

                    int[] factorsAfterReplacement =
                            getRequiredFactorCount(
                                    primeCount,
                                    prefixFactors,
                                    FACTOR_COUNTS[biggerDigit]
                            );

                    int requiredDigits =
                            getFactorCountSize(factorsAfterReplacement);

                    if (requiredDigits <= spaceAfterThisDigit) {


                        int fillOnes =
                                spaceAfterThisDigit - requiredDigits;

                        StringBuilder result =
                                new StringBuilder(n);

                        result.append(num, 0, i);

                        result.append(biggerDigit);

                        result.append("1".repeat(fillOnes));

                        result.append(
                                factorCountToString(
                                        factorsAfterReplacement
                                )
                        );

                        return result.toString();
                    }
                }
            }
        }

        factorCount = getFactorCount(primeCount);

        int requiredDigits =
                getFactorCountSize(factorCount);

        StringBuilder result = new StringBuilder(n + 1);

        result.append(
                "1".repeat(
                        n + 1 - requiredDigits
                )
        );

        result.append(
                factorCountToString(factorCount)
        );

        return result.toString();
    }

}


//You are given a string num which represents a positive integer, and an integer t.
//A number is called zero-free if none of its digits are 0.
//Return a string representing the smallest zero-free number greater than or equal to num such that the product of
// its digits is divisible by t. If no such number exists, return "-1".

//Example 1:
//Input: num = "1234", t = 256
//Output: "1488"
//Explanation:
//The smallest zero-free number that is greater than 1234 and has the product of its digits divisible by 256 is
// 1488, with the product of its digits equal to 256.

//Example 2:
//Input: num = "12355", t = 50
//Output: "12355"
//Explanation:
//12355 is already zero-free and has the product of its digits divisible by 50, with the product of its digits
// equal to 150.

//Example 3:
//Input: num = "11111", t = 26
//Output: "-1"
//Explanation:
//No number greater than 11111 has the product of its digits divisible by 26.

//Constraints:
//2 <= num.length <= 2 * 105
//num consists only of digits in the range ['0', '9'].
//num does not contain leading zeros.
//1 <= t <= 1014
