package P701_800.P767_Reorganize_String;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        System.out.println(reorganizeString("aab"));
        System.out.println(reorganizeString("aaab"));
        System.out.println(reorganizeString("aaabc"));
    }

    public static String reorganizeString(String s) {
        Map<Character, Integer> charCounter = new HashMap<>(); //створюємо мапу для підрахунку символів
        for (char c : s.toCharArray()) { //проходимо по кожному символу з строки s
            charCounter.put(c, charCounter.getOrDefault(c, 0) + 1);//збільшуємо кількість входження символа "с" на 1,
            //але враховуємо якщо не було ще такого символу у мапі, то його значення по дефолту буде = 0
        }

        PriorityQueue<Character> queueForChars = new PriorityQueue<>( //створюємо "приоритетну чергу"
                (a, b) -> charCounter.get(b) - charCounter.get(a) //сортуємо її від більших повторів чарів до меньших
        );
        queueForChars.addAll(charCounter.keySet()); //заповнюємо колекцію значеннями

        StringBuilder result = new StringBuilder(); //контейнер для результату

        while (queueForChars.size() >= 2) { //поки в нашій колекції є більше двох символів
            char first = queueForChars.poll(); //перший символ (в колекції перший за кількістю повторень)
            char second = queueForChars.poll(); //другий символ (в колекції другий за кількістю повторень)

            //будуємо відповідь
            result.append(first);
            result.append(second);

            //додаємо символи у мапу, зменьшуючи кількість повторень символів
            charCounter.put(first, charCounter.get(first) - 1);
            charCounter.put(second, charCounter.get(second) - 1);

            //якщо частота символів більше за 0, то додаємо їх назад у колекцію до символів
            if (charCounter.get(first) > 0) {
                queueForChars.offer(first);
            }
            if (charCounter.get(second) > 0) {
                queueForChars.offer(second);
            }
        }

        if (!queueForChars.isEmpty()) { //якщо колекція відсортованих символів не пуста
            char lastChar = queueForChars.poll(); //створюємо змінну для останнього символа з "черги"
            if (charCounter.get(lastChar) > 1) { //якщо частота останнього символу більше 1, то не вдається
                // розмістити символи так, щоб вони не повторювались, то
                return ""; //повертаємо нічого
            }
            result.append(lastChar); //додаємо останній символ в кінець черги
        }

        return result.toString();
    }

}

//Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.
//Return any possible rearrangement of s or return "" if not possible.

//Example 1:
//Input: s = "aab"
//Output: "aba"

//Example 2:
//Input: s = "aaab"
//Output: ""

//Constraints:
//1 <= s.length <= 500
//s consists of lowercase English letters.
