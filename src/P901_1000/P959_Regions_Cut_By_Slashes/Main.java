package P901_1000.P959_Regions_Cut_By_Slashes;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        // Test case 1
        String[] grid1 = { " /", "/ " };
        System.out.println("Test Case 1: " + solution.regionsBySlashes(grid1)); // Output: 2

        // Test case 2
        String[] grid2 = { " /", "  " };
        System.out.println("Test Case 2: " + solution.regionsBySlashes(grid2)); // Output: 1

        // Test case 3
        String[] grid3 = { "/\\", "\\/" };
        System.out.println("Test Case 3: " + solution.regionsBySlashes(grid3)); // Output: 5
    }

    public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        UnionFind uf = new UnionFind(4 * n * n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int root = 4 * (i * n + j);
                char val = grid[i].charAt(j);

                // Union internal triangles
                if (val == '/') {
                    uf.union(root + 0, root + 3);
                    uf.union(root + 1, root + 2);
                } else if (val == '\\') {
                    uf.union(root + 0, root + 1);
                    uf.union(root + 2, root + 3);
                } else {
                    uf.union(root + 0, root + 1);
                    uf.union(root + 1, root + 2);
                    uf.union(root + 2, root + 3);
                }

                // Union with right neighbor
                if (j + 1 < n) {
                    uf.union(root + 1, 4 * (i * n + j + 1) + 3);
                }

                // Union with bottom neighbor
                if (i + 1 < n) {
                    uf.union(root + 2, 4 * ((i + 1) * n + j) + 0);
                }
            }
        }

        int regions = 0;
        for (int i = 0; i < 4 * n * n; i++) {
            if (uf.find(i) == i) {
                regions++;
            }
        }

        return regions;
    }
}

class UnionFind {
    int[] parent;
    int[] rank;

    UnionFind(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }
}


//Explanation:
//UnionFind class: Manages the union-find operations, keeping track of parent and rank.
//regionsBySlashes: Iterates over each cell in the grid, unions triangles within each cell based on the
// character ('/', '\\', or ' '), and between adjacent cells to properly account for connected regions.


//An n x n grid is composed of 1 x 1 squares where each 1 x 1 square consists of a '/', '\', or blank space ' '. These
// characters divide the square into contiguous regions.
//Given the grid grid represented as a string array, return the number of regions.
//Note that backslash characters are escaped, so a '\' is represented as '\\'.
//
//Example 1:
//Input: grid = [" /","/ "]
//Output: 2

//Example 2:
//Input: grid = [" /","  "]
//Output: 1

//Example 3:
//Input: grid = ["/\\","\\/"]
//Output: 5
//Explanation: Recall that because \ characters are escaped, "\\/" refers to \/, and "/\\" refers to /\.
//
//Constraints:
//n == grid.length == grid[i].length
//1 <= n <= 30
//grid[i][j] is either '/', '\', or ' '.
