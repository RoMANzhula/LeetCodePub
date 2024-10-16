package P1401_1500.P1405_Longest_Happy_String;

import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.longestDiverseString(1, 1, 7)); // Output: "ccaccbcc" or similar

        System.out.println(solution.longestDiverseString(7, 1, 0)); // Output: "aabaa"
    }

    public String longestDiverseString(int a, int b, int c) {
        // Max heap to keep track of the characters with the most remaining occurrences
        PriorityQueue<CharCount> maxHeap = new PriorityQueue<>((x, y) -> y.count - x.count);

        // Add the characters 'a', 'b', 'c' to the heap if they have non-zero counts
        if (a > 0) maxHeap.offer(new CharCount('a', a));
        if (b > 0) maxHeap.offer(new CharCount('b', b));
        if (c > 0) maxHeap.offer(new CharCount('c', c));

        StringBuilder result = new StringBuilder();

        // Keep building the happy string
        while (!maxHeap.isEmpty()) {
            // Take the character with the most occurrences left
            CharCount first = maxHeap.poll();

            // If the last two characters in the result are the same as 'first.ch', we need to pick the next one
            if (result.length() >= 2 && result.charAt(result.length() - 1) == first.ch && result.charAt(result.length() - 2) == first.ch) {
                if (maxHeap.isEmpty()) {
                    break; // No valid character to add
                }

                // Take the next most frequent character
                CharCount second = maxHeap.poll();
                result.append(second.ch);
                second.count--;

                // Put the second character back if it still has occurrences left
                if (second.count > 0) {
                    maxHeap.offer(second);
                }

                // Put the first character back into the heap
                maxHeap.offer(first);
            } else {
                // If adding the first character is valid, add it to the result
                result.append(first.ch);
                first.count--;

                // If the first character still has occurrences left, put it back in the heap
                if (first.count > 0) {
                    maxHeap.offer(first);
                }
            }
        }

        return result.toString();
    }
}

class CharCount {
    char ch;
    int count;

    CharCount(char ch, int count) {
        this.ch = ch;
        this.count = count;
    }
}


//A string s is called happy if it satisfies the following conditions:
//s only contains the letters 'a', 'b', and 'c'.
//s does not contain any of "aaa", "bbb", or "ccc" as a substring.
//s contains at most a occurrences of the letter 'a'.
//s contains at most b occurrences of the letter 'b'.
//s contains at most c occurrences of the letter 'c'.
//Given three integers a, b, and c, return the longest possible happy string. If there are multiple longest
// happy strings, return any of them. If there is no such string, return the empty string "".
//A substring is a contiguous sequence of characters within a string.
//
//Example 1:
//Input: a = 1, b = 1, c = 7
//Output: "ccaccbcc"
//Explanation: "ccbccacc" would also be a correct answer.

//Example 2:
//Input: a = 7, b = 1, c = 0
//Output: "aabaa"
//Explanation: It is the only correct answer in this case.
//
//Constraints:
//0 <= a, b, c <= 100
//a + b + c > 0
