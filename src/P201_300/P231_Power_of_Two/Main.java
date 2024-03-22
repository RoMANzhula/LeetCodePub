package P201_300.P231_Power_of_Two;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.isPowerOfTwo(1)); // output: true
        System.out.println(solution.isPowerOfTwo(16)); // output: true
        System.out.println(solution.isPowerOfTwo(3)); // output: false
    }

    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & -n) == n;

//        return n > 0 && (n & (n - 1)) == 0;

//        return (n > 0) && ((n & -n) == n) && n <= (1 << 30); //bitwise shift

//        return (n > 0) && ((n & (n - 1)) == 0) && n <= (1 << 30); //bitwise shift

//        return (n > 0) && ((n & (n - 1)) == 0) && n <= 1073741824;

//        if (n < (int) Math.pow(-2, 31) || n > (int) Math.pow(2, 31) - 1 || n > 1073741824) {
//            return false;
//        } else if (n == 1 || n == 2 || n == 1073741824) {
//            return true;
//        }
//
//        List<Integer> powersOfTwo = new ArrayList<>();
//
//        for (int i = 1; i <= n; i *= 2) {
//            powersOfTwo.add(i);
//        }
//        return powersOfTwo.contains(n);

    }
}

//Given an integer n, return true if it is a power of two. Otherwise, return false.
//An integer n is a power of two, if there exists an integer x such that n == 2x.
//
//Example 1:
//Input: n = 1
//Output: true
//Explanation: 20 = 1

//Example 2:
//Input: n = 16
//Output: true
//Explanation: 24 = 16

//Example 3:
//Input: n = 3
//Output: false
//
//Constraints:
//-231 <= n <= 231 - 1
