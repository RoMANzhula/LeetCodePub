package P3301_3400.P3304_Find_the_Kth_Character_in_String_Game_I;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int k1 = 5;
        int k2 = 10;

        System.out.println("k = " + k1 + " → " + solution.kthCharacter(k1)); // Output: "b"
        System.out.println("k = " + k2 + " → " + solution.kthCharacter(k2)); // Output: "c"
    }

    public char kthCharacter(int k) {
        StringBuilder word = new StringBuilder("a");

        while (word.length() < k) {
            StringBuilder next = new StringBuilder();

            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                char nextChar = (char) ((ch == 'z') ? 'a' : (ch + 1));
                next.append(nextChar);
            }

            word.append(next);
        }

        return word.charAt(k - 1);
    }

}

//Complexity:
// time - O(k)      1<= k <=500
// space - O(k)


//Alice and Bob are playing a game. Initially, Alice has a string word = "a".
//You are given a positive integer k.
//Now Bob will ask Alice to perform the following operation forever:
//Generate a new string by changing each character in word to its next character in the English alphabet, and
// append it to the original word.
//For example, performing the operation on "c" generates "cd" and performing the operation on "zb" generates "zbac".
//Return the value of the kth character in word, after enough operations have been done for word to have at
// least k characters.
//Note that the character 'z' can be changed to 'a' in the operation.

//Example 1:
//Input: k = 5
//Output: "b"
//Explanation:
//Initially, word = "a". We need to do the operation three times:
//Generated string is "b", word becomes "ab".
//Generated string is "bc", word becomes "abbc".
//Generated string is "bccd", word becomes "abbcbccd".

//Example 2:
//Input: k = 10
//Output: "c"

//Constraints:
//1 <= k <= 500
