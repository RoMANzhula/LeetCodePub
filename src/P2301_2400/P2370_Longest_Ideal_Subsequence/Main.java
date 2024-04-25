package P2301_2400.P2370_Longest_Ideal_Subsequence;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.longestIdealString("acfgbd", 2)); // Output: 4
        System.out.println(solution.longestIdealString("abcd", 3));    // Output: 4

    }

    public int longestIdealString(String s, int k) {
        int[] dp = new int[26];

        for (char c : s.toCharArray()) {
            int i = c - 'a';
            dp[i] = 1 + getMaxReachable(dp, i, k);
        }

        int maxLen = 0;
        for (int i = 0; i < 26; i++) {
            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }

    private int getMaxReachable(int[] dp, int i, int k) {
        int first = Math.max(0, i - k);
        int last = Math.min(25, i + k);
        int maxReachable = 0;
        for (int j = first; j <= last; j++) {
            maxReachable = Math.max(maxReachable, dp[j]);
        }
        return maxReachable;
    }
}

//Ми створюємо масив dp довжиною 26, який представлятиме найбільшу довжину ідеального рядка ( ідеальний рядок - це
// рядок, в якому порядок літер відповідає вимогам відносно різниці у порядку алфавіту між сусідніми літерами), який
// закінчується на кожній літері алфавіту (від 'a' до 'z').
//Проходимося по кожному символу у введеному рядку s.
//Для кожного символу обчислюємо його індекс у масиві (від 0 до 25), щоб зберегти довжину найбільшої ідеальної
// підстроки, яка закінчується на цьому символі.
//Для кожного символу визначаємо максимальну довжину підстроки, яка може бути досягнута з цього символу, з
// огляду на обмеження k.
//Обчислюємо довжину ідеального рядка, який закінчується на кожній літері алфавіту, шляхом додавання 1 до
// найбільшої досяжної довжини з попередньої ітерації.
//Повертаємо найбільше значення з масиву dp як результат.
//В кінці повертаємо максимальну довжину ідеального рядка з усіх можливих символів алфавіту.

//You are given a string s consisting of lowercase letters and an integer k. We call a string t ideal if the
// following conditions are satisfied:
//t is a subsequence of the string s.
//The absolute difference in the alphabet order of every two adjacent letters in t is less than or equal to k.
//Return the length of the longest ideal string.
//A subsequence is a string that can be derived from another string by deleting some or no characters without
// changing the order of the remaining characters.
//Note that the alphabet order is not cyclic. For example, the absolute difference in the alphabet order
// of 'a' and 'z' is 25, not 1.
//
//Example 1:
//Input: s = "acfgbd", k = 2
//Output: 4
//Explanation: The longest ideal string is "acbd". The length of this string is 4, so 4 is returned.
//Note that "acfgbd" is not ideal because 'c' and 'f' have a difference of 3 in alphabet order.

//Example 2:
//Input: s = "abcd", k = 3
//Output: 4
//Explanation: The longest ideal string is "abcd". The length of this string is 4, so 4 is returned.
//
//Constraints:
//1 <= s.length <= 105
//0 <= k <= 25
//s consists of lowercase English letters.
