package P701_800.P779_Kth_Symbol_in_Grammar;

public class Main {

    public static void main(String[] args) {
        int n1 = 1, k1 = 1;
        int n2 = 2, k2 = 1;
        int n3 = 4, k3 = 3;

        System.out.println(kthGrammar(n1, k1)); // Output: 0
        System.out.println(kthGrammar(n2, k2)); // Output: 0
        System.out.println(kthGrammar(n3, k3)); // Output: 1

        System.out.println("---------------------");

        System.out.println(kthGrammar2(n1, k1)); // Output: 0
        System.out.println(kthGrammar2(n2, k2)); // Output: 0
        System.out.println(kthGrammar2(n3, k3)); // Output: 1
    }

    public static int kthGrammar(int n, int k) {
        if (n == 1) {
            return 0; //base case
        }

        //calculate the length of the previous row
        int prevLength = (int) Math.pow(2, n - 1);

        if (k <= prevLength / 2) {
            return kthGrammar(n - 1, k);
        } else {
            //k-th element in the current row depends on its corresponding element in the previous row
            return 1 - kthGrammar(n - 1, k - prevLength / 2);
        }
    }

    public static int kthGrammar2(int n, int k) {


        int result = 0;

        if (n > 0 && n < 31) {


            String str = Integer.toBinaryString(n);
            //додати "0" на початок, якщо він відсутній
            if (str.charAt(0) != '0') {
                str = "0" + str;
            }
            if (k > str.length()) {
                str = str + "0";
            }

            System.out.println(str);

            char[] fromBinary = str.toCharArray();

            for (int i = 0; i < fromBinary.length; i++) {
                result = Integer.parseInt(String.valueOf(fromBinary[k - 1]));
            }
        }

        return result;
    }

}

//We build a table of n rows (1-indexed). We start by writing 0 in the 1st row. Now in every subsequent row, we look
// at the previous row and replace each occurrence of 0 with 01, and each occurrence of 1 with 10.
//For example, for n = 3, the 1st row is 0, the 2nd row is 01, and the 3rd row is 0110.
//Given two integer n and k, return the kth (1-indexed) symbol in the nth row of a table of n rows.

//Example 1:
//Input: n = 1, k = 1
//Output: 0
//Explanation: row 1: 0

//Example 2:
//Input: n = 2, k = 1
//Output: 0
//Explanation:
//row 1: 0
//row 2: 01

//Example 3:
//Input: n = 2, k = 2
//Output: 1
//Explanation:
//row 1: 0
//row 2: 01

//Constraints:
//1 <= n <= 30
//1 <= k <= 2n - 1
