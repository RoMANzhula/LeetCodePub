package P2501_2600.P2559_Count_Vowel_Strings_in_Ranges;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String[] words1 = {"aba", "bcb", "ece", "aa", "e"};
        int[][] queries1 = {{0, 2}, {1, 4}, {1, 1}};
        System.out.println(Arrays.toString(solution.vowelStrings(words1, queries1))); // Output: [2, 3, 0]

        String[] words2 = {"a", "e", "i"};
        int[][] queries2 = {{0, 2}, {0, 1}, {2, 2}};
        System.out.println(Arrays.toString(solution.vowelStrings(words2, queries2))); // Output: [3, 2, 1]
    }

    public int[] vowelStrings(String[] words, int[][] queries) {
        int lengthWs = words.length;
        int[] prefixSum = new int[lengthWs + 1];

        //preprocessing to compute prefix sums for words starting and ending with Vowels
        for (int i = 0; i < lengthWs; i++) {
            String word = words[i];

            if (isVowel(word.charAt(0)) && isVowel(word.charAt(word.length() - 1))) {
                prefixSum[i + 1] = prefixSum[i] + 1;
            } else {
                prefixSum[i + 1] = prefixSum[i];
            }
        }

        // answering the queries
        int lengthQs = queries.length;
        int[] result = new int[lengthQs];

        for (int i = 0; i < lengthQs; i++) {
            int li = queries[i][0];
            int ri = queries[i][1];

            result[i] = prefixSum[ri + 1] - prefixSum[li];
        }

        return result;
    }

    private boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

}

//Explanation:
//Preprocessing:
//-We create a prefix sum array prefixSum where each entry prefixSum[i] holds the cumulative count of
// words starting and ending with vowels up to index i-1 in the words array.
//-For each word, check the first and last characters using the isVowel helper method. If both are
// vowels, increment the count.
//Answering Queries:
//-For each query [li, ri], compute the number of valid words in the range as:
//  result[i] = prefixSum[ri+1] − prefixSum[li]
//-This operation is O(1) for each query.
//Complexity:
//Preprocessing: O(n), where n is the number of words.
//Query answering: O(q), where q is the number of queries.
//Overall: O(n+q), efficient for large inputs.


//You are given a 0-indexed array of strings words and a 2D array of integers queries.
//Each query queries[i] = [li, ri] asks us to find the number of strings present in the range li to
// ri (both inclusive) of words that start and end with a vowel.
//Return an array ans of size queries.length, where ans[i] is the answer to the ith query.
//Note that the vowel letters are 'a', 'e', 'i', 'o', and 'u'.
//
//Example 1:
//Input: words = ["aba","bcb","ece","aa","e"], queries = [[0,2],[1,4],[1,1]]
//Output: [2,3,0]
//Explanation: The strings starting and ending with a vowel are "aba", "ece", "aa" and "e".
//The answer to the query [0,2] is 2 (strings "aba" and "ece").
//to query [1,4] is 3 (strings "ece", "aa", "e").
//to query [1,1] is 0.
//We return [2,3,0].

//Example 2:
//Input: words = ["a","e","i"], queries = [[0,2],[0,1],[2,2]]
//Output: [3,2,1]
//Explanation: Every string satisfies the conditions, so we return [3,2,1].
//
//Constraints:
//1 <= words.length <= 105
//1 <= words[i].length <= 40
//words[i] consists only of lowercase English letters.
//sum(words[i].length) <= 3 * 105
//1 <= queries.length <= 105
//0 <= li <= ri < words.length
