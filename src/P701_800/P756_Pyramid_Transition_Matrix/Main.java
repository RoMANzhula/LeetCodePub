package P701_800.P756_Pyramid_Transition_Matrix;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String bottom1 = "BCD";
        List<String> allowed1 = Arrays.asList("BCC", "CDE", "CEA", "FFF");
        System.out.println(solution.pyramidTransition(bottom1, allowed1)); // true

        String bottom2 = "AAAA";
        List<String> allowed2 = Arrays.asList("AAB", "AAC", "BCD", "BBE", "DEF");
        System.out.println(solution.pyramidTransition(bottom2, allowed2)); // false
    }

    public boolean pyramidTransition(String bottom, List<String> allowed) {
        Map<String, List<Character>> map = new HashMap<>();

        // build mapping: "AB" -> ['C', 'D', ...]
        for (String s : allowed) {
            String key = s.substring(0, 2);
            char top = s.charAt(2);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(top);
        }

        return dfs(bottom, "", 0, map);
    }

    private static boolean dfs(String currentRow, String nextRow, int index,
                               Map<String, List<Character>> map) {

        // if we reached the top
        if (currentRow.length() == 1) {
            return true;
        }

        //finished building next row → move upward
        if (index == currentRow.length() - 1) {
            return dfs(nextRow, "", 0, map);
        }

        String key = currentRow.substring(index, index + 2);
        if (!map.containsKey(key)) {
            return false;
        }

        // try all possible top blocks
        for (char c : map.get(key)) {
            if (dfs(currentRow, nextRow + c, index + 1, map)) {
                return true;
            }
        }

        return false;
    }

}

//Complexity:
// time - O(6^5)
// space - O(1)


//You are stacking blocks to form a pyramid. Each block has a color, which is represented by a single letter. Each
// row of blocks contains one less block than the row beneath it and is centered on top.
//To make the pyramid aesthetically pleasing, there are only specific triangular patterns that are allowed. A
// triangular pattern consists of a single block stacked on top of two blocks. The patterns are given as a list of
// three-letter strings allowed, where the first two characters of a pattern represent the left and right bottom
// blocks respectively, and the third character is the top block.
//For example, "ABC" represents a triangular pattern with a 'C' block stacked on top of an 'A' (left) and
// 'B' (right) block. Note that this is different from "BAC" where 'B' is on the left bottom and 'A' is on the right bottom.
//You start with a bottom row of blocks bottom, given as a single string, that you must use as the base of the pyramid.
//Given bottom and allowed, return true if you can build the pyramid all the way to the top such that every triangular
// pattern in the pyramid is in allowed, or false otherwise.

//Example 1:
//Input: bottom = "BCD", allowed = ["BCC","CDE","CEA","FFF"]
//Output: true
//Explanation: The allowed triangular patterns are shown on the right.
//Starting from the bottom (level 3), we can build "CE" on level 2 and then build "A" on level 1.
//There are three triangular patterns in the pyramid, which are "BCC", "CDE", and "CEA". All are allowed.

//Example 2:
//Input: bottom = "AAAA", allowed = ["AAB","AAC","BCD","BBE","DEF"]
//Output: false
//Explanation: The allowed triangular patterns are shown on the right.
//Starting from the bottom (level 4), there are multiple ways to build level 3, but trying all the possibilites, you
// will get always stuck before building level 1.

//Constraints:
//2 <= bottom.length <= 6
//0 <= allowed.length <= 216
//allowed[i].length == 3
//The letters in all input strings are from the set {'A', 'B', 'C', 'D', 'E', 'F'}.
//All the values of allowed are unique.
