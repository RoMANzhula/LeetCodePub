package P901_1000.P950_Reveal_Cards_In_Increasing_Order;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] deck1 = {17, 13, 11, 2, 3, 5, 7};
        System.out.println(Arrays.toString(solution.deckRevealedIncreasing(deck1))); // Output: [2, 13, 3, 11, 5, 17, 7]

        int[] deck2 = {1, 1000};
        System.out.println(Arrays.toString(solution.deckRevealedIncreasing(deck2))); // Output: [1, 1000]
    }

    public int[] deckRevealedIncreasing(int[] deck) {
        // Step 1: Sort the deck array
        Arrays.sort(deck);

        // Step 2: Initialize an empty deque
        Deque<Integer> deque = new LinkedList<>();

        // Step 3: Iterate through the sorted deck array from the end to the beginning
        for (int i = deck.length - 1; i >= 0; i--) {
            // Step 4: For each element in the deck array
            // If the deque is not empty, remove the last element from the deque and add it to the beginning
            if (!deque.isEmpty()) {
                deque.addFirst(deque.removeLast());
            }
            // Add the current element to the beginning of the deque
            deque.addFirst(deck[i]);
        }

        // Step 5: Convert the deque to an array and return it
        int[] result = new int[deck.length];
        int index = 0;
        for (int num : deque) {
            result[index++] = num;
        }
        return result;
    }
}

//Щоб вирішити цю задачу, ми можемо використовувати двійкову чергу (deque), щоб симулювати процес, описаний в умові
// задачі. Спочатку ми сортуємо масив карт (deck), щоб отримати порядок карт, які будуть відкриті.
//Ось поетапний підхід:
//Відсортувати масив карт.
//Ініціалізувати порожню двійкову чергу для зберігання результату.
//Пройтися по відсортованому масиву карт від кінця до початку.
//Для кожного елемента в масиві карт:
//  -Якщо черга не порожня, видаляємо останній елемент з черги і додаємо його на початок.
//  -Додаємо поточний елемент на початок черги.
//Конвертуємо двійкову чергу в масив і повертаємо його.
//Це рішення має складність часу O(nlogn) через сортування масиву карт, де n - довжина масиву карт.
// Складність - O(n), оскільки ми використовуємо двійкову чергу для зберігання результату.

//You are given an integer array deck. There is a deck of cards where every card has a unique integer. The integer
// on the ith card is deck[i].
//You can order the deck in any order you want. Initially, all the cards start face down (unrevealed) in one deck.
//You will do the following steps repeatedly until all cards are revealed:
//Take the top card of the deck, reveal it, and take it out of the deck.
//If there are still cards in the deck then put the next top card of the deck at the bottom of the deck.
//If there are still unrevealed cards, go back to step 1. Otherwise, stop.
//Return an ordering of the deck that would reveal the cards in increasing order.
//Note that the first entry in the answer is considered to be the top of the deck.
//
//Example 1:
//Input: deck = [17,13,11,2,3,5,7]
//Output: [2,13,3,11,5,17,7]
//Explanation:
//We get the deck in the order [17,13,11,2,3,5,7] (this order does not matter), and reorder it.
//After reordering, the deck starts as [2,13,3,11,5,17,7], where 2 is the top of the deck.
//We reveal 2, and move 13 to the bottom.  The deck is now [3,11,5,17,7,13].
//We reveal 3, and move 11 to the bottom.  The deck is now [5,17,7,13,11].
//We reveal 5, and move 17 to the bottom.  The deck is now [7,13,11,17].
//We reveal 7, and move 13 to the bottom.  The deck is now [11,17,13].
//We reveal 11, and move 17 to the bottom.  The deck is now [13,17].
//We reveal 13, and move 17 to the bottom.  The deck is now [17].
//We reveal 17.
//Since all the cards revealed are in increasing order, the answer is correct.

//Example 2:
//Input: deck = [1,1000]
//Output: [1,1000]
//
//Constraints:
//1 <= deck.length <= 1000
//1 <= deck[i] <= 106
//All the values of deck are unique.
