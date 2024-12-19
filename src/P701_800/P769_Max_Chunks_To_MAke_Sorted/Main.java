package P701_800.P769_Max_Chunks_To_MAke_Sorted;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] arr1 = {4, 3, 2, 1, 0};
        System.out.println("Example 1: " + solution.maxChunksToSorted(arr1)); // output: 1

        int[] arr2 = {1, 0, 2, 3, 4};
        System.out.println("Example 2: " + solution.maxChunksToSorted(arr2)); // Output: 4
    }

    public int maxChunksToSorted(int[] arr) {
        int max = 0;
        int chunks = 0;

        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]); // update the maximum value seen so far
            if (max == i) {
                chunks++; // increment the chunk count when max equals the current index
            }
        }

        return chunks; // rturn the total number of chunks
    }
}

//Explanation:
//Track the Maximum Value: As you iterate through the array, keep track of the maximum value encountered so far.
//Compare with Index: At each index i, if the maximum value is equal to i, it means all elements up to
// index i can form a sorted chunk.
//Count Chunks: Increment the chunk count whenever you identify such a scenario.


//You are given an integer array arr of length n that represents a permutation of the integers in the range [0, n - 1].
//We split arr into some number of chunks (i.e., partitions), and individually sort each chunk. After
// concatenating them, the result should equal the sorted array.
//Return the largest number of chunks we can make to sort the array.
//
//Example 1:
//Input: arr = [4,3,2,1,0]
//Output: 1
//Explanation:
//Splitting into two or more chunks will not return the required result.
//For example, splitting into [4, 3], [2, 1, 0] will result in [3, 4, 0, 1, 2], which isn't sorted.

//Example 2:
//Input: arr = [1,0,2,3,4]
//Output: 4
//Explanation:
//We can split into two chunks, such as [1, 0], [2, 3, 4].
//However, splitting into [1, 0], [2], [3], [4] is the highest number of chunks possible.
//
//Constraints:
//n == arr.length
//1 <= n <= 10
//0 <= arr[i] < n
//All the elements of arr are unique.
