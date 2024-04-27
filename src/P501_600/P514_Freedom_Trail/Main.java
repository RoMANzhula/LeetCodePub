package P501_600.P514_Freedom_Trail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.findRotateSteps("godding", "gd")); // Output: 4
        System.out.println(solution.findRotateSteps("godding", "godding")); // Output: 13
    }

    public int findRotateSteps(String ring, String key) {
        int n = ring.length();
        int m = key.length();

        // Create a dp table to store the minimum steps for each index of ring and key
        int[][] dp = new int[m + 1][n];

        // Store the index of each character in the ring
        HashMap<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char c = ring.charAt(i);
            map.computeIfAbsent(c, k -> new ArrayList<>()).add(i);
        }

        // Fill the dp table
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                char c = key.charAt(i);
                for (int k : map.getOrDefault(c, Collections.emptyList())) {
                    int diff = Math.abs(j - k);
                    int minSteps = Math.min(diff, n - diff);
                    dp[i][j] = Math.min(dp[i][j], minSteps + dp[i + 1][k]);
                }
            }
        }

        return dp[0][0] + m;
    }

//    public int findRotateSteps(String ring, String key) {
//        int m = key.length();
//        // Memoization table to store intermediate results
//        HashMap<String, Integer> memo = new HashMap<>();
//        return dfs(ring, key, 0,  memo) + m;
//    }
//
//    private int dfs(String ring, String key, int index, HashMap<String, Integer> memo) {
//        if (index == key.length()) return 0;
//
//        String memoKey = ring + "_" + index;
//        if (memo.containsKey(memoKey)) return memo.get(memoKey);
//
//        int ans = Integer.MAX_VALUE;
//
//        for (int i = 0; i < ring.length(); i++) {
//            if (ring.charAt(i) == key.charAt(index)) {
//                int minRotates = Math.min(i, ring.length() - i);
//                String newRing = ring.substring(i) + ring.substring(0, i);
//                int remainingRotates = dfs(newRing, key, index + 1, memo);
//                ans = Math.min(ans, minRotates + remainingRotates);
//            }
//        }
//
//        memo.put(memoKey, ans);
//        return ans;
//    }
}

//В даному вирішенні ми використовуємо динамічне програмування для знаходження мінімальної кількості кроків,
// необхідних для написання заданого ключа за допомогою обертання кільця.
//Спочатку ми створюємо двовимірний масив dp, де dp[i][j] представляє мінімальну кількість кроків, які потрібно
// для написання підстроки ключа від i-го символа до кінця, починаючи з j-го символа кільця.
//Ми також створюємо словник map, де для кожного символу кільця ми зберігаємо список його індексів у кільці.
//Потім ми заповнюємо dp у зворотньому напрямку. Для кожного символу ключа, починаючи з останнього і закінчуючи
// першим, та для кожного можливого розташування символу в кільці, ми знаходимо мінімальну кількість кроків,
// необхідних для переходу до наступного символу ключа, та додаємо цю кількість до мінімальної кількості кроків,
// необхідних для написання наступного символу ключа.
//Нарешті, ми повертаємо значення dp[0][0] (мінімальна кількість кроків для написання всього ключа, починаючи з
// першого символу кільця) плюс довжину ключа, оскільки кожне натискання центральної кнопки також враховується як крок.
//Таким чином, ми отримуємо мінімальну кількість кроків, необхідних для написання заданого ключа за допомогою
// обертання кільця.

//In the video game Fallout 4, the quest "Road to Freedom" requires players to reach a metal dial called the
// "Freedom Trail Ring" and use the dial to spell a specific keyword to open the door.
//Given a string ring that represents the code engraved on the outer ring and another string key that represents
// the keyword that needs to be spelled, return the minimum number of steps to spell all the characters in the keyword.
//Initially, the first character of the ring is aligned at the "12:00" direction. You should spell all the
// characters in key one by one by rotating ring clockwise or anticlockwise to make each character of the string
// key aligned at the "12:00" direction and then by pressing the center button.
//At the stage of rotating the ring to spell the key character key[i]:
//You can rotate the ring clockwise or anticlockwise by one place, which counts as one step. The final purpose of the
// rotation is to align one of ring's characters at the "12:00" direction, where this character must equal key[i].
//If the character key[i] has been aligned at the "12:00" direction, press the center button to spell, which also
// counts as one step. After the pressing, you could begin to spell the next character in the
// key (next stage). Otherwise, you have finished all the spelling.
//
//Example 1:
//Input: ring = "godding", key = "gd"
//Output: 4
//Explanation:
//For the first key character 'g', since it is already in place, we just need 1 step to spell this character.
//For the second key character 'd', we need to rotate the ring "godding" anticlockwise by two steps to make
// it become "ddinggo".
//Also, we need 1 more step for spelling.
//So the final output is 4.

//Example 2:
//Input: ring = "godding", key = "godding"
//Output: 13
//
//Constraints:
//1 <= ring.length, key.length <= 100
//ring and key consist of only lower case English letters.
//It is guaranteed that key could always be spelled by rotating ring.
