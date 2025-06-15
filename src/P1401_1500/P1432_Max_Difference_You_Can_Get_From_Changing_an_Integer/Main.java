package P1401_1500.P1432_Max_Difference_You_Can_Get_From_Changing_an_Integer;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.maxDiff(555)); // Output: 888
        System.out.println(solution.maxDiff(9));   // Output: 8
    }

    public int maxDiff(int num) {
        String numStr = String.valueOf(num);

        // maximize 'a' by replacing the first non-9 digit with 9
        String maxStr = getMax(numStr);

        // minimize 'b' by replacing either the first digit or another digit with 0 or 1
        String minStr = getMin(numStr);

        int a = Integer.parseInt(maxStr);
        int b = Integer.parseInt(minStr);

        return a - b;
    }

    private String getMax(String numStr) {
        char[] chars = numStr.toCharArray();
        char target = ' ';

        // find first digit that is not 9
        for (char c : chars) {
            if (c != '9') {
                target = c;
                break;
            }
        }

        if (target == ' ') return numStr; // all 9's already

        StringBuilder sb = new StringBuilder();

        for (char c : chars) {
            if (c == target) {
                sb.append('9');
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    private String getMin(String numStr) {
        char[] chars = numStr.toCharArray();
        char target = ' ';
        char replace = ' ';

        // if the first digit is not '1', change it to '1'
        if (chars[0] != '1') {
            target = chars[0];
            replace = '1';
        } else {
            // find the first digit not '0' or '1' to replace with '0'
            for (int i = 1; i < chars.length; i++) {
                if (chars[i] != '0' && chars[i] != '1') {
                    target = chars[i];
                    replace = '0';
                    break;
                }
            }
        }

        if (target == ' ') return numStr; // no change needed

        StringBuilder sb = new StringBuilder();

        for (char c : chars) {
            if (c == target) {
                sb.append(replace);
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

}

//Complexity:
// time and space - O(n)


//You are given an integer num. You will apply the following steps to num two separate times:
//Pick a digit x (0 <= x <= 9).
//Pick another digit y (0 <= y <= 9). Note y can be equal to x.
//Replace all the occurrences of x in the decimal representation of num by y.
//Let a and b be the two results from applying the operation to num independently.
//Return the max difference between a and b.
//Note that neither a nor b may have any leading zeros, and must not be 0.

//Example 1:
//Input: num = 555
//Output: 888
//Explanation: The first time pick x = 5 and y = 9 and store the new integer in a.
//The second time pick x = 5 and y = 1 and store the new integer in b.
//We have now a = 999 and b = 111 and max difference = 888

//Example 2:
//Input: num = 9
//Output: 8
//Explanation: The first time pick x = 9 and y = 9 and store the new integer in a.
//The second time pick x = 9 and y = 1 and store the new integer in b.
//We have now a = 9 and b = 1 and max difference = 8

//Constraints:
//1 <= num <= 108
