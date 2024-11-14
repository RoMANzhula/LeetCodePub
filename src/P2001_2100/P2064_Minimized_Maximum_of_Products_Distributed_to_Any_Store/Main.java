package P2001_2100.P2064_Minimized_Maximum_of_Products_Distributed_to_Any_Store;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int n1 = 6;
        int[] quantities1 = {11, 6};
        int result1 = solution.minimizedMaximum(n1, quantities1);
        System.out.println("Example 1 Output: " + result1);  // Expected output: 3

        int n2 = 7;
        int[] quantities2 = {15, 10, 10};
        int result2 = solution.minimizedMaximum(n2, quantities2);
        System.out.println("Example 2 Output: " + result2);  // Expected output: 5

        int n3 = 1;
        int[] quantities3 = {100000};
        int result3 = solution.minimizedMaximum(n3, quantities3);
        System.out.println("Example 3 Output: " + result3);  // Expected output: 100000
    }

    public int minimizedMaximum(int n, int[] quantities) {
        int left = 1, right = getMax(quantities);

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canDistribute(quantities, n, mid)) {
                right = mid;  // Try for a smaller maximum if feasible
            } else {
                left = mid + 1;  // Increase the maximum if not feasible
            }
        }

        return left;  // The minimized maximum number of products per store
    }

    private boolean canDistribute(int[] quantities, int n, int maxProductsPerStore) {
        int storesNeeded = 0;
        for (int quantity : quantities) {
            storesNeeded += (quantity + maxProductsPerStore - 1) / maxProductsPerStore;  // Ceiling division
            if (storesNeeded > n) return false;  // More stores than available, so not feasible
        }
        return true;
    }

    private int getMax(int[] quantities) {
        int max = 0;
        for (int quantity : quantities) {
            max = Math.max(max, quantity);
        }
        return max;
    }
}

//Explanation of Key Parts:
//canDistribute Function:
//This function checks if it’s feasible to distribute the products with a given maxProductsPerStore.
//For each quantity, it calculates the number of stores required using ceiling division:
//stores = quantity+maxProductsPerStore−1 / maxProductsPerStore
//If the total storesNeeded exceeds
//n, the current midpoint is not feasible.
//Binary Search:
//The binary search gradually narrows down the minimum feasible maximum
//x by adjusting the left and right bounds based on the feasibility check.
//Complexity Analysis:
//Time Complexity:
//O(m log(max(quantities))), where
//m is the length of quantities. The binary search runs in
//O(log(max(quantities))) steps, and each step checks the distribution feasibility in O(m).
//Space Complexity:
//O(1), as we use a constant amount of additional space.


//You are given an integer n indicating there are n specialty retail stores. There are m product types of
// varying amounts, which are given as a 0-indexed integer array quantities, where quantities[i] represents
// the number of products of the ith product type.
//You need to distribute all products to the retail stores following these rules:
//A store can only be given at most one product type but can be given any amount of it.
//After distribution, each store will have been given some number of products (possibly 0). Let x represent the
// maximum number of products given to any store. You want x to be as small as possible, i.e., you want to
// minimize the maximum number of products that are given to any store.
//Return the minimum possible x.
//
//Example 1:
//Input: n = 6, quantities = [11,6]
//Output: 3
//Explanation: One optimal way is:
//- The 11 products of type 0 are distributed to the first four stores in these amounts: 2, 3, 3, 3
//- The 6 products of type 1 are distributed to the other two stores in these amounts: 3, 3
//The maximum number of products given to any store is max(2, 3, 3, 3, 3, 3) = 3.

//Example 2:
//Input: n = 7, quantities = [15,10,10]
//Output: 5
//Explanation: One optimal way is:
//- The 15 products of type 0 are distributed to the first three stores in these amounts: 5, 5, 5
//- The 10 products of type 1 are distributed to the next two stores in these amounts: 5, 5
//- The 10 products of type 2 are distributed to the last two stores in these amounts: 5, 5
//The maximum number of products given to any store is max(5, 5, 5, 5, 5, 5, 5) = 5.

//Example 3:
//Input: n = 1, quantities = [100000]
//Output: 100000
//Explanation: The only optimal way is:
//- The 100000 products of type 0 are distributed to the only store.
//The maximum number of products given to any store is max(100000) = 100000.
//
//Constraints:
//m == quantities.length
//1 <= m <= n <= 105
//1 <= quantities[i] <= 105
