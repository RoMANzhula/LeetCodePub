package P1901_2000.P1963_Minimum_Number_of_Swaps_to_Make_the_string_Balanced;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.minSwaps("][][")); // Output: 1
        System.out.println(solution.minSwaps("]]][[[")); // Output: 2
        System.out.println(solution.minSwaps("[]")); // Output: 0
    }

    public int minSwaps(String s) {
        int balance = 0;   // Keeps track of balance between [ and ]
        int unmatchedClose = 0;  // Tracks unmatched closing brackets

        for (char c : s.toCharArray()) {
            if (c == '[') {
                balance++;
            } else {
                balance--;
            }

            // If balance goes negative, we have an unmatched closing bracket
            if (balance < 0) {
                unmatchedClose++;
                balance = 0;  // Reset balance since we know this unmatched ] needs fixing
            }
        }

        // Each swap fixes two unmatched closing brackets, so divide unmatchedClose by 2
        return (unmatchedClose + 1) / 2;
    }
}

//Explanation:
//The balance variable tracks the difference between the number of opening brackets [ and closing brackets ].
//When balance becomes negative (i.e., there are more ] than [ at a point), it means we have an
// unmatched closing bracket.
//The unmatchedClose counter keeps track of how many unmatched closing brackets exist.
//Each swap fixes two unmatched closing brackets, which is why we return (unmatchedClose + 1) / 2 to account for
// all necessary swaps.
//Time Complexity:
//O(n): We only traverse the string once.
//Space Complexity:
//O(1): We only use a few extra variables.


//You are given a 0-indexed string s of even length n. The string consists of exactly n / 2 opening
// brackets '[' and n / 2 closing brackets ']'.
//A string is called balanced if and only if:
//It is the empty string, or
//It can be written as AB, where both A and B are balanced strings, or
//It can be written as [C], where C is a balanced string.
//You may swap the brackets at any two indices any number of times.
//Return the minimum number of swaps to make s balanced.
//
//Example 1:
//Input: s = "][]["
//Output: 1
//Explanation: You can make the string balanced by swapping index 0 with index 3.
//The resulting string is "[[]]".

//Example 2:
//Input: s = "]]][[["
//Output: 2
//Explanation: You can do the following to make the string balanced:
//- Swap index 0 with index 4. s = "[]][][".
//- Swap index 1 with index 5. s = "[[][]]".
//The resulting string is "[[][]]".

//Example 3:
//Input: s = "[]"
//Output: 0
//Explanation: The string is already balanced.
//
//Constraints:
//n == s.length
//2 <= n <= 106
//n is even.
//s[i] is either '[' or ']'.
//The number of opening brackets '[' equals n / 2, and the number of closing brackets ']' equals n / 2.