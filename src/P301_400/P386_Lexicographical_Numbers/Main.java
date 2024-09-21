package P301_400.P386_Lexicographical_Numbers;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.lexicalOrder(13)); // Output: [1,10,11,12,13,2,3,4,5,6,7,8,9]
        System.out.println(solution.lexicalOrder(2));  // Output: [1,2]
    }

    public List<Integer> lexicalOrder(int n) {
        List<Integer> result = new ArrayList<>();
        int current = 1;

        // Loop n times to cover all numbers from 1 to n
        for (int i = 0; i < n; i++) {
            result.add(current);
            // Try to go deeper (append a '0' to the current number)
            if (current * 10 <= n) {
                current *= 10;
            } else {
                // If we can't go deeper, we increment the current number
                if (current >= n) {
                    current /= 10; // Move one level up
                }
                current++;
                // Remove trailing 9s to ensure lexicographical order
                while (current % 10 == 0) {
                    current /= 10;
                }
            }
        }

        return result;
    }
}

//Explanation:
//Start from 1: We begin by adding 1 to the result list and then attempt to go to the next number by
// multiplying it by 10, making 10.
//When a number exceeds n: If multiplying by 10 exceeds n, we increment the current number. If this causes
// it to exceed n, we go up the tree (i.e., divide by 10) and continue.
//Avoid trailing nines: To ensure lexicographical order, after incrementing a number, we skip over numbers that end in 0.
//Time and Space Complexity:
//Time complexity: O(n) since we visit each number exactly once.
//Space complexity: O(1) extra space (excluding the space used for the result list).


//Given an integer n, return all the numbers in the range [1, n] sorted in lexicographical order.
//You must write an algorithm that runs in O(n) time and uses O(1) extra space.
//
//Example 1:
//Input: n = 13
//Output: [1,10,11,12,13,2,3,4,5,6,7,8,9]

//Example 2:
//Input: n = 2
//Output: [1,2]
//
//Constraints:
//1 <= n <= 5 * 104
