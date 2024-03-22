package P2225_Find_Players_With_Zero_or_One_Losses;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Example 1
        int[][] matches1 = {{1, 3}, {2, 3}, {3, 6}, {5, 6}, {5, 7}, {4, 5}, {4, 8}, {4, 9}, {10, 4}, {10, 9}};
        List<List<Integer>> result1 = findWinners(matches1);
        System.out.println(result1); // Output: [[1, 2, 10], [4, 5, 7, 8]]

        // Example 2
        int[][] matches2 = {{2, 3}, {1, 3}, {5, 4}, {6, 4}};
        List<List<Integer>> result2 = findWinners(matches2);
        System.out.println(result2); // Output: [[1, 2, 5, 6], []]
    }

    public static List<List<Integer>> findWinners(int[][] matches) {
        // Map to store the count of losses for each player
        Map<Integer, Integer> losses = new HashMap<>();

        // Set to store players who have played at least one match
        // (to ensure only consider players who have played at least one match)
        HashSet<Integer> playersWithMatches = new HashSet<>();

        // Iterate through the matches to count losses and identify players
        for (int[] match : matches) {
            int winner = match[0];
            int loser = match[1];

            // Increment loss count for the loser
            losses.put(loser, losses.getOrDefault(loser, 0) + 1);
            // Add both winner and loser to the set of players
            playersWithMatches.add(winner);
            playersWithMatches.add(loser);
        }

        // Lists to store players with 0 and 1 loss respectively
        List<Integer> zeroLossPlayers = new ArrayList<>();
        List<Integer> oneLossPlayers = new ArrayList<>();

        // Iterate through the set of players to categorize players
        for (int player : playersWithMatches) {
            int lossCount = losses.getOrDefault(player, 0);

            if (lossCount == 0) {
                zeroLossPlayers.add(player);
            } else if (lossCount == 1) {
                oneLossPlayers.add(player);
            }
        }

        // Sort the lists in increasing order
        zeroLossPlayers.sort(null);
        oneLossPlayers.sort(null);

        // Result list containing the two lists
        List<List<Integer>> result = new ArrayList<>();
        result.add(zeroLossPlayers);
        result.add(oneLossPlayers);

        return result;
    }
}

//Задача полягає в тому, щоб визначити два списки гравців на основі результатів їхніх ігор. Перший список - це
// гравці, які не програли жодної гри, і другий список - гравці, які програли рівно один раз.
//
//Найпростіший спосіб вирішити цю задачу - використовуючи мапу для відстеження кількості програшів кожного
// гравця. Ми також використовуємо множину, щоб зберегти інформацію про те, які гравці брали участь хоча б
// у одній грі (так як завдання вимагає врахувати лише тих, хто хоча б раз взяв участь у грі).
//
//Основний алгоритм:
//
//Пройти усі матчі та підрахувати програші для кожного гравця, додаючи їх до мапи losses.
//Використовуючи множину playersWithMatches, зберегти інформацію про гравців, які брали участь у хоча б одній грі.
//Пройти по множині гравців, визначити, скільки разів кожен з них програв (losses.getOrDefault(player, 0)) і
// додати гравців без програшів або з одним програшем до відповідних списків.
//Сортувати отримані списки у порядку зростання.

//You are given an integer array matches where matches[i] = [winneri, loseri] indicates that the player
// winneri defeated player loseri in a match.
//
//Return a list answer of size 2 where:
//
//answer[0] is a list of all players that have not lost any matches.
//answer[1] is a list of all players that have lost exactly one match.
//The values in the two lists should be returned in increasing order.
//
//Note:
//
//You should only consider the players that have played at least one match.
//The testcases will be generated such that no two matches will have the same outcome.
//
//
//Example 1:
//
//Input: matches = [[1,3],[2,3],[3,6],[5,6],[5,7],[4,5],[4,8],[4,9],[10,4],[10,9]]
//Output: [[1,2,10],[4,5,7,8]]
//Explanation:
//Players 1, 2, and 10 have not lost any matches.
//Players 4, 5, 7, and 8 each have lost one match.
//Players 3, 6, and 9 each have lost two matches.
//Thus, answer[0] = [1,2,10] and answer[1] = [4,5,7,8].
//Example 2:
//
//Input: matches = [[2,3],[1,3],[5,4],[6,4]]
//Output: [[1,2,5,6],[]]
//Explanation:
//Players 1, 2, 5, and 6 have not lost any matches.
//Players 3 and 4 each have lost two matches.
//Thus, answer[0] = [1,2,5,6] and answer[1] = [].
//
//
//Constraints:
//
//1 <= matches.length <= 105
//matches[i].length == 2
//1 <= winneri, loseri <= 105
//winneri != loseri
//All matches[i] are unique.