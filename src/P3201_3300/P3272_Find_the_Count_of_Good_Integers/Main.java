package P3201_3300.P3272_Find_the_Count_of_Good_Integers;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.countGoodIntegers(3, 5)); // Output: 27
        System.out.println(solution.countGoodIntegers(1, 4)); // Output: 2
        System.out.println(solution.countGoodIntegers(5, 6)); // Output: 2468
        System.out.println(solution.countGoodIntegers(5, 7)); // Output: 2665
    }

    public long countGoodIntegers(int n, int k) {
        int halfLength = (n + 1) / 2;
        int minHalf = (int) Math.pow(10, halfLength - 1);
        int maxHalf = (int) Math.pow(10, halfLength);
        long ans = 0;
        Set<String> seen = new HashSet<>();

        for (int num = minHalf; num < maxHalf; ++num) {
            String firstHalf = Integer.toString(num);
            String secondHalf = new StringBuilder(firstHalf).reverse().toString();
            String palindrome = firstHalf + secondHalf.substring(n % 2);
            long palNum = Long.parseLong(palindrome);

            if (palNum % k != 0) continue;

            char[] digits = palindrome.toCharArray();
            Arrays.sort(digits);
            String sortedDigits = new String(digits);

            if (seen.contains(sortedDigits)) continue;
            seen.add(sortedDigits);

            int[] digitCount = new int[10];
            for (char c : palindrome.toCharArray()) {
                digitCount[c - '0']++;
            }

            // first digit cannot be zero
            int firstDigitChoices = n - digitCount[0];
            long permutations = firstDigitChoices * factorial(n - 1);

            for (int freq : digitCount) {
                if (freq > 1) {
                    permutations /= factorial(freq);
                }
            }

            ans += permutations;
        }

        return ans;
    }

    private long factorial(int n) {
        long res = 1;
        for (int i = 2; i <= n; ++i)
            res *= i;
        return res;
    }
}

//Explanation:
//Build palindromes:
//For n digits, generate all possible palindromes by mirroring the first half of digits.
//Check k-palindromic:
//For each palindrome, check if it's divisible by k. If not, skip.
//Count rearrangements:
//If it is divisible:
//Sort its digits to avoid counting duplicates (using a Set).
//-Count how many distinct permutations can be formed without leading zeros, accounting for repeated digits
// using factorials.
//-Sum up valid counts for all unique digit combinations.
//Complexity:
//time - O(m * n log n)  - m = 10^(n/2)
//space - O(m)


//You are given two positive integers n and k.
//An integer x is called k-palindromic if:
//x is a palindrome.
//x is divisible by k.
//An integer is called good if its digits can be rearranged to form a k-palindromic integer. For example, for
// k = 2, 2020 can be rearranged to form the k-palindromic integer 2002, whereas 1010 cannot be rearranged to
// form a k-palindromic integer.
//Return the count of good integers containing n digits.
//Note that any integer must not have leading zeros, neither before nor after rearrangement. For example, 1010
// cannot be rearranged to form 101.

//Example 1:
//Input: n = 3, k = 5
//Output: 27
//Explanation:
//Some of the good integers are:
//551 because it can be rearranged to form 515.
//525 because it is already k-palindromic.

//Example 2:
//Input: n = 1, k = 4
//Output: 2
//Explanation:
//The two good integers are 4 and 8.

//Example 3:
//Input: n = 5, k = 6
//Output: 2468

//Constraints:
//1 <= n <= 10
//1 <= k <= 9
