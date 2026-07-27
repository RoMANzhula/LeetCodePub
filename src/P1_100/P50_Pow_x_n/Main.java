package P1_100.P50_Pow_x_n;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        double x1 = 2.00000;
        int n1 = 10;

        System.out.println(solution.myPow(x1, n1)); // 1024.0000
    }

    public double myPow(double x, int n) {
        long power = n;

        if (power < 0) {
            x = 1 / x;
            power = -power;
        }

        return fastPow(x, power);
    }

    private double fastPow(double x, long n) {
        if (n == 0) {
            return 1;
        }

        double half = fastPow(x, n / 2);

        if (n % 2 == 0) {
            return half * half;
        }

        return half * half * x;
    }

}

//Complexity:
// time and space - O(log n)


//Implement pow(x, n), which calculates x raised to the power n (i.e., xn).

//Example 1:
//Input: x = 2.00000, n = 10
//Output: 1024.00000

//Example 2:
//Input: x = 2.10000, n = 3
//Output: 9.26100

//Example 3:
//Input: x = 2.00000, n = -2
//Output: 0.25000
//Explanation: 2-2 = 1/22 = 1/4 = 0.25

//Constraints:
//-100.0 < x < 100.0
//-231 <= n <= 231-1
//n is an integer.
//Either x is not zero or n > 0.
//-104 <= xn <= 104
