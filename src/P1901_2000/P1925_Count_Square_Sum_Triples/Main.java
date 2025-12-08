package P1901_2000.P1925_Count_Square_Sum_Triples;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.countSquareTriples(5));   // Output: 2
        System.out.println(solution.countSquareTriples(10));  // Output: 4
    }

    public static int countSquareTriples(int n) {
        int count = 0;

        for (int a = 1; a <= n; a++) {
            for (int b = 1; b <= n; b++) {
                int sum = a * a + b * b;

                for (int c = 1; c <= n; c++) {
                    if (c * c == sum) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

}

//Complexity:
// time - O(n^3)
// space - O(1)


//A square triple (a,b,c) is a triple where a, b, and c are integers and a2 + b2 = c2.
//Given an integer n, return the number of square triples such that 1 <= a, b, c <= n.

//Example 1:
//Input: n = 5
//Output: 2
//Explanation: The square triples are (3,4,5) and (4,3,5).

//Example 2:
//Input: n = 10
//Output: 4
//Explanation: The square triples are (3,4,5), (4,3,5), (6,8,10), and (8,6,10).

//Constraints:
//1 <= n <= 250
