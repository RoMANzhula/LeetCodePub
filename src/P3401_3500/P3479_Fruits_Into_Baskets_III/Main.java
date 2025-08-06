package P3401_3500.P3479_Fruits_Into_Baskets_III;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] fruits1 = {4, 2, 5};
        int[] baskets1 = {3, 5, 4};
        System.out.println(solution.numOfUnplacedFruits(fruits1, baskets1)); // Output: 1

        int[] fruits2 = {3, 6, 1};
        int[] baskets2 = {6, 4, 7};
        System.out.println(solution.numOfUnplacedFruits(fruits2, baskets2)); // Output: 0
    }

    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        SegmentTree tree = new SegmentTree(baskets);
        int unplaced = 0;

        for (int fruit : fruits) {
            if (tree.queryFirst(fruit) == -1) {
                unplaced++;
            }
        }

        return unplaced;
    }
}

class SegmentTree {

    private int[] tree;
    private int n;

    public SegmentTree(int[] nums) {
        this.n = nums.length;
        this.tree = new int[4 * n];
        build(nums, 0, 0, n - 1);
    }

    // updates baskets[i] to val
    public void update(int i, int val) {
        update(0, 0, n - 1, i, val);
    }

    // returns the first index i where baskets[i] >= target, or -1 if not found
    public int queryFirst(int target) {
        return queryFirst(0, 0, n - 1, target);
    }

    private void build(int[] nums, int treeIndex, int lo, int hi) {
        if (lo == hi) {
            tree[treeIndex] = nums[lo];
            return;
        }
        int mid = (lo + hi) / 2;
        build(nums, 2 * treeIndex + 1, lo, mid);
        build(nums, 2 * treeIndex + 2, mid + 1, hi);
        tree[treeIndex] = Math.max(tree[2 * treeIndex + 1], tree[2 * treeIndex + 2]);
    }

    private void update(int treeIndex, int lo, int hi, int i, int val) {
        if (lo == hi) {
            tree[treeIndex] = val;
            return;
        }
        int mid = (lo + hi) / 2;
        if (i <= mid)
            update(2 * treeIndex + 1, lo, mid, i, val);
        else
            update(2 * treeIndex + 2, mid + 1, hi, i, val);
        tree[treeIndex] = Math.max(tree[2 * treeIndex + 1], tree[2 * treeIndex + 2]);
    }

    private int queryFirst(int treeIndex, int lo, int hi, int target) {
        if (tree[treeIndex] < target) return -1;

        if (lo == hi) {
            update(0, 0, n - 1, lo, -1); // mark as used
            return lo;
        }

        int mid = (lo + hi) / 2;
        int left = tree[2 * treeIndex + 1];

        if (left >= target) return queryFirst(2 * treeIndex + 1, lo, mid, target);
        else return queryFirst(2 * treeIndex + 2, mid + 1, hi, target);
    }

}

//Complexity:
// time - O(n log n)
// space - O(n)


//You are given two arrays of integers, fruits and baskets, each of length n, where fruits[i] represents the
// quantity of the ith type of fruit, and baskets[j] represents the capacity of the jth basket.
//From left to right, place the fruits according to these rules:
//Each fruit type must be placed in the leftmost available basket with a capacity greater than or equal to the
// quantity of that fruit type.
//Each basket can hold only one type of fruit.
//If a fruit type cannot be placed in any basket, it remains unplaced.
//Return the number of fruit types that remain unplaced after all possible allocations are made.

//Example 1:
//Input: fruits = [4,2,5], baskets = [3,5,4]
//Output: 1
//Explanation:
//fruits[0] = 4 is placed in baskets[1] = 5.
//fruits[1] = 2 is placed in baskets[0] = 3.
//fruits[2] = 5 cannot be placed in baskets[2] = 4.
//Since one fruit type remains unplaced, we return 1.

//Example 2:
//Input: fruits = [3,6,1], baskets = [6,4,7]
//Output: 0
//Explanation:
//fruits[0] = 3 is placed in baskets[0] = 6.
//fruits[1] = 6 cannot be placed in baskets[1] = 4 (insufficient capacity) but can be placed in the next
// available basket, baskets[2] = 7.
//fruits[2] = 1 is placed in baskets[1] = 4.
//Since all fruits are successfully placed, we return 0.

//Constraints:
//n == fruits.length == baskets.length
//1 <= n <= 105
//1 <= fruits[i], baskets[i] <= 109
