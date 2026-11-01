package P401_500.P453_Minimum_Moves_to_Equal_Array_Elements;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.minMoves(new int[]{1, 2, 3})); // 3
        System.out.println(solution.minMoves(new int[]{1, 1, 1})); // 0
    }

    public int minMoves(int[] nums) {
        int min = nums[0];
        int moves = 0;

        for (int num : nums) {
            min = Math.min(min, num);
        }

        for (int num : nums) {
            moves += num - min;
        }

        return moves;
    }

}

//Complexity:
// time - O(n)
// space - O(1)


//Given an integer array nums of size n, return the minimum number of moves required to make all array elements equal.
//In one move, you can increment n - 1 elements of the array by 1.

//Example 1:
//Input: nums = [1,2,3]
//Output: 3
//Explanation: Only three moves are needed (remember each move increments two elements):
//[1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]

//Example 2:
//Input: nums = [1,1,1]
//Output: 0

//Constraints:
//n == nums.length
//1 <= nums.length <= 105
//-109 <= nums[i] <= 109
//The answer is guaranteed to fit in a 32-bit integer.
