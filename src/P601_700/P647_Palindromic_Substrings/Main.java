package P601_700.P647_Palindromic_Substrings;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.countSubstrings("abc")); // output 3 - (a, b, c)
        System.out.println(solution.countSubstrings("aaa")); // output 6 - (a, a, a, aa, aa, aaa)
    }

    public int countSubstrings(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int count = 0;

        // Single characters are palindromes
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
            count++;
        }

        // Check for substrings of length 2
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                count++;
            }
        }

        // Check for substrings of length greater than 2
        for (int len = 3; len <= n; len++) {
            for (int start = 0; start <= n - len; start++) {
                int end = start + len - 1;
                if (s.charAt(start) == s.charAt(end) && dp[start + 1][end - 1]) {
                    dp[start][end] = true;
                    count++;
                }
            }
        }

        return count;
    }
}

//Задача полягає у тому, щоб знайти кількість паліндромних підрядків у заданому рядку. Паліндром - це рядок, який
// можна читати однаково як зліва направо, так і справа наліво.
//Ми використовуємо динамічне програмування для ефективного розв'язання цієї задачі. Ми створюємо двовимірний
// масив dp, де dp[i][j] буде true, якщо підрядок з індексами від i до j є паліндромом.
//Спочатку ми заповнюємо масив dp для одиночних символів, оскільки кожен одиночний символ є паліндромом. Потім
// ми перевіряємо для підрядків довжиною 2, які також можуть бути паліндромами. Далі ми перевіряємо всі інші
// підрядки довжиною більше 2. Якщо крайні символи однакові, а внутрішні підрядки є паліндромами, то весь
// підрядок також буде паліндромом.
//У кінці ми просто підраховуємо кількість паліндромних підрядків, які ми знайшли, і повертаємо це значення.

//Given a string s, return the number of palindromic substrings in it.
//A string is a palindrome when it reads the same backward as forward.
//A substring is a contiguous sequence of characters within the string.

//Example 1:
//Input: s = "abc"
//Output: 3
//Explanation: Three palindromic strings: "a", "b", "c".

//Example 2:
//Input: s = "aaa"
//Output: 6
//Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".

//Constraints:
//1 <= s.length <= 1000
//s consists of lowercase English letters.
