package P1901_2000.P1930_Unique_Length_3_Palindromic_Subsequences;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println(countPalindromicSubsequence("aabca"));   // Output: 3
        System.out.println(countPalindromicSubsequence("adc"));     // Output: 0
        System.out.println(countPalindromicSubsequence("bbcbaba")); // Output: 4
    }

    public static int countPalindromicSubsequence(String s) {
        int ans = 0;
        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, s.length());

        for (int i = 0; i < s.length(); ++i) {
            final int index = s.charAt(i) - 'a';
            first[index] = Math.min(first[index], i);
            last[index] = i;
        }

        for (int i = 0; i < 26; ++i) {
            if (first[i] < last[i]) {
                ans += countUniqueChars(s, first[i] + 1, last[i]);
            }
        }

        return ans;
    }

    private static int countUniqueChars(String s, int start, int end) {
        Set<Character> uniqueChars = new HashSet<>();
        for (int i = start; i < end; i++) {
            uniqueChars.add(s.charAt(i));
        }
        return uniqueChars.size();
    }

}

//Ми шукаємо кількість унікальних паліндромів довжиною три, які є підпослідовністю в рядку s. Паліндром - це рядок,
// який читається однаково як зліва направо, так і справа наліво.
//У розв'язку використовуються два масиви, first та last, які тримають індекси першого і останнього входження кожної
// літери в рядок s. Це дозволяє швидко знаходити межі діапазонів для кожної літери.
//Далі використовується цикл для обходження кожної літери алфавіту. Якщо літера входить в рядок і має як мінімум одне
// входження поза межами, то обчислюється кількість унікальних символів між цими межами. Це використовується для
// підрахунку паліндромів, оскільки для кожної літери взята кількість унікальних символів відбувається окремо.
//Функція countUniqueChars використовується для підрахунку унікальних символів у вказаному діапазоні в рядку.
//Наприклад, для вхідного рядка "aabca", розраховується кількість унікальних символів між межами для літери "a",
// літери "b" і т. д. Потім сума цих значень складає відповідь.

//Given a string s, return the number of unique palindromes of length three that are a subsequence of s.
//Note that even if there are multiple ways to obtain the same subsequence, it is still only counted once.
//A palindrome is a string that reads the same forwards and backwards.
//A subsequence of a string is a new string generated from the original string with some characters (can be none)
// deleted without changing the relative order of the remaining characters.
//For example, "ace" is a subsequence of "abcde".
//
//Example 1:
//Input: s = "aabca"
//Output: 3
//Explanation: The 3 palindromic subsequences of length 3 are:
//- "aba" (subsequence of "aabca")
//- "aaa" (subsequence of "aabca")
//- "aca" (subsequence of "aabca")

//Example 2:
//Input: s = "adc"
//Output: 0
//Explanation: There are no palindromic subsequences of length 3 in "adc".

//Example 3:
//Input: s = "bbcbaba"
//Output: 4
//Explanation: The 4 palindromic subsequences of length 3 are:
//- "bbb" (subsequence of "bbcbaba")
//- "bcb" (subsequence of "bbcbaba")
//- "bab" (subsequence of "bbcbaba")
//- "aba" (subsequence of "bbcbaba")
//
//Constraints:
//3 <= s.length <= 105
//s consists of only lowercase English letters.
