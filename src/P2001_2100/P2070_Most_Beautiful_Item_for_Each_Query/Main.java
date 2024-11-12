package P2001_2100.P2070_Most_Beautiful_Item_for_Each_Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[][] items1 = {{1, 2}, {3, 2}, {2, 4}, {5, 6}, {3, 5}};
        int[] queries1 = {1, 2, 3, 4, 5, 6};
        System.out.println("Example 1 Output: " + Arrays.toString(solution.maximumBeauty(items1, queries1))); // Expected Output: [2, 4, 5, 5, 6, 6]

        int[][] items2 = {{1, 2}, {1, 2}, {1, 3}, {1, 4}};
        int[] queries2 = {1};
        System.out.println("Example 2 Output: " + Arrays.toString(solution.maximumBeauty(items2, queries2))); // Expected Output: [4]

        int[][] items3 = {{10, 1000}};
        int[] queries3 = {5};
        System.out.println("Example 3 Output: " + Arrays.toString(solution.maximumBeauty(items3, queries3))); // Expected Output: [0]
    }

    public int[] maximumBeauty(int[][] items, int[] queries) {
        // Sort items by price, then by beauty in descending order (for easier handling)
        Arrays.sort(items, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);

        // Preprocess to create a list of (price, maxBeauty)
        List<int[]> priceBeautyList = new ArrayList<>();
        int maxBeauty = 0;
        for (int[] item : items) {
            maxBeauty = Math.max(maxBeauty, item[1]);
            priceBeautyList.add(new int[] { item[0], maxBeauty });
        }

        // Store the results in the original order of queries
        int[] results = new int[queries.length];
        Integer[] queryIndices = new Integer[queries.length];
        for (int i = 0; i < queries.length; i++) {
            queryIndices[i] = i;
        }

        // Sort queries by value
        Arrays.sort(queryIndices, (a, b) -> Integer.compare(queries[a], queries[b]));

        // Answer each query using binary search
        int index = 0;
        for (int i : queryIndices) {
            int query = queries[i];

            // Use binary search to find the maximum beauty for the given price
            while (index < priceBeautyList.size() && priceBeautyList.get(index)[0] <= query) {
                index++;
            }
            results[i] = index > 0 ? priceBeautyList.get(index - 1)[1] : 0;
        }

        return results;
    }
}

//Explanation of the Code:
//Sorting Items: The items are sorted by price in ascending order, and within the same price, by beauty in
// descending order. This ensures we always have the highest beauty for each price point.
//Precomputing Maximum Beauty: As we iterate through the sorted items, we keep track of the maximum beauty
// seen so far for any given price.
//Sorting Queries: We store the indices of queries, sort them based on query values, and then use a
// two-pointer approach (or binary search) to answer each query efficiently.
//Binary Search for Queries: For each query, we find the maximum beauty of items with prices less than or
// equal to the query’s price by accessing the priceBeautyList.
//
//Complexity Analysis:
//Time Complexity:
//O(N log N+M log M), where N is the length of items and M is the length of queries. Sorting items and queries takes
//O(N log N) and O(M log M) respectively, and binary search in the precomputed list takes
//O(logN) for each query.
//Space Complexity:
//O(N+M), for storing the priceBeautyList and the result array.


//You are given a 2D integer array items where items[i] = [pricei, beautyi] denotes the price and beauty of
// an item respectively.
//You are also given a 0-indexed integer array queries. For each queries[j], you want to determine the
// maximum beauty of an item whose price is less than or equal to queries[j]. If no such item exists,
// then the answer to this query is 0.
//Return an array answer of the same length as queries where answer[j] is the answer to the jth query.
//
//Example 1:
//Input: items = [[1,2],[3,2],[2,4],[5,6],[3,5]], queries = [1,2,3,4,5,6]
//Output: [2,4,5,5,6,6]
//Explanation:
//- For queries[0]=1, [1,2] is the only item which has price <= 1. Hence, the answer for this query is 2.
//- For queries[1]=2, the items which can be considered are [1,2] and [2,4].
//  The maximum beauty among them is 4.
//- For queries[2]=3 and queries[3]=4, the items which can be considered are [1,2], [3,2], [2,4], and [3,5].
//  The maximum beauty among them is 5.
//- For queries[4]=5 and queries[5]=6, all items can be considered.
//  Hence, the answer for them is the maximum beauty of all items, i.e., 6.

//Example 2:
//Input: items = [[1,2],[1,2],[1,3],[1,4]], queries = [1]
//Output: [4]
//Explanation:
//The price of every item is equal to 1, so we choose the item with the maximum beauty 4.
//Note that multiple items can have the same price and/or beauty.

//Example 3:
//Input: items = [[10,1000]], queries = [5]
//Output: [0]
//Explanation:
//No item has a price less than or equal to 5, so no item can be chosen.
//Hence, the answer to the query is 0.
//
//Constraints:
//1 <= items.length, queries.length <= 105
//items[i].length == 2
//1 <= pricei, beautyi, queries[j] <= 109
