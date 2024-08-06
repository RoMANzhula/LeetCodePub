package P3001_3100.P3016_Minimum_Number_of_Pushes_to_Type_Word_ll;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.minimumPushes("abcde")); // Output: 5
        System.out.println(solution.minimumPushes("xyzxyzxyzxyz")); // Output: 12
        System.out.println(solution.minimumPushes("aabbccddeeffgghhiiiiii")); // Output: 24
    }

    public int minimumPushes(String word) {
        int ans = 0;
        int[] count = new int[26];

        for (char c : word.toCharArray()) {
            ++count[c - 'a'];
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int freq : count) {
            if (freq > 0) {
                pq.add(freq);
            }
        }

        int i = 0;
        while (!pq.isEmpty()) {
            ans += pq.poll() * (i / 8 + 1);
            i++;
        }

        return ans;
    }
}

//Explanation:
//Frequency Count: Count the frequency of each letter in the word using an array.
//Priority Queue: Add non-zero frequencies to a priority queue (max-heap) to prioritize the most frequent letters.
//Calculate Pushes:
//Use the priority queue to get the largest frequencies first.
//Calculate the total number of pushes by assigning the highest frequencies to the keys with the lowest push counts.
//The formula (i / 8 + 1) ensures that the first 8 most frequent letters get mapped to the keys with the fewest
// pushes (1 push), the next 8 letters to keys with more pushes (2 pushes), and so on.
//This should now produce the correct result for the given examples.


//You are given a string word containing lowercase English letters.
//Telephone keypads have keys mapped with distinct collections of lowercase English letters, which can be used to
// form words by pushing them. For example, the key 2 is mapped with ["a","b","c"], we need to push the key one
// time to type "a", two times to type "b", and three times to type "c" .
//It is allowed to remap the keys numbered 2 to 9 to distinct collections of letters. The keys can be remapped to
// any amount of letters, but each letter must be mapped to exactly one key. You need to find the minimum number of
// times the keys will be pushed to type the string word.
//Return the minimum number of pushes needed to type word after remapping the keys.
//An example mapping of letters to keys on a telephone keypad is given below. Note that 1, *, #, and 0 do not
// map to any letters.
//
//Example 1:
//Input: word = "abcde"
//Output: 5
//Explanation: The remapped keypad given in the image provides the minimum cost.
//"a" -> one push on key 2
//"b" -> one push on key 3
//"c" -> one push on key 4
//"d" -> one push on key 5
//"e" -> one push on key 6
//Total cost is 1 + 1 + 1 + 1 + 1 = 5.
//It can be shown that no other mapping can provide a lower cost.

//Example 2:
//Input: word = "xyzxyzxyzxyz"
//Output: 12
//Explanation: The remapped keypad given in the image provides the minimum cost.
//"x" -> one push on key 2
//"y" -> one push on key 3
//"z" -> one push on key 4
//Total cost is 1 * 4 + 1 * 4 + 1 * 4 = 12
//It can be shown that no other mapping can provide a lower cost.
//Note that the key 9 is not mapped to any letter: it is not necessary to map letters to every key, but
// to map all the letters.

//Example 3:
//Input: word = "aabbccddeeffgghhiiiiii"
//Output: 24
//Explanation: The remapped keypad given in the image provides the minimum cost.
//"a" -> one push on key 2
//"b" -> one push on key 3
//"c" -> one push on key 4
//"d" -> one push on key 5
//"e" -> one push on key 6
//"f" -> one push on key 7
//"g" -> one push on key 8
//"h" -> two pushes on key 9
//"i" -> one push on key 9
//Total cost is 1 * 2 + 1 * 2 + 1 * 2 + 1 * 2 + 1 * 2 + 1 * 2 + 1 * 2 + 2 * 2 + 6 * 1 = 24.
//It can be shown that no other mapping can provide a lower cost.
//
//Constraints:
//1 <= word.length <= 105
//word consists of lowercase English letters.
