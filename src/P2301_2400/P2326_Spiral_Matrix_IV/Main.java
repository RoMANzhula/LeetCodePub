package P2301_2400.P2326_Spiral_Matrix_IV;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] list1 = {3, 0, 2, 6, 8, 1, 7, 9, 4, 2, 5, 5, 0};
        ListNode head1 = createLinkedList(list1);
        int m1 = 3, n1 = 5;
        int[][] matrix1 = solution.spiralMatrix(m1, n1, head1);
        System.out.println("Example 1:");
        printMatrix(matrix1);  // Output: [[3, 0, 2, 6, 8], [5, 0, -1, -1, 1], [5, 2, 4, 9, 7]]
        System.out.println();

        int[] list2 = {0, 1, 2};
        ListNode head2 = createLinkedList(list2);
        int m2 = 1, n2 = 4;
        int[][] matrix2 = solution.spiralMatrix(m2, n2, head2);
        System.out.println("Example 2:");
        printMatrix(matrix2);  // Output: [[0, 1, 2, -1]]
        System.out.println();
    }

    public int[][] spiralMatrix(int m, int n, ListNode head) {
        // Initialize the matrix with -1
        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = -1;
            }
        }

        // Boundaries for the spiral traversal
        int top = 0, bottom = m - 1;
        int left = 0, right = n - 1;

        ListNode current = head;

        while (top <= bottom && left <= right && current != null) {
            // Traverse from left to right on the top boundary
            for (int i = left; i <= right && current != null; i++) {
                matrix[top][i] = current.val;
                current = current.next;
            }
            top++;  // Move the top boundary down

            // Traverse from top to bottom on the right boundary
            for (int i = top; i <= bottom && current != null; i++) {
                matrix[i][right] = current.val;
                current = current.next;
            }
            right--;  // Move the right boundary left

            // Traverse from right to left on the bottom boundary
            for (int i = right; i >= left && current != null; i--) {
                matrix[bottom][i] = current.val;
                current = current.next;
            }
            bottom--;  // Move the bottom boundary up

            // Traverse from bottom to top on the left boundary
            for (int i = bottom; i >= top && current != null; i--) {
                matrix[i][left] = current.val;
                current = current.next;
            }
            left++;  // Move the left boundary right
        }

        return matrix;
    }

    // Helper method to create a linked list from an array
    public static ListNode createLinkedList(int[] arr) {
        ListNode dummy = new ListNode();
        ListNode tail = dummy;
        for (int num : arr) {
            tail.next = new ListNode(num);
            tail = tail.next;
        }
        return dummy.next;
    }

    // Helper method to print the matrix
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

//Explanation:
//Matrix Initialization: The matrix is initialized with -1 in all cells using nested loops.
//Four Boundaries: We maintain four boundaries (top, bottom, left, right) that define the current layer of the spiral.
//Spiral Filling:
//Start by filling the top row (left to right), then the right column (top to bottom), the bottom row (right to left),
// and finally the left column (bottom to top).
//After completing one layer, adjust the boundaries to move inward and repeat the process until there are no
// more cells to fill.
//Linked List Traversal: We traverse the linked list, filling the matrix with the node values. When the
// list is exhausted, the remaining matrix cells retain the initial value -1.


//You are given two integers m and n, which represent the dimensions of a matrix.
//You are also given the head of a linked list of integers.
//Generate an m x n matrix that contains the integers in the linked list presented in spiral order (clockwise),
// starting from the top-left of the matrix. If there are remaining empty spaces, fill them with -1.
//Return the generated matrix.
//
//Example 1:
//Input: m = 3, n = 5, head = [3,0,2,6,8,1,7,9,4,2,5,5,0]
//Output: [[3,0,2,6,8],[5,0,-1,-1,1],[5,2,4,9,7]]
//Explanation: The diagram above shows how the values are printed in the matrix.
//Note that the remaining spaces in the matrix are filled with -1.

//Example 2:
//Input: m = 1, n = 4, head = [0,1,2]
//Output: [[0,1,2,-1]]
//Explanation: The diagram above shows how the values are printed from left to right in the matrix.
//The last space in the matrix is set to -1.
//
//Constraints:
//1 <= m, n <= 105
//1 <= m * n <= 105
//The number of nodes in the list is in the range [1, m * n].
//0 <= Node.val <= 1000