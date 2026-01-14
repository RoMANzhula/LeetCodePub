package P3401_3500.P3454_Separate_Squares_II;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[][] squares1 = {{0, 0, 1}, {2, 2, 1}};
        System.out.println(solution.separateSquares(squares1)); // 1.0

        int[][] squares2 = {{0, 0, 2}, {1, 1, 1}};
        System.out.println(solution.separateSquares(squares2)); // 1.0
    }

    public double separateSquares(int[][] squares) {
        List<Event> events = new ArrayList<>();
        TreeSet<Long> xSet = new TreeSet<>();

        for (int[] s : squares) {
            long x1 = s[0];
            long y1 = s[1];
            long l = s[2];
            long x2 = x1 + l;
            long y2 = y1 + l;

            events.add(new Event(y1, x1, x2, +1));
            events.add(new Event(y2, x1, x2, -1));

            xSet.add(x1);
            xSet.add(x2);
        }

        // coordinate compression
        long[] xs = new long[xSet.size()];
        int idx = 0;
        for (long x : xSet) xs[idx++] = x;

        Map<Long, Integer> xIndex = new HashMap<>();
        for (int i = 0; i < xs.length; i++) {
            xIndex.put(xs[i], i);
        }

        events.sort(Comparator.comparingLong(e -> e.y));
        SegmentTree st = new SegmentTree(xs);

        // total area
        long prevY = events.get(0).y;
        long totalArea = 0;

        for (Event e : events) {
            long curY = e.y;
            long coveredX = st.query();
            totalArea += coveredX * (curY - prevY);

            st.update(
                    xIndex.get(e.x1),
                    xIndex.get(e.x2),
                    e.type
            );

            prevY = curY;
        }

        double half = totalArea / 2.0;

        // find minimal y
        st = new SegmentTree(xs);
        prevY = events.get(0).y;
        double areaSoFar = 0;

        for (Event e : events) {
            long curY = e.y;
            long coveredX = st.query();
            double segmentArea = coveredX * (curY - prevY);

            if (areaSoFar + segmentArea >= half) {
                double dy = (half - areaSoFar) / coveredX;
                return prevY + dy;
            }

            areaSoFar += segmentArea;

            st.update(
                    xIndex.get(e.x1),
                    xIndex.get(e.x2),
                    e.type
            );

            prevY = curY;
        }

        return prevY;
    }

}

class Event {
    long y;
    long x1, x2;
    int type; // +1 add, -1 remove

    Event(long y, long x1, long x2, int type) {
        this.y = y;
        this.x1 = x1;
        this.x2 = x2;
        this.type = type;
    }
}

class SegmentTree {
    int n;
    int[] count;
    long[] length;
    long[] xs;

    SegmentTree(long[] xs) {
        this.xs = xs;
        this.n = xs.length - 1;
        count = new int[4 * n];
        length = new long[4 * n];
    }

    void update(int node, int l, int r, int ql, int qr, int val) {
        if (ql >= r || qr <= l) return;
        if (ql <= l && r <= qr) {
            count[node] += val;
        } else {
            int mid = (l + r) / 2;
            update(node * 2, l, mid, ql, qr, val);
            update(node * 2 + 1, mid, r, ql, qr, val);
        }

        if (count[node] > 0) {
            length[node] = xs[r] - xs[l];
        } else if (l + 1 == r) {
            length[node] = 0;
        } else {
            length[node] = length[node * 2] + length[node * 2 + 1];
        }
    }

    void update(int l, int r, int val) {
        update(1, 0, n, l, r, val);
    }

    long query() {
        return length[1];
    }
}

//Complexity:
// time - O(n log n)
// space - O(n)


//You are given a 2D integer array squares. Each squares[i] = [xi, yi, li] represents the coordinates of the
// bottom-left point and the side length of a square parallel to the x-axis.
//Find the minimum y-coordinate value of a horizontal line such that the total area covered by squares above the
// line equals the total area covered by squares below the line.
//Answers within 10-5 of the actual answer will be accepted.
//Note: Squares may overlap. Overlapping areas should be counted only once in this version.

//Example 1:
//Input: squares = [[0,0,1],[2,2,1]]
//Output: 1.00000
//Explanation:
//Any horizontal line between y = 1 and y = 2 results in an equal split, with 1 square unit above and 1 square
// unit below. The minimum y-value is 1.

//Example 2:
//Input: squares = [[0,0,2],[1,1,1]]
//Output: 1.00000
//Explanation:
//Since the blue square overlaps with the red square, it will not be counted again. Thus, the line y = 1 splits
// the squares into two equal parts.

//Constraints:
//1 <= squares.length <= 5 * 104
//squares[i] = [xi, yi, li]
//squares[i].length == 3
//0 <= xi, yi <= 109
//1 <= li <= 109
//The total area of all the squares will not exceed 1015.
