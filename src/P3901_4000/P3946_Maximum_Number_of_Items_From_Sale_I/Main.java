package P3901_4000.P3946_Maximum_Number_of_Items_From_Sale_I;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] items1 = {
                {6, 2},
                {2, 6},
                {3, 4}
        };

        System.out.println(solution.maximumSaleItems(items1, 9)); // Expected: 4

        int[][] items2 = {
                {2, 4},
                {3, 2},
                {4, 1},
                {6, 4},
                {12, 4}
        };

        System.out.println(solution.maximumSaleItems(items2, 8)); // Expected: 10
    }

    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int minPrice = Integer.MAX_VALUE;

        int[] values = new int[n];

        for (int i = 0; i < n; i++) {
            minPrice = Math.min(minPrice, items[i][1]);

            int freeCount = 0;

            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % items[i][0] == 0) {
                    freeCount++;
                }
            }

            values[i] = 1 + freeCount;
        }

        int[] dp = new int[budget + 1];
        java.util.Arrays.fill(dp, -1);

        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            int price = items[i][1];
            int value = values[i];

            for (int j = budget; j >= price; j--) {
                if (dp[j - price] != -1) {
                    dp[j] = Math.max(
                            dp[j],
                            dp[j - price] + value
                    );
                }
            }
        }

        int answer = 0;

        for (int spent = 0; spent <= budget; spent++) {
            if (dp[spent] == -1) {
                continue;
            }

            int remaining = budget - spent;

            int additionalCopies = remaining / minPrice;

            answer = Math.max(
                    answer,
                    dp[spent] + additionalCopies
            );
        }

        return answer;
    }

}


//You are given a 2D integer array items, where items[i] = [factori, pricei] represents the ith item. You are also
// given an integer budget.
//There are unlimited copies of each item available for purchase.You may buy any number of copies of any items such
// that the total cost of the purchased copies is at most budget.
//After buying items, you may receive free copies according to the following rules:
//For each item i that you bought at least one copy of, you receive one free copy of every item j such that j != i and
// factori divides factorj.
//Buying multiple copies of the same item i does not give additional free copies through item i.
//The same item j can be received multiple times for free if it is received from purchases of different item types.
//Return the maximum total number of item copies you can obtain, including both purchased copies and free copies,
// while spending at most budget on purchased items.

//Example 1:
//Input: items = [[6,2],[2,6],[3,4]], budget = 9
//Output: 4
//Explanation:
//You can buy 2 copies of item 0 and 1 copy of item 2 for a total cost of 2 * 2 + 4 = 8, which is not greater
// than budget = 9.
//Buying item 2 gives 1 free copy of item 0, because factor2 = 3 divides factor0 = 6.
//You leave with 3 purchased copies and 1 free copy, for a total of 4 item copies.

//Example 2:
//Input: items = [[2,4],[3,2],[4,1],[6,4],[12,4]], budget = 8
//Output: 10
//Explanation:
//You can buy 1 copy of item 0, 1 copy of item 1, and 2 copies of item 2 for a total cost of 4 + 2 + 2 * 1 = 8.
//Buying item 0 gives 1 free copy of items 2, 3, and 4.
//Buying item 1 gives 1 free copy of items 3 and 4.
//Buying item 2 gives 1 free copy of item 4.
//Thus, you receive 6 free copies. You leave with 4 purchased copies and 6 free copies, for a total of 10 item copies.

//Constraints:
//1 <= items.length <= 1000
//items[i] = [factori, pricei]
//1 <= factori, pricei <= 1500
//1 <= budget <= 1500
