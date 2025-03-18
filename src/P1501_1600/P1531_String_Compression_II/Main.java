package P1501_1600.P1531_String_Compression_II;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.getLengthOfOptimalCompression("aaabcccd", 2)); // Output 4
        System.out.println(main.getLengthOfOptimalCompression("aabbaa", 2)); // Output 2
        System.out.println(main.getLengthOfOptimalCompression("aaaaaaaaaaa", 0)); // Output 3
    }

    private static final int MAX = 101;
    private int[][] dp;

    public int getLengthOfOptimalCompression(String s, int k) {
        dp = new int[s.length()][k + 1];
        for (int[] row : dp) {
            Arrays.fill(row, MAX);
        }
        return compression(s, 0, k);
    }

    private int compression(String s, int i, int k) {
        if (k < 0) return MAX;
        if (i == s.length() || s.length() - i <= k) return 0;
        if (dp[i][k] != MAX) return dp[i][k];

        int maxFreq = 0;
        int[] count = new int[128];

        for (int j = i; j < s.length(); ++j) {
            maxFreq = Math.max(maxFreq, ++count[s.charAt(j)]);
            dp[i][k] = Math.min(
                    dp[i][k],
                    getLength(maxFreq) + compression(s, j + 1, k - (j - i + 1 - maxFreq))
            );
        }

        return dp[i][k];
    }

    private int getLength(int maxFreq) {
        if (maxFreq == 1) return 1;
        if (maxFreq < 10) return 2;
        if (maxFreq < 100) return 3;
        return 4;
    }

}

//Задача полягає в тому, щоб знайти мінімальну довжину стисненої версії рядка, при умові, що можна видалити
// принаймні 0, але не більше k символів.
//Алгоритм використовує динамічне програмування для знаходження оптимального рішення.
//Основні ідеї алгоритму:
//Створити динамічний масив dp, де dp[i][j] відповідає довжині оптимальної стисненої версії підстрічки
// s[i..n) за умови, що можна видалити не більше j символів.
//Заповнити масив dp з кінця рядка назад. Для кожного індексу i та кількості видалимо k, обчислити dp[i][k]
// за допомогою максимальної частоти символів у підстрічці та рекурсивних викликів для наступних можливих підстрічок.
//Створити допоміжну функцію compression, яка знаходить максимальну частоту символів у підстрічці та повертає
// довжину стисненої версії для цієї частоти.
//Використовувати масив dp[0][k] для отримання оптимальної довжини для всього рядка.
//Підрахувати довжину стисненої версії для частоти символів за допомогою допоміжної функції getLength.
//Цей алгоритм дозволяє ефективно вирішити задачу, обчислюючи оптимальну довжину стисненої версії рядка за умови,
// що можна видалити певну кількість символів.


//Run-length encoding is a string compression method that works by replacing consecutive identical
// characters (repeated 2 or more times) with the concatenation of the character and the number marking the
// count of the characters (length of the run). For example, to compress the string "aabccc" we replace "aa"
// by "a2" and replace "ccc" by "c3". Thus the compressed string becomes "a2bc3".
//Notice that in this problem, we are not adding '1' after single characters.
//Given a string s and an integer k. You need to delete at most k characters from s such that the run-length
// encoded version of s has minimum length.
//Find the minimum length of the run-length encoded version of s after deleting at most k characters.

//Example 1:
//Input: s = "aaabcccd", k = 2
//Output: 4
//Explanation: Compressing s without deleting anything will give us "a3bc3d" of length 6. Deleting any of the
// characters 'a' or 'c' would at most decrease the length of the compressed string to 5, for instance delete 2
// 'a' then we will have s = "abcccd" which compressed is abc3d. Therefore, the optimal way is to delete 'b' and 'd',
// then the compressed version of s will be "a3c3" of length 4.

//Example 2:
//Input: s = "aabbaa", k = 2
//Output: 2
//Explanation: If we delete both 'b' characters, the resulting compressed string would be "a4" of length 2.

//Example 3:
//Input: s = "aaaaaaaaaaa", k = 0
//Output: 3
//Explanation: Since k is zero, we cannot delete anything. The compressed string is "a11" of length 3.

//Constraints:
//1 <= s.length <= 100
//0 <= k <= s.length
//s contains only lowercase English letters.
