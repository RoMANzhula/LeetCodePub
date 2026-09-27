package P101_200.P187_Repeated_DNA_Sequences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String s1 = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        System.out.println(solution.findRepeatedDnaSequences(s1));

        String s2 = "AAAAAAAAAAAAA";
        System.out.println(solution.findRepeatedDnaSequences(s2));
    }

    public List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<>();

        if (s.length() < 10) {
            return result;
        }

        Map<String, Integer> frequency = new HashMap<>();

        for (int i = 0; i <= s.length() - 10; i++) {
            String sequence = s.substring(i, i + 10);

            int count = frequency.getOrDefault(sequence, 0) + 1;
            frequency.put(sequence, count);

            if (count == 2) {
                result.add(sequence);
            }
        }

        return result;
    }

}

//Complexity:
// time and space - O(n) (s.length())


//The DNA sequence is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T'.
//For example, "ACGAATTCCG" is a DNA sequence.
//When studying DNA, it is useful to identify repeated sequences within the DNA.
//Given a string s that represents a DNA sequence, return all the 10-letter-long sequences (substrings) that occur
// more than once in a DNA molecule. You may return the answer in any order.

//Example 1:
//Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
//Output: ["AAAAACCCCC","CCCCCAAAAA"]

//Example 2:
//Input: s = "AAAAAAAAAAAAA"
//Output: ["AAAAAAAAAA"]

//Constraints:
//1 <= s.length <= 105
//s[i] is either 'A', 'C', 'G', or 'T'.
