package P3801_3900.P3863_Minimum_Operations_to_Sort_a_String;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.minOperations("dog")); // 1
        System.out.println(solution.minOperations("card")); // 2
        System.out.println(solution.minOperations("gf")); // -1
    }

    public int minOperations(String s) {
        int n = s.length();
        int minC = 0;
        int maxC = 0;

        boolean sorted = true;

        for (int i = 1; i < n; i++) {
            if (s.charAt(i - 1) > s.charAt(i)) {
                sorted = false;
                break;
            }
        }

        if (sorted) {
            return 0;
        }

        if (n == 2) {
            return -1;
        }

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) < s.charAt(minC)) {
                minC = i;
            }

            if (s.charAt(i) >= s.charAt(maxC)) {
                maxC = i;
            }
        }

        if (minC == 0 || maxC == n - 1) {
            return 1;
        }

        return (minC == n - 1 && maxC == 0) ? 3 : 2;
    }

}

//Complexity:
// time - O(n)
// space - O(1)


//You are given a string s consisting of lowercase English letters.
//In one operation, you can select any substring of s that is not the entire string and sort it in non-descending
// alphabetical order.
//Return the minimum number of operations required to make s sorted in non-descending order. If it is not possible,
// return -1.

//Example 1:
//Input: s = "dog"
//Output: 1
//Explanation:
//Sort substring "og" to "go".
//Now, s = "dgo", which is sorted in ascending order. Thus, the answer is 1.

//Example 2:
//Input: s = "card"
//Output: 2
//Explanation:
//Sort substring "car" to "acr", so s = "acrd".
//Sort substring "rd" to "dr", making s = "acdr", which is sorted in ascending order. Thus, the answer is 2.

//Example 3:
//Input: s = "gf"
//Output: -1
//Explanation:
//It is impossible to sort s under the given constraints. Thus, the answer is -1.

//Constraints:
//1 <= s.length <= 105
//s consists of only lowercase English letters.
