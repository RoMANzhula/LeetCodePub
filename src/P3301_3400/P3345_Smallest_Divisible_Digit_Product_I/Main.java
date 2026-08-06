package P3301_3400.P3345_Smallest_Divisible_Digit_Product_I;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.smallestNumber(10, 2)); // 10
        System.out.println(solution.smallestNumber(15, 3)); // 16
        System.out.println(solution.smallestNumber(19, 5)); // 20
    }

    public int smallestNumber(int n, int t) {
        int num = n;

        while (true) {
            if (digitProduct(num) % t == 0) {
                return num;
            }
            num++;
        }
    }

    private int digitProduct(int num) {
        int product = 1;

        while (num > 0) {
            product *= (num % 10);
            num /= 10;
        }

        return product;
    }

}

//Complexity:
// time - O(k * d)      k - number of checks nums, d - number of digits
// space - O(1)


//You are given two integers n and t. Return the smallest number greater than or equal to n such that the product of
// its digits is divisible by t.

//Example 1:
//Input: n = 10, t = 2
//Output: 10
//Explanation:
//The digit product of 10 is 0, which is divisible by 2, making it the smallest number greater than or equal to 10
// that satisfies the condition.

//Example 2:
//Input: n = 15, t = 3
//Output: 16
//Explanation:
//The digit product of 16 is 6, which is divisible by 3, making it the smallest number greater than or equal to 15
// that satisfies the condition.

//Constraints:
//1 <= n <= 100
//1 <= t <= 10
