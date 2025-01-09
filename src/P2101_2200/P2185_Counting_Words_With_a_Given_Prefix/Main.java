package P2101_2200.P2185_Counting_Words_With_a_Given_Prefix;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String[] words1 = {"pay","attention","practice","attend"};
        String prefix1 = "at";
        System.out.println(solution.prefixCount(words1, prefix1)); // 2

        String[] words2 = {"leetcode","win","loops","success"};
        String prefix2 = "code";
        System.out.println(solution.prefixCount(words2, prefix2)); // 0
    }

    public int prefixCount(String[] words, String prefix) {
        int count = 0;

        for (int i = 0; i < words.length; i++) {
            if (words[i].startsWith(prefix)) {
                count++;
            }
        }

        return count;
    }
}

//Complexity:
//Time Complexity:
///O(n)
//Space Complexity:
//O(1)


//You are given an array of strings words and a string pref.
//Return the number of strings in words that contain pref as a prefix.
//A prefix of a string s is any leading contiguous substring of s.
//
//Example 1:
//Input: words = ["pay","attention","practice","attend"], pref = "at"
//Output: 2
//Explanation: The 2 strings that contain "at" as a prefix are: "attention" and "attend".

//Example 2:
//Input: words = ["leetcode","win","loops","success"], pref = "code"
//Output: 0
//Explanation: There are no strings that contain "code" as a prefix.
//
//Constraints:
//1 <= words.length <= 100
//1 <= words[i].length, pref.length <= 100
//words[i] and pref consist of lowercase English letters.
