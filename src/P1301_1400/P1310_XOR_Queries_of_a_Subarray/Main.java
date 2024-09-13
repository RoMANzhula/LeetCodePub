package P1301_1400.P1310_XOR_Queries_of_a_Subarray;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] arr1 = {1, 3, 4, 8};
        int[][] queries1 = {{0, 1}, {1, 2}, {0, 3}, {3, 3}};
        System.out.println(Arrays.toString(solution.xorQueries(arr1, queries1)));  // Output: [2, 7, 14, 8]

        int[] arr2 = {4, 8, 2, 10};
        int[][] queries2 = {{2, 3}, {1, 3}, {0, 0}, {0, 3}};
        System.out.println(Arrays.toString(solution.xorQueries(arr2, queries2)));  // Output: [8, 0, 4, 4]
    }

    public int[] xorQueries(int[] arr, int[][] queries) {
        int n = arr.length;
        int[] prefixXor = new int[n];
        prefixXor[0] = arr[0];

        // Step 1: Calculate prefix XOR array
        for (int i = 1; i < n; i++) {
            prefixXor[i] = prefixXor[i - 1] ^ arr[i];
        }

        // Step 2: Answer the queries
        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int left = queries[i][0];
            int right = queries[i][1];

            if (left == 0) {
                result[i] = prefixXor[right];
            } else {
                result[i] = prefixXor[right] ^ prefixXor[left - 1];
            }
        }

        return result;
    }
}

//Explanation:
//Prefix XOR Array: The prefixXor array stores the cumulative XOR from the start of the array. For instance, in
// the example arr = [1, 3, 4, 8], the prefixXor would be:
//prefixXor[0] = 1
//prefixXor[1] = 1 ^ 3 = 2
//prefixXor[2] = 1 ^ 3 ^ 4 = 6
//prefixXor[3] = 1 ^ 3 ^ 4 ^ 8 = 14
//Query Results:
//For query [0, 1], the result is prefixXor[1] = 2.
//For query [1, 2], the result is prefixXor[2] ^ prefixXor[0] = 6 ^ 1 = 7.
//For query [0, 3], the result is prefixXor[3] = 14.
//For query [3, 3], the result is prefixXor[3] ^ prefixXor[2] = 14 ^ 6 = 8.
//Time Complexity:
//Precomputing prefixXor takes O(n), where n is the length of arr.
//Each query can be answered in O(1).
//Therefore, the total time complexity is O(n+q), where q is the number of queries.


//You are given an array arr of positive integers. You are also given the array queries
// where queries[i] = [lefti, righti].
//For each query i compute the XOR of elements from lefti to righti (that is, arr[lefti]
// XOR arr[lefti + 1] XOR ... XOR arr[righti] ).
//Return an array answer where answer[i] is the answer to the ith query.
//
//Example 1:
//Input: arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]]
//Output: [2,7,14,8]
//Explanation:
//The binary representation of the elements in the array are:
//1 = 0001
//3 = 0011
//4 = 0100
//8 = 1000
//The XOR values for queries are:
//[0,1] = 1 xor 3 = 2
//[1,2] = 3 xor 4 = 7
//[0,3] = 1 xor 3 xor 4 xor 8 = 14
//[3,3] = 8

//Example 2:
//Input: arr = [4,8,2,10], queries = [[2,3],[1,3],[0,0],[0,3]]
//Output: [8,0,4,4]
//
//Constraints:
//1 <= arr.length, queries.length <= 3 * 104
//1 <= arr[i] <= 109
//queries[i].length == 2
//0 <= lefti <= righti < arr.length