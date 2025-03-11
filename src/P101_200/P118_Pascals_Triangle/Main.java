package P101_200.P118_Pascals_Triangle;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();
        int numRows = 5;
        List<List<Integer>> result = solution.generate(numRows);

        for (List<Integer> row : result) {
            System.out.println(row);
        }
    }


    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();

        if (numRows <= 0) {
            return triangle;
        }

        //initialize Pascal's triangle with the first row
        List<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        triangle.add(firstRow);

        //generate the remaining rows
        for (int i = 1; i < numRows; i++) {
            List<Integer> prevRow = triangle.get(i - 1);
            List<Integer> newRow = new ArrayList<>();

            //the first element of each row is always 1
            newRow.add(1);

            //calculate the middle elements of the row
            for (int j = 1; j < i; j++) {
                int newElement = prevRow.get(j - 1) + prevRow.get(j);
                newRow.add(newElement);
            }

            //the last element of each row is always 1
            newRow.add(1);
            triangle.add(newRow);
        }

        return triangle;
    }
}

//Given an integer numRows, return the first numRows of Pascal's triangle.
//In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:

//Example 1:
//Input: numRows = 5
//Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]

//Example 2:
//Input: numRows = 1
//Output: [[1]]

//Constraints:
//1 <= numRows <= 30
