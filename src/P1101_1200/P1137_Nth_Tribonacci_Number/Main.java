package P1101_1200.P1137_Nth_Tribonacci_Number;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.tribonacci(1));
        System.out.println(solution.tribonacci(2));
        System.out.println(solution.tribonacci(4));
        System.out.println(solution.tribonacci(25));
    }

    public int tribonacci(int n) {
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;

        int prevPrev = 0;
        int prev = 1;
        int curr = 1;

        for (int i = 3; i <= n; i++) {
            int next = prevPrev + prev + curr;
            prevPrev = prev;
            prev = curr;
            curr = next;
        }

        return curr;
    }

//    public int tribonacci(int n) {
//        if (n == 0) return 0;
//        if (n == 1 || n == 2) return 1;
//
//        int[] result = new int[n + 1];
//        result[0] = 0;
//        result[1] = 1;
//        result[2] = 1;
//
//        for (int i = 3; i <= n; i++) {
//            result[i] = result[i - 1] + result[i - 2] + result[i - 3];
//        }
//
//        return result[n];
//    }

}

//The Tribonacci sequence Tn is defined as follows:
//T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.
//Given n, return the value of Tn.
//
//Example 1:
//Input: n = 4
//Output: 4
//Explanation:
//T_3 = 0 + 1 + 1 = 2
//T_4 = 1 + 1 + 2 = 4

//Example 2:
//Input: n = 25
//Output: 1389537
//
//Constraints:
//0 <= n <= 37
//The answer is guaranteed to fit within a 32-bit integer, ie. answer <= 2^31 - 1.
