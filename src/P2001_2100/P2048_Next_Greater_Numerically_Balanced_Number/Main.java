package P2001_2100.P2048_Next_Greater_Numerically_Balanced_Number;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.nextBeautifulNumber(1));    // 22
        System.out.println(solution.nextBeautifulNumber(1000)); // 1333
        System.out.println(solution.nextBeautifulNumber(3000)); // 3133
    }

    public int nextBeautifulNumber(int n) {
        int num = n + 1;

        while (true) {
            if (isNumericallyBalanced(num)) {
                return num;
            }

            num++;
        }
    }

    private boolean isNumericallyBalanced(int num) {
        int[] count = new int[10];
        int temp = num;

        while (temp > 0) {
            count[temp % 10]++;
            temp /= 10;
        }

        for (int d = 0; d <= 9; d++) {
            if (count[d] > 0 && count[d] != d) {
                return false;
            }
        }

        return true;
    }

}

//Complexity:
// time - O(k * log n)
// space - O(1)


//An integer x is numerically balanced if for every digit d in the number x, there are exactly d occurrences of
// that digit in x.
//Given an integer n, return the smallest numerically balanced number strictly greater than n.

//Example 1:
//Input: n = 1
//Output: 22
//Explanation:
//22 is numerically balanced since:
//- The digit 2 occurs 2 times.
//It is also the smallest numerically balanced number strictly greater than 1.

//Example 2:
//Input: n = 1000
//Output: 1333
//Explanation:
//1333 is numerically balanced since:
//- The digit 1 occurs 1 time.
//- The digit 3 occurs 3 times.
//It is also the smallest numerically balanced number strictly greater than 1000.
//Note that 1022 cannot be the answer because 0 appeared more than 0 times.

//Example 3:
//Input: n = 3000
//Output: 3133
//Explanation:
//3133 is numerically balanced since:
//- The digit 1 occurs 1 time.
//- The digit 3 occurs 3 times.
//It is also the smallest numerically balanced number strictly greater than 3000.

//Constraints:
//0 <= n <= 106
