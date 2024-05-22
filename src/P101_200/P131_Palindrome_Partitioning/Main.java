package P101_200.P131_Palindrome_Partitioning;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.partition("aab")); //output: [[a, a, b], [aa, b]]
        System.out.println(solution.partition("a")); // output: [[a]]
    }

    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(String s, int start, List<String> currentPartition, List<List<String>> result) {
        if (start == s.length()) {
            result.add(new ArrayList<>(currentPartition));
            return;
        }

        for (int end = start + 1; end <= s.length(); end++) {
            String substring = s.substring(start, end);
            if (isPalindrome(substring)) {
                currentPartition.add(substring);
                backtrack(s, end, currentPartition, result);
                currentPartition.removeLast();
            }
        }
    }

    private boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}

//Ми використаємо метод backtracking (зворотний хід), щоб дослідити всі можливі розбиття рядка і перевірити, чи є
// кожна підстрічка паліндромом.
//Основні частини коду
//Основна функція (partition):
//Ініціалізує список result, який буде містити всі можливі розбиття рядка.
//Викликає функцію backtrack, починаючи з індексу 0 з порожнім поточним розбиттям.
//Функція зворотного ходу (backtrack):
//Досліджує всі можливі розбиття, починаючи з індексу start.
//Якщо індекс start досягає кінця рядка, ми знайшли коректне розбиття, і воно додається до списку результатів.
//Перебирає всі можливі кінцеві індекси, формуючи підстрічки від start до end.
//Для кожної підстрічки перевіряє, чи є вона паліндромом, використовуючи функцію isPalindrome.
//Якщо підстрічка є паліндромом, додає її до поточного розбиття і рекурсивно викликає backtrack з новим
// початковим індексом (кінцем поточної підстрічки).
//Після дослідження всіх розбиттів, починаючи з поточної підстрічки, видаляє останню підстрічку з поточного
// розбиття (крок зворотного ходу).
//Функція перевірки паліндромів (isPalindrome):
//Перевіряє, чи є даний рядок паліндромом, порівнюючи символи з початку і кінця, рухаючись до центру.
//Якщо всі символи збігаються, повертає true, інакше — false.
//partition: Головна функція, яка ініціалізує результат і викликає функцію зворотного ходу.
//backtrack: Рекурсивна функція, яка шукає всі можливі розбиття, перевіряючи кожну підстрічку на паліндромність і
// додаючи її до поточного розбиття.
//isPalindrome: Перевіряє, чи є підстрічка паліндромом.
//Це рішення використовує рекурсію для дослідження всіх можливих розбиттів і перевірки кожного з них на
// паліндромність, що гарантує повернення всіх коректних розбиттів.

//Given a string s, partition s such that every
//substring
// of the partition is a
//palindrome
//. Return all possible palindrome partitioning of s.
//
//Example 1:
//Input: s = "aab"
//Output: [["a","a","b"],["aa","b"]]

//Example 2:
//Input: s = "a"
//Output: [["a"]]
//
//Constraints:
//1 <= s.length <= 16
//s contains only lowercase English letters.
