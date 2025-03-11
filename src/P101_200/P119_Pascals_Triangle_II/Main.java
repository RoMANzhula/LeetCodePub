package P101_200.P119_Pascals_Triangle_II;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(getRow(0));
        System.out.println(getRow(4));
        System.out.println(getRow(7));
    }
    public static List<Integer> getRow(int rowIndex) {
        List<Integer> resultRow = new ArrayList<>();

        for (int i = 0; i <= rowIndex; i++) {
            resultRow.add(0, 1); //add 1 to the beginning of the list

            //add the values in the row using the previous row
            for (int j = 1; j < resultRow.size() - 1; j++) {
                resultRow.set(j, resultRow.get(j) + resultRow.get(j + 1));
            }
        }

        return resultRow;
    }

}

//Given an integer rowIndex, return the rowIndexth (0-indexed) row of the Pascal's triangle.
//In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
//
//      1
//     1 1
//    1 2 1
//   1 3 3 1
//  1 4 6 4 1
//
//Example 1:
//Input: rowIndex = 3
//Output: [1,3,3,1]

//Example 2:
//Input: rowIndex = 0
//Output: [1]

//Example 3:
//Input: rowIndex = 1
//Output: [1,1]

//Constraints:
//0 <= rowIndex <= 33
