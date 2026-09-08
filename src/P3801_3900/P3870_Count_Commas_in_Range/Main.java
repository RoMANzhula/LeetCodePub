package P3801_3900.P3870_Count_Commas_in_Range;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int n = 1002;
        int result = solution.countCommas(n);
        System.out.println(result); // 3
    }

    public int countCommas(int n) {
        return Math.max(0, n - 999);
    }

}

//Complexity:
// time and space - O(1)


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
//1 <= n <= 105
