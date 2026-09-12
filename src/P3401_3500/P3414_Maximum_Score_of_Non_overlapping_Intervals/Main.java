package P3401_3500.P3414_Maximum_Score_of_Non_overlapping_Intervals;

import java.util.Arrays;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        List<List<Integer>> intervals1 = Arrays.asList(
                Arrays.asList(1, 3, 2),
                Arrays.asList(4, 5, 2),
                Arrays.asList(1, 5, 5),
                Arrays.asList(6, 9, 3),
                Arrays.asList(6, 7, 1),
                Arrays.asList(8, 9, 1)
        );

        System.out.println(
                Arrays.toString(solution.maximumWeight(intervals1))
        );

        List<List<Integer>> intervals2 = Arrays.asList(
                Arrays.asList(5, 8, 1),
                Arrays.asList(6, 7, 7),
                Arrays.asList(4, 7, 3),
                Arrays.asList(9, 10, 6),
                Arrays.asList(7, 8, 2),
                Arrays.asList(11, 14, 3),
                Arrays.asList(3, 5, 5)
        );

        System.out.println(
                Arrays.toString(solution.maximumWeight(intervals2))
        );
    }

    private static final int MAX_K = 4;

    private static class Interval {
        int left;
        int right;
        int weight;
        int index;

        Interval(int left, int right, int weight, int index) {
            this.left = left;
            this.right = right;
            this.weight = weight;
            this.index = index;
        }
    }

    private static class State {
        long score;
        int[] indices;

        State(long score, int[] indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        Interval[] arr = new Interval[n];

        for (int i = 0; i < n; i++) {
            List<Integer> interval = intervals.get(i);

            arr[i] = new Interval(
                    interval.get(0),
                    interval.get(1),
                    interval.get(2),
                    i
            );
        }

        Arrays.sort(arr, (a, b) -> {
            if (a.right != b.right) {
                return Integer.compare(a.right, b.right);
            }

            if (a.left != b.left) {
                return Integer.compare(a.left, b.left);
            }

            return Integer.compare(a.index, b.index);
        });

        int[] right = new int[n];

        for (int i = 0; i < n; i++) {
            right[i] = arr[i].right;
        }

        int[] previous = new int[n];

        for (int i = 0; i < n; i++) {
            previous[i] = lowerBound(
                    right,
                    0,
                    i,
                    arr[i].left
            );
        }

        State[][] dp = new State[MAX_K + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            dp[0][i] = new State(0, new int[0]);
        }

        for (int k = 1; k <= MAX_K; k++) {
            for (int i = 1; i <= n; i++) {
                State skip = dp[k][i - 1];

                Interval current = arr[i - 1];

                State previousState = dp[k - 1][previous[i - 1]];

                State take = null;

                if (previousState != null) {
                    int[] candidateIndices = addIndex(
                            previousState.indices,
                            current.index
                    );

                    take = new State(
                            previousState.score + current.weight,
                            candidateIndices
                    );
                }

                dp[k][i] = better(take, skip);
            }
        }

        State answer = dp[0][n];

        for (int k = 1; k <= MAX_K; k++) {
            State candidate = dp[k][n];

            if (isBetter(candidate, answer)) {
                answer = candidate;
            }
        }

        return answer.indices;
    }

    private int lowerBound(
            int[] array,
            int from,
            int to,
            int target
    ) {
        int left = from;
        int right = to;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (array[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private int[] addIndex(int[] indices, int index) {
        int[] result = Arrays.copyOf(
                indices,
                indices.length + 1
        );

        result[result.length - 1] = index;

        Arrays.sort(result);

        return result;
    }

    private State better(State a, State b) {
        if (a == null) {
            return b;
        }

        if (b == null) {
            return a;
        }

        return isBetter(a, b) ? a : b;
    }

    private boolean isBetter(State candidate, State current) {
        if (candidate == null) {
            return false;
        }

        if (current == null) {
            return true;
        }

        if (candidate.score != current.score) {
            return candidate.score > current.score;
        }

        return compareLexicographically(
                candidate.indices,
                current.indices
        ) < 0;
    }

    private int compareLexicographically(int[] a, int[] b) {
        int minLength = Math.min(a.length, b.length);

        for (int i = 0; i < minLength; i++) {
            if (a[i] != b[i]) {
                return Integer.compare(a[i], b[i]);
            }
        }

        return Integer.compare(a.length, b.length);
    }

}

//Complexity:
// time - O(n log n)
// space - O(n)


//You are given a 2D integer array intervals, where intervals[i] = [li, ri, weighti]. Interval i starts at position li
// and ends at ri, and has a weight of weighti. You can choose up to 4 non-overlapping intervals. The score of the
// chosen intervals is defined as the total sum of their weights.
//Return the lexicographically smallest array of at most 4 indices from intervals with maximum score, representing your
// choice of non-overlapping intervals.
//Two intervals are said to be non-overlapping if they do not share any points. In particular, intervals sharing a
// left or right boundary are considered overlapping.

//Example 1:
//Input: intervals = [[1,3,2],[4,5,2],[1,5,5],[6,9,3],[6,7,1],[8,9,1]]
//Output: [2,3]
//Explanation:
//You can choose the intervals with indices 2, and 3 with respective weights of 5, and 3.

//Example 2:
//Input: intervals = [[5,8,1],[6,7,7],[4,7,3],[9,10,6],[7,8,2],[11,14,3],[3,5,5]]
//Output: [1,3,5,6]
//Explanation:
//You can choose the intervals with indices 1, 3, 5, and 6 with respective weights of 7, 6, 3, and 5.

//Constraints:
//1 <= intevals.length <= 5 * 104
//intervals[i].length == 3
//intervals[i] = [li, ri, weighti]
//1 <= li <= ri <= 109
//1 <= weighti <= 109
