package P2707_Extra_Characters_in_a_String;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        String str1 = "leetscode";
        String[] dictionary1 = {"leet", "code", "leetcode"};

        String str2 = "sayhelloworld";
        String[] dictionary2 = {"hello", "world"};

        String str3 = "dwmodizxvvbosxxw";
        String[] dictionary3 = {"ox", "lb", "diz", "gu", "v", "ksv", "o", "nuq", "r", "txhe", "e", "wmo", "cehy", "tskz", "ds", "kzbu"};

        System.out.println(solution.minExtraChar(str1, dictionary1));
        System.out.println(solution.minExtraChar(str2, dictionary2));
        System.out.println(solution.minExtraChar(str3, dictionary3));
    }

    public int minExtraChar(String s, String[] dictionary) {
        int n = s.length();
        int[] dp = new int[n + 1]; // dp[i] буде містити мінімальну кількість додаткових символів для рядка s.substring(0, i)
        Arrays.fill(dp, Integer.MAX_VALUE); // Ініціалізуємо dp значеннями, що перевищують будь-яку можливу кількість додаткових символів

        dp[0] = 0; // Нульовий підрядок не має додаткових символів

        for (int i = 1; i <= n; i++) {
            for (String word : dictionary) {
                int len = word.length();
                if (i >= len && s.substring(i - len, i).equals(word)) {
                    dp[i] = Math.min(dp[i], dp[i - len]);
                }
            }

            dp[i] = Math.min(dp[i], dp[i - 1] + 1); // Обчислюємо dp[i] на основі попередніх значень
        }

        return dp[n];
    }
}
//вірне рішення також
//public int minExtraChar(String s, String[] dictionary) {
//        int n = s.length();
//        int[] dp = new int[n + 1]; // dp[i] буде містити мінімальну кількість додаткових символів для рядка s.substring(0, i)
//        Arrays.fill(dp, n); // Ініціалізуємо dp значеннями, що перевищують будь-яку можливу кількість додаткових символів
//
//        dp[0] = 0; // Нульовий підрядок не має додаткових символів
//
//        for (int i = 1; i <= n; i++) {
//            for (int j = 0; j < i; j++) {
//                String currentSubstring = s.substring(j, i);
//                if (Arrays.asList(dictionary).contains(currentSubstring)) {
//                    dp[i] = Math.min(dp[i], dp[j]);
//                } else {
//                    dp[i] = Math.min(dp[i], dp[j] + currentSubstring.length());
//                }
//            }
//        }
//
//        return dp[n];
//    }


//НЕвірне рішення
//    public int minExtraChar(String s, String[] dictionary) {
//        int result = 0;
//        int count = 0;
//
//        for (int i = 0; i < dictionary.length; i++) {
//            if (s.contains(dictionary[i])) {
//                count += dictionary[i].length();
//
//            }
//        }
//
//        result = s.length() - count;
//        return result;
//    }
//}

//You are given a 0-indexed string s and a dictionary of words dictionary. You have to break s into one or more non-overlapping substrings such that each substring is present in dictionary. There may be some extra characters in s which are not present in any of the substrings.
//
//Return the minimum number of extra characters left over if you break up s optimally.
//
//Example 1:
//
//Input: s = "leetscode", dictionary = ["leet","code","leetcode"]
//Output: 1
//Explanation: We can break s in two substrings: "leet" from index 0 to 3 and "code" from index 5 to 8. There is only 1 unused character (at index 4), so we return 1.
//
//Example 2:
//
//Input: s = "sayhelloworld", dictionary = ["hello","world"]
//Output: 3
//Explanation: We can break s in two substrings: "hello" from index 3 to 7 and "world" from index 8 to 12. The characters at indices 0, 1, 2 are not used in any substring and thus are considered as extra characters. Hence, we return 3.
//
//Constraints:
//
//1 <= s.length <= 50
//1 <= dictionary.length <= 50
//1 <= dictionary[i].length <= 50
//dictionary[i] and s consists of only lowercase English letters
//dictionary contains distinct words