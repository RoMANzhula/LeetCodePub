package P901_1000.P948_Bag_of_Tokens;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] tokens1 = {100};
        int power1 = 50;
        System.out.println(solution.bagOfTokensScore(tokens1, power1)); // Output: 0

        int[] tokens2 = {200, 100};
        int power2 = 150;
        System.out.println(solution.bagOfTokensScore(tokens2, power2)); // Output: 1

        int[] tokens3 = {100, 200, 300, 400};
        int power3 = 200;
        System.out.println(solution.bagOfTokensScore(tokens3, power3)); // Output: 2
    }

    public int bagOfTokensScore(int[] tokens, int power) {
        Arrays.sort(tokens);
        int score = 0;
        int maxScore = 0;
        int left = 0;
        int right = tokens.length - 1;

        while (left <= right) {
            if (power >= tokens[left]) {
                power -= tokens[left++];
                maxScore = Math.max(maxScore, ++score);
            } else if (score > 0) {
                power += tokens[right--];
                score--;
            } else {
                break;
            }
        }

        return maxScore;
    }
}

//Звідкіля йдуть токени, ми спочатку сортуємо їх у порядку зростання. Потім ми використовуємо два вказівники: один,
// що вказує на початок масиву (лівий вказівник), інший - на його кінець (правий вказівник).
//У циклі ми спочатку перевіряємо, чи маємо достатньо потужності, щоб зіграти перший токен. Якщо так, ми
// граємо його "лицем вгору" (face-up), зменшуючи потужність і збільшуючи рахунок. Потім ми порівнюємо
// поточний рахунок з максимальним, що ми отримали до цього моменту, і оновлюємо максимальний рахунок,
// якщо поточний більший.
//Якщо ми не можемо зіграти токен "лицем вгору" (через недостатню потужність), але маємо позитивний рахунок,
// ми граємо токен "лицем вниз" (face-down), збільшуючи потужність і зменшуючи рахунок. Це можливо, оскільки
// ми маємо хоча б один бал. Ми продовжуємо цей процес, доки лівий вказівник не дорівнюватиме або перевищуватиме
// правий, оскільки тоді ми вичерпали всі можливі токени для гри. Повертаємо максимальний отриманий рахунок.

//You start with an initial power of power, an initial score of 0, and a bag of tokens given as an integer array
// tokens, where each tokens[i] donates the value of tokeni.
//Your goal is to maximize the total score by strategically playing these tokens. In one move, you can play
// an unplayed token in one of the two ways (but not both for the same token):
//Face-up: If your current power is at least tokens[i], you may play tokeni, losing tokens[i] power and gaining 1 score.
//Face-down: If your current score is at least 1, you may play tokeni, gaining tokens[i] power and losing 1 score.
//Return the maximum possible score you can achieve after playing any number of tokens.
//
//Example 1:
//Input: tokens = [100], power = 50
//Output: 0
//Explanation: Since your score is 0 initially, you cannot play the token face-down. You also cannot play
// it face-up since your power (50) is less than tokens[0] (100).
//
//Example 2:
//Input: tokens = [200,100], power = 150
//Output: 1
//Explanation: Play token1 (100) face-up, reducing your power to 50 and increasing your score to 1.
//There is no need to play token0, since you cannot play it face-up to add to your score. The maximum
// score achievable is 1.
//
//Example 3:
//Input: tokens = [100,200,300,400], power = 200
//Output: 2
//Explanation: Play the tokens in this order to get a score of 2:
//Play token0 (100) face-up, reducing power to 100 and increasing score to 1.
//Play token3 (400) face-down, increasing power to 500 and reducing score to 0.
//Play token1 (200) face-up, reducing power to 300 and increasing score to 1.
//Play token2 (300) face-up, reducing power to 0 and increasing score to 2.
//The maximum score achievable is 2.
//
//Constraints:
//0 <= tokens.length <= 1000
//0 <= tokens[i], power < 104
