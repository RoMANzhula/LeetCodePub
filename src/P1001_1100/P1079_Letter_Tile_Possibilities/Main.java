package P1001_1100.P1079_Letter_Tile_Possibilities;

import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.numTilePossibilities("AAB"));  // Output: 8
        System.out.println(solution.numTilePossibilities("AAABBC")); // Output: 188
        System.out.println(solution.numTilePossibilities("V"));   // Output: 1
    }

    public int numTilePossibilities(String tiles) {
        HashSet<String> result = new HashSet<>();
        boolean[] visited = new boolean[tiles.length()];
        backtrack(tiles.toCharArray(), new StringBuilder(), visited, result);

        return result.size();
    }

    private void backtrack(char[] tiles, StringBuilder current, boolean[] visited, HashSet<String> result) {
        if (!current.isEmpty()) {
            result.add(current.toString());
        }

        for (int i = 0; i < tiles.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                current.append(tiles[i]);
                backtrack(tiles, current, visited, result);
                current.deleteCharAt(current.length() - 1);
                visited[i] = false;
            }
        }
    }

}

//Explanation:
//Backtracking Approach: We explore all possible sequences recursively.
//HashSet for Unique Sequences: We store each valid sequence to avoid duplicates.
//Boolean Array for Tracking Used Tiles: Ensures we don’t reuse tiles incorrectly.
//Complexity: time complexity of this solution is approximately O(k!), where k is the number of unique
// letters in the input string.


//You have n  tiles, where each tile has one letter tiles[i] printed on it.
//Return the number of possible non-empty sequences of letters you can make using the letters printed on those tiles.
//
//Example 1:
//Input: tiles = "AAB"
//Output: 8
//Explanation: The possible sequences are "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA".

//Example 2:
//Input: tiles = "AAABBC"
//Output: 188

//Example 3:
//Input: tiles = "V"
//Output: 1
//
//Constraints:
//1 <= tiles.length <= 7
//tiles consists of uppercase English letters.