package P3801_3900.P3871_Count_Commas_in_Range_II;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        long n = 1002;
        long result = solution.countCommas(n);
        System.out.println(result); // 3
    }

    public long countCommas(long n) {
        long result = 0;
        long power = 1000;

        while (power <= n) {
            result += n - power + 1;
            power *= 1000;
        }

        return result;
    }

}

//Complexity:
// time - O(log n)
// space - O(1)


//You are given an integer n.
//Return the total number of commas used when writing all integers from [1, n] (inclusive) in standard number
// formatting.
//In standard formatting:
//A comma is inserted after every three digits from the right.
//Numbers with fewer than 4 digits contain no commas.

//Example 1:
//Input: n = 1002
//Output: 3
//Explanation:
//The numbers "1,000", "1,001", and "1,002" each contain one comma, giving a total of 3.

//Example 2:
//Input: n = 998
//Output: 0
//Explanation:
//All numbers from 1 to 998 have fewer than four digits. Therefore, no commas are used.

//Constraints:
//1 <= n <= 1015
