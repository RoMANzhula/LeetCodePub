package P1101_1200.P1105_Filling_Bookcase_Shelves;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[][] books1 = {{1, 1}, {2, 3}, {2, 3}, {1, 1}, {1, 1}, {1, 1}, {1, 2}};
        int shelfWidth1 = 4;
        System.out.println(solution.minHeightShelves(books1, shelfWidth1)); // Output: 6

        int[][] books2 = {{1, 3}, {2, 4}, {3, 2}};
        int shelfWidth2 = 6;
        System.out.println(solution.minHeightShelves(books2, shelfWidth2)); // Output: 4
    }

    public int minHeightShelves(int[][] books, int shelfWidth) {
        int n = books.length;
        int[] dp = new int[n + 1];
        dp[0] = 0; // No books, no height

        for (int i = 1; i <= n; i++) {
            int width = 0;
            int height = 0;
            dp[i] = Integer.MAX_VALUE;
            // Try to place the i-th book on the same shelf with previous books
            for (int j = i; j > 0; j--) {
                width += books[j - 1][0];
                if (width > shelfWidth) break;
                height = Math.max(height, books[j - 1][1]);
                dp[i] = Math.min(dp[i], dp[j - 1] + height);
            }
        }

        return dp[n];
    }
}

//Explanation of the Code
//Initialization: We initialize the DP array with size n + 1, where n is the number of books. dp[0] is set to 0 because
// no books mean no height.
//Main Loop: We iterate from 1 to n, considering each book. For each book i, we try to place it on the current shelf
// or start a new shelf by iterating backwards from i to 1.
//Width and Height Calculation: We maintain the cumulative width and maximum height for books considered on the
// same shelf. If adding the current book exceeds the shelf width, we break out of the loop.
//DP Update: We update the dp[i] by considering the minimum height achieved by placing the current book and all
// possible previous books on the same shelf.
//Result: Finally, the value dp[n] will give us the minimum height of the bookshelf for all the books.
//This approach ensures that we explore all possible configurations efficiently and find the minimum height for
// the bookshelf.


//You are given an array books where books[i] = [thicknessi, heighti] indicates the thickness and height of the
// ith book. You are also given an integer shelfWidth.
//We want to place these books in order onto bookcase shelves that have a total width shelfWidth.
//We choose some of the books to place on this shelf such that the sum of their thickness is less than or equal to
// shelfWidth, then build another level of the shelf of the bookcase so that the total height of the bookcase has
// increased by the maximum height of the books we just put down. We repeat this process until there are no
// more books to place.
//Note that at each step of the above process, the order of the books we place is the same order as the
// given sequence of books.
//For example, if we have an ordered list of 5 books, we might place the first and second book onto the first
// shelf, the third book on the second shelf, and the fourth and fifth book on the last shelf.
//Return the minimum possible height that the total bookshelf can be after placing shelves in this manner.
//
//Example 1:
//Input: books = [[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]], shelfWidth = 4
//Output: 6
//Explanation:
//The sum of the heights of the 3 shelves is 1 + 3 + 2 = 6.
//Notice that book number 2 does not have to be on the first shelf.

//Example 2:
//Input: books = [[1,3],[2,4],[3,2]], shelfWidth = 6
//Output: 4
//
//Constraints:
//1 <= books.length <= 1000
//1 <= thicknessi <= shelfWidth <= 1000
//1 <= heighti <= 1000