package P801_900.P846_Hand_of_Straights;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] hand1 = {1, 2, 3, 6, 2, 3, 4, 7, 8};
        int groupSize1 = 3;
        System.out.println(solution.isNStraightHand(hand1, groupSize1)); // Output: true

        int[] hand2 = {1, 2, 3, 4, 5};
        int groupSize2 = 4;
        System.out.println(solution.isNStraightHand(hand2, groupSize2)); // Output: false
    }

//    public boolean isNStraightHand(int[] hand, int groupSize) {
//        if (hand.length % groupSize != 0) {
//            return false; // If the total number of cards is not divisible by groupSize, return false.
//        }
//
//        Map<Integer, Integer> cardCountMap = new TreeMap<>();
//        for (int card : hand) {
//            cardCountMap.put(card, cardCountMap.getOrDefault(card, 0) + 1);
//        }
//
//        for (int card : cardCountMap.keySet()) {
//            int count = cardCountMap.get(card);
//            if (count > 0) {
//                for (int i = 0; i < groupSize; i++) {
//                    int currentCard = card + i;
//                    if (cardCountMap.getOrDefault(currentCard, 0) < count) {
//                        return false;
//                    }
//                    cardCountMap.put(currentCard, cardCountMap.get(currentCard) - count);
//                }
//            }
//        }
//        return true;
//    }

//    // use less memory
//    public boolean isNStraightHand(int[] hand, int groupSize) {
//        if (hand.length % groupSize != 0) {
//            return false; // Якщо загальна кількість карток не ділиться на groupSize, повертаємо false.
//        }
//
//        Arrays.sort(hand); // Сортуємо масив карток.
//
//        for (int i = 0; i < hand.length; i++) {
//            if (hand[i] == -1) {
//                continue; // Пропускаємо карти, які вже були оброблені.
//            }
//            int start = hand[i];
//            for (int j = 0; j < groupSize; j++) {
//                int target = start + j;
//                boolean found = false;
//                for (int k = i; k < hand.length; k++) {
//                    if (hand[k] == target) {
//                        hand[k] = -1; // Помічаємо карту як оброблену.
//                        found = true;
//                        break;
//                    }
//                }
//                if (!found) {
//                    return false; // Якщо не вдалося знайти карту для поточної групи, повертаємо false.
//                }
//            }
//        }
//        return true;
//    }

    // faster solution
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) {
            return false; // If the total numbers of cards is not divisible by groupSize - return false
        }

        Arrays.sort(hand);

        Map<Integer, Integer> countMap = new HashMap<>();
        for (int card : hand) {
            countMap.put(card, countMap.getOrDefault(card, 0) + 1);
        }

        for (int card : hand) {
            if (countMap.get(card) == 0) {
                continue; // We skip processed cards
            }
            for (int i = 0; i < groupSize; i++) {
                int currentCard = card + i;
                if (countMap.getOrDefault(currentCard, 0) == 0) {
                    return false; // If the Map for the current group cannot be found - return false
                }
                countMap.put(currentCard, countMap.get(currentCard) - 1); // We reduse the number of cards
            }
        }
        return true;
    }

}

// Last solve
//Кроки для вирішення проблеми
//Перевірка можливості ділення:
//Спочатку перевіряємо, чи загальна кількість карток ділиться на розмір групи (groupSize). Якщо ні, то одразу
// повертаємо false, оскільки неможливо розбити картки на групи рівного розміру.
//Сортування карток:
//Сортуємо масив карток. Це допомагає нам легко перевіряти наявність послідовних груп.
//Підрахунок частоти карток:
//Створюємо структуру даних для підрахунку частоти кожної картки. Для цього використовуємо HashMap, де ключем є
// значення картки, а значенням — кількість таких карток у масиві.
//Формування груп:
//Проходимо по відсортованому масиву карток. Для кожної картки:
//Якщо картка вже була використана в попередніх групах (її частота в мапі дорівнює нулю), пропускаємо її.
//Для кожної картки намагаємося сформувати групу розміру groupSize. Для цього перевіряємо наявність карток з
// послідовними значеннями (поточна картка, поточна картка + 1, ..., поточна картка + (groupSize - 1)).
//Якщо всі послідовні картки для групи є в наявності, зменшуємо їх частоту в мапі. Якщо хоча б однієї картки не
// вистачає для формування групи, повертаємо false.
//Завершення перевірки:
//Якщо всі картки можуть бути успішно згруповані, повертаємо true.
//Принцип роботи
//Основна ідея полягає в тому, щоб використати сортування для полегшення перевірки послідовності та HashMap для
// ефективного підрахунку частоти карток. Це допомагає уникнути вкладених циклів та підвищує продуктивність алгоритму.
//Сортування карток дозволяє нам перевіряти послідовність просто порівнюючи поточну картку з наступними. Використання
// мапи частот дозволяє ефективно зберігати та оновлювати кількість наявних карток, що забезпечує швидкий доступ
// до потрібних значень і оновлень.

//Alice has some number of cards and she wants to rearrange the cards into groups so that each group is of size
// groupSize, and consists of groupSize consecutive cards.
//Given an integer array hand where hand[i] is the value written on the ith card and an integer groupSize, return
// true if she can rearrange the cards, or false otherwise.
//
//Example 1:
//Input: hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
//Output: true
//Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8]

//Example 2:
//Input: hand = [1,2,3,4,5], groupSize = 4
//Output: false
//Explanation: Alice's hand can not be rearranged into groups of 4.
//
//Constraints:
//1 <= hand.length <= 104
//0 <= hand[i] <= 109
//1 <= groupSize <= hand.length