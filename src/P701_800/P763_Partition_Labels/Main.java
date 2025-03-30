package P701_800.P763_Partition_Labels;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.partitionLabels("ababcbacadefegdehijhklij")); // Output: [9, 7, 8]
        System.out.println(solution.partitionLabels("eccbbbbdec")); // Output: [10]

    }

    public List<Integer> partitionLabels(String s) {
        List<Integer> result = new ArrayList<>();
        int[] lastIndex = new int[26]; //stores the last occurrence of each character

        // step 1: find the last occurrence of each character
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }

        // step 2: partitioning
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            end = Math.max(end, lastIndex[s.charAt(i) - 'a']); // update the furthest last index
            if (i == end) { // if the current index is the furthest end of the partition
                result.add(end - start + 1);
                start = i + 1; // start a new partition
            }
        }

        return result;
    }

}

//Complexity:
//Time: O(n)
//Space: O(1)


//You are given a string s. We want to partition the string into as many parts as possible so that each
// letter appears in at most one part. For example, the string "ababcc" can be partitioned into ["abab", "cc"], but
// partitions such as ["aba", "bcc"] or ["ab", "ab", "cc"] are invalid.
//Note that the partition is done so that after concatenating all the parts in order, the resultant string should be s.
//Return a list of integers representing the size of these parts.

//Example 1:
//Input: s = "ababcbacadefegdehijhklij"
//Output: [9,7,8]
//Explanation:
//The partition is "ababcbaca", "defegde", "hijhklij".
//This is a partition so that each letter appears in at most one part.
//A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits s into less parts.

//Example 2:
//Input: s = "eccbbbbdec"
//Output: [10]

//Constraints:
//1 <= s.length <= 500
//s consists of lowercase English letters.
