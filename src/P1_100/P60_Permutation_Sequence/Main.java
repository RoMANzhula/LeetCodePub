package P1_100.P60_Permutation_Sequence;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.getPermutation(3, 3)); // Output: "213"
        System.out.println(solution.getPermutation(4, 9)); // Output: "2314"
        System.out.println(solution.getPermutation(3, 1)); // Output: "123"
    }

    public String getPermutation(int n, int k) {
        List<Integer> numbers = new ArrayList<>();
        int[] fact = new int[n + 1];
        fact[0] = 1;

        // precompute factorials
        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1] * i;
            numbers.add(i);
        }

        //convert k to 0-based index
        k--;
        StringBuilder sb = new StringBuilder();

        for (int i = n; i >= 1; i--) {
            int index = k / fact[i - 1];
            sb.append(numbers.get(index));
            numbers.remove(index);
            k %= fact[i - 1];
        }

        return sb.toString();
    }

}

//Complexity:
// time - O(n^2)
// space - O(n)


//The set [1, 2, 3, ..., n] contains a total of n! unique permutations.
//By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
//"123"
//"132"
//"213"
//"231"
//"312"
//"321"
//Given n and k, return the kth permutation sequence.

//Example 1:
//Input: n = 3, k = 3
//Output: "213"

//Example 2:
//Input: n = 4, k = 9
//Output: "2314"

//Example 3:
//Input: n = 3, k = 1
//Output: "123"

//Constraints:
//1 <= n <= 9
//1 <= k <= n!
