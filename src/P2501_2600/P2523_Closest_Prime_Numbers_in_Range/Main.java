package P2501_2600.P2523_Closest_Prime_Numbers_in_Range;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(Arrays.toString(solution.closestPrimes(10, 19))); // Output: [11, 13]
        System.out.println(Arrays.toString(solution.closestPrimes(4, 6)));   // Output: [-1, -1]
    }

    public int[] closestPrimes(int left, int right) {
        // step 1: use Sieve of Eratosthenes to find prime numbers up to right
        boolean[] isPrime = new boolean[right + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false; // 0 and 1 are not prime

        for (int i = 2; i * i <= right; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= right; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        // step 2: find primes in the range [left, right]
        List<Integer> primes = new ArrayList<>();
        for (int i = Math.max(left, 2); i <= right; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }

        // step 3: find the closest pair of primes
        if (primes.size() < 2) return new int[]{-1, -1}; // not enough primes

        int minDiff = Integer.MAX_VALUE;
        int[] result = {-1, -1};

        for (int i = 1; i < primes.size(); i++) {
            int num1 = primes.get(i - 1);
            int num2 = primes.get(i);
            int diff = num2 - num1;

            if (diff < minDiff) {
                minDiff = diff;
                result[0] = num1;
                result[1] = num2;
            }
        }

        return result;
    }
}

//Explanation:
//Sieve of Eratosthenes:
//-Creates a boolean array isPrime where isPrime[i] = true means i is prime.
//-Marks non-prime numbers efficiently in O(N log log N) time complexity.
//Filtering Primes in Range:
//-Iterates through isPrime and collects primes in [left, right].
//Finding the Closest Pair:
//-Iterates through the list of primes and finds the pair with the smallest difference.
//-If multiple pairs have the same minimum difference, the first one is chosen.
//Complexity Analysis:
//Sieve of Eratosthenes: O(N log log N)
//Filtering primes: O(N)
//Finding closest pair: O(N)
//Overall Complexity: O(N log log N) + O(N) = O(N log log N) (Efficient for large values)


//Given two positive integers left and right, find the two integers num1 and num2 such that:
//left <= num1 < num2 <= right .
//Both num1 and num2 are prime numbers.
//num2 - num1 is the minimum amongst all other pairs satisfying the above conditions.
//Return the positive integer array ans = [num1, num2]. If there are multiple pairs satisfying these conditions,
// return the one with the smallest num1 value. If no such numbers exist, return [-1, -1].

//Example 1:
//Input: left = 10, right = 19
//Output: [11,13]
//Explanation: The prime numbers between 10 and 19 are 11, 13, 17, and 19.
//The closest gap between any pair is 2, which can be achieved by [11,13] or [17,19].
//Since 11 is smaller than 17, we return the first pair.

//Example 2:
//Input: left = 4, right = 6
//Output: [-1,-1]
//Explanation: There exists only one prime number in the given range, so the conditions cannot be satisfied.

//Constraints:
//1 <= left <= right <= 106